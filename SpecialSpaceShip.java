/**
 * this class presents a special space ship in the SpaceWars game, it extends the PCSpaceShip class.
 */
public class SpecialSpaceShip extends PCSpaceShip {

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * overrides the SpaceWars doAction method due to this ship's specific behavior according to the
     * exercise description.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        this.turnAndAccelerate(closestShip, -1, 1, true);
        this.attemptShieldOn(closestShip.getPhysics(), this.getPhysics(), 0.10);
        for (int i = 0; i < 3; i++){
            this.attemptFire(game, closestShip.getPhysics(), this.getPhysics(), 0.15);
        }
        this.regenerateEnergy(3);
    }
}
