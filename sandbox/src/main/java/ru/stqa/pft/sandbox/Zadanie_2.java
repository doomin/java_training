package ru.stqa.pft.sandbox;

/**
 * Created by doomin on 03.12.16.
 */
public class Zadanie_2 {

  public static void main (String[] args) {

    //Function reference
    Point p1 = new Point(1,2);
    Point p2 = new Point(3,4);
    System.out.println("Function - Distance between points (" + p1.x + ", " + p1.y + ") and (" + p2.x + ", " + p2.y + ") is " + distance(p1,p2));

    //Method reference
    Point z1 = new Point(5,5);
    Point z2 = new Point(4,4);
    System.out.println("Method - Distance between points (" + z1.x + ", " + z1.y + ") and (" + z2.x + ", " + z2.y + ") is " + z1.distance(z2));
  }

  //Distance function definition
  public static double distance(Point p1, Point p2){
    return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y));
  }
}
