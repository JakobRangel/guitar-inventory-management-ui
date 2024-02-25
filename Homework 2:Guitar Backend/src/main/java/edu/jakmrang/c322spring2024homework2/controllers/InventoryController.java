package edu.jakmrang.c322spring2024homework2.controllers;

import edu.jakmrang.c322spring2024homework2.model.Guitar;
import edu.jakmrang.c322spring2024homework2.repository.InventoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class InventoryController {
    private InventoryRepository inventoryRepository;
    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }



    @PostMapping("/add")
    public boolean add(@RequestBody Guitar guitar) {
        try {
            inventoryRepository.addGuitar(guitar);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    @GetMapping("/getall")
    public List<Guitar> findAll() {
        try {
            return inventoryRepository.findAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/find/{serialNumber}")
    public Guitar find(@PathVariable String serialNumber) throws IOException {
        return inventoryRepository.getGuitar(serialNumber);
    }

    @GetMapping("/search")
    public List<Guitar> search(@RequestParam(required = false) String serialNumber,
                               @RequestParam(required = false) Double price,
                               @RequestParam(required = false) String builder,
                               @RequestParam(required = false) String model,
                               @RequestParam(required = false) String type,
                               @RequestParam(required = false) String backWood,
                               @RequestParam(required = false) String topWood
                                   ) throws IOException {
        Guitar searchCriteria = new Guitar(serialNumber, price, builder, model, type, backWood, topWood);
        return inventoryRepository.search(searchCriteria);
    }
}
