import Actions.ConcreteActions.AdminActions.MuteAction;
import Actions.ConcreteActions.ClientActions.KillAction;
import Actions.ContextAction.Action;
import Actions.ContextAction.ClientAction;
import GameLogic.Phase;
import Players.ConcretePlayers.ConcreteCivilianPlayers.Citizen;
import Players.ContextPlayers.Player;

public class Test {
    public static void main(String[] args) {
        Action adminAction = new MuteAction();
        Action clientAction = new KillAction();

        Player client = new Citizen();
        client.setAction(adminAction);
    }
}
