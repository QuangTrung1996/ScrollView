package com.trung.example.translatevietphrase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> listImageURLs;
    int he = 0;

    public GridViewAdapter(Context context, List<String> listImageURLs, int h){
        this.context = context;
        this.listImageURLs = listImageURLs;
        he = h;
    }

    @Override
    public int getCount() {
        return listImageURLs.size();
    }

    @Override
    public Object getItem(int position) {
        return listImageURLs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_main2_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = convertView.findViewById(R.id.text1);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        viewHolder.imageView.setText(listImageURLs.get(position));
        viewHolder.imageView.setHeight(he);

        return convertView;
    }

    class ViewHolder{
        TextView imageView;
    }
}