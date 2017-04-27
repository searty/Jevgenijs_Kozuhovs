package com.example.seart.afinal;
import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.Locale;




public class MainActivity extends AppCompatActivity  implements SharedPreferences.OnSharedPreferenceChangeListener {
    protected ArrayList<ListRecord> list = new ArrayList<>();
    private String storage = "savedChanges";
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();

        try {
            FileInputStream fis = context.openFileInput( storage );
            ObjectInputStream ois = new ObjectInputStream(fis);
            list = (ArrayList<ListRecord>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        loadingOptions();

        final CustomAdapter adapter = new CustomAdapter(this, list);
//this is a start
        class Item {
            private String name;

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
            }

            public String toString() {
                return name;
            }
        }
        //this is the end of what i tried at night yo



        final EditText editText = (EditText) findViewById(R.id.textentry);
        final Button add = (Button) findViewById(R.id.add);
        final Button prefs = (Button) findViewById(R.id.preferences);





        final ListView listView = (ListView) findViewById(R.id.todo);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String descDate = ((TextView) view.findViewById(R.id.date)).getText().toString();
                String object = ((TextView) view.findViewById(R.id.todoitem)).getText().toString();
                Intent myIntent = new Intent(MainActivity.this,ExtendedActivity.class);
                myIntent.putExtra("data",object);
                myIntent.putExtra("date",descDate);
                //MainActivity.this.startActivity(myIntent);
                startActivity(myIntent);
            }
        });

        prefs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(MainActivity.this, Options.class);
                startActivity(settingsIntent);
            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().length() == 0) {
                    Toast.makeText(context, "Input is empty. Try again", Toast.LENGTH_SHORT).show();
                    return;
                }
                Calendar c = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy, HH:mm", Locale.ENGLISH);
                list.add(new ListRecord(editText.getText().toString(), format.format(c.getTime())));
                adapter.notifyDataSetChanged();
                editText.setText(null);

                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
                saveChanges();
            }
        });
    }
    private void loadingOptions(){
        SharedPreferences Options = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ListView listView = (ListView) findViewById(R.id.todo);
        String Id = Options.getString("listPref","1");
        if(Id.equals("1"))
            listView.setBackgroundColor(Color.WHITE);
        else if(Id.equals("2"))
            listView.setBackgroundColor(Color.GRAY);
        else if(Id.equals("3"))
            listView.setBackgroundColor(Color.BLUE);
        else if(Id.equals("4"))
            listView.setBackgroundColor(Color.GREEN);
        if(Id.equals("5"))
        listView.setBackgroundColor(Color.YELLOW);
        Options.registerOnSharedPreferenceChangeListener(MainActivity.this);
    }
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        loadingOptions();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data, View view) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (0) : {
                if (resultCode == Activity.RESULT_OK) {


                    TextView tv = (TextView) view.findViewById(R.id.todoitem);

                    String newValue = data.getStringExtra("new");

                    for (ListRecord r: list) {

                        tv.setText(newValue);


                    }
                    saveChanges();
                }
                break;
            }
        }
    };


    protected void saveChanges(){
        try {
            ObjectOutputStream stream = new ObjectOutputStream(openFileOutput(storage, Context.MODE_PRIVATE));
            stream.writeObject(list);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//hey how are you doing?
}