package org.example.delivery.time;

import org.example.delivery.pojos.Location;
import org.example.delivery.time.distance.DistanceCalculator;
import org.example.delivery.time.distance.HaversineDistanceCalculator;

public class TimeCalculatorImpl implements TimeCalculator {

    private final DistanceCalculator distanceCalculator = new HaversineDistanceCalculator();
    private static final float speed = 20;

    @Override
    public float calculateTime(Location source, Location destination) {
        double distance = distanceCalculator.calculate(source, destination);
        return (float) (distance / speed);
    }
}
