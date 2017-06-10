package Players.ContextPlayers;


import Actions.ContextAction.ClientAction;
import Players.Status;

public abstract class Mafia extends Player<ClientAction> {
    @Override
    public void doAction(ClientAction action) {
        action.performAction();
    }
}
