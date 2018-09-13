package com.tvestergaard.exercises.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OrderdetailEntityPK implements Serializable
{
    private int orderNumber;
    private String productCode;

    @Column(name = "orderNumber") @Id public int getOrderNumber()
    {
        return this.orderNumber;
    }

    public void setOrderNumber(int orderNumber)
    {
        this.orderNumber = orderNumber;
    }

    @Column(name = "productCode") @Id public String getProductCode()
    {
        return this.productCode;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderdetailEntityPK that = (OrderdetailEntityPK) o;
        return orderNumber == that.orderNumber &&
               Objects.equals(productCode, that.productCode);
    }

    @Override public int hashCode()
    {
        return Objects.hash(orderNumber, productCode);
    }
}
