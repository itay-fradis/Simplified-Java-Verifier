package oop.ex6.component;

import java.util.HashMap;

/**
 * enum of different values.
 */
public enum VariableType {

    // Variable value patterns
    INT("int", "^([-]?[1-9]\\d*|0)$"),
    DOUBLE("double", "(^([-]?[1-9]\\d*|0)(\\.\\d+))$" + "|" + INT.getRegex()),
    STRING("String", "(\".*\")"),
    BOOLEAN("boolean", "(^true$|^false$)" + "|" + INT.getRegex() + "|" + DOUBLE.getRegex()),
    CHAR("char", "('.{1}')");

    /** the values's regex */
    private final String value;
    private final String name;

    /**
     * constructor
     * @param value value's regex
     */
    VariableType(final String name,final String value){
        this.name = name;
        this.value = value;
    }

    /**
     * @return the regex which holds the value private field
     */
    public String getRegex(){
        return value;
    }

    /**
     *
     * @param name
     * @return
     */
    public static VariableType getType(String name){
        return variablesRegex.get(name);
    }

    /***/
    private final static HashMap<String, VariableType> variablesRegex = new HashMap<>();

    /**
     *
     */
    static {
        for (VariableType v: VariableType.values()){
            variablesRegex.put(v.name, v);
        }
    }


}
