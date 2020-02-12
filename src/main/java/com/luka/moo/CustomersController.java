package com.luka.moo;


import com.google.common.base.Preconditions;
import com.luka.moo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomersController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public String home() {
        return "Hello! Use /addresses endpoint to fetch users addresses user surname param to pass surname!";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAddressesBySurname(@RequestParam(value = "surname", required = false, defaultValue = "") String surname) {
        System.out.println("GET ADDRESSES BY SURNAME " + surname);
        if(surname == null || surname.trim() == "") {
            return customerService.getAll();
        } else {
            return customerService.getAllWithSurname(surname);
        }
    }


    @RequestMapping(path = "/addresses/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getOneUser(@PathVariable("userId") String userId) {
        Preconditions.checkNotNull(userId, "Must provide user id");
        return customerService.getOne(userId);
    }
}
