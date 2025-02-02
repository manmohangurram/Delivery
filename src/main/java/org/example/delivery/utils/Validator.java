package org.example.delivery.utils;

import org.example.delivery.exceptions.ValidationException;
import org.example.delivery.pojos.Customer;
import org.example.delivery.pojos.Location;
import org.example.delivery.pojos.Restaurant;

import java.util.List;
import java.util.Objects;

public class Validator {

    public static void validateRestaurants(List<Restaurant> restaurants) throws NullPointerException, ValidationException {
        for(Restaurant restaurant: restaurants) {
            validateRestaurant(restaurant);
        }
    }

    public static void validateCustomers(List<Customer> customers) throws NullPointerException, ValidationException {
        for(Customer customer: customers) {
            validateCustomer(customer);
        }
    }

    public static void validateRestaurant(Restaurant restaurant) throws NullPointerException, ValidationException {

        if(Objects.isNull(restaurant)) {
            throw new NullPointerException("Restaurant cannot be Null");
        }

        try {
            validateId(restaurant.id());
            validateLocation(restaurant.location());
        } catch (Exception e) {
            throw new ValidationException(String.format("Restaurant Validation Exception with ID: %s and reason: %s", restaurant.id(), e.getMessage()));
        }
    }

    public static void validateCustomer(Customer customer) throws NullPointerException, ValidationException {

        if(Objects.isNull(customer)) {
            throw new NullPointerException("Customer cannot be null");
        }

        try {
            validateId(customer.id());
            validateLocation(customer.location());
        } catch (ValidationException e) {
            throw new ValidationException(String.format("Customer Validation Exception with ID: %s and reason: %s", customer.id(), e.getMessage()));
        }
    }

    public static void validateId(String id) throws NullPointerException {
        if(Objects.isNull(id) || id.isBlank()) {
            throw new NullPointerException("Id cannot be Null or Empty");
        }
    }

    public static void validateLocation(Location location) throws NullPointerException, ValidationException {
        if(Objects.isNull(location)) {
            throw new ValidationException("Value cannot be null");
        }

        validateLatitude(location.latitude());
        validateLongitude(location.longitude());
    }

    private static void validateLatitude(Double latitude) throws NullPointerException, ValidationException {

        if(Objects.isNull(latitude)) {
            throw new ValidationException("Latitude cannot be null");
        }

        if(latitude < -90 || latitude > 90) {
            throw new ValidationException("Latitude range cannot exceed -90 to 90 degrees");
        }
    }

    private static void validateLongitude(Double longitude) throws NullPointerException, ValidationException {

        if(Objects.isNull(longitude)) {
            throw new ValidationException("Longitude cannot be null");
        }

        if(longitude < -180 || longitude > 180) {
            throw new ValidationException("Longitude range cannot exceed -180 to 180 degrees");
        }
    }
}
