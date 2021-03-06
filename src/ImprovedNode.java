import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

class ImprovedNode implements Runnable {
    private static Channel channel = Channel.getInstance();
    private static int distance, stat = 0, frame;
    Thread t;
    private String StationNumber;
    private int FrameNumber, MaxFrameNumber;
    private int applicationType;
    private AtomicBoolean CheckIfSuccessfulTransmission;
    private int NumberOfAttempts;
    private int chosenStrategy;
    private NetworkVariables networkVariables;

    ImprovedNode(String threadname, int MaxFrameNumber, int applicationType) {
        StationNumber = threadname;
        t = new Thread(this, StationNumber);
        FrameNumber = 1;
        this.MaxFrameNumber = MaxFrameNumber;
        this.applicationType = applicationType;
        CheckIfSuccessfulTransmission = new AtomicBoolean();
        t.start();
    }

    public void run() {
        Random rand = new Random();
        QMatrix qMatrix = QMatrix.getInstance();
        while (!CheckIfSuccessfulTransmission.get()) {
            NumberOfAttempts++;
            while (FrameNumber <= MaxFrameNumber) {
                if (NumberOfAttempts < 15) { //15 is the maximum number of attempts
                    try {
                        if (channel.isInUse()) {

                            System.out.println(StationNumber + " is using Non-Persistent sensing, channel is busy");
                            try {
                                Thread.sleep(rand.nextInt(50) + 1000);
                            } catch (InterruptedException e) {
                                System.out.println(("Interrupt"));
                            }
                        } else {
                            //Successful transmission
                            System.out.println(StationNumber + " is trying to transmit frame number : " + FrameNumber);

                            if (NumberOfAttempts == 1) {
                                chosenStrategy = rand.nextInt(5);
                                chosenStrategy++;
                                System.out.println("The chosen strategy is: " + chosenStrategy);
                                networkVariables = new NetworkVariables(NumberOfAttempts, applicationType, chosenStrategy);
                            } else {
                                //look at the matrix and choose the most appropriate
                                chosenStrategy = qMatrix.getMostPopularStrategy();
                                networkVariables = new NetworkVariables(NumberOfAttempts, applicationType, chosenStrategy);

                            }

                            if (!channel.isInUse() && distance == 0) {
                                //increase the reward
                                System.out.println("The chosen strategy is: " + chosenStrategy);
                                qMatrix.rewardStrategy(chosenStrategy);
                                channel.setNumberOfSuccessfulTransmissions();
                                stat = CheckThreads.checking(Thread.currentThread().getName());
                                frame = this.FrameNumber;
                                channel.setInUse(true);//set channel to in use
                                for (; distance < networkVariables.getSendFileTime(); distance++)
                                    for (int i = 0; i < 1000; i++) ; //simulate transmission over some distance
                                System.out.println(StationNumber + " frame " + FrameNumber + " is successful");
                                CheckIfSuccessfulTransmission.set(true);
                                FrameNumber++;
                                distance = 0; //reset distance for next frame's transmission
                                channel.setInUse(false);
                            } else {
                                //record collision
                                //also reduce the reward of the thing
                                qMatrix.penalizeStrategy(chosenStrategy);
                                channel.setNumberOfCollisions();
                                System.out.println("Collision for frame " + FrameNumber + " of " +
                                        StationNumber + " and frame " + frame + " of Station " + stat);

                                System.out.println("Retransmitting Station " + stat + "'s frame " + frame);
                                CheckIfSuccessfulTransmission.set(false);
                                channel.setInUse(false);
                                NumberOfAttempts++;
                                try {
                                    Thread.sleep(networkVariables.getBackOffTime());
                                } catch (InterruptedException e) {
                                    System.out.println("Interrupted");
                                }
                            }
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        System.out.println(StationNumber + "Main Interrupted");
                    }
                } else {
                    CheckIfSuccessfulTransmission.set(true);
                    System.out.println("Too many attempts for frame " + FrameNumber + "of " +
                            StationNumber + ". Transmission stopped");
                }

            }
        }
    }
}
