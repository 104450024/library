package com.irm.demo.web.admin;

import com.irm.demo.po.Blog;
import com.irm.demo.po.LibraryUsers;
import com.irm.demo.po.UserBlog;
import com.irm.demo.service.BlogService;
import com.irm.demo.service.UserBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/blogs")
public class UserBlogController {

    public final UserBlogService userBlogService;
    public final BlogService blogService;
    boolean u=true;


    @Autowired
    public UserBlogController(UserBlogService userBlogService, BlogService blogService) {
        this.userBlogService = userBlogService;
        this.blogService = blogService;
    }
    @GetMapping()
    public String inputPage(UserBlog userBlog,Model model)
    {

        System.out.println(userBlog.getName());
        return "admin/inputtest";

    }

    @PostMapping("/blog")
    public String post(UserBlog userBlog,
                       Blog blog,
                       LibraryUsers libraryUsers,
                       final RedirectAttributes attributes, Model model)
    {
        UserBlog userBlog1= userBlogService.saveBlog(userBlog);


         int  x=blogService.findByTitle(userBlog1.getTitle()).getPoints();
              x=x+1;
         long point=blogService.findByTitle(userBlog1.getTitle()).getId();
         System.out.println(x);
         System.out.println("-----------------------");
         System.out.println(point);
         blogService.getAndConvertPoint(point).setPoints(x);
         System.out.println(blogService.getAndConvert(point).getPoints());

            if (userBlog1!=null)
            {
                attributes.addAttribute("message"," <"+userBlog1.getName()+" 信息提交成功");
            }
            return "redirect:/";

    }

    @GetMapping("/libraryblogs")
    public String blogs(@PageableDefault(size = 3) Pageable pageable,
                         UserBlog userBlog,
                         Model model) {

        model.addAttribute("page", userBlogService.listBlog(pageable));

        return "admin/LibraryUserBlogs";

    }

    /*@PostMapping("/libraryblogs/search")
    public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {


        model.addAttribute("page", userBlogService.listuserBlog(query, pageable));
        model.addAttribute("query", query);
        return "search";
    }*/



}
