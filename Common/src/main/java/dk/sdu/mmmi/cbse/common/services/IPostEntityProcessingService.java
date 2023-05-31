package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IPostEntityProcessingService {

    /**
     * Pre-condition: The IEntityProcessingService should already have been used
     * Post-condition: Any necessary collision checks or other post-entity processing has been performed
     * @param gameData contains metadata about the game state, such as time, game keys and the width and height of the screen
     * @param world contains all the entities and data to update the game state
     */
    void process(GameData gameData, World world);
}
