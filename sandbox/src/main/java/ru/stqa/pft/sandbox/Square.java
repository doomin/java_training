package ru.stqa.pft.sandbox;

/**
 * Created by doomin on 03.12.16.
 */
public class Square {

  public double l;
  
  public Square(double l) {
    this.l = l;
  }

  public double area() {
    return this.l*this.l;
  }

}
