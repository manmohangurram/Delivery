package org.example.delivery.time.distance;


import org.example.delivery.pojos.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HaversineDistanceTest {

    private final Location source = new Location(51.5007, 0.1246);
    private final Location destination = new Location(40.6892, 74.0445);

    @Test
    public void test() {
        DistanceCalculator distanceCalculator = new HaversineDistanceCalculator();
        double distance = distanceCalculator.calculate(source, destination);
        double expectedDistance = 5574.840456848554;
        assertEquals(expectedDistance, distance);
    }
}
