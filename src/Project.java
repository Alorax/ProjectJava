import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.Serializable;

class Project implements Serializable
{
    private static final long serialVersionUID = 1L;
    public static final int MAXMEMBERS = 5;
    public static final int MINMEMBERS = 3;
    public static final int MAXNAMELENGTH = 30;
    public static final int MINNAMELENGTH = 1;
    public static ArrayList<Project> aProject = new ArrayList<Project>();

    private String name;
    private String[] namesOfTeamMembers;
    private int numberOfTeamMembers;

    //----------------------------------------------------------------
    // Storing the information from each Project
    //----------------------------------------------------------------

    public Project (String aName, int aNumberOfTeamMembers, String[] theNamesOfTeamMembers)
    {
        if(checkName(aName))
        {
            name = aName;//
        }
        else
        {
            fatalError(" Invalid Input");
        }
        if(checkNumberOfMembers(aNumberOfTeamMembers))
        {
            numberOfTeamMembers = aNumberOfTeamMembers;
        }
        else
        {
            fatalError(" Invalid Input");
        }
        if(checkTheTeam(theNamesOfTeamMembers, numberOfTeamMembers))
        {
            namesOfTeamMembers = theNamesOfTeamMembers;
        }
        else
        {
            fatalError(" Invalid Input");
        }

    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setNamesOfTeamMembers(String[] namesOfTeamMembers)
    {
        this.namesOfTeamMembers = namesOfTeamMembers;
    }

    public void setNumberOfTeamMembers(int numberOfTeamMembers)
    {
        this.numberOfTeamMembers = numberOfTeamMembers;
    }

    public String getName() {
        return name;
    }

    public String[] getNamesOfTeamMembers() {
        return namesOfTeamMembers;
    }

    public int getNumberOfTeamMembers() {
        return numberOfTeamMembers;
    }

    public String printNamesOfTeamMembers(String[] namesOfTeamMembers) {
        String names=null;
        if(namesOfTeamMembers !=  null)
            names = namesOfTeamMembers[0];

        for (int index = 1 ; index < namesOfTeamMembers.length; index ++ ){
            if(namesOfTeamMembers[index] != null)
                names = names + " , " + namesOfTeamMembers[index];
        }
        return names;
    }
    @Override
    public String toString() {
        return  name + " , " + numberOfTeamMembers +" , " + printNamesOfTeamMembers(namesOfTeamMembers);

    }


    //---------------------------------------------
    // display an error if input is wrong
    //--------------------------------------------
    public void fatalError(String errorMessage)
    {
        System.out.println("Fatal error: " + errorMessage);
        // A non-zero status indicates an abnormal termination.
        System.exit(1);
    }

    //----------------------------------------------
    // this vaildates all kinds of user input
    //----------------------------------------------

    public static boolean checkName(String theName)
    {
        boolean nameOK = true;


        if (theName.isEmpty())
        {
            nameOK = false;
        }

        for (int i = 0; i < theName.length(); i++)
        {
            if (!Character.isLetterOrDigit(theName.charAt(i)))
                nameOK = false;
        }

        return nameOK;
    }
//---------------------------------------------------
    // validate numberof members
    //-----------------------------------------------

    static boolean checkNumberOfMembers(int theNumberOfMembers) {
        return (theNumberOfMembers >= MINMEMBERS && theNumberOfMembers <= MAXMEMBERS);
    }
    //--------------------------------------------
    // validate the team
    //--------------------------------------------

    private boolean checkTheTeam(String[] theTeam, int theNumberOfParticipants)
    {
        String theName;
        boolean TeamOK = true;

        if (TeamOK)
        {
            for (int i = 0; i < theNumberOfParticipants; i++)
            {
                theName = theTeam[i];
                TeamOK = checkName(theName);
            }
        }
        return TeamOK;
    }

}