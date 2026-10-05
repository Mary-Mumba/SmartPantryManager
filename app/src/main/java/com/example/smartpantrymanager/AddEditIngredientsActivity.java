package com.example.smartpantrymanager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.smartpantrymanager.database.DatabaseHelper;
import com.example.smartpantrymanager.models.Ingredient;

//this Activity is used to add ingredients to the pantry
public class AddEditIngredientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

// Gets the ingredient id sent from the edit button
        int ingredientId = getIntent().getIntExtra("ingredient_id", -1);

// Checks if user an editing an existing ingredient
        boolean isEditing = ingredientId != -1;

        EdgeToEdge.enable(this);//allows ofr edge to edge display

//This connects this activity to it's XML layout
        setContentView(R.layout.activity_add_edit_ingredients);

        // Connect the input fields to the Java code
        EditText editIngredientName = findViewById(R.id.editIngredientName);
        EditText editQuantity = findViewById(R.id.editQuantity);
        EditText editUnit = findViewById(R.id.editUnit);
        EditText editExpiryDate = findViewById(R.id.editExpiryDate);

//makes a connection to the save button
        Button btnSaveIngredient = findViewById(R.id.btnSaveIngredient);

//This Creates the database helper
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

//gets the existing ingredient when editing
        if (isEditing) {

            Ingredient existingIngredient =
                    databaseHelper.getIngredientById(ingredientId);

//This checks if the ingredient was found
            if (existingIngredient != null) {

//Shows the existing ingredient name
                editIngredientName.setText(
                        existingIngredient.getIngredientName()
                );

//Shows the existing quantity
                editQuantity.setText(
                        String.valueOf(existingIngredient.getQuantity())
                );

//shows the existing unit
                editUnit.setText(
                        existingIngredient.getUnit()
                );

//shows existing expiry date
                editExpiryDate.setText(
                        existingIngredient.getExpiryDate()
                );
            }
        }

//Saves ingredients when the button is clicked
        btnSaveIngredient.setOnClickListener(v -> {

//this gets information inputted by the user
            String name = editIngredientName.getText().toString().trim();
            String quantityText = editQuantity.getText().toString().trim();
            String unit = editUnit.getText().toString().trim();
            String expiryDate = editExpiryDate.getText().toString().trim();

//this checks the required fields are not empty
            if (name.isEmpty() || quantityText.isEmpty() || unit.isEmpty()) {
                Toast.makeText(this, "Please ensure that all required fields are filled", Toast.LENGTH_SHORT).show();
                return;
            }

//converts the quantity text to a number
            double quantity = Double.parseDouble(quantityText);

//Create an Ingredient object
            Ingredient ingredient;

            if (isEditing) {

//Creates the ingredient with the existing id
                ingredient = new Ingredient(
                        ingredientId,
                        name,
                        quantity,
                        unit,
                        expiryDate
                );

            } else {

                ingredient = new Ingredient(//creates a new ingredient
                        name,
                        quantity,
                        unit,
                        expiryDate
                );
            }

//this saves the ingredient to the database
            long result;

            if (isEditing) {

//updates the existing ingredient
                result = databaseHelper.updateIngredient(ingredient);

            } else {

// adds a new ingredient
                result = databaseHelper.addIngredient(ingredient);
            }

//checks if the ingredient was saved successfully
            if (result != -1) {

                if (isEditing) {

                    // Shows a message when an ingredient is updated
                    Toast.makeText(
                            this,
                            "The ingredient has been updated successfully",
                            Toast.LENGTH_LONG
                    ).show();

                } else {

// Shows the message when a new ingredient is added
                    Toast.makeText(
                            this,
                            "The ingredient has been saved successfully",
                            Toast.LENGTH_LONG
                    ).show();
                }

                finish(); // closes the activity after saving

            } else {

//shows a message if saving or updating has failed
                Toast.makeText(
                        this,
                        "Sorry! Failed to save the ingredient",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}