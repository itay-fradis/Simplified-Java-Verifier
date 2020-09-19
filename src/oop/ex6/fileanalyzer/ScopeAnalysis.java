package oop.ex6.fileanalyzer;

import oop.ex6.classification.*;
import oop.ex6.component.Component;
import oop.ex6.component.FinalUsageException;
import oop.ex6.component.VariableDeclarationException;
import oop.ex6.component.VariableType;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Analyze a scope in file
 */
public class ScopeAnalysis {

    /** const strings */
    private static final String VARIABLE_TYPE = "variableType";

    private static final String VARIABLE_NAME_REGEX = "(_\\w+)|([a-zA-Z]\\w*)";

    private static final String ARGUMENTS = "arguments";

    private static final String VARIABLE_NAME = "variableName";

    private static final String VARIABLE_VALUE = "variableValue";

    private static final String IS_FINAL = "final";

    /** hold the codeFile variables */
    private List<Component> variables = new ArrayList<>();

    /**
     * a private class represent a scope
     */
    private class Scope{
        private ArrayList<Component> components;
    }

    /**
     * a deque of scopes
     */
    private Scope[] scopes;

    /**
     * analyze lines in a scope
     * @param s - list of previos scopes
     * @param reader - reader of file
     * @throws IOException - when cannot read from file
     */
    public void Analyze(Scope[] s, BufferedReader reader) throws Exception {
        String line;
        while ((line = reader.readLine()) != null){
            line = line.trim();
            LineType type = LineClassification.generalClassify(line);
            switch (type){
                case NORMAL_LINE:
                    parseNormalLine(line);
                    break;
                case OPEN_SCOPE_LINE:
                    break;
                case CLOSED_SCOPE_LINE:
                    break;
                case COMMENT:
                    continue;
                case BAD_LINE:
                    throw new Exception("test");
                default:
                    throw new Exception("error");
            }
        }
    }

    /**
     * parse normal line
     * @param line line to be parsed
     */
    private void parseNormalLine(String line) throws BadLineFormatException {
        line = line.trim();
        LineDetails detailsL = LineClassification.SemiColonClassify(line);
        switch (detailsL.getType()){
            case NEW_VARIABLE:
                addVariables(detailsL.getMatcher());
                break;
            case BAD_LINE:
                throw new BadLineFormatException();
        }
    }

    /**
     * check if variable declaration is legal, and add it. (not completed yet)
     * @param m matcher
     * @throws Exception (should be a specific exception).
     */
    private void addVariables(Matcher m) throws VariableDeclarationException {
        String finalPrefix = m.group(IS_FINAL);
        String variableType = m.group(VARIABLE_TYPE);
        String[] arguments = m.group(ARGUMENTS).split(",");
        Pattern p = Pattern.compile(LineType.VARIABLE_ASSIGNMENT.getRegexPattern());
        for (String s: arguments){
            Matcher matcher = p.matcher(s.trim());
            if (matcher.matches())
                addVariable(matcher, finalPrefix, variableType);
            else
                throw new VariableDeclarationException();
        }

    }

    /**
     * check if variable declaration is legal, and add it. (not completed yet)
     * @param m matcher
     * @throws Exception (should be a specific exception).
     */
    private void addVariable(Matcher m, String finalPrefix, String type) throws VariableDeclarationException{
        String name = m.group(VARIABLE_NAME);
        if (!isNameLegal(name))
            throw new VariableDeclarationException();
        String value = m.group(VARIABLE_VALUE);
        value = checkIfAssignedByVariable(value);
        VariableType vType = VariableType.getType(type);
        if (vType == null)
            throw new VariableDeclarationException();
        if (value == null){
            if (finalPrefix != null) {
                throw new FinalUsageException();
            }
            variables.add(new Component(vType, name, null, null));
            return;
        }
        Pattern p = Pattern.compile(vType.getRegex());
        Matcher matcher = p.matcher(value);
        if (!matcher.matches())
            throw new VariableDeclarationException();
        variables.add(new Component(vType, name, finalPrefix, matcher.group(1)));

    }

    /**
     * if a variable assigned by another var, it will return's its value.
     * @param value value to be returned
     * @return updated value
     */
    private String checkIfAssignedByVariable(String value) throws VariableDeclarationException {
        for (Component variable: variables){
            if (variable.getName().equals(value)){
                if (variable.getValue() == null){
                    throw new VariableDeclarationException();
                }
                return variable.getValue();
            }
        }
        return value;
    }

    /**
     * @param name given variable name
     * @return whether the name is legal or not
     */
    private boolean isNameLegal(String name) {
        Matcher matcher = Pattern.compile(VARIABLE_NAME_REGEX).matcher(name);
        return matcher.matches();
    }

}
