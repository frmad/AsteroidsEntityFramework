import dk.sdu.mmmi.cbse.asteroid2.Asteroid2Plugin;
import dk.sdu.mmmi.cbse.asteroid2.Asteroid2Processor;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroid2 {
    requires Common;
    requires CommonAsteroid;
    provides IGamePluginService with dk.sdu.mmmi.cbse.asteroid2.Asteroid2Plugin;
    provides IEntityProcessingService with dk.sdu.mmmi.cbse.asteroid2.Asteroid2Processor;
}