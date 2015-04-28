package org.jkd.poc.services.customer;

import org.jkd.poc.services.AbstractEntity;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by dilunika on 4/5/15.
 */
@Entity
public class Customer extends AbstractEntity {

    private String firstName, lastName;

    private EmailAddress emailAddress;

    @Column(unique = true)
    private String mobileNumber;

    private String title;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private Set<Address> addresses = new HashSet<Address>();

    public Customer(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void add(Address address) {
        Assert.notNull(address);
        addresses.add(address);
    }

    public void validate(){

        lastNameAndFisrtNameAreNotNullOrEmpty();
        emailAddress.validate();
    }

    private void lastNameAndFisrtNameAreNotNullOrEmpty() {
        Assert.hasText(firstName, "First name should not be null or empty");
        Assert.hasText(lastName,"Last name should not be null or empty");
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmailAddress getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(EmailAddress emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", emailAddress=" + emailAddress +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
