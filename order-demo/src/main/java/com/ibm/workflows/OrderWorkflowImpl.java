package com.ibm.workflows;

import java.time.Duration;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import com.ibm.activities.OrderActivities;
import com.ibm.models.Order;

public class OrderWorkflowImpl implements OrderWorkflow{

    private final OrderActivities activities;

    public OrderWorkflowImpl(){
        ActivityOptions options = ActivityOptions.newBuilder()
                .setStartToCloseTimeout(Duration.ofSeconds(10))
                .setScheduleToCloseTimeout(Duration.ofSeconds(15))
                .setRetryOptions(RetryOptions.newBuilder()
                    .setInitialInterval(Duration.ofSeconds(60))
                    .setMaximumAttempts(4)
                    .setBackoffCoefficient(1)
                    .setMaximumInterval(Duration.ofSeconds(30))
                    .build()
                )
                .build();
        
        this.activities = Workflow.newActivityStub(OrderActivities.class, options);
    }
    @Override
    public void processOrder(Order order) {
        String orderId = order.getOrderId();
        String cardNumber = order.getCardNumber();
        boolean existsInventory = this.activities.checkInventory(orderId);
  
        Workflow.getLogger(OrderWorkflowImpl.class).info("Starts process for order: {}", orderId);
        if(!existsInventory){
            Workflow.getLogger(OrderWorkflowImpl.class).warn("No inventory for {}", orderId);
            throw new RuntimeException("No inventory available for " + orderId);
        }
        this.activities.chargeCard(cardNumber);
        this.activities.sendConfirmation(orderId);
    }

    
}
