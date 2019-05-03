package com.damar.quiztumbuhan;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class TampilTumbuhan extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextId;
    private EditText editTextNama;
    private EditText editTextJenis;
    private EditText editTextWarna;

    private Button buttonUpdate;
    private Button buttonDelete;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_tumbuhan);

        Intent intent = getIntent();

        id = intent.getStringExtra(Kofigurasi.EMP_ID);

        editTextId = (EditText) findViewById(R.id.editTextId);
        editTextNama = (EditText) findViewById(R.id.editTextNama);
        editTextJenis = (EditText) findViewById(R.id.editTextJenis);
        editTextWarna = (EditText) findViewById(R.id.editTextWarna);

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonDelete = (Button) findViewById(R.id.buttonDelete);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);

        editTextId.setText(id);

        getTumbuhan();
    }

    private void getTumbuhan(){
        class GetTumbuhan extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilTumbuhan.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showTumbuhan(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Kofigurasi.URL_GET_EMP,id);
                return s;
            }
        }
        GetTumbuhan ge = new GetTumbuhan();
        ge.execute();
    }

    private void showTumbuhan(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Kofigurasi.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
            String nama = c.getString(Kofigurasi.TAG_NAMA);
            String jenis = c.getString(Kofigurasi.TAG_JENIS);
            String warna = c.getString(Kofigurasi.TAG_WARNA);

            editTextNama.setText(nama);
            editTextJenis.setText(jenis);
            editTextWarna.setText(warna);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
//        if(v == buttonUpdate){
//            updateEmployee();
//        }
//
//        if(v == buttonDelete){
//            confirmDeleteEmployee();
//        }
    }

//    private void updateTumbuhan(){
//        final String name = editTextName.getText().toString().trim();
//        final String desg = editTextDesg.getText().toString().trim();
//        final String salary = editTextSalary.getText().toString().trim();
//
//        class UpdateEmployee extends AsyncTask<Void,Void,String>{
//            ProgressDialog loading;
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(TampilPegawai.this,"Updating...","Wait...",false,false);
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                loading.dismiss();
//                Toast.makeText(TampilPegawai.this,s,Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            protected String doInBackground(Void... params) {
//                HashMap<String,String> hashMap = new HashMap<>();
//                hashMap.put(konfigurasi.KEY_EMP_ID,id);
//                hashMap.put(konfigurasi.KEY_EMP_NAMA,name);
//                hashMap.put(konfigurasi.KEY_EMP_POSISI,desg);
//                hashMap.put(konfigurasi.KEY_EMP_GAJIH,salary);
//
//                RequestHandler rh = new RequestHandler();
//
//                String s = rh.sendPostRequest(konfigurasi.URL_UPDATE_EMP,hashMap);
//
//                return s;
//            }
//        }
//
//        UpdateEmployee ue = new UpdateEmployee();
//        ue.execute();
//    }
//
//    private void deleteEmployee(){
//        class DeleteEmployee extends AsyncTask<Void,Void,String> {
//            ProgressDialog loading;
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(TampilPegawai.this, "Updating...", "Tunggu...", false, false);
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                loading.dismiss();
//                Toast.makeText(TampilPegawai.this, s, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            protected String doInBackground(Void... params) {
//                RequestHandler rh = new RequestHandler();
//                String s = rh.sendGetRequestParam(konfigurasi.URL_DELETE_EMP, id);
//                return s;
//            }
//        }
//
//        DeleteEmployee de = new DeleteEmployee();
//        de.execute();
//    }
}
