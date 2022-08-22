public class SafeHouse extends NormalLoc {

    public SafeHouse(Player player) {
        super(player, "Güvenli Ev");
    }

    @Override
    boolean onLocation() {
        System.out.println("Güvenli Evdesin!");
        System.out.println("Canınız yenilendi!");
        this.getPlayer().setHealth(this.getPlayer().getDefHealth());
        return true;
    }
}
