package com.tvestergaard.exercises.entities;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity @Table(name = "productlines", schema = "classicmodels", catalog = "") public class ProductlineEntity
{
    private String productLine;
    private String textDescription;
    private String htmlDescription;
    private byte[] image;
    private Collection<ProductEntity> productsByProductLine;

    @Id @Column(name = "productLine") public String getProductLine()
    {
        return this.productLine;
    }

    public void setProductLine(String productLine)
    {
        this.productLine = productLine;
    }

    @Basic @Column(name = "textDescription") public String getTextDescription()
    {
        return this.textDescription;
    }

    public void setTextDescription(String textDescription)
    {
        this.textDescription = textDescription;
    }

    @Basic @Column(name = "htmlDescription") public String getHtmlDescription()
    {
        return this.htmlDescription;
    }

    public void setHtmlDescription(String htmlDescription)
    {
        this.htmlDescription = htmlDescription;
    }

    @Basic @Column(name = "image") public byte[] getImage()
    {
        return this.image;
    }

    public void setImage(byte[] image)
    {
        this.image = image;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductlineEntity that = (ProductlineEntity) o;
        return Objects.equals(productLine, that.productLine) &&
               Objects.equals(textDescription, that.textDescription) &&
               Objects.equals(htmlDescription, that.htmlDescription) &&
               Arrays.equals(image, that.image);
    }

    @Override public int hashCode()
    {
        int result = Objects.hash(productLine, textDescription, htmlDescription);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @OneToMany(mappedBy = "productlinesByProductLine") public Collection<ProductEntity> getProductsByProductLine()
    {
        return this.productsByProductLine;
    }

    public void setProductsByProductLine(Collection<ProductEntity> productsByProductLine)
    {
        this.productsByProductLine = productsByProductLine;
    }
}
