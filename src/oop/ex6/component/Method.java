package oop.ex6.component;

import java.util.ArrayList;
import java.util.List;

/**
 * A component pf type method
 */
public class Method {

    private String name;

    private int numOfGivenArgs = 0;

    private List<Variable> givenVariables;

    public Method(String methodName){
        this.name = methodName;
        givenVariables = new ArrayList<>();
    }

}
