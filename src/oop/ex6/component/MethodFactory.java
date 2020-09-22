package oop.ex6.component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * turns line method into method component
 */
public class MethodFactory {

    /***/
    private static final String METHOD_TYPE_REGEX = "^void$";

    /***/
    private static final String METHOD_NAME_REGEX = "[a-zA-Z]\\w*";

    private static final String IS_FINAL = "final";

    /***/
    private static final String METHOD_ARG_REGEX = "(?<final>^final\\s+)?(?<argType>[\\w]+)" +
        "\\s+(?<argName>.+)";

    /***/
    private static final String ARG_TYPE = "argType";

    /***/
    private static final String ARG_NAME = "argName";


    /**
     * creates a new method object.
     * @param methodType given method type - to check
     * @return new method object
     * @throws MethodDeclarationException
     */
    public static Method addMethod(String methodType, String methodName, String[] arguments)
                                    throws MethodDeclarationException, VariableDeclarationException {
        if (!isLegalType(methodType) || !isLegalMethodName(methodName)){
            throw new MethodDeclarationException();
        }
        Map<String, Variable> map = new HashMap<>();
        List<Variable> order = new ArrayList<>();
        for (String arg: arguments){
            arg = arg.trim();
            Matcher matcher = Pattern.compile(METHOD_ARG_REGEX).matcher(arg);
            if (matcher.matches()){
                String isFinal = matcher.group(IS_FINAL);
                String argType = matcher.group(ARG_TYPE);
                String argName = matcher.group(ARG_NAME);
                VariableType vType = VariableType.getType(argType);
                if (vType == null || !VariableFactory.isNameLegal(argName) || map.containsKey(argName)){
                    throw new VariableDeclarationException();
                }
                Variable variable = new Variable(vType, argName, isFinal, null);
                variable.setVarToBeMethodArg();
                map.put(argName, variable);
                order.add(variable);
            }
            else{
                throw new VariableDeclarationException();
            }
        }
        return new Method(methodName, map, order);
    }

    /**
     *
     * @param name
     * @return
     */
    public static boolean isLegalMethodName(String name) {
        Matcher matcher = Pattern.compile(METHOD_NAME_REGEX).matcher(name);
        return matcher.matches();
    }

    /**
     * checks if method name is legal
     * @param type given name
     * @return true if legal, false otherwise
     */
    private static boolean isLegalType(String type) {
        Matcher matcher = Pattern.compile(METHOD_TYPE_REGEX).matcher(type);
        return matcher.matches();
    }
}
