/**
 * this class presents a runner space ship in the SpaceWars game, it extends the PCSpaceShip class.
 */
public class RunnerSpaceShip extends PCSpaceShip {

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * overrides the SpaceWars doAction method due to this ship's specific behavior according to the
     * exercise description.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        this.attempTeleport(closestShip.getPhysics(), this.getPhysics(), 0.25, 0.23);
        this.turnAndAccelerate(closestShip, 1, -1, true);
        super.doAction(game);
    }
}
