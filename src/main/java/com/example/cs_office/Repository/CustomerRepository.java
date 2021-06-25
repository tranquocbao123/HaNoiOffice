package com.example.cs_office.Repository;

import com.example.cs_office.Model.Branch;
import com.example.cs_office.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select s from Customer s where s.id = ?1")
    Optional<Customer> findCustomerById(Integer id);

    //select customer by status
    @Query("select c from Customer c where c.status = ?1")
    List<Customer> findCustomerByStatus(boolean status);

    //select customer by email
    @Query("select c from Customer c where c.email = ?1")
    Customer findCustomerByEmail(String email);

    //select customer by name
    @Query("select c from Customer c where c.firstName like %:firstname% and c.lastName like %:lastname%")
    List<Customer> findCustomerByName(@Param("firstname") String firstname,@Param("lastname") String lastname);

}
