import java.util.Random;
import java.security.spec.RSAOtherPrimeInfo;

public abstract class BattleLoc extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;


    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle){
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obstacleNumber = this.randomObstacleNumber();

        System.out.println("Suan buradasınızz : " + this.getName());
        System.out.println("Tehlikeli olabilir dikkatli olun! Burada " + obstacleNumber + " tane " +this.getObstacle().getName() + " yaşıyor");
        System.out.println("<S>avaş veya <K>aç ");
        String selectChoice = input.nextLine().toUpperCase();
        if (selectChoice.equals("S") && combat(obstacleNumber)){
            System.out.println(this.getName() + " konumundaki tum düşmanları yendiniz");
            if(this.award.equals("Water")){
                System.out.println("Su kazandınız");
                getPlayer().getInventory().setWater(true);
            }else if (this.award.equals("FireWood")){
                System.out.println("Odun kazandınız");
                getPlayer().getInventory().setFirewood(true);
            }else if (this.award.equals("food")) {
                System.out.println("Yemek kazandınız");
                getPlayer().getInventory().setFood(true);
            }
            return true;
        }
        if (this.getPlayer().getHealth() <= 0){
            System.out.println("Öldünüz!!");
            return false;
        }
        return true;
    }

    public boolean combat (int obsNumber){

        for(int i = 1; i<= obsNumber; i++){
            this.getObstacle().setHealth(this.getObstacle().getOrginalHealth());
            playerStates();
            obstacleStats(i);
            Random random = new Random();
            int randomChance = random.nextInt(2);
            if (randomChance == 0){
                System.out.println("Ilk siz baslayacaksınız");
            while(this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                    System.out.print("<V>ur veya <K>aç : ");
                    String selectCombat = input.nextLine().toUpperCase();

                if(selectCombat.equals("V")){
                    System.out.println("Siz vurdunuz! ");
                    this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();

                    if(this.getObstacle().getHealth() > 0){
                        System.out.println();
                        System.out.println(this.getObstacle().getName() + " size vurdu!");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if(obstacleDamage < 0){
                          obstacleDamage = 0;
                        }

                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                  }
              } else return false;

            }
            } else{
                System.out.println("İlk önce canavar başlayacak!");

                while(this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0){
                    System.out.print("<V>ur veya <K>aç : ");
                    String selectCombat = input.nextLine().toUpperCase();

                    if(selectCombat.equals("V")){
                        System.out.println("Canavar vurdu! ");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0) {
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                        if(this.getPlayer().getHealth() > 0){
                            System.out.println();
                            System.out.println("Siz vurdunuz! ");
                            this.getObstacle().setHealth(this.getObstacle().getHealth()- this.getPlayer().getDamage());
                        afterHit();
                        }
                    }
                    else return false;
                }
            }
            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                if(getObstacle().getName().equals("Yılan")){
                System.out.println("Kazandınız!");
                snakeAward();
            } else {
                System.out.println("Kazandınız!");
                System.out.println(this.getObstacle().getAward() + "para kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel paranız : " + this.getPlayer().getMoney());
            }
        }
        else return false;
    }
        return true;
    }

    public void snakeAward(){
    Random random = new Random();
        int dropChance = random.nextInt(100) + 1;
        if (dropChance <= 15){
            int weaponDropChance = random.nextInt(100) + 1;
            if (weaponDropChance <= 20){
                System.out.println("Tebrikler Tüfek Kazandınız!");
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(3));
            }
            else if (weaponDropChance <= 50){
                System.out.println("Tebrikler Kılıç Kazandınız!");
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(2));
            }
            else if (weaponDropChance <= 100){
                System.out.println("Tebrikler Tabanca Kazandınız!");
                this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjById(1));
            }
        }
        else if (dropChance <= 30){
            int armorDropChance = random.nextInt(100) + 1;
            if (armorDropChance <= 20){
                System.out.println("Tebrikler ağır zırh kazandınız!");
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(3));
            }
            else if (armorDropChance <= 50){
                System.out.println("Tebrikler orta zırh kazandınız!");
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(2));
            }
            else if (armorDropChance <= 100){
                System.out.println("Tebrikler hafif zırh kazandınız!");
                this.getPlayer().getInventory().setArmor(Armor.getArmorObjById(1));
            }
        }
        else if (dropChance <= 55){
            int moneyDropChance = random.nextInt(100) + 1;
            if (moneyDropChance <= 20 ){
                System.out.println("Tebrikler 10 para kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
            }
            if (moneyDropChance <= 50 ){
                System.out.println("Tebrikler 5 para kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
            }
            if (moneyDropChance <= 100 ){
                System.out.println("Tebrikler 1 para kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
            }
        }
        else System.out.println("Hiçbir ödül kazanamadınız...");

    }

    public void afterHit(){
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + "Canı : " + this.getObstacle().getHealth());
    }

    public void playerStates(){
        System.out.println("Oyuncu Değerleri : ");
        System.out.println("-------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Para : " + this.getPlayer().getMoney());
    }

    public void obstacleStats(int i){
        System.out.println(i + "." + this.getObstacle().getName() + " Değerleri : ");
        System.out.println("-------------------");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Ödül : " + this.getObstacle().getAward());
    }

    public int randomObstacleNumber(){
    Random r = new Random();
    return r.nextInt(this.getMaxObstacle()) + 1;

    }


    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
