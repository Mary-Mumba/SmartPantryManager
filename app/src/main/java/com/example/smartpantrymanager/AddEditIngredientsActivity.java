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

        EdgeToEdge.enable(this);//allows ofr edge to edge display

//This connects this activity to it's XML layout
        setContentView(R.layout.activity_add_edit_ingredients);

        // Connect the input fields to the Java code
        EditText editIngredientName = findViewById(R.id.editIngredientName);
        EditText editQuantity = findViewById(R.id.editQuantity);
        EditText editUnit = findViewById(R.id.editUnit);
        EditText editExpiryDate = findViewById(R.id.editExpiryDate);

//Make a connection to the save button
        Button btnSaveIngredient = findViewById(R.id.btnSaveIngredient);

//This Creates the database helper
        DatabaseHelper databaseHelper = new DatabaseHelper(this);

//Saves ingredients when the button is clicked
        btnSaveIngredient.setOnClickListener(v -> {

//this gets information inputted by the user
            String name = editIngredientName.getText().toString().trim();
            String quantityText = editQuantity.getText().toString().trim();
            String unit = editUnit.getText().toString().trim();
            String expiryDate = editExpiryDate.getText().toString().trim();

//Checks the required fields are not empty
            if (name.isEmpty() || quantityText.isEmpty() || unit.isEmpty()) {
                Toast.makeText(this, "Please ensure that all required fields are filled", Toast.LENGTH_SHORT).show();
                return;
            }

//Converts the quantity text to a number
            double quantity = Double.parseDouble(quantityText);

            // Create an Ingredient object
            Ingredient ingredient = new Ingredient(
                    name,
                    quantity,
                    unit,
                    expiryDate
            );

//This adds the ingredients to the database
            long result = databaseHelper.addIngredient(ingredient);

//checks if the ingredient was saved successfully
            if (result != -1) {
                Toast.makeText(this, "The ingredient has been saved successfully", Toast.LENGTH_SHORT).show();

                finish(); //closes the activity after saving
            } else {
                Toast.makeText(this, "Sorry! Failed to save the ingredient", Toast.LENGTH_SHORT).show();
            }
        });
    }
}