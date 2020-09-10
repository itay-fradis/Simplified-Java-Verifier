package oop.ex6.component;

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
    public Component(String type, String name, String isFinal, String value){
        if (isFinal != null)
            this.isFinal = true;
        this.type = VariableType.getType(type);
        this.name = name;
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