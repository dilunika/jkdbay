package org.jkd.poc.services.customer;

import org.springframework.data.repository.Repository;

/**
 * Created by dilunika on 4/10/15.
 */
public interface CustomerRepository extends Repository<Customer, Long> {

    Customer findById(Long id);

    Customer save(Customer customer);

    Customer findByEmailAddress(EmailAddress emailAddress);
}
