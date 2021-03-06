package com.irm.demo.web;

import com.irm.demo.service.BlogService;
import com.irm.demo.service.TagService;
import com.irm.demo.service.TypeService;
import com.irm.demo.po.Tag;
import com.irm.demo.service.BlogService;
import com.irm.demo.service.TagService;
import com.irm.demo.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Created by limi on 2017/10/23.
 */
@Controller
public class TagShowController {


    private final BlogService blogService;
    private final TagService tagService;

    @Autowired
    public TagShowController(BlogService blogService, TypeService typeService, TagService tagService) {
        this.blogService = blogService;
        this.tagService = tagService;
    }

    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model) {
        List<Tag> tags = tagService.listTagTop(10000);
        if (id == -1) {
           id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.listBlog(id,pageable));
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
