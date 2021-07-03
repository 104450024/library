package com.irm.demo.dao;

import com.irm.demo.po.Blog;
import com.irm.demo.po.UserBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserBlogRepository extends JpaRepository<UserBlog,Long> {



    @Query("select user from UserBlog  user where user.name like ?1")
    Page<UserBlog> findByUserQuery(String query,Pageable pageable);




}
