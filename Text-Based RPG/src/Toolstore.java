
public class Toolstore extends NormalLoc {

    public Toolstore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    boolean onLocation() {
        System.out.println("Mağazaya Hoşgeldin!");
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zırhlar");
            System.out.println("3 - Çıkış");
            System.out.println("Seçimin:");
            int selectCase = keyboard.nextInt();

            while (selectCase < 1 || selectCase > 3) {
                System.out.println("Geçersiz değer, tekrar gir.");
                selectCase = keyboard.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Yine bekleriz!");
                    showMenu = false;
                    break;

            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("SİLAHLAR");
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + "-" + w.getName() + " <Para : " + w.getPrice() + " , Hasar : " + w.getDamage() + " >");
        }
        System.out.println("0 - Mağazaya geri dön");
    }


    public void buyWeapon() {
        System.out.println("Bir silah seç");
        int selectWeaponID = keyboard.nextInt();

        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length) {
            System.out.println("Geçersiz değer, tekrar gir.");
            selectWeaponID = keyboard.nextInt();
        }

        if (selectWeaponID != 0) {
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır.");
                } else {
                    //Satın alma işlemi
                    System.out.println(selectedWeapon.getName() + " silahını satın aldınız!");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan bakiye: " + this.getPlayer().getMoney());
                    System.out.println("Önceki Silahınız " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Yeni Silahınız " + this.getPlayer().getInventory().getWeapon().getName());
                }
            }
        }
    }


    public void printArmor() {
        System.out.println("ZIRHLAR");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + "-" + a.getName() + "<Para : " + a.getPrice() + " Zırh : " + a.getBlock() + " >");
        }
        System.out.println("0 - Mağazaya geri dön");
    }

    public void buyArmor() {
        System.out.println("Bir zırh seç");
        int selectArmorID = keyboard.nextInt();

        while (selectArmorID < 0 || selectArmorID > Armor.armors().length) {
            System.out.println("Geçersiz değer, tekrar gir.");
            selectArmorID = keyboard.nextInt();
        }

        Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);

        if (selectedArmor != null) {

            if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                System.out.println("Yeterli paranız bulunmamaktadır.");
            } else {
                System.out.println(selectedArmor.getName() + " zırhını satın aldınız.");
                int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                this.getPlayer().setMoney(balance);
                System.out.println("Kalan bakiye: " + this.getPlayer().getMoney());
                System.out.println("Önceki Zırhın " + this.getPlayer().getInventory().getArmor().getName());
                this.getPlayer().getInventory().setArmor(selectedArmor);
                System.out.println("Yeni Zırhın " + this.getPlayer().getInventory().getArmor().getName());

            }
        }
    }
}
