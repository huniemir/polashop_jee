-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 09, 2020 at 08:34 PM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `polashop`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `idproduct` int(11) NOT NULL,
  `name` varchar(45) COLLATE utf8_polish_ci NOT NULL,
  `producer` varchar(45) COLLATE utf8_polish_ci NOT NULL,
  `price` double NOT NULL,
  `description` text COLLATE utf8_polish_ci DEFAULT NULL,
  `image` varchar(20) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`idproduct`, `name`, `producer`, `price`, `description`, `image`) VALUES
(11, 'Usługa testowania aplikacji', 'test', 11.11, 'Testowanie aplikacji w okazyjnej cenie', NULL),
(12, 'Czapka z daszkiem', 'Czapex', 21.38, 'Idealny na słoneczne dni!', 'czapka.jpg'),
(13, 'Duży miś', 'Zoo z.o.o', 240, 'Nie trzeba go karmić, jest napchany', 'mis.jpg'),
(14, 'Mały miś', 'Zoo z.o.o', 241, 'Oo', 'malymis.jpg'),
(15, 'Czapka moro', 'Czapex', 8, 'Uwaga: Wrażliwa na duże temperatury podczas prania', 'czapkamoro.jpg'),
(16, 'Kwiatek Bratek', 'SewastoPol', 7.76, 'Idealny do domowego ogrodu', 'bratek.jpg'),
(17, 'Chryzantema', 'SewastoPol', 6.67, 'Dostępne kolory: złocisty', 'chryzantema.jpg'),
(18, 'Tulipan', 'SewastoPol', 7.76, 'Idealny do domowego ogrodu', 'tulipan.jpg'),
(19, 'Śrubokręt płaski', 'SrubkoPol', 4.49, NULL, 'plaski.jpg'),
(20, 'Śrubokręt krzyżak', 'SrubkoPol', 4.49, 'Wyprodukowany w Kaliningradzie', 'krzyzak.jpg'),
(21, 'Zielony grzebień', 'Chinololo', 1, 'Made in China', 'grzebien.jpg'),
(22, 'Młotek', 'Chinololo', 3.4, NULL, 'mlotek.jpg'),
(23, 'Inny młotek', 'Chinololo', 3.2, 'Do klepania kotletów', 'innymlotek.jpg'),
(24, 'Cukierki \"Michałki\"', 'Wawel', 5, 'Przepyszne', 'michalki.jpg'),
(25, 'Chałka', 'Babcinex', 3, 'Według różnych opini: takie średnie', 'chalka.jpg'),
(26, 'Domowy obiad', 'Babcinex', 30, 'Zestaw składa się z:\r\n- 7x Kluski\r\n- 2x Rolada śląska\r\n- Surówka kapuściano-ogórkowa\r\n- Sos z rolady', 'domowyobiad.jpg'),
(27, 'Szklanka', 'Chinololo', 2, NULL, 'szklanka.jpg'),
(28, 'Kubek', 'TwojaKawa', 5, 'Wypełniony kawą stanowi obowiązkowe wyposażenie aspirującego programisty', 'kubek.jpg'),
(29, 'Czapka uszatka', 'Czapex', 21.36, 'Z taką czapką, uszka nie zmarzną', 'czapkauszatka.jpg'),
(30, 'Okulary \"Lenonki\"', 'OkulMas', 30, NULL, 'lenonki.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `product_has_transaction`
--

CREATE TABLE `product_has_transaction` (
  `product_idproduct` int(11) NOT NULL,
  `transaction_idtransaction` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `product_has_transaction`
--

INSERT INTO `product_has_transaction` (`product_idproduct`, `transaction_idtransaction`, `amount`, `price`) VALUES
(11, 22, 1, 0),
(11, 28, 1, 0),
(11, 29, 2, 0),
(11, 30, 1, 0),
(11, 31, 1, 0),
(11, 32, 1, 0),
(11, 34, 1, 0),
(11, 35, 3, 0),
(11, 36, 3, 0),
(12, 29, 3, 0),
(12, 35, 3, 0),
(13, 29, 4, 0),
(22, 1, 1, 0),
(25, 1, 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `shipping_details`
--

CREATE TABLE `shipping_details` (
  `idshipping_details` int(11) NOT NULL,
  `telephone_number` int(9) DEFAULT NULL,
  `name` varchar(45) COLLATE utf8_polish_ci NOT NULL,
  `surname` varchar(45) COLLATE utf8_polish_ci NOT NULL,
  `town` varchar(45) COLLATE utf8_polish_ci NOT NULL,
  `street` varchar(45) COLLATE utf8_polish_ci NOT NULL,
  `flat` varchar(45) COLLATE utf8_polish_ci NOT NULL,
  `user_iduser` int(11) DEFAULT NULL,
  `zipcode` varchar(6) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `shipping_details`
--

INSERT INTO `shipping_details` (`idshipping_details`, `telephone_number`, `name`, `surname`, `town`, `street`, `flat`, `user_iduser`, `zipcode`) VALUES
(1, 777777777, 'Janusz', 'Zdradziecki', 'Katowice', 'Betonowa', '5', 2, '10-100'),
(11, NULL, 'Janusz', 'Kowalczyk', 'Tychy', 'Piłsudzki', '43', NULL, '43-100'),
(12, NULL, 'Janusz', 'Kowalczyk', 'Tychy', 'Piłsudzkiego', '43', 2, '43-100'),
(18, NULL, 'jou', 'jou', 'jou', 'jou', '12', NULL, '43-100'),
(19, NULL, 'adj', 'adj', 'dja', 'adj', '12', NULL, '43-100'),
(20, NULL, 'var', 'asd', 'asd', 'asd', '12', NULL, '43-100'),
(21, NULL, 'qwe', 'qwe', 'qwe', 'qwe', '12', NULL, '12-400'),
(22, NULL, 'zxc', 'zxc', 'zxc', 'zxc', '12', NULL, '43-100'),
(23, NULL, 'qwe', 'qwe', 'qwe', 'qwe', '12', NULL, '14-400'),
(24, NULL, 'qwe', 'qwe', 'qwe', 'qwe', '2', NULL, '13-400'),
(25, NULL, 'qwe', 'qwe', 'qwe', 'qwe', '14', NULL, '43-100'),
(26, NULL, 'kek', 'kek', 'kek', 'kek', '12', NULL, '13-400'),
(27, NULL, 'kek', 'kek', 'kek', 'kek', '12', NULL, '14-400'),
(28, NULL, 'kek', 'kek', 'kek', 'kek', '12', NULL, '13-400'),
(29, NULL, 'aaa', 'aaa', 'aaa', 'aaa', '12', NULL, '13-140'),
(30, NULL, 'aaa', 'aaa', 'qqq', 'aaa', '12', NULL, '13-567'),
(31, 232232232, 'Grażyna', 'Szczepanek', 'Szczecin', 'Krajowa', '13', NULL, '53-530'),
(32, NULL, 'kaka', 'kaka', 'kaka', 'kaka', '12', NULL, '13-130'),
(33, 444444444, 'pup', 'pup', 'pup', 'pup', '12', 4, '43-100'),
(34, 555555555, 'kek', 'kek', 'kek', 'kek', '12', NULL, '43-100');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `idtransaction` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `is_paid_for` tinyint(1) NOT NULL,
  `is_send` tinyint(1) NOT NULL,
  `shipping_details_idshipping_details` int(11) DEFAULT NULL,
  `user_iduser` int(11) DEFAULT NULL,
  `price` double NOT NULL,
  `shipping_costs` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`idtransaction`, `date`, `is_paid_for`, `is_send`, `shipping_details_idshipping_details`, `user_iduser`, `price`, `shipping_costs`) VALUES
(1, '2020-09-01', 1, 0, 1, 2, 29.4, 20),
(2, '2020-09-02', 0, 0, 1, 2, 0, 0),
(3, '2020-09-03', 1, 1, 1, 2, 0, 0),
(4, '2020-09-04', 1, 1, 1, 2, 0, 0),
(5, '2020-09-05', 1, 1, 1, 2, 0, 0),
(6, '2020-09-06', 1, 1, 1, 2, 0, 0),
(7, '2020-09-07', 1, 1, 1, 2, 0, 0),
(8, '2020-09-08', 1, 1, 1, 2, 0, 0),
(9, '2020-09-09', 1, 1, 1, 2, 0, 0),
(10, '2020-09-10', 1, 1, 1, 2, 0, 0),
(11, '2020-09-11', 1, 1, 1, 2, 0, 0),
(12, '2020-09-12', 1, 1, 1, 2, 0, 0),
(13, '2020-09-13', 1, 1, 1, 2, 0, 0),
(14, '2020-09-14', 1, 1, 1, 2, 0, 0),
(15, '2020-09-15', 1, 1, 1, 2, 0, 0),
(16, '2020-09-16', 1, 1, 1, 2, 0, 0),
(17, '2020-09-17', 1, 1, 1, 2, 0, 0),
(18, '2020-09-18', 1, 1, 1, 2, 0, 0),
(19, '2020-09-19', 1, 1, 1, 2, 0, 0),
(20, '2020-09-20', 1, 1, 1, 2, 0, 0),
(22, '2020-09-21', 0, 0, 21, NULL, 0, 20),
(23, '2020-09-21', 0, 0, 22, NULL, 0, 20),
(24, '2020-09-21', 0, 0, NULL, NULL, 0, 20),
(25, '2020-09-21', 0, 0, 27, NULL, 0, 20),
(26, '2020-09-21', 0, 0, 28, NULL, 0, 20),
(27, '2020-09-21', 0, 0, 29, NULL, 11, 20),
(28, '2020-09-21', 0, 0, 30, NULL, 11, 20),
(29, '2020-09-21', 0, 0, 31, NULL, 1045, 20),
(30, '2020-09-21', 0, 0, 1, NULL, 11, 20),
(31, '2020-09-21', 0, 0, 12, 2, 11, 20),
(32, '2020-09-21', 0, 0, 32, NULL, 11, 20),
(34, '2020-09-22', 0, 0, 1, 2, 11, 20),
(35, '2020-09-22', 0, 0, 33, 4, 96, 20),
(36, '2020-09-22', 0, 0, 34, NULL, 33, 20);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `iduser` int(11) NOT NULL,
  `login` varchar(45) COLLATE utf8_polish_ci NOT NULL,
  `password` varchar(45) COLLATE utf8_polish_ci NOT NULL,
  `email` varchar(45) COLLATE utf8_polish_ci NOT NULL,
  `role` varchar(20) COLLATE utf8_polish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`iduser`, `login`, `password`, `email`, `role`) VALUES
(1, 'admin', 'admin', 'admin@admin.pl', 'admin'),
(2, 'user', 'user', 'user@user.pl', 'user'),
(3, 'lel', 'lel', 'lel@lel.pl', 'user'),
(4, 'lelz', 'lelz', 'lelz@lelz.pl', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`idproduct`);

--
-- Indexes for table `product_has_transaction`
--
ALTER TABLE `product_has_transaction`
  ADD PRIMARY KEY (`product_idproduct`,`transaction_idtransaction`),
  ADD KEY `product_idproduct` (`product_idproduct`,`transaction_idtransaction`),
  ADD KEY `transaction_idtransaction` (`transaction_idtransaction`);

--
-- Indexes for table `shipping_details`
--
ALTER TABLE `shipping_details`
  ADD PRIMARY KEY (`idshipping_details`),
  ADD KEY `user_iduser` (`user_iduser`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`idtransaction`),
  ADD KEY `shipping-details_idshipping-details` (`shipping_details_idshipping_details`,`user_iduser`),
  ADD KEY `user_iduser` (`user_iduser`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`iduser`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `idproduct` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `shipping_details`
--
ALTER TABLE `shipping_details`
  MODIFY `idshipping_details` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `idtransaction` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product_has_transaction`
--
ALTER TABLE `product_has_transaction`
  ADD CONSTRAINT `product_has_transaction_ibfk_1` FOREIGN KEY (`transaction_idtransaction`) REFERENCES `transaction` (`idtransaction`),
  ADD CONSTRAINT `product_has_transaction_ibfk_2` FOREIGN KEY (`product_idproduct`) REFERENCES `product` (`idproduct`);

--
-- Constraints for table `shipping_details`
--
ALTER TABLE `shipping_details`
  ADD CONSTRAINT `shipping_details_ibfk_1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`);

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`),
  ADD CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`shipping_details_idshipping_details`) REFERENCES `shipping_details` (`idshipping_details`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
