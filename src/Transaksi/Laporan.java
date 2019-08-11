/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Koneksi.Koneksi;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author ZAKKAA
 */
public class Laporan extends javax.swing.JInternalFrame {

    /**
     * Creates new form Laporan
     */
    
    public Laporan() {
        initComponents();
        removeDecoration();
        tampil_data();
        align_tabel();
        custom_tabel();

    }
    
    private void custom_tabel()
    {
        
        //ngatur font
        tbl_laporan.setFont(new Font("Tahoma", Font.PLAIN, 14));
        //ngatur jarak tinggi
        tbl_laporan.setRowHeight(50);
        //ngatur header
        JTableHeader Theader = tbl_laporan.getTableHeader();
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
    
    private void align_tabel()
    {
        DefaultTableCellRenderer right = new DefaultTableCellRenderer();
        right.setHorizontalAlignment(JLabel.RIGHT);
        tbl_laporan.getColumnModel().getColumn(3).setCellRenderer(right);
        tbl_laporan.getColumnModel().getColumn(4).setCellRenderer(right);
        tbl_laporan.getColumnModel().getColumn(5).setCellRenderer(right);
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbl_laporan.getColumnModel().getColumn(0).setCellRenderer(center);
        tbl_laporan.getColumnModel().getColumn(1).setCellRenderer(center);
        tbl_laporan.getColumnModel().getColumn(2).setCellRenderer(center);
    }

    private void tampil_hari_ini() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode");
        model.addColumn("NAMA KASIR");
        model.addColumn("TANGGAL TRANSAKSI");
        model.addColumn("TOTAL HARGA");
        model.addColumn("TOTAL BAYAR");
        model.addColumn("KEMBALIAN");
        JTableHeader Theader = tbl_laporan.getTableHeader();
        Theader.setFont(new Font("Tahoma", Font.BOLD, 14));
        ((DefaultTableCellRenderer) Theader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);
        
        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            Date skrg = new Date();
            SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
            String hari_ini = formats.format(skrg);
            String sql = "SELECT * FROM transaksi JOIN user USING(id_user) WHERE tgl_transaksi='"+hari_ini+"'";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                int harga = Integer.parseInt(res.getString("total_hrg"));
                double angka = (double) harga;
                String total_harga = String.format("%,.0f", angka).replaceAll(",", ".");

                int bayar = Integer.parseInt(res.getString("bayar"));
                double angka2 = (double) bayar;
                String total_bayar = String.format("%,.0f", angka2).replaceAll(",", ".");

                int kembalian = Integer.parseInt(res.getString("kembalian"));
                double angka3 = (double) kembalian;
                String total_kembalian = String.format("%,.0f", angka3).replaceAll(",", ".");

                // MENGUBAH FORMAT TANGGAL INDONESIA
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date ys = null;
                try {
                    ys = format.parse(res.getString("tgl_transaksi"));
                } catch (ParseException ex) {
                    Logger.getLogger(Laporan.class.getName()).log(Level.SEVERE, null, ex);
                }
                SimpleDateFormat s = new SimpleDateFormat("dd MMMM yyyy");
                String tanggal = s.format(ys);
                
                model.addRow(new Object[]{res.getString("id_transaksi"),
                    res.getString("nm_user"),
                    tanggal,
                    total_harga,
                    total_bayar,
                    total_kembalian});
            }
            tbl_laporan.setModel(model);
        } catch (SQLException e) {
        }
    }
    
    private void tampil_minggu_ini() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode");
        model.addColumn("NAMA KASIR");
        model.addColumn("TANGGAL TRANSAKSI");
        model.addColumn("TOTAL HARGA");
        model.addColumn("TOTAL BAYAR");
        model.addColumn("KEMBALIAN");
        JTableHeader Theader = tbl_laporan.getTableHeader();
        Theader.setFont(new Font("Tahoma", Font.BOLD, 14));
        ((DefaultTableCellRenderer) Theader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            Date skrg = new Date();
            SimpleDateFormat formats = new SimpleDateFormat("yyyy-MM-dd");
            String hari_ini = formats.format(skrg);
            
            // Mendapatkan tanggal 7 hari sebelumnya
            String sql2 = "SELECT DATE_SUB(CURDATE(), INTERVAL 7 DAY) as minggu_ini";
            java.sql.Connection conn2 = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm2 = conn2.createStatement();
            java.sql.ResultSet res2 = stm2.executeQuery(sql2);
            res2.next();
            String tujuh_hari_sebelumnya = res2.getString("minggu_ini");
            
            
            String sql = "SELECT * FROM transaksi JOIN user USING(id_user) WHERE tgl_transaksi BETWEEN '" + tujuh_hari_sebelumnya + "' AND '" +hari_ini+ "'";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                int harga = Integer.parseInt(res.getString("total_hrg"));
                double angka = (double) harga;
                String total_harga = String.format("%,.0f", angka).replaceAll(",", ".");

                int bayar = Integer.parseInt(res.getString("bayar"));
                double angka2 = (double) bayar;
                String total_bayar = String.format("%,.0f", angka2).replaceAll(",", ".");

                int kembalian = Integer.parseInt(res.getString("kembalian"));
                double angka3 = (double) kembalian;
                String total_kembalian = String.format("%,.0f", angka3).replaceAll(",", ".");

                // MENGUBAH FORMAT TANGGAL INDONESIA
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date ys = null;
                try {
                    ys = format.parse(res.getString("tgl_transaksi"));
                } catch (ParseException ex) {
                    Logger.getLogger(Laporan.class.getName()).log(Level.SEVERE, null, ex);
                }
                SimpleDateFormat s = new SimpleDateFormat("dd MMMM yyyy");
                String tanggal = s.format(ys);
                
                model.addRow(new Object[]{res.getString("id_transaksi"),
                    res.getString("nm_user"),
                    tanggal,
                    total_harga,
                    total_bayar,
                    total_kembalian});
            }
            tbl_laporan.setModel(model);
        } catch (SQLException e) {
        }
    }
    
    private void tampil_bulan_ini() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode");
        model.addColumn("NAMA KASIR");
        model.addColumn("TANGGAL TRANSAKSI");
        model.addColumn("TOTAL HARGA");
        model.addColumn("TOTAL BAYAR");
        model.addColumn("KEMBALIAN");
        JTableHeader Theader = tbl_laporan.getTableHeader();
        Theader.setFont(new Font("Tahoma", Font.BOLD, 14));
        ((DefaultTableCellRenderer) Theader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            Date skrg = new Date();
            SimpleDateFormat formats = new SimpleDateFormat("MM");
            String bulan_ini = formats.format(skrg);
            String sql = "SELECT * FROM transaksi JOIN user USING(id_user) WHERE MONTH(tgl_transaksi)='"+bulan_ini+"'";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                int harga = Integer.parseInt(res.getString("total_hrg"));
                double angka = (double) harga;
                String total_harga = String.format("%,.0f", angka).replaceAll(",", ".");

                int bayar = Integer.parseInt(res.getString("bayar"));
                double angka2 = (double) bayar;
                String total_bayar = String.format("%,.0f", angka2).replaceAll(",", ".");

                int kembalian = Integer.parseInt(res.getString("kembalian"));
                double angka3 = (double) kembalian;
                String total_kembalian = String.format("%,.0f", angka3).replaceAll(",", ".");

                // MENGUBAH FORMAT TANGGAL INDONESIA
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date ys = null;
                try {
                    ys = format.parse(res.getString("tgl_transaksi"));
                } catch (ParseException ex) {
                    Logger.getLogger(Laporan.class.getName()).log(Level.SEVERE, null, ex);
                }
                SimpleDateFormat s = new SimpleDateFormat("dd MMMM yyyy");
                String tanggal = s.format(ys);
                
                model.addRow(new Object[]{res.getString("id_transaksi"),
                    res.getString("nm_user"),
                    tanggal,
                    total_harga,
                    total_bayar,
                    total_kembalian});
            }
            tbl_laporan.setModel(model);
        } catch (SQLException e) {
        }
    }
    

    private void tampil_data() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode");
        model.addColumn("NAMA KASIR");
        model.addColumn("TANGGAL TRANSAKSI");
        model.addColumn("TOTAL HARGA");
        model.addColumn("TOTAL BAYAR");
        model.addColumn("KEMBALIAN");
        JTableHeader Theader = tbl_laporan.getTableHeader();
        Theader.setFont(new Font("Tahoma", Font.BOLD, 14));
        ((DefaultTableCellRenderer) Theader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String sql = "SELECT * FROM transaksi JOIN user USING(id_user)";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                int harga = Integer.parseInt(res.getString("total_hrg"));
                double angka = (double) harga;
                String total_harga = String.format("%,.0f", angka).replaceAll(",", ".");

                int bayar = Integer.parseInt(res.getString("bayar"));
                double angka2 = (double) bayar;
                String total_bayar = String.format("%,.0f", angka2).replaceAll(",", ".");

                int kembalian = Integer.parseInt(res.getString("kembalian"));
                double angka3 = (double) kembalian;
                String total_kembalian = String.format("%,.0f", angka3).replaceAll(",", ".");

                // MENGUBAH FORMAT TANGGAL INDONESIA
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date ys = null;
                try {
                    ys = format.parse(res.getString("tgl_transaksi"));
                } catch (ParseException ex) {
                    Logger.getLogger(Laporan.class.getName()).log(Level.SEVERE, null, ex);
                }
                SimpleDateFormat s = new SimpleDateFormat("dd MMMM yyyy");
                String tanggal = s.format(ys);
                
                model.addRow(new Object[]{res.getString("id_transaksi"),
                    res.getString("nm_user"),
                    tanggal,
                    total_harga,
                    total_bayar,
                    total_kembalian});
            }
            tbl_laporan.setModel(model);
        } catch (SQLException e) {
        }
    }

    private void tampil_data_filter() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode");
        model.addColumn("NAMA KASIR");
        model.addColumn("TANGGAL TRANSAKSI");
        model.addColumn("TOTAL HARGA");
        model.addColumn("TOTAL BAYAR");
        model.addColumn("KEMBALIAN");
        JTableHeader Theader = tbl_laporan.getTableHeader();
        Theader.setFont(new Font("Tahoma", Font.BOLD, 14));
        ((DefaultTableCellRenderer) Theader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        //menampilkan data database kedalam tabel
        try {
            //int no=1;

            String awals = ((JTextField) awal.getDateEditor().getUiComponent()).getText();
            String akhirs = ((JTextField) akhir.getDateEditor().getUiComponent()).getText();
            


            DateFormat tgl_awal = new SimpleDateFormat("dd MMMM yyyy");
            DateFormat tgl_akhir = new SimpleDateFormat("yyyy-MM-dd");  
            Date today = null;
            Date todays = null;
                        try {
                            today = tgl_awal.parse(awals);
                        } catch (ParseException ex) {
                            Logger.getLogger(Laporan.class.getName()).log(Level.SEVERE, null, ex);
                        }
            String awalss = tgl_akhir.format(today);

                        try {
                            todays = tgl_awal.parse(akhirs);
                        } catch (ParseException ex) {
                            Logger.getLogger(Laporan.class.getName()).log(Level.SEVERE, null, ex);
                        }
            String akhirss = tgl_akhir.format(todays);

            String sql = "SELECT * FROM transaksi JOIN user USING(id_user) WHERE tgl_transaksi BETWEEN '" + awalss + "' AND '" + akhirss + "' ";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                int harga = Integer.parseInt(res.getString("total_hrg"));
                double angka = (double) harga;
                String total_harga = String.format("%,.0f", angka).replaceAll(",", ".");

                int bayar = Integer.parseInt(res.getString("bayar"));
                double angka2 = (double) bayar;
                String total_bayar = String.format("%,.0f", angka2).replaceAll(",", ".");

                int kembalian = Integer.parseInt(res.getString("kembalian"));
                double angka3 = (double) kembalian;
                String total_kembalian = String.format("%,.0f", angka3).replaceAll(",", ".");

                // MENGUBAH FORMAT TANGGAL INDONESIA
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date ys = null;
                try {
                    ys = format.parse(res.getString("tgl_transaksi"));
                } catch (ParseException ex) {
                    Logger.getLogger(Laporan.class.getName()).log(Level.SEVERE, null, ex);
                }
                SimpleDateFormat s = new SimpleDateFormat("dd MMMM yyyy");
                String tanggal = s.format(ys);
                
                model.addRow(new Object[]{res.getString("id_transaksi"),
                    res.getString("nm_user"),
                    tanggal,
                    total_harga,
                    total_bayar,
                    total_kembalian});
            }
            tbl_laporan.setModel(model);
        } catch (SQLException e) {
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
        tbl_laporan = new javax.swing.JTable();
        btn_tampil = new javax.swing.JButton();
        akhir = new com.toedter.calendar.JDateChooser();
        awal = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        btn_refresh = new javax.swing.JButton();
        cb_pilihan = new javax.swing.JComboBox<>();
        btn_print = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1366, 670));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_laporan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_laporan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 1270, 480));

        btn_tampil.setText("TAMPIL");
        btn_tampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tampilActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 92, 32));

        akhir.setDateFormatString("dd MMMM yyyy");
        getContentPane().add(akhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 170, 30));

        awal.setDateFormatString("dd MMMM yyyy");
        getContentPane().add(awal, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 160, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("SAMPAI");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, -1, -1));

        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });
        getContentPane().add(btn_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 40, 92, 32));

        cb_pilihan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Filter", "Hari Ini", "Minggu Ini", "Bulan Ini" }));
        cb_pilihan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_pilihanActionPerformed(evt);
            }
        });
        getContentPane().add(cb_pilihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 40, 90, 30));

        btn_print.setText("PRINT");
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        getContentPane().add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, 90, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tampilActionPerformed
        // TODO add your handling code here:
        if (((JTextField) awal.getDateEditor().getUiComponent()).getText().equals("")
                || ((JTextField) akhir.getDateEditor().getUiComponent()).getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Masukkan tanggal dengan benar !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
             tampil_data_filter();
             align_tabel();

        }
    }//GEN-LAST:event_btn_tampilActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        // TODO add your handling code here:
        tampil_data();
        align_tabel();
        awal.setDate(null);
        akhir.setDate(null);


    }//GEN-LAST:event_btn_refreshActionPerformed

    private void cb_pilihanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_pilihanActionPerformed
        // TODO add your handling code here:
        String tampung = cb_pilihan.getSelectedItem().toString();
        if (tampung == "Pilih Filter") {
            tampil_data();
            align_tabel();
        }
        else if(tampung == "Hari Ini")
        {
            tampil_hari_ini();
            align_tabel();
            
        }
        else if(tampung == "Minggu Ini")
        {
            tampil_minggu_ini();
            align_tabel();
        }
        else if(tampung == "Bulan Ini")
        {
            tampil_bulan_ini();
            align_tabel();
        }
    }//GEN-LAST:event_cb_pilihanActionPerformed

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel de = (DefaultTableModel) tbl_laporan.getModel();
            JRTableModelDataSource datasource = new JRTableModelDataSource(de);
            String reportSource = "./laporan.jrxml";

            JasperReport jr = JasperCompileManager.compileReport(reportSource);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("title1", "title 1");

            JasperPrint jp = JasperFillManager.fillReport(jr, params, datasource);


            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_printActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser akhir;
    private com.toedter.calendar.JDateChooser awal;
    private javax.swing.JButton btn_print;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_tampil;
    private javax.swing.JComboBox<String> cb_pilihan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_laporan;
    // End of variables declaration//GEN-END:variables
}
