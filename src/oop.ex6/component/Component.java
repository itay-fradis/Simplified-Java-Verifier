package oop.ex6.component;

/**
 * an abstract class represents a component
 */
public class Component {
    /** type of component */
    private final VariableType type = null;
    /** name of component */
    private final String name;
    /** is component final*/
    private final boolean isFinal = false;

    /**
     * constructor for component
     * @param t - type
     * @param n  - name
     * @param f - is final
     */
    public Component(String t, String n, String f){
        name = n;
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
