package dispenser;

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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author <a href="mailto:arnaud.durand@unifr.ch">Arnaud Durand</a>
 * @date 04.2017
 * 
 */
public abstract class DispenserTest {

    public Dispenser<String> as;
    public DispenserFactory dispenserFactory = DispenserFactory.getInstance();

    @Test
    public void emptyTest() {
        assertTrue(as.isEmpty());
    }
    
}