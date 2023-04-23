package com.lastdemo.lastdemo;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class PizzasDTO {
    @Id
    private int id;
    private String name;
    private float price;

    public PizzasDTO(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public PizzasDTO(Pizza pizza){
        this.id=pizza.getId();
        this.name=pizza.getName();
        this.price=pizza.getPrice();
    }
    public PizzasDTO(){}


}
