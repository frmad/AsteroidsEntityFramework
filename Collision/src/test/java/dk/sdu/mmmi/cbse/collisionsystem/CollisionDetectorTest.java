package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CollisionDetectorTest {
    private CollisionDetector collisionDetector;

    @BeforeEach
    void setUp() {
        collisionDetector = new CollisionDetector();
    }

    @AfterEach
    void remove(){

    }

    @Test
    void processDifPos(){
        GameData gameData = mock(GameData.class);
        Entity entity1 = createEntity1("1", 1, 1,1, 2);
        Entity entity2 = createEntity2("2",1, 30,30, 2);
        World world = createMockWorld(entity1, entity2);
        collisionDetector.process(gameData, world);
        LifePart entity1LifePart = entity1.getPart(LifePart.class);
        LifePart entity2LifePart = entity2.getPart(LifePart.class);
        verify(entity1LifePart, never()).setIsHit(anyBoolean());
        verify(entity2LifePart, never()).setIsHit(anyBoolean());
        verify(world, never()).removeEntity(entity1);
        verify(world, never()).removeEntity(entity2);
    }

    @Test
    void processSamePos(){
        GameData gameData = mock(GameData.class);
        Entity entity1 = createEntity1("1", 1, 1,1, 2);
        Entity entity2 = createEntity2("2",1, 1,1, 2);
        World world = createMockWorld(entity1, entity2);
        collisionDetector.process(gameData, world);
        //LifePart entity1LifePart = entity1.getPart(LifePart.class);
        //LifePart entity2LifePart = entity2.getPart(LifePart.class);
        assertTrue(this.collisionDetector.collides(entity1, entity2));
        verify(world, atLeastOnce()).removeEntity(entity1);




        //verify(entity1LifePart, atLeastOnce()).setIsHit(true);
        //verify(entity2LifePart, atLeastOnce()).setIsHit(true);
    }

    @Test
    void collidesDifPos() {
        Entity entity1 = createEntity1("1", 1, 1,1, 2);
        Entity entity2 = createEntity2("2",1, 30,30, 2);
        assertFalse(collisionDetector.collides(entity1, entity2));
    }
    @Test
    void collidesSamePos() {
        Entity entity1 = createEntity1("1", 1, 1,1, 2);
        Entity entity2 = createEntity2("2",1, 1,1, 2);
        assertTrue(collisionDetector.collides(entity1, entity2));
    }

    private World createMockWorld(Entity entity1, Entity entity2) {
        World mockedWorld = mock(World.class);
        List<Entity> entityList = new LinkedList<>();
        entityList.add(entity1);
        entityList.add(entity2);
        when(mockedWorld.getEntities()).thenReturn(entityList);

        when(mockedWorld.getEntity(entity2.getID())).thenReturn(entity2);
        return mockedWorld;
    }
    private Entity createEntity1(
            String entity1ID, int entity1Life, float entity1X, float entity1Y, float entity1Radius
    ) {
        // Entity 1
        Entity entity1 = mock(Entity.class);
        when(entity1.getID()).thenReturn(entity1ID);

        LifePart lifePart1 = mock(LifePart.class);
        when(entity1.getPart(LifePart.class)).thenReturn(lifePart1);

        PositionPart positionPart1 = mock(PositionPart.class);
        when(entity1.getPart(PositionPart.class)).thenReturn(positionPart1);

        when(lifePart1.getLife()).thenReturn(entity1Life);
        when(positionPart1.getX()).thenReturn(entity1X);
        when(positionPart1.getY()).thenReturn(entity1Y);
        when(entity1.getRadius()).thenReturn(entity1Radius);

        return entity1;
    }

    private Entity createEntity2(
            String entity2ID, int entity2Life, float entity2X, float entity2Y, float entity2Radius
    ) {
        // Entity 2
        Entity entity2 = mock(Entity.class);
        when(entity2.getID()).thenReturn(entity2ID);

        LifePart lifePart2 = mock(LifePart.class);
        when(entity2.getPart(LifePart.class)).thenReturn(lifePart2);

        PositionPart positionPart2 = mock(PositionPart.class);
        when(entity2.getPart(PositionPart.class)).thenReturn(positionPart2);

        when(lifePart2.getLife()).thenReturn(entity2Life);
        when(positionPart2.getX()).thenReturn(entity2X);
        when(positionPart2.getY()).thenReturn(entity2Y);
        when(entity2.getRadius()).thenReturn(entity2Radius);

        return entity2;
    }

}

