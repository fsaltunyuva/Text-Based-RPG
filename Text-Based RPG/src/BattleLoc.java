import java.util.Random;

public abstract class BattleLoc extends Location {

    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player);
        this.name = name;
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    boolean onLocation() {

        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şu anda buradasınız : " + this.getName());
        System.out.println("Burada " + obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor!");
        System.out.println("Savaş veya Kaç ! (Savaşmak için S, Kaçmak için K)");
        String selectCase = keyboard.nextLine();
        selectCase = selectCase.toUpperCase();

        if (selectCase.equals("S") && combat(obsNumber)) {
            System.out.println(this.getName() + " bölgesindeki tüm düşmanları yendiniz !");

            if (this.award.equalsIgnoreCase("Food") && getPlayer().getInventory().isFood() == false) {
                System.out.println("Tüm düşmanları temizlediğiniz için mağaradan yemek aldınız!");
                getPlayer().getInventory().setFood(true);
            } else if (this.award.equalsIgnoreCase("Water") && getPlayer().getInventory().isWater() == false) {
                System.out.println("Tüm düşmanları temizlediğiniz için nehirden su aldınız!");
                getPlayer().getInventory().setWater(true);
            } else if (this.award.equalsIgnoreCase("Firewood") && getPlayer().getInventory().isFirewood() == false) {
                System.out.println("Tüm düşmanları temizlediğiniz için ormandan odun aldınız!");
                getPlayer().getInventory().setFirewood(true);
            }

            return true;
        }
        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz !");
            return false;
        }

        return true;
    }

    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            Random random = new Random();
            int dropChance = random.nextInt(100);  //r.nextInt(high-low) + low;
            int secondDropChance = random.nextInt(100);
            this.getObstacle().setHealth(this.getObstacle().getDefHealth());
            playerStats();
            obstacleStats(i);

            Random r = new Random();
            int chance = r.nextInt() + 100;

            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                if (chance <= 50) { //%50 Şans
                    if (this.getObstacle().getHealth() > 0) {
                        System.out.println();
                        System.out.println("Düşman oyuncuya vurdu!");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0) {
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                    chance = 55;

                } else { // %50 Şans
                    System.out.println("Vur veya Kaç ! (Vurmak için V, Kaçmak için K)");
                    String selectCombat = keyboard.nextLine().toUpperCase();
                    if (selectCombat.equals("V")) {
                        System.out.println(this.getPlayer().getName() + " vurdu!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    } else {
                        return false;
                    }

                    chance = 10;

                }

            }

            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Düşmanı Yendiniz!");

                if (!this.award.equalsIgnoreCase("random")) { //Maden olmayan yerler
                    System.out.println(this.getObstacle().getAward() + " para kazandınız.");
                } else { //Maden
                    if (dropChance <= 15) { // %15 Şans ile Silah Düşürme

                        if (secondDropChance <= 20) { // %20 Şans
                            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(3));
                            System.out.println("Tüfek Düşürdün!");
                        } else if (secondDropChance > 20 && secondDropChance <= 50) { // %30 Şans
                            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));
                            System.out.println("Kılıç Düşürdün!");
                        } else if (secondDropChance > 50) { // %50 Şans
                            this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
                            System.out.println("Tabanca Düşürdün!");
                        }
                    } else if (dropChance > 15 && dropChance <= 30) { // %15 Şans ile Zırh Düşürme
                        if (secondDropChance <= 20) { // %20 Şans
                            this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(3));
                            System.out.println("Ağır Zırh Düşürdün!");
                        } else if (secondDropChance > 20 && secondDropChance <= 50) { // %30 Şans
                            this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(2));
                            System.out.println("Ağır Orta Düşürdün!");
                        } else if (secondDropChance > 50) { // %50 Şans
                            this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(1));
                            System.out.println("Ağır Hafif Düşürdün!");
                        }
                    } else if (dropChance > 30 && dropChance <= 55) { // %25 Şans ile Para Düşürme
                        if (secondDropChance <= 20) { // %20 Şans
                            getPlayer().setMoney(getPlayer().getMoney() + 10);
                            System.out.println("10 Altın düşürdün !");
                        } else if (secondDropChance > 20 && secondDropChance <= 50) { // %30 Şans
                            getPlayer().setMoney(getPlayer().getMoney() + 5);
                            System.out.println("5 Altın düşürdün !");
                        } else if (secondDropChance > 50) { // %50 Şans
                            getPlayer().setMoney(getPlayer().getMoney() + 1);
                            System.out.println("1 Altın düşürdün !");
                        }
                    } else { // %45 Şans ile hiçbir şey kazanamama ihtimali
                        System.out.println("TÜH! Hiçbir şey kazanamadın!");
                    }
                }

                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel paranız " + this.getPlayer().getMoney());
            } else {
                return false;
            }
        }
        return true;
    }

    public void afterHit() {
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı : " + this.getObstacle().getHealth());
        System.out.println("-----------------------------");
    }

    public void playerStats() {
        System.out.println("Oyuncu Değerleri");
        System.out.println("-----------------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Blok : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Para : " + this.getPlayer().getMoney());
        System.out.println();
    }

    public void obstacleStats(int i) {
        System.out.println(i + "." + this.getObstacle().getName() + " Değerleri");
        System.out.println("-----------------------------");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        if (!this.award.equalsIgnoreCase("random")) {
            System.out.println("Düşüreceği Para: " + this.getObstacle().getAward());
        }
        System.out.println();
    }


    public int randomObstacleNumber() {
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
