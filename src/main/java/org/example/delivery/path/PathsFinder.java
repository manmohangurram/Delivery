package org.example.delivery.path;

import org.example.delivery.pojos.Order;

import java.util.List;
import java.util.Map;

public interface PathsFinder {

    List<List<String>> findAllPaths(Map<String, List<String>> graph, List<Order> orders, String sourceId);
}
