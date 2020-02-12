package com.luka.moo.api;


import com.google.common.base.Preconditions;
import com.luka.moo.CustomerService;
import com.luka.moo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("")
public class CustomersController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping(method = RequestMethod.GET, path="/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAddressesBySurname(@RequestParam(value = "surname", required = false, defaultValue = "") String surname) {
        System.out.println("GET ADDRESSES BY SURNAME " + surname);
        if(surname == null || surname.trim() == "") {
            return customerService.getAll();
        } else {
            return customerService.getAllWithSurname(surname);
        }
    }


    @RequestMapping(path = "/customers/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getOneUser(@PathVariable("userId") String userId) {
        Preconditions.checkNotNull(userId, "Must provide user id");
        return customerService.getOne(userId);
    }
}
