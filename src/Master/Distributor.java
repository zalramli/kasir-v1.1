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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author ZAKKAA
 */
public class Distributor extends javax.swing.JInternalFrame {

    /**
     * Creates new form NewJInternalFrame
     */
    public Distributor() {
        initComponents();
        removeDecoration();
        tampil_data();
        kode();
        button_awal();
        reset_input();
        custom_tabel();
        txt_baris.setVisible(false);
    }
    
    private void custom_tabel()
    {
        //ngatur font
        jTable1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        //ngatur jarak tinggi
        jTable1.setRowHeight(50);
        //ngatur header
        JTableHeader Theader = jTable1.getTableHeader();
        Theader.setFont(new Font("Tahoma", Font.PLAIN, 18));
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
    
    private void tampil_data(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode");
        model.addColumn("Nama");
        model.addColumn("No. Hp");
        model.addColumn("Alamat");
        
        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String sql = "SELECT * FROM distributor";
            java.sql.Connection conn=(com.mysql.jdbc.Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4)});
            }
            jTable1.setModel(model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Tampil Data Gagal"+e.getMessage());
        }

    }
    
    public void kode(){
        try {
            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) Koneksi.configDB();
            Statement stat = c.createStatement();
            String sql = "SELECT MAX(right(id_distributor,3)) AS no FROM distributor";
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                if (rs.first()== false)
                {
                    txt_kode.setText("D001");
                } else {
                    rs.last();  
                    int set_id = rs.getInt(1)+1;
                    String no = String.valueOf(set_id);
                    int id_next = no.length();
                    for(int a=0; a <3 - id_next; a++){
                        no = "0" +no;
                    }
                    txt_kode.setText("D"+no);
                }
            }
        } catch (SQLException ex) {
            
        }
    }
    
    public void button_awal()
    {
        btn_simpan.setEnabled(true);
        btn_batal.setEnabled(true);
        btn_update.setEnabled(false);
        btn_hapus.setEnabled(false);
    }
    
    private void reset_input(){
        txt_nama.setText(null);
        txt_no_hp.setText(null);
        txt_alamat.setText(null);
        txt_cari.setText(null);
    }
    
    public void button_tabelklik()
    {
        btn_simpan.setEnabled(false);
        btn_batal.setEnabled(true);
        btn_update.setEnabled(true);
        btn_hapus.setEnabled(true);
    }
    
    private void setTextData(){
        int baris = Integer.parseInt(txt_baris.getText());
        
        String id =jTable1.getValueAt(baris, 0).toString();
        txt_kode.setText(id);
        String nama = jTable1.getValueAt(baris,1).toString();
        txt_nama.setText(nama);
        String no_hp = jTable1.getValueAt(baris,2).toString();
        txt_no_hp.setText(no_hp);
        String alamat = jTable1.getValueAt(baris,3).toString();
        txt_alamat.setText(alamat);
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
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        txt_kode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_no_hp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_alamat = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        txt_baris = new javax.swing.JTextField();

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 930, 570));

        txt_cari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 10, 180, -1));

        btn_cari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 10, -1, -1));

        txt_kode.setEditable(false);
        txt_kode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(txt_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 40, 250, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Kode");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 40, -1, -1));

        txt_nama.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_nama.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_namaFocusGained(evt);
            }
        });
        txt_nama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_namaKeyTyped(evt);
            }
        });
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 70, 250, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nama");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 70, -1, -1));

        txt_no_hp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_no_hp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_no_hpFocusGained(evt);
            }
        });
        getContentPane().add(txt_no_hp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 100, 250, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("No. Hp");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 100, -1, -1));

        txt_alamat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_alamat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_alamatFocusGained(evt);
            }
        });
        getContentPane().add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 130, 250, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Alamat");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(980, 130, -1, -1));

        btn_simpan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 170, -1, -1));

        btn_update.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_update.setText("UPDATE");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 170, -1, -1));

        btn_hapus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_hapus.setText("HAPUS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 170, -1, -1));

        btn_batal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_batal.setText("BATAL");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });
        getContentPane().add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 170, -1, -1));

        txt_baris.setEditable(false);
        getContentPane().add(txt_baris, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 36, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        if (txt_kode.getText().equals("")
                || txt_nama.getText().equals("")
                || txt_no_hp.getText().equals("")
                || txt_alamat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Masukkan data dengan benar !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nama_distributor = txt_nama.getText();
        try {
            // TODO add your handling code here:
            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) Koneksi.configDB();
            Statement stat = c.createStatement();
            String sql2 = "SELECT * FROM distributor WHERE nm_distributor='"+nama_distributor+"'";
            ResultSet rs = stat.executeQuery(sql2);
            if(txt_nama.getText().equals("")||txt_no_hp.getText().equals("")||txt_alamat.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Masukkan data dengan benar !","Kesalahan", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else if(rs.next() == true)
            {
                 JOptionPane.showMessageDialog(null, "Data Distributor sudah ada !","Kesalahan", JOptionPane.ERROR_MESSAGE);
                return;       
            }
            else
            {
                try {
                    String sql = "INSERT INTO distributor VALUES ('"+txt_kode.getText()+"','"+nama_distributor+"','"+txt_no_hp.getText()+"','"+txt_alamat.getText()+"')";
                    java.sql.Connection conn=(Connection)Koneksi.configDB();
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
            tampil_data();
            kode();
            reset_input();
            button_awal();
            custom_tabel();
        } catch (SQLException ex) {
            Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        if (txt_kode.getText().equals("")
                || txt_nama.getText().equals("")
                || txt_no_hp.getText().equals("")
                || txt_alamat.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Masukkan data dengan benar !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String sql ="UPDATE distributor SET nm_distributor = '"+txt_nama.getText()+"',no_hp = '"+txt_no_hp.getText()+"',alamat = '"+txt_alamat.getText()+"' WHERE id_distributor = '"+txt_kode.getText()+"'";
            java.sql.Connection conn=(com.mysql.jdbc.Connection)Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di update");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
        tampil_data();
        kode();
        reset_input();
        button_awal();
        custom_tabel();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        int konfirmasi = JOptionPane.showConfirmDialog(null,"Apakah yakin dihapus?","Hapus",JOptionPane.YES_NO_OPTION);
        if(konfirmasi==0)
        {
        try {
                String sql ="DELETE FROM distributor WHERE id_distributor='"+txt_kode.getText()+"'";
                java.sql.Connection conn=(com.mysql.jdbc.Connection)Koneksi.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(this, "Berhasil di hapus");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        tampil_data();
        kode();
        reset_input();
        button_awal();
        custom_tabel();
        }
        else
        {
            tampil_data();
            kode();
            reset_input();
            button_awal();
            custom_tabel();
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        tampil_data();
        kode();
        reset_input();
        button_awal();
        custom_tabel();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void txt_namaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_namaKeyTyped
        // TODO add your handling code here:
        char Test = evt.getKeyChar();
        if(!(Character.isAlphabetic(Test)))
            evt.consume();
    }//GEN-LAST:event_txt_namaKeyTyped

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        try{
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kode");
            model.addColumn("Nama");
            model.addColumn("No. Hp");
            model.addColumn("Alamat");

            String cari = txt_cari.getText();
            String sql = "SELECT * FROM distributor WHERE id_distributor LIKE '%"+cari+"%' OR nm_distributor LIKE '%"+cari+"%' OR no_hp LIKE '%"+cari+"%' OR alamat LIKE '%"+cari+"%' ORDER BY id_distributor";
            java.sql.Connection conn=(java.sql.Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4)});
            }
            jTable1.setModel(model);
            txt_cari.setText(null);
            custom_tabel();
        }catch(Exception ex){
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Terjadi kesalahan !!!!");

        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void txt_namaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_namaFocusGained
        // TODO add your handling code here:
        txt_nama.setText("");
    }//GEN-LAST:event_txt_namaFocusGained

    private void txt_no_hpFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_no_hpFocusGained
        // TODO add your handling code here:
        txt_no_hp.setText("");
    }//GEN-LAST:event_txt_no_hpFocusGained

    private void txt_alamatFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_alamatFocusGained
        // TODO add your handling code here:
        txt_alamat.setText("");
    }//GEN-LAST:event_txt_alamatFocusGained

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        int row = this.jTable1.getSelectedRow();
        this.txt_baris.setText(String.valueOf(row));
        setTextData();
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        button_tabelklik();
        int baris = jTable1.rowAtPoint(evt.getPoint());
        txt_baris.setText(String.valueOf(baris));
        setTextData();
    }//GEN-LAST:event_jTable1MouseClicked

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
        try{
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kode");
            model.addColumn("Nama");
            model.addColumn("No. Hp");
            model.addColumn("Alamat");

            String cari = txt_cari.getText();
            String sql = "SELECT * FROM distributor WHERE id_distributor LIKE '%"+cari+"%' OR nm_distributor LIKE '%"+cari+"%' OR no_hp LIKE '%"+cari+"%' OR alamat LIKE '%"+cari+"%' ORDER BY id_distributor";
            java.sql.Connection conn=(java.sql.Connection)Koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{res.getString(1),res.getString(2),res.getString(3),res.getString(4)});
            }
            jTable1.setModel(model);
            txt_cari.setText(null);
            custom_tabel();
        }catch(Exception ex){
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Terjadi kesalahan !!!!");

        }
    }//GEN-LAST:event_txt_cariActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_baris;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_no_hp;
    // End of variables declaration//GEN-END:variables
}
