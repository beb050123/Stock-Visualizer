package com.example.financeplayground.data;


import java.util.*;

public class SimpleMovingAverage {

    private final Queue<Double> Dataset = new LinkedList<Double>();
    private final int period;
    private double sum;

    public SimpleMovingAverage(int period)
    {
        this.period = period;
    }

    public void addData(double num)
    {
        sum += num;
        Dataset.add(num);


        if (Dataset.size() > period)
        {
            sum -= Dataset.remove();
        }
    }

    public double getMean()
    {
        return sum / period;
    }

}