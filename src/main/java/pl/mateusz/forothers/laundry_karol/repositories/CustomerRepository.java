package pl.mateusz.forothers.laundry_karol.repositories;

import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.forothers.laundry_karol.entities.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByName(String name);
}
