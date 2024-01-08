import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class Game {
    private Scanner input = new Scanner (System.in);
    private BattleLoc battleLocation;
    private List<Location> locations = new ArrayList<>();

    public void start(){
        System.out.println("Maceya Oyununa Hoşgeldiniz");
        System.out.print("Size nasıl hitap edelim istersiniz? Lütfen bir isim giriniz : ");
        String playerName = input.nextLine();

        Player player = new Player(playerName);
        System.out.println("Adaya Hoşgeldin " + player.getName() + "!!!");
        System.out.println("Burada yaşanacaklardan biz sorumlu değiliz.");
        System.out.println("Oyuna başlamak için öncelikle bir karakter seçmelisin!");
        player.selectChar();
        Location location = null;

        while(true){
            player.printInfo();
            System.out.println("-----------------------------------------------------------");
            System.out.println("Bölgeler");
            System.out.println("-----------------------------------------------------------");
            System.out.println("0. Çıkış Yap");
            System.out.println("1. Güvenli Ev");
            System.out.println("2. Eşya Dükkanı");
            System.out.println("3. Mağara ----> Ödül : Yemek || Dikkatli ol! Zombi çıkabilir!");
            System.out.println("4. Orman ----> Ödül : Odun  ||  Dikkatli ol! Vampir çıkabilir!");
            System.out.println("5. Nehir ----> Ödül : Su    ||  Dikkatli ol! Ayı çıkabilir!");
            System.out.println("6. Maden ----> Ödül : Para, Silah veya Zırh    ||  Dikkatli ol! Yılan çıkabilir!");
            System.out.println("-----------------------------------------------------------");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz : ");
            int selectLoc = input.nextInt();

            switch(selectLoc){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse (player);
                    break;
                case 2:
                    location = new ToolStore (player);
                    break;
                case 3:
                    if(!player.getInventory().isFood()){
                        System.out.print("Mağaraya giriyorsunuz ");
                        location = new Cave(player);
                    } else {
                        System.out.print("Buradaki ödülleri topladığın için artık giriş yapamazsın! ");
                        location = new SafeHouse (player);
                    }
                    break;
                case 4:
                    if(!player.getInventory().isFirewood()){
                        System.out.print("Ormana giriyorsunuz ");
                        location = new Forest(player);
                    } else {
                        System.out.print("Buradaki ödülleri topladığın için artık giriş yapamazsın! ");
                        location = new SafeHouse (player);
                    }
                    break;
                case 5:
                    if(!player.getInventory().isWater()){
                        System.out.print("Nehire giriyorsunuz ");
                        location = new River(player);
                    } else {
                        System.out.print("Buradaki ödülleri topladığın için artık giriş yapamazsın! ");
                        location = new SafeHouse (player);
                    }
                        break;
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    System.out.print("Lütfen geçerli bir bölge giriniz");
                    break;
            }
            if (location == null) {
                System.out.print("Oyun bitti. Yine bekleriz :)");
                break;
            }

            if (!location.onLocation()){
                System.out.print("GAME OVER");
                break;
            }
        }
    }
}




