package Players.ContextPlayers;

import Actions.ContextAction.Action;
import Players.Status;

import java.io.Serializable;

public abstract class Player<someAction extends Action> implements Serializable {

    public Status status = new Status();

    public void say() {
        if (!getStatus().isAlive()) {
            return;
        }
    }

    public void toggleStatus() {
        getStatus().toggleAlive();
    }

    public Status getStatus() {
        return status;
    }

    public abstract void doAction(someAction action);
}
