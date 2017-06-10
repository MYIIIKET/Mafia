package Actions.ConcreteActions.ClientActions;


import Actions.ContextAction.Action;
import Actions.ContextAction.ClientAction;

public class CheckPlayerAction extends ClientAction {
    @Override
    public void performAction() {
        System.out.println("Check");
    }
}
