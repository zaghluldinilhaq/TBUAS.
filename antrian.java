import java.util.Scanner;

public class antrian {

    // collection framework

    public static String queue[] = new String[5];
    public static int a = 0;

    Scanner data = new Scanner(System.in);

    // constructor

    public antrian(int i) {

    }

    public boolean queueStorage() {
        if (a < queue.length) {
            return true;
        } else {
            return false;
        }
    }

    public void createQueue() {
        String nama;
        System.out.println("Masukkan Nama Pelanggan: ");
        nama = data.nextLine();
        queue[a] = nama;
        a++;
    }

    public void removeQueue() {
        a--;
        for (int i = 0; i < a; i++) {
            queue[i] = queue[i + 1];
        }
    }

    public void displayQueue() {
        System.out.println("Daftar Antrian: ");
        for (int i = 0; i < a; i++) {
            System.out.println(+i + 1 + "." + queue[i]);
        }
        System.out.println("");
    }
}
