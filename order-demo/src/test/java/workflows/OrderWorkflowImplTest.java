package workflows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ibm.activities.OrderActivitiesImpl;
import com.ibm.models.Order;
import com.ibm.workflows.OrderWorkflow;
import com.ibm.workflows.OrderWorkflowImpl;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.testing.TestWorkflowEnvironment;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class OrderWorkflowImplTest {

    private TestWorkflowEnvironment testEnv;
    private Worker worker;
    private WorkflowClient client;

    // Set up the test workflow environment
    @BeforeEach
    public void setUp() {
        testEnv = TestWorkflowEnvironment.newInstance();
        WorkerFactory factory = testEnv.getWorkerFactory();
        worker = testEnv.newWorker("ORDER_TASK_QUEUE");
        // Register your workflow implementations
        worker.registerWorkflowImplementationTypes(OrderWorkflowImpl.class);
        worker.registerActivitiesImplementations(new OrderActivitiesImpl());

        factory.start();
        client = testEnv.getWorkflowClient();
    }

    // Clean up test environment after tests are completed
    @AfterEach
    public void tearDown() {
        testEnv.close();
    }

    @Test
    public void testActivityImpl() {
        Order demoOrder = new Order();
        demoOrder.setOrderId("order-1");
        demoOrder.setCardNumber("1239459612508400");
        demoOrder.setClientName("Ximena Cervantes");
        OrderWorkflow workflow = client.newWorkflowStub(OrderWorkflow.class, WorkflowOptions.newBuilder()
                                        .setTaskQueue("ORDER_TASK_QUEUE").build());
        WorkflowClient.start(workflow::processOrder, demoOrder);
    }
}
