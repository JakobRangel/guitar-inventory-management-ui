package edu.jakmrang.c322spring2024homework2.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HomeControllerTests {
    @Test
    void rootTest() {
        HomeController homeController = new HomeController();

        assertTrue(homeController.greeting().equalsIgnoreCase("Welcome to the inventory service")); // The required string.
    }
}
