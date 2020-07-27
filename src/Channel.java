public class Channel {
    private static Channel singletonChannelInstance;
    private boolean inUse;
    private int numberOfCollisions;
    private int numberOfSuccessfulTransmissions;

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }


    public static Channel getInstance() {
        if (singletonChannelInstance == null) {
            singletonChannelInstance = new Channel();
        }
        return singletonChannelInstance;
    }

    public void resetInstance() {
        singletonChannelInstance = new Channel();
    }

    public int getNumberOfCollisions() {
        return numberOfCollisions;
    }

    public void setNumberOfCollisions() {
        this.numberOfCollisions++;
    }

    public int getNumberOfSuccessfulTransmissions() {
        return numberOfSuccessfulTransmissions;
    }

    public void setNumberOfSuccessfulTransmissions() {
        this.numberOfSuccessfulTransmissions++;
    }

    //add thread ud
}
