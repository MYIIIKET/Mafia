package Players.ContextPlayers;


import Actions.ContextAction.ClientAction;

public abstract class Civilian extends Player<ClientAction> {

    @Override
    public void doAction(ClientAction action) {
        action.performAction();
    }

}
