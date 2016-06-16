package br.edu.ifpb.si.pdm.meuslocais;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvLista;
    private CadastroLocal cadastroLocal;
    private static final int SOBRE = 1;

    public MainActivity() {
        this.cadastroLocal = new CadastroLocal();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Adicionar novo local em um activity", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        this.carregarComponentes();
    }

    private void carregarComponentes(){
        this.lvLista = (ListView) findViewById(R.id.lvLista);

        ArrayAdapter<Local> adapter = new ArrayAdapter<Local>(this, android.R.layout.simple_list_item_1, this.cadastroLocal.get());
        this.lvLista.setAdapter(adapter);

        //TODO
        // this.lvLista.setOnItemClickListener(new OnClickList());
        //TODO
        // this.lvLista.setOnItemLongClickListener(new OnLongClickList());
    }

    //TODO onClick chama intent pra activity local dinamico
    private class OnClickList implements AdapterView.OnItemClickListener{
        @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(MainActivity.this, parent.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();
        }
    }
    //TODO NAO SEI
    private class OnLongClickList implements AdapterView.OnItemLongClickListener{
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
           /* MainActivity.this.cadastro.delete(position);
            ((ArrayAdapter)parent.getAdapter()).notifyDataSetChanged();*/
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
       //return true;
        menu.add(0, SOBRE, 1, "Sobre");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case SOBRE:
                Toast.makeText(this, "AQUI CHAMA INTENT PRA SOBRE", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/
    }
}
