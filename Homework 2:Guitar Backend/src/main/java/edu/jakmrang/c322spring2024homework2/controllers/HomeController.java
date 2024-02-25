package edu.jakmrang.c322spring2024homework2.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

    @RestController("/")
    public class HomeController {
        @GetMapping
        public String greeting() {
            return "Welcome to the inventory service";
        }

}
