package com.example.petdiary.ui.login;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.petdiary.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class GirisKayitOlActivity extends AppCompatActivity implements KayitOlFragment.OnFragmentListener , GirisFragment.OnGirisFragmentListener {
    public GirisFragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giris);
        fragment = new GirisFragment();
        changeFragment(fragment);

    }

    public void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.giris_yap_frame_layout_fl, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentCompleted(Uri uri) {

    }

    @Override
    public void onFragmentKayıtOl() {

    }
}
