package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {
    /**
     * Pre-condition: The Game has been started and plugins have not been loaded
     * Post-condition: All entities have been created added to the world
     * @param gameData contains metadata about the game state, such as time, game keys and the width and height of the screen
     * @param world contains all the entities and data to update the game state
     */
    void start(GameData gameData, World world);

}
