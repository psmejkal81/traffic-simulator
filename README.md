# traffic-simulator
Simple traffic simulator with one junction (4 roads), traffic ligts and arriving and leaving cars. 

## Execution
Just compile and start `TrafficSimulator#main` method

## Project structure
* class `TrafficSimulator` is main runable class
* class `Simulation` is responsible for initialization and execution of simulation based on provided configuration
* class `EventQeueController` is responsible for management of event queue
* class `Configuration` holds static configuration for simulation. This could be replaced with some configuration loader e.g. from JSON configuration file. Also can be tranformed from static methods to singleton class.

* package `entities` contains all elementary objects such as traffic light, vehicle,...
* package `events` contains all events which can be processed in event queue

## Implementation details
Simulation is initialized with 4 roads, two traffic lights with different inital state (each traffic light can serve for two opposite roads). During initialization there are also registered following events into event queue:
* switch traffic light via `TrafficLightEvent`
* arrival of new car on the road via `NewVehicleEvent`
* car leaving the junction via `LeaveJunctionEvent`

Queue is implemented as discrete simulation where events are processed one by one every second. For each new event is calculated execution time based on the provided configuration. Events are sorted in the queue by its execution time and processed when their execution time equals to current runtime. Events with the same execution time are executed during one iteration (in given runtime second)

Events implements `execute` method to perform appropriate behavior which is followed by registration of new event into the queue. Simulation is running (processing queue) until the queue is empty of runtime < `MAX_RUN_TIME` value.
