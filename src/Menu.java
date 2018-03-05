// Menu.java
// Authors: Miruna Serian and Anima Sutradhar.
// Program last updated: 5th March 2018.

import java.util.ArrayList;
import java.util.Scanner;

class Menu {
    ArrayList<Project> ProjectList = new ArrayList<Project>();
    ArrayList<VotesAllocation> listOfProjectsAndVotes = new ArrayList<>();
    ArrayList<VotesAllocation> listOfProjectsAndVotesFromFile = new ArrayList<>();
    private char option;
    private Scanner scan = new Scanner(System.in);
    private static String fileName = "NewProject.txt";

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

        if (scan.next() != null)
        {
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
        if (scan.next() != null) {
            displayMenu();
        }
    }

    private Project createProject() 
    {
        String projectName = getName();
        int numberOfParticipants = getNumberofTeamMembers();

        String[] teamNames = getNamesOfTeamMembers(numberOfParticipants);

        Project p = new Project(projectName, numberOfParticipants, teamNames);

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

            if (!Project.checkName(projectName)) {
                System.out.println("\nInvalid input, try again.");
            } else {
                if (checkIfProjectExists(projectName))
                    System.out.println("\n This project already exists");
                else
                    valid = true;
            }
        }
        return projectName;
    }

    public boolean checkIfProjectExists(String name) {
       // for (VotesAllocation votesAllocation : listOfProjectsAndVotesFromFile) {
            if (searchProject(name) != null) {
                return true;
            }

        return false;
    }
//-----------------------------------------------------------------------------------
//    Asks for user input and checks if it is an integer and not a character or decimal point number
//    when entering number of team members.
//-----------------------------------------------------------------------------------
    public int getNumberofTeamMembers() {
        Scanner scan = new Scanner(System.in);
        boolean valid = false;
        String n;
        int numberOfMembers = 0;

        while (valid == false) {
            System.out.print("\nEnter the number of team members: ");

            n = scan.next();
            Tester test = new Tester();
            if (test.isNumber(n)) {
                numberOfMembers = Integer.parseInt(n);
                if (Project.checkNumberOfMembers(numberOfMembers)) {
                    valid = true;
                } else {
                    System.out.println("\nProjects can have between " +
                            Project.MINMEMBERS + " and " +
                            Project.MAXMEMBERS + " members, try again.");
                }
            } else
                System.out.println("Not a number. Try again!");
        }
        return numberOfMembers;
    }
//-----------------------------------------------------------------------------------
//    Validates if the name of the participant introduced already exists
//-----------------------------------------------------------------------------------

    public boolean checkIfMemberExists(String name, String[] namesOfTeamMembers) {
        if (namesOfTeamMembers != null) {
            for (int i = 0; i < namesOfTeamMembers.length; i++)
                if (name.equals(namesOfTeamMembers[i]))
                    return true;
        }
        return false;
    }
//-----------------------------------------------------------------------------------
//    Gets the names of each member and validates the names
//-----------------------------------------------------------------------------------
    public String[] getNamesOfTeamMembers(int numberOfParticipants) {

        Scanner scan = new Scanner(System.in);
        boolean valid = false;

        String[] names = new String[Project.MAXMEMBERS];

        for (int i = 0; i < numberOfParticipants; i++) {
            valid = false;
            while (valid == false) {
                System.out.print("\nEnter team member's name: ");
                String name = scan.nextLine();
                if (!checkIfMemberExists(name, names)) {
                    if (Project.checkName(name)) {
                        valid = true;
                        names[i] = name;
                    } else {
                        System.out.println("\nA name must contain only alphanumeric characters and be between " +
                                Project.MINNAMELENGTH + " and " + Project.MAXNAMELENGTH +
                                " characters, try again.");
                    }
                } else
                    System.out.println("\n This member already exists! Try again!");

            }
        }
        return names;
    }
//-----------------------------------------------------------------------------------
//    Reads the created file and looks for if the project exists
//-----------------------------------------------------------------------------------
    public Project searchProjectInFile(String nameOfProject) {
        int found = 0;
        Project p = null;

        for (VotesAllocation votesAllocation : listOfProjectsAndVotesFromFile) {
            if (votesAllocation.getProject().getName().equals(nameOfProject)) {
                found = 1;
                p = votesAllocation.getProject();
                break;
            }
        }
        return p;
    }

    public Project searchProject(String nameOfProject) {
        int found = 0;
        Project p = null;
        p = searchProjectInFile(nameOfProject);
        if (p == null) {
            for (int index = 0; index < ProjectList.size(); index++) {
                if (ProjectList.get(index).getName().equals(nameOfProject)) {
                    found = 1;
                    p = ProjectList.get(index);
                    break;
                }
            }
        }
        return p;
    }
//-----------------------------------------------------------------------------------
//    Searches for the projects which votes are already allocated
//-----------------------------------------------------------------------------------
    public VotesAllocation searchAllocatedVotesProjectInFile(String nameOfProject) {
        int found = 0;
        VotesAllocation votesAllocation = null;

        for (VotesAllocation v : listOfProjectsAndVotesFromFile) {
            if (v.getProject().getName().equals(nameOfProject)) {
                found = 1;
                votesAllocation = v;
                break;
            }
        }
        return votesAllocation;
    }

    public VotesAllocation  searchAllocatedVotesProject(String nameOfProject) {
        int found = 0;
        VotesAllocation v = null;
        v = searchAllocatedVotesProjectInFile(nameOfProject);
        if(v == null) {
            for (int index = 0; index < listOfProjectsAndVotesFromFile.size(); index++) {
                if (listOfProjectsAndVotesFromFile.get(index).getProject().getName().equals(nameOfProject)) {
                    found = 1;
                    v= listOfProjectsAndVotesFromFile.get(index);
                    break;
                }
            }
        }
        return v;
    }
//-----------------------------------------------------------------------------------
//    Prompts user to introduce votes
//-----------------------------------------------------------------------------------
        public void enterVotes () {
            int found = 0;
            Project p = null;
            System.out.print("\nEnter the project name: ");
            String nameOfProject = scan.next();

            p = searchProject(nameOfProject);

            if (p == null) {
                System.out.println("\nThe project with name " + nameOfProject + " was not found. Try again!");
                enterVotes();
            }

            if (p != null) {
                System.out.println("There are " + p.getNumberOfTeamMembers() + " members.");
                {
                    VotesAllocation vFound = searchAllocatedVotesProject(nameOfProject);
                    if (vFound != null)
                        if (vFound.getList() != null) {
                            System.out.println("This projects has votes allocated already! Try again!");
                            enterVotes();
                        }
//-----------------------------------------------------------------------------------
//    For next deliverable
//    Allows user to edit previous votes                  
//-----------------------------------------------------------------------------------
                     /* 
                      System.out.println(" Do you want to edit this project?" (y/n));
                      String userInput= scan.next();
                      if(userInput.equals ) {
                      VotesAllocation v = searchAllocatedVotesProject(nameOfProject);
                      v.getVotes();
                      //v.setList(); // the new list of votes
                      StoreData.editData(nameOfProject, v.toString());
                      }
                  } else {

                  }
              }
          }
                 else {*/
                    VotesAllocation v = new VotesAllocation(p);
                    v.getVotes();
                    listOfProjectsAndVotes.add(v);

                }
            }
            System.out.print("\n\tPress any key to return to the Main Menu: ");
            if (scan.next() != null) {
                displayMenu();
            }
        }
//-----------------------------------------------------------------------------------
//   Menu Selection 
//-----------------------------------------------------------------------------------
    private int choose()
    {
        char userInput = scan.next().charAt(0);
        option = Character.toLowerCase(userInput); //enables the user to input either lowercase or uppercase characters.
        switch (option) {
            case 'a':
                about();
            case 'c':
                newProject();
            case 'v':
                enterVotes();
            case 's':
                showProjectPointAllocation();
            case 'q':
                quit();
            default: {
                System.out.println("Please choose another option: ");
                choose();
            }
        }

        return 0;
    }
//-----------------------------------------------------------------------------------
//    When user quits, information is saved in a file.
//-----------------------------------------------------------------------------------
    
    private void quit()
    {
        storeInformations(listOfProjectsAndVotes);
        System.exit(0);
    }


//-------------------------------------------------------------------------
//  Store Project
//-----------------------------------------------------------------------
    public void storeInformations(ArrayList<VotesAllocation> v) {
       // System.out.println(v.toString());
        StoreData data = new StoreData(v,fileName);
        data.writeData();
    }

    public void showProjectPointAllocation(){
        System.out.println("Informations about Project");
        System.out.print("\n\tPress any key to return to the Main Menu: ");
        if (scan.next() != null) {
            displayMenu();
        }
//-----------------------------------------------------------------------------------
//   Reads the file containing the team information and votes
//-----------------------------------------------------------------------------------
    }
    public void readDataFromFile() {
        StoreData data = new StoreData(fileName);
        listOfProjectsAndVotesFromFile = data.readData();
    }
}
