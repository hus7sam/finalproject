package com.codingelab.Final_Project_409;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


public class SearchAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private List<user_information_Internal> listItemStorage;

    public SearchAdapter(Context context, List<user_information_Internal> customizedListView) {
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        listItemStorage = customizedListView;

    }

    @Override
    public int getCount() { return listItemStorage.size(); }

    @Override
    public Object getItem(int position) { return listItemStorage.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) { ViewHolder listViewHolder;
        if(convertView == null){ listViewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.colmn_row, parent, false); listViewHolder.dictionaryWord = (TextView)convertView.findViewById(R.id.SecondText); convertView.setTag(listViewHolder); }else{ listViewHolder = (ViewHolder)convertView.getTag();

        } listViewHolder.dictionaryWord.setText(listItemStorage.get(position).getName());
        return convertView;

    }

    static class ViewHolder{
        TextView dictionaryWord;
    }
}
