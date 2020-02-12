package com.luka.moo.services;

import com.google.common.base.Preconditions;
import com.luka.moo.helpers.ResourceNotFoundException;
import com.luka.moo.model.Customer;
import com.luka.moo.model.DataAction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class CustomerService implements DataAction<Customer> {


    private static Map<String, Customer> customersMap = new ConcurrentHashMap<>();
    private static final String[] names = new String[]{"Luka", "Jo", "Alice", "Luke", "Misty", "Rusty"};

    public static void initDummyData()  {
        for (int i = 0; i < names.length; i++) {
            Customer temp = new Customer(names[i], "sur"+names[i], "Address for" + names[i]);
            customersMap.put(temp.getId(), temp);
        }
    }

    @Override
    public void save(Customer customer) {
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

        return customersMap.values()
                .stream()
                .filter(customer -> customer.getSurname() == surname)
                .collect(Collectors.toList());
    }
}
