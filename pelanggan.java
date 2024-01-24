import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//inheritance

public class pelanggan extends layanan {
    String url = "jdbc:mysql://localhost/uaspbo";
    String namaPelanggan, jPelanggan;
    static String jLayanan;
    int idPelanggan;
    static int idLayanan;
    int hargaTotal;

    public pelanggan() {
        super(idLayanan, jLayanan, harga);
        this.namaPelanggan = namaPelanggan;
        this.idPelanggan = idPelanggan;
    }

    @Override
    public void namaPelanggan() throws SQLException {
        System.out.println("Masukkan Nama Pelanggan: ");
        namaPelanggan = input.next();
        System.out.println("Masukkan ID Pelanggan: ");
        idPelanggan = input.nextInt();
    }

    public void jenisPelanggan() throws SQLException {
        System.out.println("Jenis Pelanggan: \n1. Pelanggan Lama\n2.Pelanggan Baru");
        System.out.println("Masukkan Jenis Pelanggan\t: ");
        jPelanggan = input.next();
        input.nextLine();
    }

    public void hargaTotal() throws SQLException {

        System.out.println("Pilih Id Layanan : ");
        idLayanan = input.nextInt();
        // percabangan

        switch (idLayanan) {
            case 1:

                // proses matematika

                hargaTotal = (int) (harga * 0.70);
                System.out.println("Total: " + hargaTotal);
                break;

            case 2:
                hargaTotal = harga;
                System.out.println("Total: " + hargaTotal);
                break;

            default:
                System.out.println("Tidak ada di pilihan");
                break;
        }
    }

    public void InsertDbPelanggan() throws SQLException {
        String sql = "INSERT INTO tbpelanggan (id_pelanggan, nama_pelanggan, id_layanan, jenis_pelanggan, jenis_layanan, hargatotal) VALUES('"
                + idPelanggan + "', '" + namaPelanggan + "', '" + idLayanan + "', '" + jPelanggan + "', '" + jLayanan
                + "', '" + hargaTotal + "')";
        con = DriverManager.getConnection(url, "root", "");
        Statement statement = con.createStatement();
        statement.execute(sql);
        System.out.println("DATA BERHASIL DIINPUTKAN!");
    }

    public void tampilData1() throws SQLException {
        String sql = "SELECT `id_layanan`, `jenis_layanan`, `harga` FROM `tblayanan` WHERE 1";
        con = DriverManager.getConnection(url, "root", "");
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);

        // perulangan

        while (result.next()) {
            System.out.println("\n");
            System.out.println("ID Layanan\t: ");
            System.out.println(result.getString("id_layanan"));
            System.out.println("Jenis Layanan\t: ");
            System.out.println(result.getString("jenis_layanan"));
            System.out.println("Harga\t: ");
            System.out.println(result.getString("harga"));
        }
    }

    public void tampilData2() throws SQLException {
        String sql = "SELECT id_pelanggan, nama_pelanggan, id_layanan, jenis_pelanggan, jenis_layanan, hargatotal FROM tbpelanggan";
        con = DriverManager.getConnection(url, "root", "");
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery(sql);

        // perulangan

        while (result.next()) {
            System.out.println("\n");
            System.out.println("ID Pelanggan\t: ");
            System.out.println(result.getString("id_pelanggan"));
            System.out.println("\nNama Pelanggan\t: ");
            System.out.println(result.getString("nama_pelanggan"));
            System.out.println("\nID Layanan\t: ");
            System.out.println(result.getString("id_layanan"));
            System.out.println("\nJenis Pelanggan\t: ");
            System.out.println(result.getString("jenis_pelanggan"));
            System.out.println("\nJenis Layanan\t: ");
            System.out.println(result.getString("jenis_layanan"));
            System.out.println("\nHarga Total\t: ");
            System.out.println(result.getString("hargatotal"));
        }
    }

    // CRUD

    public void updateData() throws SQLException {

        // exception

        try {
            System.out.println("\nMasukkan ID Layanan untuk update Data Layanan: ");
            idLayanan = 0;
            idLayanan = input.nextInt();
            input.nextLine();

            String sql = "SELECT id_layanan, jenis_layanan, harga FROM tblayanan WHERE id_layanan = '" + idLayanan
                    + "'";
            con = DriverManager.getConnection(url, "root", "");
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(sql);

            // percabangan

            if (result.next()) {
                System.out.println("ID Layanan [" + result.getInt("id_layanan") + "]\t: ");
                idLayanan = input.nextInt();
                System.out.println("Jenis Layanan [" + result.getString("jenis_layanan") + "]\t: ");
                jLayanan = input.next();
                System.out.println("Harga [" + result.getInt("harga") + "]\t: ");
                harga = input.nextInt();

                sql = "UPDATE tblayanan SET id_layanan = '" + idLayanan + "', jenis_layanan = '" + jLayanan
                        + "', harga = '" + harga + "'";

                if (statement.executeUpdate(sql) > 0) {
                    System.out.println("DATA BERHASIL DI UPDATE!");
                }
            }
            statement.close();
        } catch (SQLException e) {
            System.err.println("Terjadi Kesalahan dalam Update Data");
            System.err.println(e.getMessage());
        }
    }

    // CRUD

    public void deleteData() throws SQLException {
        try {
            tampilData1();
            System.out.println("\nMasukkan ID Layanan yang ingin dihapus: ");
            Integer idLayanan = Integer.parseInt(input.nextLine());

            String sql = "DELETE FROM tblayanan WHERE id_layanan = " + idLayanan;
            con = DriverManager.getConnection(url, "root", "");
            Statement statement = con.createStatement();

            if (statement.executeUpdate(sql) > 0) {
                System.out.println("DATA BERHASIL DIHAPUS!");
            }
        } catch (SQLException e) {
            System.out.println("Terjadi Kesalahan dalam menghapus data!");
        } catch (Exception e) {
            System.out.println("Inputkan data Kembali!");
        }
    }
}
