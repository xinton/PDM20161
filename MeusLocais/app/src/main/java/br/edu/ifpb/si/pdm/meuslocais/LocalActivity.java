package br.edu.ifpb.si.pdm.meuslocais;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LocalActivity extends AppCompatActivity {

    private TextView tvNome;
    private ImageView imgLocal;
    private TextView tvLocalizacao;
    private Button btnMapa;
    private String latitude;
    private String longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        this.tvNome = (TextView) findViewById(R.id.tvNome);
        this.imgLocal = (ImageView) findViewById(R.id.imgLocal);
        this.tvLocalizacao = (TextView) findViewById(R.id.tvLocalizacao);
        this.btnMapa = (Button) findViewById(R.id.btnMapa);
        this.btnMapa.setOnClickListener(new OnClick());

        Intent intent = getIntent();

        String nome = intent.getStringExtra("LOCAL");
        Bitmap foto = intent.getParcelableExtra("FOTO");
        this.latitude = intent.getStringExtra("LATITUDE");
        this.longitude = intent.getStringExtra("LONGITUDE");
        this.tvNome.setText(nome);
        this.imgLocal.setImageBitmap(foto);
        this.tvLocalizacao.setText(this.latitude+this.longitude);
        //Toast.makeText(this, nome, Toast.LENGTH_SHORT).show();
    }

    private class OnClick implements View.OnClickListener{
       // "http://maps.google.com/maps?saddr="+latitude+"&daddr="+longitude
        //"geo:"+latitude+","+longitude
        @Override
        public void onClick(View v) {
            String latitude = (String) LocalActivity.this.latitude;
            String longitude = (String) LocalActivity.this.longitude;

            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
            Uri.parse("http://maps.google.com/maps?"
                            + "saddr="+ latitude+","+longitude));
            startActivity(intent);
        }
    }
}
