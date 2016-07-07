package br.edu.ifpb.si.pdm.meuslocais;

import android.content.Intent;
import android.graphics.Bitmap;
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
    // Options menu
    private static final int QUANTIDADE = 1, LIMPAR = 2,SOBRE = 3;

    // Para request Code do intent
    private static final int ADD_LOCAL = 1;
    private static final int LOCAL_INFO = 2;

    public MainActivity() {
        this.cadastroLocal = new CadastroLocal();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.carregarComponentes();

       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, CadastroLocalActivity.class);
                startActivityForResult(it, ADD_LOCAL);
                /*Snackbar.make(view, "Adicionar novo local em um activity", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    private void carregarComponentes(){
        this.lvLista = (ListView) findViewById(R.id.lvLista);

        ArrayAdapter<Local> adapter = new ArrayAdapter<Local>(this, android.R.layout.simple_list_item_1, this.cadastroLocal.get());
        this.lvLista.setAdapter(adapter);
        this.lvLista.setOnItemClickListener(new OnClickList());

        //TODO
        // this.lvLista.setOnItemLongClickListener(new OnLongClickList());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == ADD_LOCAL){
                Local local = new Local(data.getStringExtra("NOME"));
                local.setFoto((Bitmap) data.getParcelableExtra("FOTO"));
                local.setLatitude(data.getStringExtra("LATITUDE"));
                local.setLongitude(data.getStringExtra("LONGITUDE"));
                // Notifica o novo cadastro
                this.cadastroLocal.get().add(local);

                ((ArrayAdapter)lvLista.getAdapter()).notifyDataSetChanged();
            }
        }
    }

    // onClick chama intent pra activity local dinamico
    private class OnClickList implements AdapterView.OnItemClickListener{
        @Override
       public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(MainActivity.this, parent.getAdapter().getItem(position).toString(), Toast.LENGTH_SHORT).show();
            Local local =  (Local) parent.getAdapter().getItem(position);
            Intent it = new Intent("LOCAL_INFO");
            it.putExtra("LOCAL", local.getNome());
            it.putExtra("FOTO", local.getFoto());
            it.putExtra("LATITUDE", local.getLatitude());
            it.putExtra("LONGITUDE", local.getLongitude());
            setResult(RESULT_OK, it);
            startActivity(it);
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
        menu.add(0, QUANTIDADE, 1, "Quantidade");
        menu.add(0, LIMPAR, 2, "Limpar");
        menu.add(0, SOBRE, 3, "Sobre");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case SOBRE:
                Toast.makeText(this, "AQUI CHAMA INTENT PRA SOBRE", Toast.LENGTH_SHORT).show();
                break;
            case QUANTIDADE:
                Toast.makeText(this, Integer.toString(this.cadastroLocal.quantidade()), Toast.LENGTH_SHORT).show();
                break;
            case LIMPAR:
                this.cadastroLocal.clear();
                ((ArrayAdapter)this.lvLista.getAdapter()).notifyDataSetChanged();
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
