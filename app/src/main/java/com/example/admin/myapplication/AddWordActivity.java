package com.example.admin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;

public class AddWordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);
    }

    public void AddNewWordToFile(View view) throws IOException {
        EditText word = (EditText)findViewById(R.id.newword);
        EditText defn = (EditText)findViewById(R.id.newworddefn);
        String newword = word.getText().toString();
        String newworddefn = defn.getText().toString();
        TextView tv =(TextView)findViewById(R.id.tv);
        if((newword.isEmpty())|| (newworddefn.isEmpty())){
           // tv.setText("nothing");
            Toast.makeText(AddWordActivity.this,"Add word & its Definition",Toast.LENGTH_SHORT).show();
        }
        else
        {
            PrintStream output = new PrintStream(openFileOutput("new_word.txt",MODE_PRIVATE|MODE_APPEND));
            output.println(newword +"\t"+newworddefn);
            output.close();
        }
        Intent goback = new Intent();
        goback.putExtra("newword",newword);
        setResult(RESULT_OK,goback);
        finish();
    }
}
