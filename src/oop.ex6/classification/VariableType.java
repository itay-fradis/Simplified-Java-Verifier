package oop.ex6.classification;

public enum VariableType {

    INT("int"),

    String("String");

    private final String type;

    VariableType(final String type) {this.type = type;}

    public String getType(){return type;}
}
