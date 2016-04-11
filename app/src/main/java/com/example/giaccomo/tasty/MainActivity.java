package com.example.giaccomo.tasty;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity implements Response.Listener<JSONArray>, Response.ErrorListener {

    private static final String URL = "https://10.0.2.2:5000/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue request = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, this, this);
        jsonArrayRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        request.add(jsonArrayRequest);

        //sendRequest();

    }

    private void sendRequest(){

        StringRequest stringRequest = new StringRequest(URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.print(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.print("errorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void irFavoritos(View v){
        Intent intent = new Intent(this, Pantalla_Favoritos.class);
        startActivity(intent);
    }

    public void irCategorias(View v){
        Intent intent = new Intent(this, Pantalla_Categoria.class);
        startActivity(intent);
    }
    public void irInicio(View v){

    }

    public void irVideo(View v){
        Intent intent = new Intent(this, Pantalla_video.class);
        startActivity(intent);
    }

    public void irBusqueda(View v){
        Intent intent = new Intent(this, Pantalla_Busqueda.class);
        startActivity(intent);
    }

    @Override
    public void onResponse(JSONArray response) {
        try{
            Log.d("onResponse()", response.toString(0));
            response.toString(0);
            //System.out.print("AKIIIIIIIIIIIIII" + response.toString(0));
            //Toast.makeText(this, response.toString(), Toast.LENGTH_LONG).show();
        }catch(JSONException e){
            System.out.print(response);
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("onErrorResponse()", error.toString());
    }
}
