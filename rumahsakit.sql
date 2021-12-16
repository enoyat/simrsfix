-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sep 09, 2019 at 10:27 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.1.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rumahsakit`
--

-- --------------------------------------------------------

--
-- Table structure for table `asuransi`
--

DROP TABLE IF EXISTS `asuransi`;
CREATE TABLE IF NOT EXISTS `asuransi` (
  `kd_asuransi` varchar(5) NOT NULL,
  `nama_asuransi` varchar(100) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `telpon` varchar(20) NOT NULL,
  PRIMARY KEY (`kd_asuransi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `asuransi`
--

INSERT INTO `asuransi` (`kd_asuransi`, `nama_asuransi`, `alamat`, `telpon`) VALUES
('AS002', 'Asuransi Jiwa Sraya', '-', '-'),
('AS003', 'Asuransi Bumi Putera', '-', '-'),
('BPJS', 'Asuransi BPJS', 'Jl. Semarang', '0726334');

-- --------------------------------------------------------

--
-- Table structure for table `biaya`
--

DROP TABLE IF EXISTS `biaya`;
CREATE TABLE IF NOT EXISTS `biaya` (
  `kd_biaya` varchar(10) NOT NULL,
  `nama_biaya` varchar(100) NOT NULL,
  `tarif` int(11) NOT NULL,
  `kd_klinik` varchar(10) NOT NULL,
  PRIMARY KEY (`kd_biaya`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `biaya`
--

INSERT INTO `biaya` (`kd_biaya`, `nama_biaya`, `tarif`, `kd_klinik`) VALUES
('ADM', 'Biaya Administrasi', 5000, 'K001');

-- --------------------------------------------------------

--
-- Table structure for table `diagnosa`
--

DROP TABLE IF EXISTS `diagnosa`;
CREATE TABLE IF NOT EXISTS `diagnosa` (
  `kd_diagnosa` varchar(10) NOT NULL,
  `nama_diagnosa` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `diagnosa`
--

INSERT INTO `diagnosa` (`kd_diagnosa`, `nama_diagnosa`) VALUES
('D0001', 'Gejala Tipes'),
('D0002', 'Gejala Demam Berdarah');

-- --------------------------------------------------------

--
-- Table structure for table `dokter`
--

DROP TABLE IF EXISTS `dokter`;
CREATE TABLE IF NOT EXISTS `dokter` (
  `kd_dokter` varchar(20) NOT NULL,
  `nama_dokter` varchar(50) DEFAULT NULL,
  `alamat_dokter` varchar(50) DEFAULT NULL,
  `kd_spesialis` varchar(20) DEFAULT NULL,
  `tarif` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`kd_dokter`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `dokter`
--

INSERT INTO `dokter` (`kd_dokter`, `nama_dokter`, `alamat_dokter`, `kd_spesialis`, `tarif`) VALUES
('g1', 'dr. pepep ok bos', 'semarang ok', '2', 10000),
('g4', 'Dr. Bejo', 'dsgdsg', '2', 20000);

-- --------------------------------------------------------

--
-- Table structure for table `faktur_rawatinap`
--

DROP TABLE IF EXISTS `faktur_rawatinap`;
CREATE TABLE IF NOT EXISTS `faktur_rawatinap` (
  `kd_faktur` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `tgl_faktur` date DEFAULT NULL,
  `kd_rawat_inap` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL,
  `kd_pasien` varchar(15) CHARACTER SET utf8mb4 DEFAULT NULL,
  `status_bayar` char(1) CHARACTER SET utf8mb4 DEFAULT 'T',
  `uang` int(11) DEFAULT NULL,
  `status_ambil` varchar(1) CHARACTER SET utf8mb4 NOT NULL DEFAULT 'T',
  `item` text CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`kd_faktur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faktur_rawatinap`
--

INSERT INTO `faktur_rawatinap` (`kd_faktur`, `tgl_faktur`, `kd_rawat_inap`, `kd_pasien`, `status_bayar`, `uang`, `status_ambil`, `item`) VALUES
('KI00000001', '2019-05-27', 'RJ00000002', 'F000000002', 'T', 71000, 'T', '[KEU] - Keuangan Rawat Jalan  RJ00000002 dgdfhdgs');

-- --------------------------------------------------------

--
-- Table structure for table `faktur_rawatjalan`
--

DROP TABLE IF EXISTS `faktur_rawatjalan`;
CREATE TABLE IF NOT EXISTS `faktur_rawatjalan` (
  `kd_faktur` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `tgl_faktur` date DEFAULT NULL,
  `kd_rawat_jalan` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL,
  `kd_pasien` varchar(15) CHARACTER SET utf8mb4 DEFAULT NULL,
  `status_bayar` char(1) CHARACTER SET utf8mb4 DEFAULT 'T',
  `uang` int(11) DEFAULT NULL,
  `status_ambil` varchar(1) CHARACTER SET utf8mb4 NOT NULL DEFAULT 'T',
  `item` text CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`kd_faktur`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faktur_rawatjalan`
--

INSERT INTO `faktur_rawatjalan` (`kd_faktur`, `tgl_faktur`, `kd_rawat_jalan`, `kd_pasien`, `status_bayar`, `uang`, `status_ambil`, `item`) VALUES
('KJ00000001', '2019-05-26', 'RJ00000002', 'F000000002', 'T', 261000, 'T', '[KEU] - Keuangan Rawat Jalan  RJ00000002 dgdfhdgs');

-- --------------------------------------------------------

--
-- Table structure for table `klinik`
--

DROP TABLE IF EXISTS `klinik`;
CREATE TABLE IF NOT EXISTS `klinik` (
  `kd_klinik` varchar(10) NOT NULL,
  `nama_klinik` varchar(100) NOT NULL,
  `tampil` varchar(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`kd_klinik`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `klinik`
--

INSERT INTO `klinik` (`kd_klinik`, `nama_klinik`, `tampil`) VALUES
('ADM', 'Pendaftaran', 'T'),
('FRM', 'Farmasi', 'T'),
('K002', 'Poli Umum', 'Y'),
('K003', 'Poli Gigi', 'Y'),
('K004', 'Poli Kesehatan Ibu dan Anak', 'Y'),
('K005', 'Poli MTBS', 'Y'),
('K006', 'Poli Lansia', 'Y'),
('K008', 'Laboratorium', 'Y'),
('K009', 'IGD', 'Y'),
('K010', 'Medical Check Up', 'Y'),
('K011', 'Kamar Bersalin', 'Y'),
('K012', 'Kamar Operasi', 'Y'),
('KEU', 'Keuangan', 'T'),
('RWTINAP', 'Rawat Inap', 'T');

-- --------------------------------------------------------

--
-- Table structure for table `obat`
--

DROP TABLE IF EXISTS `obat`;
CREATE TABLE IF NOT EXISTS `obat` (
  `kd_obat` varchar(20) NOT NULL,
  `nama_obat` varchar(50) DEFAULT NULL,
  `golongan` varchar(20) DEFAULT NULL,
  `satuan` varchar(20) NOT NULL,
  `harga` int(11) DEFAULT NULL,
  PRIMARY KEY (`kd_obat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `obat`
--

INSERT INTO `obat` (`kd_obat`, `nama_obat`, `golongan`, `satuan`, `harga`) VALUES
('1', 'sffds', 'Obat Narkotika', 'Tablet', 7500),
('dadas', 'sadsad', 'Obat Bebas Terbatas', 'Tablet', 5000),
('dsdsa', 'sdasd', 'Obat Bebas Terbatas', 'Tablet', 6000);

-- --------------------------------------------------------

--
-- Table structure for table `pasien`
--

DROP TABLE IF EXISTS `pasien`;
CREATE TABLE IF NOT EXISTS `pasien` (
  `kd_pasien` varchar(20) NOT NULL,
  `nama_pasien` varchar(50) DEFAULT NULL,
  `jenis` varchar(1) DEFAULT NULL,
  `tempat` varchar(100) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `alamat_pasien` varchar(50) DEFAULT NULL,
  `tgl_daftar` date DEFAULT NULL,
  `gol_darah` varchar(2) DEFAULT NULL,
  `pekerjaan` varchar(100) DEFAULT NULL,
  `namaorangtua` varchar(100) DEFAULT NULL,
  `telpon` varchar(100) DEFAULT NULL,
  `agama` varchar(15) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `kd_asuransi` varchar(5) DEFAULT NULL,
  `noasuransi` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`kd_pasien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pasien`
--

INSERT INTO `pasien` (`kd_pasien`, `nama_pasien`, `jenis`, `tempat`, `tgl_lahir`, `alamat_pasien`, `tgl_daftar`, `gol_darah`, `pekerjaan`, `namaorangtua`, `telpon`, `agama`, `status`, `kd_asuransi`, `noasuransi`) VALUES
('F000000001', 'ok', 'L', 'asdd', '1975-01-01', 'dasdasd', '2019-05-23', 'B', '-', 'qqq', 'asds', 'Hindu', 'Belum Menikah', 'AS002', 'aasd'),
('F000000002', 'dgdfhdgs', 'L', 'dsfdsgdsg', '2019-01-01', 'afafaf', '2019-05-23', 'B', '-', '--', '-', 'Kristen', 'Menikah', 'AS003', '9009'),
('F000000004', 'sdfsasaf', 'L', 'fasfas', '2019-02-03', 'sdsds', '2019-05-24', 'AB', 'sdsd', 'sdsad', 'sasaf', 'Kristen', 'Belum Menikah', 'AS002', 'sdsad'),
('F000000005', 'ssdsad', 'P', 'sdsdsa', '1987-09-10', 'ssfsafsaf', '2019-05-24', 'B', 'sdsdsa', 'sdsdsa', 'asdsad', 'Kristen', 'Belum Menikah', 'BPJS', '112311');

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

DROP TABLE IF EXISTS `pembayaran`;
CREATE TABLE IF NOT EXISTS `pembayaran` (
  `kd_pembayaran` varchar(20) NOT NULL,
  `kd_petugas` varchar(20) DEFAULT NULL,
  `kd_periksa` varchar(20) DEFAULT NULL,
  `jumlah_harga` int(11) DEFAULT NULL,
  PRIMARY KEY (`kd_pembayaran`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `pengguna`
--

DROP TABLE IF EXISTS `pengguna`;
CREATE TABLE IF NOT EXISTS `pengguna` (
  `iduser` varchar(50) NOT NULL,
  `namauser` varchar(20) DEFAULT NULL,
  `pwd` varchar(200) DEFAULT NULL,
  `groupuser` varchar(50) DEFAULT 'operator',
  `foto` varchar(100) NOT NULL DEFAULT 'nopic.jpg',
  PRIMARY KEY (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengguna`
--

INSERT INTO `pengguna` (`iduser`, `namauser`, `pwd`, `groupuser`, `foto`) VALUES
('admin', 'admin', 'adm', 'administrator', 'pepep.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `perawat`
--

DROP TABLE IF EXISTS `perawat`;
CREATE TABLE IF NOT EXISTS `perawat` (
  `kd_perawat` varchar(20) NOT NULL,
  `nama_perawat` varchar(50) DEFAULT NULL,
  `alamat_perawat` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`kd_perawat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `perawat`
--

INSERT INTO `perawat` (`kd_perawat`, `nama_perawat`, `alamat_perawat`) VALUES
('p1', 'perawat 1', 'sleman ok');

-- --------------------------------------------------------

--
-- Table structure for table `rawat_inap`
--

DROP TABLE IF EXISTS `rawat_inap`;
CREATE TABLE IF NOT EXISTS `rawat_inap` (
  `kd_rawat_inap` varchar(15) NOT NULL,
  `kd_pasien` varchar(10) DEFAULT NULL,
  `tgl_masuk` date DEFAULT NULL,
  `kd_ruang` varchar(10) DEFAULT NULL,
  `kd_dokter` varchar(10) DEFAULT NULL,
  `tgl_keluar` date DEFAULT NULL,
  `status_rawat_inap` varchar(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`kd_rawat_inap`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rawat_inap`
--

INSERT INTO `rawat_inap` (`kd_rawat_inap`, `kd_pasien`, `tgl_masuk`, `kd_ruang`, `kd_dokter`, `tgl_keluar`, `status_rawat_inap`) VALUES
('RI00000001', 'F000000002', '2019-10-10', 'R1', 'g4', NULL, 'T'),
('RI00000002', 'F000000005', '2019-10-01', 'R1', 'g4', NULL, 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `rawat_inap2`
--

DROP TABLE IF EXISTS `rawat_inap2`;
CREATE TABLE IF NOT EXISTS `rawat_inap2` (
  `kd_rawat_inap` varchar(20) NOT NULL,
  `kd_ruang` varchar(20) DEFAULT NULL,
  `kd_pasien` varchar(20) DEFAULT NULL,
  `tgl_masuk` date DEFAULT NULL,
  `jam_masuk` varchar(5) DEFAULT NULL,
  `tgl_keluar` date DEFAULT NULL,
  `jam_keluar` varchar(5) DEFAULT NULL,
  `status_inap` varchar(1) NOT NULL DEFAULT 'T',
  PRIMARY KEY (`kd_rawat_inap`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `rawat_inap2`
--

INSERT INTO `rawat_inap2` (`kd_rawat_inap`, `kd_ruang`, `kd_pasien`, `tgl_masuk`, `jam_masuk`, `tgl_keluar`, `jam_keluar`, `status_inap`) VALUES
('RI1', 'R1', 'P2', '2010-01-01', '10:10', NULL, NULL, 'T');

-- --------------------------------------------------------

--
-- Table structure for table `rawat_inap_biaya`
--

DROP TABLE IF EXISTS `rawat_inap_biaya`;
CREATE TABLE IF NOT EXISTS `rawat_inap_biaya` (
  `nobukti` varchar(15) NOT NULL,
  `kd_rawat_inap` varchar(15) DEFAULT NULL,
  `kd_biaya` varchar(10) DEFAULT NULL,
  `tgl_bukti` date DEFAULT NULL,
  `item` text DEFAULT NULL,
  `tarif` int(11) DEFAULT NULL,
  PRIMARY KEY (`nobukti`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rawat_inap_biaya`
--

INSERT INTO `rawat_inap_biaya` (`nobukti`, `kd_rawat_inap`, `kd_biaya`, `tgl_bukti`, `item`, `tarif`) VALUES
('B000000001', 'RI00000001', 'adm', '2019-05-27', '[adm] - Biaya Administrasi dgdfhdgs', 5000),
('B000000002', 'RI00000002', 'adm', '2019-05-27', '[adm] - Biaya Administrasi ssdsad', 5000);

-- --------------------------------------------------------

--
-- Table structure for table `rawat_inap_diagnosa`
--

DROP TABLE IF EXISTS `rawat_inap_diagnosa`;
CREATE TABLE IF NOT EXISTS `rawat_inap_diagnosa` (
  `kd_rj_diagnosa` int(15) NOT NULL AUTO_INCREMENT,
  `kd_rawat_inap` varchar(15) DEFAULT NULL,
  `kd_diagnosa` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`kd_rj_diagnosa`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rawat_inap_diagnosa`
--

INSERT INTO `rawat_inap_diagnosa` (`kd_rj_diagnosa`, `kd_rawat_inap`, `kd_diagnosa`) VALUES
(9, 'RI00000001', 'D0002');

-- --------------------------------------------------------

--
-- Table structure for table `rawat_inap_faktur`
--

DROP TABLE IF EXISTS `rawat_inap_faktur`;
CREATE TABLE IF NOT EXISTS `rawat_inap_faktur` (
  `kd_faktur_item` int(11) NOT NULL AUTO_INCREMENT,
  `kd_rawat_inap` varchar(15) DEFAULT NULL,
  `kd_klinik` varchar(10) DEFAULT NULL,
  `no_bukti` varchar(20) DEFAULT NULL,
  `tgl_bukti` date NOT NULL,
  `item` text DEFAULT NULL,
  `tarif` int(11) DEFAULT 0,
  `penjamin` int(11) DEFAULT 0,
  `tunai` int(11) DEFAULT 0,
  `status_post` varchar(1) NOT NULL DEFAULT 'T',
  PRIMARY KEY (`kd_faktur_item`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rawat_inap_faktur`
--

INSERT INTO `rawat_inap_faktur` (`kd_faktur_item`, `kd_rawat_inap`, `kd_klinik`, `no_bukti`, `tgl_bukti`, `item`, `tarif`, `penjamin`, `tunai`, `status_post`) VALUES
(27, 'RI00000001', 'RWTINAP', 'B000000001', '2019-05-27', '[adm] - Biaya Administrasi dgdfhdgs', 5000, 0, 0, 'T'),
(28, 'RI00000001', 'RWTINAP', 'T000000001', '2019-05-27', '[T0002] - Laboratorium dgdfhdgs', 200000, 0, 0, 'T'),
(29, 'RI00000001', 'RWTINAP', 'T000000002', '2019-05-27', '[T0001] - EKG dgdfhdgs', 175000, 0, 0, 'T'),
(30, 'RJ00000002', 'FRM', 'FI00000001', '2019-05-27', '[FRM] - Farmasi Rawat Jalan  RJ00000002 dgdfhdgs', 71000, 0, 0, 'T'),
(31, 'RJ00000002', 'KEU', 'KI00000001', '2019-05-27', '[KEU] - Keuangan Rawat Jalan  RJ00000002 dgdfhdgs', 0, 0, 0, 'T'),
(32, 'RI00000002', 'RWTINAP', 'B000000002', '2019-05-27', '[adm] - Biaya Administrasi ssdsad', 5000, 0, 0, 'T');

-- --------------------------------------------------------

--
-- Table structure for table `rawat_inap_tindakan`
--

DROP TABLE IF EXISTS `rawat_inap_tindakan`;
CREATE TABLE IF NOT EXISTS `rawat_inap_tindakan` (
  `nobukti` varchar(15) NOT NULL,
  `kd_rawat_inap` varchar(15) DEFAULT NULL,
  `kd_tindakan` varchar(10) DEFAULT NULL,
  `tgl_bukti` date DEFAULT NULL,
  `item` text DEFAULT NULL,
  `tarif` int(11) DEFAULT NULL,
  PRIMARY KEY (`nobukti`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rawat_inap_tindakan`
--

INSERT INTO `rawat_inap_tindakan` (`nobukti`, `kd_rawat_inap`, `kd_tindakan`, `tgl_bukti`, `item`, `tarif`) VALUES
('T000000001', 'RI00000001', 'T0002', '2019-05-27', '[T0002] - Laboratorium dgdfhdgs', 200000),
('T000000002', 'RI00000001', 'T0001', '2019-05-27', '[T0001] - EKG dgdfhdgs', 175000);

-- --------------------------------------------------------

--
-- Table structure for table `rawat_jalan`
--

DROP TABLE IF EXISTS `rawat_jalan`;
CREATE TABLE IF NOT EXISTS `rawat_jalan` (
  `kd_rawat_jalan` varchar(15) NOT NULL,
  `kd_pasien` varchar(10) DEFAULT NULL,
  `tgl_periksa` date DEFAULT NULL,
  `kd_klinik` varchar(10) DEFAULT NULL,
  `kd_dokter` varchar(10) DEFAULT NULL,
  `keluhan` text DEFAULT NULL,
  `nomor_antri` int(5) NOT NULL DEFAULT 0,
  `resep` text DEFAULT NULL,
  `status_rawat_jalan` varchar(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`kd_rawat_jalan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rawat_jalan`
--

INSERT INTO `rawat_jalan` (`kd_rawat_jalan`, `kd_pasien`, `tgl_periksa`, `kd_klinik`, `kd_dokter`, `keluhan`, `nomor_antri`, `resep`, `status_rawat_jalan`) VALUES
('RJ00000001', 'F000000001', '2019-05-25', 'K002', 'g1', '1', 1, NULL, 'T'),
('RJ00000002', 'F000000002', '2019-05-25', 'K004', 'g1', '11', 2, 'ini resep baru', 'Y'),
('RJ00000003', 'F000000005', '2019-05-27', 'FRM', 'g4', 'batuk	', 3, NULL, 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `rawat_jalan_biaya`
--

DROP TABLE IF EXISTS `rawat_jalan_biaya`;
CREATE TABLE IF NOT EXISTS `rawat_jalan_biaya` (
  `nobukti` varchar(15) NOT NULL,
  `kd_rawat_jalan` varchar(15) DEFAULT NULL,
  `kd_biaya` varchar(10) DEFAULT NULL,
  `tgl_bukti` date DEFAULT NULL,
  `item` text DEFAULT NULL,
  `tarif` int(11) DEFAULT NULL,
  PRIMARY KEY (`nobukti`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rawat_jalan_biaya`
--

INSERT INTO `rawat_jalan_biaya` (`nobukti`, `kd_rawat_jalan`, `kd_biaya`, `tgl_bukti`, `item`, `tarif`) VALUES
('B000000001', 'RJ00000001', 'adm', '2019-05-25', '[adm] - Biaya Administrasi ok', 5000),
('B000000002', 'RJ00000002', 'adm', '2019-05-25', '[adm] - Biaya Administrasi dgdfhdgs', 5000),
('B000000003', 'RJ00000003', 'adm', '2019-05-27', '[adm] - Biaya Administrasi ssdsad', 5000);

-- --------------------------------------------------------

--
-- Table structure for table `rawat_jalan_diagnosa`
--

DROP TABLE IF EXISTS `rawat_jalan_diagnosa`;
CREATE TABLE IF NOT EXISTS `rawat_jalan_diagnosa` (
  `kd_rj_diagnosa` int(15) NOT NULL AUTO_INCREMENT,
  `kd_rawat_jalan` varchar(15) DEFAULT NULL,
  `kd_diagnosa` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`kd_rj_diagnosa`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rawat_jalan_diagnosa`
--

INSERT INTO `rawat_jalan_diagnosa` (`kd_rj_diagnosa`, `kd_rawat_jalan`, `kd_diagnosa`) VALUES
(8, 'RJ00000002', 'D0002');

-- --------------------------------------------------------

--
-- Table structure for table `rawat_jalan_faktur`
--

DROP TABLE IF EXISTS `rawat_jalan_faktur`;
CREATE TABLE IF NOT EXISTS `rawat_jalan_faktur` (
  `kd_faktur_item` int(11) NOT NULL AUTO_INCREMENT,
  `kd_rawat_jalan` varchar(15) DEFAULT NULL,
  `kd_klinik` varchar(10) DEFAULT NULL,
  `no_bukti` varchar(20) DEFAULT NULL,
  `tgl_bukti` date NOT NULL,
  `item` text DEFAULT NULL,
  `tarif` int(11) DEFAULT 0,
  `penjamin` int(11) DEFAULT 0,
  `tunai` int(11) DEFAULT 0,
  `status_post` varchar(1) NOT NULL DEFAULT 'T',
  PRIMARY KEY (`kd_faktur_item`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rawat_jalan_faktur`
--

INSERT INTO `rawat_jalan_faktur` (`kd_faktur_item`, `kd_rawat_jalan`, `kd_klinik`, `no_bukti`, `tgl_bukti`, `item`, `tarif`, `penjamin`, `tunai`, `status_post`) VALUES
(20, 'RJ00000002', 'K004', 'T000000001', '2019-05-25', '[T0002] - Laboratorium dgdfhdgs', 200000, 0, 0, 'T'),
(22, 'RJ00000002', 'FRM', 'FJ000000001', '2019-05-25', '[FRM] - Farmasi Rawat Jalan  RJ00000002 dgdfhdgs', 61000, 0, 0, 'T'),
(23, 'RJ00000001', 'FRM', 'FJ00000002', '2019-05-25', '[FRM] - Farmasi Rawat Jalan  RJ00000001 ok', 97500, NULL, NULL, 'T'),
(25, 'RJ00000002', 'KEU', 'KJ00000001', '2019-05-26', '[KEU] - Keuangan Rawat Jalan  RJ00000002 dgdfhdgs', 0, 0, 0, 'T'),
(26, 'RJ00000003', 'FRM', 'B000000003', '2019-05-27', '[adm] - Biaya Administrasi ssdsad', 5000, 0, 0, 'T');

-- --------------------------------------------------------

--
-- Table structure for table `rawat_jalan_tindakan`
--

DROP TABLE IF EXISTS `rawat_jalan_tindakan`;
CREATE TABLE IF NOT EXISTS `rawat_jalan_tindakan` (
  `nobukti` varchar(15) NOT NULL,
  `kd_rawat_jalan` varchar(15) DEFAULT NULL,
  `kd_tindakan` varchar(10) DEFAULT NULL,
  `tgl_bukti` date DEFAULT NULL,
  `item` text DEFAULT NULL,
  `tarif` int(11) DEFAULT NULL,
  PRIMARY KEY (`nobukti`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rawat_jalan_tindakan`
--

INSERT INTO `rawat_jalan_tindakan` (`nobukti`, `kd_rawat_jalan`, `kd_tindakan`, `tgl_bukti`, `item`, `tarif`) VALUES
('T000000001', 'RJ00000002', 'T0002', '2019-05-25', '[T0002] - Laboratorium dgdfhdgs', 200000);

-- --------------------------------------------------------

--
-- Table structure for table `resep`
--

DROP TABLE IF EXISTS `resep`;
CREATE TABLE IF NOT EXISTS `resep` (
  `kd_resep` varchar(20) NOT NULL,
  `tgl_resep` date DEFAULT NULL,
  `kd_rawat_jalan` varchar(20) DEFAULT NULL,
  `kd_pasien` varchar(15) DEFAULT NULL,
  `status_bayar` char(1) DEFAULT 'T',
  `uang` int(11) DEFAULT NULL,
  `status_ambil` varchar(1) NOT NULL DEFAULT 'T',
  `item` text DEFAULT NULL,
  PRIMARY KEY (`kd_resep`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `resep`
--

INSERT INTO `resep` (`kd_resep`, `tgl_resep`, `kd_rawat_jalan`, `kd_pasien`, `status_bayar`, `uang`, `status_ambil`, `item`) VALUES
('FJ000000001', '2019-05-25', 'RJ00000002', 'F000000002', 'T', 61000, 'T', '[FRM] - Farmasi Rawat Jalan  RJ00000002 dgdfhdgs'),
('FJ00000002', '2019-05-25', 'RJ00000001', 'F000000001', 'T', 97500, 'Y', '[FRM] - Farmasi Rawat Jalan  RJ00000001 ok');

-- --------------------------------------------------------

--
-- Table structure for table `resep_item`
--

DROP TABLE IF EXISTS `resep_item`;
CREATE TABLE IF NOT EXISTS `resep_item` (
  `kd_resep_item` int(11) NOT NULL AUTO_INCREMENT,
  `kd_resep` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `kd_obat` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL,
  `harga` int(11) NOT NULL,
  `qty` int(11) DEFAULT NULL,
  `jumlah` int(11) NOT NULL,
  `diskon` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `aturan_pakai` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `waktuminum` enum('sesudah makan','sebelum makan','','') CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  PRIMARY KEY (`kd_resep_item`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `resep_item`
--

INSERT INTO `resep_item` (`kd_resep_item`, `kd_resep`, `kd_obat`, `harga`, `qty`, `jumlah`, `diskon`, `total`, `aturan_pakai`, `waktuminum`) VALUES
(4, 'FJ000000001', 'dadas', 5000, 5, 25000, 0, 25000, '3 x 1 sehari', 'sesudah makan'),
(5, 'FJ000000001', 'dsdsa', 6000, 6, 36000, 0, 36000, '3 x 1 sehari', 'sesudah makan'),
(6, 'FJ00000002', 'dsdsa', 6000, 10, 60000, 0, 60000, '3 x 1 sehari', 'sesudah makan'),
(7, 'FJ00000002', '1', 7500, 5, 37500, 0, 37500, '1 x 1 sehari', 'sebelum makan');

-- --------------------------------------------------------

--
-- Table structure for table `resep_item_rawatinap`
--

DROP TABLE IF EXISTS `resep_item_rawatinap`;
CREATE TABLE IF NOT EXISTS `resep_item_rawatinap` (
  `kd_resep_item` int(11) NOT NULL AUTO_INCREMENT,
  `kd_resep` varchar(20) CHARACTER SET utf8mb4 NOT NULL,
  `kd_obat` varchar(20) CHARACTER SET utf8mb4 DEFAULT NULL,
  `harga` int(11) NOT NULL,
  `qty` int(11) DEFAULT NULL,
  `jumlah` int(11) NOT NULL,
  `diskon` int(11) NOT NULL,
  `total` int(11) NOT NULL,
  `aturan_pakai` varchar(100) CHARACTER SET utf8mb4 DEFAULT NULL,
  `waktuminum` enum('sesudah makan','sebelum makan','','') CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  PRIMARY KEY (`kd_resep_item`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `resep_item_rawatinap`
--

INSERT INTO `resep_item_rawatinap` (`kd_resep_item`, `kd_resep`, `kd_obat`, `harga`, `qty`, `jumlah`, `diskon`, `total`, `aturan_pakai`, `waktuminum`) VALUES
(8, 'FI00000001', 'dsdsa', 6000, 6, 36000, 0, 36000, '3 x 1 sehari', 'sesudah makan'),
(9, 'FI00000001', 'dadas', 5000, 7, 35000, 0, 35000, '3 x 1 sehari', 'sesudah makan');

-- --------------------------------------------------------

--
-- Table structure for table `resep_rawatinap`
--

DROP TABLE IF EXISTS `resep_rawatinap`;
CREATE TABLE IF NOT EXISTS `resep_rawatinap` (
  `kd_resep` varchar(20) NOT NULL,
  `tgl_resep` date DEFAULT NULL,
  `kd_rawat_inap` varchar(20) DEFAULT NULL,
  `kd_pasien` varchar(15) DEFAULT NULL,
  `status_bayar` char(1) DEFAULT 'T',
  `uang` int(11) DEFAULT NULL,
  `status_ambil` varchar(1) NOT NULL DEFAULT 'T',
  `item` text DEFAULT NULL,
  PRIMARY KEY (`kd_resep`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `resep_rawatinap`
--

INSERT INTO `resep_rawatinap` (`kd_resep`, `tgl_resep`, `kd_rawat_inap`, `kd_pasien`, `status_bayar`, `uang`, `status_ambil`, `item`) VALUES
('FI00000001', '2019-05-27', 'RJ00000002', 'F000000002', 'T', 71000, 'T', '[FRM] - Farmasi Rawat Jalan  RJ00000002 dgdfhdgs');

-- --------------------------------------------------------

--
-- Table structure for table `ruang`
--

DROP TABLE IF EXISTS `ruang`;
CREATE TABLE IF NOT EXISTS `ruang` (
  `kd_ruang` varchar(20) NOT NULL,
  `nama_ruang` varchar(30) DEFAULT NULL,
  `kelas` varchar(10) NOT NULL,
  `nama_gedung` varchar(30) DEFAULT NULL,
  `tarif` int(11) NOT NULL DEFAULT 0,
  `status_ruang` varchar(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`kd_ruang`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `ruang`
--

INSERT INTO `ruang` (`kd_ruang`, `nama_ruang`, `kelas`, `nama_gedung`, `tarif`, `status_ruang`) VALUES
('R1', 'Dahlia', 'Kelas 1', 'Bahtera', 150000, 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `satuan`
--

DROP TABLE IF EXISTS `satuan`;
CREATE TABLE IF NOT EXISTS `satuan` (
  `satuan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `satuan`
--

INSERT INTO `satuan` (`satuan`) VALUES
('Buah'),
('Tablet');

-- --------------------------------------------------------

--
-- Table structure for table `spesialis`
--

DROP TABLE IF EXISTS `spesialis`;
CREATE TABLE IF NOT EXISTS `spesialis` (
  `kd_spesialis` varchar(10) NOT NULL,
  `nama_spesialis` varchar(200) NOT NULL,
  PRIMARY KEY (`kd_spesialis`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `spesialis`
--

INSERT INTO `spesialis` (`kd_spesialis`, `nama_spesialis`) VALUES
('1', 'Spesialis Anak'),
('2', 'Spesialis Anastesi'),
('3', 'Spesialis Andrologi'),
('4', 'Spesialis Bedah');

-- --------------------------------------------------------

--
-- Table structure for table `tindakan`
--

DROP TABLE IF EXISTS `tindakan`;
CREATE TABLE IF NOT EXISTS `tindakan` (
  `kd_tindakan` varchar(10) NOT NULL,
  `nama_tindakan` varchar(200) NOT NULL,
  `tarif` int(11) NOT NULL,
  PRIMARY KEY (`kd_tindakan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tindakan`
--

INSERT INTO `tindakan` (`kd_tindakan`, `nama_tindakan`, `tarif`) VALUES
('T0001', 'EKG', 175000),
('T0002', 'Laboratorium', 200000);

-- --------------------------------------------------------

--
-- Table structure for table `trj_diagnosa`
--

DROP TABLE IF EXISTS `trj_diagnosa`;
CREATE TABLE IF NOT EXISTS `trj_diagnosa` (
  `kd_trj_diagnosa` int(11) NOT NULL AUTO_INCREMENT,
  `kd_diagnosa` varchar(10) NOT NULL,
  `kd_rawat_jalan` varchar(10) NOT NULL,
  PRIMARY KEY (`kd_trj_diagnosa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `trj_tindakan`
--

DROP TABLE IF EXISTS `trj_tindakan`;
CREATE TABLE IF NOT EXISTS `trj_tindakan` (
  `kd_trj_tindakan` varchar(10) NOT NULL,
  `kd_rawat_jalan` varchar(10) NOT NULL,
  `tgl_tindakan` date NOT NULL,
  `kd_tindakan` varchar(10) NOT NULL,
  `tarif` int(11) NOT NULL,
  `item_tindakan` text NOT NULL,
  PRIMARY KEY (`kd_trj_tindakan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
