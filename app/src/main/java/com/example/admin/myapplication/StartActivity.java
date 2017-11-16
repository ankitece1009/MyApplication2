package com.example.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    private static final int REQ_CODE_ADDWORD = 1234;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void onClickPlayGame(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void onClickAddWord(View view) {
        Intent intent = new Intent(this,AddWordActivity.class);
        intent.putExtra("word","संरक्षक");
        intent.putExtra("definition","conservator");
        startActivityForResult(intent,REQ_CODE_ADDWORD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_CODE_ADDWORD)
        {
            String word = data.getStringExtra("newword");
            Toast.makeText(getApplicationContext(),word+" is added.",Toast.LENGTH_SHORT).show();
        }
    }
}
