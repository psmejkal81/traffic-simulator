package main.events;

import main.Simulation;

/**
 * Abstract event
 * <p>
 *
 * @since 2023-06-07
 */

public abstract class AbstractEvent implements IEvent
{

	private int executionTime;
	private final Simulation simulation;

	protected AbstractEvent(Simulation simulation)
	{
		this.simulation = simulation;
	}

	@Override
	public abstract void execute();

	protected void setExecutionTime(int executionTime)
	{
		this.executionTime = executionTime;
	}

	@Override
	public int getExecutionTime()
	{
		return executionTime;
	}

	public Simulation getSimulation()
	{
		return simulation;
	}
}
