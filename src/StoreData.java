import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

//------------------------------------------------------------------------------------
//    Stores the information about each Project and allows the user to read the file
//    so that they can access the data and edit it.
//------------------------------------------------------------------------------------

public class StoreData 
{
    static ArrayList<VotesAllocation> v = new ArrayList<VotesAllocation>();
    static FileWriter writer;
    static String fileName;
    static Tester test = new Tester();

    public StoreData(String fileName) 
    {
        this.fileName = fileName;
    }
    public StoreData(ArrayList<VotesAllocation> v,String fileName) 
    {
        this.v = v;
        this.fileName = fileName;
    }

    public static void writeData() 
    {
        try 
        {
            writer = new FileWriter(fileName,true);
            for(int index = 0 ; index < v.size();index ++)
            {
                writer.write(v.get(index).toString());
                writer.write(System.getProperty( "line.separator" ));
            }
        } 
        catch (IOException ex) 
        {
            System.out.println("error: " + ex);
        } 
        finally 
        {
            try 
            {
                writer.close();
            } catch (Exception ex) 
            {
                System.out.println("error : " + ex);
            }
        }
    }

//------------------------------------------------------------------------------------
//    Allows user to edit the data in the text file.
//------------------------------------------------------------------------------------

    public static void editData( String name, String replaceLine) 
    {
        try 
        {
            BufferedReader file = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = file.readLine()) != null)
            {
                String[] s = line.split(" , ");
                String nameOfProject = s[0];
                if(s[0].equals(name)) 
                {
                    line = "";
                    writer.write(replaceLine);
                }
                else {
                    writer.write(line);
                    writer.write(System.getProperty( "line.separator" ));
                }
            }
            String inputStr = writer.toString();

            file.close();

            System.out.println(inputStr);

            FileOutputStream fileOut = new FileOutputStream(fileName);
            fileOut.write(inputStr.getBytes());
            fileOut.close();

        } catch (Exception e) 
        {
            System.out.println("Problem reading file.");
        }
    }
    
//------------------------------------------------------------------------------------
//    Stores the information from each Project.
//------------------------------------------------------------------------------------
    
    public ArrayList<VotesAllocation> readData() 
    {
        ArrayList<VotesAllocation> votesAllocations = new ArrayList<VotesAllocation>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) 
        {
            String line;
            String[] s;
            ArrayList<VotesAllocation> v = new ArrayList<VotesAllocation>();
            while ((line = br.readLine()) != null) 
            {
                s = line.split(" , ");
                String nameOfProject = s[0];
                int numberOfMembers = Integer.parseInt(s[1]);
                String[] nameOfMembers = new String[numberOfMembers];
                int nr = 0;
                for (int index = 2; index < 2 + numberOfMembers; index++) 
                {
                    nameOfMembers[nr] = s[index];
                    nr++;

                }

                Project project = new Project(nameOfProject, numberOfMembers, nameOfMembers);

                int lengthOfListOfMemberAndVotes = 1 + 2 * (numberOfMembers - 1);
                ArrayList<Vote> listOfGroupsOfMemberAndVotes = new ArrayList<Vote>();

                for (int j = 2 + numberOfMembers; j < s.length ; j = j + lengthOfListOfMemberAndVotes) 
                {
                    String memberWhoGaveTheNote = s[j];
                    ArrayList<Member> listOfMemberAndVotes = new ArrayList<Member>();

                    for (int i = j + 1; i < j + lengthOfListOfMemberAndVotes ; i = i + 2) 
                    {
                        Member member = new Member(s[i], Integer.parseInt(s[i + 1]));
                        listOfMemberAndVotes.add(member);
                    }
                    Vote groupOfMembersAndVotes = new Vote(memberWhoGaveTheNote, listOfMemberAndVotes);
                    listOfGroupsOfMemberAndVotes.add(groupOfMembersAndVotes);
                }

                VotesAllocation votesAllocation = new VotesAllocation(project, listOfGroupsOfMemberAndVotes);
                votesAllocations.add(votesAllocation);
            }
        } 
        catch (Exception ex) 
        {
            System.out.println("error: " + ex);
        }
       return votesAllocations;

    }

}
