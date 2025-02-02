package org.example.delivery;

import org.example.delivery.exceptions.ValidationException;
import org.example.delivery.path.HamiltonianPathFinder;
import org.example.delivery.path.PathsFinder;
import org.example.delivery.pojos.*;
import org.example.delivery.time.TimeCalculator;
import org.example.delivery.time.TimeCalculatorImpl;
import org.example.delivery.utils.Validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeliveryTimeCalculator {

    private final TimeCalculator timeCalculator = new TimeCalculatorImpl();
    private final PathsFinder pathsFinder = new HamiltonianPathFinder();

    public double calculateShortestTime(Map<String, List<String>> pathGraph, Agent agent, List<Restaurant> restaurants, List<Customer> customers, List<Order> orders) throws ValidationException {

        Validator.validateRestaurants(restaurants);
        Validator.validateCustomers(customers);

        List<List<String>> paths = pathsFinder.findAllPaths(pathGraph, orders, agent.id());

        Map<String, Restaurant> restaurantMap = new HashMap<>();
        restaurants.forEach(restaurant -> restaurantMap.put(restaurant.id(), restaurant));

        Map<String, Customer> customerMap = new HashMap<>();
        customers.forEach(customer -> customerMap.put(customer.id(), customer));

        Map<String, Order> orderMap = new HashMap<>();
        orders.forEach(order -> orderMap.put(order.restaurantId(), order));

        float minTime = Float.MAX_VALUE;
        for(List<String> path: paths) {
            float pathTime = calculatePathTime(path, agent.location(), restaurantMap, customerMap, orderMap);
            minTime = Math.min(minTime, pathTime);
        }

        return minTime;
    }


    private float calculatePathTime(List<String> path, Location agentLocation, Map<String, Restaurant> restaurants, Map<String, Customer> customers, Map<String, Order> orders) {

        float totalTime = 0;

        Location sourceLocation = agentLocation;

        for (int i = 1; i < path.size(); i++) {

            String destinationId = path.get(i);
            Location destinationLocation;

            // If it's a restaurant, need to add wait time also
            if(destinationId.startsWith("R")) {
                destinationLocation = restaurants.get(destinationId).location();
                totalTime += timeCalculator.calculateTime(sourceLocation, destinationLocation);
                Order order = orders.get(destinationId);
                float waitTime = Math.max(0, order.preparationTime() - totalTime);
                totalTime += waitTime;
            } else {
                destinationLocation = customers.get(destinationId).location();
                totalTime += timeCalculator.calculateTime(sourceLocation, destinationLocation);
            }

            sourceLocation = destinationLocation;
        }

        return totalTime;
    }
}
