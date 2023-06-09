package main.entities;

/**
 * Traffic light entity
 * <p>
 *
 * @since 2023-06-07
 */
public class TrafficLight
{

	// green - true, red - false
	private Boolean state;

	public TrafficLight(Boolean initialState)
	{
		state = initialState;
	}

	public Boolean isGreen()
	{
		return state;
	}

	public void changeState()
	{
		state = !state;
	}

	@Override
	public String toString()
	{
		return state ? "GREEN" : "RED";
	}
}
