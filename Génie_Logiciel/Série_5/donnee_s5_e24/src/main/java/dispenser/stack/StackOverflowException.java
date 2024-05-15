package dispenser.stack;

import dispenser.DispenserOverflowException;

public class StackOverflowException extends DispenserOverflowException
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3208454863917870832L;

	public StackOverflowException(String msg)
	{
		super(msg);
	}

}
