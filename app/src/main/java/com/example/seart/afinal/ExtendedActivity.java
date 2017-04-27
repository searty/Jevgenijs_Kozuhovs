package com.example.seart.afinal;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ExtendedActivity  extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.extended);

        Bundle bundle = getIntent().getExtras();
     final String data = bundle.getString("data");
        final String date = bundle.getString("date");

        final ListRecord r = (ListRecord) getIntent().getSerializableExtra("Record");
      //  final TextView textview = (TextView) findViewById(R.id.tv);
        final EditText et = (EditText) findViewById(R.id.et);
        final Button eb = (Button) findViewById(R.id.save);
        final TextView descName = (TextView) findViewById(R.id.descName);
        final TextView descDate = (TextView) findViewById(R.id.descDate);



        descName.setText(data);
        descDate.setText(date);
       // textview.setText(data);
   /*     String text = et.getText().toString();
        et.setText(text);
*/
        Button share = (Button) findViewById(R.id.shareb);
        share.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String finalString = descName.getText().toString();
              Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setAction(Intent.ACTION_SEND);


                String tweetUrl = "https://twitter.com/intent/tweet?text=" + finalString;
                Uri uri = Uri.parse(tweetUrl);

                shareIntent.putExtra(Intent.EXTRA_TEXT, data.toString());
                shareIntent.setType("text/plain");
               // startActivity(Intent.createChooser(shareIntent, "Share via"));
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });

        eb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExtendedActivity.this,CustomAdapter.class);
                String text = et.getText().toString();
                intent.putExtra("new", text);
                intent.putExtra("old", data);
                toast(text);
               // textview.setText(data);
              //  et.setText(text);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
    public void toast(String msg){
        Context context = getApplicationContext();
        CharSequence text = msg;
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}



