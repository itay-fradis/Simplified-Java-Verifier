package oop.ex6.component;

import java.util.HashMap;

/**
 * enum of the reserved values .
 */
public enum VariableType {

    // Variable value patterns
    INT("int", "^([-]?[1-9]\\d*|0)$"),
    DOUBLE("double", "(^[-]?[\\d]*\\.\\d*)" + "|" + INT.getRegex()),
    STRING("String", "(\".*\")"),
    BOOLEAN("boolean", "(^true$|^false$)"  + "|" + DOUBLE.getRegex()),
    CHAR("char", "('.{1}')");

    /** the value's regex */
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
     * @param name of variable type
     * @return variable type enum
     */
    public static VariableType getType(String name){
        return variablesRegex.get(name);
    }

    /** map that fits between variable type enum and its name */
    private final static HashMap<String, VariableType> variablesRegex = new HashMap<>();

    /**
     * inits the variableRegex field
     */
    static {
        for (VariableType v: VariableType.values()){
            variablesRegex.put(v.name, v);
        }
    }


}
