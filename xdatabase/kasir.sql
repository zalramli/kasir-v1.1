-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 03 Feb 2019 pada 15.29
-- Versi server: 10.1.37-MariaDB
-- Versi PHP: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kasir`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `id_barang` varchar(30) NOT NULL,
  `id_kategori` char(3) NOT NULL,
  `id_satuan` char(3) NOT NULL,
  `nm_barang` varchar(50) NOT NULL,
  `jml_stok` smallint(5) NOT NULL,
  `hrg_jual` int(10) NOT NULL,
  `hrg_grosir` int(10) NOT NULL,
  `hrg_beli` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`id_barang`, `id_kategori`, `id_satuan`, `nm_barang`, `jml_stok`, `hrg_jual`, `hrg_grosir`, `hrg_beli`) VALUES
('089686010015', 'K08', 'S01', 'Indomie Ayam bawang 69gr', 9999, 2500, 0, 2300),
('089686010107', 'K08', 'S01', 'Indomie Kaldu Ayam 75gr', 9999, 2500, 0, 2200),
('089686010947', 'K01', 'S01', 'Indomie Goreng 85gr', 9999, 2500, 0, 2000),
('089686011494', 'K08', 'S01', 'Indomie Soto Padang 75gr', 9999, 2500, 0, 2000),
('089686017748', 'K08', 'S01', 'Sarimi isi 2 Ayam kremes 125gr', 9999, 3500, 0, 3000),
('089686017755', 'K08', 'S01', 'Sarimi isi 2 soto koya 112gr', 9999, 3000, 0, 2500),
('089686018059', 'K01', 'S01', 'Sarimi isi 2 Ayam Bawang 115gr', 9999, 3500, 0, 3000),
('089686018080', 'K08', 'S01', 'Sarimi isi 2 tongseng 120gr', 9999, 3000, 0, 2500),
('089686018103', 'K01', 'S01', 'sarimi dok-dok deer', 50, 3000, 0, 2500),
('089686021219', 'K08', 'S01', 'Intermie goreng 60gr', 9996, 1250, 0, 1000),
('089686043297', 'K08', 'S01', 'Indomie Sambal Matah 85gr', 9999, 2500, 0, 2000),
('089686043686', 'K08', 'S01', 'Indomie goreng Iga penyet 80gr', 9999, 2500, 0, 2300),
('089686048704', 'K08', 'S01', 'Sarimi isi 2 Ayam kecap 126gr', 9999, 3500, 0, 3000),
('089686060003', 'K08', 'S01', 'Pop Mie Ayam bawang 75gr', 9999, 4000, 0, 3500),
('089686060027', 'K08', 'S01', 'Pop Mie Ayam 75gr', 9999, 4000, 0, 3500),
('089686060126', 'K08', 'S01', 'Pop Mie Baso 75gr', 9999, 4000, 0, 3500),
('089686060164', 'K08', 'S01', 'Pop Mie Baso Spesial 75gr', 9999, 4000, 0, 3500),
('089686060744', 'K08', 'S01', 'Pop Mie Goreng Spesial 80gr', 9999, 4000, 0, 3500),
('089686061024', 'K08', 'S01', 'Pop Mie Ayam Bawang 40gr', 9999, 3000, 0, 2500),
('089686061079', 'K08', 'S01', 'Pop Mie Mini Soto 39gr ', 9999, 3000, 0, 2500),
('089686400427', 'K01', 'S02', 'Indofood Sambal pedas 135ml', 9999, 5500, 0, 5000),
('089686400526', 'K01', 'S02', 'Indofood sambal pedas manis 135ml', 9999, 5500, 0, 5000),
('089686400816', 'K01', 'S02', 'Indofood Ekstra pedas 135ml', 9999, 5500, 0, 5000),
('089686401721', 'K01', 'S02', 'Indofood Saus Tomat 135ml', 9999, 5500, 0, 5000),
('089686552010', 'K07', 'S06', 'GoWell vanila 29gr ', 9998, 1000, 0, 750),
('089686910384', 'K08', 'S01', 'Indomie Soto Spesial 75gr', 9999, 2500, 0, 2200),
('089686923032', 'K08', 'S01', 'Sarimi gelas kari ayam 30gr', 9999, 1000, 0, 850),
('089686923063', 'K08', 'S01', 'Sarimi gelas soto ayam 30gr', 9999, 1000, 0, 750),
('4711036008187', 'K01', 'S01', 'Hatari cream crackers 260gr', 9999, 5000, 0, 4500),
('4711036008200', 'K01', 'S01', 'Hatari malkist 260gr', 9999, 5000, 0, 4500),
('4711036010265', 'K01', 'S01', 'Inafood marie susu 95gr', 9999, 2500, 0, 2000),
('4711036011347', 'K01', 'S01', 'Hatari chocolate 200gr', 9999, 4500, 0, 4000),
('4711036012122', 'K01', 'S01', 'Biskuit delux 300gr', 9999, 6000, 0, 5500),
('4902430557122', 'K04', 'S06', 'downy sunrise fresh', 9999, 1000, 0, 750),
('4902430685856', 'K04', 'S06', 'downy sweetheart 20ml', 9999, 1000, 0, 750),
('4902430693455', 'K04', 'S06', 'downy daring 20ml', 9999, 1000, 0, 750),
('4902430710008', 'K04', 'S01', 'Downy banlaw 240ml', 1, 10000, 0, 9500),
('4902430716628', 'K04', 'S01', 'Downy mystique 230ml', 1, 10000, 0, 9500),
('4902430745703', 'K04', 'S06', 'downy sunrise fresh 11ml', 9999, 500, 0, 250),
('4902430803380', 'K04', 'S06', 'downy floral pink 11ml', 9999, 500, 0, 250),
('4902430821827', 'K04', 'S06', 'downy sport fresh 10ml', 9999, 500, 0, 250),
('6901668053121', 'K01', 'S01', 'Oreo Vanilla @pcs 68gr', 4, 4500, 0, 3280),
('711844110113', 'K20', 'S02', 'Kecap ABC 135ml', 4, 7000, 0, 6500),
('711844110182', 'K20', 'S01', 'Kecap ABC 65ml', 48, 2000, 0, 1750),
('76164217', 'K06', 'S01', 'Marlboro red 20btng', 9999, 25000, 0, 23375),
('7622210437686', 'K01', 'S01', 'Biskuat cokelat @pcs 10gr', 20, 500, 0, 425),
('7622210580276', 'K01', 'S01', 'Oreo Cokelat @pcs 68gr', 4, 4500, 0, 3280),
('7622210786722', 'K01', 'S01', 'Biskuat original @pcs 60gr', 4, 3000, 0, 2400),
('7622210786739', 'K01', 'S01', 'Biskuat cokelat @pcs 60gr', 4, 3000, 0, 2400),
('7622210828163', 'K01', 'S01', 'Biskuat sandwich cokelat @pcs 118gr', 2, 6500, 0, 5520),
('7622210946553', 'K01', 'S01', 'Biskuat sandwich cokelat @pcs 27gr', 9, 1500, 0, 1230),
('7622300136055', 'K01', 'S01', 'oreo strawberry creme 137gr', 9999, 6500, 0, 6000),
('7622300405588', 'K01', 'S01', 'Kejucake @pcs 16gr', 12, 1500, 0, 1290),
('88820309', 'K13', 'S01', 'Cussons baby 150gr', 9999, 7000, 0, 6500),
('8886020033431', 'K19', 'S01', 'kapur ajaib (3,5gr x 2)', 9999, 3000, 0, 2500),
('8886055200228', 'K01', 'S01', 'Duplex original flavour 32gr', 9999, 2000, 0, 1750),
('8886057883665', 'K09', 'S02', 'Kratingdeng 150ml', 9999, 5000, 0, 4250),
('8888103200013', 'K02', 'S01', 'Cussons baby blue @pcs 75gr', 12, 3500, 0, 2900),
('8888103201010', 'K13', 'S01', 'Cusson baby mild & gentle 75gr', 9999, 4000, 0, 3500),
('8888826019589', 'K14', 'S01', 'Gillete blue 2 plus', 9999, 7500, 0, 7000),
('8990090990409', 'K12', 'S01', 'mirabella chic 30gr', 9999, 5000, 0, 4500),
('8990168651195', 'K01', 'S01', 'Bj bawang merah goreng 50gr', 9999, 2500, 0, 2250),
('8991002109353', 'K01', 'S01', 'Freshco kopi bubuk 165gr', 9999, 9500, 0, 9000),
('8991002115446', 'K01', 'S06', 'Kapal Api signature 25gr', 9999, 2500, 0, 2000),
('8991002304017', 'K16', 'S01', 'Relaxa barley mint 125gr (50pcs)', 9999, 5000, 0, 4500),
('8991002306813', 'K16', 'S01', 'Espresso 135gr (50pcs)', 9999, 5000, 0, 4500),
('8991002504936', 'K01', 'S01', 'roll n roll milk chocolate 40gr', 9999, 2000, 0, 1500),
('8991002504943', 'K01', 'S01', 'roll n roll coockies n cream 40gr', 9999, 2000, 0, 1500),
('8991102101837', 'K02', 'S01', 'formula strong+SIKAT', 29, 9000, 0, 8500),
('8991102213189', 'K16', 'S01', 'Blaster choco ', 9999, 5000, 0, 4500),
('8991102281416', 'K16', 'S01', 'MintZ peppermint 115gr (50pcs)', 9999, 4500, 0, 4000),
('8991102281430', 'K16', 'S01', 'MintZ duomint 115gr (50pcs)', 9999, 4500, 0, 4000),
('8991102282222', 'K16', 'S01', 'Hot Kurang Asem 130gr', 9999, 4500, 0, 4000),
('8991102283373', 'K16', 'S01', 'MintZ Lemonmint 115gr (50pcs)', 9999, 4500, 0, 4000),
('8991102283380', 'K16', 'S01', 'MintZ cherrymint 115gr (50pcs)', 9999, 4500, 0, 4000),
('8991102308823', 'K01', 'S01', 'klop coconut milk 117gr', 9999, 5000, 0, 4500),
('8991102308892', 'K01', 'S01', 'fullo blasto 16g', 100, 1500, 0, 1100),
('8991102384841', 'K01', 'S01', 'Tango chocolate 130gr', 9999, 5000, 0, 4500),
('8991102387262', 'K01', 'S01', 'tango vanulla milk 52gr', 9999, 2000, 0, 1500),
('8991102387286', 'K01', 'S01', 'tango chocolate 47gr', 9999, 2000, 0, 1500),
('8991102987141', 'K01', 'S01', 'klop coconut milk 39gr', 9999, 2000, 0, 1500),
('8991102987738', 'K01', 'S01', 'tango strawberry jam 47gr', 9999, 2000, 0, 1500),
('8991102989282', 'K01', 'S01', 'tango bubblegum 47gr', 9999, 2000, 0, 1500),
('8991102991063', 'K16', 'S01', 'MintZ Zing 100gr (40pcs)', 9999, 4500, 0, 4000),
('8991102998628', 'K16', 'S01', 'Blaster Pop orange', 9999, 500, 0, 250),
('8991906101668', 'K06', 'S01', 'rokok 76 kretek 12btg', 9999, 13000, 0, 12500),
('8992003782354', 'K17', 'S06', 'Antangin JRG 15ml', 9999, 2500, 0, 2000),
('8992003784501', 'K17', 'S06', 'ziplong 12ml', 11, 2000, 0, 1650),
('8992628010139', 'K10', 'S07', 'Bimoli 5L ', 9999, 67000, 0, 65000),
('8992628020152', 'K10', 'S07', 'Bimoli 2L', 9999, 23000, 0, 21500),
('8992628022156', 'K10', 'S07', 'Bimoli 1L', 9999, 12500, 0, 11000),
('8992696404441', 'K07', 'S05', 'Bear Brand 189ml', 9999, 8500, 0, 8000),
('8992696405424', 'K07', 'S01', 'Dancow fortiGro 200gr', 9999, 21000, 0, 20000),
('8992702000063', 'K07', 'S05', 'Susu Indomilk coklat 370 gr', 9999, 9000, 0, 8500),
('8992702000179', 'K07', 'S05', 'Susu Kreamer 370gr', 9999, 8500, 0, 8000),
('8992702005976', 'K09', 'S02', 'indomilk stroberi 190ml', 9993, 3500, 0, 3000),
('8992702006003', 'K09', 'S02', 'indomilk melon 190ml', 9998, 3500, 0, 3000),
('8992705012100', 'K01', 'S06', 'Agar-agar satelit', 9999, 2000, 0, 1500),
('8992705032108', 'K01', 'S06', 'satelit chocolate 5gr', 9999, 2000, 0, 1500),
('8992705042107', 'K01', 'S06', 'satelit apple green 5gr', 9999, 2000, 0, 1500),
('8992716108816', 'K01', 'S01', 'Biskuat Original @pcs 140gr', 3, 11000, 0, 9733),
('8992716109189', 'K01', 'S01', 'Biskuat bolu @pcs ', 12, 1500, 0, 1290),
('8992716109462', 'K01', 'S01', 'Biskuat bolu pandan @pcs 16gr', 12, 1500, 0, 1290),
('8992716109561', 'K01', 'S01', 'Biskuat Cokelat @pcs 140gr', 2, 9000, 0, 8162),
('8992717781025', 'K01', 'S01', 'santen kara 65 ml', 80, 2500, 0, 2333),
('8992727000048', 'K11', 'S01', 'laurier', 9999, 3500, 0, 3000),
('8992736010168', 'K01', 'S06', 'Sasa pisang goreng vanilla ', 9999, 2500, 0, 2000),
('8992736290409', 'K01', 'S06', 'Sasa bakwan spesial 100gr', 9999, 2500, 0, 2000),
('8992736945132', 'K01', 'S06', 'Sasa Original 40gr', 9999, 1000, 0, 900),
('8992736980164', 'K01', 'S06', 'Sasa Kentucky ayam krispi 80gr', 9999, 2500, 0, 2000),
('8992745540359', 'K02', 'S01', 'Proclin pemutih 30ml', 100, 500, 0, 420),
('8992747180126', 'K14', 'S02', 'Vixal 500ml', 9999, 12500, 0, 12000),
('8992747180225', 'K14', 'S02', 'Vixal 800ml', 9999, 15000, 0, 14500),
('8992760221028', 'K01', 'S01', 'Oreo coklat vanilla', 9999, 6500, 0, 6000),
('8992765101004', 'K12', 'S08', 'Gillete goal', 9999, 3000, 0, 2500),
('8992765301008', 'K14', 'S01', 'Gillete goal ', 9999, 6500, 0, 6000),
('8992770011091', 'K01', 'S03', 'ajino moto   250g', 180, 9000, 0, 8500),
('8992770011152', 'K01', 'S06', 'Ajinomoto 24gr', 9999, 1000, 0, 500),
('8992770011169', 'K01', 'S06', 'Ajinomoto 120gr', 9999, 5000, 0, 4500),
('8992770061058', 'K01', 'S06', 'sajiku golden crispy', 999, 2500, 0, 2000),
('8992770094117', 'K01', 'S06', 'Saori Saus tiram 23ml', 9999, 2500, 0, 2250),
('8992772195058', 'K04', 'S02', 'Khispray 318ml', 9999, 12000, 0, 11000),
('8992772401012', 'K17', 'S06', 'vegeta herbal anggur 5gr', 9999, 2600, 0, 2000),
('8992779269202', 'K14', 'S06', 'Kit motor multi guna RT 25ml', 9999, 1500, 0, 1000),
('8992796022101', 'K12', 'S02', 'hand body lotion 100ml', 9999, 3500, 0, 3166),
('8992929751014', 'K04', 'S02', 'Kris feminime perfumed 100ml', 9999, 3200, 0, 3000),
('8992929751090', 'K12', 'S01', 'Kris casual 100ml', 9999, 3200, 0, 3000),
('8992933320114', 'K01', 'S06', 'nutrijell bluebarry 10gr', 9999, 2200, 0, 1800),
('8992933322118', 'K01', 'S06', 'nutrijell jeruk 10gr', 9999, 2200, 0, 1800),
('8992933324112', 'K01', 'S06', 'nutrijell coklat 20gr', 9999, 2500, 0, 2000),
('8992933327113', 'K01', 'S06', 'nutrijell jambu biji 10gr', 9999, 2200, 0, 1800),
('8992933329117', 'K01', 'S06', 'nutrijell cincau 10gr', 9999, 2200, 0, 2000),
('8992933411119', 'K01', 'S06', 'nutrijell kelapa muda 10gr', 9999, 2000, 0, 1500),
('8992946512285', 'K02', 'S01', 'Shinzui kirei @pcs 85gr', 3, 3500, 0, 3200),
('8992946512629', 'K10', 'S01', 'Hemart refill 2L', 9999, 22000, 0, 21000),
('8992946521133', 'K02', 'S01', 'Active @pcs 80gr', 2, 2000, 0, 1750),
('8992946521416', 'K02', 'S01', 'Shinzui myori @pcs 85gr', 2, 3500, 0, 3200),
('8992946521836', 'K02', 'S01', 'Shinzui kensho @pcs 87gr', 3, 3500, 0, 3200),
('8992946522062', 'K10', 'S01', 'tropical 500ml', 9999, 6500, 0, 6000),
('8992946530357', 'K10', 'S02', 'hemart 1L', 9999, 11000, 0, 10000),
('8992946531538', 'K02', 'S01', 'Zen @pcs 105gr', 11, 3000, 0, 2700),
('8992959107539', 'K11', 'S01', 'Softex comfort slim', 9999, 4500, 0, 4000),
('8992959953020', 'K29', 'S01', 'sweety pants M 1', 9999, 2200, 0, 1800),
('8993005123015', 'K22', 'S02', 'caladine lotion 60ml', 9999, 12500, 0, 12000),
('8993007000239', 'K09', 'S01', 'indomilk kids coklat 115ml', 9999, 2500, 0, 2000),
('8993007000253', 'K09', 'S01', 'indomilk kids stroberi 115ml', 9999, 2500, 0, 2000),
('8993007001359', 'K07', 'S06', 'Indomilk 37gr', 9999, 1500, 0, 1250),
('8993007001557', 'K01', 'S06', 'Indomilk putih 37gr', 9999, 1500, 0, 1000),
('8993007001694', 'K01', 'S01', 'Indomilk putih PAK', 9999, 7000, 0, 6500),
('8993007001700', 'K07', 'S06', 'indomilk coklat 6pcs', 9999, 7000, 0, 6500),
('8993007002936', 'K07', 'S05', 'Susu Kreamer 500gr', 9999, 10000, 0, 9000),
('8993007002967', 'K07', 'S05', 'Indofood iga sapi 500gr', 9999, 10000, 0, 9500),
('8993111112507', 'K01', 'S06', 'Garam cap kapal 250gr', 9999, 2000, 0, 1550),
('8993175537285', 'K01', 'S01', 'nabati cheese 145gr', 1, 5000, 0, 4500),
('8993175537346', 'K01', 'S01', 'Nabati chocolate 145gr', 9999, 5000, 0, 4500),
('8993175545006', 'K01', 'S01', 'nabati pink lava 20gr', 9999, 1000, 0, 750),
('8993176110074', 'K10', 'S02', 'Minyak kayu putih 60ml', 3, 22500, 0, 22000),
('8993176110081', 'K10', 'S02', 'Minyak kayu putih 30ml', 12, 11000, 0, 10500),
('8993176110098', 'K10', 'S02', 'Minyak kayu putih 15ml', 12, 6000, 0, 5270),
('8993176110104', 'K10', 'S01', 'gpu ', 9999, 18000, 0, 17000),
('8993176110111', 'K10', 'S02', 'gpu 30 ml', 500, 9000, 0, 8500),
('8993176110135', 'K10', 'S01', 'Telon Lang 30ml', 18, 9000, 0, 8500),
('8993176110142', 'K10', 'S02', 'minyak ganda pura', 30, 9000, 0, 8500),
('8993176120028', 'K22', 'S01', 'balsem lang 20gr', 9999, 9000, 0, 7791),
('8993176812022', 'K15', 'S01', 'geliga 20gr', 9996, 8500, 0, 8000),
('8993176812039', 'K15', 'S01', 'balsem geliga', 40, 5000, 0, 4250),
('8993189230059', 'K11', 'S01', 'Charm safe night 29cm 5pads', 9999, 4250, 0, 4000),
('8993189230103', 'K11', 'S01', 'Safe night 29cm 10pads', 9999, 8000, 0, 7500),
('8993189270284', 'K12', 'S01', 'charmextra maxi 8pads', 89, 4500, 0, 3999),
('8993189270291', 'K11', 'S01', 'Charm extra maxi 23cm 20pads', 9999, 5000, 0, 4500),
('8993189270338', 'K11', 'S01', 'Charm extra maxi wing 23cm 10pads ', 12, 6500, 0, 6000),
('8993189270635', 'K11', 'S01', 'Charm 29cm 8pads', 9999, 8000, 0, 7500),
('8993189270802', 'K11', 'S01', 'MammyPoko pants M9pcs 7~12kg', 3, 15500, 0, 15000),
('8993189320279', 'K11', 'S01', 'Charm safe night 35cm 6pads', 9999, 7000, 0, 6500),
('8993189320286', 'K11', 'S01', 'Charm Safe Night 35cm 12pads', 9999, 13000, 0, 12500),
('8993226365522', 'K01', 'S06', 'garam Gyuri 200gr', 9999, 2000, 0, 1500),
('8993335514293', 'K18', 'S01', 'oxyklin hygiene 800gr', 9999, 17500, 0, 17000),
('8993335518819', 'K02', 'S06', 'liquish jeruk nipis blj800', 56, 14500, 0, 13600),
('8993335519397', 'K18', 'S01', 'bukrim superklin 300gr', 9999, 3500, 0, 3000),
('8993335521543', 'K18', 'S01', 'bukrim superklin 300gr', 9999, 3500, 0, 3250),
('8993335525572', 'K04', 'S01', 'Almeera total 225ml', 3, 5000, 0, 4500),
('8993335525725', 'K02', 'S01', 'Almeera 230ml', 6, 5000, 0, 4500),
('8993335525732', 'K02', 'S01', 'Almeera 105ml', 5, 3000, 0, 2500),
('8993335526043', 'K18', 'S01', 'Nur deterjen bubuk 250gr', 9999, 3000, 0, 2500),
('8993338005033', 'K12', 'S08', 'koyo cabe @biji', 9999, 850, 0, 500),
('8993379200886', 'K02', 'S01', 'Harmony orange 70gr', 41, 1900, 0, 1500),
('8993379500313', 'K02', 'S01', 'Champion red @pcs ', 9, 1500, 0, 1250),
('8993379500320', 'K02', 'S01', 'Champion blue @pcs 60gr', 7, 1500, 0, 1250),
('8993417112270', 'K12', 'S02', 'Cologne Special Day 100ml', 9999, 12500, 0, 12000),
('8993417212116', 'K12', 'S02', 'Cologne Monday 50ml', 9999, 7500, 0, 7000),
('8993417212123', 'K12', 'S02', 'Cologne Gel tuesday 50ml', 9999, 7500, 0, 7000),
('8993417212147', 'K12', 'S02', 'Cologne thursday 50ml', 9999, 7500, 0, 7000),
('8993417212161', 'K12', 'S02', 'Colegne Saturday 50ml', 9999, 7500, 0, 7000),
('8993417212178', 'K12', 'S02', 'Cologne Sunday gel 50ml', 9999, 7500, 0, 7000),
('8993417212215', 'K12', 'S02', 'Cologne monday 100ml', 9999, 13000, 0, 12500),
('8993417212253', 'K12', 'S02', 'Cologne Gel friday 100ml', 9999, 12500, 0, 12000),
('8993417212260', 'K12', 'S01', 'Cologne Gel saturday 100ml', 9999, 12500, 0, 12000),
('8993417455414', 'K12', 'S12', 'samantha natural black', 89, 10000, 0, 9000),
('8993560024635', 'K02', 'S01', 'Dettol active 65g', 8, 3400, 0, 3200),
('8993560024642', 'K02', 'S01', 'Dettol active 105gr', 6, 5300, 0, 5000),
('8993560024987', 'K02', 'S01', 'Dettol original 105gr', 6, 5300, 0, 5000),
('8993560025007', 'K02', 'S01', 'Dettol cool @pcs 105gr', 6, 5300, 0, 5000),
('8993560025014', 'K02', 'S01', 'Dettol cool 65gr', 6, 3400, 0, 3200),
('8993560025038', 'K02', 'S01', 'dettol fresh 65gr', 5, 3400, 0, 3200),
('8993560025069', 'K02', 'S01', 'Dettol sensitive 105gr', 6, 5300, 0, 5000),
('8993560025083', 'K02', 'S01', 'Dettol skincare', 6, 5300, 0, 5000),
('8993560025090', 'K02', 'S01', 'Dettol skincare 65gr', 5, 3400, 0, 3200),
('8993560025397', 'K02', 'S01', 'Dettol deep cleanse 105 gr', 5, 5300, 0, 5000),
('8993560025410', 'K02', 'S01', 'Dettol deep cleanse 65gr', 8, 3400, 0, 3200),
('8993560033293', 'K04', 'S01', 'Vanish cair 150ml', 12, 7500, 0, 7000),
('8993560033477', 'K04', 'S01', 'Vanish cair 60ml', 22, 3000, 0, 2500),
('8994002150479', 'K01', 'S01', 'Teh sepeda balap kecil ', 9999, 1500, 0, 1000),
('8994021000021', 'K01', 'S06', 'Lumba-lumba agar chocolate 5gr', 9999, 2000, 0, 1500),
('8994021000045', 'K01', 'S06', 'lumba-lumba agar seaweed 5gr', 9999, 2000, 0, 1500),
('8994021123454', 'K01', 'S06', 'kijang mas chocolate', 9999, 2000, 0, 1500),
('8994170101570', 'K16', 'S01', 'Pindi susu 115gr (50butir)', 9999, 5000, 0, 4500),
('8994286130570', 'K01', 'S01', 'Jasmine tea 25 teh celup 50gr', 9999, 6500, 0, 6000),
('8994326101409', 'K01', 'S02', 'pewarna makanan RNW ungu', 10, 2000, 0, 1500),
('8995205301231', 'K12', 'S01', 'Cottonbuds', 9999, 2500, 0, 2000),
('8995227500230', 'K17', 'S05', 'cap kaki tiga anggur 320ml', 9999, 5500, 0, 5000),
('8995227500247', 'K17', 'S05', 'cap kaki tiga jambu biji 320ml', 9999, 5500, 0, 5000),
('8995227500261', 'K09', 'S02', 'cap kaki tiga 500ml ', 9999, 6500, 0, 6000),
('8995227500278', 'K17', 'S05', 'cap kaki tiga leci 320ml', 9999, 5500, 0, 5000),
('8995227500292', 'K17', 'S05', 'Cap kaki tiga melon 320ml', 9999, 5500, 0, 5000),
('8995227500308', 'K17', 'S05', 'cap kaki tiga jeruk 320ml', 9999, 5500, 0, 5000),
('8995227500582', 'K09', 'S05', 'cincau cap panda 310ml ', 9999, 4500, 0, 4000),
('8995227500995', 'K09', 'S05', 'cap kaki tiga anak leci 250ml', 9999, 4500, 0, 4000),
('8995227501008', 'K09', 'S05', 'Cap kaki tiga anak jeruk 250ml', 9999, 4500, 0, 4000),
('8995227501015', 'K17', 'S05', 'cap kaki tiga anak stroberi 250ml', 9999, 4500, 0, 4000),
('8995757501004', 'K21', 'S09', 'kertas kado', 9999, 1000, 0, 750),
('8995899250143', 'K01', 'S06', 'Boncabe lvl15 7gr', 9999, 1000, 0, 900),
('8995899250747', 'K01', 'S06', 'Boncabe lvl15 2,5gr', 9999, 500, 0, 400),
('8996168442184', 'K20', 'S02', 'Kecap Sate 275ml', 1, 12500, 0, 11500),
('8996168442191', 'K20', 'S01', 'Kecap sate @pcs 525ml', 7, 16500, 0, 15000),
('8996200900092', 'K13', 'S01', 'Herocyn 85gr', 9999, 11500, 0, 10000),
('8997004700017', 'K01', 'S06', 'junior santan bubuk 25gr', 9999, 1800, 0, 1500),
('8997009510017', 'K01', 'S02', 'C 1000 Lemon 140ml', 9999, 5500, 0, 5000),
('8997015660140', 'K10', 'S01', 'Minya Goreng LIZA 450ml', 9999, 5500, 0, 5000),
('8997015660348', 'K10', 'S01', 'primaco 450ml', 9999, 5500, 0, 5000),
('8997015660393', 'K10', 'S02', 'liza 900ml', 9999, 10000, 0, 9166),
('8997015660409', 'K10', 'S02', 'liza 450ml', 9999, 5500, 0, 5000),
('8997016370314', 'K05', 'S01', 'miranda premium', 9999, 11000, 0, 10000),
('8997021870540', 'K15', 'S02', 'hot in cream 60gr', 9999, 11500, 0, 11000),
('8997021870908', 'K22', 'S02', 'hot in cream strong 60g', 10, 13000, 0, 12500),
('8997025911256', 'K01', 'S01', 'Hatari Strawberry 200gr', 9999, 4500, 0, 4000),
('8997025911706', 'K01', 'S01', 'Hatari rasa margarin 260gr', 9999, 5000, 0, 4500),
('8997028630079', 'K01', 'S01', 'Antaka balado 100gr', 9999, 5000, 0, 4500),
('8997035563414', 'K09', 'S02', 'Pocari Sweat 500ml', 3, 6500, 0, 6000),
('8997035563544', 'K09', 'S02', 'Pocari Sweat 350ml', 6, 5500, 0, 5000),
('8997208800858', 'K01', 'S01', 'Kacang atom putri panda', 9998, 2000, 0, 1500),
('8997208800865', 'K01', 'S01', 'Kacang atom putri panda 155gr', 9999, 5000, 0, 4500),
('8997212800011', 'K09', 'S02', 'Yuzu tea 350ml', 9999, 3500, 0, 3000),
('8997212800028', 'K09', 'S02', 'Yuzu Green tea 350ml', 9999, 3500, 0, 3000),
('8997215890040', 'K01', 'S01', 'krupuk singkon pedas panda 500gr', 9999, 7000, 0, 6500),
('8998008152109', 'K01', 'S06', 'miwon bio 500 an', 9999, 500, 0, 350),
('8998103000534', 'K02', 'S01', 'Cusson baby red @pcs 75gr', 10, 3500, 0, 2900),
('8998103000565', 'K13', 'S01', 'Cusson Soft & Smooth', 9999, 4000, 0, 3500),
('8998103011806', 'K13', 'S01', 'Cusson Baby Fresh & Nourish 75gr', 9999, 4000, 0, 3500),
('89981108', 'K06', 'S01', 'Lucky Strike 20btng', 9999, 20000, 0, 19000),
('8998127514123', 'K06', 'S01', 'Dunhill 20btng', 9999, 21500, 0, 20000),
('8998389112211', 'K01', 'S01', 'kokola orange 200gr', 9999, 5000, 0, 4500),
('8998389112228', 'K01', 'S01', 'BonBon coklat 200gr', 9999, 5000, 0, 4500),
('8998389112433', 'K01', 'S01', 'Kokola Strawberry 200gr', 10, 5000, 0, 4500),
('8998866101509', 'K12', 'S02', 'Emeron sweet mangir 100ml', 9999, 3500, 0, 3000),
('8998866104371', 'K12', 'S02', 'emeron sweet avocado 100ml', 9999, 3500, 0, 3000),
('8998866105491', 'K12', 'S02', 'emeron lovelu jeju orange 120ml', 9999, 5000, 0, 4500),
('8998866105507', 'K12', 'S02', 'emeron jojoba oil 120ml', 9999, 5000, 0, 4500),
('8998866106306', 'K12', 'S02', 'emeron red pomegranate 120ml', 9999, 5000, 0, 4500),
('8998866181068', 'K03', 'S01', 'Ciptadent fresh mint 75gr', 17, 3500, 0, 2800),
('8998866200301', 'K08', 'S01', 'Mie sedap goreng 90gr', 9999, 2400, 0, 2000),
('8998866200325', 'K08', 'S01', 'mie sedap soto 75gr', 9999, 2250, 0, 2000),
('8998866200646', 'K01', 'S01', 'Top robusta bubuk 165gr', 9999, 10000, 0, 9500),
('8998866600095', 'K02', 'S06', 'ekonomi lemon el 500k', 9999, 2000, 0, 1750),
('8998866600569', 'K04', 'S01', 'So klin apel 400ml', 1, 5500, 0, 5000),
('8998866605809', 'K18', 'S01', 'so klin smart white 800gr', 9999, 18500, 0, 18000),
('8998866605816', 'K18', 'S01', 'so klin smart color 800gr ', 9999, 18500, 0, 18000),
('8998866607872', 'K02', 'S06', 'ekonomi putih EP 500K', 9999, 2000, 0, 1750),
('8998866608039', 'K20', 'S01', 'Kecap Sedap 70ml', 32, 2000, 0, 1750),
('8998866608084', 'K20', 'S01', 'Kecap Sedap 600ml', 8, 18500, 0, 17500),
('8998866608602', 'K02', 'S01', 'Giv white 76gr', 36, 1660, 0, 1500),
('8998866608824', 'K20', 'S02', 'Kecap Sedap 720ml', 5, 24000, 0, 23000),
('8998866608978', 'K14', 'S06', 'so klin liquid antibacterial 55ml', 9999, 1000, 0, 750),
('8998866610070', 'K18', 'S01', 'so klin aroma sensory 280gr', 9999, 5000, 0, 4500),
('8998866610261', 'K14', 'S01', 'daia violet 305gr', 9999, 5000, 0, 4000),
('8998866610438', 'K18', 'S01', 'So klin fresh protection 800gr', 9999, 15000, 0, 14500),
('8998866610452', 'K18', 'S06', 'So klin Aroma sensory happy 50gr', 9999, 1000, 0, 750),
('8998866611121', 'K18', 'S06', 'ekonomi ungu EV500K', 9999, 2000, 0, 1750),
('8998866611138', 'K02', 'S06', 'ekonomi ev350k', 9999, 1000, 0, 850),
('8998866800716', 'K02', 'S06', ' EKONOMI PUTIH 200K', 9999, 500, 0, 350),
('8998866803700', 'K18', 'S06', 'so klin aroma sensory 52gr', 9999, 1000, 0, 750),
('8998866803731', 'K04', 'S01', 'Super sol 60ml', 16, 1000, 0, 750),
('8998866808729', 'K04', 'S06', 'royale starry night 15ml', 9999, 500, 0, 250),
('8998866809245', 'K04', 'S01', 'So klin lantai 80ml', 28, 1000, 0, 750),
('8998899001623', 'K19', 'S12', 'baygon max 10 jam', 88, 4000, 0, 3750),
('8998899013015', 'K14', 'S02', 'bayclin 5in1 100ml', 9999, 27500, 0, 27000),
('8998989100120', 'K06', 'S01', 'Gudang garam international 12btng', 9999, 16500, 0, 16000),
('8998989110129', 'K06', 'S01', 'Surya 12btng', 9999, 16000, 0, 15800),
('8998989300391', 'K06', 'S01', 'Surya Pro fessional', 9999, 22000, 0, 21600),
('8999042646999', 'K18', 'S06', 'Rinso advance + molto 42ml', 9999, 1000, 0, 750),
('8999908010605', 'K12', 'S01', 'Marina bright fresh 100ml', 9999, 5000, 0, 4500),
('8999908034205', 'K09', 'S02', 'Hemaviton 150ml', 9999, 4500, 0, 4000),
('8999908045607', 'K12', 'S01', 'Marina nutri fresh 100ml', 9999, 4500, 0, 4000),
('8999908045706', 'K12', 'S02', 'marina nutri fresh 200ml', 9999, 7500, 0, 7000),
('8999908060709', 'K12', 'S02', 'Marina natural almond 100ml', 9999, 5000, 0, 4500),
('8999908061102', 'K12', 'S02', 'Marina smooth & glow 200ml', 9999, 7500, 0, 7000),
('8999908214706', 'K12', 'S02', 'marina rich moisturizing 100ml', 9999, 4500, 0, 4000),
('8999988888972', 'K17', 'S02', 'larutan cap badak 500ml', 999, 6500, 0, 6000),
('8999999008505', 'K03', 'S01', 'Pepsodent sensitive expert', 3, 22000, 0, 21000),
('8999999012625', 'K20', 'S01', 'Kecap bango @pcs 220ml', 5, 10500, 0, 9500),
('8999999031107', 'K02', 'S01', 'Lux lily fresh 80gr', 13, 3500, 0, 3000),
('8999999036348', 'K02', 'S02', 'Sunlight jeruk nipis 400ml', 2, 12500, 0, 12000),
('8999999036607', 'K02', 'S01', 'Lux soft rose 80gr', 18, 3500, 0, 3000),
('8999999036638', 'K02', 'S01', 'Lux velvet jasmine 80gr', 16, 3000, 0, 2500),
('8999999036676', 'K02', 'S01', 'Lux aqua delight 80gr', 18, 3500, 0, 3000),
('8999999036690', 'K02', 'S01', 'Lux magical spell 80gr', 26, 3500, 0, 3000),
('8999999042639', 'K18', 'S06', 'Rinso calssic fresh 42ml', 9999, 1000, 0, 750),
('8999999047221', 'K18', 'S06', 'Rinso classic fresh 46gr', 9999, 1000, 0, 750),
('8999999047245 ', 'K18', 'S06', 'Rinso rose fresh molto 46gr', 9999, 1000, 0, 750),
('8999999048167', 'K14', 'S06', 'sunsilk black shine 10ml', 9999, 500, 0, 250),
('8999999048280', 'K02', 'S01', 'sunsilk hijab recharge', 999, 1000, 0, 777),
('8999999049034', 'K02', 'S01', 'Sunlight jeruk nipis 50ml', 5, 1000, 0, 750),
('8999999050009', 'K02', 'S01', 'Sunlight jeruk nipis 105ml', 42, 2000, 0, 1500),
('8999999057695', 'K02', 'S01', 'lux magical spell 60ml', 9999, 3000, 0, 2500),
('8999999059309', 'K02', 'S01', 'Lifebuoy red @pcs 80gr', 18, 3000, 0, 2750),
('8999999059316', 'K02', 'S01', 'Lifebuoy mild care 80gr', 23, 3000, 0, 2500),
('8999999059323', 'K02', 'S01', 'Lifebuoy lemont fresh 80gr', 16, 3000, 0, 2500),
('8999999059330', 'K02', 'S01', 'Lfiebuoy nature pure 80gr', 3, 3000, 0, 2500),
('8999999059781', 'K02', 'S01', 'Sunlight jeruk nipis 220ml', 14, 5000, 0, 4500),
('8999999100506', 'K20', 'S01', 'Kecap bango @pcs 575ml', 8, 22500, 0, 21000),
('8999999195649', 'K01', 'S12', 'sariwangi', 50, 5000, 0, 4500),
('8999999390198', 'K02', 'S01', 'Sunlight jeruk nipis 900ml', 3, 15000, 0, 14000),
('8999999400903', 'K04', 'S01', 'Molto pewangi 450ml', 2, 6000, 0, 5500),
('8999999400958', 'K04', 'S01', 'Molto pewangi blue 900ml', 3, 10500, 0, 10000),
('8999999401023', 'K04', 'S01', 'Molto pengawi red 900ml', 1, 10500, 0, 10000),
('8999999401238', 'K14', 'S01', 'rinso classic fresh 800gr', 9999, 17500, 0, 16500),
('8999999500399', 'K02', 'S01', 'Lux camellia white 80gr', 15, 3000, 0, 2500),
('8999999500672', 'K14', 'S02', 'Vixal 200ml', 9999, 5000, 0, 4500),
('8999999500917', 'K14', 'S06', 'sunslik soft smooth 5ml', 9999, 500, 0, 250),
('8999999514006', 'K20', 'S01', 'Kecap bango @pcs 60ml', 2, 3000, 0, 2000),
('8999999520151', 'K23', 'S01', 'pepsodent family ', 9999, 2500, 0, 2000),
('8999999521004', 'K04', 'S01', 'Super pell aple 111ml', 2, 2000, 0, 1500),
('8999999521011', 'K04', 'S01', 'Super pell lemon 111ml', 31, 2000, 0, 1500),
('8999999524722', 'K02', 'S01', 'Sunlight jeruk nipis 455ml', 2, 10000, 0, 9000),
('8999999526856', 'K04', 'S06', 'molto all in one 11ml', 9999, 500, 0, 250),
('8999999526863', 'K04', 'S06', 'molto all in one 11ml', 9999, 500, 0, 250),
('8999999528850', 'K12', 'S01', 'Citra Natural Glowing 60ml', 9999, 6000, 0, 5500),
('8999999529291', 'K04', 'S06', 'molto pure 10ml', 9999, 500, 0, 250),
('8999999530662', 'K02', 'S01', 'lifebuoy matcha', 12, 3000, 0, 2800),
('8999999531478', 'K04', 'S06', 'molto bunga prancis 10ml', 9999, 500, 0, 250),
('8999999531485', 'K04', 'S06', 'molto bunga prancis 10ml', 9999, 500, 0, 250),
('8999999533304', 'K26', 'S06', 'dove jumbo sachet 20ml', 9999, 2000, 0, 1500),
('8999999533717', 'K02', 'S01', 'Citra hijau @pcs 70gr', 8, 2250, 0, 1900),
('8999999533724', 'K02', 'S01', 'Citra Pearly White 70gr', 22, 2250, 0, 1900),
('8999999533731', 'K02', 'S01', 'Citra bengkoang @pcs 70gr', 48, 2250, 0, 1900),
('8999999534097', 'K03', 'S01', 'Pepsodent cool mint 75gr', 5, 3500, 0, 3000),
('8999999706081', 'K03', 'S01', 'Pepsodent 75gr', 8, 4000, 0, 3750),
('8999999706111', 'K03', 'S01', 'Pepsodent 25gr', 26, 2000, 0, 1800),
('8999999706173', 'K03', 'S01', 'Pepsoden 120gr', 6, 6500, 0, 6000),
('8999999706180', 'K03', 'S01', 'Pepsodent 190gr', 7, 10500, 0, 9750),
('8999999707835', 'K03', 'S01', 'closeup ever fresh 65ml', 9999, 6500, 0, 6000),
('8999999707859', 'K03', 'S01', 'Close up menthol fresh 160gr', 2, 13500, 0, 13000),
('8999999710866', 'K03', 'S01', 'Pepsodent action 123 herbal 190gr ', 1, 16000, 0, 15000),
('8999999710880', 'K03', 'S01', 'Pepsoden action 123 herbal 75gr', 7, 6500, 0, 5500),
('905200871125', 'K15', 'S01', 'Sriti cream antiseptic 10gr', 11, 7000, 0, 6500),
('azza001', 'K09', 'S11', 'azza 45 x 120ml', 9999, 13000, 0, 12000),
('b000001', 'K01', 'S10', 'beras gading mas 5kg ', 9999, 52500, 0, 50000),
('b000002', 'K01', 'S10', 'beras gading mas 25kg', 9999, 252500, 0, 247500),
('bawang00001', 'K01', 'S01', 'bawang putih 1kg', 9999, 16000, 0, 12500),
('bawang00002', 'K01', 'S01', 'bawang putih 1/2kg', 9999, 8000, 0, 7000),
('bawang00003', 'K01', 'S01', 'bawang putih 1/4kg', 9999, 4000, 0, 3000),
('bawang00005', 'K01', 'S01', 'bawang putih 1ons', 9999, 1600, 0, 1000),
('beras004', 'K01', 'S10', 'beras gading mas 10kg', 9999, 103000, 0, 100000),
('beras010001', 'K01', 'S10', 'beras panda 25kg', 9999, 237500, 0, 231250),
('beras010002', 'K01', 'S01', 'beras panda 1kg', 9999, 9800, 0, 9000),
('beras010003', 'K01', 'S10', 'beras panda 10kg', 9999, 97000, 0, 90000),
('beras010004', 'K01', 'S10', 'beras panda 5kg ', 9999, 48500, 0, 47000),
('beras020002', 'K01', 'S01', 'beras garuda 1kg', 9999, 9800, 0, 9500),
('beras020003', 'K01', 'S10', 'beras garuda 25kg', 9999, 240000, 0, 235000),
('BT001', 'K01', 'S01', 'kacang oven BT', 9999, 9000, 0, 8000),
('cakra001', 'K01', 'S01', 'cakra 1kg', 9999, 8000, 0, 7500),
('cupkue01', 'K21', 'S01', 'cup kue olivine', 999, 1000, 0, 900),
('curah001', 'K10', 'S01', 'minyak curah 1/2kg', 9999, 5000, 0, 4500),
('curah002', 'K10', 'S01', 'minyak curah 1kg', 9999, 10000, 0, 9000),
('endog001', 'K01', 'S01', 'endog 1kg', 9999, 21000, 0, 20000),
('endog002', 'K01', 'S01', 'endog 1/2kg', 9999, 10500, 0, 10000),
('endog0025', 'K01', 'S01', 'endog 1/4kg', 9999, 5500, 0, 5000),
('fitri001', 'K10', 'S01', 'fitri 900ml', 9999, 10500, 0, 9500),
('garam0002', 'K01', 'S04', 'garam 2kg', 9999, 4000, 0, 2000),
('garam0003', 'K01', 'S04', 'garam kasar 1kg', 9999, 1800, 0, 1500),
('garam001', 'K01', 'S04', 'garam 5kg', 9999, 9000, 0, 5000),
('gula0001', 'K01', 'S01', 'gulo 1/2kg', 9999, 5000, 0, 4500),
('gula0002', 'K01', 'S01', 'gula 1kg', 9999, 10000, 0, 9000),
('jagung0001', 'K01', 'S01', 'jagung oc 1kg', 9999, 6000, 0, 5500),
('kacang00001', 'K01', 'S01', 'kacang goreng 1ons', 9999, 3000, 0, 2500),
('kakaptengiri001', 'K28', 'S01', 'tengiri dua bersaudara ', 9999, 3000, 0, 2500),
('kanji 1 kg', 'K01', 'S04', 'kanji 1 kg', 10000, 9500, 0, 9000),
('kanji, lose, rose 500kg', 'K01', 'S04', 'kanji, lose, rose 500kg', 10000, 5000, 0, 4500),
('kanji, lose, rose, 1/4kg', 'K01', 'S04', 'kanji, lose, rose, 1/4kg', 1000, 2500, 0, 2000),
('karunia001', 'K01', 'S01', 'karunia bolu special', 9999, 10000, 0, 9000),
('kemiri000004', 'K01', 'S01', 'kemiri 1/2ons', 9999, 2250, 0, 2000),
('kemiri0001', 'K01', 'S01', 'kemiri 1/4kg', 9999, 11250, 0, 10000),
('kemiri0002', 'K01', 'S01', 'kemiri 1/2kg', 9999, 22500, 0, 20000),
('kemiri0003', 'K01', 'S01', 'kemiri 1ons', 9999, 4500, 0, 4000),
('kemiri001', 'K01', 'S01', 'kemiri 1kg', 9999, 45000, 0, 40000),
('korek001', 'K21', 'S01', 'korek sinar 1pcs', 9999, 250, 0, 200),
('liza0001', 'K10', 'S11', 'liza kardus @450ml', 9999, 115000, 0, 112500),
('miepanda001', 'K08', 'S01', 'mie beruang panda bekas 333gr', 9999, 4000, 0, 3000),
('pewarna', 'K01', 'S02', 'srigunting merah tua 15 ml', 50, 2000, 0, 1750),
('pewarna 01', 'K01', 'S02', 'srigunting hijau apel 15ml', 9, 2000, 0, 1800),
('pewarna 03', 'K01', 'S02', ' srigunting kuning telur', 5, 2000, 0, 1750),
('pewarna 05', 'K01', 'S02', 'cendrawasih merah tua 15 ml', 3, 2500, 0, 2000),
('pewarna merah muda o2', 'K01', 'S02', 'srigunting', 14, 2000, 0, 1750),
('remasil0001', 'K24', 'S08', 'remasil 1 butir', 9999, 500, 0, 250),
('sandal001', 'K25', 'S01', 'swallow ', 9999, 10000, 0, 9000),
('sterofom001', 'K21', 'S09', 'sterofom l03s', 9999, 500, 0, 250),
('terigu002', 'K01', 'S01', 'terigu canting 1/2', 9999, 3250, 0, 3000),
('terigu01', 'K01', 'S04', 'terigu canting 1kg', 9999, 6500, 0, 5500),
('terigu0101', 'K01', 'S04', 'terigu cakra 1/2kg', 9999, 4000, 0, 3500),
('vanir0001', 'K01', 'S04', 'vanir 1kg', 9999, 18000, 0, 16000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_pemasokan`
--

CREATE TABLE `detail_pemasokan` (
  `id_detail_pemasokan` int(11) NOT NULL,
  `id_pemasokan` char(8) NOT NULL,
  `id_barang` varchar(30) NOT NULL,
  `qty` smallint(5) NOT NULL,
  `total_hrg` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_transaksi`
--

CREATE TABLE `detail_transaksi` (
  `id_detail_transaksi` int(11) NOT NULL,
  `id_transaksi` char(10) NOT NULL,
  `id_barang` varchar(30) NOT NULL,
  `qty` smallint(5) NOT NULL,
  `total_hrg` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `detail_transaksi`
--

INSERT INTO `detail_transaksi` (`id_detail_transaksi`, `id_transaksi`, `id_barang`, `qty`, `total_hrg`) VALUES
(1, 'T000000001', '8992946521133', 1, 2000),
(2, 'T000000002', '089686021219', 3, 3750),
(3, 'T000000003', '8992702005976', 1, 7000),
(4, 'T000000004', '8992702006003', 1, 8000),
(5, 'T000000005', '8993176812022', 3, 25500),
(6, 'T000000006', '8992702005976', 5, 17500),
(7, 'T000000007', '8997208800858', 1, 2000),
(8, 'T000000007', '089686552010', 1, 1000),
(9, 'T000000008', '8991102101837', 1, 9000),
(10, 'T000000009', '905200871125', 1, 7000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `distributor`
--

CREATE TABLE `distributor` (
  `id_distributor` char(4) NOT NULL,
  `nm_distributor` varchar(50) NOT NULL,
  `no_hp` char(15) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `distributor`
--

INSERT INTO `distributor` (`id_distributor`, `nm_distributor`, `no_hp`, `alamat`) VALUES
('D001', 'test', 'test', 'test'),
('D002', 'testtest', 'test2', 'test2');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` char(3) NOT NULL,
  `nm_kategori` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nm_kategori`) VALUES
('K01', 'pangan'),
('K02', 'sabun'),
('K03', 'odol'),
('K04', 'pewangi'),
('K05', 'semir'),
('K06', 'Rokok'),
('K07', 'susu'),
('K08', 'mie'),
('K09', 'minuman'),
('K10', 'minyak'),
('K11', 'Pembalut'),
('K12', 'kosmetik'),
('K13', 'Bedak'),
('K14', 'pembersih'),
('K15', 'krim'),
('K16', 'permen'),
('K17', 'jamu'),
('K18', 'deterjen'),
('K19', 'insektisida'),
('K20', 'kecap'),
('K21', 'kertas'),
('K22', 'lotion'),
('K23', 'sikat'),
('K24', 'obat'),
('K25', 'sandal'),
('K26', 'sampo'),
('K27', 'kopi'),
('K28', 'kerupuk'),
('K29', 'pampes');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pemasokan`
--

CREATE TABLE `pemasokan` (
  `id_pemasokan` char(8) NOT NULL,
  `id_distributor` char(4) NOT NULL,
  `id_user` char(3) NOT NULL,
  `total_pasok` int(10) NOT NULL,
  `bayar` int(10) NOT NULL,
  `kembalian` int(10) NOT NULL,
  `tgl_pemasokan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `satuan`
--

CREATE TABLE `satuan` (
  `id_satuan` char(3) NOT NULL,
  `nm_satuan` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `satuan`
--

INSERT INTO `satuan` (`id_satuan`, `nm_satuan`) VALUES
('S01', 'pcs'),
('S02', 'botol'),
('S03', 'gr'),
('S04', 'kg'),
('S05', 'kaleng'),
('S06', 'sachet'),
('S07', 'liter'),
('S08', 'biji'),
('S09', 'lembar'),
('S10', 'glangsi'),
('S11', 'dos'),
('S12', 'kotak'),
('S13', 'ons');

-- --------------------------------------------------------

--
-- Struktur dari tabel `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` char(10) NOT NULL,
  `id_user` char(3) NOT NULL,
  `total_hrg` int(10) NOT NULL,
  `bayar` int(10) NOT NULL,
  `kembalian` int(10) NOT NULL,
  `tgl_transaksi` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_user`, `total_hrg`, `bayar`, `kembalian`, `tgl_transaksi`) VALUES
('T000000001', 'U01', 2000, 5000, 3000, '2019-02-01'),
('T000000002', 'U01', 3750, 10000, 6250, '2019-02-01'),
('T000000003', 'U06', 7000, 8000, 1000, '2019-02-02'),
('T000000004', 'U06', 8000, 10000, 2000, '2019-02-02'),
('T000000005', 'U06', 25500, 30000, 4500, '2019-02-02'),
('T000000006', 'U06', 17500, 30000, 12500, '2019-02-02'),
('T000000007', 'U06', 3000, 3000, 0, '2019-02-02'),
('T000000008', 'U06', 9000, 20000, 11000, '2019-02-02'),
('T000000009', 'U06', 7000, 20000, 13000, '2019-02-02');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id_user` char(3) NOT NULL,
  `nm_user` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `akses` enum('Admin','Kasir','Gudang') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id_user`, `nm_user`, `username`, `password`, `akses`) VALUES
('U01', 'kika', 'kika', 'kika', 'Admin'),
('U02', 'haha', 'haha', 'jaja', 'Kasir'),
('U03', 's', 's', 's', 'Gudang'),
('U04', 'kasir', 'kasir', 'kasir', 'Kasir'),
('U05', 'gudang', 'gudang', 'gudang', 'Gudang'),
('U06', 'sumber', 'sumber', 'sumber', 'Admin');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`);

--
-- Indeks untuk tabel `detail_pemasokan`
--
ALTER TABLE `detail_pemasokan`
  ADD PRIMARY KEY (`id_detail_pemasokan`);

--
-- Indeks untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  ADD PRIMARY KEY (`id_detail_transaksi`);

--
-- Indeks untuk tabel `distributor`
--
ALTER TABLE `distributor`
  ADD PRIMARY KEY (`id_distributor`);

--
-- Indeks untuk tabel `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indeks untuk tabel `pemasokan`
--
ALTER TABLE `pemasokan`
  ADD PRIMARY KEY (`id_pemasokan`);

--
-- Indeks untuk tabel `satuan`
--
ALTER TABLE `satuan`
  ADD PRIMARY KEY (`id_satuan`);

--
-- Indeks untuk tabel `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `detail_pemasokan`
--
ALTER TABLE `detail_pemasokan`
  MODIFY `id_detail_pemasokan` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `detail_transaksi`
--
ALTER TABLE `detail_transaksi`
  MODIFY `id_detail_transaksi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
