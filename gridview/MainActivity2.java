package com.trung.example.translatevietphrase;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity
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
        setContentView(R.layout.activity_main2);

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
        //arrayAdapter1 = new ArrayAdapter<>(this, R.layout.activity_main2_item, stringList1);
        //arrayAdapter2 = new ArrayAdapter<>(this, R.layout.activity_main2_item, stringList2);

        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        GridViewAdapter arrayAdapter1 = new GridViewAdapter(this, stringList1, dm.heightPixels / 10);
        GridViewAdapter arrayAdapter2 = new GridViewAdapter(this, stringList2, dm.heightPixels / 10);

        gridView1.setAdapter(arrayAdapter1);
        gridView2.setAdapter(arrayAdapter2);

        for (int i =0; i < 10 * 3; i++)
        {
            stringList1.add(String.valueOf(i));
        }

        for (int i =0; i < 10 * 30; i++)
        {
            stringList2.add(String.valueOf(i));
        }

        arrayAdapter1.notifyDataSetChanged();
        arrayAdapter2.notifyDataSetChanged();

        /*DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float width = displayMetrics.widthPixels / displayMetrics.density;

        gridView1.setNumColumns(14);
        gridView1.setColumnWidth((int) width);

        gridView2.setNumColumns(14);
        gridView2.setColumnWidth((int) width);*/

        gridViewSetting(gridView1);
        gridViewSetting1(gridView2);

        //gridView1.invalidate();
        //gridView2.invalidate();
    }

    private void gridViewSetting(GridView gridView1) {

        // this is size of your list with data
        int size = 10;
        // Calculated single Item Layout Width for each grid element .. for me it was ~100dp
        int width = 100 ;

        // than just calculate sizes for layout params and use it
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;

        int totalWidth = (int) (width * size * density);
        int singleItemWidth = (int) (width * density);

        int h = 100 ;

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                totalWidth, ViewGroup.LayoutParams.MATCH_PARENT);

        gridView1.setLayoutParams(params);
        gridView1.setColumnWidth(singleItemWidth);
        gridView1.setHorizontalSpacing(2);
        gridView1.setStretchMode(GridView.STRETCH_SPACING);
        gridView1.setNumColumns(size);
    }

    private void gridViewSetting1(GridView gridView1) {

        // this is size of your list with data
        int size = 10;
        // Calculated single Item Layout Width for each grid element .. for me it was ~100dp
        int width = 100 ;
        int h = 100 ;
        // than just calculate sizes for layout params and use it
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;

        int totalWidth = (int) (width * size * density);
        int singleItemWidth = (int) (width * density);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                totalWidth, dm.heightPixels / 10 * 31 + 30 * 2);

        gridView1.setLayoutParams(params);
        gridView1.setColumnWidth(singleItemWidth);
        gridView1.setHorizontalSpacing(2);
        gridView1.setStretchMode(GridView.STRETCH_SPACING);
        gridView1.setNumColumns(size);
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
