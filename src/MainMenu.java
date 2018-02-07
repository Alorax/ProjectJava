<<<<<<< HEAD
import java.util.Scanner;
=======
// This program is a "program" with the purpose of helping
// teams allocate the credit for a project fairly so that all parties
// are satisfied with the outcome.
>>>>>>> b5931dcba94bcaf3f497f8b9e6d99fe8f6f778c7


class Project {
    String name;
    String[] namesOfTeamMembers;

    public String getName() {
        return name;
    }

<<<<<<< HEAD
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
        System.out.println("\n\n\tAbout (A)");
        System.out.println("\tCreate Project (C)");
        System.out.println("\tEnter Votes (V)");
        System.out.println("\tShow Project (S)");
        System.out.println("\tQuit (Q)");
        System.out.print("\n\tPlease choose an option: ");

        choose();
    }

    public void about() {
        System.out.println("\n\tThis is a program designed to allocate credit fairly for a project based on each team member's contribution.");
        System.out.print("\tPress any key to return to the Main Menu: ");
        if (scan.next() != null) {
            displayMenu();
        }
    }

    public void newProject() {
        Project p = new Project(); //think of a more intuitive name for "p".
        String[] names = new String[10];
        System.out.print("\tEnter the project name: ");
        String name = scan.next();
        p.setName(name);
        System.out.print("\tEnter the number of team members: ");
        String NumberTeamMembers = scan.next(); //change this to scan for int.
        int n = Integer.parseInt(NumberTeamMembers);
        for (int i = 1; i <= n; i++) {
            System.out.print("\tEnter the name of team member " + i + ": ");
            names[i] = scan.next();
        }
        p.setNamesOfTeamMembers(names);
        System.out.print("\tPress any key to return to the Main Menu: ");
        if (scan.next() != null) {
            displayMenu();
        }
    }

    public int choose() {
        char userInput = scan.next().charAt(0);
        option = Character.toLowerCase(userInput);
        switch (option) { //should these be upper case?
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
                System.out.println("Please choose another option: ");
            }
        }

        return 0;
    }

}
=======
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
>>>>>>> b5931dcba94bcaf3f497f8b9e6d99fe8f6f778c7
