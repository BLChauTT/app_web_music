package com.demo.controllers;

import com.demo.services.AccountJPAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AccountJPAService accountJPAService;

    @GetMapping("index")
    public String admin() {
        return "admin/index";
    }

    @GetMapping("music")
    public String music() {
        return "admin/music";
    }

}
