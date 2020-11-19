package com.epam.university.java.core.task044;

import com.epam.university.java.core.task013.Vertex;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Task044Impl implements Task044 {
    @Override
    public int findCountOfTraces(Integer points, List<String> lines) {
        if (points == null || lines == null) {
            throw new IllegalArgumentException();
        }

        if (lines.isEmpty()) {
            return points;
        }

        List<Vector> vectors = new ArrayList<>();
        for (String line : lines) {
            String[] s = line.split(" ");
            vectors.add(new Vector(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
        }

        for (int i = 0; i < vectors.size(); i++) {
            if (i != vectors.size() - 1) {
                Vector vector = vectors.get(i);
                Vector vectorNext = vectors.get(i + 1);
                if (vector.getPointB() == vectorNext.getPointA()) {
                    vectorNext.setPointA(vector.getPointA());
                    vectors.set(i, null);
                }
            }
        }

        while (vectors.remove(null)){}

        Set<Integer> uniquePoints = new TreeSet<>();
        for (String line : lines) {
            String[] s = line.split(" ");
            uniquePoints.add(Integer.parseInt(s[0]));
            uniquePoints.add(Integer.parseInt(s[1]));
        }
        List<Integer> integersPoints = new ArrayList<>(uniquePoints);
        int delta = points - integersPoints.get(integersPoints.size() - 1);

        return vectors.size() + delta;
    }
}
