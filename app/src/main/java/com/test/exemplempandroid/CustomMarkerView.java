package com.test.exemplempandroid;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gtshilombowanticale on 18-12-16.
 */
public class CustomMarkerView extends MarkerView {

    private TextView valueText;
    private long timestamp_reference;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public CustomMarkerView(Context context, int layoutResource,long timestamp_reference) {
        super(context, layoutResource);
        valueText = (TextView)findViewById(R.id.id_value);
        this.timestamp_reference = timestamp_reference;
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        super.refreshContent(e, highlight);
        long smallNumber = (long)e.getX();
        long timestamp = timestamp_reference + smallNumber;
        Format formatterHD = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date date = new Date(timestamp * 1000);
        valueText.setText(String.format("%d", (int)e.getY())+" "+formatterHD.format(date));
    }

}
