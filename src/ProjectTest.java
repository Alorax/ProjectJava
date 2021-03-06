//--------------------------------------------------------------------------------------------------------------
// Contains tests for some of the methods used in the project
//--------------------------------------------------------------------------------------------------------------

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ProjectTest {
    @Test
    public void checkNameTest() {
        assertEquals("Input is not valid.", false, Project.checkName("Team_"));
        assertEquals("Input is valid.", true, Project.checkName("Team"));
    }

    @Test
    public void checkNumberOfMembersTest() {
        assertEquals("Input is not valid", false, Project.checkNumberOfMembers(2));
        assertEquals("Input is valid", true, Project.checkNumberOfMembers(4));
    }

    @Test
    public void checkIfMemberExistsTest() {
        String[] namesOfTeamMembers = {"Benn", "Miruna", "George"};
        assertEquals("Member doesn't exist", false, Menu.checkIfMemberExists("Ben", namesOfTeamMembers));
        assertEquals("Member already exists", true, Menu.checkIfMemberExists("Miruna", namesOfTeamMembers));
    }

    @Test
    public void getSumOfVotesTest()
    {
        Member m1 = new Member("Ben", 20);
        Member m2 = new Member("Ana", 30);
        Member m3 = new Member("Benny", 50);
        ArrayList<Member> memberList = new ArrayList();
        memberList.add(m1);
        memberList.add(m2);
        memberList.add(m3);
        assertEquals("Votes add up", 100, VotesAllocation.getSumOfVotes(memberList));
    }

    @Test
    public void checkTheTeamTest()
    {   String[] theTeam= new String[4];
        theTeam[0] = "Ben";
        theTeam[1] = "Miruna";
        theTeam[2] = "Anima";
        theTeam[3] = "Josh";
        assertEquals("Team is valid", true, Project.checkTheTeam(theTeam, 4));
    }
