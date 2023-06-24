package com.example.taller2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.taller2.WebService.Asynchtask;
import com.example.taller2.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://dummyjson.com/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtMostrar = (TextView)findViewById(R.id.txtMostrar);

        String lista ="";
        JSONObject objecto = new JSONObject(result);
        JSONArray JSONlista = objecto.getJSONArray("users");
        for(int i=0; i< JSONlista.length();i++)
        {
            JSONObject JSONObjecto = JSONlista.getJSONObject(i);
            lista = lista + "\n" + JSONObjecto.getString("firstName").toString()
                    + ", " + JSONObjecto.getString("age").toString()
                    + ", " + JSONObjecto.getString("email").toString();
        }
        txtMostrar.setText(lista);
    }
}