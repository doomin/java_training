package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by doomin on 12.12.16.
 */
public class PointTests {

 @Test
  public void testDistance1(){
    Point p1 = new Point(2,2);
    Point p2 = new Point(3,5);

    Assert.assertEquals(p1.distance(p2), 3.162);
  }

  @Test
  public void testDistance2(){
    Point p1 = new Point(1.54,145.1);
    Point p2 = new Point(111.9,-233.0);

    Assert.assertEquals(p1.distance(p2), 393.877);
  }
}
