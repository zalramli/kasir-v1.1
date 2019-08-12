/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;

import Koneksi.Koneksi;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.sql.SQLException;
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
 * @author zakka
 */
public class DataStok extends javax.swing.JInternalFrame {

    /**
     * Creates new form DataStok
     */
    public DataStok() {
        initComponents();
        removeDecoration();
        tampil_data();
        custom_tabel();
    }

    private void custom_tabel() {
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

    // untuk menghilangkan dekorasi pada frame
    void removeDecoration() {
        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) this.getUI()).getNorthPane();
        this.remove(titlePane);
    }

    // memanggil data dan membuat table
    private void tampil_data() {
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Kode", "Nama", "Kategori", "Stok", "Satuan", "Harga Jual", "Harga Distributor"
                }) // BIAR FIELD TABEL TIDAK BISA EDIT
        {
            boolean[] tdk_bisa_edit = new boolean[]{
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int row, int column) {
                return tdk_bisa_edit[column];
            }
        };
        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String sql = "SELECT * FROM barang b ,kategori k , satuan s where b.id_kategori = k.id_kategori && b.id_satuan = s.id_satuan && b.jml_stok > 0 ORDER BY nm_barang ASC";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {

                int hrg_eceran = Integer.parseInt(res.getString("hrg_jual"));
                double angka = (double) hrg_eceran;
                String harga_eceran = String.format("%,.0f", angka).replaceAll(",", ".");

                int hrg_beli = Integer.parseInt(res.getString("hrg_beli"));
                double angka3 = (double) hrg_beli;
                String harga_beli = String.format("%,.0f", angka3).replaceAll(",", ".");

                // menambahkan baris data kedalam table
                model.addRow(new Object[]{res.getString(1), res.getString(4), res.getString("k.nm_kategori"), res.getString(5), res.getString("s.nm_satuan"),
                    harga_eceran, harga_beli});
            }
            jTable1.setModel(model);
            DefaultTableCellRenderer right = new DefaultTableCellRenderer();
            right.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(5).setCellRenderer(right);
            jTable1.getColumnModel().getColumn(6).setCellRenderer(right);
        } catch (SQLException e) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Gagal Menampilkan Data");
        }
    }

    // proses pencarian
    public void pencarian() {

        // membuat table
        DefaultTableModel model = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Kode", "Nama", "Kategori", "Stok", "Satuan", "Harga Jual", "Harga Distributor"
                }) // BIAR FIELD TABEL TIDAK BISA EDIT
        {
            boolean[] tdk_bisa_edit = new boolean[]{
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int row, int column) {
                return tdk_bisa_edit[column];
            }
        };
        try {
            String cari = txt_cari.getText();
            String sql = "SELECT * FROM barang b JOIN kategori k ON b.id_kategori=k.id_kategori JOIN satuan s ON b.id_satuan = s.id_satuan "
                    + "WHERE b.id_barang LIKE '%" + cari + "%' OR b.nm_barang LIKE '%" + cari + "%' OR k.nm_kategori LIKE '%" + cari + "%' OR b.hrg_jual LIKE '%" + cari
                    + "%' OR b.jml_stok LIKE '%" + cari + "%' OR s.nm_satuan LIKE '%" + cari + "%' OR b.hrg_beli LIKE '%" + cari + "%' ORDER BY b.nm_barang ASC";
            java.sql.Connection conn = (java.sql.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                int hrg_eceran = Integer.parseInt(res.getString("hrg_jual"));
                double angka = (double) hrg_eceran;
                String harga_eceran = String.format("%,.0f", angka).replaceAll(",", ".");

                int hrg_beli = Integer.parseInt(res.getString("hrg_beli"));
                double angka3 = (double) hrg_beli;
                String harga_beli = String.format("%,.0f", angka3).replaceAll(",", ".");

                // menambahkan baris data ke dalam tabel
                model.addRow(new Object[]{res.getString(1), res.getString(4), res.getString("k.nm_kategori"), res.getString(5), res.getString("s.nm_satuan"),
                    harga_eceran, harga_beli});
            }
            jTable1.setModel(model);
            DefaultTableCellRenderer right = new DefaultTableCellRenderer();
            right.setHorizontalAlignment(JLabel.RIGHT);
            jTable1.getColumnModel().getColumn(5).setCellRenderer(right);
            jTable1.getColumnModel().getColumn(6).setCellRenderer(right);
            txt_cari.setText(null);
            custom_tabel();
        } catch (Exception ex) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

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
        btn_cari = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1366, 670));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 40, 1310, 560));

        btn_cari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 10, -1, -1));

        txt_cari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 10, 170, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("STOK BARANG YANG TERSEDIA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        pencarian();
    }//GEN-LAST:event_btn_cariActionPerformed

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
        pencarian();
    }//GEN-LAST:event_txt_cariActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_cari;
    // End of variables declaration//GEN-END:variables
}
