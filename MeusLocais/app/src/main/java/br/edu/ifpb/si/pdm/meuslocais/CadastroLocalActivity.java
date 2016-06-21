package br.edu.ifpb.si.pdm.meuslocais;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CadastroLocalActivity extends AppCompatActivity {
    private static final int FOTO = 1;

    private Button btCadastrar;
    private EditText etNome;
    private  Button btnFoto;
    private ImageView imgFoto;
    private Bitmap foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);

        this.btCadastrar = (Button) findViewById(R.id.btCadastrar);
        this.etNome = (EditText) findViewById(R.id.etNome);
        this.btnFoto = (Button) findViewById(R.id.btnFoto);
        this.imgFoto = (ImageView) findViewById(R.id.imgFoto);

        btCadastrar.setOnClickListener( new OnClick() );
        btnFoto.setOnClickListener(new OnClick());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            if (requestCode == FOTO){
                this.foto = data.getParcelableExtra("data");
                this.imgFoto.setImageBitmap(this.foto);
            }
        }
    }

    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.equals(CadastroLocalActivity.this.btCadastrar)) {

                String nome = CadastroLocalActivity.this.etNome.getText().toString();
               // Local local = new Local(nome);

                Bitmap foto = CadastroLocalActivity.this.foto;
                //local.setFoto(foto);

                Intent it = new Intent();
                //it.putExtra("LOCAL", local);
                it.putExtra("NOME", nome);
                it.putExtra("FOTO", foto);
                setResult(RESULT_OK, it);
                finish();
            }
            else if (v.equals(CadastroLocalActivity.this.btnFoto)) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it, CadastroLocalActivity.this.FOTO);
            }



        }
    }
}
