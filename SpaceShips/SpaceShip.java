import java.awt.Image;
import oop.ex2.*;

/**
 *
 * this class presents a SpaceShip in the SpaceWars game. it has a physics object, a number presents
 * seconds passed after a ship committed fire, max energy level, current energy level and
 * current health level which are integers, and a boolean data member which presents the ship's shield
 * off status, true if its off and false if its on.
 *  
 * @author Omer Salman
 */
public class SpaceShip{

    /** the physics object of this spaceship. */
    private SpaceShipPhysics ShipPhysics = new SpaceShipPhysics();

    /** the ship's max energy level. */
    private int MaxEnergyLevel = 210;

    /** the ship's current energy level. */
    private int CurrentEnergyLevel = 190;

    /** the ship's current health level */
    private int CurrentHealthLevel = 22;

    /** the ship's shield off status, true if its off and false if its on. */
    private Boolean ShieldOff = true;

    /** rounds required for the ship's cannons to cool after fire, meaning rounds cooldown
     *  between each shot */
    private int RoundsAfterFire = 8;

    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        this.regenerateEnergy(1);
    }

    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip(){ ;
        if (this.ShieldOff){
            this.CurrentHealthLevel --;
            this.MaxEnergyLevel -= 10;
            if (this.CurrentEnergyLevel > this.MaxEnergyLevel)
                this.CurrentEnergyLevel = this.MaxEnergyLevel;
        }
        else {
            this.MaxEnergyLevel += 18;
            this.CurrentEnergyLevel += 18;
        }
    }

    /** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
        this.ShipPhysics = new SpaceShipPhysics();
        this.MaxEnergyLevel = 210;
        this.CurrentEnergyLevel = 190;
        this.CurrentHealthLevel = 22;
        this.ShieldOff = true;
        this.RoundsAfterFire = 8;

    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        if (this.CurrentHealthLevel <= 0)
            return true;
        return false;
    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return this.ShipPhysics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (this.ShieldOff)
            this.CurrentHealthLevel--;
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage() {
        if (this.ShieldOff)
            return GameGUI.ENEMY_SPACESHIP_IMAGE;
        else
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
    }

    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        this.RoundsAfterFire++;
        if (this.RoundsAfterFire > 7) {
            if (this.CurrentEnergyLevel >= 19){
                game.addShot(this.ShipPhysics);
                this.RoundsAfterFire = 0;
                this.CurrentEnergyLevel -= 19;
                if (this.CurrentEnergyLevel < 0)
                    this.CurrentEnergyLevel = 0;
            }
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (this.CurrentEnergyLevel >= 3) {
            this.ShieldOff = false;
            this.CurrentEnergyLevel -= 3;
        }
        else
            this.ShieldOff = true;
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (this.CurrentEnergyLevel >= 140){
            this.ShipPhysics = new SpaceShipPhysics();
            this.CurrentEnergyLevel -= 140;
            if (this.CurrentEnergyLevel < 0)
                this.CurrentEnergyLevel = 0;
        }
    }

    /**regenerate energy according to a given integer
     *
     * @param energyAmount integer presents energy amount to raise to the ship's current energy amount.
     */
    protected void regenerateEnergy(int energyAmount) {
        if (this.CurrentEnergyLevel < this.MaxEnergyLevel) {
            this.CurrentEnergyLevel += energyAmount;
            if (this.CurrentEnergyLevel > this.MaxEnergyLevel)
                this.CurrentEnergyLevel = energyAmount;
        }
    }

    /**
     * checks if the ship's shield is off
     * @return true if the ship's shield is off, false if it isn't.
     */
    protected boolean isShieldOff(){
        return this.ShieldOff;
    }

    /**
     * turn off the ship's shield.
     */
    protected void setShieldOff() {
        this.ShieldOff = true;
    }
}
