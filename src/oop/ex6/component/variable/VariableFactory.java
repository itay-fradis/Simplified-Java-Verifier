package oop.ex6.component.variable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * turn line into a component
 */
public class VariableFactory {

    private static final String VARIABLE_NAME_REGEX = "(_\\w+)|([a-zA-Z]\\w*)";

    /**
     * self static analyzer
     */
    private static VariableFactory analyzer = new VariableFactory();

    /**
     * default constructor
     */
    private VariableFactory(){}

    /**
     * check if variable assigned to is eligible type
     * @param type - type that value assigned to
     * @param other - type that should fit our type
     * @return - true iff assignment is eligible
     */
    public static boolean checkAssignedType(VariableType type, VariableType other) {
        if (type == other)
            return true;
        switch (type) {
            case BOOLEAN:
                return (other == VariableType.INT || other == VariableType.DOUBLE);
            case DOUBLE:
                return (other == VariableType.INT);
            default:
                return false;
        }
    }

    /**
     * @param name given variable name
     * @return whether the name is legal or not
     */
    public static boolean isNameLegal(String name) {
        Matcher matcher = Pattern.compile(VARIABLE_NAME_REGEX).matcher(name);
        return matcher.matches();
    }

    /**
     * check if variable declaration is legal, and add it. (not completed yet)
     * @param value matcher
     * @param name - name of variable to added
     * @param finalPrefix - is variable final
     * @param type - type of new variable
     * @return added variable
     * @throws VariableDeclarationException iff fail to add variable.
     */
    public static Variable addVariable(String name, String value, String finalPrefix, String type)
            throws VariableDeclarationException{
        if (!isNameLegal(name))
            throw new VariableDeclarationException();
        VariableType vType = VariableType.getType(type);
        if (vType == null)
            throw new VariableDeclarationException();
        if (value == null){
            if (finalPrefix != null)
                throw new VariableDeclarationException();
            return new Variable(vType, name, null, null);
        }
        Pattern p = Pattern.compile(vType.getRegex());
        Matcher matcher = p.matcher(value);
        if (!matcher.matches()) {
            if (isNameLegal(value)) {
                throw new unRecognizedValueException();
            }
            throw new VariableDeclarationException();
        }
        return new Variable(vType, name, finalPrefix, value);
    }

}
