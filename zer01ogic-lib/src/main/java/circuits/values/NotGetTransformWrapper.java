package circuits.values;

public class NotGetTransformWrapper extends ValueWrapper<Integer> {

    @Override
    public Integer getValue() {
        return ~value.getValue();
    }

    @Override
    public Class getValueType() {
        return Integer.class;
    }
}
