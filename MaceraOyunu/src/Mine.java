import java.util.Random;

public class Mine extends BattleLoc {
    public Mine(Player player) {
        super(player, "Maden", new Snake(), "ganimet", 5);
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();

        System.out.println("Şuan buradasınız : " + this.getName());
        System.out.println("Dikkatli ol! Burada " + obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor");

        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase = input.nextLine().toUpperCase();

        if (selectCase.equals("S") && combat(obsNumber)) {
            if (this.getPlayer().getHealth() <= 0) {
                System.out.println("Öldünüz! ");
                return false;
            }

            String[] possibleAwards = {"Para", "Silah", "Zırh"};
            String randomAward = possibleAwards[new Random().nextInt(possibleAwards.length)];

            System.out.println(this.getName() + " tüm düşmanları yendiniz!");
            System.out.println("Kazandığınız ödül: " + randomAward);


            addAward(randomAward);
        }
        return true;
    }

    private void addAward(String award) {
        switch (award) {
            case "Silah":
                System.out.println("Yeni silah kazandınız!");
                break;
            case "Zırh":
                System.out.println("Yeni zırh kazandınız!");
                break;
            default:
                System.out.println("Geçersiz ödül tipi!");
        }
    }
}

