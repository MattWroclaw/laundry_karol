package pl.mateusz.forothers.laundry_karol.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mateusz.forothers.laundry_karol.entities.Customer;
import pl.mateusz.forothers.laundry_karol.entities.Laundry;
import pl.mateusz.forothers.laundry_karol.repositories.LaundryRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LaundryService {

    private final LaundryRepository repository;

    public List<Laundry> laundries(){
        return repository.findAll();
    }

    public Laundry addLaundry( Laundry laundry){
        laundry.setDelivered(LocalDateTime.now());
        repository.save(laundry);
        return laundry;
    }

    public Laundry findById(long id){
        Laundry laundry = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Could not find laundry with id=" + id));
        return laundry;
    }

    public void updateLaundryProcess(Laundry laundry, String station){
        switch (station){

            case "startWashing":
                laundry.setStartWashing(LocalDateTime.now());
                break;
            case "startDrying":
                laundry.setStartDrying(LocalDateTime.now());
                break;
            case "finished":
                laundry.setFinished(LocalDateTime.now());
                break;
        }
        repository.save(laundry);
    }

    public Laundry findNewestLaundry(){
        Laundry newestLaundry = repository.findTopByOrderByIdDesc()
                .orElseThrow(()->new NoSuchElementException("There is no laundry in process"));
        return newestLaundry;
    }

    public List <Laundry> readyForWashing(){
        List<Laundry> laundries = laundries();
        List<Laundry> beforeWashing = laundries.stream()
                .filter(m->(m.getDelivered() != null) && (m.getStartWashing() == null))
                .collect(Collectors.toList());
        return beforeWashing;
     }

    public List<Laundry> readyForDrying(){
        List<Laundry> laundries = laundries();
        List<Laundry> beforeDrying = laundries.stream().filter(m -> (m.getStartWashing() != null) && (m.getStartDrying() == null))
                .collect(Collectors.toList());
        return beforeDrying;
    }

    public List<Laundry> readyForFinish(){
        List<Laundry> laundries = laundries();
        List<Laundry> beforeFinish = laundries.stream()
                .filter(m-> (m.getStartDrying() != null) && (m.getFinished() == null))
                .collect(Collectors.toList());
        return beforeFinish;
    }


}
