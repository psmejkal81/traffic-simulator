package main.events;

import main.Configuration;
import main.Simulation;
import main.entities.Road;

/**
 * Event responsible for a vehicle leaving junction
 * <p>
 *
 * @since 2023-06-07
 */

public final class LeaveJunctionEvent extends AbstractEvent
{

	private final Road road;

	public LeaveJunctionEvent(Simulation simulation, Road road)
	{
		super(simulation);
		this.road = road;
		setExecutionTime(simulation.getRunTime() + Configuration.LEAVE_JUNCTION_DELAY);
	}

	@Override
	public void execute()
	{
		if (road.getTrafficLight().isGreen() && !road.getVehicles().isEmpty())
		{
			// remove vehicle from the waiting vehicles queue
			road.leaveFirstVehicle();
			// increment counter of vehicles that left the junction
			road.incrementLeftVehicles();
		}
		// register next event
		getSimulation().getEventQueueController().addEvent(new LeaveJunctionEvent(getSimulation(), road));
	}

}
