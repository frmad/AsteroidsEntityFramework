package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;

public class EnemyPlugin implements IGamePluginService {
  
    private Entity enemy;

    public EnemyPlugin() {
    }
    
    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemyShip(GameData gameData) {
        float deacceleration = 10;
        float acceleration = 150;
        float maxSpeed = 200;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth()/2+20;
        float y = gameData.getDisplayHeight()/2+20;
        float radians = 3.1415f / 2;

        Entity enemyShip = new Enemy();
        enemyShip.setRadius(5);
        enemyShip.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        enemyShip.add(new PositionPart(x, y, radians));
        enemyShip.add(new LifePart(1));

        return enemyShip;
    }

}
