package com.example.leaftest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    
/* 
    @GetMapping("hello")
    public String hello (Model model){
        model.addAttribute(attributeName: "data" ,attributeValue: "helllo");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute(attributeName: "name", name);
        return "hello-template";
    }
*/
    @GetMapping("hello-string")
    @ResponseBody   //html body 쪽에 전달?
    public String helloString(@RequestParam("name") String name){
        return "hello " +name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloapi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }


    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
