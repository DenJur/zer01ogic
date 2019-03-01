package circuits.values;

public class NotTransformWrapper extends ValueWrapper<Integer> {

    @Override
    public Integer getValue() {
        return value.getValue();
    }

    @Override
    public void setValue(Integer newValue) {
        value.setValue(~newValue);
    }

    @Override
    public Class getValueType() {
        return Integer.class;
    }
}
