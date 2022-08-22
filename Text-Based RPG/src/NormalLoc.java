public class NormalLoc extends Location {

    public NormalLoc(Player player, String name) {
        super(player);
        this.name = name;
    }

    @Override
    boolean onLocation() {
        return true;
    }
}
