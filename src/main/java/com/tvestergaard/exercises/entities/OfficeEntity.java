package com.tvestergaard.exercises.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity @Table(name = "offices", schema = "classicmodels", catalog = "") public class OfficeEntity
{
    private String officeCode;
    private String city;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String state;
    private String country;
    private String postalCode;
    private String territory;
    private Collection<EmployeeEntity> employeesByOfficeCode;

    @Id @Column(name = "officeCode") public String getOfficeCode()
    {
        return this.officeCode;
    }

    public void setOfficeCode(String officeCode)
    {
        this.officeCode = officeCode;
    }

    @Basic @Column(name = "city") public String getCity()
    {
        return this.city;
    }

    public void setCity(String city)
    {
        this.city = city;
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

    @Basic @Column(name = "state") public String getState()
    {
        return this.state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    @Basic @Column(name = "country") public String getCountry()
    {
        return this.country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    @Basic @Column(name = "postalCode") public String getPostalCode()
    {
        return this.postalCode;
    }

    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }

    @Basic @Column(name = "territory") public String getTerritory()
    {
        return this.territory;
    }

    public void setTerritory(String territory)
    {
        this.territory = territory;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OfficeEntity that = (OfficeEntity) o;
        return Objects.equals(officeCode, that.officeCode) &&
               Objects.equals(city, that.city) &&
               Objects.equals(phone, that.phone) &&
               Objects.equals(addressLine1, that.addressLine1) &&
               Objects.equals(addressLine2, that.addressLine2) &&
               Objects.equals(state, that.state) &&
               Objects.equals(country, that.country) &&
               Objects.equals(postalCode, that.postalCode) &&
               Objects.equals(territory, that.territory);
    }

    @Override public int hashCode()
    {
        return Objects.hash(officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory);
    }

    @OneToMany(mappedBy = "officesByOfficeCode") public Collection<EmployeeEntity> getEmployeesByOfficeCode()
    {
        return this.employeesByOfficeCode;
    }

    public void setEmployeesByOfficeCode(Collection<EmployeeEntity> employeesByOfficeCode)
    {
        this.employeesByOfficeCode = employeesByOfficeCode;
    }
}
