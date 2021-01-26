package com.example.searchgitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtSearch;
    Button btnSearch;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch = (EditText)findViewById(R.id.edtSearch);
        btnSearch = (Button)findViewById(R.id.btnSearch);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String searchPhrase = edtSearch.getText().toString();

               if (edtSearch.getText().toString().isEmpty()){
                   edtSearch.setError(getResources().getString(R.string.error));
               }else {

                   Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                   intent.putExtra("searchPhrase", searchPhrase);
                   startActivity(intent);
               }
            }
        });


    }

    @Override
    public void onBackPressed() {


        finishAffinity();

    }
}