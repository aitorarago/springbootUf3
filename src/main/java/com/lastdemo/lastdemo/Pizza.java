package com.lastdemo.lastdemo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.nio.channels.Pipe;

@Data
@Entity
@Table(name = "pizzas")
public class Pizza {
    @Id
    @Column(name = "idpizza")
    private int id;
    private String name;
    private float price;



    public Pizza(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Pizza(PizzasDTO dto){
        this.id=dto.getId();
        this.name=dto.getName();
        this.price= dto.getPrice();
    }
    public Pizza(){}
}
