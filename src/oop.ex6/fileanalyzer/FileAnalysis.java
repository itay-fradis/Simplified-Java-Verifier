package fileanalyzer;

import oop.ex6.component.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileAnalysis {

    private static FileAnalysis self = new FileAnalysis();

    private FileAnalysis() {
    }

    public void Analysis(File toRead) {
        try (BufferedReader reader = new BufferedReader(new FileReader(toRead))){

            ScopeAnalysis.Analyze(null, reader);

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
