// Votes.java
// Authors: Miruna Serian and Anima Sutradhar.
// Program last updated: 5th March 2018.

import java.util.ArrayList;
import java.util.Scanner;

//-----------------------------------------------------------------------------------
//    ***
//-----------------------------------------------------------------------------------

public class Votes
{
    Project p;
    static ArrayList<Vote> list  ;
    Vote v;
    static StoreData store;


    public Votes(Project p)
    {
        this.p = p;
    }

    @Override
    public String toString()
    {
        return p.toString() + " , " +printListOfMembersVotes();
    }

//-----------------------------------------------------------------------------------
//    Retrieves team member names from a stored project, to later allow user to enter
//    votes for them each. ***may need to reword.
//-----------------------------------------------------------------------------------

    public void getVotes()
    {
        list = new ArrayList<>();

        int numberOfMembers = p.getNumberOfTeamMembers();
        for (int i = 0 ; i < numberOfMembers;i ++)
            if( p.getNamesOfTeamMembers()[i] != null )
            {
                ArrayList<Member> listOfMembersVotes = new ArrayList<>();
                System.out.print("\nEnter " + p.getNamesOfTeamMembers()[i] +"’s votes, points must add up to 100:\n");
                for (int j = 0; j < numberOfMembers; j++ )
                {
                    if( p.getNamesOfTeamMembers()[j] != null)
                    {
                        if (i != j)
                        {
                            System.out.print("\n\tEnter " + p.getNamesOfTeamMembers()[i] + "’s points for " + p.getNamesOfTeamMembers()[j] +" : ");
                            int vote = readAndCheckVote();
                            Member m = new Member(p.getNamesOfTeamMembers()[j],vote);
                            listOfMembersVotes.add(m);
                        }
                    }
                }
                Vote v = new Vote (p.getNamesOfTeamMembers()[i],listOfMembersVotes);
                list.add(v);
            }

    }

//-----------------------------------------------------------------------------------
//    Scans for user input to allow user to input votes for team members.
//-----------------------------------------------------------------------------------

    public static int readAndCheckVote()
    {
        Scanner s = new Scanner(System.in);
        int vote = s.nextInt();

        // validate the note

        return vote;
    }

//-----------------------------------------------------------------------------------
//    Specifies the format the number of votes (input by user) should be stored in,
//    in the .txt file.
//-----------------------------------------------------------------------------------

    public String printListOfMembersVotes ()
    {
        String listOfMembersVotes = null;

        if (list != null)
        {
            listOfMembersVotes = list.get(0).toString();
        }
        for (int index = 1; index < list.size(); index++)
        {
            listOfMembersVotes = listOfMembersVotes + ", " + list.get(index).toString();
        }
        return listOfMembersVotes;
    }

}