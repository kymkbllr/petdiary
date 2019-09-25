package com.example.petdiary.ui.login;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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



public class GirisFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String TAG ="GirisKayitOlActivity";

    private String mParam1;
    private String mParam2;

    private OnGirisFragmentListener mListener;

    public GirisFragment() {
        // Required empty public constructor
    }

    public static GirisFragment newInstance(String param1, String param2) {
        GirisFragment fragment = new GirisFragment();
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
        View view = inflater.inflate(R.layout.fragment_giris, container, false);

        final EditText usernameEditText = view.findViewById(R.id.giris_yap_mail_et);
        final EditText passwordEditText = view.findViewById(R.id.giris_yap_sifre_et);
        final Button loginButton = view.findViewById(R.id.giris_yap_butonu_btn);
        final TextView girisKayitOl = view.findViewById(R.id.giris_kayit_ol_tv);
        final ProgressBar loadingProgressBar = view.findViewById(R.id.loading);
        girisKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                KayitOlFragment kayitOlFragment = new KayitOlFragment();
                changeFragment(kayitOlFragment);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseGiris();
            }
        });
        return view;
    }

    private void changeFragment(KayitOlFragment kayitOlFragment) {


            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.giris_yap_frame_layout_fl, kayitOlFragment);
            fragmentTransaction.commit();
        }


    private void firebaseGiris() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                String username = (String) document.getData().get("user_name");
                                Log.d(TAG, ""+username);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentKayıtOl();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnGirisFragmentListener) {
            mListener = (OnGirisFragmentListener) context;
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

    public interface OnGirisFragmentListener {
        // TODO: Update argument type and name
        void onFragmentKayıtOl();
    }
}
