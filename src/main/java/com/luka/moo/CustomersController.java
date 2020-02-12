package com.luka.moo;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomersController {

    @GetMapping
    public String home() {
        return "Hello! Use /addresses endpoint to fetch users addresses user surname param to pass surname!";
    }

    @GetMapping(path = "/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
    public List getAddresses() {

        List users = new ArrayList();
        users.add("String");
        return users;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/addresses}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List getAddressesBySurname(@RequestParam(value = "surname", required = false, defaultValue = "") String surname) {
        System.out.println("GET ADDRESSES BY SURNAME " + surname);
        return null;
    }

}
