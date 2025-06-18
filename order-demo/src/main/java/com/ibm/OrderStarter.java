package com.ibm;

import com.ibm.models.Order;
import com.ibm.workflows.OrderWorkflow;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.serviceclient.WorkflowServiceStubs;

public class OrderStarter {
    public static void main(String[] args) throws Exception {

        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(service);
        //Values for orderId, cardNumber and clientName must be changed   
        Order demoOrder = new Order();
        demoOrder.setOrderId("order-1");
        demoOrder.setCardNumber("1232987645670900");
        demoOrder.setClientName("Ximena");

        WorkflowOptions options = WorkflowOptions.newBuilder()
                    .setWorkflowId("order-workflow")
                    .setTaskQueue("order-tasks")
                    .build();
       
        OrderWorkflow workflow = client.newWorkflowStub(OrderWorkflow.class, options);

        workflow.processOrder(demoOrder);

        String workflowId = WorkflowStub.fromTyped(workflow).getExecution().getWorkflowId();

        System.out.println("workflowId: " + workflowId + " for the orderId: " + demoOrder.getOrderId());
        System.exit(0);
    }
}
