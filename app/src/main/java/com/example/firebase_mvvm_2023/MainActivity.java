package com.example.firebase_mvvm_2023;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mETNom, mETIngreddientes,mETPreu;
    private Button BtnAfeelir, mBtanActuaizar, mBtnEsborrar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InicializarComponents();
    }
    private void aianivslizarComponentes(){
        mETNom = findViewById(R.id.ET_Nom);
        mETIngreddientes = findViewById(R.id.ET_Ingrediente);
        mETPreu = findViewById(R.id.ET_Precio);
    }
}