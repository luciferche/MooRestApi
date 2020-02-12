package com.luka.moo;

import com.google.common.base.Preconditions;
import com.luka.moo.helpers.ResourceNotFoundException;
import com.luka.moo.model.Customer;
import com.luka.moo.model.DataAction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class CustomerService implements DataAction<Customer> {

    private List<Customer> customers;

    private Map<String, Customer> customersMap;

    public CustomerService() {
        customers = new ArrayList<>();
        customersMap = new ConcurrentHashMap<>();
        System.out.println("Created customer service");
    }



    @Override
    public void save(Customer customer) {
        customers.add(customer);
        customersMap.put(customer.getId(), customer);
    }

    @Override
    public List<Customer> getAll() {
        //returns shallow copy of values inn the map
        return new ArrayList<>(customersMap.values());
    }

    @Override
    public Customer getOne(String id) {
        Preconditions.checkNotNull(id, "Must provide user id");
        Customer customer = customersMap.get(id);
        if(customer == null) {
            throw new ResourceNotFoundException("Customer not found");
        }
        return customer;
    }

    /**
     * Returns list of customers whose surname matches param provided
     * @param surname surname to match against
     * @return List of Customer objects
     */
    public List<Customer> getAllWithSurname(String surname) {
        Preconditions.checkNotNull(surname, "Must provide surname to compare");

        return customers
                .stream()
                .filter(customer -> customer.getSurname() == surname)
                .collect(Collectors.toList());
    }
}
