package com.damar.quiztumbuhan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextNama;
    private EditText editTextJenis;
    private EditText editTextWarna;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisialisasi dari View
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextJenis = (EditText) findViewById(R.id.editTextJenis);
        editTextWarna = (EditText) findViewById(R.id.editTextWarna);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
        buttonAdd.setOnClickListener(this);
        buttonView.setOnClickListener(this);
    }

    //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
    private void addTumbuhan(){

        final String nama = editTextNama.getText().toString().trim();
        final String jenis = editTextJenis.getText().toString().trim();
        final String warna = editTextWarna.getText().toString().trim();

        class AddTumbuhan extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Kofigurasi.KEY_EMP_NAMA, nama);
                params.put(Kofigurasi.KEY_EMP_JENIS, jenis);
                params.put(Kofigurasi.KEY_EMP_WARNA, warna);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Kofigurasi.URL_ADD, params);
                return res;
            }
        }

        AddTumbuhan at = new AddTumbuhan();
        at.execute();
    }

    @Override
    public void onClick(View v) {

        if (v == buttonView) {
            startActivity(new Intent(this, TampilSemuaTumbuhan.class));
        } else {
            if (editTextNama.getText().toString().equals("")){
                editTextNama.setError("This field is required");
            } else if(editTextJenis.getText().toString().equals("")){
                editTextJenis.setError("This field is required");
            } else if(editTextWarna.getText().toString().equals("")){
                editTextWarna.setError("This field is required");
            } else {
                if (v == buttonAdd) {
                    addTumbuhan();
                }
            }
        }


    }


}
