package com.tvestergaard.exercises.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity @Table(name = "payments", schema = "classicmodels", catalog = "")
@IdClass(PaymentEntityPK.class) public class PaymentEntity
{
    private int customerNumber;
    private String checkNumber;
    private Date paymentDate;
    private double amount;
    private CustomerEntity customersByCustomerNumber;

    @Id @Column(name = "customerNumber") public int getCustomerNumber()
    {
        return this.customerNumber;
    }

    public void setCustomerNumber(int customerNumber)
    {
        this.customerNumber = customerNumber;
    }

    @Id @Column(name = "checkNumber") public String getCheckNumber()
    {
        return this.checkNumber;
    }

    public void setCheckNumber(String checkNumber)
    {
        this.checkNumber = checkNumber;
    }

    @Basic @Column(name = "paymentDate") public Date getPaymentDate()
    {
        return this.paymentDate;
    }

    public void setPaymentDate(Date paymentDate)
    {
        this.paymentDate = paymentDate;
    }

    @Basic @Column(name = "amount") public double getAmount()
    {
        return this.amount;
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentEntity that = (PaymentEntity) o;
        return customerNumber == that.customerNumber &&
               Double.compare(that.amount, amount) == 0 &&
               Objects.equals(checkNumber, that.checkNumber) &&
               Objects.equals(paymentDate, that.paymentDate);
    }

    @Override public int hashCode()
    {
        return Objects.hash(customerNumber, checkNumber, paymentDate, amount);
    }

    @ManyToOne
    @JoinColumn(name = "customerNumber", referencedColumnName = "customerNumber", nullable = false, insertable = false, updatable = false)
    public CustomerEntity getCustomersByCustomerNumber()
    {
        return this.customersByCustomerNumber;
    }

    public void setCustomersByCustomerNumber(CustomerEntity customersByCustomerNumber)
    {
        this.customersByCustomerNumber = customersByCustomerNumber;
    }
}
