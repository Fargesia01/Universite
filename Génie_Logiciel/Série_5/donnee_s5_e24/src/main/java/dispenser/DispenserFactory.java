package dispenser;

/**
 * A factory that creates a dispenser implemented as a stack or a queue.
 * 
 * @author Andreas Ruppen
 */
public class DispenserFactory {
	
	private static DispenserFactory instance;

	/**
	 * The class to be used for stack instances.
	 */
	private static Class stackClass = dispenser.stack.ArrayStack.class;

	/**
	 * The class to be used for queue instances.
	 */
	private static Class queueClass = dispenser.queue.ArrayCircularQueue.class;

	/**
	* Protected Constructor. Necessary for the singleton Pattern.
	*/
	protected DispenserFactory()
	{

	}
	
	public static DispenserFactory getInstance() {
        if (instance == null) {
            instance = new DispenserFactory();
        }
        return instance;
	}

	/**
	 * Creates and returns a dispenser implemented as a stack.
	 * 
	 * @return the created stack
	 */
	public Dispenser createStack() {
		Dispenser dispenser = null;
		try {
			dispenser = (Dispenser) stackClass.newInstance();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}

		return dispenser;
	}

	/**
	 * Creates and returns a dispenser implemented as a queue.
	 * 
	 * @return the created queue
	 */
	public Dispenser createQueue() {
		Dispenser dispenser = null;
		try {
			dispenser = (Dispenser) queueClass.newInstance();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		}

		return dispenser;
	}
	
	/**
	 * Sets the class to be used for stack instances.
	 * 
	 * @param className the fully qualified name of the stack class.
	 */
	public void setStackClass(String className) {
		try {
            stackClass = Class.forName(className);
		} catch (ClassNotFoundException ex) {
            ex.printStackTrace();
		}
	}
	
	/**
	 * Sets the class to be used for queue instances.
	 * 
	 * @param className the fully qualified name of the queue class.
	 */
	public void setQueueClass(String className) {
        try {
            queueClass = Class.forName(className);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
	}
}
