/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simrsberanda;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author macbook
 */
public class CariPeriksa extends javax.swing.JFrame {
    Koneksi conn = new Koneksi();
    public String namaform;
    Statement st;
    ResultSet rs;
    public void cari() {
       try {
           String sql = "SELECT * FROM rawat_jalan join pasien on rawat_jalan.kd_pasien=pasien.kd_pasien where nama_pasien like '%"+txtcari.getText()+"%' and rawat_jalan.status_rawat_jalan='Y'";
           st = (Statement) conn.getKoneksi().createStatement();
           rs = st.executeQuery(sql);
            DefaultTableModel dtm = (DefaultTableModel) gridtabel.getModel();
        dtm.setRowCount(0);
        String [] data = new String[9];
        int i = 1;
       
        while(rs.next()) {
            data[0] = String.valueOf(i);
            data[1] = rs.getString("kd_rawat_jalan");
             data[2] = rs.getString("kd_pasien");
             data[3] = rs.getString("nama_pasien");
            data[4] = rs.getString("alamat_pasien");            
            data[5] = rs.getString("resep"); 
             data[6] = rs.getString("tgl_periksa");
            dtm.addRow(data);
            i++;
        }
        
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    /**
     * Creates new form CariPeriksa
     */
    public CariPeriksa() {
        initComponents();
        this.getRootPane().setDefaultButton(jButton1);
        this.getRootPane().setDefaultButton(jButton2);
        this.getRootPane().setDefaultButton(jButton3);

        setLocationRelativeTo(null);
        cari();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridtabel = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton3.setText("Batal");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setText("Pilih");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Nama");

        gridtabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Kode", "kd Pasien", "Pasien", "alamat", "resep", "Tgl Periksa"
            }
        ));
        jScrollPane1.setViewportView(gridtabel);

        jButton1.setText("Cari");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(168, 168, 168)
                                .addComponent(jButton2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         int baris=gridtabel.getSelectedRow();
        if (namaform=="resep") {  
            int brs=Resep.cekbarisresep();
            Resep.kd_rawat_jalan.setText(gridtabel.getValueAt(baris, 1).toString());
            Resep.kd_pasien.setText(gridtabel.getValueAt(baris, 2).toString());
            Resep.nama_pasien.setText(gridtabel.getValueAt(baris, 3).toString());
            if (gridtabel.getValueAt(baris, 5) != null) {
                Resep.resep.setText(gridtabel.getValueAt(baris, 5).toString());  
            }
            else {
                Resep.resep.setText("--");  
            }           
            Resep.tgl_periksa.setText(gridtabel.getValueAt(baris, 6).toString());
            Resep.gridtabel.requestFocus();
            Resep.gridtabel.changeSelection(brs,0, false, false);
            
            dispose();
        }
        else if (namaform=="kasir") {
            int brs=Kasir.cekbarisresep();
            Kasir.kd_rawat_jalan.setText(gridtabel.getValueAt(baris, 1).toString());
            Kasir.kd_pasien.setText(gridtabel.getValueAt(baris, 2).toString());
            Kasir.nama_pasien.setText(gridtabel.getValueAt(baris, 3).toString());          
            Kasir.tgl_periksa.setText(gridtabel.getValueAt(baris, 6).toString());
            Kasir.tampiltransaksi();
            Kasir.gridtabel.requestFocus();
            Kasir.gridtabel.changeSelection(brs,0, false, false);
            dispose();       
        }
       // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(CariPeriksa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CariPeriksa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CariPeriksa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CariPeriksa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CariPeriksa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable gridtabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
