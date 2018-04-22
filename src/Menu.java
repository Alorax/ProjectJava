import java.util.ArrayList;
import java.util.Scanner;

class Menu
{
    static ArrayList<Project> ProjectList = new ArrayList<Project>();
    static ArrayList<VotesAllocation> listOfProjectsAndVotes = new ArrayList<>();
    static ArrayList<VotesAllocation> listOfProjectsAndVotesFromFile = new ArrayList<>();
    static ArrayList<VotesAllocation> votesAllocationList;
    private char option;
    private Scanner scan = new Scanner(System.in);
    private static String fileName = "NewProject.txt";

//--------------------------------------------------------------------------------------------------------------
// Read Data from file before the menu is displayed
//--------------------------------------------------------------------------------------------------------------

    public Menu() 
    {
        this.readDataFromFile();
    }
    public void readDataFromFile()
    {
        StoreData data = new StoreData(fileName);
        listOfProjectsAndVotesFromFile = data.readData();
    }

//--------------------------------------------------------------------------------------------------------------
// Display menu text
//--------------------------------------------------------------------------------------------------------------
    
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
    
//--------------------------------------------------------------------------------------------------------------
// Allow user to choose their option
//--------------------------------------------------------------------------------------------------------------

    public int choose()
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
                showProjectPointAllocation();
            case 'q':
                quit();
            default:
            {
                System.out.println("Please choose another option: ");
                choose();
            }
        }

        return 0;
    }

//--------------------------------------------------------------------------------------------------------------
// About option
//--------------------------------------------------------------------------------------------------------------

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
    
//--------------------------------------------------------------------------------------------------------------
// Allow user to create a new project
//--------------------------------------------------------------------------------------------------------------

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
    
//--------------------------------------------------------------------------------------------------------------
// Get name of project and check if the name already exists
//--------------------------------------------------------------------------------------------------------------

    public String getName()
    {
        String projectName = new String();
        Scanner scan = new Scanner(System.in);
        boolean valid = false;

        while (valid == false)
        {
            System.out.print("\nEnter the project name: ");

            projectName = scan.nextLine();

            if (!Project.checkName(projectName))
            {
                System.out.println("\nInvalid input, try again.");
            } else
            {
                if (checkIfProjectExists(projectName))
                    System.out.println("\n This project already exists");
                else
                    valid = true;
            }
        }
        return projectName;
    }

    public static boolean checkIfProjectExists(String name)
    {
        if (searchProject(name) != null)
        {
            return true;
        }

        return false;
    }
    
//--------------------------------------------------------------------------------------------------------------
// Get the number of team members and validate it
//--------------------------------------------------------------------------------------------------------------
    
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
            Tester test = new Tester();
            if (test.isNumber(n))
            {
                numberOfMembers = Integer.parseInt(n);
                if (Project.checkNumberOfMembers(numberOfMembers))
                {
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
    
//--------------------------------------------------------------------------------------------------------------
// Get names of team members and validate them
//--------------------------------------------------------------------------------------------------------------
    public static boolean checkIfMemberExists(String name, String[] namesOfTeamMembers)
    {
        if (namesOfTeamMembers != null)
        {
            for (int i = 0; i < namesOfTeamMembers.length; i++)
                if (name.equals(namesOfTeamMembers[i]))
                    return true;
        }
        return false;
    }

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
                if (!checkIfMemberExists(name, names))
                {
                    if (Project.checkName(name))
                    {
                        valid = true;
                        names[i] = name;
                    } else
                    {
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
    
//--------------------------------------------------------------------------------------------------------------
// Allow user to enter votes
//--------------------------------------------------------------------------------------------------------------

    public void enterVotes()
    {
        int found = 0;
        Project p = null ;
        System.out.print("\nEnter the project name: ");
        String nameOfProject = scan.next();

        p = searchProject(nameOfProject);

        if(p==null)
        {
            System.out.println("\nThe project with name " + nameOfProject + " was not found. Press N to try again, R to return to main menu or Q to quit:");
            chooseProject_TryAgain();
        }

        if(p != null)
        {
            System.out.println("There are " + p.getNumberOfTeamMembers() + " members.");
            {   VotesAllocation vFound = searchAllocatedVotesProject(nameOfProject);
                if (vFound != null)
                    if(vFound .getList() != null)
                    {
                        System.out.println("This projects has votes allocated already! Press N to try again, R to return to main menu or Q to quit:");
                        chooseProject_TryAgain();
                    }
                VotesAllocation v = new VotesAllocation(p);
                v.getVotes();
                listOfProjectsAndVotes.add(v);

            }
        }
        System.out.print("\n\tPress any key to return to the Main Menu: ");
        if (scan.next() != null)
        {
            displayMenu();
        }
    }

//--------------------------------------------------------------------------------------------------------------
// Give user other opotions if the project is not found
//--------------------------------------------------------------------------------------------------------------
    
    private int chooseProject_TryAgain()
    {
        char userInput = scan.next().charAt(0);
        option = Character.toLowerCase(userInput); //enables the user to input either lowercase or uppercase characters.
        switch (option) {
            case 'n':
                enterVotes();
            case 'r':
                displayMenu();
            case 'q':
                quit();
            default: {
                System.out.println("Please choose another option: ");
                choose();
            }
        }
        return 0;
    }

    //--------------------------------------------------------------------------------------------------------------
// Search if project is stored in the data file
//--------------------------------------------------------------------------------------------------------------
    public Project searchProjectInFile(String nameOfProject)
    {
        int found = 0;
        Project p = null;

        for (VotesAllocation votesAllocation : listOfProjectsAndVotesFromFile)
        {
            if (votesAllocation.getProject().getName().equals(nameOfProject))
            {
                found = 1;
                p = votesAllocation.getProject();
                break;
            }
        }
        return p;
    }

    public static Project searchProject(String nameOfProject)
    {
        int found = 0;
        Project p = null;
        p = searchProjectInFile(nameOfProject);
        if (p == null)
        {
            for (int index = 0; index < ProjectList.size(); index++)
            {
                if (ProjectList.get(index).getName().equals(nameOfProject))
                {
                    found = 1;
                    p = ProjectList.get(index);
                    break;
                }
            }
        }
        return p;
    }
    
//--------------------------------------------------------------------------------------------------------------
// Check if the project has allocated votes
//--------------------------------------------------------------------------------------------------------------
    
    public VotesAllocation searchAllocatedVotesProjectInFile(String nameOfProject)
    {
        int found = 0;
        VotesAllocation votesAllocation = null;

        for (VotesAllocation v : listOfProjectsAndVotesFromFile)
        {
            if (v.getProject().getName().equals(nameOfProject))
            {
                found = 1;
                votesAllocation = v;
                break;
            }
        }
        return votesAllocation;
    }

    public VotesAllocation  searchAllocatedVotesProject(String nameOfProject)
    {
        int found = 0;
        VotesAllocation v = null;
        v = searchAllocatedVotesProjectInFile(nameOfProject);
        if(v == null) {
            for (int index = 0; index < listOfProjectsAndVotesFromFile.size(); index++)
            {
                if (listOfProjectsAndVotesFromFile.get(index).getProject().getName().equals(nameOfProject))
                {
                    found = 1;
                    v= listOfProjectsAndVotesFromFile.get(index);
                    break;
                }
            }
        }
        return v;
    }

//--------------------------------------------------------------------------------------------------------------
// Check if the project has votes allocated to it and display the point allocation
//--------------------------------------------------------------------------------------------------------------
    
    public void showProjectPointAllocation()
    {
       // ArrayList<VotesAllocation> votesAllocationList = new ArrayList<VotesAllocation>();
        if(listOfProjectsAndVotesFromFile != null)
            votesAllocationList=listOfProjectsAndVotesFromFile;
        if(listOfProjectsAndVotes != null)
            votesAllocationList.addAll(listOfProjectsAndVotes);
        if(votesAllocationList == null)
            System.out.println("There are 0 projects.");
        else {
            ProjectPointAllocation projectPointAllocation = new ProjectPointAllocation(votesAllocationList);
            projectPointAllocation.showProjectPointAllocation();
        }
        System.out.print("\n\tPress any key to return to the Main Menu: ");
        if (scan.next() != null)
        {
            displayMenu();
        }

    }

//--------------------------------------------------------------------------------------------------------------
// QUIT option and store project
//--------------------------------------------------------------------------------------------------------------
    
    public void quit()
    {
        if(listOfProjectsAndVotes != null)
             storeInformation(listOfProjectsAndVotes);
        System.exit(0);
    }

//-------------------------------------------------------------
//  Store Project in a file
//------------------------------------------------------------
    
    public void storeInformation(ArrayList<VotesAllocation> v)
    {
        // System.out.println(v.toString());
        StoreData data = new StoreData(v,fileName);
        data.writeData();
    }
