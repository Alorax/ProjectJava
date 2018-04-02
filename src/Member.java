//----------------------------------------------------------------
// Stores the information for each member
//----------------------------------------------------------------
public class Member {

    String name;
    int vote;

    public Member(String name, int vote) 
    {
        this.name = name;
        this.vote = vote;
        this.ratio = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) 
    {
        this.vote = vote;
    }
    
     public double getRatio() 
     {
        return ratio;
    }

    public void setRatio(double ratio)
    {
        this.ratio = ratio;
    }

    public double getFinalShare() 
    {
        return finalShare;
    }

    public void setFinalShare(double finalShare) 
    {
        this.finalShare = finalShare;
    }
    @Override
    public String toString() 
    {
        return name + " , " + vote;
    }
}
