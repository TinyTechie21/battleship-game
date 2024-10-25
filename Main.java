import java.util.Scanner;

/**
 * Main class for a GameSystem application.
 *
 * @author - Pâmela Cuna 
 */

public class Main {

    /**
     * User's commands.
     */
    public static final String SCORE = "score";
    public static final String FLEET = "fleet";
    public static final String QUIT = "quit";
    private static final String PLAYER = "player";
    private static final String SHOOT = "shoot";

    /**
     * Feedback from the program.
     */
    private static final String GAME_OVER = "The game is over\n";
    private static final String NEXT_PLAYER = "Next player: %s\n";
    private static final String INVALID_CMD = "Invalid command\n";
    private static final String NON_EXISTENT_PLAYER = "Nonexistent player\n";
    private static final String POINTS_PLAYER = "%s has %d points\n";
    private static final String INVALID_SHOT = "Invalid shot\n";
    private static final String GAME_NOT_OVER = "The game was not over yet...\n";
    private static final String WON_GAME = "%s won the game!\n";

    public static final int ONE = 1;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        GameSystem gs = createGameSystem(in);
        processCmd(in, gs);

        in.close();
    }

    /**
     * Command's interpreter.
     *
     * @param in - the input where the data is going to be read.
     * @param gs - the GameSystem in which we'll start the game.
     */
    private static void processCmd(Scanner in, GameSystem gs) {
        String cmd;

        do {
            cmd = in.next();
            switch (cmd) {
                case PLAYER:
                    executePlayer(gs);
                    break;
                case SCORE:
                    executeScore(in, gs);
                    break;
                case FLEET:
                    executeFleet(in, gs);
                    break;
                case SHOOT:
                    executeShoot(in, gs);
                    break;
                case QUIT:
                    executeQuit(gs);
                    break;
                default:
                    executeInvalidCmd(in);
            }
        } while (!cmd.equals(QUIT));

    }

    /**
     * Executes an invalid command.
     *
     * @param in - the input on which the data is going to be read.
     */
    private static void executeInvalidCmd(Scanner in) {
        System.out.printf(INVALID_CMD);
        in.nextLine();
    }

    /**
     * Quits the game.
     *
     * @param gs - the GameSystem on which the game is going to quit.
     */
    private static void executeQuit(GameSystem gs) {
        if (!gs.isGameOver())
            System.out.printf(GAME_NOT_OVER);
        else
            System.out.printf(WON_GAME, gs.getWinnerName());
    }

    /**
     * Shoots a player's position.
     *
     * @param in - the input on which the data is going to be read.
     * @param gs - the GameSystem on which the game is going to shoot.
     */
    private static void executeShoot(Scanner in, GameSystem gs) {
        int position = in.nextInt();
        in.nextLine();

        if (gs.isGameOver())
            System.out.printf(GAME_OVER);
        else if (position < ONE || position > gs.getFleetLength())
            System.out.printf(INVALID_SHOT);
        else
            gs.shoot(position);
    }

    /**
     * Displays the fleet of a player.
     *
     * @param in - the input on which the data is going to be read.
     * @param gs - the GameSystem on which a fleet is to be displayed.
     */
    private static void executeFleet(Scanner in, GameSystem gs) {
        String player_name = in.nextLine().trim();

        if (!gs.isExistentPlayer(player_name))
            System.out.printf(NON_EXISTENT_PLAYER);
        else
            System.out.println(gs.getPlayerFleet(player_name));
    }

    /**
     * Displays the score of a player.
     *
     * @param in - the input on which the data is going to be read.
     * @param gs - the GameSystem on which the player's score is to be displayed.
     */
    private static void executeScore(Scanner in, GameSystem gs) {
        String player_name = in.nextLine().trim();

        if (!gs.isExistentPlayer(player_name))
            System.out.printf(NON_EXISTENT_PLAYER);
        else
            System.out.printf(POINTS_PLAYER, player_name, gs.getPlayerScore(player_name));
    }

    /**
     * Displays the name of the next player.
     *
     * @param gs - the GameSystem on which the next player's name is to be displayed.
     */
    private static void executePlayer(GameSystem gs) {
        if (gs.isGameOver())
            System.out.printf(GAME_OVER);
        else
            System.out.printf(NEXT_PLAYER, gs.getCurrentPlayerName());
    }

    /**
     * Creates a GameSystem with two players and two fleets.
     *
     * @param in - the input on which the data is going to be read.
     * @return a GameSystem with two players and their fleets.
     */
    private static GameSystem createGameSystem(Scanner in) {
        String player_name1 = in.nextLine().trim();
        String fleet1 = in.nextLine().trim();
        String player_name2 = in.nextLine().trim();
        String fleet2 = in.nextLine().trim();

        return new GameSystem(player_name1, fleet1, player_name2, fleet2);
    }
}
