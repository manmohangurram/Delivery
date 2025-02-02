package org.example.delivery.time.distance;

import org.example.delivery.pojos.Location;

public interface DistanceCalculator {
    double calculate(Location source, Location destination);
}
