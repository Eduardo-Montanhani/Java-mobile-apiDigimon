package com.example.aula04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aula04.R;
import com.example.aula04.datasource.DownloadImagem;

public class DetalhesActivity extends AppCompatActivity {

    TextView txtNome;
    ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        //carregando os campos da tela(xml) com os objetos do java
        txtNome = findViewById(R.id.txtNome);
        imagem = findViewById(R.id.imagem);

        //getIntent -> captura o caminho que foi vitilizado para abrir a tela
        Intent caminhoRecebido = getIntent();

        if (caminhoRecebido != null) {
            //captura os parametros recebidos no caminho de tela
            Bundle parametros = caminhoRecebido.getExtras();

            if (parametros != null){
                txtNome.setText(parametros.getString("nome"));
                new DownloadImagem(imagem).execute(parametros.getString("imagem"));
            }
        }

    }
}