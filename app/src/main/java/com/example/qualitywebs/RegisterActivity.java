package com.example.qualitywebs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
    EditText fullName,registerEmail,passwd,confirmPasswd,phoneNumber;
    TextView loginHere;
    AppCompatButton registerButton;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        phoneNumber = findViewById(R.id.phone_number);
        fullName =findViewById(R.id.register_name);
        registerEmail =findViewById(R.id.register_email);
        passwd =findViewById(R.id.register_password);
        confirmPasswd =findViewById(R.id.comfirm_password);
        loginHere =findViewById(R.id.login_text);
        registerButton =findViewById(R.id.register_button);

        loginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emailTxt= registerEmail.getText().toString();
                final String phoneTxt= phoneNumber.getText().toString();
                final String nametxt= fullName.getText().toString();
                final String passwdtxt= passwd.getText().toString();
                final String confirmPasstxt= confirmPasswd.getText().toString();

                if (emailTxt.isEmpty()|| nametxt.isEmpty()|| passwdtxt.isEmpty()|| confirmPasstxt.isEmpty()||phoneTxt.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else if (phoneTxt.length()!=10){
                    Toast.makeText(RegisterActivity.this, "Enter 10 Digits Number", Toast.LENGTH_SHORT).show();
                }
                else if (!passwdtxt.equals(confirmPasstxt)){
                    Toast.makeText(RegisterActivity.this, "Passwords Are Not Matching..", Toast.LENGTH_SHORT).show();
                }
                else {
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(phoneTxt)){
                                Toast.makeText(RegisterActivity.this, "Phone No. is Already register", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                databaseReference.child("users").child(phoneTxt).child("full name").setValue(nametxt);
                                databaseReference.child("users").child(phoneTxt).child("email").setValue(emailTxt);
                                databaseReference.child("users").child(phoneTxt).child("password").setValue(passwdtxt);
                                Toast.makeText(RegisterActivity.this, "user Register Succesfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }

            }
        });


    }
}