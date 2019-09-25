package com.example.petdiary.ui.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petdiary.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class KayitOlFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String TAG = "kayitOlFragment";
    private String mParam1;
    private String mParam2;

    private EditText kullaniciAdi;
    private EditText sifre;
    private EditText ad;
    private EditText soyad;
    private EditText mail;
    private EditText adres;
    private Button kaydetButonu;

    private OnFragmentListener mListener;

    public KayitOlFragment() {
        // Required empty public constructor
    }

    public static KayitOlFragment newInstance(String param1, String param2) {
        KayitOlFragment fragment = new KayitOlFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // TODO  1- bir view oluştur inflate satırını o view e al
        View view = inflater.inflate(R.layout.fragment_kayit_ol, container, false);
        // TODO 3- üzerinde değişiklik yapacağım edittext gibi şeyleri burada çağır
        kullaniciAdi = view.findViewById(R.id.kayit_ol_kullanici_adi_et);
        sifre = view.findViewById(R.id.kayit_ol_sifre_et);
        ad = view.findViewById(R.id.kayit_ol_adi_et);
        soyad = view.findViewById(R.id.kayit_ol_soyadi_et);
        mail = view.findViewById(R.id.kayit_ol_mail_et);
        adres = view.findViewById(R.id.kayit_ol_adres_et);
        kaydetButonu = view.findViewById(R.id.kayit_ol_kaydet_btn);

        // TODO 3e kadar standart
        //TODO 4- kaydet butonunun click eventi oluştur
        kaydetButonu.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                firebaseKaydet();
            }
        });

        return view;
    }

    private void firebaseKaydet() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("user_id", UUID.randomUUID().toString());
        user.put("user_name", "" + kullaniciAdi.getText().toString());
        user.put("password","" + sifre.getText().toString());
        user.put("name", ad.getText().toString());
        user.put("surname", soyad.getText().toString());
        user.put("email", mail.getText().toString());
        user.put("adress", adres.getText().toString());
        user.put("type", 1);

        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(getContext(),"Kayıt Başarılı", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                        Toast.makeText(getContext(),"Kayıt Başarısız", Toast.LENGTH_LONG).show();
                    }
                });

    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentCompleted(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentListener) {
            mListener = (OnFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentListener {
        // TODO: Update argument type and name
        void onFragmentCompleted(Uri uri);
    }
}
