package com.example.smartpantrymanager.models;

//this class represents a recipe stored in the database
public class Recipes {

    private int id; // Unique id for recipe

    private String recipeName;// name of recipe

    private String requiredIngredients; //the ingredients needed for the recipe

    private String preparationSteps;// instructions for making recipe

// this constructor is used when a recipe already has an id
    public Recipes(int id, String recipeName, String requiredIngredients, String preparationSteps) {
        this.id = id;
        this.recipeName = recipeName;
        this.requiredIngredients = requiredIngredients;
        this.preparationSteps = preparationSteps;
    }

//this constructor is used when creating a new recipe
    public Recipes(String recipeName, String requiredIngredients, String preparationSteps) {
        this.recipeName = recipeName;
        this.requiredIngredients = requiredIngredients;
        this.preparationSteps = preparationSteps;
    }

/** the followng returns the recipe
 * id
 * name
 * needed ingredients and preperation steps
 */
    public int getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public String getRequiredIngredients() {
        return requiredIngredients;
    }

    // Returns the preparation steps
    public String getPreparationSteps() {
        return preparationSteps;
    }

 /** the following updates the recipe
     * name
     * required ingredients and preparation steps
     * using set()
     */
    public void setId(int id) {
        this.id = id;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public void setRequiredIngredients(String requiredIngredients) {
        this.requiredIngredients = requiredIngredients;
    }

    public void setPreparationSteps(String preparationSteps) {
        this.preparationSteps = preparationSteps;
    }
}