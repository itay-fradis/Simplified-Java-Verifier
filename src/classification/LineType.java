package oop.ex6.classification;

public enum LineType {


    RETURN("^return;$");


    private final String regexPattern;

    LineType(final String regexPattern) { this.regexPattern = regexPattern;}

    public String getRegexPattern() {
        return regexPattern;
    }
}
