package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity hittingEntity : world.getEntities()) {
            for (Entity collisionEntity : world.getEntities()) {
                // if the two entities are identical, skip the iteration
                if (hittingEntity.getID().equals(collisionEntity.getID())) {
                    continue;
                }
                LifePart hittingEntityLifePart = hittingEntity.getPart(LifePart.class);

                // CollisionDetection
                if (this.collides(hittingEntity, collisionEntity)) {
                    // if entity has been hit, it must have its life reduced
                    if (hittingEntityLifePart.getLife() > 0) {
                        hittingEntityLifePart.setLife(hittingEntityLifePart.getLife() - 1);
                        hittingEntityLifePart.setIsHit(true);
                        // if entity is out of life remove it
                        if (hittingEntityLifePart.getLife() <= 0) {
                            world.removeEntity(hittingEntity);
                        }
                    }
                }
            }
        }
    }

    public Boolean collides(Entity entity, Entity entity2) {
        PositionPart entityPart = entity.getPart(PositionPart.class);
        PositionPart entity2Part = entity2.getPart(PositionPart.class);
        float dx = entityPart.getX() - entity2Part.getX();
        float dy = entityPart.getY() - entity2Part.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        if (distance < (entity.getRadius() + entity2.getRadius())) {
            return true;
        }
        return false;
    }

}
