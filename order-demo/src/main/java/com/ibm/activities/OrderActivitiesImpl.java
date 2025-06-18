package com.ibm.activities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderActivitiesImpl implements OrderActivities{
private static final Logger logger = LoggerFactory.getLogger(OrderActivitiesImpl.class);

    @Override
    public boolean checkInventory(String orderId) {
        //TODO: Add the necessary code to validate the inventory
        System.out.println("Checking inventory for the order: " + orderId);
        logger.info("Checking inventory for the order: " + orderId);
        return true;
    }

    @Override
    public void chargeCard(String cardNumber) {
        //TODO: Add the necessary code to make the card charge
        System.out.println("Charging card: " + cardNumber);
        logger.info("Charging card: " + cardNumber);
         }

    @Override
    public void sendConfirmation(String orderId) {
        //TODO: Add the necessary code to send the confirmation
        System.out.println("Confirmation sent for the order: " + orderId);
        logger.info("Confirmation sent for the order: " + orderId);
    }
}
