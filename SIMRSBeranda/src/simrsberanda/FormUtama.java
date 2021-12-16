/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simrsberanda;

import java.awt.CardLayout;
import java.awt.Toolkit;
import simrsberanda.laporan.FormrptDokter;
import simrsberanda.laporan.FormrptKlinik;
import simrsberanda.laporan.FormrptObat;
import simrsberanda.laporan.FormrptPasien;
import simrsberanda.laporan.FormrptRawatInap;
import simrsberanda.laporan.FormrptRuang;
import simrsberanda.laporan.RptKlinik;
import java.util.Calendar;
import java.util.GregorianCalendar;
import simrsberanda.laporan.FormrptRawatJalan;
import javax.swing.ImageIcon;

public class FormUtama extends javax.swing.JFrame {
    private final Obat frmobat = new Obat();
    private final RawatJalan priksa = new RawatJalan();
    private final Resep resep = new Resep();
    private final Resep_RawatInap reseprawatinap = new Resep_RawatInap();
    private final Klinik klinik = new Klinik();
    private final Dokter dokter = new Dokter();
    private final Pasien pasien = new Pasien();
    private final Ruang ruang = new Ruang();
    private final Perawat perawat = new Perawat();
    private final AmbilObat ambiobat = new AmbilObat();
    private final AmbilObatInap ambiobatinap = new AmbilObatInap();
    private final RawatJalan rawatjalan = new RawatJalan();
    private final PerawatanRawatJalan perawatanrawatjalan = new PerawatanRawatJalan();
    private final PerawatanRawatInap perawatanrawatinap = new PerawatanRawatInap();

    private final RawatInap rawatinap = new RawatInap();
    private final Kasir kasir = new Kasir();
    private final Kasir_RawatInap kasirrawatinap = new Kasir_RawatInap();

    private final Diagnosa diagnosa = new Diagnosa();
    private final Tindakan tindakan = new Tindakan();
    private final Asuransi  asuransi = new Asuransi();

    private final FormrptDokter frmrptdokter = new FormrptDokter();
    private final FormrptPasien frmrptpaisen= new FormrptPasien();
    private final FormrptKlinik frmrptklinik = new FormrptKlinik();
    private final FormrptObat frmrptobat = new FormrptObat();
    private final FormrptRuang frmrptruang = new FormrptRuang();
    private final FormrptRawatInap frmrptrawatinap=new FormrptRawatInap();
    private final FormrptRawatJalan frmrptrawatjalan = new FormrptRawatJalan();
    private final RptKlinik rptklinik = new RptKlinik();

    private void bukabg() {
            PanelImage panelku = new PanelImage("bgcharlie.jpg");
            PanelBackground.add(panelku,"bac");
            CardLayout cl2 = (CardLayout)PanelBackground.getLayout();        
            cl2.show(PanelBackground, "bac");  
    }

    /**
     * Creates new form FormUtama
     */
    public FormUtama() {     
        initComponents();
        ImageIcon ico = new ImageIcon(getClass().getResource("inap.png"));
        setIconImage(ico.getImage());
        bukabg();
        // jam
        new Thread() {
            @Override
            public void run() {
                int timeRun=0;
                while(timeRun == 0)
                    {
                        Calendar cal = new GregorianCalendar();
                        int hour = cal.get(Calendar.HOUR);
                        int min = cal.get(Calendar.MINUTE);
                        int sec = cal.get(Calendar.SECOND);
                        int AM_PM = cal.get(Calendar.AM_PM);

                        String day_night = "";
                        if(AM_PM ==1)
                        {
                                day_night = "PM";
                        }
                            else
                        {
                            day_night = "AM";
                        }
                        String time = hour + ":" + min + ":" + sec;
                        jLabel.setText(time);
                        jLabelAMPM.setText(day_night);
                    }
            }
        }.start();
         
        //menu utama
        panelform.add(frmobat,"Obat");
        panelform.add(pasien,"Pasien");
        panelform.add(priksa,"Pemeriksaan");
        panelform.add(rawatjalan,"RawatJalan");
        panelform.add(perawat,"Perawat");
        panelform.add(rawatinap,"RawatInap");
        panelform.add(resep,"Resep");
        panelform.add(reseprawatinap,"Resep_RawatInap");
        panelform.add(ruang,"Ruang");
        panelform.add(ambiobat,"AmbilObat");
        panelform.add(ambiobatinap,"AmbilObatInap");
        panelform.add(dokter,"Dokter");
        panelform.add(klinik,"Klinik");
        panelform.add(kasir,"Kasir");
        panelform.add(kasirrawatinap,"Kasir_RawatInap");
        panelform.add(diagnosa,"Diagnosa");
        panelform.add(tindakan,"Tindakan");
        panelform.add(asuransi,"Asuransi");
        panelform.add(perawatanrawatjalan,"PerawatanRawatJalan");
         panelform.add(perawatanrawatinap,"PerawatanRawatInap");
      //laporan
        panelform.add(frmrptobat,"FormrptObat");
        panelform.add(frmrptdokter,"FormrptDokter");
        panelform.add(frmrptklinik,"FormrptKlinik");
        panelform.add(frmrptpaisen,"FormrptPasien");
        panelform.add(frmrptrawatinap,"FormrptRawatInap");
        panelform.add(frmrptrawatjalan,"FormrptRawatJalan");
        panelform.add(frmrptruang,"FormrptRuang");
        panelform.add(rptklinik,"RptKlinik");
        
        setExtendedState(MAXIMIZED_BOTH);       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelform = new javax.swing.JPanel();
        panelutama = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel = new javax.swing.JLabel();
        jLabelAMPM = new javax.swing.JLabel();
        PanelBackground = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenuItem25 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem27 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem26 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISTEM INFORMASI RUMAH SAKIT");
        setIconImages(null);

        panelform.setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(0, 145, 175));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("RS. Charlie Hospital");

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Jln raya semarang No.1, Gowok");

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText(" Ngabean, Boja, Kabupaten Kendal, Jawa Tengah 51381");

        jLabel.setFont(new java.awt.Font("DS-Digital", 1, 48)); // NOI18N
        jLabel.setForeground(new java.awt.Color(204, 204, 204));
        jLabel.setText("4:44:44");

        jLabelAMPM.setFont(new java.awt.Font("DS-Digital", 1, 48)); // NOI18N
        jLabelAMPM.setForeground(new java.awt.Color(204, 204, 204));
        jLabelAMPM.setText("PM");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                .addComponent(jLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAMPM)
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelAMPM)
                        .addComponent(jLabel))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(8, 8, 8)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        PanelBackground.setBackground(new java.awt.Color(208, 250, 243));
        PanelBackground.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout panelutamaLayout = new javax.swing.GroupLayout(panelutama);
        panelutama.setLayout(panelutamaLayout);
        panelutamaLayout.setHorizontalGroup(
            panelutamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelutamaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelutamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelutamaLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelutamaLayout.createSequentialGroup()
                        .addComponent(PanelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelutamaLayout.setVerticalGroup(
            panelutamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelutamaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelform.add(panelutama, "card2");

        jMenuBar1.setBackground(new java.awt.Color(0, 145, 175));
        jMenuBar1.setBorder(null);
        jMenuBar1.setForeground(new java.awt.Color(204, 204, 204));
        jMenuBar1.setBorderPainted(false);

        jMenu2.setForeground(new java.awt.Color(255, 255, 255));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/cabinet.png"))); // NOI18N
        jMenu2.setText("Induk");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem2.setText("Dokter");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Perawat");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Klinik");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem5.setText("Ruang");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem18.setText("Asuransi");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem18);

        jMenuItem24.setText("Tindakan");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem24);

        jMenuItem25.setText("Diagnosa");
        jMenuItem25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem25ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem25);

        jMenuBar1.add(jMenu2);

        jMenu5.setForeground(new java.awt.Color(255, 255, 255));
        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/registrasi.png"))); // NOI18N
        jMenu5.setText("Registrasi");
        jMenu5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/simrsberanda/icon.jpg"))); // NOI18N
        jMenu5.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/simrsberanda/icon.jpg"))); // NOI18N
        jMenu5.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/simrsberanda/icon.jpg"))); // NOI18N

        jMenuItem6.setText("Pasien");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenuItem7.setText("Rawat Jalan");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem7);

        jMenuItem27.setText("Rawat  Inap");
        jMenuItem27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem27ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem27);

        jMenuBar1.add(jMenu5);

        jMenu3.setForeground(new java.awt.Color(255, 255, 255));
        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/dokter.png"))); // NOI18N
        jMenu3.setText("Klinik");

        jMenuItem8.setText("Pemeriksaan Rawat Jalan ");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);

        jMenuItem26.setText("Pemeriksaan Rawat Inap");
        jMenuItem26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem26ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem26);

        jMenuBar1.add(jMenu3);

        jMenu4.setForeground(new java.awt.Color(255, 255, 255));
        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/obat.png"))); // NOI18N
        jMenu4.setText("Farmasi");

        jMenuItem10.setText("Resep Rawat Jalan");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem10);

        jMenuItem13.setText("Resep Rawat Inap");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        jMenuItem11.setText("Pengambilan Obat Rawat Jalan");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuItem20.setText("Pengambilan Obat Rawat Inap");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem20);

        jMenuItem23.setText("Obat");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem23);

        jMenuBar1.add(jMenu4);

        jMenu6.setForeground(new java.awt.Color(255, 255, 255));
        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/bayar2.png"))); // NOI18N
        jMenu6.setText("Kasir");

        jMenuItem12.setText("Kasir Rawat Jalan");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem12);

        jMenuItem14.setText("Kasir Rawat Inap");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem14);

        jMenuBar1.add(jMenu6);

        jMenu8.setForeground(new java.awt.Color(255, 255, 255));
        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pemeriksaan.png"))); // NOI18N
        jMenu8.setText("Laporan");

        jMenuItem1.setText("Data Pasien");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem1);

        jMenuItem22.setText("Data Klinik");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem22);

        jMenuItem15.setText("Data Dokter");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem15);

        jMenuItem17.setText("Obat");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem17);

        jMenuItem9.setText("Data Ruangan");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem9);

        jMenuItem19.setText("Rawat Jalan");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem19);

        jMenuItem16.setText("Rawat Inap");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem16);

        jMenuBar1.add(jMenu8);

        jMenu9.setForeground(new java.awt.Color(255, 255, 255));
        jMenu9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/exit2.png"))); // NOI18N
        jMenu9.setText("Keluar");
        jMenu9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu9MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu9);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelform, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelform, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "FormrptPasien");        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "FormrptRawatInap");   
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "RawatJalan");       
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "Klinik");
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenu9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu9MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jMenu9MouseClicked

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "FormrptKlinik");      
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
      
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "Dokter"); 
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "Perawat"); 
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "Ruang");        
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "Pasien");       
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "PerawatanRawatJalan");
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "Obat");
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "Resep"); 
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "AmbilObat"); 
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "FormrptDokter"); 
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "FormrptObat"); 
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "FormrptRuang");            
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem25ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "Diagnosa");        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem25ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "Tindakan");         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "Asuransi");
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem27ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "RawatInap");        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem27ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "Kasir");        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem26ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "PerawatanRawatInap");        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem26ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "Resep_RawatInap"); 
                // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "Kasir_RawatInap");          // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "FormrptRawatJalan"); 
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        CardLayout cl = (CardLayout)panelform.getLayout();
        cl.show(panelform, "AmbilObatInap");         // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem20ActionPerformed

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
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelBackground;
    private javax.swing.JLabel jLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelAMPM;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem25;
    private javax.swing.JMenuItem jMenuItem26;
    private javax.swing.JMenuItem jMenuItem27;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel panelform;
    private javax.swing.JPanel panelutama;
    // End of variables declaration//GEN-END:variables
}
