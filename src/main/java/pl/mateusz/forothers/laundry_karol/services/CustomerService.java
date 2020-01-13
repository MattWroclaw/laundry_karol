package pl.mateusz.forothers.laundry_karol.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mateusz.forothers.laundry_karol.entities.Customer;
import pl.mateusz.forothers.laundry_karol.repositories.CustomerRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public List<Customer> customers() {
        return repository.findAll();
    }

    public Customer findByName(String name) {
        Customer givenCustomer = repository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("No such customer like: " + name));
        return givenCustomer;
    }

    public Customer findById(long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No customer with id=" + id));
        return customer;
    }


    public Customer addCustomer(Customer customer) {
        repository.save(customer);
        return customer;
    }

    public Customer updateCustomer(long id, String name, String city, String street, String telephone, String email) {
        Customer customer = findById(id);

        customer.setName(name);
        customer.setCity(city);
        customer.setStreet(street);
        customer.setTelephone(telephone);
        customer.setEmail(email);

        repository.save(customer);
        return customer;
    }

    public void deleteCustomer(long id){
        Customer customer = findById(id);
        repository.delete(customer);
    }
}
