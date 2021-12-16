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
import static simrsberanda.InputDokter.kd_dokter;
import static simrsberanda.Resep.grandtotal;
import static simrsberanda.Resep.gridtabel;
import static simrsberanda.Resep.hitungtotal;

/**
 *
 * @author macbook
 */
public class InputPerawatanRawatJalan extends javax.swing.JFrame {
    public String aksi;
    public String nobukti;
    Koneksi conn = new Koneksi();
    Statement st;
    ResultSet rs;
    SimpleDateFormat tgl= new SimpleDateFormat();
    public void tampiltindakan() {
       try {
           String sql = "SELECT * FROM rawat_jalan_tindakan join tindakan on rawat_jalan_tindakan.kd_tindakan=tindakan.kd_tindakan  where kd_rawat_jalan ='"+kd_rawat_jalan.getText()+"' ";
           st = (Statement) conn.getKoneksi().createStatement();
           rs = st.executeQuery(sql);
            DefaultTableModel dtm = (DefaultTableModel) gridtindakan.getModel();
            dtm.setRowCount(0);
            String [] data = new String[10];
            int i = 1;

            while(rs.next()) {
                data[0] = rs.getString("kd_tindakan");
                data[1] = rs.getString("nama_tindakan");
                data[2] = rs.getString("tarif");            
                dtm.addRow(data);
                i++;
            }

            }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }
    public void tampildiagnosa() {
       try {
           String sql = "SELECT * FROM rawat_jalan_diagnosa join diagnosa on rawat_jalan_diagnosa.kd_diagnosa=diagnosa.kd_diagnosa  where kd_rawat_jalan ='"+kd_rawat_jalan.getText()+"' ";
           st = (Statement) conn.getKoneksi().createStatement();
           rs = st.executeQuery(sql);
            DefaultTableModel dtm = (DefaultTableModel) griddiagnosa.getModel();
            dtm.setRowCount(0);
            String [] data = new String[10];
            int i = 1;

            while(rs.next()) {
                data[0] = rs.getString("kd_diagnosa");
                data[1] = rs.getString("nama_diagnosa");         
                dtm.addRow(data);
                i++;
            }

            }
            catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }
    public void cari() {
       try {
           String sql = "SELECT * FROM `rawat_jalan` join pasien on rawat_jalan.kd_pasien=pasien.kd_pasien \n" +
                         "join dokter on rawat_jalan.kd_dokter=dokter.kd_dokter join klinik on rawat_jalan.kd_klinik=klinik.kd_klinik left join asuransi on pasien.kd_asuransi=asuransi.kd_asuransi \n"+
                             "where kd_rawat_jalan ='"+kd_rawat_jalan.getText()+"' order by rawat_jalan.kd_klinik, nomor_antri";
             st = (Statement) conn.getKoneksi().createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                kd_pasien.setText(rs.getString("kd_pasien")); 
                nama_pasien.setText(rs.getString("nama_pasien"));
                alamat_pasien.setText(rs.getString("alamat_pasien"));
                kd_klinik.setText(rs.getString("kd_klinik"));
                kd_dokter.setText(rs.getString("kd_dokter"));
                nama_klinik.setText(rs.getString("nama_klinik"));
                nama_dokter.setText(rs.getString("nama_dokter"));
                tgl_periksa.setText(rs.getString("tgl_periksa"));
                kd_rawat_jalan.setText(rs.getString("kd_rawat_jalan"));
                resep.setText(rs.getString("resep"));
                if (rs.getString("kd_klinik") != null) {
                     kd_asuransi.setText(rs.getString("kd_klinik"));
                } 
                else {
                    kd_asuransi.setText("");
                }
                if (rs.getString("nama_asuransi") != null) {
                  nama_asuransi.setText(rs.getString("nama_asuransi"));
                } 
                else {
                    nama_asuransi.setText("");
                }
                if (rs.getString("noasuransi") != null) {
                   noasuransi.setText(rs.getString("noasuransi"));
                }  
                else {
                    noasuransi.setText("");
                }
                }
            }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void nomorbukti() throws SQLException {
        try {
        String data;
            int nomor=0;
            int jlen=0;
            String nomorakhir="";

            String sql = "select max(nobukti) as nomorakhir from rawat_jalan_tindakan";
            Statement stx = (Statement) Koneksi.getKoneksi().createStatement();
            ResultSet rsx = stx.executeQuery(sql);
            if (rsx.getRow()<1){
                   nobukti="T000000001";
            }                
            while (rsx.next()) {
                  nomorakhir=rsx.getString("nomorakhir").substring(1);     
                  nomor= Integer.parseInt(nomorakhir)+1;
                  jlen=String.valueOf(nomor).length();
                  data="T000000000";
                  nobukti = data.substring(0, 10-jlen)+String.valueOf(nomor);           
            }             
        } catch (Exception e) {
        }

    }
    public boolean cektindakan(String kode, String kode2) throws SQLException {
                
        String sqlq ="select * from rawat_jalan_tindakan where kd_tindakan= '"+kode+"' and kd_rawat_jalan='"+kode2+"'";
        st = (Statement) Koneksi.getKoneksi().createStatement();
        ResultSet rs3 = st.executeQuery(sqlq);
        while (rs3.next()) {
            return !kode.equals(rs3.getString("kd_tindakan"));
        }
        return true;
    }
    public void simpantindakan() throws SQLException {
        int jbaris=gridtindakan.getRowCount();
        String kd_tindakan, nama_tindakan,tarif;
        for (int i = 0; i < jbaris; i++) {
            if (gridtindakan.getValueAt(i, 0) != null) {
                    kd_tindakan=gridtindakan.getValueAt(i, 0).toString();
                    nama_tindakan=gridtindakan.getValueAt(i, 1).toString();
                    tarif=gridtindakan.getValueAt(i, 2).toString();
                    if (cektindakan(kd_tindakan,kd_rawat_jalan.getText())==false){
                    }
                    else {
                        String sql3 ="INSERT INTO rawat_jalan_tindakan (nobukti, kd_rawat_jalan, kd_tindakan, tgl_bukti, item, tarif)  \n"+
                        " VALUES (?, ?,?,CURRENT_DATE,?,?)";
                        PreparedStatement stinsert2 = conn.getKoneksi().prepareStatement(sql3);
                        nomorbukti();
                        stinsert2.setString(1,nobukti);
                        stinsert2.setString(2,kd_rawat_jalan.getText());
                        stinsert2.setString(3,kd_tindakan);
                        String item="["+kd_tindakan+"] - "+nama_tindakan+" "+nama_pasien.getText();
                        stinsert2.setString(4,item);
                        stinsert2.setInt(5,Integer.valueOf(tarif));
                        stinsert2.execute();

                        sql3 ="INSERT INTO rawat_jalan_faktur (kd_rawat_jalan, kd_klinik, no_bukti, tgl_bukti, item, tarif)   \n"+
                        " VALUES (?, ?,?,CURRENT_DATE,?,?)";
                        PreparedStatement stinsert3 = conn.getKoneksi().prepareStatement(sql3);
                        stinsert3.setString(1,kd_rawat_jalan.getText());
                        stinsert3.setString(2,kd_klinik.getText());
                        stinsert3.setString(3,nobukti);
                        stinsert3.setString(4,item);
                        stinsert3.setInt(5,Integer.valueOf(tarif));
                        stinsert3.execute();
                    }
            
            }   
        }
    }
    public boolean cekdiagnosa(String kode, String kode2) throws SQLException {
                
        String sqlq ="select * from rawat_jalan_diagnosa where kd_diagnosa= '"+kode+"' and kd_rawat_jalan='"+kode2+"'";
        st = (Statement) Koneksi.getKoneksi().createStatement();
        ResultSet rs3 = st.executeQuery(sqlq);
        while (rs3.next()) {
            return !kode.equals(rs3.getString("kd_diagnosa"));
        }
        return true;
    }
    public void simpandiagnosa() throws SQLException {
        int jbaris=griddiagnosa.getRowCount();
        String kd_diagnosa, nama_diagnosa;
        for (int i = 0; i < jbaris; i++) {
            if (griddiagnosa.getValueAt(i, 0) != null) {
                    kd_diagnosa=griddiagnosa.getValueAt(i, 0).toString();
                    nama_diagnosa=griddiagnosa.getValueAt(i, 1).toString();
                    if (cekdiagnosa(kd_diagnosa,kd_rawat_jalan.getText())==false){
                    }
                    else {                    
                        String sql3 ="INSERT INTO rawat_jalan_diagnosa (kd_rawat_jalan, kd_diagnosa)  \n"+
                        " VALUES (?, ?)";
                        PreparedStatement stinsert2 = conn.getKoneksi().prepareStatement(sql3);
                        nomorbukti();
                        stinsert2.setString(1,kd_rawat_jalan.getText());
                        stinsert2.setString(2,kd_diagnosa);
                        stinsert2.execute();
                    }

            }   
        }
    }
    public static int cekbaristindakan() {
        int jbaris;
        jbaris = gridtindakan.getRowCount();
        int i=0;
        for (int j = 0; j < jbaris; j++) {
            if (gridtindakan.getValueAt(i, 0) != null) {
               i=j;
            }  
        }   
        return i;
    }
    public static int cekbarisdiagnosa() {
        int jbaris;
        jbaris = griddiagnosa.getRowCount();
        int i=0;
        for (int j = 0; j < jbaris; j++) {
            if (griddiagnosa.getValueAt(i, 0) != null) {
               i=j;
            }        
        }   
        return i;
    }
    /**
     * Creates new form InputPerawatanRawatJalan
     */
    public InputPerawatanRawatJalan() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        kd_pasien = new javax.swing.JTextField();
        nama_pasien = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tgl_periksa = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        alamat_pasien = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        kd_rawat_jalan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        kd_asuransi = new javax.swing.JTextField();
        nama_asuransi = new javax.swing.JTextField();
        noasuransi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        kd_klinik = new javax.swing.JTextField();
        kd_dokter = new javax.swing.JTextField();
        nama_klinik = new javax.swing.JTextField();
        nama_dokter = new javax.swing.JTextField();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        gridtindakan = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        griddiagnosa = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        resep = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_simrs/registrasi.png"))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("FORM TINDAKAN DAN DIAGNOSA RAWAT JALAN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel2.setText("Formulir untuk entry data tindakan dan diagnosa rawat jalan ");

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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Pasien "));

        kd_pasien.setEnabled(false);

        nama_pasien.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel7.setText("Kode Pasien");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel6.setText("Tanggal Pendaftaran");

        tgl_periksa.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel11.setText("Klinik");

        alamat_pasien.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel12.setText("Alamat");

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_simrs/simpen.png"))); // NOI18N
        jToggleButton1.setText("SIMPAN");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jToggleButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_simrs/batal.png"))); // NOI18N
        jToggleButton2.setText("BATAL");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel8.setText("Nomor Rawat Jalan");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel16.setText("Dokter");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Penjamin dari Perusahaan / Instansi"));

        kd_asuransi.setEnabled(false);

        nama_asuransi.setEnabled(false);

        noasuransi.setEnabled(false);

        jLabel4.setText("No. Kartu");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel4))
                    .addComponent(kd_asuransi, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(noasuransi, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nama_asuransi, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kd_asuransi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nama_asuransi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noasuransi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kd_klinik.setEnabled(false);

        kd_dokter.setEnabled(false);

        nama_klinik.setEnabled(false);

        nama_dokter.setEnabled(false);

        jTabbedPane3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        gridtindakan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "kd_tindakan", "Tindakan", "Tarif"
            }
        ));
        jScrollPane1.setViewportView(gridtindakan);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_simrs/hapus.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(151, 151, 151))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jButton4)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Transaksi", jPanel5);

        griddiagnosa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Kode", "Diagnosa"
            }
        ));
        jScrollPane2.setViewportView(griddiagnosa);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_simrs/hapus.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(151, 151, 151))
        );

        jTabbedPane3.addTab("Diagnosa", jPanel6);

        resep.setColumns(20);
        resep.setRows(5);
        jScrollPane3.setViewportView(resep);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane3.addTab("Resep", jPanel4);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/jalan.png"))); // NOI18N
        jButton2.setText("Tindakan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/inap.png"))); // NOI18N
        jButton3.setText("Diagnosa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel16))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(alamat_pasien)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(kd_dokter, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                                                    .addComponent(kd_klinik))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(nama_klinik)
                                                    .addComponent(nama_dokter))))
                                        .addGap(6, 6, 6)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(kd_pasien, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                            .addComponent(kd_rawat_jalan))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tgl_periksa, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(nama_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jToggleButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton2)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(kd_rawat_jalan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(tgl_periksa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kd_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(nama_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(alamat_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kd_klinik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nama_klinik, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kd_dokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nama_dokter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButton2)
                    .addComponent(jToggleButton1)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        try {
            if ("input".equals(aksi)) {
                    // input ke transaksi tindakan
                    simpantindakan();
                    simpandiagnosa();
                    String sql ="update  rawat_jalan set resep =? where kd_rawat_jalan=?";
                    PreparedStatement stupdate = conn.getKoneksi().prepareStatement(sql);                
                    stupdate.setString(1,resep.getText());
                    stupdate.setString(2,kd_rawat_jalan.getText());
                    stupdate.execute();
                    JOptionPane.showMessageDialog(null, "Insert Data Sukses !");
                    PerawatanRawatJalan.tampil();
                    dispose();
            }
            else if ("edit".equals(aksi)) {
                    // input ke transaksi tindakan
                    simpantindakan();
                    simpandiagnosa();
                    String sql ="update  rawat_jalan set resep =? where kd_rawat_jalan=?";
                    PreparedStatement stupdate = conn.getKoneksi().prepareStatement(sql);                
                    stupdate.setString(1,resep.getText());
                    stupdate.setString(2,kd_rawat_jalan.getText());
                    stupdate.execute();
                    JOptionPane.showMessageDialog(null, "Update Data Sukses !");
                    PerawatanRawatJalan.tampil();
                    dispose();
            }
            
        } catch (SQLException | HeadlessException e) {
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        CariTindakan frmcari = new CariTindakan();
        frmcari.setVisible(true);
        frmcari.cari();
        frmcari.namaform="rawatjalan";
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        CariDiagnosa frmcari = new CariDiagnosa();
        frmcari.setVisible(true);
        frmcari.cari();
        frmcari.namaform="rawatjalan";        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if ("input".equals(aksi)) {
            int baris=gridtindakan.getSelectedRow();
            ((DefaultTableModel)gridtindakan.getModel()).removeRow(baris);
        }
        if ("edit".equals(aksi)) {
            try {
                int baris=gridtindakan.getSelectedRow();              
                String kode;
                kode = gridtindakan.getValueAt(baris, 0).toString();
                if(JOptionPane.showConfirmDialog(null, "Anda Yakin menghapus Data ini ??","Warning",2) == JOptionPane.YES_OPTION){
                    //cek bukti
                    String sqlq ="select * from rawat_jalan_tindakan where kd_tindakan= '"+kode+"' and kd_rawat_jalan='"+kd_rawat_jalan.getText()+"'";
                    st = (Statement) Koneksi.getKoneksi().createStatement();
                    ResultSet rs3 = st.executeQuery(sqlq);
                    while (rs3.next()) {
                        nobukti=rs3.getString("nobukti");
                    }                    
                    String input = "delete from rawat_jalan_tindakan where kd_tindakan= '"+kode+"' and kd_rawat_jalan='"+kd_rawat_jalan.getText()+"'";
                    st= conn.getKoneksi().createStatement();
                    int rsx = st.executeUpdate(input);                    
                    input = "delete from rawat_jalan_faktur where no_bukti= '"+nobukti+"' ";
                    int rsy = st.executeUpdate(input);                   
                    if(rsy == 1){
                           ((DefaultTableModel)gridtindakan.getModel()).removeRow(baris);
                    }
                }      
            } catch (HeadlessException | SQLException e) {
            }

        }      
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       if ("input".equals(aksi)) {
            int baris=griddiagnosa.getSelectedRow();
            ((DefaultTableModel)griddiagnosa.getModel()).removeRow(baris);
        }
        if ("edit".equals(aksi)) {
            try {
                int baris=griddiagnosa.getSelectedRow();              
                String kode;
                kode = griddiagnosa.getValueAt(baris, 0).toString();
                if(JOptionPane.showConfirmDialog(null, "Anda Yakin menghapus Data ini ??","Warning",2) == JOptionPane.YES_OPTION){
                    //cek bukti                                    
                    String input = "delete from rawat_jalan_diagnosa where kd_diagnosa= '"+kode+"' and kd_rawat_jalan='"+kd_rawat_jalan.getText()+"' ";
                    int rsy = st.executeUpdate(input);                   
                    if(rsy == 1){
                           ((DefaultTableModel)griddiagnosa.getModel()).removeRow(baris);
                    }
                }      
            } catch (HeadlessException | SQLException e) {
            }

        }              // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed
    public void cetak() {
      try {
    
            HashMap param = new HashMap();
            param.put("kd_rawat_jalan",kd_rawat_jalan.getText());
            JasperPrint jp = JasperFillManager.fillReport(getClass().getResourceAsStream("rptrawatjalan.jasper"), param, Koneksi.getKoneksi());
            JasperViewer.viewReport(jp, false);
        } catch(Exception e) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, e);
     
          
        }   
    }
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
            java.util.logging.Logger.getLogger(InputPerawatanRawatJalan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputPerawatanRawatJalan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputPerawatanRawatJalan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputPerawatanRawatJalan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InputPerawatanRawatJalan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField alamat_pasien;
    public static javax.swing.JTable griddiagnosa;
    public static javax.swing.JTable gridtindakan;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    public static javax.swing.JTextField kd_asuransi;
    private javax.swing.JTextField kd_dokter;
    private javax.swing.JTextField kd_klinik;
    public static javax.swing.JTextField kd_pasien;
    public static javax.swing.JTextField kd_rawat_jalan;
    public static javax.swing.JTextField nama_asuransi;
    private javax.swing.JTextField nama_dokter;
    private javax.swing.JTextField nama_klinik;
    public static javax.swing.JTextField nama_pasien;
    public static javax.swing.JTextField noasuransi;
    private javax.swing.JTextArea resep;
    private javax.swing.JTextField tgl_periksa;
    // End of variables declaration//GEN-END:variables
}
