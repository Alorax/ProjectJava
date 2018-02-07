// This program is a "program" with the purpose of helping
// teams allocate the credit for a project fairly so that all parties
// are satisfied with the outcome.

// This is the first deliverable of the Fair Grade Allocator that shows
// the user the Main Menu, and allows them to create a project.

// Author: Anima Sutradhar.
// Last update: Sunday 4th January 2018.

//------------------------------------------------------------------------
// The Main Menu page that will be the first thing the user sees.
//------------------------------------------------------------------------

import java.util.Scanner;


class Project {
    String name;
    String[] namesOfTeamMembers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getNamesOfTeamMembers() {
        return namesOfTeamMembers;
    }

    public void setNamesOfTeamMembers(String[] namesOfTeamMembers) {
        this.namesOfTeamMembers = namesOfTeamMembers;
    }
}
class Menu {

    private char option;
    Scanner scan = new Scanner(System.in);

    public void displayMenu() {
        System.out.println("\n\n\tAbout(A)");
        System.out.println("\tCreate Project(C)");
        System.out.println("\tEnter Votes(V( ")
        System.out.println("\tShow Project(S)");
        System.out.println("\tQuit(Q)");
        System.out.print("\n\tPlease choose an option: ");
        c
    }

    public void about() {
        System.out.print("\tThis is a program designed to assign grades for a project based on each member's participation developed by Anima and Miruna. ");
        displayMenu();
    }

    public void newProject() {

        Project p = new Project();
        String[] names = new String[10];
        System.out.print(" Enter the project name: ");
        String name = scan.next();
        p.setName(name);
        System.out.print(" Enter the number of team members: ");
        String NumberTeamMembers = scan.next();
        int n = Integer.parseInt(NumberTeamMembers);
        for (int i = 1; i <= n; i++) {
            System.out.print("\t Enter the name of team member " + i +": ");
            names[i] = scan.next();
        }
        p.setNamesOfTeamMembers(names);
        System.out.print("Press any key to return to the main menu: ");
        if (scan.next() != null) {
            displayMenu();
        }
    }

    public int choose() {
        char userInput = scan.next().charAt(0);
        option = Character.toLowerCase(userInput);
        switch (option) {
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
            default: {
                System.out.println("Please choose another option");
                choose();
            }
        }

        return 0;
    }

}
public class Main{
    public static void main(String[] args) {
       Menu m = new Menu();
       m.displayMenu();

    }
}
