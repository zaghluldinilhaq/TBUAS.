import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//interface

public class layanan implements barbershop {
    static Connection con;
    String url = "jdbc:mysql://localhost/uaspbo";
    String jLayanan;
    int idLayanan, jumlah, hargatotal;
    static int harga;
    Scanner input = new Scanner(System.in);

    // constructor

    public layanan(int idLayanan, String jlayanan, int harga) {
        this.idLayanan = idLayanan;
        this.jLayanan = jlayanan;
        this.harga = harga;
    }

    @Override
    public void idLayanan() throws SQLException {
        System.out.println("Masukkan ID Layanan: ");
        idLayanan = input.nextInt();
    }

    @Override
    public void jenisLayanan() throws SQLException {
        System.out.println("Masukkan Jenis Layanan\t: ");
        jLayanan = input.next();
        input.nextLine();
    }

    @Override
    public void namaPelanggan() throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void jenisPelanggan() throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public void harga() throws SQLException {
        System.out.println("Masukkan Harga Jenis Layanan: ");
        harga = input.nextInt();
    }

    @Override
    public void hargaTotal() throws SQLException {
        // TODO Auto-generated method stub

    }

    public void InsertDbLayanan() throws SQLException {
        String sql = "INSERT INTO tblayanan (id_layanan, jenis_layanan, harga) VALUES('" + idLayanan
                + "', '" + jLayanan
                + "', '" + harga + "')";
        con = DriverManager.getConnection(url, "root", "");
        Statement statement = con.createStatement();
        statement.execute(sql);
        System.out.println("DATA BERHASIL DIINPUTKAN!");
    }

}
