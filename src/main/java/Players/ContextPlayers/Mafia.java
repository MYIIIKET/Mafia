package Players.ContextPlayers;


import Actions.ContextAction.ClientAction;

public abstract class Mafia extends Player<ClientAction> {
    @Override
    public void setAction(ClientAction action) {
        action.performAction();
    }
}
