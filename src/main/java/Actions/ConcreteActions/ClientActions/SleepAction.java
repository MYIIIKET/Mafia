package Actions.ConcreteActions.ClientActions;


import Actions.ContextAction.Action;
import Actions.ContextAction.ClientAction;

public class SleepAction extends ClientAction {
    @Override
    public void performAction() {
        System.out.println("Sleep");
    }
}
