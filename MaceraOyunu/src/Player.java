import java.util.Scanner;
public class Player {
    private int damage;
    private int health;
    private int orginalHealth;
    private int money;
    private String charName;
    private String name;
    private Scanner input = new Scanner(System.in);
    private Inventory inventory;

    public Player(String name){
        this.name = name;
        this.inventory = new Inventory();
    }


    public void selectChar(){
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};

        System.out.println("-----------------------------------------------------------");
        System.out.println("Karakterler");
        System.out.println("-----------------------------------------------------------");

        for(GameChar gameChar: charList) {
            System.out.println("ID : " + gameChar.getId() +
                    "\t\t Karakter : " + gameChar.getName() +
                    "\t\t  Hasar : " + gameChar.getDamage() +
                    "\t    Sağlık : " + gameChar.getHealth() +
                    "\t    Para : " + gameChar.getMoney());

        }
        System.out.println("-----------------------------------------------------------");
        System.out.print("Lütfen bir karakter giriniz : ");
        int selectChar = input.nextInt();

        switch(selectChar){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }

        System.out.println("Karakter : " + this.getCharName() +
                "\t--------->>" +
                "\t  Hasar : " + this.getDamage() +
                "\t  Sağlık : " + this.getHealth() +
                "\t  Para : " + this.getMoney());
    }


    public void initPlayer(GameChar gameChar){
        this.setDamege(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOrginalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }
    public void printInfo(){
        System.out.println(
                "Silah : " + this.getInventory().getWeapon().getName() +
                        "\t  Zırh : " + this.getInventory().getArmor().getName() +
                        "\t  Bloklama : " + this.getInventory().getArmor().getBlock() +
                        "\t  Hasar : " + this.getTotalDamage() +
                        "\t  Sağlık durumunuz : " + this.getHealth() +
                        "\t  Paranız : " + this.getMoney());
    }
public int getTotalDamage(){
        return this.getInventory().getWeapon().getDamage();
}
    public int getDamage(){
        return damage;
    }
    public void setDamege(int damage){
        this.damage = damage;
}
    public int getHealth(){
        return health;
    }
    public void setHealth(int health){
        if(health < 0){
            health = 0;
        }
        this.health = health;
    }
    public int getMoney(){
        return money;
    }
    public void setMoney(int money){
        this.money = money;
    }
    public String getCharName(){
        return charName;
    }
    public void setCharName(String charName){
        this.charName = charName;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Weapon getWeapon(){
        return this.getInventory().getWeapon();
    }

    public int getOrginalHealth() {
        return orginalHealth;
    }

    public void setOrginalHealth(int orginalHealth) {
        this.orginalHealth = orginalHealth;
    }
}

