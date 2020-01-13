package pl.mateusz.forothers.laundry_karol.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "client")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String city;
    private String street;
    private String telephone;
    private String email;

}
