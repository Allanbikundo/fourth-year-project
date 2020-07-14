public class QMatrix {

    private static QMatrix single_instance = null;
    private int[] qMatrix = new int[6];

    public static QMatrix getInstance() {
        if (single_instance == null)
            single_instance = new QMatrix();

        return single_instance;
    }

    public void penalizeStrategy(int i) {
        i--;
        this.qMatrix[i] = this.qMatrix[i] - 10;
    }

    public void rewardStrategy(int i) {
        i--;
        this.qMatrix[i] = this.qMatrix[i] + 10;
    }

    public int getMostPopularStrategy() {
        int max = qMatrix[0];
        int indexOfCurrentMax = 0;
        for (int i = 1; i < qMatrix.length; i++) {
            if (qMatrix[i] > max) {
                max = qMatrix[i];
                indexOfCurrentMax = i;
            }
        }
        indexOfCurrentMax++;
        return indexOfCurrentMax;
    }
}
