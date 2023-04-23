package com.lastdemo.lastdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    PizzaDAO pizzaDAO;
    public List<Pizza> readPizzas() {
        return pizzaDAO.findAll();
    }

    public Pizza getpizzabyid(Integer id) {
        Optional<Pizza> pizza;
        pizza= pizzaDAO.findById(id);
        if(pizza.isPresent())return pizza.get();
        else return null;
    }

    public Pizza addPizza(Pizza pizza){
        return pizzaDAO.save(pizza);
    }

    public void deletepizza(Integer id) {
        System.out.println(id);
        pizzaDAO.deleteById(id);
    }

    public Pizza editPizza(Pizza pizza) {
        deletepizza(pizza.getId());
        addPizza(pizza);
        return pizza;
    }
}
