package com.signme.signme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.signme.signme.activity.HomeActivity;
import com.signme.signme.tutoreal.TutorMainActivity;

public class RegisterfinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerfin);
    }

    public void Registerfin(View view){
        Intent registerfin = new Intent(RegisterfinActivity.this, TutorMainActivity.class);
        startActivity(registerfin);
    }
}