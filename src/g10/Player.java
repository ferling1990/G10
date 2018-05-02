package g10;

import battleship.interfaces.BattleshipsPlayer;
import battleship.interfaces.Fleet;
import battleship.interfaces.Position;
import battleship.interfaces.Board;
import battleship.interfaces.Ship;
import java.util.ArrayList;
import java.util.Random;

public class Player implements BattleshipsPlayer {

    private final static Random rnd = new Random();
    private int sizeX, sizeY;
    private int shipsBeforeShot, shipsAfterShot, duplication;
    private Board myBoard;
    private boolean seeking, shipDead, vertical;
    private ArrayList<Position> possibleShots;
    private Position firstHit, lastHit, lastShot;
    private shotDirectionEnum directionEnum;

    public Player() {
        seeking = true;
        possibleShots = new ArrayList();
        lastShot = null;
        lastHit = null;
        directionEnum = shotDirectionEnum.unknown;
    }

    /**
     * The method called when its time for the AI to place ships on the board
     * (at the beginning of each round).
     *
     * The Ship object to be placed MUST be taken from the Fleet given (do not
     * create your own Ship objects!).
     *
     * A ship is placed by calling the board.placeShip(..., Ship ship, ...) for
     * each ship in the fleet (see board interface for details on placeShip()).
     *
     * A player is not required to place all the ships. Ships placed outside the
     * board or on top of each other are wrecked.
     *
     * @param fleet Fleet all the ships that a player should place.
     * @param board Board the board were the ships must be placed.
     */
//    @Override
//    public void placeShips(Fleet fleet, Board board) {
//        myBoard = board;
//        sizeX = board.sizeX();
//        sizeY = board.sizeY();
//        boolean[][] myShips = new boolean[sizeX][sizeY];
//        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {
//            Ship s = fleet.getShip(i);
//            Position pos;
//            boolean vertical;
//            do {
//                vertical = rnd.nextBoolean();
//                if (vertical) {
//                    int x = rnd.nextInt(sizeX);
//                    int y = rnd.nextInt(sizeY - (s.size() - 1));
//                    pos = new Position(x, y);
//                } else {
//                    int x = rnd.nextInt(sizeX - (s.size() - 1));
//                    int y = rnd.nextInt(sizeY);
//                    pos = new Position(x, y);
//                }
//            } while (collision(pos, s, vertical, myShips));
//            for (int j = 0; j < s.size(); j++) {
//                if (vertical) {
//                    myShips[pos.x][pos.y + j] = true;
//                } else {
//                    myShips[pos.x + j][pos.y] = true;
//                }
//            }
//            board.placeShip(pos, s, vertical);
//        }
//    }
    @Override
    public void placeShips(Fleet fleet, Board board) {
        myBoard = board;
        sizeX = board.sizeX();
        sizeY = board.sizeY();
        int x = 0;
        int y = 0;
        Random r = new Random();
        int n = r.nextInt(3);
        boolean[][] myShips = new boolean[sizeX][sizeY];
        for (int i = 0; i < fleet.getNumberOfShips(); ++i) {
            Ship s = fleet.getShip(i);

            if (n == 0) {
                Position pos = formation0(s);
                board.placeShip(pos, s, vertical);
            }
            if (n == 1) {
                Position pos = formation90(s);
                board.placeShip(pos, s, vertical);
            }
            if (n == 2) {
                Position pos = formation180(s);
                board.placeShip(pos, s, vertical);
            }
            if (n == 3) {
                Position pos = formation270(s);
                board.placeShip(pos, s, vertical);
            }

        }
    }

    private Position formation0(Ship s) {

        Position pos;
        int x;
        int y;
        if (s.size() == 2) {                         //size 2 ship
            x = 0;
            y = 7;
            vertical = true;
            pos = new Position(x, y);
            return pos;

        }
        if (s.size() == 3 && duplication == 0) { //1st size 3 ship
            x = 1;
            y = 4;
            vertical = true;
            duplication++;
            pos = new Position(x, y);
            return pos;
        } else if (s.size() == 3 && duplication == 1) { //2nd size 3 ship
            x = 2;
            y = 2;
            vertical = true;
            duplication--;
            pos = new Position(x, y);
            return pos;
        }

        if (s.size() == 4) {                        //size 4 ship
            x = 5;
            y = 2;
            vertical = false;
            pos = new Position(x, y);
            return pos;
        }
        if (s.size() == 5) {                        //size 5 ship
            x = 9;
            y = 3;
            vertical = true;
            pos = new Position(x, y);
            return pos;
        }

        return null;
    }

    private Position formation90(Ship s) {

        Position pos;
        int x;
        int y;

        if (s.size() == 2) {                         //size 2 ship
            x = 7;
            y = 9;
            vertical = false;
            pos = new Position(x, y);
            return pos;

        }
        if (s.size() == 3 && duplication == 0) { //1st size 3 ship
            x = 4;
            y = 8;
            vertical = false;
            duplication++;
            pos = new Position(x, y);
            return pos;
        } else if (s.size() == 3 && duplication == 1) { //2nd size 3 ship
            x = 2;
            y = 7;
            vertical = false;
            duplication--;
            pos = new Position(x, y);
            return pos;
        }

        if (s.size() == 4) {                        //size 4 ship
            x = 2;
            y = 1;
            vertical = true;
            pos = new Position(x, y);
            return pos;
        }
        if (s.size() == 5) {                        //size 5 ship
            x = 3;
            y = 0;
            vertical = false;
            pos = new Position(x, y);
            return pos;
        }
        return null;
    }

    private Position formation180(Ship s) {

        Position pos;
        int x;
        int y;

        if (s.size() == 2) {                         //size 2 ship
            x = 9;
            y = 1;
            vertical = true;
            pos = new Position(x, y);
            return pos;

        }
        if (s.size() == 3 && duplication == 0) { //1st size 3 ship
            x = 8;
            y = 3;
            vertical = true;
            duplication++;
            pos = new Position(x, y);
            return pos;
        } else if (s.size() == 3 && duplication == 1) { //2nd size 3 ship
            x = 7;
            y = 5;
            vertical = true;
            duplication--;
            pos = new Position(x, y);
            return pos;
        }

        if (s.size() == 4) {                        //size 4 ship
            x = 1;
            y = 7;
            vertical = false;
            pos = new Position(x, y);
            return pos;
        }
        if (s.size() == 5) {                        //size 5 ship
            x = 0;
            y = 2;
            vertical = true;
            pos = new Position(x, y);
            return pos;
        }
        return null;
    }

    private Position formation270(Ship s) {

        Position pos;
        int x;
        int y;

        if (s.size() == 2) {                         //size 2 ship
            x = 1;
            y = 0;
            vertical = false;
            pos = new Position(x, y);
            return pos;

        }
        if (s.size() == 3 && duplication == 0) { //1st size 3 ship
            x = 3;
            y = 1;
            vertical = false;
            duplication++;
            pos = new Position(x, y);
            return pos;
        } else if (s.size() == 3 && duplication == 1) { //2nd size 3 ship
            x = 5;
            y = 2;
            vertical = false;
            duplication--;
            pos = new Position(x, y);
            return pos;
        }

        if (s.size() == 4) {                        //size 4 ship
            x = 7;
            y = 5;
            vertical = true;
            pos = new Position(x, y);
            return pos;
        }
        if (s.size() == 5) {                        //size 5 ship
            x = 3;
            y = 9;
            vertical = false;
            pos = new Position(x, y);
            return pos;
        }
        return null;
    }

//    private boolean collision(Position pos, Ship s, boolean vertical, boolean[][] myShips) {
//        for (int i = 0; i < s.size(); i++) {
//            if (vertical) {
//                if (myShips[pos.x][pos.y + i]) {
//                    return true;
//                }
//            } else {
//                if (myShips[pos.x + i][pos.y]) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
    private void createPossibleShotList() {
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                possibleShots.add(new Position(x, y));
            }
        }
    }

    /**
     * Called every time the enemy has fired a shot.
     *
     * The purpose of this method is to allow the AI to react to the enemy's
     * incoming fire and place his/her ships differently next round.
     *
     * @param pos Position of the enemy's shot
     */
    @Override
    public void incoming(Position pos) {
        //Do nothing
    }

    /**
     * Called by the Game application to get the Position of your shot.
     * hitFeedBack(...) is called right after this method.
     *
     * @param enemyShips Fleet the enemy's ships. Compare this to the Fleet
     * supplied in the hitFeedBack(...) method to see if you have sunk any
     * ships.
     *
     * @return Position of you next shot.
     */
    @Override
    public Position getFireCoordinates(Fleet enemyShips) {
        shipsBeforeShot = enemyShips.getNumberOfShips();
//        if (possibleShots.isEmpty()) {
//            createPossibleShotList();
//        }
        if (seeking) {
            seekShot();
        } else {
            killShot();
        }
        return lastShot;
    }

    private void seekShot() {
        for (int i = 0; i < 100; i++) {
            int shot = rnd.nextInt(possibleShots.size());
            if (hasNoNeighboors(possibleShots.get(shot))) {
                lastShot = possibleShots.get(shot);
                possibleShots.remove(shot);
                return;
            }
        }
        randomShot();
    }

    private void randomShot() {
        int shot = rnd.nextInt(possibleShots.size());
        lastShot = possibleShots.get(shot);
        possibleShots.remove(shot);
    }

    private boolean hasNoNeighboors(Position pos) {
        if (possibleShots.contains(pos) && possibleShots.contains(new Position(pos.x + 1, pos.y)) && possibleShots.contains(new Position(pos.x - 1, pos.y))
                && possibleShots.contains(new Position(pos.x, pos.y + 1)) && possibleShots.contains(new Position(pos.x, pos.y - 1))) {
            return true;
        }
        return false;
    }

    private void killShot() {
        if (firstHit == null) {
            firstHit = lastHit;
        }
        Position guess = new Position(lastHit.x + 1, lastHit.y);
        Position guess2 = new Position(lastHit.x - 1, lastHit.y);
        Position guess3 = new Position(lastHit.x, lastHit.y + 1);
        Position guess4 = new Position(lastHit.x, lastHit.y - 1);
        Position guess5 = new Position(firstHit.x - 1, firstHit.y);
        Position guess6 = new Position(firstHit.x, firstHit.y - 1);
        if (possibleShots.contains(guess) && directionEnum == shotDirectionEnum.right) {
            lastShot = guess;
            possibleShots.remove(guess);
        } else if (possibleShots.contains(guess2) && directionEnum == shotDirectionEnum.left) {
            lastShot = guess2;
            possibleShots.remove(guess2);
        } else if (possibleShots.contains(guess3) && directionEnum == shotDirectionEnum.up) {
            lastShot = guess3;
            possibleShots.remove(guess3);
        } else if (possibleShots.contains(guess4) && directionEnum == shotDirectionEnum.down) {
            lastShot = guess4;
            possibleShots.remove(guess4);
        } else if (possibleShots.contains(guess)) {
            lastShot = guess;
            possibleShots.remove(guess);
            directionEnum = shotDirectionEnum.right;
        } else if (possibleShots.contains(guess2)) {
            lastShot = guess2;
            possibleShots.remove(guess2);
            directionEnum = shotDirectionEnum.left;
        } else if (possibleShots.contains(guess5)) {
            lastShot = guess5;
            possibleShots.remove(guess5);
            directionEnum = shotDirectionEnum.left;
            //firstHit = null;
        } else if (possibleShots.contains(guess3)) {
            lastShot = guess3;
            possibleShots.remove(guess3);
            directionEnum = shotDirectionEnum.up;
        } else if (possibleShots.contains(guess4)) {
            lastShot = guess4;
            possibleShots.remove(guess4);
            directionEnum = shotDirectionEnum.down;
        } else if (possibleShots.contains(guess6)) {
            lastShot = guess6;
            possibleShots.remove(guess6);
            directionEnum = shotDirectionEnum.down;
            //firstHit = null;
        } else {
            //seeking = true;
            randomShot();
            directionEnum = shotDirectionEnum.unknown;
        }
    }

    /**
     * Called right after getFireCoordinates(...) to let your AI know if you hit
     * something or not.
     *
     * Compare the number of ships in the enemyShips with that given in
     * getFireCoordinates in order to see if you sunk a ship.
     *
     * @param hit boolean is true if your last shot hit a ship. False otherwise.
     * @param enemyShips Fleet the enemy's ships.
     */
    @Override
    public void hitFeedBack(boolean hit, Fleet enemyShips) {
        shipsAfterShot = enemyShips.getNumberOfShips();
        if (shipsAfterShot < shipsBeforeShot) {
            seeking = true;
            firstHit = null;
            directionEnum = shotDirectionEnum.unknown;
        } else if (hit) {
            seeking = false;
            lastHit = lastShot;
        } else {
            directionEnum = shotDirectionEnum.unknown;
        }

    }

    private int[] checkEnemyFleet(Fleet enemyShips) {
        int[] shipSizes = {0, 0, 0, 0};
        for (int i = 0; i < enemyShips.getNumberOfShips(); i++) {
            if (enemyShips.getShip(i).size() == 2) {
                shipSizes[0]++;
            }
            if (enemyShips.getShip(i).size() == 3) {
                shipSizes[1]++;
            }
            if (enemyShips.getShip(i).size() == 4) {
                shipSizes[2]++;
            }
            if (enemyShips.getShip(i).size() == 5) {
                shipSizes[3]++;
            }
        }
        return shipSizes;
    }

    /**
     * Called in the beginning of each match to inform about the number of
     * rounds being played.
     *
     * @param rounds int the number of rounds i a match
     */
    @Override
    public void startMatch(int rounds, Fleet ships, int sizeX, int sizeY) {

    }

    /**
     * Called at the beginning of each round.
     *
     * @param round int the current round number.
     */
    @Override
    public void startRound(int round) {
        possibleShots.clear();
        createPossibleShotList();
    }

    /**
     * Called at the end of each round to let you know if you won or lost.
     * Compare your points with the enemy's to see who won.
     *
     * @param round int current round number.
     * @param points your points this round: 100 - number of shot used to sink
     * all of the enemy's ships.
     *
     * @param enemyPoints int enemy's points this round.
     */
    @Override
    public void endRound(int round, int points, int enemyPoints) {
        //Do nothing
    }

    /**
     * Called at the end of a match (that usually last 1000 rounds) to let you
     * know how many losses, victories and draws you scored.
     *
     * @param won int the number of victories in this match.
     * @param lost int the number of losses in this match.
     * @param draw int the number of draws in this match.
     */
    @Override
    public void endMatch(int won, int lost, int draw) {
        //Do nothing
    }

}
