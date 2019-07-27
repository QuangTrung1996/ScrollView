package com.trung.example.translatevietphrase;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.HorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    private HorizontalScrollView horizontalScrollView1;
    private HorizontalScrollView horizontalScrollView2;

    private GridView gridView1;
    private GridView gridView2;

    private List<String> stringList1;
    private List<String> stringList2;

    ArrayAdapter<String> arrayAdapter1;
    ArrayAdapter<String> arrayAdapter2;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initLoadData();
        initSysced();
    }

    private void initView()
    {
        horizontalScrollView1 = findViewById(R.id.horizontalScrollView1);
        horizontalScrollView2 = findViewById(R.id.horizontalScrollView2);

        gridView1 = findViewById(R.id.gridView1);
        gridView2 = findViewById(R.id.gridView2);
    }

    private void initLoadData()
    {
        stringList1 = new ArrayList<>();
        stringList2 = new ArrayList<>();
        arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList1);
        arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringList2);
        gridView1.setAdapter(arrayAdapter1);
        gridView2.setAdapter(arrayAdapter2);

        for (int i =0; i < 14 * 5; i++)
        {
            stringList1.add(String.valueOf(i));
        }

        for (int i =0; i < 14 * 30; i++)
        {
            stringList2.add(String.valueOf(i));
        }

        arrayAdapter1.notifyDataSetChanged();
        arrayAdapter2.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initSysced()
    {
        horizontalScrollView1.setOnScrollChangeListener(new View.OnScrollChangeListener()
        {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY)
            {
                horizontalScrollView2.scrollTo(scrollX, 0);
            }
        });

        horizontalScrollView2.setOnScrollChangeListener(new View.OnScrollChangeListener()
        {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY)
            {
                horizontalScrollView1.scrollTo(scrollX, 0);
            }
        });

    }
}
