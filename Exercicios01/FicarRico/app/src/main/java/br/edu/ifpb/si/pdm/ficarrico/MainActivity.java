package br.edu.ifpb.si.pdm.ficarrico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvNumero;
    private Button btGerar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.instanciaComponentesInterface();
    }

    private void instanciaComponentesInterface(){
        this.tvNumero = (TextView) findViewById(R.id.textView);
        this.btGerar =  (Button) findViewById(R.id.btGerar);
        this.btGerar.setOnClickListener(new OnClick());
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            MainActivity.this.tvNumero.setText(Megasena.getNumero());
        }
    }
}
