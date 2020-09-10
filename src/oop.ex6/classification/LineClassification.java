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
    private static final LineType[] generalTypes = new LineType[]{LineType.COMMENT, LineType.NORMAL_LINE,
            LineType.CLOSED_SCOPE_LINE, LineType.OPEN_SCOPE_LINE};
    /**
     * types of line end on semi colon
     */
    private static final LineType[] SEMICOLON_TYPES = new LineType[]{
            LineType.RETURN, LineType.NEW_VARIABLE, LineType.VARIABLE_ASSIGNMENT};

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

    public static class LineDetails{
        private LineType type;
        private Matcher matcher;

        public LineDetails(LineType t, Matcher m){
            matcher = m;
            type = t;
        }

        public LineType getType() {
            return type;
        }

        public Matcher getMatcher() {
            return matcher;
        }
    }

    /**
     * classify a line from a list of possible types
     * @param line - line to classify
     * @param types - types of lines possible
     * @return - type
     */
    private static LineDetails classify(String line, LineType[] types){
        for (LineType l : types){
            Matcher m = Pattern.compile(l.getRegexPattern()).matcher(line);
            if (m.matches())
                return new LineDetails(l, m);
        }
        return new LineDetails(LineType.BAD_LINE, null);

    }

    /**
     * classify semicolon ended types of line
     * @param line - line to classify
     * @return - type
     */
    public static LineDetails SemiColonClassify(String line){
        return classify(line, SEMICOLON_TYPES);
    }


}
