package com.example.smartpantrymanager.models;

//Class represents the ingredient that will be stored in the pantry
public class Ingredient {

    private int id; //unique id for the ingredient
    private String ingredientName;
    private double quantity;// amount of ingredient that is available.
    private String unit; // used to measure qauntity like g or kg
    private String expiryDate; // expiry date of the ingredient, can be optional

//this constructor is used if an ingredient already has an id
    public Ingredient(int id, String ingredientName, double quantity, String unit, String expiryDate) {
        this.id = id;
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
        this.expiryDate = expiryDate;
    }

// this constructor is used to add a new ingredient to the pantry.
    public Ingredient(String ingredientName, double quantity, String unit, String expiryDate) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
        this.expiryDate = expiryDate;
    }
// id of the ingredient is returned
    public int getId() {
        return id;
    }
//ingredient name is returned
    public String getIngredientName() {
        return ingredientName;
    }
// ingredient quantity is returned
    public double getQuantity() {
        return quantity;
    }
//returns unit
    public String getUnit() {
        return unit;
    }
    // returns ingredient expiry date
    public String getExpiryDate() {
        return expiryDate;
    }

    public void setId(int id) {
        this.id = id;
    }
/**  The next code uses set... to upate the ingediennt:
 *  id
 *  name
 *  unit and qauntity
 *  lastly expiry date
**/
    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
}
