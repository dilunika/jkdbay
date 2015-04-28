package org.jkd.poc.services.customer;

import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

/**
 * Created by dilunika on 4/5/15.
 */
@Embeddable
public class EmailAddress {

    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    @Column(name = "email", unique = true)
    private String value;

    protected EmailAddress(){}

    public EmailAddress(String emailAddress) {

        this.value = emailAddress;
    }

    public void validate(){
        Assert.isTrue(isValid(value),"Invalid email address.");
    }

    private boolean isValid(String emailAddress) {
        return emailAddress == null ? false : PATTERN.matcher(emailAddress).matches();
    }

    public String value(){
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailAddress that = (EmailAddress) o;

        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
