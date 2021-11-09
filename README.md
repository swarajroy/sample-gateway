Sample-Gateway

- A reactive web application consuming a reactive endpoint from the sample-service
- It is just a proxy but using the reactive WebClient to consume endpoints in sample-service so as
  to keep the stack reactive [Async/Non-Blocking]
- For Integration Testing/ Acceptance Testing we are using Cucumber where the feature and the step
  definition files is consuming a contract exposed from sample-service there by adhering to
  consumer-driven-contract-testing