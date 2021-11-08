package com.example.multipledatasource.entity.a;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name="same",schema = "ADB")
public class A {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;

    public A(String name) {
        this.name = name;
    }
}