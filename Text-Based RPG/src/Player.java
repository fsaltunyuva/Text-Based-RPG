import java.util.Scanner;

public class Player extends Game {

    private int damage, health, defHealth, money;
    private String name, charName;
    Scanner keyboard = new Scanner(System.in);
    private Inventory inventory;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }


    public void selectChar() {
        System.out.println("----------------------------------------------------------------");
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
        for (GameChar gameChar : charList) {
            System.out.println("ID:" + gameChar.getId() + "\t Karakter:" + gameChar.getName() + "\t Hasar: " + gameChar.getDamage() + "\t Sağlık: " + gameChar.getHealth() + "\t Para : " + gameChar.getMoney());
        }
        System.out.println("----------------------------------------------------------------");
        System.out.println("Başlamak istediğin karakteri seç:");
        int charSelect = keyboard.nextInt();

        switch (charSelect) {
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
                System.out.println("Doğru giriş yapmadığınız için karakteriniz otomatik olarak 'Samuray' olarak atandı.");
                initPlayer(new Samurai());
        }

    }

    public void initPlayer(GameChar gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setDefHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }

    public void printInfo() {
        System.out.println("Silahınız: " + this.getInventory().getWeapon().getName() +
                "\t Zırhınız: " + this.getInventory().getArmor().getName() +
                "\t Bloklama:" + this.getInventory().getArmor().getBlock() +
                "\t Hasarınız: " + this.getTotalDamage() + "\t Sağlığınız: " + this.getHealth() + "\t Paranız: " + this.getMoney());
    }

    public int getTotalDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0) {
            health = 0;
        }
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getDefHealth() {
        return defHealth;
    }

    public void setDefHealth(int defHealth) {
        this.defHealth = defHealth;
    }
}
