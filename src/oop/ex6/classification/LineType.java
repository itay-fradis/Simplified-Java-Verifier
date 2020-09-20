package oop.ex6.classification;

/**
 * enums of line types
 */
public enum LineType {

    COMMENT("^//"),

    NORMAL_LINE("[;]$"),

    OPEN_SCOPE_LINE("[{]$"),

    CLOSED_SCOPE_LINE("[}]$"),

    RETURN("^return$"),

    BAD_LINE(""),

    NEW_METHOD("(?<methodType>[^\\s])\\s*(?<methodName>[^\\(\\s]+)" +
            "\\s*((?<arguments>.)\\)\\s{"),

    NEW_VARIABLE("(?<final>^final\\s+)?(?<variableType>[\\w]+)?\\s+(?<arguments>[^;]+);"),

    VARIABLE_ASSIGNMENT("(?<variableName>[^;=\\s]+)(\\s*=\\s*(?<variableValue>.*))?");


    /** the regex pattern */
    private final String regexPattern;


    /**
     * enum constructor
     * @param regexPattern regex pattern
     */
    LineType(final String regexPattern) { this.regexPattern = regexPattern;}

    /**
     * @return the enum's regex pattern
     */
    public String getRegexPattern() {
        return regexPattern;
    }
}
