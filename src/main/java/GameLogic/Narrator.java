package GameLogic;


import Actions.ContextAction.Action;
import Actions.ContextAction.AdminAction;
import Players.ContextPlayers.Player;
import Players.Status;

public class Narrator extends Player<AdminAction> {
    public void say() {

    }

    public void setAction(AdminAction action) {
        action.performAction();
    }


    public void setStatus(Status status) {

    }
}
