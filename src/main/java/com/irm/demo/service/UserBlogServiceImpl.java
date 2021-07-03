package com.irm.demo.service;

import com.irm.demo.dao.UserBlogRepository;
import com.irm.demo.po.Blog;
import com.irm.demo.po.UserBlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class UserBlogServiceImpl implements UserBlogService{



    public final UserBlogRepository userBlogRepository;

    @Autowired
    public UserBlogServiceImpl(UserBlogRepository userBlogRepository) {
        this.userBlogRepository = userBlogRepository;
    }




    @Override
    public UserBlog saveBlog(UserBlog userblog) {

        Date date = new Date();
        userblog.setCreateTime(date);

        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.MONTH, 1);
        dt = c.getTime();
        userblog.setUntilTime(dt);

        return userBlogRepository.save(userblog);
    }

    @Override
    public Page<UserBlog> listBlog(Pageable pageable) {
        return userBlogRepository.findAll(pageable);
    }
    @Override //search 在用的
    public Page<UserBlog> listuserBlog(String query, Pageable pageable) {
        return userBlogRepository.findByUserQuery(query,pageable);
    }







}
