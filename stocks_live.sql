-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 22, 2019 at 05:26 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 5.6.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `stocks_live`
--

-- --------------------------------------------------------

--
-- Table structure for table `CallType`
--

CREATE TABLE `CallType` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CallType`
--

INSERT INTO `CallType` (`id`, `name`) VALUES
(1, 'BUY'),
(2, 'SELL\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `StockCall`
--

CREATE TABLE `StockCall` (
  `id` int(11) NOT NULL,
  `completed` tinyint(4) DEFAULT NULL,
  `createdAt` datetime DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stopLoss` double DEFAULT NULL,
  `target1` double DEFAULT NULL,
  `target2` double DEFAULT NULL,
  `target3` double DEFAULT NULL,
  `TypeId` int(11) DEFAULT NULL,
  `StockId` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `StockCall`
--

INSERT INTO `StockCall` (`id`, `completed`, `createdAt`, `price`, `stopLoss`, `target1`, `target2`, `target3`, `TypeId`, `StockId`) VALUES
(1, 0, '2019-04-22 14:47:34', 31664, 350, 290, 290, 290, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `StockInfo`
--

CREATE TABLE `StockInfo` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `StockInfo`
--

INSERT INTO `StockInfo` (`id`, `name`, `typeId`) VALUES
(1, 'GOLD', 1);

-- --------------------------------------------------------

--
-- Table structure for table `StockType`
--

CREATE TABLE `StockType` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `StockType`
--

INSERT INTO `StockType` (`id`, `name`) VALUES
(1, 'COMMODITIES');

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `id` int(11) NOT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `is_admin` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `notification_token` varchar(255) DEFAULT NULL,
  `otp` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`id`, `access_token`, `email`, `is_admin`, `name`, `notification_token`, `otp`, `password`, `phone_number`, `refresh_token`, `deleted`) VALUES
(1, NULL, 'gaurav.bpunjabi@gmail.com', b'1', 'Gaurav Punjabi', NULL, NULL, 'f84f57034bece01d5491307e4f83343af414704d', '8828262618', NULL, 0),
(2, NULL, 'test@gmail.com', b'1', 'Test User', NULL, NULL, '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', '9320010500', NULL, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `CallType`
--
ALTER TABLE `CallType`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `StockCall`
--
ALTER TABLE `StockCall`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1m4l1mvahu5101as9ei27jrkx` (`TypeId`),
  ADD KEY `FKd4jwfj51tl4d8k4j8jgkpi9k3` (`StockId`);

--
-- Indexes for table `StockInfo`
--
ALTER TABLE `StockInfo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs8u9wtv523rplr2jtayt76je0` (`typeId`);

--
-- Indexes for table `StockType`
--
ALTER TABLE `StockType`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `CallType`
--
ALTER TABLE `CallType`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `StockCall`
--
ALTER TABLE `StockCall`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `StockInfo`
--
ALTER TABLE `StockInfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `StockType`
--
ALTER TABLE `StockType`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
