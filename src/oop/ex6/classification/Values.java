package oop.ex6.classification;

/**
 * enum of different values.
 */
public enum Values {

    // Variable value patterns
    INT("^([-]?[1-9]\\d*|0)$"),
    DOUBLE("^([-]?[1-9]\\d*|0)(\\.\\d+)?$"),
    STRING("\".*\""),
    BOOLEAN("^true$|^false$"),
    CHAR("'.{1}'"),
    VARIABLE_NAME("(_\\w+)|([a-zA-Z]\\w*)");

    /** the values's regex */
    private final String value;

    /**
     * constructor
     * @param value value's regex
     */
    Values(final String value){
        this.value = value;
    }

    /**
     * @return the regex which holds the value private field
     */
    @Override
    public String toString(){
        return value;
    }


}
