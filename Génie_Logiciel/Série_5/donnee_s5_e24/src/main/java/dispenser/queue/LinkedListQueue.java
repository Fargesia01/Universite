package dispenser.queue;

import dispenser.Dispenser;

/**
 * The <code>LinkedListQueue</code> class represents a last-in-first-out
 * (LIFO) stack implemented with a linked list.
 * 
 * @author Andreas Ruppen
 */
public class LinkedListQueue<E> implements Dispenser<E>
{

	/**
	 * The first node of the linked list.
	 */
	private Node<E>	first;

	/**
	 * The last node of the linked list.
	 */
	private Node<E>	last;

	/**
	 * Creates an empty <code>LinkedListQueue</code>.
	 */
	public LinkedListQueue()
	{
		Node<E> newNode = new Node<E>();
		first = newNode;
		last = newNode;
		first.item = null;
		first.next = null;
	}

	/**
	 * Puts an item onto the top of this stack.
	 * 
	 * @param item
	 *            the item to be put onto this stack.
	 */
	public void put(E item)
	{
		Node<E> newNode = new Node<E>();
		last.item = item;
		last.next = newNode;
		newNode.item = null;
		newNode.next = null;
		last = newNode;
	}

	public void slowPut(E item)
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
		E oldItem = first.item;
		first = first.next;
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
		return first.item;
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

	/**
	 * A private class that represents a list node.
	 */
	private static class Node<E>
	{
		public E		item;

		public Node<E>	next;
	}
}
