package com.example.qr;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView, msg, msg1, plat1, nama1, jabatan1;
    DB_parqr parkir;
    String hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        msg = findViewById(R.id.msg);
        msg1 = findViewById(R.id.msg1);
        plat1 = findViewById(R.id.noplat);
        nama1 = findViewById(R.id.nama);
        jabatan1 = findViewById(R.id.jabatan);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);

        parkir = new DB_parqr(this, "", null, 1);

    }

    public void ScanButton(View view) {

        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        //intentIntegrator.setCaptureActivity(CaptureActivityPortrait.class);
        intentIntegrator.initiateScan();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null){
            if (intentResult.getContents() == null){
                textView.setText("canceled");
            }else {
                textView.setText(intentResult.getContents());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);

        hasil = textView.getText().toString();
//        parkir.insert_qr(hasil);

        KendaraanRequest kendaraanRequest = new KendaraanRequest(hasil);
        APIInterface service = Connection.GetConnect(APIInterface.class);
        Call<KendaraanResponse> call = service.kendaraan(kendaraanRequest);
        call.enqueue(new Callback<KendaraanResponse>() {
            @Override
            public void onResponse(Call<KendaraanResponse> call, Response<KendaraanResponse> response) {
                if (response.body() != null){
                    String rCode = response.body().getrCode();
                    String message = response.body().getMessage();

                    if (rCode.equals("0")) {
                        String plat = response.body().no_plat;
                        String nama = response.body().nama_pemilik;
                        String jabatan = response.body().jabatan;

                       nama1.setText(nama);
                       jabatan1.setText(jabatan);
                       plat1.setText(plat);
                       msg.setText(message);

                       if (msg1 != null){
                           msg1.setText(null);
                       }

                        //Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                    } else {
                        msg1.setText(message);
                        msg.setText(null);
                        nama1.setText(null);
                        jabatan1.setText(null);
                        plat1.setText(null);
                        //Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<KendaraanResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Data Tidak Ditemukan", Toast.LENGTH_SHORT).show();
            }
        });

    }

//    public void HistoryButton(View view) {
//        Intent intent = new Intent(MainActivity.this, history.class);
//        startActivity(intent);
//    }
//
//    public void GenerateButton(View view) {
//        Intent intent = new Intent(MainActivity.this, generate.class);
//        startActivity(intent);
//    }

    public void CekButton(View view) {

//        KendaraanRequest kendaraanRequest = new KendaraanRequest(hasil);
//        APIInterface service = Connection.GetConnect(APIInterface.class);
//        Call<KendaraanResponse> call = service.kendaraan(kendaraanRequest);
//        call.enqueue(new Callback<KendaraanResponse>() {
//            @Override
//            public void onResponse(Call<KendaraanResponse> call, Response<KendaraanResponse> response) {
//                if (response.body() != null){
//                    String rCode = response.body().getrCode();
//                    String message = response.body().getMessage();
//
//                    if (rCode.equals("0")) {
//                        String plat = response.body().no_plat;
//                        String nama = response.body().nama_pemilik;
//                        String jabatan = response.body().jabatan;
//
//                        Intent intent = new Intent(MainActivity.this, cek.class);
//                        intent.putExtra("PLAT", Objects.requireNonNull(plat));
//                        intent.putExtra("NAMA", Objects.requireNonNull(nama));
//                        intent.putExtra("JABATAN", Objects.requireNonNull(jabatan));
//                        startActivity(intent);
//
//                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<KendaraanResponse> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Data Tidak Ditemukan", Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}
