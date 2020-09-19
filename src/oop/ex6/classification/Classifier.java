package oop.ex6.classification;

import oop.ex6.component.VariableType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class to classify lines
 */
public class Classifier {
    /**
     * private instance of classifier
     */
    private static Classifier classifier = new Classifier();

    /**
     * a default constructor
     */
    private Classifier(){};

    /**
     * return line type for a given line
     * @param line - line to classify
     * @return - lineType
     */
    public static LineType LineClassify(String line) {
        return null;
    }

    /**
     * @param value given value.
     * @return the type which fits to the given value.
     */
    public static VariableType classifyValue(String value) throws IllegalValueException {
        if (value == null) {
            return null;
        }
        for (VariableType varType : VariableType.values()) {
            Matcher match = Pattern.compile(varType.getRegex()).matcher(value);
            if (match.matches()) {
                return varType;
            }
        }
        throw new IllegalValueException();
    }
}
