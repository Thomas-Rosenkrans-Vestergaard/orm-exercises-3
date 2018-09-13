package com.tvestergaard.exercises;

import com.tvestergaard.exercises.entities.CustomerEntity;
import com.tvestergaard.exercises.entities.EmployeeEntity;
import com.tvestergaard.exercises.entities.OrderEntity;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FacadeTest
{

    private final EntityManagerFactory emf      = Persistence.createEntityManagerFactory("jpau");
    private final EntityManager        em       = emf.createEntityManager();
    private final Facade               instance = new Facade(em);

    @Test
    public void createEmployee()
    {
        String lastName  = "Vestergaard";
        String firstName = "Thomas";
        String extension = "ex1";
        String email     = "tvestergaard@hotmail.com";
        int    office    = 1;
        int    reportsTo = 1002;
        String title     = "Human";

        EmployeeEntity employeeEntity = instance.createEmployee(lastName, firstName, extension, email, office, reportsTo, title);
        EmployeeEntity find           = em.find(EmployeeEntity.class, employeeEntity.getEmployeeNumber());
        assertNotNull(find);
        assertEquals(lastName, find.getLastName());
        assertEquals(firstName, find.getFirstName());
        assertEquals(extension, find.getExtension());
        assertEquals(email, find.getEmail());
        assertEquals(String.valueOf(office), find.getOfficesByOfficeCode().getOfficeCode());
        assertEquals(reportsTo, find.getEmployeesByReportsTo().getEmployeeNumber());
        assertEquals(title, find.getJobTitle());
    }

    @Test
    public void updateCustomer()
    {
        CustomerEntity customerEntity = instance.findCustomer(103);
        instance.transaction(EntityTransaction::rollback, () -> {
            String newName = String.valueOf(new Random().nextInt());
            customerEntity.setCustomerName(newName);
            instance.updateCustomer(customerEntity);
            assertEquals(newName, instance.findCustomer(103).getCustomerName());
        });
    }

    @Test
    public void findCustomer()
    {
        CustomerEntity customerEntity = instance.findCustomer(112);
        assertEquals("Signal Gift Stores", customerEntity.getCustomerName());
    }

    @Test
    public void getEmployeeCount()
    {
        long before = instance.getEmployeeCount();
        instance.transaction(() -> {
            instance.createEmployee("", "", "", "", 1, 1002, "");
        });

        long after = instance.getEmployeeCount();

        assertEquals(before, after - 1);
    }

    @Test
    public void getAllEmployees()
    {
        List<EmployeeEntity> all = instance.getAllEmployees();
        assertEquals(instance.getEmployeeCount(), all.size());
        assertEquals(1002, all.get(0).getEmployeeNumber());
    }


    @Test
    public void getCustomersInCity()
    {
        List<CustomerEntity> customersFromNantes = instance.getCustomersInCity("Nantes");
        for (CustomerEntity customerEntity : customersFromNantes)
            assertEquals("Nantes", customerEntity.getCity());
    }

    @Test
    public void getEmployeeMaxCustomers()
    {
        assertEquals(1401, instance.getEmployeeMaxCustomers().getEmployeeNumber());
    }

    @Test
    public void getOrdersOnHold()
    {
        List<OrderEntity> ordersOnHold = instance.getOrdersOnHold();
        assertEquals(10334, ordersOnHold.get(0).getOrderNumber());
        assertEquals(10401, ordersOnHold.get(1).getOrderNumber());
        assertEquals(10407, ordersOnHold.get(2).getOrderNumber());
        assertEquals(10414, ordersOnHold.get(3).getOrderNumber());
        assertEquals(4, ordersOnHold.size());
    }

    @Test
    public void getOrdersOnHoldCustomer()
    {
        List<OrderEntity> orders = instance.getOrdersOnHold(144);
        assertEquals(1, orders.size());
        assertEquals(10334, orders.get(0).getOrderNumber());
    }
}