package main;

import java.util.Map;

/**
 * Global configuration constants
 * <p>
 *
 * @since 2023-06-08
 */
public class Configuration
{

	// max time the simulation can run
	public static final int MAX_RUN_TIME = 60;
	// time required to leave crossroad
	public static final int LEAVE_JUNCTION_DELAY = 1;
	// traffic lights short interval
	public static final int SHORT_INTERVAL = 3;
	// traffic lights long interval
	public static final int LONG_INTERVAL = 4;

	public enum RoadName
	{
		NORTH,
		SOUTH,
		EAST,
		WEST
	}

	// for simplification the value "vehicles per minute" should be divisor of the 60 to get correct results
	// otherwise the algorithm will generate incorrect count of vehicles due to rounding on integer
	public static final Map<RoadName, Integer> VEHICLES_PER_MINUTE = Map.of(
			RoadName.NORTH, 20,
			RoadName.SOUTH, 30,
			RoadName.EAST, 20,
			RoadName.WEST, 30);
}