import java.util.Random;

public class Snake extends Obstacle {
    Random random = new Random();

    public Snake() {
        super(1,"Yılan", 4,12,0);
        int randomDamage = random.nextInt(4) + 3;
        this.setDamage(randomDamage);
    }
}