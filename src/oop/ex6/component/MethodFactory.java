package oop.ex6.component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * turns line method into method component
 */
public class MethodFactory {

    /***/
    private static final String METHOD_TYPE_REGEX = "^void$";

    /**
     *
     * @param name
     * @param numOfArgs
     * @return
     * @throws MethodDeclarationException
     */
    public static Method addMethod(String name, int numOfArgs) throws MethodDeclarationException {
        if (!isLegalType(name)){
            throw new MethodDeclarationException();
        }
        return new Method(name, numOfArgs);
    }

    /**
     * checks if method name is legal
     * @param name given name
     * @return true if legal, false otherwise
     */
    private static boolean isLegalType(String name) {
        Matcher matcher = Pattern.compile(METHOD_TYPE_REGEX).matcher(name);
        return matcher.matches();
    }
}
