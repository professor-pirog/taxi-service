package com.pirogsoft.taxiservice.service.price;

import com.pirogsoft.taxiservice.model.car.Car;
import com.pirogsoft.taxiservice.model.trip.Point;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PifagorPriceStrategy implements PriceStrategy {

    public static final double PRICE_FACTOR = 2000;

    @Override
    public BigDecimal calculatePrice(Point departure, Point destination, Car car) {
        double distance = Math.sqrt(
                Math.pow(departure.getX() - destination.getX(), 2)
                        + Math.pow(departure.getY() - destination.getY(), 2)
        );
        double price = distance * PRICE_FACTOR * car.getCarCategory().getFactor();
        return BigDecimal.valueOf(price).setScale(2, RoundingMode.DOWN);
    }
}
