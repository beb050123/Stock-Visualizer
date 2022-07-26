package com.example.financeplayground.data;

import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class ExponentialMovingAverage {

  private final LinkedList<Double> Dataset = new LinkedList<Double>();
  private final int period;
  private double sum;

  public ExponentialMovingAverage(int period) {
    this.period = period;
  }

  private double calcMultiplier() {
    return 2.0 / (period + 1);
  }

  public void addData(double num) {
    sum += num;
    Dataset.add(num);

    if (Dataset.size() > period) {
      sum -= Dataset.remove();
    }
  }

  // getema() returns the current EMA value
  public double calculateEMA() {

    for (int i = 0; i < Dataset.size(); i++) {
      if (i == 0) {
        return Dataset.get(i);
      } else {
        return (Dataset.get(i) * calcMultiplier()) + (Dataset.get(i - 1) * (1 - calcMultiplier()));
      }
    }

    return 0;
  }
}
