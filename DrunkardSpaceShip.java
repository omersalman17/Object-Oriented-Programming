import java.util.Random;

/**
 * this class presents a drunkard space ship in the SpaceWars game, it extends the PCSpaceShip class.
 */
public class DrunkardSpaceShip extends PCSpaceShip {

    /** number of rounds to pass after the ship's turn side was decided, e.g. the ship will keep turning
     *  for this side for the next 500 rounds before maybe changing its turn side */
    private int RoundsAfterTurn = 500;

    /** the turn side for this ship presented by integer (0: won't turn, 1: turn left, -1: turn right). */
    private int TurnSide = 0;

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * overrides the SpaceWars doAction method due to this ship's specific behavior according to the exercise description.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        SpaceShip closestShip = game.getClosestShipTo(this);
        this.turnAndAccelerate();
        this.attemptShieldOn(closestShip.getPhysics(), this.getPhysics(), 0.30);
        this.attemptFire(game, closestShip.getPhysics(),this.getPhysics(), 0.28);
        super.doAction(game);
    }

    /**
     * this ship movement method. the acceleration will change randomly each round, the turn side will be
     * determined randomly and won't change for at least 500 rounds.
     */
    private void turnAndAccelerate() {
        this.RoundsAfterTurn++;
        Random randomBoolean = new Random();
        if (this.RoundsAfterTurn >= 500) {
            boolean turnBoolean = randomBoolean.nextBoolean();
            if (turnBoolean)
                this.TurnSide = 1;
            else
                this.TurnSide = -1;
            this.RoundsAfterTurn = 0;
        }
        boolean accelerate = randomBoolean.nextBoolean();
        this.getPhysics().move(accelerate, this.TurnSide);
    }
}
