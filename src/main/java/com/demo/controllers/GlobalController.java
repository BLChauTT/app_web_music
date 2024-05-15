package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.demo.entities.Account;
import com.demo.services.UserProfileService;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
public class GlobalController {
    @Autowired
    private Environment environment;
    @Autowired
    private UserProfileService userProfileService;
    @ModelAttribute
    public void addAttributes(Model model, HttpSession session, ModelMap modelMap) {
        Account loggedInUser = (Account) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("loggedInUser", loggedInUser);
            String avatarUrl = userProfileService.getAvatarUrlByAccountId(loggedInUser.getAccountId());
            String filePath = environment.getProperty("imageUrl");
            String stringPath = filePath + avatarUrl;
            model.addAttribute("stringPath", stringPath);
        }
    }
}
