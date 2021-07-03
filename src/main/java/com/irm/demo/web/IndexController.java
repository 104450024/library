package com.irm.demo.web;

import com.irm.demo.po.LibraryUsers;
import com.irm.demo.po.UserBlog;
import com.irm.demo.service.BlogService;
import com.irm.demo.service.LibarayUserService;
import com.irm.demo.service.TagService;
import com.irm.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by limi on 2017/10/13.
 */
@Controller
public class IndexController {

    private final BlogService blogService;
    private final TypeService typeService;
    private final TagService tagService;
    private final LibarayUserService libarayUserService;

    boolean d=true;
    @Autowired
    public IndexController(BlogService blogService, TypeService typeService, TagService tagService, LibarayUserService libarayUserService) {
        this.blogService = blogService;
        this.typeService = typeService;
        this.tagService = tagService;
        this.libarayUserService = libarayUserService;
    }

    int x=0;
    @GetMapping("/")
    public String index(@PageableDefault(size = 4, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        LibraryUsers libraryUsers,Model model) {
        boolean d=true;
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(2));
        model.addAttribute("tags", tagService.listTagTop(2));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(5));

        System.out.println(d);

        return "index";
    }

     @GetMapping("/kk")
     public String UsersBlogs(){

        return "admin/IntoTheLibraryUsers";
    }


    @PostMapping("/search")
    public String search(@PageableDefault(size = 2, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                         @RequestParam String query, Model model) {
        model.addAttribute("page", blogService.listBlog("%"+query+"%", pageable));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable int id, LibraryUsers libraryUsers, Model model) {

        if (libraryUsers!=null){
        model.addAttribute("blog", blogService.getAndConvert(id));
        }
        return "blog";
    }


    /*@GetMapping("/blog/borrow/{id}")
    public String BorrowBlog(@PathVariable Long id, UserBlog userBlog, LibraryUsers libraryUsers, Model model) {

        System.out.println(libraryUsers.getStatus());
      *//*  if (libraryUsers.getStatus()==null){
            return "redirect:/";
        }*//*

        model.addAttribute("blog", blogService.getAndConvert(id));

        System.out.println(libraryUsers.getUsername());

        return "Borrowblog";
    }*/

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        model.addAttribute("newblogs", blogService.listRecommendBlogTop(3));
        return "_fragments :: newblogList";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.removeAttribute("library_users");
        return "redirect:/";
    }

}
