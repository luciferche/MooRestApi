package com.luka.moo.services;

import com.luka.moo.helpers.ResourceNotFoundException;
import com.luka.moo.helpers.RestPreconditions;
import com.luka.moo.model.Customer;
import com.luka.moo.model.DataAction;
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

    /**
     * Initializing dummy data for the customer service
     */
    public static void initDummyData()  {
        for (int i = 0; i < names.length; i++) {
            Customer temp = new Customer(names[i], "sur"+names[i], "Address for" + names[i]);
            customersMap.put(temp.getId(), temp);
        }
    }

    /**
     * method not used in the API but used for adding customers to the map directly
     * @param customer
     */
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
        RestPreconditions.checkNotNull(id);
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
        RestPreconditions.checkNotNull(surname);

        return customersMap.values()
                .stream()
                .filter(customer -> customer.getSurname() == surname)
                .collect(Collectors.toList());
    }
}
