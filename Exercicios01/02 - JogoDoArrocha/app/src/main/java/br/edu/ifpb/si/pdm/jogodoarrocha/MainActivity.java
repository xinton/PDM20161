package br.edu.ifpb.si.pdm.jogodoarrocha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView tvMenor, tvMaior;
    private Button btApostar;
    private EditText eNumero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.instanciarComponentesInterface();
    }

    private void instanciarComponentesInterface(){
        this.btApostar = (Button) findViewById(R.id.btApostar);
        this.tvMaior = (TextView) findViewById(R.id.tvMaior);
        this.tvMenor = (TextView) findViewById(R.id.tvMenor);
        this.eNumero = (EditText) findViewById(R.id.eNumero);
    }
}
