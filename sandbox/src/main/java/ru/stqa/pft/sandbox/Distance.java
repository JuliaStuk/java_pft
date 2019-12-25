package ru.stqa.pft.sandbox;

public class Distance {
    public static void main(String[] args) {

        Point p1 = new Point(2, 1);
        Point p2 = new Point(6, 4);

        System.out.println("Расстояние между точкой А с координатой (" + p1.x + ", " + p1.y + ") и " +
                "точкой В с координатой (" + p2.x + ", " + p2.y + ") = " +
                p1.distance(p2));

    }
}