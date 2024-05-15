package dispenser;

public class EmptyDispenserException extends Exception
{

	/**
	 * 
	 */
	private static final long	serialVersionUID	= -7096924605732575640L;

	public EmptyDispenserException()
	{
	}

	public EmptyDispenserException(String msg)
	{
		super(msg);
	}

}
