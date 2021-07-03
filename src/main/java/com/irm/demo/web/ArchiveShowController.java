package com.irm.demo.web;

import com.irm.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by limi on 2017/10/23.
 */
@Controller
public class ArchiveShowController {



    private final BlogService blogService;

    @Autowired
    public ArchiveShowController(BlogService blogService) {
        this.blogService = blogService;
    }


    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap", blogService.archiveBlog());
        model.addAttribute("blogCount" ,  blogService.countBlog());
        return "archives";
    }
}
