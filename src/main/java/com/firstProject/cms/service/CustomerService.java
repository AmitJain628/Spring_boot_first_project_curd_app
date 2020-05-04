package com.firstProject.cms.service;

import com.firstProject.cms.dao.CustomerDAO;
import com.firstProject.cms.exception.CustomerNotFoundException;
import com.firstProject.cms.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    private List<Customer> customerList = new CopyOnWriteArrayList<>();
    private int customerIdCount = 1;

    public Customer addCustomer(Customer customer) {
        /*customer.setCustomerId(customerIdCount);
          customerList.add(customer);
          customerIdCount++; */

          /*return  customer; */
        return customerDAO.save(customer);
    }

    public List<Customer> getCustomers() {

        return   customerDAO.findAll();

       // return  customerList;
    }

    public Customer getCustomer(int customerId){

         Optional<Customer> optionalCustomer =  customerDAO.findById(customerId);

         if(!optionalCustomer.isPresent()) {
             throw new CustomerNotFoundException("Customer record not available");
         }
        return optionalCustomer
                          .get();

        /*return customerList
               .stream()
               .filter(customer -> customer.getCustomerId() == customerId)
               .findFirst()
               .get(); */
    }

    public  Customer updateCustomer(int customerId, Customer customer) {

        /*
        customerList

                .stream()
                .forEach(customer1 -> {
                    if(customer1.getCustomerId() == customerId) {
                        customer1.setCustomerEmail(customer.getCustomerEmail());
                        customer1.setCustomerFirstName((customer.getCustomerFirstName()));
                        customer1.setCustomerLastName(customer.getCustomerLastName());
                    }
                });

        return customerList
                .stream()
                .filter(c -> c.getCustomerId() == customerId)
                .findFirst()
                .get();
                       */
        customer.setCustomerId(customerId);

       return customerDAO.save(customer);
    }

    public void deleteCustomer(int customerId) {
        /*
        customerList
                 .stream()
                 .forEach(customer -> {
                     if(customer.getCustomerId() == customerId) {
                         customerList.remove(customer);
                     }
                 });
                 */

        customerDAO.deleteById(customerId);

    }


}
