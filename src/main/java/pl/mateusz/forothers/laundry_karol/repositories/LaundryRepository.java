package pl.mateusz.forothers.laundry_karol.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mateusz.forothers.laundry_karol.entities.Laundry;

import java.util.Optional;

public interface LaundryRepository extends JpaRepository<Laundry, Long> {

    Optional<Laundry> findById(long id);

    Optional<Laundry> findTopByOrderByIdDesc();

}
