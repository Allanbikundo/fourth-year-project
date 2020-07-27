import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.Scanner;

class CSMACD {
    private static Channel channel = Channel.getInstance();
    private static ReportMaker reportMaker = ReportMaker.getInstance();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        channel.setInUse(false);
        System.out.println("Enter number of nodes");
        //choose application
        int NumberOfStations = sc.nextInt();
        System.out.println("Network Details: ");
//        System.out.println("Network Size: 4 , 7 and 13 nodes");
        System.out.println("Bandwidth 11 Mbps");
        //have an array of nodes and test for each
        System.out.println("Please select what you want to pass through the network \n 1. File Transfer (Heavy) \n 2. Database Access (Light)");
        int chosenApplication = sc.nextInt();
        while (!(chosenApplication == 1) && !(chosenApplication == 2)) {
            System.out.println("That's an invalid selection please choose an appropriate application");
            chosenApplication = sc.nextInt();
        }
        normalCSMA(NumberOfStations, chosenApplication);
        aicsma(NumberOfStations, chosenApplication);
        badCSMA(NumberOfStations,chosenApplication);
        reportMaker.generateReport();
        reportMaker.writeTheReportToTheFile();
    }

    private static void normalCSMA(int NumberOfStations, int applicationType) {
        try {
            long startTime = System.nanoTime();
            Node[] ArrayOfObjects = new Node[NumberOfStations + 1];
            int[] FrameArray = new int[NumberOfStations + 1];
            FileWriter frameLogs = new FileWriter("FrameLogs - " + Instant.now().toEpochMilli() + ".txt");
            for (int i = 1; i <= NumberOfStations; i++) {
                FrameArray[i] = 10;
                frameLogs.write("The number of frames for Station " + i + " are " + FrameArray[i] + "\n");
                System.out.println("The number of frames for Station " + i + " are " + FrameArray[i]);


            }
            frameLogs.close();


            for (int i = 1; i <= NumberOfStations; i++)

                ArrayOfObjects[i] = new Node("Station " + i, FrameArray[i], applicationType);


            try {
                // wait for stations to complete transmission
                for (int i = 1; i <= NumberOfStations; i++)
                    ArrayOfObjects[i].t.join();
            } catch (InterruptedException e) {
                System.out.println("Main Thread Interrupted");
            }
            System.out.println("Number of successful transmissions: " + channel.getNumberOfSuccessfulTransmissions());
            System.out.println("Number of collisions: " + channel.getNumberOfCollisions());
            System.out.println("total number of trials: " + (channel.getNumberOfSuccessfulTransmissions() + channel.getNumberOfCollisions()));
            System.out.println("Efficiency " + ((double)channel.getNumberOfSuccessfulTransmissions()/(channel.getNumberOfSuccessfulTransmissions() + channel.getNumberOfCollisions())));
            System.out.println("Transmission completed.");
            long endTime = System.nanoTime();
            long duration = ((endTime - startTime) / 1000000000);
            System.out.println("========================================");
            System.out.println("CSMA/CD took " + duration + " seconds");
            System.out.println("========================================");
            reportMaker.setNormalNodesnumberOfSuccessfulTransmissions(channel.getNumberOfSuccessfulTransmissions());
            reportMaker.setNormalNodesnumberOfCollisions(channel.getNumberOfCollisions());
            reportMaker.setNormalNodesTotalNumberOfTrials(channel.getNumberOfSuccessfulTransmissions() + channel.getNumberOfCollisions());
            reportMaker.setNormalNodesefficiency((double)channel.getNumberOfSuccessfulTransmissions()/(channel.getNumberOfSuccessfulTransmissions() + channel.getNumberOfCollisions()));
            reportMaker.setNormalNodesTime((int)duration);
        } catch (
                IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    private static void aicsma(int NumberOfStations, int chosenApplication) {
        try {
//            channel.resetInstance();
            long startTime = System.nanoTime();
            ImprovedNode[] ArrayOfObjects = new ImprovedNode[NumberOfStations + 1];
            int[] FrameArray = new int[NumberOfStations + 1];

            for (int i = 1; i <= NumberOfStations; i++) {
                FrameArray[i] = 10;
                System.out.println("The number of frames for Station " + i + " are " + FrameArray[i]);
            }
            for (int i = 1; i <= NumberOfStations; i++)

                ArrayOfObjects[i] = new ImprovedNode("Station " + i, FrameArray[i], chosenApplication);


            try {
                // wait for stations to complete transmission
                for (int i = 1; i <= NumberOfStations; i++)
                    ArrayOfObjects[i].t.join();
            } catch (InterruptedException e) {
                System.out.println("Main Thread Interrupted");
            }

            FileWriter myWriter = new FileWriter(Instant.now().toEpochMilli() + ".txt");
            myWriter.write("{\n" +
                    "  \"totalNumberOfTrials\": " + (channel.getNumberOfSuccessfulTransmissions() + channel.getNumberOfCollisions()) + ",\n" +
                    "  \"numberOfCollisions\": " + channel.getNumberOfCollisions() + ",\n" +
                    "  \"numberOfSuccessfulTransmissions\":" + channel.getNumberOfSuccessfulTransmissions() + ",\n" +
                    "  \"numberOfNodes\":" + NumberOfStations + "\n" +
                    "  \n" +
                    "}");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");

            System.out.println("Number of successful transmissions: " + channel.getNumberOfSuccessfulTransmissions());
            System.out.println("Number of collisions: " + channel.getNumberOfCollisions());
            System.out.println("total number of trials: " + (channel.getNumberOfSuccessfulTransmissions() + channel.getNumberOfCollisions()));
            System.out.println("Efficiency " + ((double)channel.getNumberOfSuccessfulTransmissions()/(channel.getNumberOfSuccessfulTransmissions() + channel.getNumberOfCollisions())));
            System.out.println("Transmission completed.");
            long endTime = System.nanoTime();
            long duration = ((endTime - startTime) / 1000000000);
            System.out.println("========================================");
            System.out.println("Zack's took " + duration + " seconds");
            System.out.println("========================================");
            reportMaker.setAiNodesnumberOfSuccessfulTransmissions(channel.getNumberOfSuccessfulTransmissions());
            reportMaker.setAiNodesNumberOfCollisions(channel.getNumberOfCollisions());
            reportMaker.setAiNodesTotalNumberOfTrials(channel.getNumberOfSuccessfulTransmissions() + channel.getNumberOfCollisions());
            reportMaker.setAiNodesefficiency((double)channel.getNumberOfSuccessfulTransmissions()/(channel.getNumberOfSuccessfulTransmissions() + channel.getNumberOfCollisions()));
            reportMaker.setAiNodesTime((int)duration);
        } catch (
                IOException e) {
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    private static void badCSMA(int NumberOfStations, int applicationType) {
        try {
//            channel.resetInstance();
            long startTime = System.nanoTime();
            ChaoticNodes[] ArrayOfObjects = new ChaoticNodes[NumberOfStations + 1];
            int[] FrameArray = new int[NumberOfStations + 1];

            for (int i = 1; i <= NumberOfStations; i++) {
                FrameArray[i] = 10;
                System.out.println("The number of frames for Station " + i + " are " + FrameArray[i]);
            }

            for (int i = 1; i <= NumberOfStations; i++)

                ArrayOfObjects[i] = new ChaoticNodes("Station " + i, FrameArray[i], applicationType);


            try {
                // wait for stations to complete transmission
                for (int i = 1; i <= NumberOfStations; i++)
                    ArrayOfObjects[i].t.join();
            } catch (InterruptedException e) {
                System.out.println("Main Thread Interrupted");
            }


            System.out.println("Successfully wrote to the file.");

            System.out.println("Number of successful transmissions: " + channel.getNumberOfSuccessfulTransmissions());
            System.out.println("Number of collisions: " + channel.getNumberOfCollisions());
            System.out.println("total number of trials: " + (channel.getNumberOfSuccessfulTransmissions() + channel.getNumberOfCollisions()));
            System.out.println("Efficiency " + ((double)channel.getNumberOfSuccessfulTransmissions()/(channel.getNumberOfSuccessfulTransmissions() + channel.getNumberOfCollisions())));
            System.out.println("Transmission completed.");
            long endTime = System.nanoTime();
            long duration = ((endTime - startTime) / 1000000000);

            System.out.println("========================================");
            System.out.println("The bad nodes took " + duration + " seconds");
            System.out.println("========================================");
            reportMaker.setChaoticNodesnumberOfSuccessfulTransmissions(channel.getNumberOfSuccessfulTransmissions());
            reportMaker.setChaoticNodesnumberOfCollisions(channel.getNumberOfCollisions());
            reportMaker.setChaoticNodesTotalNumberOfTrials(channel.getNumberOfSuccessfulTransmissions() + channel.getNumberOfCollisions());
            reportMaker.setChaoticNodesefficiency((double)channel.getNumberOfSuccessfulTransmissions()/(channel.getNumberOfSuccessfulTransmissions() + channel.getNumberOfCollisions()));
            reportMaker.setChaoticNodesTime((int)duration);
        } catch (Error e) {
            System.out.println("An error occurred");
        }
    }
}
