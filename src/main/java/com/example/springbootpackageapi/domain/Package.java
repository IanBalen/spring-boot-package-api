package com.example.springbootpackageapi.domain;

import com.example.springbootpackageapi.domain.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Package {
    // Razlog koristenja vlastitog generatora je objasnjenje u klasi MyIdGenerator
    // Zbog ideje zadatka sam uz id stavio i packageCode
    // Ideja iza toga je da inače kada se dobiva paket, dobijemo neki kod preko kojeg ga korisnici mogu pratiti
    // On je jedinstven za svaki paket i ne ponavlja se
    @Id
    @GeneratedValue(generator = "MyIdGenerator")
    @GenericGenerator(name = "MyIdGenerator", strategy = "com.example.springbootpackageapi.config.MyIdGenerator")
    private Long id;
    private String packageCode;
    private String name;
    @Enumerated(EnumType.STRING)
    private Status orderStatus;
    private String deliveryAddress;
    private String billingAddress;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    @JoinTable(name = "customer_package",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private Customer customer;

}

