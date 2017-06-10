package Players.ContextPlayers;

import Actions.ContextAction.Action;
import Players.Status;

public abstract class Player {
    public void say() {

    }

    public abstract void setAction(Action action);

    public abstract void setStatus(Status status);
}
