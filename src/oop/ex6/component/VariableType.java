package oop.ex6.component;

import oop.ex6.classification.Values;


/**
 * enum represent a reserved keyword of variable type
 */
public enum VariableType { //change

    INT("int", Values.INT.toString()),
    DOUBLE("double", Values.DOUBLE.toString()),
    STRING("String", Values.STRING.toString()),
    BOOLEAN("boolean", Values.BOOLEAN.toString()),
    CHAR("char", Values.CHAR.toString());

    /** the variable type */
    private final String type;

    /** the regex of a value of the specific type */
    private final String regex;

    /**
     * enum constructor
     * @param type type of variableType
     * @param regex regex of variableType
     */
    VariableType(String type, String regex) {
        this.type = type;
        this.regex = regex;
    }

    /**
     * @return regex of variableType
     */
    public String getTypeRegex(){
        return regex;
    }

    /**
     * @return type of variableType
     */
    public String getTypeName(){
        return type;
    }

}
