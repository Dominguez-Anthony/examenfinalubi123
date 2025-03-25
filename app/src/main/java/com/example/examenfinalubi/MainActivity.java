package com.example.examenfinalubi;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfinalubi.adaptarores.RevistaAdaptador;
import com.example.examenfinalubi.clases.Revista;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RevistaAdaptador adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        cargaRevistas();
    }

    private void cargaRevistas() {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://revistas.uteq.edu.ec/ws/journals.php")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonResponse = response.body().string();
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<Revista>>() {}.getType();
                    List<Revista> revistas = gson.fromJson(jsonResponse, listType);

                    runOnUiThread(() -> {
                        adapter = new RevistaAdaptador(revistas);
                        recyclerView.setAdapter(adapter);
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(MainActivity.this, "Failed to load data", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
}