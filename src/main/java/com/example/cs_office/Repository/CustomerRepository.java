package com.example.cs_office.Repository;

import com.example.cs_office.Model.Customer;
import com.example.cs_office.Model.TypeRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
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
    // select customer by email list
    @Query("select s from Customer s where s.email Like %:email%")
    public List<Customer> findCustomerByname(@PathParam("email")String email);

    @Query("select s from Customer s where s.address Like %:address%")
    public List<Customer> findaddress(@PathParam("address")String address);


    @Query("select s from Customer s where s.firstName Like %:firstName%")
    public List<Customer> findfirtname(@PathParam("firstName")String firstName);

}
