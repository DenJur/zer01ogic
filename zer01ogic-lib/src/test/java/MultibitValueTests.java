import circuits.MultibitValue;

import interfaces.IObservableValue;
import interfaces.IObserver;
import org.junit.Assert;
import org.junit.Test;

public class MultibitValueTests {
    @Test
    public void TestBitSizeTruncation() {
        MultibitValue tested = new MultibitValue();
        tested.setValue(10);
        Assert.assertEquals("Expected to truncate leading bits leaving 0", 0, tested.getValue().intValue());
        tested.setValue(1);
        Assert.assertEquals("Expected value to be unchanged (1)", 1, tested.getValue().intValue());
        tested.setValue(7);
        Assert.assertEquals("Expected to truncate leading bits leaving 1", 1, tested.getValue().intValue());
        tested = new MultibitValue(7, (byte) 2);
        Assert.assertEquals("Expected to truncate leading bits leaving 3", 3, tested.getValue().intValue());
    }

    @Test
    public void TestObserverRegister() {
        MultibitValue tested = new MultibitValue(0);
        TestObserver observer1=new TestObserver();
        TestObserver observer2=new TestObserver();
        tested.registerObserver(observer1);
        tested.registerObserver(observer2);

//        tested.setValue(0);
//        Assert.assertFalse("Value didn't change (observer1)", observer1.called);
//        Assert.assertFalse("Value didn't change (observer2)", observer2.called);
//        tested.setValue(10);
//        Assert.assertFalse("Value didn't change truncate (observer1)", observer1.called);
//        Assert.assertFalse("Value didn't change truncate (observer2)", observer2.called);
//        tested.setValue(1);
//        Assert.assertTrue("Value changed (observer1)", observer1.called);
//        Assert.assertTrue("Value changed (observer2)", observer2.called);
        // TODO restore when circuits are added
    }

    @Test
    public void TestObserverDeregister() {
        MultibitValue tested = new MultibitValue(0);
        TestObserver observer1=new TestObserver();
        TestObserver observer2=new TestObserver();
        tested.registerObserver(observer1);
        tested.registerObserver(observer2);
        tested.deregisterObserver(observer1);

        tested.setValue(1);
//        Assert.assertFalse("Value changed deregistered (observer1)", observer1.called);
//        Assert.assertTrue("Value changed (observer2)", observer2.called);
        // TODO restore when circuits are added
    }

    private class TestObserver implements IObserver {
        public boolean called = false;

        @Override
        public void update() {
            called = true;
        }
    }
}
