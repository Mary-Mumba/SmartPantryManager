package com.example.smartpantrymanager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartpantrymanager.database.DatabaseHelper;
import com.example.smartpantrymanager.PantryIngredientsAdapter;
import com.example.smartpantrymanager.models.Ingredient;
import android.content.Intent;
import android.widget.Button;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper; // database helper
    RecyclerView recyclerPantry; //Recyclerview
    PantryIngredientsAdapter pantryAdapter; // pantry adapter

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//This will connect this activity to the main XML layout
        setContentView(R.layout.activity_main);

//This connects the RecyclerView to the the Java code
        recyclerPantry = findViewById(R.id.recyclerPantry);

//creates the database helper
      databaseHelper = new DatabaseHelper(this);

//this sets the recyclerview so that it can display pantry items vertically
        recyclerPantry.setLayoutManager(
                new LinearLayoutManager(this)
        );
        loadIngredients();

//Connects the buttons to the Java code
        Button btnAddIngredient = findViewById(R.id.btnAddIngredient);
        Button btnSuggestedRecipes = findViewById(R.id.btnSuggestedRecipes);
        Button btnSettings = findViewById(R.id.btnSettings);

//This Opens the Add Ingredient screen
        btnAddIngredient.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditIngredientsActivity.class);
            startActivity(intent);
        });

//Opens the Suggested Recipes screen
        btnSuggestedRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SuggestedRecipeActivity.class);
            startActivity(intent);
        });

// Opens the Settings screen
        btnSettings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }
//This method is used to load ingredients from the database
    private void loadIngredients() {

// Gets all the ingredients saved in the database
        ArrayList<Ingredient> ingredientList =
                databaseHelper.getAllIngredients();

// Creates the pantry adapter
        pantryAdapter = new PantryIngredientsAdapter(ingredientList);

// Connects the adapter to the RecyclerView
        recyclerPantry.setAdapter(pantryAdapter);
    }

//This runs when we return to the MainActivity
    @Override
    protected void onResume() {
        super.onResume();

        //Reloads the ingredients after adding an ingredient
        if (databaseHelper != null) {
            loadIngredients();
        }
    }
}