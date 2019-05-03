import interfaces.elements.IObservableValue;
import interfaces.elements.IObserver;
import org.junit.jupiter.api.Test;
import simulation.values.MultibitValue;

import static org.junit.jupiter.api.Assertions.*;

public class MultibitValueTests {
    @Test
    public void TestBitSizeTruncation() {
        MultibitValue tested = new MultibitValue();
        tested.setValue(10);
        assertEquals(0, tested.getValue().intValue(), "Expected to truncate leading bits leaving 0");
        tested.setValue(1);
        assertEquals(1, tested.getValue().intValue(), "Expected value to be unchanged (1)");
        tested.setValue(7);
        assertEquals(1, tested.getValue().intValue(), "Expected to truncate leading bits leaving 1");
        tested = new MultibitValue(7, (byte) 2);
        assertEquals(3, tested.getValue().intValue(), "Expected to truncate leading bits leaving 3");
    }

    @Test
    public void TestObserverRegister() {
        MultibitValue tested = new MultibitValue(0);
        TestObserver observer1 = new TestObserver();
        TestObserver observer2 = new TestObserver();
        tested.registerObserver(observer1);
        tested.registerObserver(observer2);

        tested.setValue(0);
        assertFalse(observer1.called, "Value didn't change (observer1)");
        assertFalse(observer2.called, "Value didn't change (observer2)");
        tested.setValue(10);
        assertFalse(observer1.called, "Value didn't change truncate (observer1)");
        assertFalse(observer2.called,"Value didn't change truncate (observer2)");
        tested.setValue(1);
        assertTrue(observer1.called, "Value changed (observer1)");
        assertTrue(observer2.called,"Value changed (observer2)");
    }

    @Test
    public void TestObserverDeregister() {
        MultibitValue tested = new MultibitValue(0);
        TestObserver observer1 = new TestObserver();
        TestObserver observer2 = new TestObserver();
        tested.registerObserver(observer1);
        tested.registerObserver(observer2);
        tested.deregisterObserver(observer1);

        tested.setValue(1);
        assertFalse(observer1.called, "Value changed deregistered (observer1)" );
        assertTrue(observer2.called, "Value changed (observer2)");
        // TODO restore when simulation are added
    }

    private class TestObserver implements IObserver {
        public boolean called = false;

        @Override
        public void update(IObservableValue value) {
            called = true;
        }
    }
}
