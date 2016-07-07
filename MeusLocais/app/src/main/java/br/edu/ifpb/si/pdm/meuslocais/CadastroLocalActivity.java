package br.edu.ifpb.si.pdm.meuslocais;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CadastroLocalActivity extends AppCompatActivity {
    private static final int FOTO = 1;

    private Button btCadastrar;
    private EditText etNome;
    private Button btnFoto;
    private Bitmap foto;
    private ImageView imgFoto;

    private Button btnLocalizacao;
    private TextView tvLocalizacao;
    private LocationManager manager;
    private String longitude;
    private String latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_local);

        this.btCadastrar = (Button) findViewById(R.id.btCadastrar);
        this.etNome = (EditText) findViewById(R.id.etNome);
        this.btnFoto = (Button) findViewById(R.id.btnFoto);
        this.btnLocalizacao = (Button) findViewById(R.id.btnLocalizacao);
        this.imgFoto = (ImageView) findViewById(R.id.imgFoto);
        this.tvLocalizacao = (TextView) findViewById(R.id.tvLocalizacao);

        btCadastrar.setOnClickListener( new OnClick() );
        btnFoto.setOnClickListener( new OnClick());
        btnLocalizacao.setOnClickListener( new OnClick() );
        this.manager = (LocationManager) getSystemService(LOCATION_SERVICE);
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
                Bitmap foto = CadastroLocalActivity.this.foto;
                String longitude = CadastroLocalActivity.this.longitude;
                String latitude = CadastroLocalActivity.this.latitude;

                Intent it = new Intent();
                it.putExtra("NOME", nome);
                it.putExtra("FOTO", foto);
                it.putExtra("LONGITUDE",longitude);
                it.putExtra("LATITUDE",latitude);

                setResult(RESULT_OK, it);
                finish();
            }
            else if (v.equals(CadastroLocalActivity.this.btnFoto)) {
                Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(it, CadastroLocalActivity.this.FOTO);
            }
            else  if (v.equals(CadastroLocalActivity.this.btnLocalizacao)){
                Log.i("GPS", "Chamar GPS");
                if (ContextCompat.checkSelfPermission(CadastroLocalActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(CadastroLocalActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for Activity#requestPermissions for more details.
                    return;
                }
                CadastroLocalActivity.this.manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new GPSListener());
                Log.i("GPS", "Localização solicitada");
            }
        }
    }

    private class GPSListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Log.i("GPS", "Localização recebida");
            CadastroLocalActivity.this.tvLocalizacao.setText( String.format("%f %f", location.getLatitude(), location.getLongitude()) );
            CadastroLocalActivity.this.latitude = String.format( "%f", location.getLatitude() );
            CadastroLocalActivity.this.longitude = String.format( "%f", location.getLongitude() );
            if (ContextCompat.checkSelfPermission(CadastroLocalActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(CadastroLocalActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
            CadastroLocalActivity.this.manager.removeUpdates(this);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
