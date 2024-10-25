/**
 * Player class for a game application.
 *
 * @author - Pâmela Cuna 63560
 */
public class Player {
    /**
     * Constants and instance variables
     */
    private static final char WATER = '.';
    private static final char SHOT = '*';

    private String name;
    private char[] fleet;
    private char[] reference_fleet;
    private int points;

    /**
     * Initializes a player object with a name and a fleet.
     *
     * @param name  - the name of the player.
     * @param fleet - the fleet of the player.
     * @pre - name != null && fleet != null
     */
    public Player(String name, String fleet) {
        this.name = name;
        this.fleet = fleet.toCharArray();
        this.reference_fleet = fleet.toCharArray();
        points = 0;
    }

    /**
     * Returns the name of the player.
     *
     * @return name of player.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the fleet of a player.
     *
     * @return fleet of a player.
     */
    public char[] getFleet() {
        return fleet;
    }

    /**
     * Returns the fleet length.
     *
     * @return the fleet length.
     */
    public int getFleetLength() {
        return fleet.length;
    }

    /**
     * Returns the points of a player.
     *
     * @return the points of a player.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Updates the points of a player.
     *
     * @param points the amount of points that are going to be added.
     */
    public void updatePoints(int points) {
        this.points += points;
    }

    /**
     * Shoots a given position and calculates the size of the boat.
     *
     * @param position the position to be shot.
     * @return the number of pipes a boat has.
     */
    public int shootPos(int position) {
        position = position - 1;
        int boat_pipe = 0;
        char aux = fleet[position];

        if (aux != WATER) {
            char[] auxFleet;
            if (aux == SHOT) {
                auxFleet = copyFleet(reference_fleet);
                boat_pipe = -1 * (deleteAndCountRight(position, reference_fleet) + deleteAndCountLeft(position, reference_fleet) + deleteAndCountMiddle(position, reference_fleet));
                this.reference_fleet = auxFleet;
            } else {
                boat_pipe = deleteAndCountRight(position, fleet) + deleteAndCountLeft(position, fleet) + deleteAndCountMiddle(position, fleet);
            }
        }
        return boat_pipe;
    }

    /**
     * Copies and saves the reference fleet to be used when needed.
     *
     * @param reference_fleet - the fleet that is going to be saved.
     * @return a new array that has the values of the reference fleet.
     */
    private char[] copyFleet(char[] reference_fleet) {
        char[] auxArray = new char[reference_fleet.length];

        for (int i = 0; i < reference_fleet.length; i++) {
            auxArray[i] = reference_fleet[i];
        }
        return auxArray;
    }

    /**
     * Deletes the current index.
     *
     * @param index - the index that is going to be updated.
     * @param fleet - the fleet that is going to be used.
     * @return the given index, to add to boat size number.
     */
    private int deleteAndCountMiddle(int index, char[] fleet) {
        int boatPipeCounter = 0;

        if ((fleet[index] != WATER) && (fleet[index] != SHOT)) {
            fleet[index] = SHOT;
            boatPipeCounter++;
        }

        return boatPipeCounter;
    }

    /**
     * Deletes to the left of the given index.
     *
     * @param index - the initial index.
     * @param fleet - the fleet that is going to be used.
     * @return the number of times it went to the left, to add to the boat size number.
     */
    private int deleteAndCountLeft(int index, char[] fleet) {
        int i = index - 1;
        int boatPipeCounter = 0;

        while ((i >= 0) && (fleet[index] == fleet[i])) {
            fleet[i] = SHOT;
            boatPipeCounter++;
            i--;
        }
        return boatPipeCounter;
    }

    /**
     * Deletes to the right of the given index.
     *
     * @param index - the initial index.
     * @param fleet - the fleet that is going to be used.
     * @return the number of times it went to the right, to add to the boat size number.
     */
    private int deleteAndCountRight(int index, char[] fleet) {
        int i = index + 1;
        int boatPipeCounter = 0;

        while ((i < fleet.length) && (fleet[index] == fleet[i])) {
            fleet[i] = SHOT;
            boatPipeCounter++;
            i++;
        }
        return boatPipeCounter;
    }

    /**
     * Returns <code>true</code> if a fleet has sunk, or <code>false</code> otherwise.
     *
     * @return if the fleet sank.
     */
    public boolean hasFleetSank() {
        for (int i = 0; i < fleet.length; i++) {
            if (fleet[i] != WATER && fleet[i] != SHOT) {
                return false;
            }
        }
        return true;
    }

}
