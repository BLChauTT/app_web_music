package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.demo.entities.Account;
import com.demo.entities.AccountSong;
import com.demo.services.AccountSongService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("uploader")
public class UploaderController {
    @Autowired
    private AccountSongService accountSongService;

    @GetMapping("/upSongs/{accountId}")
    public String accountSongs(@PathVariable("accountId") int accountId, ModelMap modelMap, HttpSession httpSession) {

        Account loggedInUser = (Account) httpSession.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/account/login";
        }

        List<AccountSong> accountSongs = accountSongService.findByAccountId(accountId);

        modelMap.put("accountSongs", accountSongs);
        modelMap.put("loggedInUser", loggedInUser);

        return "user/uploader";
    }
}
