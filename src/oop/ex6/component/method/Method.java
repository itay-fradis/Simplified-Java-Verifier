package oop.ex6.component.method;

import oop.ex6.component.variable.Variable;

import java.util.List;
import java.util.Map;

/**
 * A component of type method
 */
public class Method {

    /** name of the method */
    final private String name;

    /** given variables of method */
    final private Map <String, Variable> givenVariables;

    /** save variables in entry order*/
    final private List<Variable> variableOrder;

    /**
     * constructor
     * @param methodName method's name
     * @param map the given list of variables
     * @param order list of method variables in their order of appearance
     */
    public Method(String methodName, Map<String, Variable> map, List<Variable> order){
        this.name = methodName;
        givenVariables = map;
        variableOrder = order;
    }

    /**
     * get list of variables from method declaration
     * @return list of variables
     */
    public Map<String, Variable> getVariables(){
        return givenVariables;
    }

    public List<Variable> getVariableOrder() {return variableOrder;}
}
