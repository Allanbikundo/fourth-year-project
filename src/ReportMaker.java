import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

public class ReportMaker {


    //normal nodes
    //normalNodes
    private int normalNodesTime;
    private int normalNodesTotalNumberOfTrials;
    private int normalNodesnumberOfSuccessfulTransmissions;
    private int normalNodesnumberOfCollisions;
    private double normalNodesefficiency;
    //chaotic nodes
    //chaoticNodes
    private int chaoticNodesTime;
    private int chaoticNodesTotalNumberOfTrials;
    private int chaoticNodesnumberOfSuccessfulTransmissions;
    private int chaoticNodesnumberOfCollisions;
    private double chaoticNodesefficiency;

    //ai nodes
    //aiNodes
    private int aiNodesTime;
    private int aiNodesTotalNumberOfTrials;
    private int aiNodesnumberOfSuccessfulTransmissions;
    private int aiNodesNumberOfCollisions;
    private double aiNodesefficiency;

    private static ReportMaker singletonChannelInstance;

    public static ReportMaker getInstance() {
        if (singletonChannelInstance == null) {
            singletonChannelInstance = new ReportMaker();
        }
        return singletonChannelInstance;
    }

    public int getChaoticNodesTime() {
        return chaoticNodesTime;
    }

    public void setChaoticNodesTime(int chaoticNodesTime) {
        this.chaoticNodesTime = chaoticNodesTime;
    }

    public int getNormalNodesTime() {
        return normalNodesTime;
    }

    public void setNormalNodesTime(int normalNodesTime) {
        this.normalNodesTime = normalNodesTime;
    }

    public int getAiNodesTime() {
        return aiNodesTime;
    }

    public void setAiNodesTime(int aiNodesTime) {
        this.aiNodesTime = aiNodesTime;
    }

    public int getNormalNodesTotalNumberOfTrials() {
        return normalNodesTotalNumberOfTrials;
    }

    public void setNormalNodesTotalNumberOfTrials(int normalNodesTotalNumberOfTrials) {
        this.normalNodesTotalNumberOfTrials = normalNodesTotalNumberOfTrials;
    }

    public int getNormalNodesnumberOfSuccessfulTransmissions() {
        return normalNodesnumberOfSuccessfulTransmissions;
    }

    public void setNormalNodesnumberOfSuccessfulTransmissions(int normalNodesnumberOfSuccessfulTransmissions) {
        this.normalNodesnumberOfSuccessfulTransmissions = normalNodesnumberOfSuccessfulTransmissions;
    }

    public int getChaoticNodesTotalNumberOfTrials() {
        return chaoticNodesTotalNumberOfTrials;
    }

    public void setChaoticNodesTotalNumberOfTrials(int chaoticNodesTotalNumberOfTrials) {
        this.chaoticNodesTotalNumberOfTrials = chaoticNodesTotalNumberOfTrials;
    }

    public int getChaoticNodesnumberOfSuccessfulTransmissions() {
        return chaoticNodesnumberOfSuccessfulTransmissions;
    }

    public void setChaoticNodesnumberOfSuccessfulTransmissions(int chaoticNodesnumberOfSuccessfulTransmissions) {
        this.chaoticNodesnumberOfSuccessfulTransmissions = chaoticNodesnumberOfSuccessfulTransmissions;
    }

    public int getAiNodesTotalNumberOfTrials() {
        return aiNodesTotalNumberOfTrials;
    }

    public void setAiNodesTotalNumberOfTrials(int aiNodesTotalNumberOfTrials) {
        this.aiNodesTotalNumberOfTrials = aiNodesTotalNumberOfTrials;
    }

    public int getAiNodesnumberOfSuccessfulTransmissions() {
        return aiNodesnumberOfSuccessfulTransmissions;
    }

    public void setAiNodesnumberOfSuccessfulTransmissions(int aiNodesnumberOfSuccessfulTransmissions) {
        this.aiNodesnumberOfSuccessfulTransmissions = aiNodesnumberOfSuccessfulTransmissions;
    }

    public static ReportMaker getSingletonChannelInstance() {
        return singletonChannelInstance;
    }

    public static void setSingletonChannelInstance(ReportMaker singletonChannelInstance) {
        ReportMaker.singletonChannelInstance = singletonChannelInstance;
    }

    public int getNormalNodesnumberOfCollisions() {
        return normalNodesnumberOfCollisions;
    }

    public void setNormalNodesnumberOfCollisions(int normalNodesnumberOfCollisions) {
        this.normalNodesnumberOfCollisions = normalNodesnumberOfCollisions;
    }

    public int getChaoticNodesnumberOfCollisions() {
        return chaoticNodesnumberOfCollisions;
    }

    public void setChaoticNodesnumberOfCollisions(int chaoticNodesnumberOfCollisions) {
        this.chaoticNodesnumberOfCollisions = chaoticNodesnumberOfCollisions;
    }

    public int getAiNodesNumberOfCollisions() {
        return aiNodesNumberOfCollisions;
    }

    public void setAiNodesNumberOfCollisions(int aiNodesNumberOfCollisions) {
        this.aiNodesNumberOfCollisions = aiNodesNumberOfCollisions;
    }

    public double getNormalNodesefficiency() {
        return normalNodesefficiency;
    }

    public void setNormalNodesefficiency(double normalNodesefficiency) {
        this.normalNodesefficiency = normalNodesefficiency;
    }

    public double getChaoticNodesefficiency() {
        return chaoticNodesefficiency;
    }

    public void setChaoticNodesefficiency(double chaoticNodesefficiency) {
        this.chaoticNodesefficiency = chaoticNodesefficiency;
    }

    public double getAiNodesefficiency() {
        return aiNodesefficiency;
    }

    public void setAiNodesefficiency(double aiNodesefficiency) {
        this.aiNodesefficiency = aiNodesefficiency;
    }

    public void generateReport(){
        System.out.println("\n\n\n");
        System.out.println("REPORT");
        System.out.println("========================================");
        System.out.println("NORMAL CSMA/CD");
        System.out.println("========================================");
        System.out.println("Number of successful transmissions: " + getNormalNodesnumberOfSuccessfulTransmissions());
        System.out.println("Number of collisions: " + getNormalNodesnumberOfCollisions());
        System.out.println("total number of trials: " + getNormalNodesTotalNumberOfTrials());
        System.out.println("Efficiency: " + getNormalNodesefficiency() );
        System.out.println("========================================");
        System.out.println("\n\n");
        System.out.println("========================================");
        System.out.println("BAD CSMA/CD");
        System.out.println("========================================");
        System.out.println("Number of successful transmissions: " + getChaoticNodesnumberOfSuccessfulTransmissions());
        System.out.println("Number of collisions: " + getChaoticNodesnumberOfCollisions());
        System.out.println("total number of trials: " + getChaoticNodesTotalNumberOfTrials());
        System.out.println("Efficiency: " + getChaoticNodesefficiency());
        System.out.println("========================================");
        System.out.println("\n\n");
        System.out.println("========================================");
        System.out.println("ZACK'S");
        System.out.println("========================================");
        System.out.println("Number of successful transmissions: " + getAiNodesnumberOfSuccessfulTransmissions());
        System.out.println("Number of collisions: " + getAiNodesNumberOfCollisions());
        System.out.println("total number of trials: " + getAiNodesTotalNumberOfTrials());
        System.out.println("Efficiency: " + getAiNodesefficiency());
        System.out.println("========================================");
        System.out.println("\n\n");
        System.out.println("========================================");
        System.out.println("===============SUMMARY==================");
        System.out.println("========================================");
        System.out.println("Zack's took " + getAiNodesTime() + " seconds");

        System.out.println("CSMA/CD took " + getNormalNodesTime() + " seconds");

        System.out.println("Bad CSMA/CD took " + getChaoticNodesTime() + " seconds");
    }

    public void writeTheReportToTheFile(){
        try{
        FileWriter myWriter = new FileWriter("Summary Report-" + Instant.now().toEpochMilli() + ".txt");
            myWriter.write("REPORT\n");
            myWriter.write("========================================\n");
            myWriter.write("NORMAL CSMA/CD\n");
            myWriter.write("========================================\n");
            myWriter.write("Number of successful transmissions: " + getNormalNodesnumberOfSuccessfulTransmissions() + "\n");
            myWriter.write("Number of collisions: " + getNormalNodesnumberOfCollisions() + "\n");
            myWriter.write("total number of trials: " + getNormalNodesTotalNumberOfTrials() + "\n");
            myWriter.write("Efficiency: " + getNormalNodesefficiency()+ "\n");
            myWriter.write("========================================\n");
            myWriter.write("\n\n");

            myWriter.write("========================================\n");
            myWriter.write("BAD CSMA/CD");
            myWriter.write("========================================\n");

            myWriter.write("Number of successful transmissions: " + getChaoticNodesnumberOfSuccessfulTransmissions() + "\n");
            myWriter.write("Number of collisions: " + getChaoticNodesnumberOfCollisions() + "\n");
            myWriter.write("total number of trials: " + getChaoticNodesTotalNumberOfTrials() + "\n");
            myWriter.write("Efficiency: " + getChaoticNodesefficiency()+ "\n");

            myWriter.write("========================================\n");
            myWriter.write("\n\n");
            myWriter.write("========================================\n");
            myWriter.write("ZACK'S");
            myWriter.write("========================================\n");
            myWriter.write("Number of successful transmissions: " + getAiNodesnumberOfSuccessfulTransmissions() + "\n");
            myWriter.write("Number of collisions: " + getAiNodesNumberOfCollisions() + "\n");
            myWriter.write("total number of trials: " + getAiNodesTotalNumberOfTrials() + "\n");
            myWriter.write("Efficiency: " + getAiNodesefficiency());
            myWriter.write("========================================\n");
            myWriter.write("\n\n");
            myWriter.write("========================================\n");
            myWriter.write("===============SUMMARY==================\n");
            myWriter.write("========================================\n");
            myWriter.write("Zack's took " + getAiNodesTime() + " seconds\n");

            myWriter.write("CSMA/CD took " + getNormalNodesTime() + " seconds\n");

            myWriter.write("Bad CSMA/CD took " + getChaoticNodesTime() + " seconds\n");
        myWriter.close();
            System.out.println("Successfully wrote to the file.");
        }catch (IOException err){
            System.out.println(err);
        }

    }}
