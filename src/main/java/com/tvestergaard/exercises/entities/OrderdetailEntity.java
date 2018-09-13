package com.tvestergaard.exercises.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity @Table(name = "orderdetails", schema = "classicmodels", catalog = "")
@IdClass(OrderdetailEntityPK.class) public class OrderdetailEntity
{
    private int orderNumber;
    private String productCode;
    private int quantityOrdered;
    private double priceEach;
    private short orderLineNumber;
    private OrderEntity ordersByOrderNumber;
    private ProductEntity productsByProductCode;

    @Id @Column(name = "orderNumber") public int getOrderNumber()
    {
        return this.orderNumber;
    }

    public void setOrderNumber(int orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    @Id @Column(name = "productCode") public String getProductCode()
    {
        return this.productCode;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    @Basic @Column(name = "quantityOrdered") public int getQuantityOrdered()
    {
        return this.quantityOrdered;
    }

    public void setQuantityOrdered(int quantityOrdered)
    {
        this.quantityOrdered = quantityOrdered;
    }

    @Basic @Column(name = "priceEach") public double getPriceEach()
    {
        return this.priceEach;
    }

    public void setPriceEach(double priceEach)
    {
        this.priceEach = priceEach;
    }

    @Basic @Column(name = "orderLineNumber") public short getOrderLineNumber()
    {
        return this.orderLineNumber;
    }

    public void setOrderLineNumber(short orderLineNumber)
    {
        this.orderLineNumber = orderLineNumber;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderdetailEntity that = (OrderdetailEntity) o;
        return orderNumber == that.orderNumber &&
               quantityOrdered == that.quantityOrdered &&
               Double.compare(that.priceEach, priceEach) == 0 &&
               orderLineNumber == that.orderLineNumber &&
               Objects.equals(productCode, that.productCode);
    }

    @Override public int hashCode()
    {
        return Objects.hash(orderNumber, productCode, quantityOrdered, priceEach, orderLineNumber);
    }

    @ManyToOne
    @JoinColumn(name = "orderNumber", referencedColumnName = "orderNumber", nullable = false)
    public OrderEntity getOrdersByOrderNumber()
    {
        return this.ordersByOrderNumber;
    }

    public void setOrdersByOrderNumber(OrderEntity ordersByOrderNumber)
    {
        this.ordersByOrderNumber = ordersByOrderNumber;
    }

    @ManyToOne
    @JoinColumn(name = "productCode", referencedColumnName = "productCode", nullable = false)
    public ProductEntity getProductsByProductCode()
    {
        return this.productsByProductCode;
    }

    public void setProductsByProductCode(ProductEntity productsByProductCode)
    {
        this.productsByProductCode = productsByProductCode;
    }
}
