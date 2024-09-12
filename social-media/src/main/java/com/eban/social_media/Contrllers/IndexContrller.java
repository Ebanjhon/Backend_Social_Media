package com.eban.social_media.Contrllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")  // Cho phép mọi nguồn gốc có thể truy cập API này
public class IndexContrller {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World!";
    }
}