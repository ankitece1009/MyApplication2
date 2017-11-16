package com.example.admin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    /*String [] str = {
            "Ankit" , "Kesarwani",
            "Ranjeet" ,"Singh",
            "Krishna" , "Maurya"
    };*/
    Map<String,String> map ;
    List<String> mylist;
    void ReadfromBothFiles() {
        Scanner sc1 = new Scanner(getResources().openRawResource(R.raw.hindiwords));
        ReadFromFile(sc1);
        try{
        Scanner sc = new Scanner(openFileInput("new_word.txt"));
        ReadFromFile(sc);
        }
        catch (FileNotFoundException fnfe)
        {

        }
    }
    void ReadFromFile(Scanner sc)
    {
        try{

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] keyvalue = line.split("\t");
                if(keyvalue.length < 2) continue;
                map.put(keyvalue[0], keyvalue[1]);
                mylist.add(keyvalue[0]);
                //map.put(line,"1");
            }
        }
        catch (Exception ex){
           // TextView tv  = (TextView) findViewById(R.id.tv);
           // tv.setText(ex.getMessage()+"\n"+ex.getLocalizedMessage());
        }
    }

  /*  void WriteIntoFile()
    {
        try{
            PrintStream output = new PrintStream(openFileOutput("output.txt",MODE_PRIVATE));
            output.println("from");output.println( "print");
            output.println("stream");
            output.close();
            Scanner scan  = new Scanner(openFileInput("output.txt"));
            String str = "";
            while(scan.hasNext()){
                String line = scan.nextLine()+"\n";
                str += line;
            }
            TextView tv  = (TextView) findViewById(R.id.tv);
            tv.setText(str);
            scan.close();
        }
        catch(Exception ex)
        {
            TextView tv  = (TextView) findViewById(R.id.tv);
            tv.setText(ex.getMessage()+"\n"+ex.getLocalizedMessage());
        }
    }*/
    void wordGame(){
        Random randy = new Random();
        int value = randy.nextInt(mylist.size());
        String keyword = mylist.get(value);
        String answer = map.get(keyword);
        List<String> options = new ArrayList<>(map.values());
        options.remove(answer);
        Collections.shuffle(options);
        options =  options.subList(0,4);
        options.add(answer);
        Collections.shuffle(options);
         TextView tv = (TextView)findViewById(R.id.the_word);
        tv.setText(keyword);
        ListView lv = (ListView) findViewById(R.id.the_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                new ArrayList<String>(options));
        lv.setAdapter(adapter);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        map = new HashMap<>();
        mylist = new ArrayList<>();
        /*for(int i=0;i<str.length;i+=2)
        {
            map.put(str[i],str[i+1]);
        }
        final Spinner spin = (Spinner) findViewById(R.id.spin);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                new ArrayList<String>(map.keySet())) ;
        spin.setAdapter(adapter);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  String word = parent.getItemAtPosition(position).toString();
                  String defn = map.get(word);
                Toast.makeText(getApplicationContext(),defn,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
               // Toast.makeText(getApplicationContext(),"Nothing Selected.",Toast.LENGTH_SHORT).show();
               // spin.setSelection(0);
            }
        });*/
            ReadfromBothFiles();

        wordGame();
        //WriteIntoFile();
        ListView LV=(ListView)findViewById(R.id.the_list);
        LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String chosen = parent.getItemAtPosition(position).toString();
                TextView myText = (TextView)findViewById(R.id.the_word);
                String  key = myText.getText().toString();
                String correct = map.get(key);
                if(chosen.equals(correct))
                    Toast.makeText(getApplicationContext(),"AweSome!",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(),":-{,In correct.",Toast.LENGTH_SHORT).show();
                wordGame();
            }
        });

    }
}
