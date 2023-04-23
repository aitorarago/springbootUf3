package com.lastdemo.lastdemo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    ObjectMapper objectMapper = new ObjectMapper();



    public List<PizzasDTO> readAllPizzas() {
       List<Pizza> pizzas = pizzaService.readPizzas();
      return pizzas.stream().map(PizzasDTO::new).collect(Collectors.toList());
    }

    public PizzasDTO getpizzabyid(Integer id) {
        Pizza p = pizzaService.getpizzabyid(id);
        return new PizzasDTO(p);
    }

    public float getPricebyid(Integer id) {
        Pizza p = pizzaService.getpizzabyid(id);
        return p.getPrice();
    }

    public PizzasDTO addPizza(PizzasDTO pizza) {
        Pizza p = pizzaService.addPizza(new Pizza(pizza));
        return new PizzasDTO(p);
    }

    public void deletepizza(Integer id) {
        pizzaService.deletepizza(id);
    }

    public PizzasDTO editPizza(PizzasDTO pizzasDTO) {
        Pizza p = pizzaService.editPizza(new Pizza(pizzasDTO));
        return new PizzasDTO(p);
    }

    public PizzasDTO actualizarPrecio(JsonPatch patch, PizzasDTO pizza) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(pizza, JsonNode.class));
        return objectMapper.treeToValue(patched, PizzasDTO.class);
    }
}
