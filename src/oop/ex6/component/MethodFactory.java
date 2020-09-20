package oop.ex6.component;

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

    /***/
    private static final String METHOD_ARG_REGEX = "(?<final>^final\\s+)?(?<variableType>[\\w]+)?\\s+(?<arguments>[^;]+);";

    /**
     *
     * @param type
     * @return
     * @throws MethodDeclarationException
     */
    public static Method addMethod(String type,String name, String[] arguments) throws MethodDeclarationException {
        if (!isLegalType(type) || !isLegalName(name)){
            throw new MethodDeclarationException();
        }
        for (String arg: arguments){

        }
        return new Method(name);
    }

    /**
     *
     * @param name
     * @return
     */
    private static boolean isLegalName(String name) {
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
