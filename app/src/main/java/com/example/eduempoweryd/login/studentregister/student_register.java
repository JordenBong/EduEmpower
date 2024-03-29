package com.example.eduempoweryd.login.studentregister;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.databinding.DataBindingUtil;

import com.example.eduempoweryd.R;
import com.example.eduempoweryd.databinding.LrStudentRegisterBinding;
import com.example.eduempoweryd.login.instructorregister.instructor_register;
import com.example.eduempoweryd.login.loginpage.login_page;
import com.example.eduempoweryd.login.registerpage.register_page;
import com.example.eduempoweryd.login.studentregister.StudentRegisterVM;
import com.example.eduempoweryd.login.studentsurvey.student_survey;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.HashMap;

public class student_register extends AppCompatActivity {

    private EditText SelectDate;
    private EditText username;
    private EditText email;
    private EditText phone;
    private EditText password;
    private EditText password2;
    private EditText dob;
    private Spinner gender;
    private Spinner education;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lr_student_register);
//        super.onCreate(savedInstanceState);
//        StudentRegisterBinding binding = DataBindingUtil.setContentView(this, R.layout.student_register);
//        StudentRegisterVM viewModel = new StudentRegisterVM();
//        binding.setStudentRegisterVM(viewModel);
//        binding.setLifecycleOwner(this);
        // Set your layout file using DataBindingUtil or setContentView

        SelectDate = findViewById(R.id.SelectDate);
        username = findViewById(R.id.usernameinput);
        email = findViewById(R.id.emailinput);
        phone = findViewById(R.id.phoneinput);
        password = findViewById(R.id.passinput);
        password2 = findViewById(R.id.pass2input);
        dob = findViewById(R.id.SelectDate);
        gender = findViewById(R.id.spinnergender);
        education = findViewById(R.id.spinneredu);


        SelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        student_register.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our edit text.
                                SelectDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });
        Spinner spinner = (Spinner) findViewById(R.id.spinnergender);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.gender,
                R.layout.lr_spinner_register
        );
// Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(R.layout.lr_spinner_register);
// Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Change the text color for the first item
                if (position == 0) {
                    ((TextView) parentView.getChildAt(0)).setTextColor(Color.GRAY);
                } else {
                    ((TextView) parentView.getChildAt(0)).setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        Spinner spinner2 = (Spinner) findViewById(R.id.spinneredu);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
                this,
                R.array.educationalstatus,
                R.layout.lr_spinner_register
        );
// Specify the layout to use when the list of choices appears.
        adapter2.setDropDownViewResource(R.layout.lr_spinner_register);
// Apply the adapter to the spinner.
        spinner2.setAdapter(adapter2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Change the text color for the first item
                if (position == 0) {
                    ((TextView) parentView.getChildAt(0)).setTextColor(Color.GRAY);
                } else {
                    ((TextView) parentView.getChildAt(0)).setTextColor(Color.BLACK);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        AppCompatButton b1 = findViewById(R.id.btnSignUp);
        b1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        CharSequence text = "Your account is registered successfully!";
//                        int duration = Toast.LENGTH_SHORT;
//                        Toast toast = Toast.makeText(student_register.this, text, duration);
//                        toast.show();
//                        Intent i = new Intent(student_register.this, student_survey.class);
//                        startActivity(i);
                        String txt_email = email.getText().toString();
                        String txt_password = password.getText().toString();
                        String txt_password2 = password2.getText().toString();
                        String txt_username = username.getText().toString();
                        String txt_phone = phone.getText().toString();
                        String txt_dob = dob.getText().toString();
                        String txt_gender = gender.getSelectedItem().toString();
                        String txt_education = education.getSelectedItem().toString();

                        if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)){
                            Toast.makeText(getApplicationContext(), "Empty Credentials", Toast.LENGTH_SHORT).show();
                        } else if (txt_password.length() < 6){
                            Toast.makeText(getApplicationContext(), "Password too short", Toast.LENGTH_SHORT).show();
                        } else if(!txt_password.equals(txt_password2)){
                            Toast.makeText(getApplicationContext(), "Password not match", Toast.LENGTH_SHORT).show();
                        } else {
                            registerUser(txt_username, txt_email, txt_phone, txt_password, txt_dob, txt_gender, txt_education);
                        }
                    }
                }
        );

        TextView b2;

        b2 = findViewById(R.id.txtSignIn);
        b2.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(student_register.this, login_page.class);
                    startActivity(i);
                }
            }
        );
    }

    private void registerUser(String username, String email, String phone, String password, String dob, String gender, String education) {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Students");

        HashMap<String, Object> map = new HashMap<>();
        map.put("Username", username);
        map.put("Email", email);
        map.put("Phone", phone);
        map.put("Password", password);
        map.put("DateOfBirth", dob);
        map.put("Gender", gender);
        map.put("Education", education);

        // Use push to generate a unique key in the Students node
        DatabaseReference newStudentRef = db.push();
        String uniqueKey = newStudentRef.getKey(); // Get the unique key

        // Use setValue on the specific reference
        newStudentRef.setValue(map)
                .addOnSuccessListener(aVoid -> {
                    Log.d("StQuizInsideQuizFrag", "Attempt stored successfully");
                    Toast.makeText(getApplicationContext(), "Register successfully", Toast.LENGTH_SHORT).show();

                    SharedPreferences pref = getSharedPreferences("system", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("uid", uniqueKey);
                    editor.putString("role", "student");
                    editor.apply();

                    Log.d("StQuizInsideQuizFrag", "dbKey: " + db.getKey());

                    startActivity(new Intent(student_register.this, student_survey.class));
                })
                .addOnFailureListener(e -> {
                    // Show error message
                    Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                });
    }

}
