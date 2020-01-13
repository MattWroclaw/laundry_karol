package pl.mateusz.forothers.laundry_karol.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table
public class Laundry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private int towelsAmount;

    private int bedclothesAmount;

    private LocalDateTime delivered;

    private LocalDateTime startWashing;

    private LocalDateTime startDrying;

    private LocalDateTime finished;

}
