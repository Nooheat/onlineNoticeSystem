package com.signme.signme.Content;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.signme.signme.R;
import com.signme.signme.server.APIinterface;
import com.signme.signme.server.ApplicationController;
import com.signme.signme.server.contentlistRepo;

import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dsm2016 on 2017-09-25.
 */

public class ContentMainSimpleActivity extends AppCompatActivity {
    private TextView title_text, date_text, content_text, name_text;
    private APIinterface apIinterface;
    private Retrofit retrofit;
    int letterNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_content_main_simple);
        retrofit = new Retrofit.Builder().baseUrl(APIinterface.URL).addConverterFactory(GsonConverterFactory.create()).build();
        apIinterface = retrofit.create(APIinterface.class);

        Intent intent = getIntent();
        letterNumber = intent.getExtras().getInt("letterNumber");
        Log.d("ASDASDASDASD",letterNumber+"");
        Call<JsonObject> call = apIinterface.getResponselessLetter("/letter/responseless/"+letterNumber,getSharedPreferences("test", MODE_PRIVATE).getString("signme-x-access-token", null));
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
              //  title_text.setText();
                Log.d("STATUS CODE : ",response.code()+"");
                JsonObject letter = response.body();
                Log.d("ADASDASDASDA", letter.toString());
                String title = letter.get("title").toString().replace("\"","");
                String date=letter.get("openDate").toString().replace("\"","");
                String contents = letter.get("contents").toString().replace("\"","");
                String writerName = letter.get("writerName").toString().replace("\"","");
                Log.d("TITLE", title);
                Log.d("DATE", date);
                Log.d("TITLE", contents);
                Log.d("TITLE", writerName);
                title_text = (TextView) findViewById(R.id.responseless_title);
                name_text = (TextView) findViewById(R.id.responseless_writerName);
                date_text = (TextView) findViewById(R.id.responseless_openDate);
                content_text = (TextView) findViewById(R.id.responseless_contents);

                title_text.setText(title);
                content_text.setText(contents);
                date_text.setText(date);
                name_text.setText(writerName);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });
    }

}
