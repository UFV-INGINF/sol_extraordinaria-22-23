package com.ufv.extraordinaria.dis;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Methods {

    public static ArrayList<Spaceship> deleteItems(ArrayList<Spaceship> spaceshipArrayList, Spaceship item) {
        ArrayList<Spaceship> clean = new ArrayList<>();
        for (Spaceship spaceship : spaceshipArrayList) {
            if (!Objects.equals(spaceship.getName(), item.getName())) {
                clean.add(spaceship);
            }
        }
        return clean;
    }
}
