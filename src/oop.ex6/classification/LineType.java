package oop.ex6.classification;


public enum LineType {

    COMMENT("^//"),

    NORMAL_LINE("[;]$"),

    OPEN_SCOPE_LINE("[{]$"),

    CLOSED_SCOPE_LINE("[}]$"),

    RETURN("^return$"),

    BAD_LINE(""),

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
