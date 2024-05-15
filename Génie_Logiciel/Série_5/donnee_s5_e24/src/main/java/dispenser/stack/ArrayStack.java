package dispenser.stack;

import dispenser.Dispenser;

/**
 * The <code>ArrayStack</code> class represents a last-in-first-out (LIFO)
 * stack implemented with an array.
 * 
 * @author Andreas Ruppen
 */
public class ArrayStack<E> implements Dispenser<E>
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
	 * The current number of items in the stack.
	 */
	private int			count;

	/**
	 * Creates an empty <code>ArrayStack</code>.
	 */
	public ArrayStack()
	{
		items = (E[]) new Object[MAX_ITEM];
		count = 0;
	}

	/**
	 * Puts an item onto the top of this stack.
	 * 
	 * @param item
	 *            the item to be put onto this stack.
	 */
	public void put(E item) throws StackOverflowException
	{
		if (count >= MAX_ITEM)
		{
			throw new StackOverflowException("The stack is full: " + count
					+ " items.");
		}
		items[count] = item;
		count++;
	}

	public void slowPut(E item) throws StackOverflowException
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
	public E remove() throws EmptyStackException
	{
		if (this.isEmpty())
		{
			throw new EmptyStackException("the Stack is empty");
		}
		E oldItem = (E) items[count - 1];
		items[count - 1] = null; // Will be garbage collected...
		count = count - 1;
		return oldItem;
	}

	/**
	 * Returns the item at the top of this stack without removing it.
	 * 
	 * @return the item at the top of this stack.
	 */
	public E item() throws EmptyStackException
	{
		if (this.isEmpty())
		{
			throw new EmptyStackException("the Stack is empty");
		}
		return (E) items[count - 1];
	}

	/**
	 * Tests if this stack is empty.
	 * 
	 * @return <code>true</code> if and only if the stack has no items;
	 *         <code>false</code> otherwise.
	 */
	public boolean isEmpty()
	{
		return count == 0;
	}
}
