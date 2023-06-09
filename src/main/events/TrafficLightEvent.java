package main.events;

import main.Configuration;
import main.Simulation;
import main.entities.TrafficLight;

/**
 * Event responsible for switching traffic light
 * <p>
 *
 * @since 2023-06-07
 */

public final class TrafficLightEvent extends AbstractEvent
{

	private final TrafficLight trafficLight;
	// specify whether is running long or short interval
	private final Boolean longInterval;

	// initialization constructor
	public TrafficLightEvent(Simulation simulation, TrafficLight trafficLight)
	{
		this(simulation, trafficLight, true);
	}

	public TrafficLightEvent(Simulation simulation, TrafficLight trafficLight, boolean longInterval)
	{
		super(simulation);
		this.trafficLight = trafficLight;
		this.longInterval = longInterval;
		setExecutionTime(simulation.getRunTime() + (longInterval ? Configuration.LONG_INTERVAL : Configuration.SHORT_INTERVAL));
	}

	@Override
	public void execute()
	{
		// switch the traffic light
		trafficLight.changeState();
		// register next event with the opposite running interval
		getSimulation().getEventQueueController().addEvent(new TrafficLightEvent(getSimulation(), trafficLight, !longInterval));
	}
}
