package com.example.aula04.datasource;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

//bitmap salvar o formato da imagem
public class DownloadImagem extends AsyncTask<String, Void, Bitmap> {
    ImageView imagem;

    public DownloadImagem(ImageView imagem){
        this.imagem = imagem;
    }
    @Override
    protected Bitmap doInBackground(String... strings) {
        String urlImagem = strings[0];
        Bitmap bitmap = null;

        try {
            //criando a url e buscando os dados da imagem e salvando no stream
            InputStream stream = new URL(urlImagem).openStream();
            //transformando os dados da memoria stream em uma imagem
            bitmap = BitmapFactory.decodeStream(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        //add no objeto da imagem o bitmap baixado do link
        imagem.setImageBitmap(bitmap);
    }
}
