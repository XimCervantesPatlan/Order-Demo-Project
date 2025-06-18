
# Order Demo

This project was created to show the Workflows, Activities and Workers performance in Temporal using a simple order demo with Java.

You can change the following configurations in the pom.xml file:
- Maven Compiler Version
- Temporal Version
- JUnit Version
- QOS Logback Version




## Run Locally

Clone the project

```bash
  git clone https://github.com/XimCervantesPatlan/Order-Demo-Project.git
```

Go to the project directory

```bash
  cd order-demo
```

Start the local server

```bash
  temporal server start-dev --ui-port 8080
```

In other terminal run the Temporal Worker, it will execute the OrderWorkflow and its OrderActivities with the order-tasks queue

```bash
  mvn compile exec:java -Dexec.mainClass="OrderWorker"
```
In a third terminal run the OrderStarter to initiate the Workflow with the parameters specified in the OrderStarter class 

```bash
  mvn compile exec:java -Dexec.mainClass="OrderStarter"
```

Check the status of your workflows in http://localhost:8080/.
## Running Tests

To run tests, use the class OrderWorkflowImplTest.
