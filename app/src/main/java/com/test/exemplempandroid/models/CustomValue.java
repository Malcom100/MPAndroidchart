package com.test.exemplempandroid.models;

/**
 * Created by gtshilombowanticale on 18-12-16.
 */
public class CustomValue {

    private float axisX;
    private float axisY;

    public CustomValue(){}

    public CustomValue(float axisX, float axisY){
        setAxisX(axisX);
        setAxisY(axisY);
    }

    public float getAxisX() {
        return axisX;
    }

    public void setAxisX(float axisX) {
        this.axisX = axisX;
    }

    public float getAxisY() {
        return axisY;
    }

    public void setAxisY(float axisY) {
        this.axisY = axisY;
    }

    public String toString(){
        return getAxisX()+","+getAxisY();
    }
}
