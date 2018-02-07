// MainMenu.java
// Authors: Miruna Serian and Anima Sutradhar.
// Program last updated: 7th February 2018.

/* This program helps teams allocate the credit for a project fairly so that all
   parties are satisfied with the outcome.                                          */


/*
------------------------------------------------------------------------------------
    This class allows users to create a new project.
------------------------------------------------------------------------------------
*/

import java.util.Scanner;

class Project
{
    private String name;
    private String[] namesOfTeamMembers;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String[] getNamesOfTeamMembers()
    {
        return namesOfTeamMembers;
    }

    public void setNamesOfTeamMembers(String[] namesOfTeamMembers)
    {
        this.namesOfTeamMembers = namesOfTeamMembers;
    }
}

/*
------------------------------------------------------------------------------------
    This class prints out the Main Menu.
------------------------------------------------------------------------------------
*/

class Menu
{
    private char option;
    private Scanner scan = new Scanner(System.in);

    private void displayMenu()
    {
        System.out.println("\nWelcome to Split-It");
        System.out.println("\n\tAbout (A)");
        System.out.println("\tCreate Project (C)");
        System.out.println("\tEnter Votes (V)");
        System.out.println("\tShow Project (S)");
        System.out.println("\tQuit (Q)");
        System.out.print("\n\tPlease choose an option: ");

        choose();
    }

/*
------------------------------------------------------------------------------------
    'About' page for when user chooses option 'A' from Main Menu.
------------------------------------------------------------------------------------
*/

    private void about()
    {
        System.out.println("\n\tThis is a program designed to allocate credit fairly" +
                " for a project based on each team member's contributions.");
        System.out.print("\tPress any key to return to the Main Menu: ");
        if (scan.next() != null)
        {
            displayMenu();
        }
    }

/*
------------------------------------------------------------------------------------
    'Create Project' page for when user chooses option 'C' from Main Menu.
------------------------------------------------------------------------------------
*/

    private void newProject()
    {
        Project proiect = new Project();
        String[] names = new String[10];
        System.out.print("\n\tEnter the project name: ");
        String name = scan.next();
        proiect.setName(name);
        System.out.print("\tEnter the number of team members: ");
        int NumberTeamMembers = scan.nextInt();
        System.out.println();
        for (int i = 1; i <= NumberTeamMembers; i++)
        {
            System.out.print("\t\tEnter the name of team member " + i + ": ");
            names[i] = scan.next();
        }
        proiect.setNamesOfTeamMembers(names);
        System.out.print("\n\tPress any key to return to the Main Menu: ");
        if (scan.next() != null)
        {
            displayMenu();
        }
    }

/*
------------------------------------------------------------------------------------
    Allows user to choose an option from Main Menu.
------------------------------------------------------------------------------------
*/

    private int choose()
    {
        char userInput = scan.next().charAt(0);
        option = Character.toLowerCase(userInput);
        switch (option)
        {
            case 'a':
                about();
            case 'c':
                newProject();
            case 'v':
                displayMenu();
            case 's':
                displayMenu();
            case 'q':
                System.exit(0);
            default:
                {
                System.out.println("Please choose another option: ");
                }
        }

        return 0;
    }

}
