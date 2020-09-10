package oop.ex6.component;

import oop.ex6.classification.Values;

import java.util.HashMap;
import java.util.Map;

public enum VariableType {

    INT("int", Values.INT.toString()),
    DOUBLE("double", Values.DOUBLE.toString()),
    STRING("String", Values.STRING.toString()),
    BOOLEAN("boolean", Values.BOOLEAN.toString()),
    CHAR("char", Values.CHAR.toString()),
    REFERENCE("reference", Values.VARIABLE_NAME.toString());

    private static final Map<String, VariableType> map = new HashMap<>();

    private final String type;
    private final String regex;

    static {
        for (VariableType v: values()){
            map.put(v.name(), v);
        }
    }

    VariableType(String type, String regex) {
        this.type = type;
        this.regex = regex;
    }

    public String getTypeRegex(){
        return regex;
    }

    public static VariableType getType(String type){return map.get(type);}
}
