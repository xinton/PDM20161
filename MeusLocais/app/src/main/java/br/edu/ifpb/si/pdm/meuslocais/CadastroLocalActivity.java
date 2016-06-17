package br.edu.ifpb.si.pdm.meuslocais;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroLocalActivity extends AppCompatActivity {

    private Button btCadastrar;
    private EditText etNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);

        this.btCadastrar = (Button) findViewById(R.id.btCadastrar);
        this.etNome = (EditText) findViewById(R.id.etNome);
        btCadastrar.setOnClickListener( new OnClick() );
    }

    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            String nome = CadastroLocalActivity.this.etNome.getText().toString();
            Local local = new Local(nome);

            Intent it = new Intent();
            it.putExtra("LOCAL", local);
            setResult(RESULT_OK, it);
            finish();
        }
    }
}
