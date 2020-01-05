import java.util.Scanner;


class CSMACD {
    private static Channel channel = Channel.getInstance();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        channel.setInUse(false);
        System.out.println("Enter number of nodes");
        int NumberOfStations = sc.nextInt();
        NewThread[] ArrayOfObjects = new NewThread[NumberOfStations + 1];
        int[] FrameArray = new int[NumberOfStations + 1];
        for(int i = 1;i<=NumberOfStations;i++)
        {
//            System.out.println("Enter number of frames for Station " + i);
            FrameArray[i] = 10;

        }

        for(int i = 1;i<=NumberOfStations;i++)

            ArrayOfObjects[i] = new NewThread("Station "+ i,FrameArray[i]);


        try {
// wait for stations to complete transmission
           for(int i=1;i<=NumberOfStations;i++)
                ArrayOfObjects[i].t.join();
        }
        catch (InterruptedException e) {
            System.out.println("Main Thread Interrupted");
        }
        System.out.println("Number of successful transmissions: " + channel.getNumberOfSuccessfulTransmissions());
        System.out.println("Number of collisions: " + channel.getNumberOfCollisions());
        System.out.println("total number of trials: " + (channel.getNumberOfSuccessfulTransmissions() + channel.getNumberOfCollisions()));
//        System.out.println("");
        System.out.println("Transmission completed.");
    }
}
