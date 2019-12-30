package com.codingelab.Final_Project_409;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class listViewAdapter_External {
    public ArrayList<user_information_External> personlist;
    Activity activity;

    public listViewAdapter_External(Activity activity, ArrayList<user_information_External> personlist) {
        super();
        this.activity = activity; this.personlist = personlist;  }



    public int getCount() {
        return personlist.size();
    }


    public Object getItem(int position) {
        return personlist.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder { TextView users_id, users_Name, users_Phone, users_Email;  }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            holder = new ViewHolder();
            holder.users_id = (TextView) convertView.findViewById(R.id.Column_id);
            holder.users_Name = (TextView) convertView.findViewById(R.id.Column_Name);
            holder.users_Phone = (TextView) convertView.findViewById(R.id.Column_Phone);
            holder.users_Email = (TextView) convertView.findViewById(R.id.Column_Email);
            convertView.setTag(holder);
        } else {  holder = (ViewHolder) convertView.getTag();  }

        user_information_External item = personlist.get(position);
        holder.users_id.setText(item.getId());
        holder.users_Name.setText(item.getName().toString());
        holder.users_Phone.setText(item.getPhone().toString());
        holder.users_Email.setText(item.getEmail().toString());
        return convertView;
    }
}
