package com.damar.quiztumbuhan;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class TampilSemuaTumbuhan extends AppCompatActivity implements ListView.OnItemClickListener {

    private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_semua_tumbuhan);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        getJSON();
    }


    private void showTumbuhan(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Kofigurasi.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Kofigurasi.TAG_ID);
                String name = jo.getString(Kofigurasi.TAG_NAMA);
                String jenis = jo.getString(Kofigurasi.TAG_JENIS);
                String warna = jo.getString(Kofigurasi.TAG_WARNA);

                HashMap<String,String> tumbuhan = new HashMap<>();
                tumbuhan.put(Kofigurasi.TAG_ID,id);
                tumbuhan.put(Kofigurasi.TAG_NAMA,name);
                tumbuhan.put(Kofigurasi.TAG_JENIS,jenis);
                tumbuhan.put(Kofigurasi.TAG_WARNA,warna);
                list.add(tumbuhan);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                TampilSemuaTumbuhan.this, list, R.layout.list_item,
                new String[]{Kofigurasi.TAG_ID,Kofigurasi.TAG_NAMA, Kofigurasi.TAG_JENIS, Kofigurasi.TAG_WARNA},
                new int[]{R.id.id, R.id.name, R.id.jenis, R.id.warna});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(TampilSemuaTumbuhan.this,"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showTumbuhan();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Kofigurasi.URL_GET_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, TampilTumbuhan.class);
        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
        String empId = map.get(Kofigurasi.TAG_ID).toString();
        intent.putExtra(Kofigurasi.EMP_ID,empId);
        startActivity(intent);
    }
}
