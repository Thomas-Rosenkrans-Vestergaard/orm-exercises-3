package com.tvestergaard.exercises.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity @Table(name = "orders", schema = "classicmodels", catalog = "") public class OrderEntity
{
    private int orderNumber;
    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;
    private String status;
    private String comments;
    private Collection<OrderdetailEntity> orderdetailsByOrderNumber;
    private CustomerEntity customersByCustomerNumber;

    @Id @Column(name = "orderNumber") public int getOrderNumber()
    {
        return this.orderNumber;
    }

    public void setOrderNumber(int orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    @Basic @Column(name = "orderDate") public Date getOrderDate()
    {
        return this.orderDate;
    }

    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }

    @Basic @Column(name = "requiredDate") public Date getRequiredDate()
    {
        return this.requiredDate;
    }

    public void setRequiredDate(Date requiredDate)
    {
        this.requiredDate = requiredDate;
    }

    @Basic @Column(name = "shippedDate") public Date getShippedDate()
    {
        return this.shippedDate;
    }

    public void setShippedDate(Date shippedDate)
    {
        this.shippedDate = shippedDate;
    }

    @Basic @Column(name = "status") public String getStatus()
    {
        return this.status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Basic @Column(name = "comments") public String getComments()
    {
        return this.comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return orderNumber == that.orderNumber &&
               Objects.equals(orderDate, that.orderDate) &&
               Objects.equals(requiredDate, that.requiredDate) &&
               Objects.equals(shippedDate, that.shippedDate) &&
               Objects.equals(status, that.status) &&
               Objects.equals(comments, that.comments);
    }

    @Override public int hashCode()
    {
        return Objects.hash(orderNumber, orderDate, requiredDate, shippedDate, status, comments);
    }

    @OneToMany(mappedBy = "ordersByOrderNumber") public Collection<OrderdetailEntity> getOrderdetailsByOrderNumber()
    {
        return this.orderdetailsByOrderNumber;
    }

    public void setOrderdetailsByOrderNumber(Collection<OrderdetailEntity> orderdetailsByOrderNumber)
    {
        this.orderdetailsByOrderNumber = orderdetailsByOrderNumber;
    }

    @ManyToOne @JoinColumn(name = "customerNumber", referencedColumnName = "customerNumber", nullable = false)
    public CustomerEntity getCustomersByCustomerNumber()
    {
        return this.customersByCustomerNumber;
    }

    public void setCustomersByCustomerNumber(CustomerEntity customersByCustomerNumber)
    {
        this.customersByCustomerNumber = customersByCustomerNumber;
    }
}
