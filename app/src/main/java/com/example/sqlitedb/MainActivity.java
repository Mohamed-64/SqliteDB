package com.example.sqlitedb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTextModel, editTextColor, editTextDpl;
    Button btnSave, btnRestore;
    MyDatabase db;//pointer to the database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextModel = findViewById(R.id.editTextModel);
        editTextColor = findViewById(R.id.editTextColor);
        editTextDpl = findViewById(R.id.editTextDpl);
        btnSave = findViewById(R.id.btnSave);
        btnRestore = findViewById(R.id.btnRestore);
        db = new MyDatabase(this);
        //setting listeners to the buttons
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String model = editTextModel.getText().toString();
                String color = editTextColor.getText().toString();
                String dpl = editTextDpl.getText().toString();
                boolean res = db.addNewCar(new Car(model, color, dpl));
                if (res)
                    Toast.makeText(MainActivity.this, "Car added successfully", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        btnRestore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Car> cars = db.searchForCar("2020");
                Toast.makeText(MainActivity.this, "#cars = " + cars.size(), Toast.LENGTH_SHORT).show();
                for (Car car : cars) {
                    Log.d("car" + car.getId() + " = ", car.getModel());
                }
            }
        });
    }
}