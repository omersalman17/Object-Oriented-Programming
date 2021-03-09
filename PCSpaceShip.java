import oop.ex2.*;
import java.lang.Math;

/**
 * this class presents a SpaceShip controlled by the computer, it extends SpaceShip class, e.g. it inherit's
 * all the data members and methods of the SpaceShip Class.
 */
public class PCSpaceShip extends SpaceShip {

    /**
     * the movement of the ship - the ship's turn angle and acceleration will determine it.
     * @param closestShip SpaceShip object presents the closest ship to this ship.
     * @param direction1 an integer presents first direction for the ship to turn.
     * @param direction2 an integer presents second direction for the ship to turn.
     * @param accelerate a boolean presents the acceleration of the ship, if its true the ship
     *                  will accelerate, if its false the ship won't accelerate.
     */
    protected void turnAndAccelerate(SpaceShip closestShip, int direction1, int direction2,
                                     boolean accelerate) {
        double turnAngle = this.getPhysics().angleTo(closestShip.getPhysics());
        int turn = 0;
        if (turnAngle < 0 && turnAngle > -Math.PI)
            turn = direction1;
        else if (turnAngle > 0 && turnAngle < Math.PI)
            turn = direction2;
        this.getPhysics().move(accelerate, turn);
    }

    /**
     * attempt fire at the closest ship to this ship.
     * @param game the SpaceWars game object.
     * @param closestShipPhysics the physics object of the closest ship to this ship.
     * @param thiShipPhysics this ship physics object.
     * @param radiansAngle the angle in radians so that every lower angle in radians between this ship
     *                    and the closest one to it would allow the ship to commit fire at the closest
     *                    ship to it.
     */
    protected void attemptFire(SpaceWars game, SpaceShipPhysics closestShipPhysics,
                             SpaceShipPhysics thiShipPhysics, double radiansAngle){
        if (Math.abs(thiShipPhysics.angleTo(closestShipPhysics)) < radiansAngle)
            this.fire(game);
    }

    /**
     * attempt turn this ship shield on.
     * @param closestShipPhysics the physics object of the closest ship to this ship.
     * @param thiShipPhysics this ship physics object.
     * @param distance a double presents the distance between this ship and the closest one to it.
     */
    protected void attemptShieldOn(SpaceShipPhysics closestShipPhysics,
                                 SpaceShipPhysics thiShipPhysics, double distance) {
        if (closestShipPhysics.distanceFrom(thiShipPhysics) <= distance)
            this.shieldOn();
        else
            this.setShieldOff();
    }

    /**
     * this ship attempts to commit teleport.
     * @param closestShipPhysics the physics object of the closest ship to this ship.
     * @param thiShipPhysics this ship physics object.
     * @param distance a double presents the distance between this ship and the closest one to it.
     * @param radiansAngle the angle in radians between this ship and the closest one to it.
     */
    protected void attempTeleport(SpaceShipPhysics closestShipPhysics,
                                SpaceShipPhysics thiShipPhysics, double distance, double radiansAngle){
        if (closestShipPhysics.distanceFrom(thiShipPhysics) < distance)
            if (Math.abs(closestShipPhysics.angleTo(thiShipPhysics)) < radiansAngle)
                this.teleport();
    }
}
