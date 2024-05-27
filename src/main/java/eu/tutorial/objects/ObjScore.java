package eu.tutorial.objects;

public class ObjScore {
    private int id ;
    private String IP;
    private double score ;

    public ObjScore(int i, String iIP, double iScore){
        id= i;
        IP = iIP;
        score = iScore;
    }

    public String toString(){
        return "id="+id+ " IP="+ IP + " score=" +score;
    }


    public int getId(){
        return this.id;
    }
    public String getIP(){
        return this.IP;
    }
    public double getScore(){
        return this.score;
    }

    public void setId(int iId){
        this.id=iId;
    }
    public void setIP(String iIP){
        this.IP=iIP;
    }
    public void setScore(double iS){
        this.score=iS;
    }



}
