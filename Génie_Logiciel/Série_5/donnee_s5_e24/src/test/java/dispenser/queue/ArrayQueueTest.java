package dispenser.queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import dispenser.Dispenser;
import dispenser.DispenserFactory;
import dispenser.DispenserOverflowException;
import dispenser.EmptyDispenserException;

/**
 * @author <a href="mailto:arnaud.durand@unifr.ch">Arnaud Durand</a>
 * @date 04.2017
 * 
 */
public class ArrayQueueTest extends QueueTest {

    @BeforeEach
    public void createQueue() {
        as = new ArrayCircularQueue<String>();
        dispenserFactory.setQueueClass("dispenser.queue.ArrayQueue");
        as = dispenserFactory.createQueue();
    }
    
    @Test
    public void overFlowTest() throws DispenserOverflowException {
        assertThrows(QueueOverflowException.class, () -> {
            for (int i = 0; i < 200; i++) {
                as.put("element" + i);
                System.out.println("dispenser.queue.ArrayQueueTest.overFlowTest()");
            }
        });
    }

}
