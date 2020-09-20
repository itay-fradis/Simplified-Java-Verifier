package oop.ex6.component;

/**
 * A component pf type method
 */
public class Method {

    private String name;

    private int numOfGivenArgs = 0;

    private Variable[] givenVariables;

    public Method(String methodName, int numOfArgs){
        this.name = methodName;
        this.numOfGivenArgs = numOfArgs;
        givenVariables = new Variable[numOfGivenArgs];
    }

}
