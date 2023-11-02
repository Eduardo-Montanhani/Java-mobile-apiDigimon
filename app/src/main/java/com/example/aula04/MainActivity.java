package com.example.aula04;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.aula04.datasource.BuscarDadosApi;
import com.example.aula04.models.Digimon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//agr pertence a uma tela de lista
public class MainActivity extends ListActivity {

    private ArrayList<Digimon> listaDados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            listaDados = new BuscarDadosApi().execute(Config.link).get();

            //criando um mapeamento para listar
            ListAdapter adapter = new SimpleAdapter(this,  //this é a classe que tem o objetivo listView
                    dadostoMap(listaDados),//lista com os dados em formato HashMap(mapa de dados)
                    R.layout.listview_modelo,//Layout de modelo para cada item da lista
                    new String[] { "nome" },//campo dos dados que sera carregado na lista
                    new int[] {R.id.txtNome}//em que item da lista carrega os dados
            );
            // ve a classe que tem o list
            setListAdapter(adapter);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        //Pokemon pokemon = listaDados.get(0);
       // Toast.makeText(this, pokemon.nome, Toast.LENGTH_SHORT).show();

    }

    private List<HashMap<String,String>> dadostoMap(ArrayList<Digimon> listaDados) {
        List<HashMap<String,String>> lista = new ArrayList<>();

        for (int i = 0; i < listaDados.size(); i++){
            HashMap<String,String> item = new HashMap<>();
            item.put("id", String.valueOf(listaDados.get(i).id()));
            item.put("nome", listaDados.get(i).nome);
            item.put("imagem", listaDados.get(i).imagem);

            lista.add(item);
        }

        return lista;
    }
    protected void  onListItemClick(ListView l, View v, int position,long id){
        super.onListItemClick(l,v, position, id);
        //position -> tem o indice do item clicado na listView

        Digimon digimon = listaDados.get(position);

        Intent tela = new Intent(MainActivity.this, DetalhesActivity.class);

        //criando objeto Bundle para enviar os dados para o detlahes
        Bundle parametros = new Bundle();
        parametros.putString("nome", digimon.nome);
        parametros.putString("imagem", digimon.imagem);

        //add os parametros no caminho da tela
        tela.putExtras(parametros);

        //abrindo a tela de detalhes
        startActivity(tela);
    }
}