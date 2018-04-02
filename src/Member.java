//----------------------------------------------------------------
// Stores the information for each member
//----------------------------------------------------------------
public class Member 
{
    private String name;
    private int vote;
    private double ratio;
    private double finalShare;

    public Member(String name, int vote) 
    {
        this.name = name;
        this.vote = vote;
        this.ratio = 1.0D;
    }

    public String getName() 
    {
        return this.name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public int getVote() 
    {
        return this.vote;
    }

    public void setVote(int vote) 
    {
        this.vote = vote;
    }

    public double getRatio() 
    {
        return this.ratio;
    }

    public void setRatio(double ratio) 
    {
        this.ratio = ratio;
    }

    public double getFinalShare() 
    {
        return this.finalShare;
    }

    public void setFinalShare(double finalShare) 
    {
        this.finalShare = finalShare;
    }

    public String toString() 
    {
        return this.name + " , " + this.vote;
    }
}
