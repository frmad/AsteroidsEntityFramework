package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class LifePart implements EntityPart {

    private boolean dead = false;
    private int life;
    private boolean isHit = false;
    private boolean isDead = false;

    public LifePart(int life) {
        this.life = life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public boolean isDead() {
        return dead;
    }

    @Override
    public void process(GameData data, Entity entity) {
        if (isHit()) {
            setLife(getLife() - 1);
            setIsHit(false);
        }
        if (getLife() <= 0) {
            isDead = true;
        }
    }


}
