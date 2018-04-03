import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class ProjectPointAllocation
{
    private ArrayList<VotesAllocation> votesAllocationList;
    private Menu menu= new Menu();
    private Member[] listOfMembers;
    private Scanner scan = new Scanner(System.in);
    private char option;

    public ProjectPointAllocation(ArrayList<VotesAllocation> votesAllocationList)
    {
        this.votesAllocationList= votesAllocationList;
    }

    public void setListOfMembers(Project p)
    {
        Member[] listOfMembers = new Member[p.getNumberOfTeamMembers()];
        for(int i = 0; i< listOfMembers.length;i++)
        {
            listOfMembers[i] = new Member(p.getNamesOfTeamMembers()[i],0);
        }
        this.listOfMembers = listOfMembers;
    }

    public Project  searchInProject(String name)
    {
        Project p = null;
        for(VotesAllocation votesAllocation:votesAllocationList)
            if(votesAllocation.getProject().getName().equals(name))
            {
                p = votesAllocation.getProject();
                break;
            }

            return p;

    }

    public void showProjectPointAllocation()
    {
        Scanner scan = new Scanner(System.in);

        System.out.print("\nEnter project name: ");
        String nameOfProject = scan.next();
        Project p = searchInProject(nameOfProject);

        if (p == null)
        {
            System.out.println("The project " + nameOfProject + " doesn't exist.Press N to try again, R to return to main menu or Q to quit: ");
            chooseShowProject_TryAgain90();

        }
        if (p != null)
        {
            System.out.println("There are " + p.getNumberOfTeamMembers() + " members.");
            calculatePointsAllocation(p);
            showVotes();
        }
    }

    private int chooseShowProject_TryAgain90()
    {
        char userInput = scan.next().charAt(0);
        option = Character.toLowerCase(userInput); //enables the user to input either lowercase or uppercase characters.
        switch (option) {
            case 'n':
                showProjectPointAllocation();
            case 'r':
                menu.displayMenu();
            case 'q':
                menu.quit();
            default: {
                System.out.println("Please choose another option: ");
                menu.choose();
            }
        }
        return 0;


    }

    public void calculatePointsAllocation(Project p)
    {
        Tester test = new Tester();
        this.setListOfMembers(p);

        for (VotesAllocation votesAllocation : votesAllocationList)
        {
            if (votesAllocation.getProject().equals(p))
            {
                ArrayList<Vote> list = votesAllocation.getList();

                for(Vote v : list)
                {
                    ArrayList<Member> listOfMemberAndVotes = v.getListOfMembersAndVotes();
                    for(int i=0; i< listOfMemberAndVotes.size()-1;i=i+2)
                    {
                        pointAllocationOfMember(listOfMemberAndVotes.get(i),listOfMemberAndVotes.get(i).getVote() / (double)listOfMemberAndVotes.get(i + 1).getVote());
                        pointAllocationOfMember(listOfMemberAndVotes.get(i+1),listOfMemberAndVotes.get(i+1).getVote() / (double)listOfMemberAndVotes.get(i).getVote());
                    }

                }

            }
        }
    }

    public void pointAllocationOfMember(Member member,double r)
    {
        int i = 0;
        double denominator = r;
        for(Member m:listOfMembers){
            if(m.getName().equals(member.getName()))
            {
                denominator = denominator + m.getRatio();
                listOfMembers[i].setRatio(denominator);
            }
            i++;
        }

        i=0;
        for(Member m:listOfMembers)
        {
            if(m.getName().equals(member.getName()))
            {
                denominator = m.getRatio();
                listOfMembers[i].setFinalShare(1/denominator);
            }
            i++;
        }


    }

    public void showVotes()
    {
        System.out.println("The point allocation based on votes is: ");
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        for(int i=0; i<listOfMembers.length; i++)
        {
            System.out.println(listOfMembers[i].getName()+ " : " + twoDForm.format(listOfMembers[i].getFinalShare()));
        }

    }

}
