package com.example.springbootpackageapi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    // Razlog koristenja vlastitog generatora je objasnjenje u klasi MyIdGenerator
    @Id
    @GeneratedValue(generator = "MyIdGenerator")
    @GenericGenerator(name = "MyIdGenerator", strategy = "com.example.springbootpackageapi.config.MyIdGenerator")
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    @OneToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.REFRESH,
            CascadeType.PERSIST
    })
    @JoinTable(name = "customer_package",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "package_id")
    )
    private List<Package> packages;
    // Stavio sam OneToMany jer mi je intuitivno bilo da jedan customer moze imati vise paketa
    // a da jedan paket moze imati samo jednog customera
}
