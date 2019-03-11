-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 11, 2019 at 11:55 AM
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
(1, 2, 1, 129.75, 132, 127, 130, 0, 0, 0, '2019-03-11 11:34:10', NULL, NULL),
(2, 3, 1, 450.9, 452, 440, 430, 0, 0, 0, '2019-03-11 11:35:55', NULL, NULL);

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
(2, 'ALUMINI', 1),
(3, 'COPPER', 1),
(4, 'CRUDEOIL', 1),
(5, 'GBPINR', 1),
(6, 'EURINR', 1);

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
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `access_token` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `access_time` datetime DEFAULT NULL,
  `refresh_time` datetime DEFAULT NULL,
  `is_admin` varchar(255) DEFAULT NULL,
  `deleted` tinyint(4) DEFAULT '0',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  `notification_token` varchar(255) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `otp` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`id`, `email`, `password`, `access_token`, `refresh_token`, `access_time`, `refresh_time`, `is_admin`, `deleted`, `created_at`, `updated_at`, `created_by`, `updated_by`, `notification_token`, `phone_number`, `otp`) VALUES
(1, 'gaurav@gmail.com', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', NULL, NULL, NULL, NULL, '1', 0, '2019-03-11 00:51:54', NULL, NULL, NULL, 'ExponentPushToken[OQzngxFCtzN7TuIACUVM6b]', '8828262618', '261542'),
(3, 'gaurav.bpunjabi@gmail.com', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8', NULL, NULL, NULL, NULL, '0', 0, '2019-03-11 15:25:17', NULL, NULL, NULL, 'ExponentPushToken[OQzngxFCtzN7TuIACUVM6b]', '9320010500', '302607');

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
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `phone_number` (`phone_number`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `StockInfo`
--
ALTER TABLE `StockInfo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `StockType`
--
ALTER TABLE `StockType`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
