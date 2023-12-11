package com.example.freq_max;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNome;
    private EditText editTextIdade;
    private Button btAdicionarCalcular;
    private ListView ListaResultados;

    private List<Atleta> listaAtletas;
    private ArrayAdapter<Atleta> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);

        Log.d("MainActivity", "onCreate: Inicializando a lista de atletas");

        editTextNome = findViewById(R.id.editTextNome);
        editTextIdade = findViewById(R.id.editTextIdade);
        btAdicionarCalcular = findViewById(R.id.btAdicionarCalcular);
        ListaResultados = findViewById(R.id.ListaResultados);

        listaAtletas = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaAtletas);
        ListaResultados.setAdapter(adapter);

        btAdicionarCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarECalcular();
            }
        });
    }

    private void adicionarECalcular() {
        String nome = editTextNome.getText().toString().trim();
        String idadeString = editTextIdade.getText().toString().trim();

        if (nome.isEmpty() || idadeString.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        int idade = Integer.parseInt(idadeString);
        int fcm = calcularFCM(idade);

        Atleta novoAtleta = new Atleta(nome, idade, fcm);
        listaAtletas.add(novoAtleta);

        // Ordena a lista
        Collections.sort(listaAtletas, new Comparator<Atleta>() {
            @Override
            public int compare(Atleta a1, Atleta a2) {
                return Integer.compare(a2.getFcm(), a1.getFcm());
            }
        });

        adapter.notifyDataSetChanged();

        // Limpa os campos de entrada
        editTextNome.getText().clear();
        editTextIdade.getText().clear();
    }

    private int calcularFCM(int idade) {
        return 220 - idade;
    }
}
