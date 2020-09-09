package oop.ex6.classification;

public enum VariableType {

<<<<<<< HEAD
    INT("int"),

    String("String");
=======
    INT("int");
>>>>>>> 26915f6... VariableType

    private final String type;

    VariableType(final String type) {this.type = type;}

    public String getType(){return type;}
}
