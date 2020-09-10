package oop.ex6.component;

import java.util.HashMap;
import java.util.Map;

public enum VariableType {

    INT("int"),

    String("String");

    private static final Map<String, VariableType> map = new HashMap<>();

    private final String type;

    static {
        for (VariableType v: values()){
            map.put(v.name(), v);
        }
    }

    VariableType(final String type) {this.type = type;}

    public static VariableType getType(String type){return map.get(type);}
}
