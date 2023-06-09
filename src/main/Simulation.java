package main;

import main.Configuration.RoadName;
import main.entities.Car;
import main.entities.Road;
import main.entities.TrafficLight;
import main.events.IEvent;
import main.events.LeaveJunctionEvent;
import main.events.NewCarEvent;
import main.events.TrafficLightEvent;

/**
 * Simulation executor class
 * <p>
 *
 * @since 2023-06-07
 */
public class Simulation
{

	// North road
	private Road roadN;
	// South road
	private Road roadS;
	// East road
	private Road roadE;
	// West road
	private Road roadW;

	// instance of traffic lights located in north-south direction 
	private TrafficLight trafficLightNS;
	// instance of traffic lights located in east-west direction
	private TrafficLight trafficLightEW;

	// runtime counter
	private int runTime = 0;

	private final EventQueueController eventQueueController;

	public Simulation()
	{
		eventQueueController = new EventQueueController();
	}

	/*
	Return current runtime
	 */
	public int getRunTime()
	{
		return runTime;
	}

	public EventQueueController getEventQueueController()
	{
		return eventQueueController;
	}

	/*
	Initialized simulation
	 */
	private void initialize(EventQueueController queueController)
	{
		// vehicle that will be placed on the road
		Car car = new Car();

		// traffic lights for the junction
		trafficLightNS = new TrafficLight(true);
		trafficLightEW = new TrafficLight(false);

		roadN = new Road(Configuration.VEHICLES_PER_MINUTE.get(RoadName.NORTH), trafficLightNS);
		roadS = new Road(Configuration.VEHICLES_PER_MINUTE.get(RoadName.SOUTH), trafficLightNS);
		roadE = new Road(Configuration.VEHICLES_PER_MINUTE.get(RoadName.EAST), trafficLightEW);
		roadW = new Road(Configuration.VEHICLES_PER_MINUTE.get(RoadName.WEST), trafficLightEW);

		// register initial event to start switching the traffic lights
		queueController.addEvent(new TrafficLightEvent(this, trafficLightNS));
		queueController.addEvent(new TrafficLightEvent(this, trafficLightEW));

		// register initial event to start placing vehicles on the road
		queueController.addEvent(new NewCarEvent(this, roadS, car));
		queueController.addEvent(new NewCarEvent(this, roadN, car));
		queueController.addEvent(new NewCarEvent(this, roadE, car));
		queueController.addEvent(new NewCarEvent(this, roadW, car));

		// register initial event for leaving the junction
		queueController.addEvent(new LeaveJunctionEvent(this, roadS));
		queueController.addEvent(new LeaveJunctionEvent(this, roadN));
		queueController.addEvent(new LeaveJunctionEvent(this, roadE));
		queueController.addEvent(new LeaveJunctionEvent(this, roadW));

		// print initial state
		printResult();
	}

	public void startSimulation()
	{

		initialize(eventQueueController);

		while (eventQueueController.getQueueSize() > 0 && runTime <= Configuration.MAX_RUN_TIME)
		{
			runTime += 1;

			// if the event execution time equals to current runtime
			// remove event from the queue and execute event code
			while (eventQueueController.getFirstEvent().getExecutionTime() == runTime)
			{
				IEvent event = eventQueueController.popEvent();
				event.execute();
			}

			// generate simulation progress
			printResult();
		}
	}

	private void printResult()
	{
		System.out.printf("Runtime: %1d\t", runTime);
		System.out.printf("Traffic lights North-South: %s, East-West: %s\t", trafficLightNS.toString(), trafficLightEW.toString());
		System.out.printf("North road: waiting [%d], left [%d]\t", roadN.getVehicles().size(), roadN.getLeftVehicles());
		System.out.printf("South road: waiting [%d], left [%d]\t", roadS.getVehicles().size(), roadS.getLeftVehicles());
		System.out.printf("East road: waiting [%d], left [%d]\t", roadE.getVehicles().size(), roadE.getLeftVehicles());
		System.out.printf("West road: waiting [%d], left [%d]", roadW.getVehicles().size(), roadW.getLeftVehicles());
		System.out.println();
	}
}
