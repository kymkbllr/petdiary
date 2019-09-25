package com.example.petdiary;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button sahiplen;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // toolbar = findViewById(R.id.toolbar);
//
       // toolbar.setTitle("Sahiplendirme Sayfası");
       // toolbar.setTitleTextColor(getResources().getColor(R.color.mavi));
       // setSupportActionBar(toolbar);



//
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            case R.id.profil:
                // profil butonuna tıklanıldığında ne yapılacak
                Toast.makeText(this, "Profil butonuna tıklanıldı", Toast.LENGTH_SHORT).show();
                break;

            case R.id.ekleme:
                // ekleme butonuna tıklanıldığında ne yapılacak
                Toast.makeText(this, "ekleme butonuna tıklanıldı", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
