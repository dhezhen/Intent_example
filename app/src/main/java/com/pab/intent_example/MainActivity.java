package com.pab.intent_example;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnMove = findViewById(R.id.btnMove);
        Button btnShare = findViewById(R.id.btnShare);
        Button btnWeb = findViewById(R.id.open_website_button);
        Button btnMap = findViewById(R.id.open_location_button);
        mWebsiteEditText = findViewById(R.id.website_edittext);
        mLocationEditText = findViewById(R.id.location_edittext);

        btnMove.setOnClickListener(v -> {
// Perintah Intent Explicit pindah halaman ke activity_detail

            Intent i = new Intent(MainActivity.this,DetailActivity.class);
            i.putExtra("isiData","Kiriman dari halaman pertama");
            startActivity(i);
        });

        btnShare.setOnClickListener(v -> {

          // Perintah Intent Implicit untuk share ke sosmed
           Intent intent = new Intent(Intent.ACTION_SEND);
           // Membawa data / pesan yang ingin dishare
           intent.putExtra(intent.EXTRA_TEXT,"Hallo ini adalah data dari aplikasi ini ya yang akan dikirim dengan  saya share ke sosial media ");
           intent.setType("text/plain");
           // Menjalankan perintah Intent Implicit
           startActivity(Intent.createChooser(intent,"Share to :"));
        });


        btnMap.setOnClickListener(view -> {
            // Get the string indicating a location. Input is not validated; it is
            // passed to the location handler intact.
            String loc = mLocationEditText.getText().toString();

            // Parse the location and create the intent.
            Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
            Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

            // Find an activity to handle the intent, and start that activity.
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Log.d("ImplicitIntents", "Can't handle this intent!");
            }

        });

        btnWeb.setOnClickListener(v -> {
            // Get the URL text.
            String url = mWebsiteEditText.getText().toString();

            // Pastikan EditText tidak kosong.
            if (!url.isEmpty()) {
                // Tambahkan skema http:// atau https:// jika tidak disertakan.
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "http://" + url;
                }

                // Parse the URI and create the intent.
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

                // Tentukan komponen untuk browser Chrome
                intent.setPackage("com.android.chrome");

                // Temukan activity untuk menangani intent dan mulai activity tersebut.
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    // Jika Chrome tidak terinstal, buka browser default
                    intent.setPackage(null);
                    startActivity(intent);
                }
            } else {
                // Tampilkan pesan jika URL kosong.
                Toast.makeText(this, "URL tidak boleh kosong!", Toast.LENGTH_SHORT).show();
            }
        });


    }

}