package com.tvestergaard.exercises.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity @Table(name = "employees", schema = "classicmodels", catalog = "") public class EmployeeEntity
{
    private int employeeNumber;
    private String lastName;
    private String firstName;
    private String extension;
    private String email;
    private String jobTitle;
    private Collection<CustomerEntity> customersByEmployeeNumber;
    private OfficeEntity officesByOfficeCode;
    private EmployeeEntity employeesByReportsTo;
    private Collection<EmployeeEntity> employeesByEmployeeNumber;

    @GeneratedValue(strategy = GenerationType.TABLE,generator="s1")
    @TableGenerator(name="s1",table = "My_SEQ",
            initialValue = 2000,allocationSize = 25)
    @Id @Column(name = "employeeNumber") public int getEmployeeNumber()
    {
        return this.employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber)
    {
        this.employeeNumber = employeeNumber;
    }

    @Basic @Column(name = "lastName") public String getLastName()
    {
        return this.lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    @Basic @Column(name = "firstName") public String getFirstName()
    {
        return this.firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    @Basic @Column(name = "extension") public String getExtension()
    {
        return this.extension;
    }

    public void setExtension(String extension)
    {
        this.extension = extension;
    }

    @Basic @Column(name = "email") public String getEmail()
    {
        return this.email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Basic @Column(name = "jobTitle") public String getJobTitle()
    {
        return this.jobTitle;
    }

    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return employeeNumber == that.employeeNumber &&
               Objects.equals(lastName, that.lastName) &&
               Objects.equals(firstName, that.firstName) &&
               Objects.equals(extension, that.extension) &&
               Objects.equals(email, that.email) &&
               Objects.equals(jobTitle, that.jobTitle);
    }

    @Override public int hashCode()
    {
        return Objects.hash(employeeNumber, lastName, firstName, extension, email, jobTitle);
    }

    @OneToMany(mappedBy = "employeesBySalesRepEmployeeNumber")
    public Collection<CustomerEntity> getCustomersByEmployeeNumber()
    {
        return this.customersByEmployeeNumber;
    }

    public void setCustomersByEmployeeNumber(Collection<CustomerEntity> customersByEmployeeNumber)
    {
        this.customersByEmployeeNumber = customersByEmployeeNumber;
    }

    @ManyToOne @JoinColumn(name = "officeCode", referencedColumnName = "officeCode", nullable = false)
    public OfficeEntity getOfficesByOfficeCode()
    {
        return this.officesByOfficeCode;
    }

    public void setOfficesByOfficeCode(OfficeEntity officesByOfficeCode)
    {
        this.officesByOfficeCode = officesByOfficeCode;
    }

    @ManyToOne @JoinColumn(name = "reportsTo", referencedColumnName = "employeeNumber")
    public EmployeeEntity getEmployeesByReportsTo()
    {
        return this.employeesByReportsTo;
    }

    public void setEmployeesByReportsTo(EmployeeEntity employeesByReportsTo)
    {
        this.employeesByReportsTo = employeesByReportsTo;
    }

    @OneToMany(mappedBy = "employeesByReportsTo") public Collection<EmployeeEntity> getEmployeesByEmployeeNumber()
    {
        return this.employeesByEmployeeNumber;
    }

    public void setEmployeesByEmployeeNumber(Collection<EmployeeEntity> employeesByEmployeeNumber)
    {
        this.employeesByEmployeeNumber = employeesByEmployeeNumber;
    }
}
