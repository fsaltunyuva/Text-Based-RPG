import java.util.Random;

public class Snake extends Obstacle {
    private static Random random = new Random();
    private static int randomDamage = random.nextInt(3) + 3;

    public Snake() {
        super(4, "Yılan", randomDamage, 12, 0);
    }

}
