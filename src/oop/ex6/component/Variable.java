package oop.ex6.component;

/**
 * an abstract class represents a component
 */
public class Variable {
    /** type of component */
    private VariableType type = null;
    /** name of component */
    private final String name;
    /** is component final*/
    private boolean isFinal = false;
    /** value of variable */
    private String value;
    /** flag that indicates if that variable is a method argument*/
    private boolean isMethodArgument = false;


    /**
     * constructor for component
     * @param type - type
     * @param name  - name
     * @param isFinal - is final
     */
    public Variable(VariableType type, String name, String isFinal, String value){
        if (isFinal != null)
            this.isFinal = true;
        this.type = type;
        this.name = name;
        this.value = value;

    }

    /**
     * copy constructor
     * @param rhs variable to be copied
     */
    public Variable(Variable rhs){
        this.type = rhs.type;
        this.name = rhs.name;
        this.isFinal = rhs.isFinal;
        this.value = rhs.value;
        this.isMethodArgument = rhs.isMethodArgument;
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

    /**
     * @return value of component
     */
    public String getValue() {
        return value;
    }

    /**
     * set variable value
     * @param value - new value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * set isMethodArgument field to be true
     */
    public void setVarToBeMethodArg(){
        isMethodArgument = true;
    }

    /**
     * @return if this variable is a method argument
     */
    public boolean isMethodArg(){
        return isMethodArgument;
    }
}