// Menu.java
// Authors: Miruna Serian and Anima Sutradhar.
// Program last updated: 5th March 2018.

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.*;


//-----------------------------------------------------------------------------------
//    ***
//-----------------------------------------------------------------------------------

class Menu
{
    ArrayList<Project> ProjectList = new ArrayList<Project>();
    ArrayList<Votes> listOfProjectsAndVotes = new ArrayList<>();
    private char option;
    private Scanner scan = new Scanner(System.in);

//-----------------------------------------------------------------------------------
//    Displays the 'Main Menu' and allows user to choose options.
//-----------------------------------------------------------------------------------

    public void displayMenu()
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

//-----------------------------------------------------------------------------------
//    Displays the 'About' page for when user chooses option 'A' from Main Menu.
//-----------------------------------------------------------------------------------

    private void about()
    {
        System.out.println("\n\tThis is a program designed to allocate credit fairly" +
                " for a project, based on each team member's contributions.");
        System.out.print("\tPress any key to return to the Main Menu: ");

        if (scan.next() != null) {
            displayMenu();
        }
    }

//-----------------------------------------------------------------------------------
//    Allows user to create new project, and enter project name and number of
//    team members - by choosing option 'C' from Main Menu.
//-----------------------------------------------------------------------------------

    private void newProject()
    {
        Project p = createProject();
        ProjectList.add(p);
        System.out.print("\n\tPress any key to return to the Main Menu: ");
        if (scan.next() != null)
        {
            displayMenu();
        }
    }

    private Project createProject()
    {
        String projectName = getName();
        int numberOfParticipants = getNumberofTeamMembers();

        String[] memberNames = getNamesOfTeamMembers(numberOfParticipants);

        Project p = new Project(projectName, numberOfParticipants, memberNames);

        return p;
    }

    public String getName()
    {
        String projectName = new String();
        Scanner scan = new Scanner(System.in);
        boolean valid = false;

        while (valid == false)
        {
           System.out.print("\nEnter the project name: ");

            projectName = scan.nextLine();

            if (!Project.checkName(projectName)|| !checkStringCharacters(projectName))
            {
                System.out.println("\nInvalid input, try again.");
            }
            else if(checkExistentTeams(projectName))
            {
                System.out.println("\nThis team name already exists.");
            }
            else
            {
                valid = true;
            }
        }
        return projectName;
    }

    public int getNumberofTeamMembers()
    {
        Scanner scan = new Scanner(System.in);
        boolean valid = false;
        String n;
        int numberOfMembers = 0;

        while (valid == false)
        {
            System.out.print("\nEnter the number of team members: ");

            n = scan.next();

            if(numberOk(n))
            {
                numberOfMembers = Integer.parseInt(n);
                if (Project.checkNumberOfMembers(numberOfMembers))
                {
                    valid = true;
                }
                else
                {
                    System.out.println("\nProjects can have between " +
                            Project.MINMEMBERS + " and " +
                            Project.MAXMEMBERS + " members, try again.");
                }
            }
            else
            {
                System.out.println("Not a number. Try again!");
            }
        }
        return numberOfMembers;
    }

//-----------------------------------------------------------------------------------
//    Checks if user enters an integer and not a character or decimal point number
//    when entering number of team members.
//-----------------------------------------------------------------------------------

    public boolean numberOk(String validInteger)
    {
        boolean valid = false;
        try
        {
            Integer.parseInt(validInteger);
            valid = true;
        }
        catch (Exception ex)
        {
            //not an integer
        }
        return valid;
    }

//-----------------------------------------------------------------------------------
//    Allows user to enter team member names after creating a new project.
//-----------------------------------------------------------------------------------

    public String[] getNamesOfTeamMembers(int numberOfParticipants)
    {
        Scanner scan = new Scanner(System.in);
        boolean valid = false;

        String[] names = new String[Project.MAXMEMBERS];

        for (int i = 0; i < numberOfParticipants; i++)
        {
            valid = false;
            while (valid == false)
            {
                System.out.print("\nEnter team member's name: ");
                String name = scan.nextLine();
                if (Project.checkName(name))
                {
                    valid = true;
                    names[i] = name;
                }
                else
                {
                    System.out.println("\nA name must contain only alphanumeric characters and be between " +
                            Project.MINNAMELENGTH + " and " + Project.MAXNAMELENGTH +
                            " characters, try again.");
                }
            }
        }
        return names;
    }

//-----------------------------------------------------------------------------------
//    Allows user to enter votes for each of the team members by accessing a specific
//    existing project name. If project does not exist, outputs error message.
//-----------------------------------------------------------------------------------

    public void enterVotes()
    {
        int found = 0;
        Project p = null ;
        System.out.print("\nEnter the project name: ");
        String nameOfProject = scan.next();

        for (int index= 0; index< ProjectList.size(); index++)
        {
            if (ProjectList.get(index).getName().equals(nameOfProject))
            {
                found = 1;
                p = ProjectList.get(index);
                break;
            }
        }

        if(found == 0)
        {
            System.out.println("\nThe project with name " + nameOfProject + " was not found. Try again!");
            enterVotes();
        }

        if(found == 1)
            if(p != null)
            {
                System.out.println("There are " + p.getNumberOfTeamMembers() + " members.");
                Votes v = new Votes(p);
                v.getVotes();
                listOfProjectsAndVotes.add(v);
            }
        System.out.print("\n\tPress any key to return to the Main Menu: ");
        if (scan.next() != null)
        {
            displayMenu();
        }

    }

//-----------------------------------------------------------------------------------
//    Allows user to choose from Main Menu options to navigate around the app.
//    Different letters correspond to different pages that the user gets directed to.
//-----------------------------------------------------------------------------------

    private int choose()
    {
        char userInput = scan.next().charAt(0);
        option = Character.toLowerCase(userInput); //enables the user to input either lowercase or uppercase characters.
        switch (option)
        {
            case 'a':
                about();
            case 'c':
                newProject();
            case 'v':
                enterVotes();
            case 's':
                displayMenu();
            case 'q':
                quit();
            default:
            {
                System.out.println("Please choose another option: ");
            }
        }

        return 0;
    }

    //--------------------------------------------------------------
    // QUIT - when user quits, the project is stored in a text file.
    private void quit()
    {
        storeInformations(listOfProjectsAndVotes);
        System.exit(0);
    }
    //--------------------------------------------------------------

//-----------------------------------------------------------------------------------
//    Stores the project in a .txt file.
//-----------------------------------------------------------------------------------

    public void storeInformations(ArrayList<Votes> v)
    {
        // System.out.println(v.toString());
        StoreData data = new StoreData(v);
        data.writeData();
    }
//----------------------------------------------------------------------------
// Checks if the name of the Team already exists
//----------------------------------------------------------------------------

    private boolean checkExistentTeams(String aName)
    {
        boolean found = false;
        Project p = null;
        ;

        for (int index = 0; index < ProjectList.size(); index++) {
            if (ProjectList.get(index).getName().equals(aName)) {
                found = true;
                p = ProjectList.get(index);
                break;
            }
        }
        return found;
    }
//----------------------------------------------------------------------------
// Checks if the string contains only characters
//----------------------------------------------------------------------------
    private boolean checkStringCharacters(String name )
    {
        return name.matches("[a-zA-Z]+");
    }

}
