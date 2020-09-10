package oop.ex6.classification;

public enum LineType {

    COMMENT("^//"),

    NORMAL_LINE(";$"),

    OPEN_SCOPE_LINE("{$"),

    CLOSED_SCOPE_LINE("}$"),

    RETURN("^return;$"),

    BAD_LINE(""),

    NEW_VARIABLE("((?<final>^final\\s+)|(^))(?<variableType>[a-zA-z][a-z]+)\\s+" +
                "(?<variableName>[_a-zA-z]\\w*)(;|\\s*=\\s*(?<variableValue>[\\w-.]*"),

    VARIABLE_ASSIGNMENT("?<variableName>^[_a-zA-z]\\w*)(;|\\s*=\\s*(?<variableValue>\\w*;))")

    ;



    private final String regexPattern;

    LineType(final String regexPattern) { this.regexPattern = regexPattern;}

    public String getRegexPattern() {
        return regexPattern;
    }
}
