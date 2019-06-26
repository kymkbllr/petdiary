package com.example.petdiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button sahiplen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sahiplendirme_sayfasi);

        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Sahiplendirme Sayfası");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mavi));
        setSupportActionBar(toolbar);
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
