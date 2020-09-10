package oop.ex6.component;

import oop.ex6.classification.VariableType;

/**
 * an abstract class represents a component
 */
public class Component {
    /** type of component */
    private VariableType type = null;
    /** name of component */
    private final String name;
    /** is component final*/
    private boolean isFinal = false;
    /***/
    private String value;


    /**
     * constructor for component
     * @param type - type
     * @param name  - name
     * @param isFinal - is final
     */
    public Component(VariableType type, String name, boolean isFinal, String value){
        this.name = name;
        this.isFinal = isFinal;
        this.type = type;
        this.value = value;
    }

    /**
     * @return type
     */
    public VariableType getType() {
        return type;
    }

    /**
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * @return is final
     */
    public boolean isFinal() {
        return isFinal;
    }
}