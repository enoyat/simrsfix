/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simrsberanda;

import java.awt.Component;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author macbook
 */
public class Kasir_RawatInap extends javax.swing.JPanel {
    public String aksi;
    Koneksi conn = new Koneksi();
    Statement st;
    ResultSet rs;
    SimpleDateFormat tgl= new SimpleDateFormat();
    public String noresep;
    public static int cekbarisresep() {
        int jbaris;
        jbaris = gridtabel.getRowCount();
        int i=0;
        for (int j = 0; j < jbaris; j++) {
            if (gridtabel.getValueAt(i, 0) != null) {
               i=j;
            }  
        }   
        return i;
    }
    public static void tampiltransaksi() {
       try {
           String sql = "SELECT * FROM rawat_inap_faktur join klinik on rawat_inap_faktur.kd_klinik=klinik.kd_klinik  where kd_rawat_inap ='"+kd_rawat_inap.getText()+"' ";
           Statement stx = (Statement) Koneksi.getKoneksi().createStatement();
           ResultSet rsx = stx.executeQuery(sql);
            DefaultTableModel dtm = (DefaultTableModel) gridtabel.getModel();
            dtm.setRowCount(0);
            String [] data = new String[10];
            int i = 1;

            while(rsx.next()) {
                data[0] = rsx.getString("nama_klinik");
                data[1] = rsx.getString("no_bukti");
                data[2] = rsx.getString("tgl_bukti");
                data[3] = rsx.getString("item");                
                data[4] = rsx.getString("penjamin");
                data[5] = rsx.getString("tunai");
                data[6] = rsx.getString("tarif");
                dtm.addRow(data);
                i++;
            }
            grandtotal.setText(String.valueOf(hitungtotal()));
            }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }
    public boolean cek(String kode) throws SQLException {
                
        String sqlq ="select * from faktur_rawatinap where kd_rawat_inap= '"+kode+"'";
        st = (Statement) Koneksi.getKoneksi().createStatement();
        ResultSet rs3 = st.executeQuery(sqlq);
        while (rs3.next()) {
            return !kode.equals(rs3.getString("kd_rawat_inap"));
        }
        return true;
    }
    public void nomorotomatis() throws SQLException {
        try {
                String data;
                int nomor=0;
                int jlen=0;
                String nomorakhir="";

                String sql = "select max(kd_faktur) as nomorakhir from faktur_rawatinap";
                st = (Statement) Koneksi.getKoneksi().createStatement();
                rs = st.executeQuery(sql);
                if (rs.getRow()<1){
                      kd_faktur.setText("KI00000001");
                }                
                while (rs.next()) {
                      nomorakhir=rs.getString("nomorakhir").substring(2);     
                      nomor= Integer.parseInt(nomorakhir)+1;
                      jlen=String.valueOf(nomor).length();
                      data="KI00000000";
                      kd_faktur.setText(data.substring(0, 10-jlen)+String.valueOf(nomor));           
                }

        } catch (SQLException | NumberFormatException e) {
        }

    }

    /**
     *
     * @return
     */
    public static int hitungtotal() {
        int xtotal=0;
        int jbaris=gridtabel.getRowCount();
        for (int i = 0; i < jbaris; i++) {
            if (gridtabel.getValueAt(i, 6) != null) {
                int ntotal=Integer.parseInt(gridtabel.getValueAt(i, 6).toString());
                xtotal=xtotal+ntotal;
            }   
        }
        return xtotal;
    }
    public void bersih() {
        kd_faktur.setText("");
        DefaultTableModel dtm = (DefaultTableModel) gridtabel.getModel();
        dtm.setRowCount(0);
        kd_pasien.setText("");
        nama_pasien.setText("");
        kd_rawat_inap.setText("");
        kd_rawat_inap.requestFocus();
    }
    /**
    /**
     * Creates new form Resep
     */
 public void cetak() {
      try {
    
        HashMap param = new HashMap();
        param.put("kd_faktur",kd_faktur.getText());
        JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("rptfakturrawatinap.jasper"), param, Koneksi.getKoneksi());
        JasperViewer.viewReport(jp, false);
        } catch(Exception e) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, e);
        }   
    }
    /**
     * Creates new form Kasir
     */
    public Kasir_RawatInap() {
        initComponents();
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
        gridtabel = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        kd_rawat_inap = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        tgl_periksa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        kd_pasien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nama_pasien = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        kd_faktur = new javax.swing.JTextField();
        jToggleButton4 = new javax.swing.JToggleButton();
        grandtotal = new javax.swing.JTextField();
        jToggleButton1 = new javax.swing.JToggleButton();
        jButton4 = new javax.swing.JButton();
        btbaru = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        kd_ruang = new javax.swing.JTextField();

        gridtabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Klinik", "No. Bukti", "Tanggal", "item", "penjamin", "Tunai", "Tarif"
            }
        ));
        jScrollPane1.setViewportView(gridtabel);
        if (gridtabel.getColumnModel().getColumnCount() > 0) {
            gridtabel.getColumnModel().getColumn(0).setPreferredWidth(150);
            gridtabel.getColumnModel().getColumn(0).setMaxWidth(150);
            gridtabel.getColumnModel().getColumn(1).setPreferredWidth(75);
            gridtabel.getColumnModel().getColumn(1).setMaxWidth(75);
            gridtabel.getColumnModel().getColumn(2).setPreferredWidth(75);
            gridtabel.getColumnModel().getColumn(2).setMaxWidth(75);
            gridtabel.getColumnModel().getColumn(4).setPreferredWidth(75);
            gridtabel.getColumnModel().getColumn(4).setMaxWidth(75);
            gridtabel.getColumnModel().getColumn(5).setPreferredWidth(75);
            gridtabel.getColumnModel().getColumn(5).setMaxWidth(75);
            gridtabel.getColumnModel().getColumn(6).setPreferredWidth(100);
            gridtabel.getColumnModel().getColumn(6).setMaxWidth(100);
        }

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_simrs/resep.png"))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("FORM PEMBAYARAN RAWAT INAP");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel2.setText("Formulir untuk entry data resep");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))))
        );

        jButton1.setText("Cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("No. Rawat Inap");

        tgl_periksa.setEnabled(false);

        jLabel5.setText("Tgl. Periksa");

        kd_pasien.setEnabled(false);
        kd_pasien.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                kd_pasienPropertyChange(evt);
            }
        });

        jLabel6.setText("Kode Pasien");

        jLabel7.setText("Nama Pasien");

        nama_pasien.setEnabled(false);

        jLabel9.setText("Total");

        jLabel11.setText("No. KWITANSI");

        jToggleButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_simrs/keluar.png"))); // NOI18N
        jToggleButton4.setText("KELUAR");
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });

        grandtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        grandtotal.setEnabled(false);

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_simrs/simpen.png"))); // NOI18N
        jToggleButton1.setText("KONFIRMASI");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/print.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btbaru.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_simrs/tambah.png"))); // NOI18N
        btbaru.setText("Baru");
        btbaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btbaruActionPerformed(evt);
            }
        });

        jLabel8.setText("Kd. Ruang");

        kd_ruang.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btbaru, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nama_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(kd_pasien, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(kd_rawat_inap, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(tgl_periksa, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel8)
                                                .addGap(18, 18, 18)
                                                .addComponent(kd_ruang, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(kd_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 169, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(grandtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btbaru, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(kd_faktur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kd_rawat_inap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kd_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(tgl_periksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(kd_ruang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(nama_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(grandtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jToggleButton1)
                        .addComponent(jToggleButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CariPeriksaInap frm=new CariPeriksaInap();
        frm.setVisible(true);
        frm.namaform="kasirrawatinap";
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void kd_pasienPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_kd_pasienPropertyChange

        // TODO add your handling code here:
    }//GEN-LAST:event_kd_pasienPropertyChange

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        this.setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        try {
            if (cek(kd_rawat_inap.getText())==false){
                JOptionPane.showMessageDialog(null, "Data sudah ada !");
            }

            else {
                String item="[KEU] - Keuangan Rawat Jalan  "+kd_rawat_inap.getText()+" "+nama_pasien.getText();

                String sql2 ="INSERT INTO faktur_rawatinap (kd_faktur, tgl_faktur, kd_rawat_inap, kd_pasien, status_bayar, uang, status_ambil, item) \n" +
                " VALUES (?,CURRENT_DATE,?,?,'T',?,'T',?)";
                PreparedStatement stinsert = conn.getKoneksi().prepareStatement(sql2);
                stinsert.setString(1,kd_faktur.getText());
                stinsert.setString(2,kd_rawat_inap.getText());
                stinsert.setString(3,kd_pasien.getText());
                stinsert.setInt(4,Integer.valueOf(grandtotal.getText()));
                stinsert.setString(5,item);
                stinsert.execute();

                // input faktur
                String sql3 ="INSERT INTO rawat_inap_faktur (kd_rawat_inap, kd_klinik, no_bukti, tgl_bukti, item, tarif)   \n"+
                " VALUES (?, ?,?,CURRENT_DATE,?,?)";
                PreparedStatement stinsert3 = conn.getKoneksi().prepareStatement(sql3);
                stinsert3.setString(1,kd_rawat_inap.getText());
                stinsert3.setString(2,"KEU");
                stinsert3.setString(3,kd_faktur.getText());
                stinsert3.setString(4,item);
                stinsert3.setInt(5,0);
                stinsert3.execute();
                
                // update pendaftaran
                sql3 ="update rawat_inap set status_rawat_inap='T' \n"+
                " where kd_rawat_inap=?";
                PreparedStatement stinsert4 = conn.getKoneksi().prepareStatement(sql3);
                stinsert4.setString(1,kd_rawat_inap.getText());
                stinsert4.execute();                
                
                sql3 ="update ruang set status_ruang='Y' \n"+
                " where kd_ruang=?";
                PreparedStatement stinsert5 = conn.getKoneksi().prepareStatement(sql3);
                stinsert5.setString(1,kd_ruang.getText());
                stinsert5.execute();                   
                JOptionPane.showMessageDialog(null, "Konfirmasi Sukses !");
                tampiltransaksi();
            }
        } catch (SQLException | HeadlessException e) {
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        cetak();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btbaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btbaruActionPerformed
        try {
            bersih();
            nomorotomatis();
        } catch (Exception e) {
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btbaruActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btbaru;
    public static javax.swing.JTextField grandtotal;
    public static javax.swing.JTable gridtabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JTextField kd_faktur;
    public static javax.swing.JTextField kd_pasien;
    public static javax.swing.JTextField kd_rawat_inap;
    public static javax.swing.JTextField kd_ruang;
    public static javax.swing.JTextField nama_pasien;
    public static javax.swing.JTextField tgl_periksa;
    // End of variables declaration//GEN-END:variables
}
