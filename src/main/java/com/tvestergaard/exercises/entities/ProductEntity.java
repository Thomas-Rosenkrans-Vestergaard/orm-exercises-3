package com.tvestergaard.exercises.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity @Table(name = "products", schema = "classicmodels", catalog = "") public class ProductEntity
{
    private String productCode;
    private String productName;
    private String productScale;
    private String productVendor;
    private String productDescription;
    private short quantityInStock;
    private double buyPrice;
    private double msrp;
    private Collection<OrderdetailEntity> orderdetailsByProductCode;
    private ProductlineEntity productlinesByProductLine;

    @Id @Column(name = "productCode") public String getProductCode()
    {
        return this.productCode;
    }

    public void setProductCode(String productCode)
    {
        this.productCode = productCode;
    }

    @Basic @Column(name = "productName") public String getProductName()
    {
        return this.productName;
    }

    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    @Basic @Column(name = "productScale") public String getProductScale()
    {
        return this.productScale;
    }

    public void setProductScale(String productScale)
    {
        this.productScale = productScale;
    }

    @Basic @Column(name = "productVendor") public String getProductVendor()
    {
        return this.productVendor;
    }

    public void setProductVendor(String productVendor)
    {
        this.productVendor = productVendor;
    }

    @Basic @Column(name = "productDescription") public String getProductDescription()
    {
        return this.productDescription;
    }

    public void setProductDescription(String productDescription)
    {
        this.productDescription = productDescription;
    }

    @Basic @Column(name = "quantityInStock") public short getQuantityInStock()
    {
        return this.quantityInStock;
    }

    public void setQuantityInStock(short quantityInStock)
    {
        this.quantityInStock = quantityInStock;
    }

    @Basic @Column(name = "buyPrice") public double getBuyPrice()
    {
        return this.buyPrice;
    }

    public void setBuyPrice(double buyPrice)
    {
        this.buyPrice = buyPrice;
    }

    @Basic @Column(name = "MSRP") public double getMsrp()
    {
        return this.msrp;
    }

    public void setMsrp(double msrp)
    {
        this.msrp = msrp;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return quantityInStock == that.quantityInStock &&
               Double.compare(that.buyPrice, buyPrice) == 0 &&
               Double.compare(that.msrp, msrp) == 0 &&
               Objects.equals(productCode, that.productCode) &&
               Objects.equals(productName, that.productName) &&
               Objects.equals(productScale, that.productScale) &&
               Objects.equals(productVendor, that.productVendor) &&
               Objects.equals(productDescription, that.productDescription);
    }

    @Override public int hashCode()
    {
        return Objects.hash(productCode, productName, productScale, productVendor, productDescription, quantityInStock, buyPrice, msrp);
    }

    @OneToMany(mappedBy = "productsByProductCode") public Collection<OrderdetailEntity> getOrderdetailsByProductCode()
    {
        return this.orderdetailsByProductCode;
    }

    public void setOrderdetailsByProductCode(Collection<OrderdetailEntity> orderdetailsByProductCode)
    {
        this.orderdetailsByProductCode = orderdetailsByProductCode;
    }

    @ManyToOne @JoinColumn(name = "productLine", referencedColumnName = "productLine", nullable = false)
    public ProductlineEntity getProductlinesByProductLine()
    {
        return this.productlinesByProductLine;
    }

    public void setProductlinesByProductLine(ProductlineEntity productlinesByProductLine)
    {
        this.productlinesByProductLine = productlinesByProductLine;
    }
}
