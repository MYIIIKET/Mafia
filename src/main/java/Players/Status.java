package Players;


import java.io.Serializable;

public class Status implements Serializable {
    private boolean isAlive = true;

    public boolean isAlive() {
        return isAlive;
    }

    public void toggleAlive() {
        isAlive = !isAlive;
    }
}
