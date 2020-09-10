package oop.ex6.classification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class to classify lines according to classification wanted
 */
public class LineClassification {
    /**
     * general type of lines
     */
    private static final LineType[] generalTypes = new LineType[]{LineType.NORMAL_LINE,
            LineType.CLOSED_SCOPE_LINE, LineType.OPEN_SCOPE_LINE};

    private static final LineType[] SEMICOLON_TYPES = new LineType[]{
            LineType.RETURN, LineType.NEW_VARIABLE};

    /**
     * default constructor
     */
    private LineClassification(){};

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
    private static LineType classify(String line, LineType[] types){
        for (LineType l : types){
            Matcher m = Pattern.compile(l.getRegexPattern()).matcher(line);
            if (m.matches())
                return l;
        }
        return LineType.BAD_LINE;

    }

    /**
     * classify semicolon ended types of line
     * @param line - line to classify
     * @return - type
     */
    public static LineType SemiColonClassify(String line){
        return classify(line, SEMICOLON_TYPES);
    }


}
