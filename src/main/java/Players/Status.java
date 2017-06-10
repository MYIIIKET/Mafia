package Players;


public class Status {
    private boolean isAlive = true;

    public boolean isAlive() {
        return isAlive;
    }

    public void toggleAlive() {
        isAlive = !isAlive;
    }
}
