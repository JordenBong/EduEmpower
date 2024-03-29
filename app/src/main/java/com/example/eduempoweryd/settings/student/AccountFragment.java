package com.example.eduempoweryd.settings.student;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.eduempoweryd.R;
import com.example.eduempoweryd.course.CustomerSupportActivity;
import com.example.eduempoweryd.course.MainActivity;
import com.example.eduempoweryd.login.loginpage.login_page;
import com.example.eduempoweryd.settings.instructor.UserSettingsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountFragment extends Fragment {

    TextView userName;
    TextView userEmail;
    Button btn_logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.settings_st_fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){

        //Button CS
        ImageButton cs_button = view.findViewById(R.id.btn_acc_support);
        userEmail = view.findViewById(R.id.userEmail);
        userName = view.findViewById(R.id.userName);
        btn_logout = view.findViewById(R.id.btn_logout);

        cs_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).enterCustomerSupport(view);
            }
        });

        // Show profile username and email
        SharedPreferences preferences = getActivity().getSharedPreferences("system", MODE_PRIVATE);
        String uid = preferences.getString("uid", "null");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Students");
        reference.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot snapshot = task.getResult();
                    String username = String.valueOf(snapshot.child("Username").getValue());
                    String email = String.valueOf(snapshot.child("Email").getValue());
                    userName.setText(username);
                    userEmail.setText(email);
                }
            }
        });

        // Button Change Username
        ImageButton btnChangeName = view.findViewById(R.id.btn_acc_username);

        btnChangeName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(getActivity(), St_UpdateUsernameActivity.class);
                i.putExtra("Username", userName.getText().toString());
                i.putExtra("Email", userEmail.getText().toString());
                startActivity(i);
            }
        });

        ImageButton btnChangeEmail = view.findViewById(R.id.btn_acc_email);
        btnChangeEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), St_UpdateEmailActivity.class);
                i.putExtra("Username", userName.getText().toString());
                i.putExtra("Email", userEmail.getText().toString());
                startActivity(i);
            }
        });

        ImageButton btnChangeDOB = view.findViewById(R.id.btn_acc_dob);
        btnChangeDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), St_UpdateDOBActivity.class);
                i.putExtra("Username", userName.getText().toString());
                i.putExtra("Email", userEmail.getText().toString());
                startActivity(i);
            }
        });

        ImageButton btnChangePassword = view.findViewById(R.id.btn_acc_change_password);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), St_UpdatePasswordActivity.class);
                i.putExtra("Username", userName.getText().toString());
                i.putExtra("Email", userEmail.getText().toString());
                startActivity(i);
            }
        });

        ImageButton btnChangePhoneNumber = view.findViewById(R.id.btn_acc_phone);
        btnChangePhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), St_UpdatePhoneActivity.class);
                i.putExtra("Username", userName.getText().toString());
                i.putExtra("Email", userEmail.getText().toString());
                startActivity(i);
            }
        });

        ImageButton btnChangeGender = view.findViewById(R.id.btn_acc_gender);
        btnChangeGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), St_UpdateGenderActivity.class);
                i.putExtra("Username", userName.getText().toString());
                i.putExtra("Email", userEmail.getText().toString());
                startActivity(i);
            }
        });

        cs_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), CustomerSupportActivity.class);
                startActivity(i);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), login_page.class));
                getActivity().finish();
            }
        });
    }
}