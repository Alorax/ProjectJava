import java.util.ArrayList;
import java.util.Scanner;

public class VotesAllocation {

      private Project project;
      private ArrayList<Vote> list  ;
      private Vote v;


    static StoreData store;

    public VotesAllocation(Project p){
        this.project = p;
    }

    public VotesAllocation(Project p, ArrayList<Vote> list){
        this.project=p;
        this.list  = list;
    }

    public ArrayList<Vote> getList() {
        return list;
    }

    public void setList(ArrayList<Vote> list) {
        this.list = list;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return project.toString() + " , " +printListOfMembersVotes();
    }
    public void getVotes(){
        list = new ArrayList<>();
        int numberOfMembers = project.getNumberOfTeamMembers();
        for (int i = 0 ; i < numberOfMembers;i ++)
            if( project.getNamesOfTeamMembers()[i] != null ){
                ArrayList<Member> listOfMembersVotes = null;
                System.out.print("\nEnter " + project.getNamesOfTeamMembers()[i] +"’s votes, points must add up to 100:\n");
                listOfMembersVotes = giveVotes(i,numberOfMembers);
            Vote v = new Vote (project.getNamesOfTeamMembers()[i],listOfMembersVotes);
            list.add(v);
        }

        }


    public ArrayList<Member>  giveVotes(int i,int numberOfMembers) {
        ArrayList<Member> listOfMembersVotes = new ArrayList<>();
        for (int j = 0; j < numberOfMembers; j++ ) {
            if (project.getNamesOfTeamMembers()[j] != null) {
                if (i != j) {
                    int vote = readAndCheckVote(i,j);
                    Member m = new Member(project.getNamesOfTeamMembers()[j], vote);
                    listOfMembersVotes.add(m);
                }
            }
        }
        int sumOfVotes = getSumOfVotes(listOfMembersVotes);
        if(sumOfVotes != 100) {
            System.out.println("The sum doesn't add up to 100.Try again!");
            giveVotes(i,numberOfMembers);
        }
     return listOfMembersVotes;
    }

    public int readAndCheckVote(int i,int j) {
        int vote = 0 ;
        Scanner s = new Scanner(System.in);

        System.out.print("\n\tEnter " + project.getNamesOfTeamMembers()[i] + "’s points for " + this.project.getNamesOfTeamMembers()[j] + " : ");
        String userInput = s.next();
        if(Tester.isNumber(userInput))
            vote = Integer.parseInt(userInput);

        if(vote == 0) {
            System.out.println("\n Input is not valid. Try again!");
            readAndCheckVote(i,j);
        }

        return vote;
    }
    public int getSumOfVotes(ArrayList<Member> list) {
        int sum = 0;
        for(Member m: list){
            sum = sum + m.getVote();
        }
     return sum;
    }

    public String printListOfMembersVotes () {
        String listOfMembersVotes = null;

        if (list != null)
            listOfMembersVotes = list.get(0).toString();
        for (int index = 1; index < list.size(); index++) {
            listOfMembersVotes = listOfMembersVotes + " , " + list.get(index).toString();
        }
        return listOfMembersVotes;
    }

}
