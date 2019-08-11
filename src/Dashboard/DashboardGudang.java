/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import Transaksi.CariBarang_DashboardGudang;
import Koneksi.Koneksi;
import Login.SetGet;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Lenovo
 */
public class DashboardGudang extends javax.swing.JFrame {

    /**
     * Creates new form DashboardGudang
     */
    boolean maximized = true;
    DefaultTableModel list_produk = new DefaultTableModel(
            new Object[][]{},
            new String[]{"KODE BARANG", "NAMA BARANG", "SATUAN", "JUMLAH PEMASOKAN (QTY)", "HARGA DISTRIBUTOR", "TOTAL", "jml_stok"
            }) // BIAR FIELD TABEL TIDAK BISA EDIT
    {
        boolean[] tdk_bisa_edit = new boolean[]{
            false, false, true, true, true, false, false
        };

        public boolean isCellEditable(int row, int column) {
            return tdk_bisa_edit[column];
        }
    };

    public DashboardGudang() {
        initComponents();
        String id = SetGet.getId_user();
        id_users.setText(id);
        String aksess = SetGet.getAkses();
        akses.setText(aksess);
        String namaa = SetGet.getNama();
        nama.setText(namaa);

        tgl_sekarang();
        kode();
        nonaktif();
        ambil_distributor();
        kosongkan();
        hidden();
        fullscreen();
    }

    private void fullscreen() {
        if (maximized) {
            DashboardGudang.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            DashboardGudang.this.setMaximizedBounds(env.getMaximumWindowBounds());
            maximized = false;
        } else {
            setExtendedState(JFrame.NORMAL);
            maximized = true;
        }
    }

    private void tgl_sekarang() {
        Date ys = new Date();
        SimpleDateFormat s = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        txt_tanggal.setText(s.format(ys));
    }

    public void kode() {
        try {
            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) Koneksi.configDB();
            Statement stat = c.createStatement();
            String sql = "SELECT MAX(right(id_pemasokan,7)) AS no FROM pemasokan";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                if (rs.first() == false) {
                    txt_id_pemasokan.setText("P0000001");
                } else {
                    rs.last();
                    int set_id = rs.getInt(1) + 1;
                    String no = String.valueOf(set_id);
                    int id_next = no.length();
                    for (int a = 0; a < 7 - id_next; a++) {
                        no = "0" + no;
                    }
                    txt_id_pemasokan.setText("P" + no);
                }
            }
        } catch (SQLException ex) {

        }
    }

    private void nonaktif() {
        btn_hapus.setEnabled(false);
        btn_batal.setEnabled(false);
    }

    private void inisialisasi_tabel() {
        daftar_produk.setModel(list_produk);
        TableColumn col2 = daftar_produk.getColumnModel().getColumn(6);
        col2.setMinWidth(0);
        col2.setMaxWidth(0);
        col2.setWidth(0);
        col2.setPreferredWidth(0);
    }

    private void simpan_ditabel() {    //SIMPAN SEMENTARA
        try {

            //JIKA STRING
            String id_barang = String.valueOf(barcode.getText());
            String nama = String.valueOf(txt_nama.getText());
            String satuan = String.valueOf(txt_satuan.getText());

            //JIKA INTEGER
            int hrg_distributor = Integer.parseInt(txt_hrg_distributor.getText());
            int qty = 1;
            int total = Integer.parseInt(txt_hrg_distributor.getText());
            int jml_stok = Integer.parseInt(txt_jml_stok.getText());

            inisialisasi_tabel();
            // kolom ke 4 => array(3);
            list_produk.addRow(new Object[]{id_barang, nama, satuan, qty, hrg_distributor, total, jml_stok});

        } catch (NumberFormatException exception) {
            System.out.println("Error ss : " + exception);
        }

    }

    public void getsum() {  //MENJUMLAHKAN HARGA DATA
        int batas = daftar_produk.getRowCount();
        int sum = 0;
        for (int i = 0; i < batas; i++) {
            sum = sum + Integer.parseInt(daftar_produk.getValueAt(i, 5).toString());
        }
        double angka = (double) sum;
        String mataUang = String.format("%,.0f", angka).replaceAll(",", ".");
        txt_total.setText(mataUang);

    }

    public void getKembalian() {
        if (txt_total.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Masukkan Barang !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            txt_bayar.setText("");
        } else if (txt_bayar.getText().equals("")) {
            txt_kembalian.setText("0");
        } else {
            // MENGUBAH DARI FORMAT RUPIAH KE NUMBER
            String total = txt_total.getText();
            DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

            formatRp.setCurrencySymbol("");
            formatRp.setMonetaryDecimalSeparator(' ');
            formatRp.setGroupingSeparator('.');
            kursIndonesia.setDecimalFormatSymbols(formatRp);
            try {
                Number number = kursIndonesia.parse(total);
                double nilai = number.doubleValue();
                int bayar = Integer.parseInt(txt_bayar.getText());
                double kembalian = bayar - nilai;
                String kembalians = String.format("%,.0f", kembalian).replaceAll(",", ".");
                txt_kembalian.setText(kembalians);
            } catch (ParseException ex) {
                System.out.println("Kesalahan Parsing");
            }

        }
    }

    private void ambil_distributor() {
        try {
            cb_distributor.addItem("Pilih Distributor");
            txt_id_distributor.setText(null);
            //int no=1;
            String sql = "SELECT * FROM distributor ORDER BY id_distributor ASC";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                String nama = res.getString("nm_distributor");
                cb_distributor.addItem(nama);
            }
        } catch (SQLException e) {
        }
    }

    public void updateHarga() {

        int baris = Integer.parseInt(txt_baris.getText());
        String kode = daftar_produk.getValueAt(baris, 0).toString();
        txt_kode.setText(kode);
        String qty = daftar_produk.getValueAt(baris, 3).toString();
        txt_qty.setText(qty);
        String hrg_distributor = daftar_produk.getValueAt(baris, 4).toString();
        txt_hrg_distributor.setText(hrg_distributor);

        int total = (Integer.parseInt(qty)) * (Integer.parseInt(hrg_distributor));
        list_produk.setValueAt(total, baris, 5);

        getsum();
        if (txt_bayar.getText().length() > 0) {
            getKembalian();
        }

    }

    private void kosongkan() {
        txt_nama.setText(null);
        txt_hrg_distributor.setText(null);
        txt_satuan.setText(null);
        txt_jml_stok.setText(null);
        txt_kode.setText(null);
        txt_qty.setText(null);
        txt_baris.setText(null);
        txt_bayar.setText(null);
        txt_total.setText(null);
        txt_kembalian.setText(null);
        update_stok.setText(null);
        DefaultTableModel model = (DefaultTableModel) daftar_produk.getModel();
        model.setRowCount(0);
        cb_distributor.setSelectedIndex(0);
        barcode.setText(null);
    }

    private void hidden() {
        txt_nama.setVisible(false);
        txt_hrg_distributor.setVisible(false);
        txt_satuan.setVisible(false);
        txt_jml_stok.setVisible(false);
        txt_kode.setVisible(false);
        txt_baris.setVisible(false);
        txt_qty.setVisible(false);
        txt_id_pemasokan.setVisible(false);
        id_users.setVisible(false);
        txt_id_distributor.setVisible(false);

        id_user.setVisible(false);
        akses.setVisible(false);
        nama.setVisible(false);
        update_stok.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        id_user = new javax.swing.JLabel();
        akses = new javax.swing.JLabel();
        nama = new javax.swing.JLabel();
        txt_id_pemasokan = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        daftar_produk = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txt_bayar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_kembalian = new javax.swing.JTextField();
        txt_tanggal = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        barcode = new javax.swing.JTextField();
        btn_cariBarang = new javax.swing.JButton();
        cb_distributor = new javax.swing.JComboBox<>();
        btn_simpan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        txt_nama = new javax.swing.JTextField();
        txt_hrg_distributor = new javax.swing.JTextField();
        txt_jml_stok = new javax.swing.JTextField();
        txt_kode = new javax.swing.JTextField();
        txt_baris = new javax.swing.JTextField();
        txt_qty = new javax.swing.JTextField();
        txt_id_distributor = new javax.swing.JLabel();
        id_users = new javax.swing.JLabel();
        txt_satuan = new javax.swing.JTextField();
        btn_batal = new javax.swing.JButton();
        update_stok = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1366, 783));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        id_user.setText("jLabel5");
        getContentPane().add(id_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        akses.setText("jLabel3");
        getContentPane().add(akses, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, -1, -1));

        nama.setText("jLabel4");
        getContentPane().add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, -1, -1));

        txt_id_pemasokan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_id_pemasokan.setText("Kode Pemasokan");
        getContentPane().add(txt_id_pemasokan, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 10, 20));

        daftar_produk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Satuan", "Jumlah Pemasokan (qty)", "Harga Distributor", "Total"
            }
        ));
        daftar_produk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                daftar_produkMouseClicked(evt);
            }
        });
        daftar_produk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                daftar_produkKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(daftar_produk);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 87, 1327, 540));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Distributor");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 20));

        txt_bayar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bayarActionPerformed(evt);
            }
        });
        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_bayarKeyTyped(evt);
            }
        });
        getContentPane().add(txt_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 640, 160, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("bayar :");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 640, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("total :");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 50, -1, -1));

        txt_total.setEditable(false);
        txt_total.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 50, 140, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("kembalian :");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 50, -1, -1));

        txt_kembalian.setEditable(false);
        txt_kembalian.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        getContentPane().add(txt_kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1203, 50, 130, -1));

        txt_tanggal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_tanggal.setText("Tanggal Pemasokan");
        getContentPane().add(txt_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1017, 2, 320, 31));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("Barcode :");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        barcode.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barcodeKeyPressed(evt);
            }
        });
        getContentPane().add(barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 160, -1));

        btn_cariBarang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_cariBarang.setText("Cari Barang");
        btn_cariBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariBarangActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cariBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

        cb_distributor.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cb_distributor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_distributorActionPerformed(evt);
            }
        });
        getContentPane().add(cb_distributor, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 134, -1));

        btn_simpan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 640, -1, -1));

        btn_hapus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 71, -1));

        txt_nama.setEditable(false);
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 50, 33, -1));

        txt_hrg_distributor.setEditable(false);
        getContentPane().add(txt_hrg_distributor, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, 31, -1));

        txt_jml_stok.setEditable(false);
        getContentPane().add(txt_jml_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 39, -1));

        txt_kode.setEditable(false);
        getContentPane().add(txt_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 25, -1));

        txt_baris.setEditable(false);
        getContentPane().add(txt_baris, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 50, 33, -1));

        txt_qty.setEditable(false);
        getContentPane().add(txt_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 50, 36, -1));

        txt_id_distributor.setText("id");
        getContentPane().add(txt_id_distributor, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 28, 23));

        id_users.setText("id user");
        getContentPane().add(id_users, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 50, -1, -1));

        txt_satuan.setEditable(false);
        getContentPane().add(txt_satuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 38, -1));

        btn_batal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });
        getContentPane().add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 50, -1, -1));
        getContentPane().add(update_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void daftar_produkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_daftar_produkMouseClicked
        // TODO add your handling code here:
        btn_hapus.setEnabled(true);

        int baris = daftar_produk.rowAtPoint(evt.getPoint());
        txt_baris.setText(String.valueOf(baris));
        updateHarga();
    }//GEN-LAST:event_daftar_produkMouseClicked

    private void daftar_produkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_daftar_produkKeyReleased
        // TODO add your handling code here:
        int row = this.daftar_produk.getSelectedRow();
        this.txt_baris.setText(String.valueOf(row));

        updateHarga();
    }//GEN-LAST:event_daftar_produkKeyReleased

    private void txt_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyReleased
        // TODO add your handling code here:
        int max = 9;
        int len = txt_bayar.getText().length();
        if (len > max) {
            JOptionPane.showMessageDialog(null, "Maximal 9 digit !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            txt_bayar.setText("");
        }
        getKembalian();
    }//GEN-LAST:event_txt_bayarKeyReleased

    private void txt_bayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyTyped
        // TODO add your handling code here:
        char Test = evt.getKeyChar();
        if (!(Character.isDigit(Test))) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_bayarKeyTyped

    private void barcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcodeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) //JIKA ADA BARCODE OTOMATIS IKI!!
        {
            // Mengambil nama,harga,dll
            // di hidden texfield e

            String id_barang = barcode.getText();
            try {
                //int no=1;
                String sql = "SELECT * FROM barang b,satuan s WHERE b.id_barang='" + id_barang + "' && b.id_satuan = s.id_satuan";
                java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet res = stm.executeQuery(sql);
                while (res.next()) {
                    String nama = res.getString("b.nm_barang");
                    String harga_distributor = res.getString("b.hrg_beli");
                    String jml_stok = res.getString("b.jml_stok");
                    String satuan = res.getString("s.nm_satuan");

                    txt_nama.setText(nama);
                    txt_hrg_distributor.setText(harga_distributor);
                    txt_jml_stok.setText(jml_stok);
                    txt_kode.setText(id_barang);
                    txt_baris.setText(String.valueOf(list_produk.getRowCount()));
                    txt_qty.setText("1");
                    txt_satuan.setText(satuan);

                }

            } catch (SQLException e) {
            }

            // Simpan nang tabel
            try {
                // TODO add your handling code here:
                com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) Koneksi.configDB();
                Statement stat = c.createStatement();
                String sql2 = "SELECT * FROM barang WHERE id_barang='" + id_barang + "'";
                ResultSet rs = stat.executeQuery(sql2);
                if (barcode.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Masukkan kode !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                } else if (rs.next() == false) {
                    JOptionPane.showMessageDialog(null, "Kode barang tidak ada !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    barcode.setText(null);
                    return;
                } else {
                    simpan_ditabel();
                    getsum();
                    barcode.setText("");

                    getKembalian();

                }
            } catch (SQLException ex) {
                Logger.getLogger(DashboardGudang.class.getName()).log(Level.SEVERE, null, ex);
            }
            btn_batal.setEnabled(true);
        }
    }//GEN-LAST:event_barcodeKeyPressed

    private void btn_cariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariBarangActionPerformed
        // TODO add your handling code here:
        new CariBarang_DashboardGudang().setVisible(true);
    }//GEN-LAST:event_btn_cariBarangActionPerformed

    private void cb_distributorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_distributorActionPerformed
        // TODO add your handling code here:
        String tampung = cb_distributor.getSelectedItem().toString();
        if (tampung == "Pilih Distributor") {
            txt_id_distributor.setText(null);
        } else {
            try {
                //int no=1;
                String sql = "SELECT * FROM distributor WHERE nm_distributor='" + tampung + "'";
                java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet res = stm.executeQuery(sql);
                while (res.next()) {
                    String id = res.getString("id_distributor");
                    txt_id_distributor.setText(id);
                }
            } catch (SQLException e) {
            }
        }
    }//GEN-LAST:event_cb_distributorActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        if (txt_id_distributor.getText() == null) {
            JOptionPane.showMessageDialog(null, "Pilih Distributor", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else if (txt_bayar.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Isikan Bayar", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Date skrg = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String tgl_pemasokan = format.format(skrg);
                String id_user = id_users.getText();

                String totals = txt_total.getText();
                DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                formatRp.setCurrencySymbol("");
                formatRp.setMonetaryDecimalSeparator(' ');
                formatRp.setGroupingSeparator('.');
                kursIndonesia.setDecimalFormatSymbols(formatRp);
                Number number = kursIndonesia.parse(totals);
                double total = number.doubleValue();
                int total_harga = (int) total;

                String kembalians = txt_kembalian.getText();
                formatRp.setCurrencySymbol("");
                formatRp.setMonetaryDecimalSeparator(' ');
                formatRp.setGroupingSeparator('.');
                kursIndonesia.setDecimalFormatSymbols(formatRp);
                Number numbers = kursIndonesia.parse(kembalians);
                double kembalian = numbers.doubleValue();
                int total_kembalian = (int) kembalian;

                if (total_kembalian < 0) {
                    JOptionPane.showMessageDialog(null, "Kembalian tidak boleh minus", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Insert Pemasokan
                    String sql_transaksi = "INSERT INTO pemasokan VALUES ('" + txt_id_pemasokan.getText() + "','" + txt_id_distributor.getText() + "','" + id_user + "','" + total_harga + "','" + txt_bayar.getText() + "','" + total_kembalian + "','" + tgl_pemasokan + "')";
                    java.sql.Connection conn = (Connection) Koneksi.configDB();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql_transaksi);
                    pst.execute();

                    // Insert Detail Pemasokan
                    int row = daftar_produk.getRowCount();
                    for (int i = 0; i < row; i++) {
                        String id_barang = daftar_produk.getValueAt(i, 0).toString();
                        int qty = Integer.parseInt(daftar_produk.getValueAt(i, 3).toString());
                        int total_hrg = Integer.parseInt(daftar_produk.getValueAt(i, 5).toString());
//                        int stok = Integer.parseInt(daftar_produk.getValueAt(i, 6).toString());

                        // Ambil stok                        
                        com.mysql.jdbc.Connection c1 = (com.mysql.jdbc.Connection) Koneksi.configDB();
                        Statement stat1 = c1.createStatement();
                        String sql_ambil_stok = "SELECT jml_stok from barang where id_barang ='" + id_barang + "'";
                        ResultSet rs1 = stat1.executeQuery(sql_ambil_stok);
                        while (rs1.next()) {
                            String jml_stok = rs1.getString("jml_stok");

                            update_stok.setText(jml_stok);
                        }

                        int akhir_stok = Integer.parseInt(update_stok.getText()) + qty;

                        String sql_detail_transaksi = "insert into detail_pemasokan (id_pemasokan,id_barang,qty,total_hrg) values('" + txt_id_pemasokan.getText() + "','" + id_barang + "','" + qty + "','" + total_hrg + "')";
                        java.sql.PreparedStatement pst2 = conn.prepareStatement(sql_detail_transaksi);
                        pst2.execute();

                        String sql_update_stok = "UPDATE barang SET jml_stok = '" + akhir_stok + "' WHERE id_barang = '" + id_barang + "'";
                        java.sql.PreparedStatement pst3 = conn.prepareStatement(sql_update_stok);
                        pst3.execute();

                    }
                    JOptionPane.showMessageDialog(null, "Transaksi Berhasil!");
                    hidden();
                    tgl_sekarang();
                    nonaktif();
                    kode();
                    kosongkan();
                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (ParseException ex) {
                Logger.getLogger(DashboardGudang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        list_produk.removeRow(daftar_produk.getSelectedRow());
        btn_hapus.setEnabled(false);
        getsum();
        txt_bayar.setText("");
        txt_kembalian.setText("0");
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        kosongkan();
        btn_batal.setEnabled(false);
        btn_hapus.setEnabled(false);
    }//GEN-LAST:event_btn_batalActionPerformed

    private void txt_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bayarActionPerformed
        // TODO add your handling code here:
        if (txt_id_distributor.getText() == null) {
            JOptionPane.showMessageDialog(null, "Pilih Distributor", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else if (txt_bayar.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Isikan Bayar", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Date skrg = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String tgl_pemasokan = format.format(skrg);
                String id_user = id_users.getText();

                String totals = txt_total.getText();
                DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                formatRp.setCurrencySymbol("");
                formatRp.setMonetaryDecimalSeparator(' ');
                formatRp.setGroupingSeparator('.');
                kursIndonesia.setDecimalFormatSymbols(formatRp);
                Number number = kursIndonesia.parse(totals);
                double total = number.doubleValue();
                int total_harga = (int) total;

                String kembalians = txt_kembalian.getText();
                formatRp.setCurrencySymbol("");
                formatRp.setMonetaryDecimalSeparator(' ');
                formatRp.setGroupingSeparator('.');
                kursIndonesia.setDecimalFormatSymbols(formatRp);
                Number numbers = kursIndonesia.parse(kembalians);
                double kembalian = numbers.doubleValue();
                int total_kembalian = (int) kembalian;

                if (total_kembalian < 0) {
                    JOptionPane.showMessageDialog(null, "Kembalian tidak boleh minus", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Insert Pemasokan
                    String sql_transaksi = "INSERT INTO pemasokan VALUES ('" + txt_id_pemasokan.getText() + "','" + txt_id_distributor.getText() + "','" + id_user + "','" + total_harga + "','" + txt_bayar.getText() + "','" + total_kembalian + "','" + tgl_pemasokan + "')";
                    java.sql.Connection conn = (Connection) Koneksi.configDB();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql_transaksi);
                    pst.execute();

                    // Insert Detail Pemasokan
                    int row = daftar_produk.getRowCount();
                    for (int i = 0; i < row; i++) {
                        String id_barang = daftar_produk.getValueAt(i, 0).toString();
                        int qty = Integer.parseInt(daftar_produk.getValueAt(i, 3).toString());
                        int total_hrg = Integer.parseInt(daftar_produk.getValueAt(i, 5).toString());
//                        int stok = Integer.parseInt(daftar_produk.getValueAt(i, 6).toString());

                        // Ambil stok                        
                        com.mysql.jdbc.Connection c1 = (com.mysql.jdbc.Connection) Koneksi.configDB();
                        Statement stat1 = c1.createStatement();
                        String sql_ambil_stok = "SELECT jml_stok from barang where id_barang ='" + id_barang + "'";
                        ResultSet rs1 = stat1.executeQuery(sql_ambil_stok);
                        while (rs1.next()) {
                            String jml_stok = rs1.getString("jml_stok");

                            update_stok.setText(jml_stok);
                        }

                        int akhir_stok = Integer.parseInt(update_stok.getText()) + qty;

                        String sql_detail_transaksi = "insert into detail_pemasokan (id_pemasokan,id_barang,qty,total_hrg) values('" + txt_id_pemasokan.getText() + "','" + id_barang + "','" + qty + "','" + total_hrg + "')";
                        java.sql.PreparedStatement pst2 = conn.prepareStatement(sql_detail_transaksi);
                        pst2.execute();

                        String sql_update_stok = "UPDATE barang SET jml_stok = '" + akhir_stok + "' WHERE id_barang = '" + id_barang + "'";
                        java.sql.PreparedStatement pst3 = conn.prepareStatement(sql_update_stok);
                        pst3.execute();

                    }
                    JOptionPane.showMessageDialog(null, "Transaksi Berhasil!");
                    hidden();
                    tgl_sekarang();
                    nonaktif();
                    kode();
                    kosongkan();
                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (ParseException ex) {
                Logger.getLogger(DashboardGudang.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txt_bayarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardGudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardGudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardGudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardGudang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardGudang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel akses;
    public static javax.swing.JTextField barcode;
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cariBarang;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox<String> cb_distributor;
    private javax.swing.JTable daftar_produk;
    private javax.swing.JLabel id_user;
    private javax.swing.JLabel id_users;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nama;
    private javax.swing.JTextField txt_baris;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_hrg_distributor;
    private javax.swing.JLabel txt_id_distributor;
    private javax.swing.JLabel txt_id_pemasokan;
    private javax.swing.JTextField txt_jml_stok;
    private javax.swing.JTextField txt_kembalian;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JTextField txt_satuan;
    private javax.swing.JLabel txt_tanggal;
    private javax.swing.JTextField txt_total;
    private javax.swing.JTextField update_stok;
    // End of variables declaration//GEN-END:variables
}
