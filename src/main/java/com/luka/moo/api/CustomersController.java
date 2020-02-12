package com.luka.moo.api;


import com.luka.moo.model.Customer;
import com.luka.moo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomersController {

    @Autowired
    private CustomerService customerService;


    @RequestMapping(method = RequestMethod.GET, path="/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> getAddressesBySurname(@RequestParam(value = "surname", required = false, defaultValue = "") String surname) {
        if(surname == null || surname.trim().equals("")) {
            return customerService.getAll();
        } else {
            return customerService.getAllWithSurname(surname);
        }
    }


    @RequestMapping(path = "/customers/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getOneUser(@NonNull @PathVariable("userId") String userId) {
        return customerService.getOne(userId);
    }
}
