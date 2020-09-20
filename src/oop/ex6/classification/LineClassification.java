package oop.ex6.classification;

import java.util.regex.*;
import java.util.regex.Pattern;

/**
 * class to classify lines according to classification wanted
 */
public class LineClassification {
    /**
     * general type of lines
     */
    private static final LineType[] generalTypes = new LineType[]{LineType.COMMENT, LineType.NORMAL_LINE,
            LineType.CLOSED_SCOPE_LINE, LineType.OPEN_SCOPE_LINE};
    /**
     * types of line end with semi colon
     */
    private static final LineType[] SEMICOLON_TYPES = new LineType[]{
            LineType.RETURN, LineType.NEW_VARIABLE, LineType.VARIABLE_ASSIGNMENT};

    /** types of line ends with open parenthesis */
    private static final LineType[] OPEN_PARENTHESIS = new LineType[]{LineType.NEW_METHOD, LineType.CONDITION};

    /**
     * default constructor
     */
    private LineClassification(){}

    /**
     * general classification
     * @param line - line to classify
     * @return - line type
     */
    public static LineType generalClassify(String line){
        for (LineType l : generalTypes){
            Matcher m = Pattern.compile(l.getRegexPattern()).matcher(line);
            if (m.find())
                return l;
        }
        return LineType.BAD_LINE;
    }

    /**
     * classify a line from a list of possible types
     * @param line - line to classify
     * @param types - types of lines possible
     * @return - type
     */
    private static LineDetails classify(String line, LineType[] types){
        for (LineType lineType : types){
            Matcher matcher = Pattern.compile(lineType.getRegexPattern()).matcher(line);
            if (matcher.matches())
                return new LineDetails(lineType, matcher);
        }
        return new LineDetails(LineType.BAD_LINE, null);

    }

    /**
     * classify semicolon ended types of line
     * @param line - line to classify
     * @return - lineDetails
     */
    public static LineDetails SemiColonClassify(String line){
        return classify(line, SEMICOLON_TYPES);
    }

    /**
     * @param line line with openParenthesisToClassify
     * @return lineDetails
     */
    public static LineDetails openParenthesisClassify(String line){
        return classify(line, OPEN_PARENTHESIS);
    }


}
