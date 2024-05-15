package dispenser.stack;

import dispenser.Dispenser;

/**
 * The <code>LinkedListStack</code> class represents a last-in-first-out
 * (LIFO) stack implemented with a linked list.
 * 
 * @author Andreas Ruppen
 */
public class LinkedListStack<E> implements Dispenser<E>
{

	/**
	 * The head node of the linked list.
	 */
	private Node<E>	head;

	/**
	 * Creates an empty <code>LinkedListStack</code>.
	 */
	public LinkedListStack()
	{
		head = new Node<E>();
		head.next = head;
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
		newNode.item = item;
		newNode.next = head.next;
		head.next = newNode;
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
			throw new EmptyStackException();
		}
		E oldItem = head.next.item;
		head.next = head.next.next;
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
			throw new EmptyStackException();
		}
		return head.next.item;
	}

	/**
	 * Tests if this stack is empty.
	 * 
	 * @return <code>true</code> if and only if the stack has no items;
	 *         <code>false</code> otherwise.
	 */
	public boolean isEmpty()
	{
		return head.next == head;
	}

	/**
	 * A private class that represents a list node.
	 */
	private static class Node<E>
	{
		public E		item;

		public Node<E>	next;
	}

	public void slowPut(E msg)
	{
		// try
		// {
		// Thread.sleep(20);
		// } catch (InterruptedException e)
		// {
		// e.printStackTrace();
		// }
		put(msg);
	}
}
