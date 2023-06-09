package main.entities;

import java.util.LinkedList;

/**
 * Road entity with the queue of waiting vehicles
 * <p>
 *
 * @since 2023-06-07
 */
public class Road
{

	private static final int SECONDS_IN_MINUTE = 60;
	// queue of the waiting vehicles
	private LinkedList<Vehicle> vehicles = new LinkedList<Vehicle>();
	private final TrafficLight trafficLight;

	private final int vehiclesPerMinute;

	// count of vehicles that left the road (just for informative purpose)
	private int leftVehicles = 0;

	public Road(int vehiclesPerMinute, TrafficLight trafficLight)
	{
		this.vehiclesPerMinute = vehiclesPerMinute;
		this.trafficLight = trafficLight;
	}

	public int getVehiclesPerMinute()
	{
		return vehiclesPerMinute;
	}

	public LinkedList<Vehicle> getVehicles()
	{
		return vehicles;
	}

	public TrafficLight getTrafficLight()
	{
		return trafficLight;
	}

	public void enterNewVehicle(Vehicle vehicle)
	{
		vehicles.addLast(vehicle);
	}

	public void leaveFirstVehicle()
	{
		vehicles.removeFirst();
	}

	public int newVehicleInterval()
	{
		// calculate new vehicle interval based on the "vehicles per minute" value
		return SECONDS_IN_MINUTE / vehiclesPerMinute;
	}

	public void incrementLeftVehicles()
	{
		leftVehicles++;
	}

	public int getLeftVehicles()
	{
		return leftVehicles;
	}
}
