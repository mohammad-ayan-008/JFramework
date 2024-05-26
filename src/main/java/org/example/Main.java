package org.example;


import org.example.Annotations.AutoConfig;
import org.example.Inits.JDBCInitilaizer;
import org.example.TEst.Client;
import org.example.TEst.DataInterface;
import org.example.TEst.USER;
import org.example.TEst.testInterface;

public class Main {


    @AutoConfig
    public static testInterface infs;

    public static void main(String[] args) {
         new JDBCInitilaizer("org.example");
         System.out.println(infs.save(new Client(2,"AYan")));
         System.out.println(infs.findAll(Client.class));
    }
}