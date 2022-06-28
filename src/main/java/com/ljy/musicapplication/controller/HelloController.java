package com.ljy.musicapplication.controller;

/*import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;*/

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/*@Api("test" )*/
public class HelloController {
    @GetMapping("/hello")
//    @ApiOperation("hello")
    public String helloGet() {
        System.out.println("Hello， SpringBoot");
        return "Hello， SpringBoot";
    }
}
