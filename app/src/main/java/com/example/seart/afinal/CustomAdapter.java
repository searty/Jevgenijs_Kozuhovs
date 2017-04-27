package com.example.seart.afinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {
    public Context context;
    public LayoutInflater layoutInflater;
    public ArrayList<ListRecord> records;




    @Override
    public int getCount() {
        return records.size();
    }

    @Override
    public Object getItem(int position) {
        return records.get(position);
    }


    public CustomAdapter(Context context, ArrayList<ListRecord> records) {
        this.context = context;
        this.records = records;
        layoutInflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final CustomAdapter recordAdapter = this;
        final ListRecord record = (ListRecord) getItem(position);


        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.checkbox_delete_image, null);
        }
        TextView tv;
        TextView tvdate;
        tv = (TextView) view.findViewById(R.id.todoitem);
        tvdate = (TextView) view.findViewById(R.id.date);
        tv.setText(record.getTaskName());
        tvdate.setText(record.getTaskName());
        ((TextView) view.findViewById(R.id.date)).setText(record.getDate());




        ImageView image = (ImageView) view.findViewById(R.id.erase);
        image.setImageResource(R.mipmap.erase);

        image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                records.remove(record);
                recordAdapter.notifyDataSetChanged();
                if(context instanceof MainActivity){
                    ((MainActivity)context).saveChanges();
                }
            }
        });

        final CheckBox cb = (CheckBox) view.findViewById(R.id.checkbox);
        cb.setTag(position);
        cb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                record.setCheck(cb.isChecked());
            }
        });

        return view;
    }




}



