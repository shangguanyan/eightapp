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

@RequestMapping(value = "/role")
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/list")
    public String roleList(){
        return "role/list";
    }


}