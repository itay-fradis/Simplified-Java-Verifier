package oop.ex6.component;

import oop.ex6.classification.LineType;

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
    private VariableFactory(){};

    /**
     * classify a line of variable
     * @param line - line to classify
     * @return - component
     */
    public static Variable classify(String line){
        for(LineType t: LineType.values()){
            Matcher m = Pattern.compile(t.getRegexPattern()).matcher(line);
            if (m.matches()){
               return null;
            }
        }
        return null;
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
     * @throws Exception (should be a specific exception).
     */
    public static Variable addVariable(String name, String value, String finalPrefix, String type) throws VariableDeclarationException{
        if (!isNameLegal(name))
            throw new VariableDeclarationException();
        VariableType vType = VariableType.getType(type);
        if (vType == null)
            throw new VariableDeclarationException();
        if (value == null){
            if (finalPrefix != null)
                throw new FinalUsageException();
            return new Variable(vType, name, null, null);
        }
        Pattern p = Pattern.compile(vType.getRegex());
        Matcher matcher = p.matcher(value);
        if (!matcher.matches())
            throw new VariableDeclarationException();
        return new Variable(vType, name, finalPrefix, value);
    }



}
