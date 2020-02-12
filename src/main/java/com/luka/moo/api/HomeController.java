package com.luka.moo.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest controller used just for home route '/'
 */
@RestController("/")
public class HomeController {

    @GetMapping
    public String home() {
        return "Hello! Use /addresses endpoint to fetch users addresses user surname param to pass surname!";
    }
}
