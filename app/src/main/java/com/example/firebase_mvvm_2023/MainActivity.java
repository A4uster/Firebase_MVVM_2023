package com.example.firebase_mvvm_2023;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    private EditText mETNom, mETIngreddientes,mETPreu;
    private Button mBtnAñadir, mBtanActuaizar, mBtnEsborrar;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;

    private ListView mLVCarta;
    private List<Pizza> mListPizzas = new ArrayList<>();
    private ArrayAdapter<Pizza> mAdapterPizza;

    private Pizza mPizzaSeleccionada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InicializarComponents();
        InicializarListeners();
        LlistarPizza();
    }

    private void LlistarPizza() {
        mReference.child("Pizzes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mListPizzas.clear();
                //Rellenar la lista de la array Pizza con el metodo snapshot de FIREBASE
                for(DataSnapshot datosSnap: snapshot.getChildren()){
                    Pizza pizza = datosSnap.getValue(Pizza.class);
                    mListPizzas.add(pizza);
                }

                //Pasamos la lista al componente listView sw la pantalla
                //android.R.layout.simple_list_item_1 no funciona con el intelisens
                mAdapterPizza = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, mListPizzas);
                //Es el list View
                mLVCarta.setAdapter(mAdapterPizza);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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

        //ARNAU NO ME VA
        mLVCarta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mPizzaSeleccionada = (Pizza) adapterView.getItemAtPosition(i);

                mETNom.setText(mPizzaSeleccionada.getmNombre());
                mETIngreddientes.setText(mPizzaSeleccionada.getmIngredients());
                mETPreu.setText(mPizzaSeleccionada.getmPrecio());
            }
        });
    }

    private void EsborrarPizza() {
        mReference.child("Pizza").child(mPizzaSeleccionada.getmUid()).removeValue();
    }

    private void ActualizarPizza() {
        String nom = mPizzaSeleccionada.getmNombre();
        String ingredientes = mPizzaSeleccionada.getmIngredients();
        String Precio = mPizzaSeleccionada.getmPrecio();
        Pizza pizza = new Pizza(nom, ingredientes, Precio, mPizzaSeleccionada.getmUid());

        //Otro metodo
        //String uid = mReference.push().getKey();
        //Pizza pizza = new Pizza(nom, ingredientes, Precio, uid);
        //mReference.child("Pizzas").child(uid).setValue(pizza);

        mReference.child("Pizzas").child(mPizzaSeleccionada.getmUid()).setValue(pizza);

        ResetCamps();
    }

    private void AñadirPizza() {

        String nom = mETNom.getText().toString();
        String ingredients= mETIngreddientes.getText().toString();
        String precio = mETPreu.getText().toString();
        String uid = UUID.randomUUID().toString();

        Pizza pizza = new Pizza( nom,  ingredients,  precio,  uid);

        mReference.child("Pizza").child(uid).setValue(pizza);

        ResetCamps();
    }

    private void ResetCamps() {
        mETNom.setText("");
        mETIngreddientes.setText("");
        mETPreu.setText("");
    }

    private void InicializarComponents(){
        mETNom = findViewById(R.id.ET_Nom);
        mETIngreddientes = findViewById(R.id.ET_Ingrediente);
        mETPreu = findViewById(R.id.ET_Precio);
        mBtanActuaizar = findViewById(R.id.BT_Actuaizar);
        mBtnAñadir = findViewById(R.id.BT_Añadir);
        mBtnEsborrar = findViewById(R.id.BT_Esborrar);

        mLVCarta = findViewById(R.id.LV_Carta);
        mDatabase = FirebaseDatabase.getInstance("https://fir-mvvm-2023-default-rtdb.europe-west1.firebasedatabase.app");
        mReference = mDatabase.getReference();



    }
}