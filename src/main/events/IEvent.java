package main.events;

/**
 * Event interface
 * <p>
 *
 * @since 2023-06-07
 */
public interface IEvent
{

	void execute();

	int getExecutionTime();
}
