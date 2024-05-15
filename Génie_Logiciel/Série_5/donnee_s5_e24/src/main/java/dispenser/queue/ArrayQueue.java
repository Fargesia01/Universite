package dispenser.queue;

import dispenser.Dispenser;

/**
 * The <code>ArrayQueue</code> class represents a last-in-first-out (LIFO)
 * stack implemented with an array.
 * 
 * @author Andreas Ruppen
 */
public class ArrayQueue<E> implements Dispenser<E>
{

	/**
	 * A constant holding the maximum number of items a stack can have.
	 */
	private static int	MAX_ITEM	= 100;

	/**
	 * The array that holds the items.
	 */
	private E[]			items;

	/**
	 * The first element in the queue.
	 */
	private int			first;

	/**
	 * The last element in the queue.
	 */
	private int			last;

	/**
	 * Creates an empty <code>ArrayQueue</code>.
	 */
	public ArrayQueue()
	{
		items = (E[]) new Object[MAX_ITEM];
		first = 0;
		last = 0;
	}

	/**
	 * Puts an item onto the top of this stack.
	 * 
	 * @param item
	 *            the item to be put onto this stack.
	 */
	public void put(E item) throws QueueOverflowException
	{
		if (last >= MAX_ITEM)
		{
			throw new QueueOverflowException("The queue is full: " + last
					+ " items.");
		}
		items[last] = item;
		last++;
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
	 * Removes the item at the top of this stack.
	 * 
	 * @return the item that was removed.
	 */
	public E remove() throws EmptyQueueException
	{
		if (this.isEmpty())
		{
			throw new EmptyQueueException();
		}
		E oldItem = (E) items[first];
		items[first] = null; // Will be garbage collected...
		first++;
		return oldItem;
	}

	/**
	 * Returns the item at the top of this stack without removing it.
	 * 
	 * @return the item at the top of this stack.
	 */
	public E item() throws EmptyQueueException
	{
		if (this.isEmpty())
		{
			throw new EmptyQueueException();
		}
		return (E) items[first];
	}

	/**
	 * Tests if this stack is empty.
	 * 
	 * @return <code>true</code> if and only if the stack has no items;
	 *         <code>false</code> otherwise.
	 */
	public boolean isEmpty()
	{
		return first == last;
	}
}
