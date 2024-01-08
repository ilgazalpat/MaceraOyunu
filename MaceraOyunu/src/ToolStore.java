public class ToolStore extends NormalLoc{
    public ToolStore(Player player){
        super(player,"Mağaza");
    }
    @Override
    public boolean onLocation(){
        boolean showMenu = true;
        while(showMenu){
            System.out.println("-------- Mağazaya Hoşgeldiniz --------");

            System.out.println("1. Silahlar ");
            System.out.println("2. Zırhlar ");
            System.out.println("3. Çıkış Yap ");

            System.out.print("Seçiminiz : ");
            int selectCase = Location.input.nextInt();
            while(selectCase < 1 || selectCase > 3){
                System.out.print("Geçersiz değer, tekrar giriniz : ");
                selectCase = Location.input.nextInt();
            }

            switch (selectCase){
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Yine bekleriz :)");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }
    public void printWeapon(){
        System.out.println("-------- Silahlar --------");
        System.out.println();
        for (Weapon w : Weapon.weapons()){
            System.out.println(w.getId() + " - " +
                    w.getName() + "\t\t----->   Para : " +
                    w.getPrice() + "\t Hasar : " +
                    w.getDamage());

        }
        System.out.println("0. Çıkış Yap ");
    }

    public void buyWeapon(){
        System.out.print("Bir silah seçiniz : ");
        int selectWeaponID = input.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length){
            System.out.print("Geçersiz değer, tekrar giriniz : ");
            selectWeaponID = Location.input.nextInt();
        }
        if (selectWeaponID != 0){
            Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeaponID);
            if(selectedWeapon != null){
                if(selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.print("Yeterli paranız bulunmamaktadır!");
                } else {
                    System.out.print(selectedWeapon.getName() + " silahını satın aldınız");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.print("Kalan bakiyeniz : " + this.getPlayer().getMoney());
                    System.out.print("Önceki silahınız : " +  this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.print("Yeni silahınız : " +  this.getPlayer().getInventory().getWeapon().getName());
                }
            }
        }
    }

    public void printArmor(){
        System.out.println("-------- Zırhlar --------");
        System.out.println();
        for(Armor a : Armor.armors()){
            System.out.println(a.getId() + "-" +
                    a.getName() + "\t\t----->   Para : " +
                    a.getPrice() + "\tZırh : " +
                    a.getBlock());
        }
        System.out.println("0. Çıkış Yap ");
    }
    public void buyArmor(){
        System.out.print("Bir zırh seçiniz : ");
        int selectArmorID = input.nextInt();
        while (selectArmorID < 0 || selectArmorID > Armor.armors().length){
            System.out.print("Geçersiz değer, tekrar giriniz : ");
            selectArmorID = Location.input.nextInt();
        }
        if (selectArmorID != 0){
            Armor selectedArmor = Armor.getArmorObjById(selectArmorID);

            if (selectedArmor != null){
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.print("Yeterli paranız bulunmamaktadır!");
                } else {
                    System.out.print(selectedArmor.getName() + " zırhını satın aldınız");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.print("Kalan bakiyeniz : " + this.getPlayer().getMoney());
                    System.out.print("Önceki zırhınız : " + this.getPlayer().getInventory().getArmor().getName());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.print("Yeni zırhınız : " + this.getPlayer().getInventory().getArmor().getName());
                }
            }
        }
    }
}





