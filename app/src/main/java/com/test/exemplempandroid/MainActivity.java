package com.test.exemplempandroid;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.test.exemplempandroid.models.CustomValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * List containing values from server after request GET
     */
    private Map<String,List<CustomValue>> realTimeValues = new HashMap<String,List<CustomValue>>();

    /**
     * Colors list
     */
    private List<Integer> colors = new ArrayList<Integer>();

    /**
     * Components
     */
    private LineChart lineChart;
    private TextView realTimeTex;
    private TextView choiceTex;

    /**
     * Simulate real time
     */
    final Handler handlerGraph = new Handler();

    /**
     * Simulate update from real time
     */
    private boolean canListen = true;
    private int upValue = 0;
    private int downValue = 3;
    private float valueX = 17f;
    private float valueY = 6.5f;

    /**
     * data list to display on line chart (all line data set)
     */
    List<ILineDataSet> dataSets;
    /**
     * Allows to delete data
     */
    List<ILineDataSet> dataSetsToRemove = new ArrayList<ILineDataSet>();

    /**
     * This map allows to save for each channel the LineDataSet to update the
     * values from real time (websocket by example)
     */
    Map<String,LineDataSet> saveDataSetChanel = new HashMap<String,LineDataSet>();


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

        initColors();
        initRealtimeValues();
        initView();

        handlerGraph.postDelayed(runnableGraph, 1000);
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

    Runnable runnableGraph = new Runnable() {

        @Override
        public void run() {
            try{
                //do your code here
                if(realTimeValues.size() > 0 && canListen){
                    if(upValue < 4){
                        LineDataSet lineDataSet = saveDataSetChanel.get("1_r_polled");
                        List<Entry> en = lineDataSet.getValues();
                        en.add(new Entry(valueX,valueY));
                        valueX++;
                        valueY += 1.5f;
                        upValue++;
                        downValue = 3;
                    }else{
                        if(downValue > 0){
                            downValue--;
                            LineDataSet lineDataSet = saveDataSetChanel.get("1_r_polled");
                            List<Entry> en = lineDataSet.getValues();
                            en.add(new Entry(valueX,valueY));
                            valueX++;
                            valueY -= 2.5f;
                        }else{
                            upValue = 0;
                        }
                    }

                    //update values :
                    lineChart.notifyDataSetChanged();
                    lineChart.invalidate();
                }
            }
            catch (Exception e) {
            }
            finally{
                //also call the same runnable to call it at regular interval
                handlerGraph.postDelayed(this, 6000);
            }
        }
    };

    private void initColors(){
        colors.add(getResources().getColor(R.color.valuePurple));
        colors.add(getResources().getColor(R.color.valueBlue));
        colors.add(getResources().getColor(R.color.valueGreen));
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
        listOne.add(new CustomValue(12f,11f));
        listOne.add(new CustomValue(13f,11f));
        listOne.add(new CustomValue(15f,9f));
        listOne.add(new CustomValue(16f,8.5f));
        listOne.add(new CustomValue(17f,7f));
        listOne.add(new CustomValue(19f,5f));
        listOne.add(new CustomValue(20f,4.5f));


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

    private void initView(){
        lineChart = (LineChart)findViewById(R.id.chart);
        realTimeTex = (TextView)findViewById(R.id.real_time);
        choiceTex = (TextView)findViewById(R.id.choice);

        realTimeTex.setOnClickListener(this);
        choiceTex.setOnClickListener(this);

        initValues();
    }

    private void initValues(){
        if(dataSets == null){
            dataSets = new ArrayList<ILineDataSet>();
        }
        int i = 0;
        for(Map.Entry<String,List<CustomValue>> v : realTimeValues.entrySet()){
            String key = v.getKey();
            List<CustomValue> value = v.getValue();

            //loop on lists
            List<Entry> entries = new ArrayList<Entry>();
            for(CustomValue cv : value){
                //1. adding in entries
                entries.add(new Entry(cv.getAxisX(),cv.getAxisY()));
            }

            //2. adding to dataset with label
            LineDataSet lineDataSet = new LineDataSet(entries,key);
            //set color here
            lineDataSet.setColor(colors.get(i));
            //disable point label on line
            lineDataSet.setDrawValues(false);
            //disable circles on line
            lineDataSet.setDrawCircles(false);
            //highlight
            lineDataSet.setHighlightEnabled(true);
            lineDataSet.setDrawHighlightIndicators(true);
            lineDataSet.setHighLightColor(colors.get(1));

            //3. adding to lineData
            dataSets.add(lineDataSet);
            dataSetsToRemove.add(lineDataSet);
            //for real time
            saveDataSetChanel.put(key,lineDataSet);
            i++;
        }


        //adding dataSet :
        LineData data = new LineData(dataSets);
        //adding to chart :
        lineChart.setData(data);
        //disable axis
        lineChart.getXAxis().setEnabled(false);
        lineChart.getAxisLeft().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        //disable description :
        lineChart.getDescription().setEnabled(false);
        //marker view
        lineChart.setDrawMarkerViews(true);
        CustomMarkerView customMarkerView = new CustomMarkerView(this,R.layout.layout_marker_view);
        lineChart.setMarkerView(customMarkerView);
        lineChart.invalidate(); //refresh
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.real_time:
                canListen = true;
                initValues();
                handlerGraph.postDelayed(runnableGraph, 1000);
                break;
            case R.id.choice :
                canListen = false;
                handlerGraph.removeCallbacks(runnableGraph);
                dataSets.removeAll(dataSetsToRemove);
                break;
        }
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }


}
