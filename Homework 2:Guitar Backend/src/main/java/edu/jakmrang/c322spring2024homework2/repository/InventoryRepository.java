package edu.jakmrang.c322spring2024homework2.repository;

import edu.jakmrang.c322spring2024homework2.model.Guitar;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class InventoryRepository {
    private ArrayList<Guitar> totalGuitars = new ArrayList<>();
    static final String NEW_LINE = System.lineSeparator();
    static final String DATABASE_NAME = "guitars_database.txt";

    static void appendToFile(Path path, String content) throws IOException {
        Files.write(path, content.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }


    public boolean addGuitar(Guitar guitarData) throws IOException {
        Path path = Paths.get(DATABASE_NAME);
        String data = guitarData.toString();
        appendToFile(path, data + NEW_LINE);
        return true;
    }

    //Helper method for deserializing the guitar in the txt file.
    public Guitar parseGuitar(String line) {
        try {
            // Split the line by commas
            String[] values = line.split(",");

            // Extract each property value
            String serialNumber = values[0].trim();
            Double price = Double.parseDouble(values[1].trim());
            String builder = values[2].trim();
            String model = values[3].trim();
            String type = values[4].trim();
            String backWood = values[5].trim();
            String topWood = values[6].trim();

            // Create and return a new Guitar object
            return new Guitar(serialNumber, price, builder, model, type, backWood, topWood);
        } catch (Exception e) {
            System.out.println("Failed to parse guitar....");
            return null;
        }
    }
    public Guitar getGuitar(String serialNumber) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATABASE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Guitar guitar = parseGuitar(line);
                if (guitar != null && guitar.getSerialNumber().equals(serialNumber)) {
                    return guitar;
                }
            }
        }
        return null; // Return null if no guitar with the given serial number is found
    }
    public static Guitar fromLine(String line) {
        String[] tokens = line.split(",");
        return new Guitar(tokens[0], Double.parseDouble(tokens[1]), tokens[2], tokens[3], tokens[4], tokens[5], tokens[6]);
    }
    public List<Guitar> findAll() throws IOException {
        List<Guitar> result = new ArrayList<>();
        Path path = Paths.get(DATABASE_NAME);
        List<String> data = Files.readAllLines(path);
        for (String line : data) {
            if(!line.trim().isEmpty()) {
                Guitar d = fromLine(line);
                result.add(d);
            }
        }
        return result;
    }
    private void loadGuitarsFromFile() throws IOException {
        Path path = Paths.get(DATABASE_NAME);
        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Guitar guitar = parseGuitar(line);
                if (guitar != null) {
                    totalGuitars.add(guitar);
                }
            }
        }
    }

    public List<Guitar> search(Guitar criteria) throws IOException {
        loadGuitarsFromFile(); // Populate the current guitar array so we can search from it.
        return totalGuitars.stream()
                .filter(guitar -> isNullOrEqual(guitar.getSerialNumber(), criteria.getSerialNumber()))
                .filter(guitar -> criteria.getPrice() == null || guitar.getPrice().equals(criteria.getPrice()))
                .filter(guitar -> isNullOrEqual(guitar.getBuilder(), criteria.getBuilder()))
                .filter(guitar -> isNullOrEqual(guitar.getModel(), criteria.getModel()))
                .filter(guitar -> isNullOrEqual(guitar.getType(), criteria.getType()))
                .filter(guitar -> isNullOrEqual(guitar.getBackWood(), criteria.getBackWood()))
                .filter(guitar -> isNullOrEqual(guitar.getTopWood(), criteria.getTopWood()))
                .collect(Collectors.toList());
    }

    private boolean isNullOrEqual(String value1, String value2) {
        return value2 == null || value2.isEmpty() || value1.equals(value2);
    }



}
