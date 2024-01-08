public class SafeHouse extends NormalLoc{
    public SafeHouse(Player player){
        super(player, "Güvenli Ev");
    }
    @Override
    public boolean onLocation(){
        System.out.println("Güvenli evdesiniz");
        this.getPlayer().setHealth(this.getPlayer().getOrginalHealth());
        System.out.println("Canınız yenilendi");
        gameCheck();
        return true;
    }
    public void gameCheck(){
        if (getPlayer().getInventory().isFirewood() &&
                getPlayer().getInventory().isWater()&&
                getPlayer().getInventory().isFood()){
            System.out.println("Tebrikler! Tüm ödülleri topladınız ve oyunu kazandınız :).");
            System.out.println("Artık yalnızca maden bölgesinde savaşabilirsiniz!");
        }
    }
}
