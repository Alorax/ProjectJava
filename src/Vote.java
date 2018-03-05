// Vote.java
// Authors: Miruna Serian and Anima Sutradhar.
// Program last updated: 5th March 2018.

import java.util.ArrayList;

//-----------------------------------------------------------------------------------
//    ***
//-----------------------------------------------------------------------------------

public class Vote
{
    String member;
    ArrayList<Member> listOfMembersAndVotes ;

    public Vote(String member,ArrayList<Member> listOfMembersAndVotes)
    {
        this.member = member;
        this.listOfMembersAndVotes = listOfMembersAndVotes;
    }

    public String getMember()
    {
        return member;
    }

    public void setMember(String member)
    {
        this.member = member;
    }

    public ArrayList<Member> getListOfMembersAndVotes()
    {
        return listOfMembersAndVotes;
    }

    public void setListOfMembersAndVotes(ArrayList<Member> listOfMembersAndVotes)
    {
        this.listOfMembersAndVotes = listOfMembersAndVotes;
    }

//-----------------------------------------------------------------------------------
//    Specifies the format the name of the team members and team member votes (input
//    by user) should be stored in, in the .txt file.
//-----------------------------------------------------------------------------------

    public String printListOfMemberAndVotes()
    {
        String list = null;

        if(listOfMembersAndVotes != null)
            list = listOfMembersAndVotes.get(0).toString();

        for (int index = 1; index < listOfMembersAndVotes.size(); index ++)
            list = list + " , " + listOfMembersAndVotes.get(index).toString();

        return list;
    }

//-----------------------------------------------------------------------------------
//    Specifies the format the name of the team member (input by user) should be
//    stored in, in the .txt file.
//-----------------------------------------------------------------------------------

    @Override
    public String toString()
    {
        return member + " , " + printListOfMemberAndVotes();
    }
}
