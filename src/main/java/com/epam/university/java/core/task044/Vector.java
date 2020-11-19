package com.epam.university.java.core.task044;

public class Vector {
    private Integer pointA;
    private Integer pointB;

    public Vector(int pointA, int pointB) {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public Vector() {
    }

    public Integer getPointA() {
        return pointA;
    }

    public void setPointA(Integer pointA) {
        this.pointA = pointA;
    }

    public Integer getPointB() {
        return pointB;
    }

    public void setPointB(Integer pointB) {
        this.pointB = pointB;
    }
}
