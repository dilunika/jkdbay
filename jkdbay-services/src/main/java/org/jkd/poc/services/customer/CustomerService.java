package org.jkd.poc.services.customer;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by dilunika on 4/19/15.
 */
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public void registerNewCustomer(Customer customer) {

        customer.validate();
        customerRepository.save(customer);

    }

    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
}
