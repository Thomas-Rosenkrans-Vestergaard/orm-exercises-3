package com.tvestergaard.exercises.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class PaymentEntityPK implements Serializable
{
    private int customerNumber;
    private String checkNumber;

    @Column(name = "customerNumber") @Id public int getCustomerNumber()
    {
        return this.customerNumber;
    }

    public void setCustomerNumber(int customerNumber)
    {
        this.customerNumber = customerNumber;
    }

    @Column(name = "checkNumber") @Id public String getCheckNumber()
    {
        return this.checkNumber;
    }

    public void setCheckNumber(String checkNumber)
    {
        this.checkNumber = checkNumber;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentEntityPK that = (PaymentEntityPK) o;
        return customerNumber == that.customerNumber &&
               Objects.equals(checkNumber, that.checkNumber);
    }

    @Override public int hashCode()
    {
        return Objects.hash(customerNumber, checkNumber);
    }
}
