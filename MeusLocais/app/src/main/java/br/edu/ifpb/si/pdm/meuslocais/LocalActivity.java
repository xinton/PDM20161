package br.edu.ifpb.si.pdm.meuslocais;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LocalActivity extends AppCompatActivity {

    private TextView tvNome;
    private ImageView imgLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        this.tvNome = (TextView) findViewById(R.id.tvNome);
        this.imgLocal = (ImageView) findViewById(R.id.imgLocal);

        Intent intent = getIntent();

        String nome = intent.getStringExtra("LOCAL");
        Bitmap foto = intent.getParcelableExtra("FOTO");
        this.tvNome.setText(nome);
        this.imgLocal.setImageBitmap(foto);
        //Toast.makeText(this, nome, Toast.LENGTH_SHORT).show();
    }
}
