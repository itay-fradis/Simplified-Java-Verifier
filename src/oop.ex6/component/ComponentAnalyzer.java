package oop.ex6.component;

import oop.ex6.classification.LineType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * turn line into a component
 */
public class ComponentAnalyzer {
    /**
     * self static analyzer
     */
    private static ComponentAnalyzer analyzer = new ComponentAnalyzer();

    /**
     * default constructor
     */
    private ComponentAnalyzer(){};

    /**
     * classify a line of variable
     * @param line - line to classify
     * @return - component
     */
    public static Component classify(String line){
        for(LineType t: LineType.values()){
            Matcher m = Pattern.compile(t.getRegexPattern()).matcher(line);
            if (m.matches()){
                Component c = new Component(m.group("variableType"), m.group("variableName"),
                        m.group("final"));
            }
        }
        return null;
    }



}
