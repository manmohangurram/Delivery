package org.example.delivery.time;

import org.example.delivery.pojos.Location;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeCalculatorTest {

    private final Location source = new Location(51.5007, 0.1246);
    private final Location destination = new Location(40.6892, 74.0445);

    @Test
    public void test() {
        TimeCalculator timeCalculator = new TimeCalculatorImpl();
        float expectedTime = 278.7420228F;
        float time = timeCalculator.calculateTime(source, destination);
        assertEquals(expectedTime, time);
    }
}
