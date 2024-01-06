package com.example.eduempoweryd.chapters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.eduempoweryd.R;
import com.example.eduempoweryd.databinding.ChapterActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ChapterActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ChapterActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.home){
                replaceFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.courses){
                replaceFragment(new CoursesFragment());
            } else if (item.getItemId() == R.id.account){
                replaceFragment(new AccountFragment());
            }
            return true;
        });

        SharedPreferences sharedPreferences = getSharedPreferences("system", MODE_PRIVATE);
        Log.i("TAG", "Get uid: " + sharedPreferences.getString("uid", "none"));
    }

    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    public void enterCustomerSupport(View view){
        Intent i = new Intent(getApplicationContext(), CustomerSupportActivity.class);
        startActivity(i);
    }
    

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }
}