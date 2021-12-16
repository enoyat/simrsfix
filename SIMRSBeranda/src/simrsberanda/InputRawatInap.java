/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simrsberanda;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import static simrsberanda.Kasir_RawatInap.kd_ruang;

/**
 *
 * @author macbook
 */
public class InputRawatInap extends javax.swing.JFrame {
    public String aksi;
    public String[] arkd_dokter = new String[100];
    public String nobukti;
    Koneksi conn = new Koneksi();
    Statement st;
    ResultSet rs;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public void cari() {
       try {
           String sql = "SELECT * FROM `rawat_inap` join pasien on rawat_inap.kd_pasien=pasien.kd_pasien \n" +
                         "join dokter on rawat_inap.kd_dokter=dokter.kd_dokter join ruang on rawat_inap.kd_ruang=ruang.kd_ruang \n"+
                         "join asuransi on pasien.kd_asuransi=asuransi.kd_asuransi \n"+
                             "where kd_rawat_inap ='"+kd_rawat_inap.getText()+"' order by rawat_inap.kd_ruang";
             st = (Statement) conn.getKoneksi().createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                kd_pasien.setText(rs.getString("kd_pasien")); 
                nama_pasien.setText(rs.getString("nama_pasien"));
                alamat_pasien.setText(rs.getString("alamat_pasien"));
                kd_ruang.setText(rs.getString("kd_ruang")); 
                nama_ruang.setText(rs.getString("nama_ruang"));
                nama_dokter.setSelectedItem(rs.getString("nama_dokter"));
                tgl_masuk.setDate(java.sql.Date.valueOf(rs.getString("tgl_masuk")));
                kd_rawat_inap.setText(rs.getString("kd_rawat_inap"));
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void cariruang() {
       try {
            String sql = "SELECT * FROM ruang where kd_ruang=?";
            PreparedStatement stcari = conn.getKoneksi().prepareStatement(sql);
            stcari.setString(1,kd_ruang.getText());
            rs = stcari.executeQuery();
            while(rs.next()) {
                nama_ruang.setText(rs.getString("nama_ruang"));
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void caribiaya() {
       try {
            String sql = "SELECT * FROM biaya where kd_biaya=?";
            PreparedStatement stcari = conn.getKoneksi().prepareStatement(sql);
            stcari.setString(1,kd_biaya.getText());
            rs = stcari.executeQuery();
            while(rs.next()) {
                nama_biaya.setText(rs.getString("nama_biaya"));
                tarif.setText(rs.getString("tarif"));
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public boolean cek(String kode) throws SQLException {
                
        String sqlq ="select * from rawat_inap where kd_pasien= '"+kode+"' and status_rawat_inap='Y'";
        st = (Statement) Koneksi.getKoneksi().createStatement();
        ResultSet rs3 = st.executeQuery(sqlq);
        while (rs3.next()) {
            return !kode.equals(rs3.getString("kd_pasien"));
        }
        return true;
    }

    public void combodokter(){
        try {
      
            String query = "select * from  dokter order by nama_dokter";
            Statement stcombo = conn.getKoneksi().createStatement();
            ResultSet rscombo = stcombo.executeQuery(query);
            int i=1; 
            while (rscombo.next()) {                
                nama_dokter.addItem(rscombo.getString("nama_dokter"));
                arkd_dokter[i]=rscombo.getString("kd_dokter");
                i++;
            }                   
        } catch (SQLException e) {
        }
    }
    public void nomorotomatis() throws SQLException {
        try {
                String data;
                int nomor=0;
                int jlen=0;
                String nomorakhir="";

                String sql = "select max(kd_rawat_inap) as nomorakhir from rawat_inap";
                st = (Statement) Koneksi.getKoneksi().createStatement();
                rs = st.executeQuery(sql);
                if (rs.getRow()<1){
                      kd_rawat_inap.setText("RI00000001");
                }                
                while (rs.next()) {
                      nomorakhir=rs.getString("nomorakhir").substring(2);     
                      nomor= Integer.parseInt(nomorakhir)+1;
                      jlen=String.valueOf(nomor).length();
                      data="RI000000000";
                      kd_rawat_inap.setText(data.substring(0, 10-jlen)+String.valueOf(nomor));           
                }

        } catch (SQLException | NumberFormatException e) {
        }

    }
    public void nomorbukti() throws SQLException {
        try {
        String data;
            int nomor=0;
            int jlen=0;
            String nomorakhir="";

            String sql = "select max(nobukti) as nomorakhir from rawat_inap_biaya";
            Statement stx = (Statement) Koneksi.getKoneksi().createStatement();
            ResultSet rsx = stx.executeQuery(sql);
            if (rsx.getRow()<1){
                   nobukti="B000000001";
            }                
            while (rsx.next()) {
                  nomorakhir=rsx.getString("nomorakhir").substring(1);     
                  nomor= Integer.parseInt(nomorakhir)+1;
                  jlen=String.valueOf(nomor).length();
                  data="B000000000";
                  nobukti = data.substring(0, 10-jlen)+String.valueOf(nomor);           
            }             
        } catch (Exception e) {
        }

    }
    /**
     * Creates new form InputPendaftaran
     */
    public InputRawatInap() {
        initComponents();
        setLocationRelativeTo(null);
        combodokter();
   
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
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        alamat_pasien = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        kd_rawat_inap = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        nama_dokter = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        kd_biaya = new javax.swing.JTextField();
        nama_biaya = new javax.swing.JTextField();
        tarif = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        kd_asuransi = new javax.swing.JTextField();
        nama_asuransi = new javax.swing.JTextField();
        noasuransi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        kd_ruang = new javax.swing.JTextField();
        nama_ruang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tgl_masuk = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon_simrs/registrasi.png"))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("FORM PENDAFTARAN RAWAT INAP");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel2.setText("Formulir untuk entry data pendaftaran rawat inap");

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
                .addContainerGap(346, Short.MAX_VALUE))
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

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Pendaftaran Pasien "));

        nama_pasien.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel7.setText("Kode Pasien");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel6.setText("Tanggal Masuk");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel10.setText("Nama Pasien");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel11.setText("Ruang");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/cari4.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
        jLabel8.setText("Nomor Pendaftaran");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel16.setText("Dokter");

        nama_dokter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "== Pilih Dokter ==" }));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        jLabel14.setText("Administrasi");

        kd_biaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kd_biayaKeyPressed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Penjamin dari Perusahaan / Instansi"));

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

        kd_ruang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kd_ruangKeyPressed(evt);
            }
        });

        jLabel5.setText("F2 = Cari Ruangan");

        tgl_masuk.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(57, 57, 57)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(kd_biaya, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(nama_biaya, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tarif, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel16)
                            .addComponent(jLabel6))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nama_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(kd_pasien, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                    .addComponent(kd_rawat_inap))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addComponent(nama_dokter, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(kd_ruang, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(nama_ruang))
                                .addComponent(alamat_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel5))
                            .addComponent(tgl_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToggleButton2)
                .addGap(79, 79, 79))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(kd_rawat_inap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kd_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nama_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(alamat_pasien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tgl_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(kd_ruang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nama_ruang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nama_dokter, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kd_biaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nama_biaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tarif, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToggleButton1)
                    .addComponent(jToggleButton2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        try {
            if ("input".equals(aksi)) {
                if (cek(kd_pasien.getText())==false){
                    JOptionPane.showMessageDialog(null, "Data sudah ada !");
                    dispose();
                }

                else {
                 //   JOptionPane.showMessageDialog(null, "Dssdsds!");
                    String sql2 ="INSERT INTO rawat_inap(kd_rawat_inap, kd_pasien, tgl_masuk, kd_ruang, kd_dokter) \n" +
                                "VALUES (?,?,?,?,?)";
                    PreparedStatement stinsert = conn.getKoneksi().prepareStatement(sql2);
                    stinsert.setString(1,kd_rawat_inap.getText());
                    stinsert.setString(2,kd_pasien.getText());
                    stinsert.setString(3, df.format(tgl_masuk.getDate()));
                    stinsert.setString(4,kd_ruang.getText());
                    stinsert.setString(5,arkd_dokter[nama_dokter.getSelectedIndex()]);                    
                    stinsert.execute();
                    
                    // input ke transaksi biaya
                    String sql3 ="INSERT INTO rawat_inap_biaya(nobukti, kd_rawat_inap, kd_biaya, tgl_bukti, item, tarif)  \n"+
                                 " VALUES (?, ?,?,CURRENT_DATE,?,?)";
                    PreparedStatement stinsert2 = conn.getKoneksi().prepareStatement(sql3);
                    nomorbukti();
                    stinsert2.setString(1,nobukti);
                    stinsert2.setString(2,kd_rawat_inap.getText());
                    stinsert2.setString(3,kd_biaya.getText());
                    String item="["+kd_biaya.getText()+"] - "+nama_biaya.getText()+" "+nama_pasien.getText();
                    stinsert2.setString(4,item);                    
                    stinsert2.setInt(5,Integer.valueOf(tarif.getText()));
                    stinsert2.execute();
                    
                    // insert ke transaksi faktur
                    sql3 ="INSERT INTO rawat_inap_faktur(kd_rawat_inap, kd_klinik, no_bukti, tgl_bukti, item, tarif)   \n"+
                                 " VALUES (?, ?,?,CURRENT_DATE,?,?)";
                    PreparedStatement stinsert3 = conn.getKoneksi().prepareStatement(sql3);
                    stinsert3.setString(1,kd_rawat_inap.getText());
                    stinsert3.setString(2,"RWTINAP");
                    stinsert3.setString(3,nobukti);
                    stinsert3.setString(4,item);                    
                    stinsert3.setInt(5,Integer.valueOf(tarif.getText()));
                    stinsert3.execute();                    
                    
                    sql3 ="update ruang set status_ruang='T' \n"+
                    " where kd_ruang=?";
                    PreparedStatement stinsert5 = conn.getKoneksi().prepareStatement(sql3);
                    stinsert5.setString(1,kd_ruang.getText());
                    stinsert5.execute();  
                    JOptionPane.showMessageDialog(null, "Sukses tersimpan!");
                    RawatInap.tampil();
                    dispose();
                }
            }
            else {
                String sql ="UPDATE rawat_inap SET kd_pasien=?,kd_ruang=?,kd_dokter=?,tgl_masuk=? where kd_rawat_inap=?";
                PreparedStatement stupdate = conn.getKoneksi().prepareStatement(sql);
                stupdate.setString(1,kd_pasien.getText());
                stupdate.setString(2,kd_ruang.getText());
                stupdate.setString(3,arkd_dokter[nama_dokter.getSelectedIndex()]); 
                stupdate.setString(5,df.format(tgl_masuk.getDate() ));
                
                stupdate.setString(5,kd_rawat_inap.getText());
                stupdate.execute();
                JOptionPane.showMessageDialog(null, "Upadate  Data Sukses !");
                RawatInap.tampil();
                dispose();

            }
        } catch (SQLException | HeadlessException e) {
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CariPasien frm=new CariPasien();
        frm.setVisible(true);
        frm.namaform="rawatinap";
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void kd_biayaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kd_biayaKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            caribiaya();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_kd_biayaKeyPressed

    private void kd_ruangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kd_ruangKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            cariruang();
        }      
        else if(evt.getKeyCode() == KeyEvent.VK_F2)
        {
            CariRuang frm=new CariRuang();
            frm.namaform="rawatinap";
            frm.setVisible(true);
        }
        
    }//GEN-LAST:event_kd_ruangKeyPressed

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
            java.util.logging.Logger.getLogger(InputRawatInap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InputRawatInap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InputRawatInap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InputRawatInap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InputRawatInap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField alamat_pasien;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    public static javax.swing.JTextField kd_asuransi;
    private javax.swing.JTextField kd_biaya;
    public static javax.swing.JTextField kd_pasien;
    public static javax.swing.JTextField kd_rawat_inap;
    public static javax.swing.JTextField kd_ruang;
    public static javax.swing.JTextField nama_asuransi;
    private javax.swing.JTextField nama_biaya;
    private javax.swing.JComboBox nama_dokter;
    public static javax.swing.JTextField nama_pasien;
    public static javax.swing.JTextField nama_ruang;
    public static javax.swing.JTextField noasuransi;
    private javax.swing.JTextField tarif;
    private com.toedter.calendar.JDateChooser tgl_masuk;
    // End of variables declaration//GEN-END:variables
}
