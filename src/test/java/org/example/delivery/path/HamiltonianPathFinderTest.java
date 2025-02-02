package org.example.delivery.path;

import org.example.delivery.pojos.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HamiltonianPathFinderTest {

    private final PathsFinder pathsFinder = new HamiltonianPathFinder();

    @Test
    void findAllPaths() {

        Map<String, List<String>> graph = Map.of(
                "A", List.of("R1", "R2"),
                "R1", List.of("A", "R2", "C1", "C2"),
                "R2", List.of("A", "R1", "C1", "C2"),
                "C1", List.of("R1", "R2", "C2"),
                "C2", List.of("R1", "R2", "C1")
        );

        List<Order> orders = List.of(
                new Order("R1", "C1", 0.25f),
                new Order("R2", "C2", 0.5f)
        );

        List<List<String>> paths = pathsFinder.findAllPaths(graph, orders, "A");

        for(List<String> path: paths) {
            System.out.println(Arrays.toString(path.toArray(String[]::new)));
        }
    }
}