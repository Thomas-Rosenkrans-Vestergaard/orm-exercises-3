package com.tvestergaard.exercises.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity @Table(name = "customers", schema = "classicmodels", catalog = "") public class CustomerEntity
{
    private int customerNumber;
    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Double creditLimit;
    private EmployeeEntity employeesBySalesRepEmployeeNumber;
    private Collection<OrderEntity> ordersByCustomerNumber;
    private Collection<PaymentEntity> paymentsByCustomerNumber;

    @Id @Column(name = "customerNumber") public int getCustomerNumber()
    {
        return this.customerNumber;
    }

    public void setCustomerNumber(int customerNumber)
    {
        this.customerNumber = customerNumber;
    }

    @Basic @Column(name = "customerName") public String getCustomerName()
    {
        return this.customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    @Basic @Column(name = "contactLastName") public String getContactLastName()
    {
        return this.contactLastName;
    }

    public void setContactLastName(String contactLastName)
    {
        this.contactLastName = contactLastName;
    }

    @Basic @Column(name = "contactFirstName") public String getContactFirstName()
    {
        return this.contactFirstName;
    }

    public void setContactFirstName(String contactFirstName)
    {
        this.contactFirstName = contactFirstName;
    }

    @Basic @Column(name = "phone") public String getPhone()
    {
        return this.phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Basic @Column(name = "addressLine1") public String getAddressLine1()
    {
        return this.addressLine1;
    }

    public void setAddressLine1(String addressLine1)
    {
        this.addressLine1 = addressLine1;
    }

    @Basic @Column(name = "addressLine2") public String getAddressLine2()
    {
        return this.addressLine2;
    }

    public void setAddressLine2(String addressLine2)
    {
        this.addressLine2 = addressLine2;
    }

    @Basic @Column(name = "city") public String getCity()
    {
        return this.city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    @Basic @Column(name = "state") public String getState()
    {
        return this.state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    @Basic @Column(name = "postalCode") public String getPostalCode()
    {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    @Basic @Column(name = "country") public String getCountry()
    {
        return this.country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    @Basic @Column(name = "creditLimit") public Double getCreditLimit()
    {
        return this.creditLimit;
    }

    public void setCreditLimit(Double creditLimit)
    {
        this.creditLimit = creditLimit;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return customerNumber == that.customerNumber &&
               Objects.equals(customerName, that.customerName) &&
               Objects.equals(contactLastName, that.contactLastName) &&
               Objects.equals(contactFirstName, that.contactFirstName) &&
               Objects.equals(phone, that.phone) &&
               Objects.equals(addressLine1, that.addressLine1) &&
               Objects.equals(addressLine2, that.addressLine2) &&
               Objects.equals(city, that.city) &&
               Objects.equals(state, that.state) &&
               Objects.equals(postalCode, that.postalCode) &&
               Objects.equals(country, that.country) &&
               Objects.equals(creditLimit, that.creditLimit);
    }

    @Override public int hashCode()
    {
        return Objects.hash(customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, creditLimit);
    }

    @ManyToOne @JoinColumn(name = "salesRepEmployeeNumber", referencedColumnName = "employeeNumber")
    public EmployeeEntity getEmployeesBySalesRepEmployeeNumber()
    {
        return this.employeesBySalesRepEmployeeNumber;
    }

    public void setEmployeesBySalesRepEmployeeNumber(EmployeeEntity employeesBySalesRepEmployeeNumber)
    {
        this.employeesBySalesRepEmployeeNumber = employeesBySalesRepEmployeeNumber;
    }

    @OneToMany(mappedBy = "customersByCustomerNumber") public Collection<OrderEntity> getOrdersByCustomerNumber()
    {
        return this.ordersByCustomerNumber;
    }

    public void setOrdersByCustomerNumber(Collection<OrderEntity> ordersByCustomerNumber)
    {
        this.ordersByCustomerNumber = ordersByCustomerNumber;
    }

    @OneToMany(mappedBy = "customersByCustomerNumber") public Collection<PaymentEntity> getPaymentsByCustomerNumber()
    {
        return this.paymentsByCustomerNumber;
    }

    public void setPaymentsByCustomerNumber(Collection<PaymentEntity> paymentsByCustomerNumber)
    {
        this.paymentsByCustomerNumber = paymentsByCustomerNumber;
    }
}
