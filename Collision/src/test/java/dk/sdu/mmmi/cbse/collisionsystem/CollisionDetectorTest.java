package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
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
        Entity entity1 = createMockEntity("1", 1, 1,1, 2);
        Entity entity2 = createMockEntity("2",1, 30,30, 2);
        World world = createMockWorld(entity1, entity2);
        collisionDetector.process(gameData, world);
        LifePart entity1LifePart = entity1.getPart(LifePart.class);
        LifePart entity2LifePart = entity2.getPart(LifePart.class);
        verify(entity1LifePart, never()).setIsHit(true);
        verify(entity2LifePart, never()).setIsHit(true);
        verify(world, never()).removeEntity(entity1);
        verify(world, never()).removeEntity(entity2);
    }

    @Test
    void processSamePos(){
        GameData gameData = mock(GameData.class);
        Entity entity1 = createMockEntity("1", 1, 1,1, 4);
        Entity entity2 = createMockEntity("2",1, 2,1, 4);
        World world = createMockWorld(entity1, entity2);
        collisionDetector.process(gameData, world);
        // Check that entity life is changed
        verify((LifePart)entity1.getPart(LifePart.class), atLeastOnce()).setIsHit(true);
        // Check that entity is removed after hit
        verify(world, atLeastOnce()).removeEntity(entity1);
    }

    @Test
    void collidesDifPos() {
        Entity entity1 = createMockEntity("1", 1, 1,1, 2);
        Entity entity2 = createMockEntity("2",1, 30,30, 2);
        assertFalse(collisionDetector.collides(entity1, entity2));
    }
    @Test
    void collidesSamePos() {
        Entity entity1 = createMockEntity("1", 1, 1,1, 2);
        Entity entity2 = createMockEntity("2",1, 1,1, 2);
        assertTrue(collisionDetector.collides(entity1, entity2));
    }

    private World createMockWorld(Entity entity1, Entity entity2) {
        World mockedWorld = mock(World.class);
        List<Entity> entityList = new LinkedList<>();
        entityList.add(entity1);
        entityList.add(entity2);
        when(mockedWorld.getEntities()).thenReturn(entityList);
        when(mockedWorld.getEntity(entity1.getID())).thenReturn(entity1);
        when(mockedWorld.getEntity(entity2.getID())).thenReturn(entity2);
        return mockedWorld;
    }
    private Entity createMockEntity(
            String entityID, int entityLife, float entityX, float entityY, float entityRadius
    ) {
        Entity entity = mock(Entity.class);
        when(entity.getID()).thenReturn(entityID);
        when(entity.getRadius()).thenReturn(entityRadius);

        LifePart lifePart = mock(LifePart.class);
        when(entity.getPart(LifePart.class)).thenReturn(lifePart);
        when(lifePart.getLife()).thenReturn(entityLife);

        PositionPart positionPart = mock(PositionPart.class);
        when(entity.getPart(PositionPart.class)).thenReturn(positionPart);
        when(positionPart.getX()).thenReturn(entityX);
        when(positionPart.getY()).thenReturn(entityY);

        doAnswer(newLife ->{
            when(lifePart.getLife()).thenReturn(newLife.getArgument(0));
            return null;
        }).when(lifePart).setLife(anyInt());

        return entity;
    }

}

