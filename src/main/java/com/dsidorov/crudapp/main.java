package com.dsidorov.crudapp;

import com.dsidorov.crudapp.commander.TagCommander;
import com.dsidorov.crudapp.controller.TagController;

import java.util.Scanner;

public class main {
    public static final String ANSI_BLUE = "\u001B[38;5;33m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String[] args)
    {
        System.out.println(ANSI_BLUE + "To create a new tag write \"create\"" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "To update the tag write \"update\"" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "To delete the tag write \"delete\"" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "To get the tag write \"get\"" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "To view the tags write \"show\"" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "To end the program write \"end\"" + ANSI_RESET);
        System.out.println();
        int end = 0;
        TagCommander tagCommander = new TagCommander();
        Scanner scanner = new Scanner(System.in);
        while(end == 0)
        {
            String command = scanner.nextLine();
            if(command.equals("create"))
            {
                tagCommander.save();
            }
            else if(command.equals("update"))
            {
                tagCommander.update();
            }
            else if(command.equals("delete"))
            {
                tagCommander.deleteById();
            }
            else if(command.equals("get"))
            {
                tagCommander.getById();
            }
            else if(command.equals("show"))
            {
                tagCommander.getAll();
            }
            else if(command.equals("end"))
            {
                end++;
            }
            else
            {
                System.out.println("Command was not recognized. Try again!");
            }
        }
    }
}
