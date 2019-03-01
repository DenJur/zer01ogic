package circuits.values;

public class NotSetTransformWrapper extends ValueWrapper<Integer> {

    @Override
    public void setValue(Integer newValue) {
        value.setValue(~newValue);
    }

    @Override
    public Class getValueType() {
        return Integer.class;
    }
}
