package com.neo.gw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping(produces = "text/html")
    @ResponseBody
    public String homePage() {
        return "<h1 style='text-align:center !important; color:#f44336;'>Welcome to NEOVASAPI Service!</h1>";
    }
}
