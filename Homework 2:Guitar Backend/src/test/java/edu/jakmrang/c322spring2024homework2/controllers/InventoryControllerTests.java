package edu.jakmrang.c322spring2024homework2.controllers;

import edu.jakmrang.c322spring2024homework2.model.Guitar;
import edu.jakmrang.c322spring2024homework2.controllers.InventoryController;
import edu.jakmrang.c322spring2024homework2.repository.InventoryRepository;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryControllerTests {
    private InventoryRepository inventoryRepository = new InventoryRepository();

    @Test
    public void testAddGuitar() throws IOException {
        Guitar guitarToAdd = new Guitar("SN12345", 1500.0, "Fender", "Stratocaster", "Electric", "Alder", "Maple");
        boolean added = inventoryRepository.addGuitar(guitarToAdd);
        assertTrue(added);
    }

    @Test
    public void testAddGuitar2() throws IOException {
        Guitar guitarToAdd = new Guitar("SN100", 100000.0, "Gibson", "AC", "Electric", "Mahogony", "Walnut");
        boolean added = inventoryRepository.addGuitar(guitarToAdd);
        assertTrue(added);
    }

    @Test
    public void testGetGuitar() {
        String serialNumber = "SN12345";
        try {
            Guitar guitar = inventoryRepository.getGuitar(serialNumber);
            assertNotNull(guitar);
            assertEquals(serialNumber, guitar.getSerialNumber());
        } catch (IOException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testSearch() throws IOException {
        Guitar guitar = new Guitar("SN100", null, null, null, null, null, null); // Emulates the search preferences/input
        List<Guitar> results = inventoryRepository.search(guitar);
        assertNotNull(results);
        assertFalse(results.isEmpty());
    }
}
