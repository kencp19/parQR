package com.example.qr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class cek extends AppCompatActivity {

    TextView no_plat, nama, jabatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek);

        no_plat = findViewById(R.id.no_plat);
        nama = findViewById(R.id.nama);
        jabatan = findViewById(R.id.jabatan);

        no_plat.setText(getIntent().getStringExtra("PLAT"));
        nama.setText(getIntent().getStringExtra("NAMA"));
        jabatan.setText(getIntent().getStringExtra("JABATAN"));

    }
}
