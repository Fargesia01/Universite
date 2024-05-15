package dispenser.queue;

import dispenser.Dispenser;
import dispenser.stack.StackOverflowException;

/**
 * The <code>ArrayCircularQueue</code> class represents a first-in-first-out
 * (FIFO) circular queue implemented with an array.
 * 
 * @author Andreas Ruppen
 */
public class ArrayCircularQueue<E> implements Dispenser<E>
{

	/**
	 * A constant holding the maximum number of items a queue can have.
	 */
	private static int	MAX_ITEM	= 4;

	/**
	 * The array that holds the items.
	 */
	private E[]			items;

	/**
	 * The index of the first item in the queue.
	 */
	private int			first;

	/**
	 * The index of the last item in the queue.
	 */
	private int			last;

	/**
	 * Creates an empty <code>ArrayCircularQueue</code>.
	 */
	public ArrayCircularQueue()
	{
		items = (E[]) new Object[MAX_ITEM];
		first = 0;
		last = 0;
	}

	/**
	 * Puts an item into this queue.
	 * 
	 * @param item
	 *            the item to be put into this queue.
	 */
	public void put(E item) throws QueueOverflowException
	{
		if (last == first && items[first] != null)
		{
			throw new QueueOverflowException("The queue is full: last is "
					+ last + " and first is " + first);
		}
		items[last] = item;
		last = last + 1;
		if (last > MAX_ITEM - 1)
		{
			last = 0;
		}
	}

	public void slowPut(E item) throws QueueOverflowException
	{
		// try {
		// Thread.sleep(10);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		put(item);
	}

	/**
	 * Removes the head of this queue.
	 * 
	 * @return the item that was removed.
	 */
	public E remove() throws EmptyQueueException
	{
		if (this.isEmpty())
		{
			throw new EmptyQueueException();
		}
		E oldItem = items[first];
		items[first] = null; // Will be garbage collected...
		first++;
		if (first > MAX_ITEM - 1)
		{
			first = 0;
		}
		return oldItem;
	}

	/**
	 * Returns the item at the head of this queue without removing it.
	 * 
	 * @return the item at the head of this queue.
	 */
	public E item() throws EmptyQueueException
	{
		if (this.isEmpty())
		{
			throw new EmptyQueueException();
		}
		return items[first];
	}

	/**
	 * Tests if this queue is empty.
	 * 
	 * @return <code>true</code> if and only if the queue has no items;
	 *         <code>false</code> otherwise.
	 */
	public boolean isEmpty()
	{
		return first == last && items[first] == null;
	}
}
