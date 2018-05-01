package g10;

import battleship.interfaces.BattleshipsPlayer;
import tournament.player.PlayerFactory;

public class G10 implements PlayerFactory<BattleshipsPlayer> {

    @Override
    public BattleshipsPlayer getNewInstance() {
        return new Player();
    }

    @Override
    public String getID() {
        return "G10";
    }

    @Override
    public String getName() {
        return "tuff-gai"; //insert cool name
    }

    @Override
    public String[] getAuthors() {
        String[] authors = {"Jonatan", "Mads"};
        return authors;
    }

}
