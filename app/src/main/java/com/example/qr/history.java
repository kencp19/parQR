package com.example.qr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class history extends AppCompatActivity {

    TextView pesan;
    DB_parqr parkir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        pesan = findViewById(R.id.pesan);
        parkir = new DB_parqr(this, "", null, 1);

        parkir.list_qr(pesan);
    }
}
