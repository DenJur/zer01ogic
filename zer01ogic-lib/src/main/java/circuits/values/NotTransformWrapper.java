package circuits.values;

public class NotTransformWrapper extends ValueWrapper<Integer> {

    public NotTransformWrapper(TransformerMode mode) {
        super(mode);
    }

    @Override
    public void setValue(Integer newValue) {
        if(mode==TransformerMode.SET) value.setValue(~newValue);
        else value.setValue(newValue);
    }

    @Override
    public Integer getValue() {
        if(mode==TransformerMode.GET) return ~value.getValue();
        else return value.getValue();
    }

    @Override
    public Class<Integer> getValueType() {
        return Integer.class;
    }
}
