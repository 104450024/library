package com.irm.demo.web.admin;

import com.irm.demo.po.LibraryUsers;
import com.irm.demo.po.Tag;
import com.irm.demo.po.Type;
import com.irm.demo.po.User;
import com.irm.demo.service.LibarayUserService;
import com.irm.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by limi on 2017/10/15.
 */
@Controller
@RequestMapping("/admin")
public class LoginController {



    private final UserService userService;
    private final LibarayUserService libarayUserService;
    @Autowired
    public LoginController(UserService userService, LibarayUserService libarayUserService) {
        this.userService = userService;
        this.libarayUserService = libarayUserService;
    }

    @GetMapping
    public String loginPage() {
        return "admin/login";
    }




    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes, Model model) {
        User user = userService.checkUser(username, password);
        boolean testBoolean;
        if (user != null) {
            user.setPassword(null);
            testBoolean=false;
            model.addAttribute("testBoolean",testBoolean);
            session.setAttribute("user",user);

            return "admin/index";
        } else {
            attributes.addFlashAttribute("message", "用户名和密码错误");
            return "redirect:/admin";
        }
    }


    @GetMapping("/re")
    public String loginPage1(Model model) {
        return "admin/LibraryRegister";
    }

    @PostMapping("/register")
    public String post(LibraryUsers libraryUser) {

        LibraryUsers libraryUsers=libarayUserService.getByUsernmae(libraryUser.getUsername());

        if (libraryUsers != null) {
            return "admin/LibraryRegister";
        }
        LibraryUsers libraryUsers1 = libarayUserService.savelibrary(libraryUser);

        return "redirect:/users";
    }
}
