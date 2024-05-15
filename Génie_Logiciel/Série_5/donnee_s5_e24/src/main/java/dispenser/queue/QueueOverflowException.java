package dispenser.queue;

import dispenser.DispenserOverflowException;

public class QueueOverflowException extends DispenserOverflowException
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 998213150958021553L;

	public QueueOverflowException(String msg)
	{
		super(msg);
	}

}
