package com.irm.demo.service;

import com.irm.demo.po.Blog;
import com.irm.demo.po.Tag;
import com.irm.demo.po.UserBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserBlogService {


    UserBlog saveBlog(UserBlog userblog);

    Page<UserBlog> listBlog(Pageable pageable);

    Page<UserBlog> listuserBlog(String query, Pageable pageable);





}
