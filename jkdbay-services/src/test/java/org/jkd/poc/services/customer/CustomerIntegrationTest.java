package org.jkd.poc.services.customer;


import org.jkd.poc.services.ApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by dilunika on 4/14/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = ApplicationConfig.class)
public class CustomerIntegrationTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void saveCustomerCorrectly() throws Exception {

        EmailAddress email = new EmailAddress("alicia@keys.com");

        Customer dave = new Customer("Alicia", "Keys");
        dave.setEmailAddress(email);
        dave.add(new Address("27 Broadway", "New York", "United States"));
        dave.add(new Address("28 Broadway", "New York", "United States"));
        dave.add(new Address("29 Broadway", "New York", "United States"));

        Customer result = customerRepository.save(dave);
        assertThat(result.getId(), is(notNullValue()));

        Customer savedCustomer = customerRepository.findById(result.getId());
        assertThat(savedCustomer.getAddresses().size(),is(3));

    }

    @Test
    public void readsCustomerByEmail() throws Exception{

        EmailAddress email = new EmailAddress("alicia@keys.com");
        Customer alicia = new Customer("Alicia", "Keys");
        alicia.setEmailAddress(email);

        customerRepository.save(alicia);

        Customer result = customerRepository.findByEmailAddress(email);
        assertThat(result, is(alicia));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void preventDuplicateEmails() throws Exception {

        EmailAddress email = new EmailAddress("alicia@keys.com");

        Customer kasun = new Customer("Kasun","Dilunika");
        kasun.setEmailAddress(email);

        Customer amal = new Customer("Amal", "Peris");
        amal.setEmailAddress(email);

        customerRepository.save(kasun);
        customerRepository.save(amal);

    }

    @Test(expected = DataIntegrityViolationException.class)
    public void preventDuplicateMobileNumbers() throws Exception {

        EmailAddress email = new EmailAddress("alicia@keys.com");

        Customer kasun = new Customer("Kasun","Dilunika");
        kasun.setEmailAddress(email);
        kasun.setMobileNumber("0777500300");

        Customer amal = new Customer("Amal", "Peris");
        amal.setEmailAddress(email);
        amal.setMobileNumber("0777500300");

        customerRepository.save(kasun);
        customerRepository.save(amal);

    }

}
