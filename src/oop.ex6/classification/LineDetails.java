package oop.ex6.classification;

import java.util.regex.Matcher;

/**
 * holds line objects - Matcher and LineType
 */
public class LineDetails{
    private final LineType type;
    private final Matcher matcher;

    /**
     * constructor
     * @param type line type
     * @param matcher matcher object.
     */
    public LineDetails(LineType type, Matcher matcher){
        this.matcher = matcher;
        this.type = type;
    }

    /**
     * @return line type
     */
    public LineType getType() {
        return type;
    }

    /**
     * @return matcher for line
     */
    public Matcher getMatcher() {
        return matcher;
    }
}