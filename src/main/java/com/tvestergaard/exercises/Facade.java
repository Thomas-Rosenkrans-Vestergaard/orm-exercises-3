package com.tvestergaard.exercises;

import com.tvestergaard.exercises.entities.CustomerEntity;
import com.tvestergaard.exercises.entities.EmployeeEntity;
import com.tvestergaard.exercises.entities.OfficeEntity;
import com.tvestergaard.exercises.entities.OrderEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.function.Consumer;

public class Facade
{

    private final EntityManager manager;

    public Facade(EntityManager manager)
    {
        this.manager = manager;
    }

    public void transaction(Runnable runnable)
    {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        runnable.run();
        transaction.commit();
    }

    public void transaction(Consumer<EntityTransaction> after, Runnable runnable)
    {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        runnable.run();
        after.accept(transaction);
    }

    public void transaction(Consumer<Facade> consumer)
    {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        consumer.accept(this);
        transaction.commit();
    }

    public void transaction(Consumer<EntityTransaction> after, Consumer<Facade> consumer)
    {
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();
        consumer.accept(this);
        after.accept(transaction);
    }

    public EmployeeEntity createEmployee(
            String lastName,
            String firstName,
            String extension,
            String email,
            int office,
            int reportsTo,
            String title
    )
    {

        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setLastName(lastName);
        employeeEntity.setFirstName(firstName);
        employeeEntity.setExtension(extension);
        employeeEntity.setEmail(email);
        employeeEntity.setOfficesByOfficeCode(manager.find(OfficeEntity.class, String.valueOf(office)));
        employeeEntity.setEmployeesByReportsTo(manager.find(EmployeeEntity.class, reportsTo));
        employeeEntity.setJobTitle(title);

        manager.persist(employeeEntity);
        return employeeEntity;
    }

    public CustomerEntity updateCustomer(CustomerEntity customerEntity)
    {
        Query query = manager.createQuery("UPDATE CustomerEntity SET " +
                                          "customerName = :customerName, " +
                                          "contactLastName = :contactLastName, " +
                                          "contactFirstName = :contactFirstName, " +
                                          "phone = :phone, " +
                                          "addressLine1 = :addressLine1, " +
                                          "addressLine2 = :addressLine2, " +
                                          "city = :city, " +
                                          "state = :state, " +
                                          "postalCode = :postalCode, " +
                                          "country = :country, " +
                                          "salesRepEmployeeNumber = :rep, " +
                                          "creditLimit = :creditLimit " +
                                          "WHERE customerNumber = :customerNumber");

        query.setParameter("customerName", customerEntity.getCustomerName());
        query.setParameter("contactLastName", customerEntity.getContactLastName());
        query.setParameter("contactFirstName", customerEntity.getContactFirstName());
        query.setParameter("phone", customerEntity.getPhone());
        query.setParameter("addressLine1", customerEntity.getAddressLine1());
        query.setParameter("addressLine2", customerEntity.getAddressLine2());
        query.setParameter("city", customerEntity.getCity());
        query.setParameter("state", customerEntity.getState());
        query.setParameter("postalCode", customerEntity.getPostalCode());
        query.setParameter("country", customerEntity.getCountry());
        query.setParameter("rep", customerEntity.getEmployeesBySalesRepEmployeeNumber().getEmployeeNumber());
        query.setParameter("creditLimit", customerEntity.getCreditLimit());
        query.setParameter("customerNumber", customerEntity.getCustomerNumber());

        query.executeUpdate();

        return customerEntity;
    }

    public CustomerEntity findCustomer(int id)
    {
        return manager.find(CustomerEntity.class, id);
    }

    public long getEmployeeCount()
    {
        Query query = manager.createQuery("SELECT count(e.id) FROM EmployeeEntity e");
        return (long) query.getSingleResult();
    }

    public List<EmployeeEntity> getAllEmployees()
    {
        return manager.createQuery("SELECT e FROM EmployeeEntity e").getResultList();
    }

    public List<CustomerEntity> getCustomersInCity(String city)
    {
        Query query = manager.createQuery("SELECT c FROM CustomerEntity c WHERE c.city = :city");
        query.setParameter("city", city);
        return query.getResultList();
    }

    public EmployeeEntity getEmployeeMaxCustomers()
    {
        //    Query query = manager.createQuery("SELECT e FROM EmployeeEntity e WHERE e.employeeNumber = " +
        //                                      "(SELECT c.salesRepEmployeeNumber emp FROM CustomerEntity c GROUP BY emp " +
        //                                      "ORDER BY count(c.salesRepEmployeeNumber) DESC LIMIT 1)");


        TypedQuery<EmployeeEntity> query = manager.createQuery("SELECT c.employeesBySalesRepEmployeeNumber FROM CustomerEntity c " +
                                                               "GROUP BY c.employeesBySalesRepEmployeeNumber " +
                                                               "ORDER BY count(c.employeesBySalesRepEmployeeNumber) DESC", EmployeeEntity.class);

        query.setMaxResults(1);
        return query.getResultList().get(0);
    }

    public List<OrderEntity> getOrdersOnHold()
    {
        return manager.createQuery("SELECT o FROM OrderEntity o WHERE o.status = 'On hold'", OrderEntity.class)
                .getResultList();
    }

    public List<OrderEntity> getOrdersOnHold(int customer)
    {
        return manager.createQuery("SELECT o FROM OrderEntity o " +
                                   "WHERE o.status = 'On hold' " +
                                   "AND o.customersByCustomerNumber.customerNumber = :customerNumber", OrderEntity.class)
                .setParameter("customerNumber", customer)
                .getResultList();
    }

    public List<String> getCustomerNamesSorted()
    {
        //        return manager.createQuery("")
        return null;
    }
}
