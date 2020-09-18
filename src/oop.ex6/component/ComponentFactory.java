package oop.ex6.component;

import oop.ex6.classification.Classifier;
import oop.ex6.classification.LineClassification;
import oop.ex6.classification.LineDetails;
import oop.ex6.classification.LineType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * creates components as variables and etc..
 * should be singleton
 */
public class ComponentFactory {

    private static final String VARIABLE_TYPE = "variableType";

    private static final String VARIABLE_NAME = "variableName";

    private static final String VARIABLE_VALUE = "variableValue";

    private static final String isFinal = "final";

    private static final String INT = "int";

    private static final String DOUBLE = "double";

    private static final String STRING = "String";

    private static final String CHAR = "char";

    private static final String BOOLEAN = "boolean";

    /** private constructor */
    private ComponentFactory(){}

    /** single instance */
    private static final ComponentFactory instance = new ComponentFactory();

    /** getter */
    public static final ComponentFactory getInstance(){ return instance; }

    /**
     * check if variable decleration is legal, and add it. (not completed yet)
     * @param details line details
     * @throws Exception (should be a specific exception).
     */
    public static void addVariable(LineDetails details) throws Exception {
        Matcher matcher = details.getMatcher();
        String type = matcher.group(VARIABLE_TYPE);
        String name = matcher.group(VARIABLE_NAME);
        String finalPrefix = matcher.group(isFinal);
        String value = matcher.group(VARIABLE_VALUE);
        VariableType variableTypeValue = Classifier.classifyValue(value);
        if (finalPrefix != null && value == null){
            throw new Exception();
        }
        if (variableTypeValue != null){
            String valueType = variableTypeValue.getTypeName();
            if (type.equals(INT) && !valueType.equals(INT)){
                throw new Exception();
            }

            if (type.equals(DOUBLE) && !valueType.equals(DOUBLE)){
                throw new Exception();
            }

            if (type.equals(STRING) && !valueType.equals(STRING)){
                throw new Exception();
            }

            if (type.equals(CHAR) && !valueType.equals(CHAR)){
                throw new Exception();
            }

            if (type.equals(BOOLEAN) && !valueType.equals(BOOLEAN) && !valueType.equals(DOUBLE) &&
                    !valueType.equals(INT)){
                throw new Exception();
            }
        }

    }
}
