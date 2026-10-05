package com.example.smartpantrymanager.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import com.example.smartpantrymanager.models.Ingredient;
import android.content.ContentValues;
import java.util.ArrayList;

//class helps to create and manage sqlite database
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "smart_pantry.db"; // name of database file
    private static final int DATABASE_VERSION = 2; // database version used when changes are made to database.

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);// constructor creates or opens database
    }

    @Override // will be called when the database is created for the 1st time.
    public void onCreate(SQLiteDatabase db) {
// SQL statement used to create pantry table.
        String createPantryTable = "CREATE TABLE pantry (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ingredient_name TEXT NOT NULL, " +
                "quantity REAL NOT NULL, " +
                "unit TEXT NOT NULL, " +
                "expiry_date TEXT" +
                ")";

        db.execSQL(createPantryTable); // executes sql statement and creates table
// Sql statment used to create the recipe table
        String createRecipeTable = "CREATE TABLE recipes (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "recipe_name TEXT NOT NULL, " +
                "required_ingredients TEXT NOT NULL, " +
                "preparation_steps TEXT NOT NULL" +
                ")";

        db.execSQL(createRecipeTable);


    }
    @Override // called when the database version increases
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS pantry");// this deletes the old pantry table
        onCreate(db); // this creates the table again with a new structure
    }
//CRUD METHODS
//Adds new ingredient to pantry
    public long addIngredient(Ingredient ingredient) {

//get a writable database which allows to make changes to database
        SQLiteDatabase db = this.getWritableDatabase();

//this stores the values of the ingredient
        ContentValues values = new ContentValues();
        values.put("ingredient_name", ingredient.getIngredientName());
        values.put("quantity", ingredient.getQuantity());
        values.put("unit", ingredient.getUnit());
        values.put("expiry_date", ingredient.getExpiryDate());

//inserts the ingredients into pantry table
        long result = db.insert("pantry", null, values);

        db.close();//closes the database

        return result;
    }

// gets all ingredients from the pantry
    public ArrayList<Ingredient> getAllIngredients() {


        SQLiteDatabase db = this.getReadableDatabase();//gets database that is readable

//this creates a list that stores the ingredients
        ArrayList<Ingredient> ingredients = new ArrayList<>();

//this queries all ingredients from the pantry table
        Cursor cursor = db.rawQuery("SELECT * FROM pantry", null);

//Goes/loops through each row in the database
        if (cursor.moveToFirst()) {
            do {
//This gets values from the current row
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("ingredient_name"));
                double quantity = cursor.getDouble(cursor.getColumnIndexOrThrow("quantity"));
                String unit = cursor.getString(cursor.getColumnIndexOrThrow("unit"));
                String expiryDate = cursor.getString(cursor.getColumnIndexOrThrow("expiry_date"));

//this makes an ingredient object
                Ingredient ingredient = new Ingredient(
                        id,
                        name,
                        quantity,
                        unit,
                        expiryDate
                );

// Adds the ingredients to the list
                ingredients.add(ingredient);

            } while (cursor.moveToNext());
        }


        cursor.close();//this closes the cursor

        db.close();

        return ingredients;
    }

//Gets one ingredient from the pantry using its Id
    public Ingredient getIngredientById(int ingredientId) {

//this gets a readable database
        SQLiteDatabase db = this.getReadableDatabase();

//this finds the ingredient with the matching Id
        Cursor cursor = db.rawQuery(
                "SELECT * FROM pantry WHERE id = ?",
                new String[]{String.valueOf(ingredientId)}
        );

//This will store the ingredient found
        Ingredient ingredient = null;

//checks if an ingredient was found
        if (cursor.moveToFirst()) {

// Gets the ingredient id
            int id = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id")
            );

// Gets the ingredient name
            String name = cursor.getString(
                    cursor.getColumnIndexOrThrow("ingredient_name")
            );

//gets the quantity
            double quantity = cursor.getDouble(
                    cursor.getColumnIndexOrThrow("quantity")
            );

//gets the unit
            String unit = cursor.getString(
                    cursor.getColumnIndexOrThrow("unit")
            );

//gets the expiry date
            String expiryDate = cursor.getString(
                    cursor.getColumnIndexOrThrow("expiry_date")
            );

// creates an ingredient object using the information that has been found
            ingredient = new Ingredient(
                    id,
                    name,
                    quantity,
                    unit,
                    expiryDate
            );
        }

        cursor.close();// Closes the cursor

        db.close();// Closes the database

        return ingredient;// Returns the ingredient
    }

//this updates an ingredient in pantry that already exists
    public int updateIngredient(Ingredient ingredient) {

        SQLiteDatabase db = this.getWritableDatabase();

//this stores updated ingredient values
        ContentValues values = new ContentValues();
        values.put("ingredient_name", ingredient.getIngredientName());
        values.put("quantity", ingredient.getQuantity());
        values.put("unit", ingredient.getUnit());
        values.put("expiry_date", ingredient.getExpiryDate());

//this updates the ingredient using its id
        int result = db.update(
                "pantry",
                values,
                "id = ?",
                new String[]{String.valueOf(ingredient.getId())}
        );

        db.close(); // closes the database

        return result;
    }

//deletes a ingredient from the pantry
    public int deleteIngredient(int ingredientId) {

        SQLiteDatabase db = this.getWritableDatabase();

//This deletes the ingredient using its id
        int result = db.delete(
                "pantry",
                "id = ?",
                new String[]{String.valueOf(ingredientId)}
        );

        db.close();

        return result;
    }
}