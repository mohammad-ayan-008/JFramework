package org.example;


import org.example.Inits.JDBCInitilaizer;
import org.example.TEst.DataInterface;
import org.example.TEst.testInterface;

public class Main {
    public static DataInterface inf;

    public static void main(String[] args) {
        var repo = new JDBCInitilaizer();
        inf.findByName("Ayan");
    }
}