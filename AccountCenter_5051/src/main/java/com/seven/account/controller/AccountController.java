package com.seven.account.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @ResponseBody
    @RequestMapping("/account/get")
    public Integer getAccount() {
        return 0;
    }

    @ResponseBody
    @RequestMapping("/account/add")
    public int addAccount() {
        return 0;
    }
}