package org.example.delivery.time.distance;

import org.example.delivery.pojos.Location;


public class HaversineDistanceCalculator implements DistanceCalculator{

    private static final double RADIUS = 6371;

    @Override
    public double calculate(Location source, Location destination) {
        double sLat = Math.toRadians(source.latitude());
        double sLon = Math.toRadians(source.longitude());
        double deLat = Math.toRadians(destination.latitude());
        double deLon = Math.toRadians(destination.longitude());

        // Difference in latitude and longitude
        double dLat = deLat - sLat;
        double dLon = deLon - sLon;

        // Haversine formula
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.cos(sLat) * Math.cos(deLat) *
                        Math.pow(Math.sin(dLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RADIUS * c;
    }

}
