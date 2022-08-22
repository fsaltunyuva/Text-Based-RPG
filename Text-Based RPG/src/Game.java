import java.util.Scanner;

public class Game {

    Scanner keyboard = new Scanner(System.in);

    public void start() {
        System.out.println("Oyuna Hoş Geldiniz!");
        System.out.println("Lütfen karakterinizin adını girin:");
        String playerName = keyboard.nextLine();
        Player player = new Player(playerName);
        System.out.println("Karakterinizin adı " + player.getName() + ".");
        player.selectChar();

        Location location = null;
        while (true) {
            player.printInfo();
            System.out.println("----------------------------------------------------------------");
            System.out.println("Bölgeler:");
            System.out.println("1 - Güvenli Ev --> Burada canını yenileyebilirsin.");
            System.out.println("2 - Eşya Dükkanı --> Burada Silah veya zırh zatın alabilirsin.");
            System.out.println("3 - Mağara --> Mağaraya gir (Ödülü <yemek>). (Burada zombi ya da zombiler çıkabilir)");
            System.out.println("4 - Orman --> Ormana gir(Ödülü <odun>). (Burada vampir ya da vampirler çıkabilir)");
            System.out.println("5 - Nehir --> Nehire gir(Ödülü <su>). (Burada ayı ya da ayılar çıkabilir)");
            System.out.println("6 - Maden --> Madene gir(Rastgele silah-zırh-para kazanma şansı). (Burada yılan ya da yılanlar" +
                    "çıkabilir)");
            System.out.println("0 - Çıkış Yap --> Oyunu sonlandır.");
            System.out.println("Gitmek istediğin yeri seç: ");
            int selectLoc = keyboard.nextInt();

            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new Toolstore(player);
                    break;
                case 3:
                    if (player.getInventory().isFood() == true) {
                        System.out.println("Mağaradaki düşmanların hepsini yendin!");
                        continue;
                    } else {
                        location = new Cave(player);
                        break;
                    }
                case 4:
                    if (player.getInventory().isFirewood() == true) {
                        System.out.println("Ormandaki düşmanların hepsini yendin!");
                        continue;
                    } else {
                        location = new Forest(player);
                        break;
                    }
                case 5:
                    if (player.getInventory().isWater() == true) {
                        System.out.println("Nehirdeki düşmanların hepsini yendin!");
                        continue;
                    } else {
                        location = new River(player);
                        break;
                    }
                case 6:
                    location = new Mine(player);
                    break;
                default:
                    System.out.println("Geçersiz giriş yaptığın için 'Güvenli Ev' seçimi varsayıldı.");
                    location = new SafeHouse(player);
            }

            if (location == null) {
                System.out.println("KORKAK !");
                break;
            }

            if (location.getClass().getName().equals("SafeHouse")) {
                if (player.getInventory().isFirewood() && player.getInventory().isFood() && player.getInventory().isWater()) {
                    System.out.println("Tebrikler Oyunu Kazandınız !");
                    break;
                }
            }

            if (location.onLocation() == false) {
                System.out.println("OYUN BİTTİ!");
                break;
            }
        }


    }

}
