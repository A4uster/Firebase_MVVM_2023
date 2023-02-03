package com.example.firebase_mvvm_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private EditText mETNom, mETIngreddientes,mETPreu;
    private Button mBtnAñadir, mBtanActuaizar, mBtnEsborrar;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InicializarComponents();
        InicializarListeners();
    }

    private void InicializarListeners() {
        mBtnAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AñadirPizza();
            }
        });
        mBtanActuaizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActualizarPizza();
            }
        });
        mBtnEsborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EsborrarPizza();
            }
        });
    }

    private void EsborrarPizza() {
    }

    private void ActualizarPizza() {
    }

    private void AñadirPizza() {

        String nom = mETNom.getText().toString();
        String ingredients= mETIngreddientes.getText().toString();
        String precio = mETPreu.getText().toString();
        String uid = UUID.randomUUID().toString();

        Pizza pizza = new Pizza( nom,  ingredients,  precio,  uid);


        mReference.child("Pizza").child(uid).setValue(pizza);
    }

    private void InicializarComponents(){
        mETNom = findViewById(R.id.ET_Nom);
        mETIngreddientes = findViewById(R.id.ET_Ingrediente);
        mETPreu = findViewById(R.id.ET_Precio);
        mBtanActuaizar = findViewById(R.id.BT_Actuaizar);
        mBtnAñadir = findViewById(R.id.BT_Añadir);
        mBtnEsborrar = findViewById(R.id.BT_Esborrar);
        mDatabase = FirebaseDatabase.getInstance("https://fir-mvvm-2023-default-rtdb.europe-west1.firebasedatabase.app");
        mReference = mDatabase.getReference();



    }
}