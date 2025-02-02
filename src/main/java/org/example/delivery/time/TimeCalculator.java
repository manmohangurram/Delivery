package org.example.delivery.time;

import org.example.delivery.pojos.Location;

public interface TimeCalculator {

    float calculateTime(Location source, Location destination);
}
