package com.irm.demo.web.admin;


import com.irm.demo.po.Blog;
import com.irm.demo.po.LibraryUsers;
import com.irm.demo.po.UserBlog;
import com.irm.demo.service.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class LibraryLoginController {

    public final LibarayUserService libarayUserService;
    public final BlogService blogService;
    private final TypeService typeService;
    private final TagService tagService;
    private final UserBlogService userBlogService;


    public LibraryLoginController(LibarayUserService libarayUserService, UserService userService, BlogService blogService, TypeService typeService, TagService tagService, UserService userService1, UserBlogService userBlogService) {
        this.libarayUserService = libarayUserService;
        this.blogService = blogService;
        this.typeService = typeService;
        this.tagService = tagService;
        this.userBlogService = userBlogService;
    }

    public String x, y;
    public boolean d = true;
    int point = 0;

    @GetMapping
    public String Librarylogin(LibraryUsers libraryUsers) {
        return "admin/LibraryLogin";

    }

    @PostMapping("/login")
    public String UserLogin(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession httpSession,
                            LibraryUsers libraryUsers,
                            UserBlog userBlog,
                            RedirectAttributes attributes,
                            @PageableDefault(size = 3, sort = {"updateTime"},
                                    direction = Sort.Direction.DESC) Pageable pageable,
                            Model model) {
        libraryUsers = libarayUserService.checkLibarayUsers(username, password);

        if (libraryUsers != null) {

            System.out.println(libraryUsers.getStatus());
            libraryUsers = libarayUserService.saveUserTime(libraryUsers);
            libraryUsers.setPassword(null);
            d = false;
            httpSession.setAttribute("library_users", libraryUsers);
            model.addAttribute("d", d);
            model.addAttribute("page", blogService.listBlog(pageable));
            model.addAttribute("types", typeService.listTypeTop(2));
            model.addAttribute("tags", tagService.listTagTop(2));
            model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(5));
            x = libraryUsers.getUsername();
            userBlog.setName(x);
            y = userBlog.getName();
            System.out.println(x);
            System.out.println("--------");
            System.out.println(y);
            return "index";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/users";
        }
    }

    @GetMapping("/logout1")
    public String logout(LibraryUsers libraryUsers, HttpSession session) {
        System.out.println(x);
        libraryUsers.setStatus(1);
        System.out.println(libraryUsers.getStatus());
        System.out.println();
        session.removeAttribute("library_users");
        return "redirect:/users";
    }

    @GetMapping("/libraryblogs/search")
    public String search(@PageableDefault(size = 2) Pageable pageable,
                         Model model) {
        String query = x;
        model.addAttribute("page", userBlogService.listuserBlog(query, pageable));
        /*   model.addAttribute("query", query);*/
        return "admin/LibraryUserBlogs";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, LibraryUsers libraryUsers) {

        System.out.println(libraryUsers.getStatus());
        session.removeAttribute("library_users");
        System.out.println(libraryUsers.getStatus());
        d = true;
        return "redirect:/";
    }

    @GetMapping("/blog/borrow/{id}")
    public String BorrowBlog(@PathVariable int id,Blog blog, LibraryUsers libraryUsers, Model model) {

        if (d == true) {
            return "admin/LibraryLogin";
        }

        if(blogService.getAndConvertPoint(id).getPoints()>2){
            return "redirect:/";
        }


//        point=point+1;
//        blogService.getAndConvertPoint(id).setPoints(point);
//        System.out.println(blogService.getAndConvertPoint(id).getPoints());


        model.addAttribute("blog", blogService.getAndConvert(id));
        /*  model.addAttribute("blog", blogService.getAndConvertPoint(id));*/


        return "Borrowblog";


    }
}


  /*  public String index(@PageableDefault(size = 2, sort = {"updateTime"},
                        direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(2));
        model.addAttribute("tags", tagService.listTagTop(2));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(5));
        return "index";
    */