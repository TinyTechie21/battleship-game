/**
 * GameSystem class for a game application.
 *
 * @author - Pâmela Cuna 63560
 */
public class GameSystem {
    /**
     * Constants and instance variables
     */
    private static final int GAINED_POINTS = 100;
    private static final int LOST_POINTS = 30;

    private Player p1;
    private Player p2;
    private Player current_player;
    private Player opponent;

    /**
     * Initializes the object with values it has received.
     *
     * @param player_name1 - the name of the first player.
     * @param fleet1       - the fleet of the first player.
     * @param player_name2 - the name of the second player.
     * @param fleet2       - the fleet of the second player.
     * @pre - player_name1 != null && fleet != null && player_name2 != null && fleet2 != null
     */
    public GameSystem(String player_name1, String fleet1, String player_name2, String fleet2) {
        p1 = new Player(player_name1, fleet1);
        p2 = new Player(player_name2, fleet2);
        current_player = p1;
        opponent = p2;
    }

    /**
     * Returns <code>true</code> if the game is over, or <code>false</code> otherwise.
     *
     * @return if the game is over.
     */
    public boolean isGameOver() {
        return p1.hasFleetSank() || p2.hasFleetSank();
    }

    /**
     * The value next player variable switches to the current.
     */
    private void switchNextPlayerToCurrent() {

        if (current_player.getName().equals(p1.getName())) {
            current_player = p2;
            opponent = p1;
        } else {
            current_player = p1;
            opponent = p2;
        }
    }

    /**
     * Returns the name of the current player.
     *
     * @return name of the current player.
     */
    public String getCurrentPlayerName() {
        return current_player.getName();
    }

    /**
     * Returns <code>true</code> if the given player name exists, or <code>false</code> otherwise.
     *
     * @param player_name - the name of the player that is going to be compared.
     * @return if the given name equals one of the player's name from the game.
     */
    public boolean isExistentPlayer(String player_name) {
        return player_name.equals(p1.getName()) || player_name.equals(p2.getName());
    }

    /**
     * Returns the score of the given player name.
     *
     * @param player_name - the given name of the player.
     * @return the score of the player with the given name.
     * @pre - this.isExistentPlayer(player_name)
     */
    public int getPlayerScore(String player_name) {
        if (player_name.equals(p1.getName()))
            return p1.getPoints();
        else
            return p2.getPoints();
    }

    /**
     * Returns the fleet of the player with the given name.
     *
     * @param player_name - the name of the player.
     * @return the fleet of the player.
     * @pre - this.isExistentPlayer(player_name)
     */
    public String getPlayerFleet(String player_name) {
        if (player_name.equals(p1.getName()))
            return new String(p1.getFleet());
        else
            return new String(p2.getFleet());
    }

    /**
     * Returns the name of the winner.
     *
     * @return the name of the winner.
     */
    public String getWinnerName() {
        if (p1.getPoints() == p2.getPoints()) {
            return winnerName();
        } else if (p1.getPoints() > p2.getPoints()) {
            return p1.getName();
        } else
            return p2.getName();

    }

    /**
     * Returns the name of the winner when they have the same number of points.
     *
     * @return the name of the player who sank the opponent's boats first.
     */
    private String winnerName() {
        if (p1.hasFleetSank())
            return p2.getName();
        else
            return p1.getName();
    }

    /**
     * Shoots in the given position on the fleet of the opponent.
     *
     * @param position - the position on which the shoot is going to be.
     */
    public void shoot(int position) {
        int points = opponent.shootPos(position);

        if (points > 0) {
            current_player.updatePoints(points * GAINED_POINTS);
        } else {
            current_player.updatePoints(points * LOST_POINTS);
        }
        switchNextPlayerToCurrent();

    }

    /**
     * Returns the fleet of the current player.
     *
     * @return the fleet of the current player.
     */
    public int getFleetLength() {
        return current_player.getFleetLength();
    }
}


