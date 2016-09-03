package com.example.controller;

import com.example.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author afillatre@ippon.fr
 * @since 04/09/16.
 */
@RestController
public class HelloController {

    private final HelloService helloService;

    @Autowired
    public HelloController(HelloService helloService) {
        Assert.notNull(helloService, "Hello service should not be null");
        this.helloService = helloService;
    }

    @RequestMapping("/hello")
    public String sayHello() {
        return helloService.sayHello();
    }
}
