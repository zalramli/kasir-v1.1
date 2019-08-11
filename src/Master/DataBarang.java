/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;

import Koneksi.Koneksi;
import com.mysql.jdbc.Connection;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

/**
 *
 * @author ZAKKAA
 */
public class DataBarang extends javax.swing.JInternalFrame {

    Kategori var_kategori;

    /**
     * Creates new form Barang
     */
    public DataBarang() {
        initComponents();
        removeDecoration();
        tampil_data();
        button_awal();
        ambil_kategori();
        ambil_satuan();
        txt_baris.setVisible(false);
        custom_tabel();
        
    }
    
    private void custom_tabel()
    {
        //ngatur widht coloumn nama barang
//        TableColumn col1 = jTable1.getColumnModel().getColumn(1);
//        col1.setMinWidth(450);
//        col1.setMaxWidth(450);
//        col1.setWidth(450);
//        col1.setPreferredWidth(450);
        //ngatur font
        jTable1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        //ngatur jarak tinggi
        jTable1.setRowHeight(50);
        //ngatur header
        JTableHeader Theader = jTable1.getTableHeader();
        Theader.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ((DefaultTableCellRenderer) Theader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
    }

    void removeDecoration() {
        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) this.getUI()).getNorthPane();
        this.remove(titlePane);
    }

    public void button_awal() {
        btn_simpan.setEnabled(true);
        btn_batal.setEnabled(true);
        btn_update.setEnabled(false);
        btn_hapus.setEnabled(false);

    }

    public void button_tabelklik() {
        btn_simpan.setEnabled(false);
        btn_batal.setEnabled(true);
        btn_update.setEnabled(true);
        btn_hapus.setEnabled(true);

    }

    private void reset_input() {
        txt_kode.setText(null);
        txt_nama.setText(null);
        cb_kategori.setSelectedIndex(0);
        txt_stok.setText(null);
        cb_satuan.setSelectedIndex(0);
        txt_hrg_jual.setText(null);
        txt_hrg_grosir.setText(null);
        txt_hrg_beli.setText(null);
        txt_cari.setText(null);
        txt_baris.setText(null);
    }

    private void tampil_data() {
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        //ngatur nama barang
        
        model.addColumn("KODE");
        model.addColumn("NAMA");
        model.addColumn("KATEGORI");
        model.addColumn("STOK");
        model.addColumn("JENIS SATUAN");
        model.addColumn("HARGA JUAL");
        model.addColumn("HARGA GROSIR");
        model.addColumn("HARGA DISTRIBUTOR");
        

        
        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String sql = "SELECT * FROM barang b ,kategori k , satuan s where b.id_kategori = k.id_kategori && b.id_satuan = s.id_satuan ORDER BY nm_barang ASC";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                
                int hrg_eceran = Integer.parseInt(res.getString("hrg_jual"));
                int hrg_grosir = Integer.parseInt(res.getString("hrg_grosir"));
                int hrg_beli = Integer.parseInt(res.getString("hrg_beli"));
                double angka = (double) hrg_eceran;
                String harga_eceran = String.format("%,.0f", angka).replaceAll(",", ".");
                double angka2 = (double) hrg_grosir;
                String harga_grosir = String.format("%,.0f", angka2).replaceAll(",", ".");
                double angka3 = (double) hrg_beli;
                String harga_beli = String.format("%,.0f", angka3).replaceAll(",", ".");

                model.addRow(new Object[]{res.getString(1), res.getString(4), res.getString("k.nm_kategori"), res.getString(5), res.getString("s.nm_satuan"), 
                    harga_eceran,harga_grosir,harga_beli});
            }
            jTable1.setModel(model);
            DefaultTableCellRenderer right = new DefaultTableCellRenderer();
            right.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(5).setCellRenderer(right);
            jTable1.getColumnModel().getColumn(6).setCellRenderer(right);        
            jTable1.getColumnModel().getColumn(7).setCellRenderer(right);
        } catch (SQLException e) {
        }
    }

    private void ambil_kategori() {
        try {
            cb_kategori.addItem("Pilih Kategori");
            txt_id_kategori.setText(null);
            //int no=1;
            String sql = "SELECT * FROM kategori ORDER BY nm_kategori ASC";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                String nama = res.getString("nm_kategori");
                cb_kategori.addItem(nama);
            }
        } catch (SQLException e) {
        }
    }

    private void ambil_satuan() {
        try {
            cb_satuan.addItem("Pilih Satuan");
            txt_id_satuan.setText(null);
            //int no=1;
            String sql = "SELECT * FROM satuan ORDER BY nm_satuan ASC";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                String nama = res.getString("nm_satuan");
                cb_satuan.addItem(nama);
            }
        } catch (SQLException e) {
        }
    }

    private void setTextData() {
        int baris = Integer.parseInt(txt_baris.getText());

        String kode = jTable1.getValueAt(baris, 0).toString();
        txt_kode.setText(kode);
        String nama = jTable1.getValueAt(baris, 1).toString();
        txt_nama.setText(nama);
        String kategori = jTable1.getValueAt(baris, 2).toString();
        cb_kategori.setSelectedItem(kategori);
        String stok = jTable1.getValueAt(baris, 3).toString();
        txt_stok.setText(stok);
        String satuan = jTable1.getValueAt(baris, 4).toString();
        cb_satuan.setSelectedItem(satuan);
        String hrg_jual = jTable1.getValueAt(baris, 5).toString();
        String hrg_grosir = jTable1.getValueAt(baris, 6).toString(); 
        String hrg_beli = jTable1.getValueAt(baris, 7).toString();
        
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("");
        formatRp.setMonetaryDecimalSeparator(' ');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        try {
            
        Number number = kursIndonesia.parse(hrg_jual);
        double nilai = number.doubleValue();
        int nilai2 = (int) nilai;
        String harga_jual = String.valueOf(nilai2);
        txt_hrg_jual.setText(harga_jual);
        
        Number number2 = kursIndonesia.parse(hrg_grosir);
        double nilai3 = number2.doubleValue();
        int nilai4 = (int) nilai3;
        String harga_grosir = String.valueOf(nilai4);
        txt_hrg_grosir.setText(harga_grosir);
        
        Number number3 = kursIndonesia.parse(hrg_beli);
        double nilai5 = number3.doubleValue();
        int nilai6 = (int) nilai5;
        String harga_beli = String.valueOf(nilai6);
        txt_hrg_beli.setText(harga_beli);
        }
        catch (ParseException ex) {
                System.out.println("Kesalahan Parsing");
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_kode = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        txt_stok = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_hrg_jual = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_hrg_beli = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cb_kategori = new javax.swing.JComboBox<>();
        txt_id_kategori = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cb_satuan = new javax.swing.JComboBox<>();
        txt_id_satuan = new javax.swing.JLabel();
        txt_baris = new javax.swing.JTextField();
        txt_hrg_grosir = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1366, 670));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "KODE BARANG", "NAMA", "KATEGORI", "STOK", "JENIS SATUAN", "HARGA JUAL", "HARGA DISTRIBUTOR"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 960, 570));

        txt_kode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(txt_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 40, 177, -1));

        txt_nama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 70, 177, -1));

        txt_stok.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_stok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_stokKeyTyped(evt);
            }
        });
        getContentPane().add(txt_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 130, 177, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Kode");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nama ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 70, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Stok");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 130, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Harga Eceran");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 190, -1, -1));

        txt_hrg_jual.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(txt_hrg_jual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 190, 177, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Harga Beli");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 250, 71, -1));

        txt_hrg_beli.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(txt_hrg_beli, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 250, 177, -1));

        btn_simpan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 290, -1, -1));

        btn_update.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_update.setText("UPDATE");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 290, -1, -1));

        btn_hapus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_hapus.setText("HAPUS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 290, -1, -1));

        btn_batal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_batal.setText("BATAL");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });
        getContentPane().add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 290, -1, -1));

        txt_cari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 10, 100, -1));

        btn_cari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 60, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Kategori");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 100, 71, -1));

        cb_kategori.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cb_kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_kategoriActionPerformed(evt);
            }
        });
        getContentPane().add(cb_kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 100, 177, -1));

        txt_id_kategori.setText("id_kate");
        getContentPane().add(txt_id_kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 100, 10, 23));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Jenis Satuan");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 160, -1, -1));

        cb_satuan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cb_satuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_satuanActionPerformed(evt);
            }
        });
        getContentPane().add(cb_satuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 160, 177, -1));

        txt_id_satuan.setText("id_sat");
        getContentPane().add(txt_id_satuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 170, 10, -1));

        txt_baris.setEditable(false);
        getContentPane().add(txt_baris, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 35, -1));

        txt_hrg_grosir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(txt_hrg_grosir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 220, 177, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Harga Grosir");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 220, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String kode_barang = txt_kode.getText();
        try {
            // TODO add your handling code here:
            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) Koneksi.configDB();
            Statement stat = c.createStatement();
            String sql2 = "SELECT * FROM barang WHERE id_barang='" + kode_barang + "'";
            ResultSet rs = stat.executeQuery(sql2);
            if (txt_kode.getText().equals("") || txt_nama.getText().equals("") || cb_kategori.getSelectedItem() == "Pilih Kategori" || txt_stok.getText().equals("") || cb_satuan.getSelectedItem() == "Pilih Satuan" || txt_hrg_jual.getText().equals("") || txt_hrg_grosir.getText().equals("") || txt_hrg_beli.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Masukkan data dengan benar !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (rs.next() == true) {
                JOptionPane.showMessageDialog(null, "Data Barang Sudah ada !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                try {
                    String sql = "INSERT INTO barang VALUES ('" + kode_barang + "','" + txt_id_kategori.getText() + "','" + txt_id_satuan.getText() + "','" + txt_nama.getText() + "','" + txt_stok.getText() + "','" + txt_hrg_jual.getText() + "','" + txt_hrg_grosir.getText() + "','" + txt_hrg_beli.getText() + "')";
                    java.sql.Connection conn = (Connection) Koneksi.configDB();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
            tampil_data();
            reset_input();
            button_awal();
            txt_kode.setEditable(true);
            custom_tabel();
        } catch (SQLException ex) {
            Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        button_tabelklik();
        txt_kode.setEditable(false);
        int baris = jTable1.rowAtPoint(evt.getPoint());
        txt_baris.setText(String.valueOf(baris));
        setTextData();

    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        tampil_data();
        reset_input();
        button_awal();
        txt_kode.setEditable(true);
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        if (txt_kode.getText().equals("") || txt_nama.getText().equals("") || cb_kategori.getSelectedItem() == "Pilih Kategori" || txt_stok.getText().equals("") || cb_satuan.getSelectedItem() == "Pilih Satuan" || txt_hrg_jual.getText().equals("") || txt_hrg_grosir.getText().equals("") || txt_hrg_beli.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Masukkan data dengan benar !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String sql = "UPDATE barang SET id_kategori = '" + txt_id_kategori.getText() + "', id_satuan = '" + txt_id_satuan.getText() + "', nm_barang = '" + txt_nama.getText() + "',jml_stok = '" + txt_stok.getText() + "',hrg_jual = '" + txt_hrg_jual.getText() + "',hrg_grosir = '" + txt_hrg_grosir.getText() + "',hrg_beli = '" + txt_hrg_beli.getText() + "' WHERE id_barang = '" + txt_kode.getText() + "'";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di update");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal" + e.getMessage());
        }
        tampil_data();
        reset_input();
        button_awal();
        txt_kode.setEditable(true);
        custom_tabel();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        int konfirmasi = JOptionPane.showConfirmDialog(null, "Apakah yakin dihapus?", "Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == 0) {
            try {
                String sql = "DELETE FROM barang WHERE id_barang='" + txt_kode.getText() + "'";
                java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(this, "Berhasil di hapus");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            tampil_data();
            reset_input();
            button_awal();
            txt_kode.setEditable(true);
            custom_tabel();
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("KODE");
            model.addColumn("NAMA");
            model.addColumn("KATEGORI");
            model.addColumn("STOK");
            model.addColumn("JENIS SATUAN");
            model.addColumn("HARGA JUAL");
            model.addColumn("HARGA GROSIR");
            model.addColumn("HARGA DISTRIBUTOR");

            String cari = txt_cari.getText();
            String sql = "SELECT * FROM barang b JOIN kategori k ON b.id_kategori=k.id_kategori JOIN satuan s ON b.id_satuan = s.id_satuan "
                    + "WHERE b.id_barang LIKE '%" + cari + "%' OR b.nm_barang LIKE '%" + cari + "%' OR k.nm_kategori LIKE '%" + cari + "%' OR b.hrg_jual LIKE '%" + cari
                    + "%' OR b.jml_stok LIKE '%" + cari + "%' OR s.nm_satuan LIKE '%" + cari + "%' OR b.hrg_beli LIKE '%" + cari + "%' ORDER BY b.nm_barang ASC";
            java.sql.Connection conn = (java.sql.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                int hrg_eceran = Integer.parseInt(res.getString("hrg_jual"));
                int hrg_grosir = Integer.parseInt(res.getString("hrg_grosir"));
                int hrg_beli = Integer.parseInt(res.getString("hrg_beli"));
                double angka = (double) hrg_eceran;
                String harga_eceran = String.format("%,.0f", angka).replaceAll(",", ".");
                double angka2 = (double) hrg_grosir;
                String harga_grosir = String.format("%,.0f", angka2).replaceAll(",", ".");
                double angka3 = (double) hrg_beli;
                String harga_beli = String.format("%,.0f", angka3).replaceAll(",", ".");
                model.addRow(new Object[]{res.getString(1), res.getString(4), res.getString("k.nm_kategori"), res.getString(5), res.getString("s.nm_satuan"), 
                    harga_eceran,harga_grosir,harga_beli});
            }
            jTable1.setModel(model);
            DefaultTableCellRenderer right = new DefaultTableCellRenderer();
            right.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(5).setCellRenderer(right);
            jTable1.getColumnModel().getColumn(6).setCellRenderer(right);        
            jTable1.getColumnModel().getColumn(7).setCellRenderer(right);
            txt_cari.setText(null);
            custom_tabel();
        } catch (Exception ex) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void txt_stokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_stokKeyTyped
        // TODO add your handling code here:
        char Test = evt.getKeyChar();
        if (!(Character.isDigit(Test))) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_stokKeyTyped

    private void cb_kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_kategoriActionPerformed
        // TODO add your handling code here:
        String tampung = cb_kategori.getSelectedItem().toString();
        if (tampung == "Pilih Kategori") {
            txt_id_kategori.setText(null);
        } else {
            try {
                //int no=1;
                String sql = "SELECT * FROM kategori WHERE nm_kategori='" + tampung + "'";
                java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet res = stm.executeQuery(sql);
                while (res.next()) {
                    String id = res.getString("id_kategori");
                    txt_id_kategori.setText(id);
                }
            } catch (SQLException e) {
            }
        }

    }//GEN-LAST:event_cb_kategoriActionPerformed

    private void cb_satuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_satuanActionPerformed
        // TODO add your handling code here:
        String tampung = cb_satuan.getSelectedItem().toString();
        if (tampung == "Pilih Satuan") {
            txt_id_satuan.setText(null);
        } else {
            try {
                //int no=1;
                String sql = "SELECT * FROM satuan WHERE nm_satuan='" + tampung + "'";
                java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet res = stm.executeQuery(sql);
                while (res.next()) {
                    String id = res.getString("id_satuan");
                    txt_id_satuan.setText(id);
                }
            } catch (SQLException e) {
            }
        }

    }//GEN-LAST:event_cb_satuanActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        int row = this.jTable1.getSelectedRow();
        this.txt_baris.setText(String.valueOf(row));
        setTextData();
    }//GEN-LAST:event_jTable1KeyReleased

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("KODE");
            model.addColumn("NAMA");
            model.addColumn("KATEGORI");
            model.addColumn("STOK");
            model.addColumn("JENIS SATUAN");
            model.addColumn("HARGA JUAL");
            model.addColumn("HARGA GROSIR");
            model.addColumn("HARGA DISTRIBUTOR");

            String cari = txt_cari.getText();
            String sql = "SELECT * FROM barang b JOIN kategori k ON b.id_kategori=k.id_kategori JOIN satuan s ON b.id_satuan = s.id_satuan "
                    + "WHERE b.id_barang LIKE '%" + cari + "%' OR b.nm_barang LIKE '%" + cari + "%' OR k.nm_kategori LIKE '%" + cari + "%' OR b.hrg_jual LIKE '%" + cari
                    + "%' OR b.jml_stok LIKE '%" + cari + "%' OR s.nm_satuan LIKE '%" + cari + "%' OR b.hrg_beli LIKE '%" + cari + "%' ORDER BY b.nm_barang ASC";
            java.sql.Connection conn = (java.sql.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                int hrg_eceran = Integer.parseInt(res.getString("hrg_jual"));
                int hrg_grosir = Integer.parseInt(res.getString("hrg_grosir"));
                int hrg_beli = Integer.parseInt(res.getString("hrg_beli"));
                double angka = (double) hrg_eceran;
                String harga_eceran = String.format("%,.0f", angka).replaceAll(",", ".");
                double angka2 = (double) hrg_grosir;
                String harga_grosir = String.format("%,.0f", angka2).replaceAll(",", ".");
                double angka3 = (double) hrg_beli;
                String harga_beli = String.format("%,.0f", angka3).replaceAll(",", ".");
                model.addRow(new Object[]{res.getString(1), res.getString(4), res.getString("k.nm_kategori"), res.getString(5), res.getString("s.nm_satuan"), 
                    harga_eceran,harga_grosir,harga_beli});
            }
            jTable1.setModel(model);
            DefaultTableCellRenderer right = new DefaultTableCellRenderer();
            right.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(5).setCellRenderer(right);
            jTable1.getColumnModel().getColumn(6).setCellRenderer(right);        
            jTable1.getColumnModel().getColumn(7).setCellRenderer(right);
            txt_cari.setText(null);
            custom_tabel();
        } catch (Exception ex) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_txt_cariActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cb_kategori;
    private javax.swing.JComboBox<String> cb_satuan;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_baris;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_hrg_beli;
    private javax.swing.JTextField txt_hrg_grosir;
    private javax.swing.JTextField txt_hrg_jual;
    private javax.swing.JLabel txt_id_kategori;
    private javax.swing.JLabel txt_id_satuan;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_stok;
    // End of variables declaration//GEN-END:variables
}
