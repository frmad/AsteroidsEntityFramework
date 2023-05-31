package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IEntityProcessingService {

    /**
     * Pre-condition: The Game is running and all entities in the game have been initialized and placed in the world object
     * Post-condition: All the entities have been processed/updated.
     * @param gameData contains metadata about the game state, such as time, game keys and the width and height of the screen
     * @param world contains all the entities and data to update the game state
     */
    void process(GameData gameData, World world);
}
