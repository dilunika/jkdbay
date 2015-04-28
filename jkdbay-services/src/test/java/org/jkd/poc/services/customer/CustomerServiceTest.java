package org.jkd.poc.services.customer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;

import static org.junit.Assert.fail;

/**
 * Created by dilunika on 4/19/15.
 */
public class CustomerServiceTest {

    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService();
        customerService.setCustomerRepository(customerRepository);
    }

    // createNewCustomerCorrectly

    @Test(expected = IllegalArgumentException.class)
    public void createNewCustomerWithInvalidEmailAddress() throws Exception {

        customerService.registerNewCustomer(customerWithInvalidEmail());
        fail("Should be failed as email is invalid.");
    }

    private Customer customerWithInvalidEmail() {

        Customer c = new Customer("Kasun","Dilunika");
        c.setEmailAddress(new EmailAddress("k"));

        return c;
    }


    // createNewCustomerWithIncompleteAddress

    // updateExistingCustomer
}
