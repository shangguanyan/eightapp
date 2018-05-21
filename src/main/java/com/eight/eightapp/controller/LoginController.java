package com.eight.eightapp.controller;

import com.eight.eightapp.bean.User;
import com.eight.eightapp.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 登陆控制器
 */
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserService userService;

    @RequestMapping(value = {"/login", "/login.action", "/"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
            @RequestParam(value = "username", required = true) String userName,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "rememberMe", required = true, defaultValue = "false") boolean rememberMe, Model model
    ) {
        logger.info("==========" + userName + password + rememberMe);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        token.setRememberMe(rememberMe);

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            model.addAttribute("msg", "用户名或密码错误");
            return "login";
        }
        return "index";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        logger.debug("method=>logout");
		SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
    public String changePassword(HttpServletRequest request) {
        return "changepassword";
    }
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String changePassword(
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "newpassword", required = true) String newpassword, Model model) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        if(user  != null){
            if(!user.getPassword().equals(password)){
                model.addAttribute("msg", "原始密码错误");
                return "changepassword";
            }else {
                userService.changePassword(user.getId()+"",newpassword);
                return "redirect:/login";
            }
        }else{
            return "redirect:/login";
        }
    }
}
