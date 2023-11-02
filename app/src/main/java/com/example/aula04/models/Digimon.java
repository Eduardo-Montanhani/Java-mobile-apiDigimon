package com.example.aula04.models;

import com.example.aula04.Config;

public class Digimon {

    public String nome;
    public String url;

    public String imagem;

    public int id(){
        if (url != null){
            String codigo = url.replace(Config.linkService, "") .replace("/", "");
            return Integer.parseInt(codigo);
        }
        return 0;
    }


}

