package eu.tutorial.objects;

public class ObjScore {
    private int id;
    private String IP;
    private String SRC;
    private double score;

    private ObjScore(int i, String ip, String src, double iScore) {
        id = i;
        IP = ip;
        SRC = src;
        score = iScore;
    }

    // IP-based objects
    public static ObjScore createWithIP(int id, String ip, double score) {
        return new ObjScore(id, ip, null, score);
    }

    // SRC-based objects
    public static ObjScore createWithSRC(int id, String src, double score) {
        return new ObjScore(id, null, src, score);
    }

    @Override
    public String toString() {
        return "id=" + id + 
               (IP != null ? " IP=" + IP : "") + 
               (SRC != null ? " SRC=" + SRC : "") + 
               " score=" + score;
    }

    public int getId() {
        return this.id;
    }

    public String getIP() {
        return this.IP;
    }

    public String getSRC() {
        return this.SRC;
    }

    public double getScore() {
        return this.score;
    }

    public void setId(int iId) {
        this.id = iId;
    }

    public void setIP(String iIP) {
        this.IP = iIP;
    }

    public void setSRC(String iSRC) {
        this.SRC = iSRC;
    }

    public void setScore(double iS) {
        this.score = iS;
    }
}
