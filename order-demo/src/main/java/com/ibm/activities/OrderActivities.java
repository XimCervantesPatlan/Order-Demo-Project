package com.ibm.activities;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface()
public interface OrderActivities {
    @ActivityMethod
    public boolean checkInventory(String orderId);
    
    @ActivityMethod
    public void chargeCard(String cardId);
    
    @ActivityMethod
    public void sendConfirmation(String orderId);
}
