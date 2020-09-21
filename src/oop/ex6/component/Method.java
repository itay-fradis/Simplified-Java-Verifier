package oop.ex6.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A component pf type method
 */
public class Method {

    /** name of the method */
    private String name;

    /** given variables of method */
    private Map <String, Variable> givenVariables;

    /** save variables in entry order*/
    private List<Variable> variableOrder;

    /**
     * constructor
     * @param methodName method's name
     * @param map the given list of variables
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
