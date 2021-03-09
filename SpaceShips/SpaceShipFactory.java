/**
 * this class presents a SpaceShipFactory, e.g. creates SpaceShip objects according to the specific kinds
 * given by the user through the cmd and puts them into an array of Spaceships objects.
 */
public class SpaceShipFactory {

    /**
     *
     * @param args an array of strings presenting the kinds of SpaceShip objects the user wishes to create
     *            for the SpaceWars game. ( a - aggressive spaceship, b - basher spaceship,
     *            r - runner spaceship, d - drunkard spaceship, h - human controlled spaceship,
     *            s - special spaceship).
     * @return an array of SpaceShips objects which contains the SpaceShip objects the user wished
     * to create for the game.
     */
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip [] SpaceShipArray = new SpaceShip[args.length];
        for ( int i = 0; i < args.length; i++){
            switch (args[i]) {
                case "h":
                    SpaceShip HShip = new HumanSpaceShip();
                    SpaceShipArray[i] = HShip;
                    break;
                case "a":
                    SpaceShip AShip = new AggressiveSpaceShip();
                    SpaceShipArray[i] = AShip;
                    break;
                case "r":
                    SpaceShip RShip = new RunnerSpaceShip();
                    SpaceShipArray[i] = RShip;
                    break;
                case "b":
                    SpaceShip BShip = new BasherSpaceShip();
                    SpaceShipArray[i] = BShip;
                    break;
                case "d":
                    SpaceShip DShip = new DrunkardSpaceShip();
                    SpaceShipArray[i] = DShip;
                    break;
                case "s":
                    SpaceShip SShip = new SpecialSpaceShip();
                    SpaceShipArray[i] = SShip;
                    break;
            }
        }
        return SpaceShipArray;
    }
}
