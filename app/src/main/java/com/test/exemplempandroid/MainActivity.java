package com.test.exemplempandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.test.exemplempandroid.models.CustomValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<String,List<CustomValue>> realTimeValues = new HashMap<String,List<CustomValue>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initRealtimeValues();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initRealtimeValues(){
        List<CustomValue> listOne = new ArrayList<CustomValue>();
        List<CustomValue> listTwo = new ArrayList<CustomValue>();
        List<CustomValue> listThree = new ArrayList<CustomValue>();

        listOne.add(new CustomValue(1f,1f));
        listOne.add(new CustomValue(3f,3f));
        listOne.add(new CustomValue(3f,3f));
        listOne.add(new CustomValue(5f,5f));
        listOne.add(new CustomValue(7f,7f));
        listOne.add(new CustomValue(7f,7f));
        listOne.add(new CustomValue(9f,9f));
        listOne.add(new CustomValue(11f,11f));

        listTwo.add(new CustomValue(10f,1f));
        listTwo.add(new CustomValue(20f,2f));
        listTwo.add(new CustomValue(30f,2.5f));
        listTwo.add(new CustomValue(40f,4f));
        listTwo.add(new CustomValue(50f,5f));
        listTwo.add(new CustomValue(80f,7f));

        listThree.add(new CustomValue(5f,0.5f));
        listThree.add(new CustomValue(6f,1f));
        listThree.add(new CustomValue(7f,1.5f));
        listThree.add(new CustomValue(8f,2f));
        listThree.add(new CustomValue(9f,2.5f));
        listThree.add(new CustomValue(10f,3f));
        listThree.add(new CustomValue(11f,3.5f));
        listThree.add(new CustomValue(12f,4f));
        listThree.add(new CustomValue(13f,4.5f));
        listThree.add(new CustomValue(14f,5f));
        listThree.add(new CustomValue(15f,5f));

        realTimeValues.put("1_r_polled", listOne);
        realTimeValues.put("2_r_ind",listTwo);
        realTimeValues.put("5_r_polled",listThree);
    }
}
