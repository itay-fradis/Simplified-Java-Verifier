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

	private static final String ARGUMENTS = "arguments";

	private static final String VARIABLE_NAME = "variableName";

	private static final String VARIABLE_VALUE = "variableValue";

	private static final String IS_FINAL = "final";

	private static final String INT = "int";

	private static final String DOUBLE = "double";

	private static final String STRING = "String";

	private static final String CHAR = "char";

	private static final String BOOLEAN = "boolean";

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
		// remove semi colon
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
		String value = m.group(VARIABLE_VALUE);
		value = checkIfAssignedByVariable(value);
		VariableType variableTypeValue = Classifier.classifyValue(value);
		if (finalPrefix != null && value == null){
			throw new FinalUsageException();
		}
		if (!Classifier.isLegalVariableType(type)) {
			throw new VariableDeclarationException();
		}
		if (variableTypeValue != null){
			String valueType = variableTypeValue.getTypeName();
			if (type.equals(INT) && !valueType.equals(INT)){
				throw new VariableDeclarationException();
			}

			if (type.equals(DOUBLE) && !valueType.equals(DOUBLE) && !valueType.equals(INT)){
				throw new VariableDeclarationException();
			}

			if (type.equals(STRING) && !valueType.equals(STRING) && !valueType.equals(CHAR)){
				throw new VariableDeclarationException();
			}

			if (type.equals(CHAR) && !valueType.equals(CHAR)){
				throw new VariableDeclarationException();
			}

			if (type.equals(BOOLEAN) && !valueType.equals(BOOLEAN) && !valueType.equals(DOUBLE) &&
					!valueType.equals(INT)){
				throw new VariableDeclarationException();
			}
			if (!isNameLegal(name)){
				throw new VariableDeclarationException();
			}

		}

		variables.add(new Component(Classifier.classifyValue(value), name, finalPrefix, value));
	}

	/**
	 *
	 * @param name
	 * @return
	 */
	private boolean isNameLegal(String name) {
		Matcher matcher = Pattern.compile(Values.VARIABLE_NAME.toString()).matcher(name);
		return matcher.matches();
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

}
