package GameLogic;


import Players.ConcretePlayers.ConcreteCivilianPlayers.Citizen;
import Players.ConcretePlayers.ConcreteCivilianPlayers.Commissar;
import Players.ConcretePlayers.ConcreteMafiaPlayers.Don;
import Players.ContextPlayers.Player;

import java.util.*;

public class GameLogic {
    private static final int minPlayerNumber = 3;
    private static final int maxPlayerNumber = 10;
    private static final int calcFactor = 4;
    private static final int CivilianRolesNumber = 2;
    private static final int MafiaRolesNumber = 1;
    private static Map<Integer, Player> playerMap = new TreeMap<>();
    private static List<Player> players = new ArrayList<>();
    private static GameLogic gameLogic = new GameLogic();
    private static int numberOfPlayers;
    private static int numberOfMafiaPlayers = (int) Math.ceil(numberOfPlayers / calcFactor);

    private GameLogic() {
    }

    public static void generatePlayerRoles(int numberOfPlayers) {
        GameLogic.numberOfPlayers = numberOfPlayers;
        createPlayers();
        createMafiaPlayers();
        createCommissar();
    }

    private static void createCommissar() {
        Random random = new Random();
        int index = random.nextInt(numberOfPlayers);
        while (true) {
            if (players.get(index) instanceof Citizen) {
                players.set(index, new Commissar());
                break;
            }
        }
    }

    private static void createMafiaPlayers() {
        Integer[] indexesOfMafiaPlayers = getIndexesOfMafiaPlayers();
        for (int i = 0; i < numberOfMafiaPlayers; i++) {
            players.set(indexesOfMafiaPlayers[i], new Don());
        }
    }

    private static void createPlayers() {
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Citizen());
        }
    }

    private static Integer[] getIndexesOfMafiaPlayers() {
        Integer[] arr = new Integer[numberOfPlayers];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Collections.shuffle(Arrays.asList(arr));
        Integer[] indexes = new Integer[numberOfMafiaPlayers];
        for (int i = 0; i < numberOfMafiaPlayers; i++) {
            indexes[i] = arr[i];
        }
        return indexes;
    }
}
