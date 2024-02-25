package edu.jakmrang.c322spring2024homework2.model;

public class Guitar {
    private String serialNumber;
    Double price;
    private String builder;
    private String model;
    private String type;
    private String backWood;
    private String topWood;

    // Constructor method
    public Guitar() {
        // Necessary for JSON response from the post endpoint
    }
    public Guitar(String serialNumber, Double price, String builder, String model, String type, String backWood, String topWood) {
        this.serialNumber = serialNumber;
        this.price = price;
        this.builder = builder;
        this.model = model;
        this.type = type;
        this.backWood = backWood;
        this.topWood = topWood;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getBuilder() {
        return this.builder;
    }

    public void setBuilder(String builder) {
        this.builder = builder;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBackWood() {
        return this.backWood;
    }

    public void setBackWood(String backWood) {
        this.backWood = backWood;
    }

    public String getTopWood() {
        return this.topWood;
    }

    public void setTopWood(String topWood) {
        this.topWood = topWood;
    }


    //Need to make an override for to_string to make it easier to parse it to the txt file:
    //Override is used to override the default to_string method in the superclass(which would return the object, which isn't legible) with our own breakdown of the object.
    @Override
    public String toString() {
        return serialNumber + "," +
                price + "," +
                builder + "," +
                model + "," +
                type + "," +
                backWood + "," +
                topWood;
    }
}