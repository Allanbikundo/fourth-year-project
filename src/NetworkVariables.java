import java.util.Random;

public class NetworkVariables {

    private double contentionWindow;
    private int tfr = 50;
    private int r;
    private int backOffTime;
    private int sendFileTime;

    NetworkVariables(int NumberOfAttempts, int applicationType, int chosenStrategy) {
        Random rand = new Random();
        switch (chosenStrategy) {
            case 1:
//              Standard cw size and a slower rate of increase of the BO window
                System.out.println("standard cw size and a slower rate of increase of the BO window\n");
                this.contentionWindow = Math.pow(2, NumberOfAttempts - 1);
                this.r = rand.nextInt((int) this.contentionWindow);
                this.backOffTime = ((int) ((r * this.tfr) * 0.05));
                sendFileTime(applicationType);
                break;
            case 2:
                //Standard cw size and fixed BO window
                System.out.println("standard cw size and fixed BO window\n");
                this.contentionWindow = Math.pow(2, NumberOfAttempts - 1);
                this.r = rand.nextInt((int) this.contentionWindow);
                this.backOffTime = 25;
                sendFileTime(applicationType);
                break;
            case 3:
//                Standard cw size and standard rate of increase of the BO window
                System.out.println("standard cw size and standard rate of increase of the BO window\n");
                this.contentionWindow = Math.pow(2, NumberOfAttempts - 1);
                this.r = rand.nextInt((int) this.contentionWindow);
                this.backOffTime = r * this.tfr;

                sendFileTime(applicationType);
                break;
            case 4:
//              Small cw size and a slower rate of increase of the BO window
                System.out.println("Small cw size and a slower rate of increase of the BO window\n");
                this.contentionWindow = Math.pow(2, NumberOfAttempts - 1) / 2;
                if (this.contentionWindow < 1) this.contentionWindow = 1;
                this.r = rand.nextInt((int) this.contentionWindow);
                this.backOffTime = ((int) ((r * this.tfr) * 0.05));
                sendFileTime(applicationType);
                break;
            case 5:
                //Small cw size and fixed BO window
                System.out.println("Small cw size and fixed BO window\n");
                this.contentionWindow = Math.pow(2, NumberOfAttempts - 1) / 2;
                if (this.contentionWindow < 1) this.contentionWindow = 1;
                this.r = rand.nextInt((int) this.contentionWindow);
                this.backOffTime = 25;
                sendFileTime(applicationType);
                break;
            case 6:
//                Small cw size and standard rate of increase of the BO window
                System.out.println("Small cw size and standard rate of increase of the BO window\n");
                this.contentionWindow = Math.pow(2, NumberOfAttempts - 1) / 2;
                if (this.contentionWindow < 1) this.contentionWindow = 1;
                this.r = rand.nextInt((int) this.contentionWindow);
                this.backOffTime = r * this.tfr;
                sendFileTime(applicationType);
                break;
            default:
                System.out.println("Strategy does not exist therefore we shall fall back to the standard one\n");
                this.contentionWindow = Math.pow(2, NumberOfAttempts - 1);
                this.r = rand.nextInt((int) this.contentionWindow);
                this.backOffTime = r * this.tfr;
                sendFileTime(applicationType);
//                 code block
        }

    }

    public int getBackOffTime() {
        return backOffTime;
    }

    public void setBackOffTime(int backOffTime) {
        this.backOffTime = backOffTime;
    }

    public int getSendFileTime() {
        return sendFileTime;
    }

    private void sendFileTime(int applicationType) {
        if (applicationType == 1) {
            this.sendFileTime = 90000000;
        } else {
            this.sendFileTime = 9000000;
        }
    }

    private void strategyOne() {

    }
}
