package simulation.values;

/**
 * Modes that value transformer can operate in.
 * GET - transformation is performed when value is accessed
 * SET - transformation is performed when value is set
 * BOT - transformation is performed both when value is accessed and when it is set
 */
public enum TransformerMode {
    GET,
    SET,
    BOTH
}
