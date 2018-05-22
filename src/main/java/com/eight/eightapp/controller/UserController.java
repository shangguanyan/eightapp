package com.eight.eightapp.controller;


import com.eight.eightapp.bean.Role;
import com.eight.eightapp.bean.User;
import com.eight.eightapp.service.RoleService;
import com.eight.eightapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/user")
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
    @Autowired
    private RoleService roleService;

	@RequestMapping(value = "userList",method = {RequestMethod.GET,RequestMethod.POST})
	public String userList(HttpServletRequest request,Model model, @RequestParam(value="username",required = false)String username){
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        List<User> user = userService.getByMap(map);
        model.addAttribute("username", username);
        model.addAttribute("userlist", user);
	    return "user/userList";
    }

    @RequestMapping(value = "userAdd", method = {RequestMethod.GET, RequestMethod.POST})
    public String userAdd(HttpServletRequest request,Model model,@Valid User user,
                          @RequestParam(value="username",required = false)String username){
	    //获取到用户类型
        if(username == null || username == ""){
            List<Role> roles = roleService.getByMap(null);
            model.addAttribute("roles", roles);
            return "user/userAdd";
        }else {
            return "user/userUpdate";
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> list(HttpServletRequest request) {
        return userService.getByMap(null);
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User detail(@PathVariable Integer id) {
		return userService.getById(id);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestBody User user) {
		return userService.create(user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public User update(@RequestBody User user) {
		return userService.update(user);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public int delete(@PathVariable Integer id) {
		return userService.delete(id);
    }
    
}