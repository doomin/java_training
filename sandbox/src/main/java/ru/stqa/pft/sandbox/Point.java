package ru.stqa.pft.sandbox;

/**
 * Created by doomin on 03.12.16.
 */
public class Point {

  //Parameters definition
  public double x;
  public double y;
  public double result;

  //Constructor for Point object
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }


  //Method for calculating distance from one point to another
  public double distance(Point other) {
    result = Math.sqrt((other.x - this.x) * (other.x - this.x) + (other.y - this.y) * (other.y - this.y));
    result *= 1000;
    result = Math.round(result);
    result /= 1000;
    return result;
  }
}
