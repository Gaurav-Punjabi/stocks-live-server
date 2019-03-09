-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 09, 2019 at 09:18 PM
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `CallType`
--

INSERT INTO `CallType` (`id`, `name`) VALUES
(1, 'BUY'),
(2, 'SELL');

-- --------------------------------------------------------

--
-- Table structure for table `StockCall`
--

CREATE TABLE `StockCall` (
  `id` int(11) NOT NULL,
  `stockId` int(11) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stopLoss` double DEFAULT NULL,
  `target1` double DEFAULT NULL,
  `target2` double DEFAULT NULL,
  `target3` double DEFAULT NULL,
  `completed` tinyint(4) DEFAULT '0',
  `deleted` tinyint(4) DEFAULT '0',
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `createdBy` int(11) DEFAULT NULL,
  `updatedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `StockCall`
--

INSERT INTO `StockCall` (`id`, `stockId`, `typeId`, `price`, `stopLoss`, `target1`, `target2`, `target3`, `completed`, `deleted`, `createdAt`, `createdBy`, `updatedAt`) VALUES
(1, 1, 1, 12.12, 13.12, 12.3, 13.21, 0, 0, 0, '2019-01-06 03:31:26', NULL, NULL),
(2, 2, 1, 132.13, 13214, 13214, 13214, 13214, 0, 0, '2019-01-19 01:43:21', NULL, NULL),
(3, 2, 1, 132.13, 13214, 13214, 13214, 13214, 0, 0, '2019-01-19 01:51:04', NULL, NULL),
(4, 2, 1, 133.15, 231, 214, 1243, 1322, 0, 0, '2019-01-19 12:21:47', NULL, NULL),
(5, 2, 1, 133.15, 150, 160, 170, 190, 0, 0, '2019-01-19 19:27:22', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `StockInfo`
--

CREATE TABLE `StockInfo` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `StockInfo`
--

INSERT INTO `StockInfo` (`id`, `name`, `typeId`) VALUES
(1, 'Zinc', 1),
(2, 'ALUMINI', 1);

-- --------------------------------------------------------

--
-- Table structure for table `StockType`
--

CREATE TABLE `StockType` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `StockType`
--

INSERT INTO `StockType` (`id`, `name`) VALUES
(1, 'Commodity');

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `id` int(11) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `StockInfo`
--
ALTER TABLE `StockInfo`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `StockInfo`
--
ALTER TABLE `StockInfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `StockType`
--
ALTER TABLE `StockType`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
