package br.edu.ifpb.si.pdm.meuslocais;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class LocalActivity extends AppCompatActivity {

    private TextView tvNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        this.tvNome = (TextView) findViewById(R.id.tvNome);

        Intent intent = getIntent();

        String nome = intent.getStringExtra("LOCAL");
        this.tvNome.setText(nome);
        Toast.makeText(this, nome, Toast.LENGTH_SHORT).show();
    }
}
