package main.events;

import main.Simulation;
import main.entities.Road;
import main.entities.Vehicle;

/**
 * Event responsible for placing car on the road
 * <p>
 *
 * @since 2023-06-07
 */

public final class NewCarEvent extends AbstractEvent
{

	private final Road road;
	private final Vehicle newVehicle;

	public NewCarEvent(Simulation simulation, Road road, Vehicle newVehicle)
	{
		super(simulation);
		this.road = road;
		this.newVehicle = newVehicle;
		setExecutionTime(simulation.getRunTime() + road.newVehicleInterval());
	}

	@Override
	public void execute()
	{
		// put new vehicle on the road
		road.enterNewVehicle(newVehicle);
		// register next event
		getSimulation().getEventQueueController().addEvent(new NewCarEvent(getSimulation(), road, newVehicle));
	}

}
