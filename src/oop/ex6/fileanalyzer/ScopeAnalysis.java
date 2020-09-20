package oop.ex6.fileanalyzer;

import oop.ex6.classification.*;
import oop.ex6.component.*;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Analyze all scopes in file
 */
public class ScopeAnalysis {

    /** const strings for regex usage */
    private static final String VARIABLE_TYPE = "variableType";

    private static final String ARGUMENTS = "arguments";

    private static final String VARIABLE_NAME = "variableName";

    private static final String VARIABLE_VALUE = "variableValue";

    private static final String IS_FINAL = "final";

    private static final String ARGUMENTS_DELIMITER = ",";

    private static final String CONDITION_DELIMITER = "(\\|{2}|\\&{2})";

    private static final String METHOD_TYPE = "methodType";

    private static final String METHOD_NAME = "methodName";

    /**
     * a deque of scopes
     */
    private final LinkedList<Scope> scopes;

    /**
     * constructor
     */
    public ScopeAnalysis(){
        scopes = new LinkedList<>();
        scopes.push(new Scope());
    }

    /**
     * a private class represent a single scope
     */
    private class Scope{

        /** the scope's methods and variables */
        private final Map<String, Variable> variables;
        private final Map<String, Method> methods;

        /**
         * Scope constructor
         */
        private Scope(){
            variables = new HashMap();
            methods = new HashMap();
        }
    }

    /**
     * analyze lines in a scope
     * @param reader - reader of file
     * @throws IOException - when cannot read from file
     */
    public void Analyze(BufferedReader reader) throws IOException, BadLineFormatException {
        String line;
        while ((line = reader.readLine()) != null){
            line = line.trim();
            LineType type = LineClassification.generalClassify(line);
            switch (type) {
                case NORMAL_LINE:
                    parseNormalLine(line);
                    break;
                case OPEN_SCOPE_LINE:
                    parseNewScope(line);
                    break;
                case CLOSED_SCOPE_LINE:
                    scopes.pop();
                    break;
                case COMMENT:
                case EMPTY_LINE:
                    continue;
                default:
                    throw new BadLineFormatException();
            }
        }
    }

    /**
     * parse new Scope.
     * @param line given line to parse
     * @throws BadLineFormatException bad method declaration
     */
    private void parseNewScope(String line) throws BadLineFormatException {
        scopes.push(new Scope());
        LineDetails detailsL = LineClassification.openParenthesisClassify(line);
        switch (detailsL.getType()){
            case NEW_METHOD:
                addMethod(detailsL.getMatcher());
                break;
            case CONDITION:
                checkCondition(detailsL.getMatcher().group(ARGUMENTS));
                break;
            case BAD_LINE:
                throw new BadLineFormatException();
                //case IF:
        }
    }

    /**
     * check if a condition line in correct form
     * @param arg - arguments in condition
     */
    private void checkCondition(String arg) throws BadConditionException {
        String[] args = arg.split(CONDITION_DELIMITER);
        Pattern p = Pattern.compile(VariableType.BOOLEAN.getRegex());
        for (String str: args){
            Matcher matcher = p.matcher(str);
            if (!matcher.matches() && !checkBoolean(str)) {
                throw new BadConditionException();
            }
        }
    }

    /**
     * check if it is a valid boolean parameter
     * @param boolParameter boolean parameter to check
     * @return true if valid, false otherwise
     */
    private boolean checkBoolean(String boolParameter) {
        Variable variable;
        if ((variable = searchVariable(boolParameter)) != null){
            VariableType type = variable.getType();
            return type == VariableType.BOOLEAN || type == VariableType.DOUBLE
                    || type == VariableType.INT;
            /// to add if boolean is initialized
        }
        return false;
    }

    /**
     * if a variable assigned by another var, it will return's its value.
     * @param name name of variable
     * @return updated value
     */
    private Variable searchVariable(String name){
        for (Scope sc: scopes){
            if (sc.variables.containsKey(name))
                return sc.variables.get(name);
        }
        return null;
    }

    /**
     * creates and add method to scope.
     * @param matcher matcher of line with method-regex
     */
    private void addMethod(Matcher matcher) throws BadLineFormatException {
        String mType = matcher.group(METHOD_TYPE);
        String mName = matcher.group(METHOD_NAME);
        String[] arguments = matcher.group(ARGUMENTS).split(ARGUMENTS_DELIMITER);
        if (matcher.group(ARGUMENTS).length() == 0){
            arguments = new String[0];
        }
        scopes.getFirst().methods.put(mName, MethodFactory.addMethod(mType, mName, arguments));

    }

    /**
     * parse normal line which ends with semicolon.
     * @param line line to be parsed
     */
    private void parseNormalLine(String line) throws BadLineFormatException {
        LineDetails detailsL = LineClassification.SemiColonClassify(line);
        switch (detailsL.getType()){
            case NEW_VARIABLE:
                addVariables(detailsL.getMatcher());
                break;
            case RETURN:
                break;
            case BAD_LINE:
                throw new BadLineFormatException();
        }
    }

    /**
     * check if variable declaration is legal, and add it. (not completed yet)
     * @param newVarMatcer matcher
     * @throws Exception (should be a specific exception).
     */
    private void addVariables(Matcher newVarMatcer) throws VariableDeclarationException {
        String finalPrefix = newVarMatcer.group(IS_FINAL);
        String variableType = newVarMatcer.group(VARIABLE_TYPE);
        String[] arguments = newVarMatcer.group(ARGUMENTS).split(ARGUMENTS_DELIMITER);
        Pattern p = Pattern.compile(LineType.VARIABLE_ASSIGNMENT.getRegexPattern());
        for (String str: arguments){
            Matcher matcher = p.matcher(str.trim());
            if (matcher.matches()) {
                String value = matcher.group(VARIABLE_VALUE);
                value = checkIfAssignedByVariable(value);
                scopes.getFirst().variables.put(matcher.group(VARIABLE_NAME),
                        VariableFactory.addVariable(matcher.group(VARIABLE_NAME), value,
                                finalPrefix, variableType));
            }
            else
                throw new VariableDeclarationException();
        }
    }

    /**
     * if a variable assigned by another var, it will return's its value.
     * @param value value to be returned
     * @return updated value
     */
    private String checkIfAssignedByVariable(String value) throws VariableDeclarationException {
        for (Scope s: scopes){
            for (Variable variable: s.variables.values()){
                if (variable.getName().equals(value)){
                    if (variable.getValue() == null){
                        throw new VariableDeclarationException();
                    }
                    return variable.getValue();
                }
            }
        }

        return value;
    }

}
