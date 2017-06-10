package Players.ContextPlayers;

import Actions.ContextAction.Action;
import Actions.ContextAction.ClientAction;
import Players.Status;

public abstract class Player<someAction extends Action> {
    public void say() {

    }

    public abstract void setAction(someAction action);

    public abstract void setStatus(Status status);
}
