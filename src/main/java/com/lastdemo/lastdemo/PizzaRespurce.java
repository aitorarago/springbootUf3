package com.lastdemo.lastdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(PizzaRespurce.PIZZA)
public class PizzaRespurce {
    public static final String PIZZA = "/v1/pizzas";

    @Autowired
    PizzaController pizzaController;

    @GetMapping
    public List<PizzasDTO> pizzas(){
       return pizzaController.readAllPizzas();

    }
    @GetMapping("/{id}")
    public PizzasDTO pizzasDTO(@PathVariable Integer id){
        return pizzaController.getpizzabyid(id);
    }

    @GetMapping("/{id}/price")
    public Map<String, Float> pizzaEmail(@PathVariable Integer id){
        return Collections.singletonMap("Price",pizzaController.getPricebyid(id));
    }
    @PostMapping
    public PizzasDTO newUser(@RequestBody PizzasDTO pizza){
        return pizzaController.addPizza(pizza);
    }

    @DeleteMapping("/{id}")
    public void getUserDelID(@PathVariable Integer id){
        pizzaController.deletepizza(id);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json-patch+json;charset=UTF-8")
    public PizzasDTO subirPrecios(@PathVariable Integer id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        PizzasDTO customerPatched = pizzaController.actualizarPrecio(patch, pizzaController.getpizzabyid(id));
        pizzaController.editPizza(customerPatched);
        return ResponseEntity.ok(customerPatched).getBody();
    }


    @PutMapping("/{id}")
    public PizzasDTO pizzasDTOPUT(@RequestBody PizzasDTO pizzasDTO){
        return pizzaController.editPizza(pizzasDTO);
    }

}
