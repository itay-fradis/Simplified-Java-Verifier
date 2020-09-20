package oop.ex6.component;

import java.util.ArrayList;
import java.util.List;

/**
 * A component pf type method
 */
public class Method {

    /** name of the method */
    private String name;

    /** given variables of method */
    private List<Variable> givenVariables;

    /**
     * constructor
     * @param methodName method's name
     * @param list the given list of variables
     */
    public Method(String methodName, List<Variable> list){
        this.name = methodName;
        givenVariables = list;
    }

}
