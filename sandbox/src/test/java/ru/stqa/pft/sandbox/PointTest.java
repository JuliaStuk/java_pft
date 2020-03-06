package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {
    @Test
    public void test1Point () {
        Point p1 = new Point(2, 1);
        Point p2 = new Point(6, 4);
        Assert.assertEquals(p1.distance(p2), 5);

    }
    @Test(enabled = true)
    public void test2Point () {
        Point p1 = new Point(-2, 1);
        Assert.assertEquals(p1.distance(new Point(6, -4)), 5);

    }
}
