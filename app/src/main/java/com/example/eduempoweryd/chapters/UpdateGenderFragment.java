package com.example.eduempoweryd.chapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.eduempoweryd.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.widget.Button;
import android.widget.TextView;

public class UpdateGenderFragment extends Fragment {

    TextView userName;
    TextView userEmail;
    EditText gender;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.chapter_fragment_update_gender, container, false);
        userName = view.findViewById(R.id.userName9);
        userEmail = view.findViewById(R.id.userEmail9);
        gender = view.findViewById(R.id.EVEditGender);
        //readData(txt_username, gender);

        // Show profile username and email
        // Later need to change hardcoded UID to FirebaseAuth.getInstance().getUID();
        String uid = "KW9dQocc6mUJtUkssl73sO07oDr2";
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Students");
        reference.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    String username = String.valueOf(snapshot.child("Username").getValue());
                    String email = String.valueOf(snapshot.child("Email").getValue());
                    String txt_gender = String.valueOf(snapshot.child("Gender").getValue());
                    userName.setText(username);
                    userEmail.setText(email);
                    gender.setText(txt_gender);

                }
            }
        });

        Button btn = view.findViewById(R.id.btnConfirmGender);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToAccountFragment();
            }
        });

        return view;
    }

    private void readData(String txtUsername, EditText dob) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Students");
        reference.child(txtUsername).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    DataSnapshot snapshot = task.getResult();
                    String txt_dob = String.valueOf(snapshot.child("Gender").getValue());
                    dob.setText(txt_dob);
                }
            }
        });
    }

    public void navigateToAccountFragment(){
        Fragment fragment = new AccountFragment();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        BottomNavigationView btm = getActivity().findViewById(R.id.bottomNavigationView);
        btm.setVisibility(View.VISIBLE);
        ft.replace(R.id.frameLayout, fragment).commit();
    }
}