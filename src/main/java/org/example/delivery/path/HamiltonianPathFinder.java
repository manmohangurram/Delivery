package org.example.delivery.path;

import org.example.delivery.pojos.Order;

import java.util.*;

public class HamiltonianPathFinder implements PathsFinder {

    @Override
    public List<List<String>> findAllPaths(Map<String, List<String>> graph, List<Order> orders, String sourceId) {
        Stack<String> visited = new Stack<>();
        List<String> path = new ArrayList<>();
        List<List<String>> paths = new ArrayList<>();

        visited.push(sourceId);
        path.add(sourceId);

        // Convert the order to map for quick search purpose
        Map<String, String> ordersMap = new HashMap<>();
        orders.forEach(order -> ordersMap.put(order.customerId(), order.restaurantId()));

        backtrack(graph, ordersMap, sourceId, visited, path, paths, graph.size());

        return paths;
    }

    private void backtrack(Map<String, List<String>> graph, Map<String, String> orders, String current, Stack<String> visited, List<String> path, List<List<String>> paths, int requiredPathSize) {

        if (path.size() == requiredPathSize) {
            paths.add(new ArrayList<>(path));
            return;
        }


        // Explore all neighbors of the current vertex
        for (String neighbor : graph.get(current)) {

            if (!visited.contains(neighbor)) {

                // Check if the neighbor is customer, if it's a customer make sure restaurant was visited first else don't go that path.
                if (neighbor.startsWith("C")) {
                    String restaurantId = orders.get(neighbor);
                    if(!visited.contains(restaurantId)) {
                        return;
                    }
                }

                // Add the neighbor to visited and path
                visited.push(neighbor);
                path.add(neighbor);

                backtrack(graph, orders, neighbor, visited, path, paths, requiredPathSize);

                // Remove the neighbor so that it can visit different path.
                visited.pop();
                path.remove(path.size() - 1);
            }
        }
    }
}
