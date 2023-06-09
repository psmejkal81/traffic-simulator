package main;

import main.events.IEvent;

import java.util.Comparator;
import java.util.LinkedList;

/**
 * Event queue controller
 * <p>
 *
 * @since 2023-06-09
 */
public class EventQueueController
{

	private final LinkedList<IEvent> eventQueue = new LinkedList<>();

	/*
	 Add element and re-sort the queue 
	 */
	public void addEvent(IEvent event)
	{
		// add event to the queue
		eventQueue.addLast(event);
		// sort events in the queue by the execution time (lower execution time is placed on top)
		eventQueue.sort(Comparator.comparingInt(IEvent::getExecutionTime));
	}

	/*
	 Remove and return top element from the queue
	 */
	public IEvent popEvent()
	{
		return eventQueue.pop();
	}

	/*
	 Return top element of the queue
	 */
	public IEvent getFirstEvent()
	{
		return eventQueue.getFirst();
	}

	/*
	Return count of elements in the queue
	 */
	public int getQueueSize()
	{
		return eventQueue.size();
	}
}
