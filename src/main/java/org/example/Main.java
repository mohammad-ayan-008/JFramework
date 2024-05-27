package org.example;


import org.example.Annotations.AutoConfig;
import org.example.Inits.JDBCInitilaizer;
import org.example.TEst.Client;
import org.example.TEst.DataInterface;
import org.example.TEst.USER;
import org.example.TEst.testInterface;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;

public class Main {


    @AutoConfig
    public static testInterface infs;

    public static void main(String[] args) {

         new JDBCInitilaizer("org.example");
   //     System.out.println(infs.save(new Client(1,"AYan")));
//         System.out.println(infs.findAll(Client.class));
//        System.out.println(infs.findClientById(1));
//        System.out.println(infs.findClientByName("AYan"));
//
       System.out.println(infs.deleteByObject(new Client(1,"AYan")));
    }
}