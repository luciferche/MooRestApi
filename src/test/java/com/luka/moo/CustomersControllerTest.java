package com.luka.moo;

import com.luka.moo.api.CustomersController;
import com.luka.moo.model.Customer;
import com.luka.moo.services.CustomerService;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CustomersController.class)
public class CustomersControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;


    private Customer alex = new Customer("alex", "alex", "address alex");
    private Customer luka = new Customer("luka", "luka", "address luka");
    private Customer jo = new Customer("jo", "jo", "address jo");
    private Customer secondAlex = new Customer("second", "alex", "address second alex");


    @Before
    public void setup() {

        List<Customer> allCustomers = Arrays.asList(alex, luka, jo, secondAlex);

        given(customerService.getAll()).willReturn(allCustomers);
        given(customerService.getAllWithSurname("alex")).willReturn(Arrays.asList(alex,secondAlex));
        given(customerService.getOne("jo_id")).willReturn(jo);
    }

    @Test
    public void whenUnknownPath_then_notFound() throws Exception {
        mvc.perform(get("/asdf"))
                .andExpect(status().is(404));

    }

    @Test
    public void givenEmployees_whenGetCustomers_thenReturnJsonArray()
            throws Exception {


        mvc.perform(get("/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname", Is.is(alex.getSurname())));
    }


    @Test
    public void givenEmployees_whenGetDuplicatedCustomersSurname_thenReturnAllResults()
            throws Exception {


        mvc.perform(get("/customers?surname=alex")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].surname", Is.is(alex.getSurname())));
    }

    @Test
    public void givenEmployees_whenGetCustomerById_thenReturnThatOne()
            throws Exception {


        mvc.perform(get("/customers/jo_id")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Is.is(jo.getName())))
                .andExpect(jsonPath("$.surname", Is.is(jo.getSurname())))
                .andExpect(jsonPath("$.address", Is.is(jo.getAddress())))
                .andExpect(jsonPath("$.id", Is.is(jo.getId())));
    }


}
