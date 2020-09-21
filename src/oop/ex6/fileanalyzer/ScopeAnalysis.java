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

    /**
     * const strings for regex usage
     */
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
     * Variables who's assigned by perhaps a global variable, which its name is represented by the string.
     */
    private final HashMap<Variable, String> unRecognizedVariables;

    /**
     * methods who has been called, before their declaration. first string represent the method call name,
     * and the second one represents the method call arguments.
     */
    private final HashMap<String, String> untRecognizedMethods;

    /**
     * constructor
     */
    public ScopeAnalysis() {
        scopes = new LinkedList<>();
        scopes.push(new Scope(new HashMap<>()));
        unRecognizedVariables = new HashMap<>();
        untRecognizedMethods = new HashMap<>();
    }

    /**
     * a private class represent a single scope
     */
    private class Scope {

        /**
         * the scope's methods and variables
         */
        private final Map<String, Variable> variables;
        private final Map<String, Method> methods;

        /** all scopes inside a method, has to have access to the method's given args. */
        private final Map<String, Variable> givenMethodVariables;

        /**
         * Scope constructor
         */
        private Scope(Map<String, Variable> givenArgs) {
            variables = new HashMap();
            methods = new HashMap();
            this.givenMethodVariables = givenArgs;
        }
    }

    /**
     * analyze lines in a scope
     *
     * @param reader - reader of file
     * @throws IOException - when cannot read from file
     */
    public void Analyze(BufferedReader reader) throws IOException, BadLineFormatException {
        String line;
        while ((line = reader.readLine()) != null) {
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
        closedFileChecks();
    }

    /**
     * parse new Scope.
     *
     * @param line given line to parse
     * @throws BadLineFormatException bad method declaration
     */
    private void parseNewScope(String line) throws BadLineFormatException {
        LineDetails detailsL = LineClassification.openParenthesisClassify(line);
        switch (detailsL.getType()) {
            case NEW_METHOD:
                addMethod(detailsL.getMatcher());
                return;
            case CONDITION:
                checkCondition(detailsL.getMatcher().group(ARGUMENTS));
                break;
            default:
                throw new BadLineFormatException();
                //case IF:
        }
        scopes.push(new Scope(scopes.getFirst().givenMethodVariables));
    }

    /**
     * check if a condition line in correct form
     *
     * @param arg - arguments in condition
     */
    private void checkCondition(String arg) throws BadConditionException {
        String[] args = arg.split(CONDITION_DELIMITER);
        Pattern p = Pattern.compile(VariableType.BOOLEAN.getRegex());
        for (String str : args) {
            Matcher matcher = p.matcher(str);
            if (!matcher.matches() && !checkBoolean(str)) {
                throw new BadConditionException();
            }
        }
    }

    /**
     * check if it is a valid boolean parameter
     *
     * @param boolParameter boolean parameter to check
     * @return true if valid, false otherwise
     */
    private boolean checkBoolean(String boolParameter) {
        Variable variable;
        if ((variable = searchVariable(boolParameter)) != null) {
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
    private Variable searchVariable(String name) {
        for (Scope scope : scopes) {
            if (scope.variables.containsKey(name))
                return scope.variables.get(name);
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
        if (matcher.group(ARGUMENTS).length() == 0) {
            arguments = new String[0];
        }
        Method newMethod = MethodFactory.addMethod(mType, mName, arguments);
        scopes.getFirst().methods.put(mName, newMethod);
        scopes.push(new Scope(newMethod.getVariables()));
    }

    /**
     * get method by their name
     * @param name - name of method
     * @return - method if exist
     */
    private Method getMethodByName(String name){
        for (Scope scope: scopes){
            if (scope.methods.containsKey(name))
                return scope.methods.get(name);
        }
        return null;
    }

    /**
     * check if variable assigned to is eligible type
     *
     * @param type - type that value assigned to
     * @param other - type that should fit our type
     * @return - true iff assignment is eligible
     */
    private boolean checkAssignedType(VariableType type, VariableType other) {
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
     * when new variable has a unrecognized value
     * @param value   - value of new variable
     * @throws VariableDeclarationException if value is not in correct form
     */
    private void unRecognizedValue(Variable variable, String value)
            throws VariableUsageException {
        if (scopes.size() == 1){
            unRecognizedValueToAssignGlobal(variable, value);
            return;
        }
        // scope 2 - assign local variables by value
        if (isValueIsGivenFromMethod(value)) {
            return;
        }
        Variable globalVariable = searchVariable(value);
        if (globalVariable != null) {
            declaredGlobalToAssignLocalVar(globalVariable, variable);
            return;
        }
        // if global variable has not been declared yet
        unRecognizedVariables.put(variable, value);
    }

    /**
     * when new local variable assigned with declared global variable
     * @param globalVariable global variable which assigns local variable
     * @param variable variable which we assign
     * @throws VariableDeclarationException if value is not in correct form
     */
    private void declaredGlobalToAssignLocalVar(Variable globalVariable, Variable variable)
                                                throws VariableUsageException {
        if (!checkAssignedType(variable.getType(), globalVariable.getType()))
            throw new VariableUsageException();
        if (globalVariable.getValue() == null){
            unRecognizedVariables.put(variable, globalVariable.getName());
            return;
        }
        variable.setValue(globalVariable.getValue());

    }

    /**
     * when new global variable has a unrecognized value
     * @param value   - value of new variable
     * @throws VariableDeclarationException iff value is not in correct form
     */
    private void unRecognizedValueToAssignGlobal(Variable variable, String value)
                                        throws unRecognizedValueException {
        Variable other = searchVariable(value);
        if (other == null || other.getValue() == null ||
                !checkAssignedType(variable.getType(), other.getType()))
            throw new unRecognizedValueException();
        variable.setValue(other.getValue());
    }

    /**
     * parse normal line which ends with semicolon.
     *
     * @param line line to be parsed
     */
    private void parseNormalLine(String line) throws BadLineFormatException {
        LineDetails detailsL = LineClassification.SemiColonClassify(line);
        switch (detailsL.getType()) {
            case NEW_VARIABLE:
                addVariables(detailsL.getMatcher());
                break;
            case METHOD_USAGE:
                analyzeMethodUsage(detailsL.getMatcher());
                break;
            case RETURN:
                break;
            case VARIABLE_ASSIGNMENT:
                String name = detailsL.getMatcher().group(VARIABLE_NAME);
                String value = detailsL.getMatcher().group(VARIABLE_VALUE);
                assignVariables(name, value);
                break;
            default:
                throw new BadLineFormatException();
        }
    }

    /**
     * assign a variable to a value
     * @param name - name of variable
     * @param value - value of variable
     * @throws VariableUsageException - iff value is not in correct form
     */
    private void assignVariables(String name, String value) throws VariableUsageException {
        Variable variable = searchVariable(name);
        if (variable == null)
            throw new VariableUsageException();
        Pattern p = Pattern.compile(variable.getType().getRegex());
        Matcher m = p.matcher(value);
        if (m.matches()){
            variable.setValue(value);
            return;
        }
        if (VariableFactory.isNameLegal(value))
            unRecognizedValue(variable, value);
        else
            throw new unRecognizedValueException();


    }

    /**
     * parse call for a method
     * @param matcher of the line with the METHOD USAGE regex.
     * @throws MethodDeclarationException if it is an illegal method declaration.
     */
    private void analyzeMethodUsage(Matcher matcher) throws MethodDeclarationException{
        String name = matcher.group(METHOD_NAME);
        String arguments = matcher.group(ARGUMENTS);
        Method method = getMethodByName(name);
        if (method == null) {
            if (!MethodFactory.isLegalMethodName(name))
                throw new MethodDeclarationException();
            untRecognizedMethods.put(name, arguments);
            return;
        }
        methodArgumentsCheck(name, arguments);
    }

    /**
     * check if there is a correlation between the method call arguments, and its declaration.
     * @param name of method call
     * @param arguments input arguments by the calling method
     * @throws MethodDeclarationException if it is an illegal method declaration.
     */
    private void methodArgumentsCheck(String name, String arguments) throws MethodDeclarationException{
        Method method = getMethodByName(name);
        if (method == null) {
            throw new MethodDeclarationException();
        }
        List<Variable> variablesOrder = method.getVariableOrder();
        if (arguments.length() == 0 && variablesOrder.size() == 0) //no arguments in method declaration
            return;
        String[] args = arguments.split(ARGUMENTS_DELIMITER);
        if (args.length != variablesOrder.size())
            throw new MethodDeclarationException(); // to change exception
        checkMethodArgumentsOneByOne(variablesOrder, args);
    }

    /**
     * checks each argument of the method call if it is compatible with the method declaration
     * @param variablesOrder the variables of the method declaration, by order
     * @param args of the method call
     * @throws MethodDeclarationException if there is no adjustment between arguments
     */
    private void checkMethodArgumentsOneByOne(List<Variable> variablesOrder, String[] args) throws MethodDeclarationException {
        for (int i = 0; i < variablesOrder.size(); i++) {
            VariableType type = variablesOrder.get(i).getType();
            Matcher m = Pattern.compile(type.getRegex()).matcher(args[i]);
            if (m.matches()) // in case the argument is constant value
                continue;
            Variable globalV = searchVariable(args[i]);
            if (globalV == null || !checkAssignedType(globalV.getType(),
                    variablesOrder.get(i).getType())) {
                throw new MethodDeclarationException(); //to check
            }
        }
    }


    /**
     * checks if value is given in method arguments
     * @param nameOfValue - name ov value
     * @return - true iff value is given
     */
    private boolean isValueIsGivenFromMethod(String nameOfValue){
        return scopes.getFirst().givenMethodVariables.containsKey(nameOfValue);
    }

    /**
     * check if variable declaration is legal, and add it. (not completed yet)
     *
     * @param newVarMatcher matcher
     * @throws Exception (should be a specific exception).
     */
    private void addVariables(Matcher newVarMatcher) throws VariableDeclarationException {
        String finalPrefix = newVarMatcher.group(IS_FINAL);
        String variableType = newVarMatcher.group(VARIABLE_TYPE);
        String[] arguments = newVarMatcher.group(ARGUMENTS).split(ARGUMENTS_DELIMITER);
        Pattern p = Pattern.compile(LineType.VARIABLE_ASSIGNMENT.getRegexPattern());
        for (String str : arguments) {
            Matcher matcher = p.matcher(str.trim());
            if (!matcher.matches())
                throw new VariableDeclarationException();
            String value = matcher.group(VARIABLE_VALUE);
            String varName = matcher.group(VARIABLE_NAME);
            try {

                Variable newVariable = VariableFactory.addVariable(varName, value, finalPrefix, variableType);
                scopes.getFirst().variables.put(varName, newVariable);
            }
            catch (unRecognizedValueException e) {
                Variable newVariable = VariableFactory.addVariable(varName, null, finalPrefix, variableType);
                scopes.getFirst().variables.put(varName, newVariable);
                unRecognizedValue(newVariable, value);
            }
        }
    }

    /**
     * check if an variable had been correctly assigned
     * @param v - variable with unrecognized value
     * @param name - name of value
     * @return - true iff assignment is correct
     */
    private boolean assignmentCheck(Variable v, String name){
        Variable other = searchVariable(name);
        if (other == null || other.getValue() == null ||
                !checkAssignedType(v.getType(), other.getType()))
            return false;
        return true;
    }

    /**
     * checks if all unrecognized variables had been assigned in correct form
     * @throws VariableDeclarationException - iff some unrecognized value is bad
     */
    private void closedFileChecks() throws VariableDeclarationException , MethodDeclarationException
    {
        for (Variable variable : unRecognizedVariables.keySet()){
            if (!assignmentCheck(variable, unRecognizedVariables.get(variable)))
                throw new VariableDeclarationException();
        }
        for (String methodName : untRecognizedMethods.keySet()){
            methodArgumentsCheck(methodName, untRecognizedMethods.get(methodName));
        }
    }
}
