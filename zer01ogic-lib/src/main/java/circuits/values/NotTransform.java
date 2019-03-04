package circuits.values;

public class NotTransform extends ValueTransformerBase<Integer> {

    public NotTransform(TransformerMode mode) {
        super(mode);
    }

    @Override
    public Integer getValue() {
        switch (mode) {
            case BOTH:
            case GET:
                return ~value.getValue();
            case SET:
            default:
                return value.getValue();

        }
    }

    @Override
    public void setValue(Integer newValue) {
        switch (mode) {
            case SET:
            case BOTH:
                value.setValue(~newValue);
                break;
            case GET:
            default:
                value.setValue(newValue);
                break;

        }
    }

    @Override
    public Class<Integer> getValueType() {
        return Integer.class;
    }
}
