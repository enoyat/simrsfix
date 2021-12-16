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
import static simrsberanda.Resep.gridtabel;

/**
 *
 * @author macbook
 */
public class CariPasien extends javax.swing.JFrame {
    Koneksi conn = new Koneksi();
    public String namaform;
    Statement st;
    ResultSet rs;
    public void cari() {
       try {
           String sql = "SELECT * FROM pasien left join asuransi on pasien.kd_asuransi=asuransi.kd_asuransi where nama_pasien like '%"+txtcari.getText()+"%'";
           st = (Statement) conn.getKoneksi().createStatement();
           rs = st.executeQuery(sql);
            DefaultTableModel dtm = (DefaultTableModel) gridtabel.getModel();
        dtm.setRowCount(0);
        String [] data = new String[9];
        int i = 1;
       
        while(rs.next()) {
            data[0] = String.valueOf(i);
            data[1] = rs.getString("kd_pasien");
            data[2] = rs.getString("nama_pasien");
            data[3] = rs.getString("alamat_pasien");            
            data[4] = rs.getString("tgl_lahir");   
            data[5] = rs.getString("kd_asuransi");  
            data[6] = rs.getString("nama_asuransi");   
            data[7] = rs.getString("noasuransi");   
            dtm.addRow(data);
            i++;
        }
        
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    /**
     * Creates new form CariPasien
     */
    public CariPasien() {
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

        txtcari = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridtabel = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("Cari");

        jLabel1.setText("Nama");

        gridtabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Kode", "Pasien", "alamat", "Tgl Lahir", "kd asuransi", "Asuransi", "No. Kartu"
            }
        ));
        jScrollPane1.setViewportView(gridtabel);
        if (gridtabel.getColumnModel().getColumnCount() > 0) {
            gridtabel.getColumnModel().getColumn(4).setHeaderValue("Tgl Lahir");
        }

        jButton2.setText("Pilih");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Batal");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addContainerGap(385, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 252, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(16, 16, 16))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(60, 60, 60)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(52, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (namaform=="rawatjalan") {
            int baris=gridtabel.getSelectedRow();      
            InputRawatJalan.kd_pasien.setText(gridtabel.getValueAt(baris, 1).toString());
            InputRawatJalan.nama_pasien.setText(gridtabel.getValueAt(baris, 2).toString());
            InputRawatJalan.alamat_pasien.setText(gridtabel.getValueAt(baris, 3).toString());
            if (gridtabel.getValueAt(baris, 5) != null) {
               InputRawatJalan.kd_asuransi.setText(gridtabel.getValueAt(baris, 5).toString());
            } 
            else {
                InputRawatJalan.kd_asuransi.setText("");
            }
            if (gridtabel.getValueAt(baris, 6) != null) {
              InputRawatJalan.nama_asuransi.setText(gridtabel.getValueAt(baris, 6).toString());
            } 
            else {
                InputRawatJalan.nama_asuransi.setText("");
            }
            if (gridtabel.getValueAt(baris, 7) != null) {
               InputRawatJalan.noasuransi.setText(gridtabel.getValueAt(baris, 7).toString());
            }  
            else {
                InputRawatJalan.noasuransi.setText("");
            }
            dispose();
        }
        else if (namaform=="perawatanrawatjalan") {
            int baris=gridtabel.getSelectedRow();      
            InputPerawatanRawatJalan.kd_pasien.setText(gridtabel.getValueAt(baris, 1).toString());
            InputPerawatanRawatJalan.nama_pasien.setText(gridtabel.getValueAt(baris, 2).toString());
            InputPerawatanRawatJalan.alamat_pasien.setText(gridtabel.getValueAt(baris, 3).toString());
            if (gridtabel.getValueAt(baris, 5) != null) {
               InputPerawatanRawatJalan.kd_asuransi.setText(gridtabel.getValueAt(baris, 5).toString());
            } 
            else {
                InputPerawatanRawatJalan.kd_asuransi.setText("");
            }
            if (gridtabel.getValueAt(baris, 6) != null) {
              InputPerawatanRawatJalan.nama_asuransi.setText(gridtabel.getValueAt(baris, 6).toString());
            } 
            else {
                InputPerawatanRawatJalan.nama_asuransi.setText("");
            }
            if (gridtabel.getValueAt(baris, 7) != null) {
               InputPerawatanRawatJalan.noasuransi.setText(gridtabel.getValueAt(baris, 7).toString());
            }  
            else {
                InputPerawatanRawatJalan.noasuransi.setText("");
            }
            dispose();
        }
        else if (namaform=="rawatinap") {
            int baris=gridtabel.getSelectedRow();      
            InputRawatInap.kd_pasien.setText(gridtabel.getValueAt(baris, 1).toString());
            InputRawatInap.nama_pasien.setText(gridtabel.getValueAt(baris, 2).toString());
            InputRawatInap.alamat_pasien.setText(gridtabel.getValueAt(baris, 3).toString());
            if (gridtabel.getValueAt(baris, 5) != null) {
               InputRawatInap.kd_asuransi.setText(gridtabel.getValueAt(baris, 5).toString());
            } 
            else {
                InputRawatInap.kd_asuransi.setText("");
            }
            if (gridtabel.getValueAt(baris, 6) != null) {
              InputRawatInap.nama_asuransi.setText(gridtabel.getValueAt(baris, 6).toString());
            } 
            else {
                InputRawatJalan.nama_asuransi.setText("");
            }
            if (gridtabel.getValueAt(baris, 7) != null) {
               InputRawatInap.noasuransi.setText(gridtabel.getValueAt(baris, 7).toString());
            }  
            else {
                InputRawatInap.noasuransi.setText("");
            }
            dispose();
        }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(CariPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CariPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CariPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CariPasien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CariPasien().setVisible(true);
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