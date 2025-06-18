package com.ibm;

import com.ibm.activities.OrderActivitiesImpl;
import com.ibm.workflows.OrderWorkflowImpl;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class OrderWorker {
    public static void main(String[] args){
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();

        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);

        Worker worker = factory.newWorker("order-tasks");
        worker.registerWorkflowImplementationTypes(OrderWorkflowImpl.class);
        worker.registerActivitiesImplementations(new OrderActivitiesImpl());
        factory.start();
        
        if(factory.isStarted())
        System.out.println("Worker started successfully: " + factory.getWorker("order-tasks"));
    }
}
