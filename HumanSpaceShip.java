import oop.ex2.GameGUI;
import java.awt.*;

/**
 * this class presents a human controlled space ship in the SpaceWars game, it extends the SpaceShip class.
 */
public class HumanSpaceShip extends SpaceShip{

    /**
     * Does the actions of this ship for this round.
     * This is called once per round by the SpaceWars game driver.
     * overrides the SpaceWars doAction method due to this ship's specific behavior according to the
     * exercise description..
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        GameGUI gGui = game.getGUI();
        this.checkTeleport(gGui);
        this.checkTurnAndAcc(gGui);
        this.checkShield(gGui);
        this.checkShot(gGui, game);
        super.doAction(game);
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     *
     * @return the image of this ship.
     */
    public Image getImage() {
        if (this.isShieldOff())
            return GameGUI.SPACESHIP_IMAGE;
        else
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
    }

    /**
     *  check if the player pressed one of the buttons to turn the ship, is he pressed on the right
     *  direction button the method will return -1, if he pressed on the left direction button the
     *  method will return 1, if he pressed both or didn't press at all, the method will return 0.
     * @param gGui the GameGui object of SpaceWars game.
     * @return an integer presents the ship's turn side: 1 for the ship to turn left and -1 for the ship
     * to turn right.
     */
    private int turnPressed(GameGUI gGui){
        int turn = 0;
        if (gGui.isLeftPressed()){
            if (gGui.isRightPressed())
                turn = 0;
            turn = 1;
        }
        else if (gGui.isRightPressed()){
            if (gGui.isLeftPressed())
                turn = 0;
            turn = - 1;
        }
        return turn;
    }

    /**
     * check if the player pressed on the teleport button, if he does, trying to commit teleport.
     * @param gGui = the GameGui object of SpaceWars game.
     */
    private void checkTeleport(GameGUI gGui) {
        if (gGui.isTeleportPressed())
            this.teleport();
    }

    /**
     * checks if the player pressed on the turn sides buttons or the acceleration button, if he does,
     * attempts to turn or accelerate.
     * @param gGui the GameGui object of SpaceWars game.
     */
    private void checkTurnAndAcc(GameGUI gGui){
        int turn = this.turnPressed(gGui);
        boolean accelerate = gGui.isUpPressed();
        this.getPhysics().move(accelerate, turn);
    }

    /**
     * checks if the player pressed on the shield button in order to turn it on, if he does,
     * attempts to turn on shield.
     * @param gGui the GameGui object of SpaceWars game.
     */
    private void checkShield(GameGUI gGui){
        if (gGui.isShieldsPressed())
            this.shieldOn();
        else
            this.setShieldOff();
    }

    /**
     * checks if the player pressed on the shooting button in order to fire at other spaceships, if he does,
     * attempts to fire.
     * @param gGui the GameGui object of SpaceWars game.
     * @param game the SpaceWars game object.
     */
    private void checkShot(GameGUI gGui, SpaceWars game){
        if (gGui.isShotPressed())
            this.fire(game);
    }
}
