-- phpMyAdmin SQL Dump
-- version 4.3.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 28, 2015 at 04:05 PM
-- Server version: 5.6.24
-- PHP Version: 5.6.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `codemefast`
--

-- --------------------------------------------------------

--
-- Table structure for table `achievements`
--

CREATE TABLE IF NOT EXISTS `achievements` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `xp` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `codesforsubmition`
--

CREATE TABLE IF NOT EXISTS `codesforsubmition` (
  `id` int(11) NOT NULL,
  `task` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `code` text COLLATE utf8_unicode_ci NOT NULL,
  `uploaddate` int(11) NOT NULL,
  `uploadmonth` int(11) NOT NULL,
  `uploadyear` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `codesforsubmition`
--

INSERT INTO `codesforsubmition` (`id`, `task`, `user`, `code`, `uploaddate`, `uploadmonth`, `uploadyear`) VALUES
(4, 2, 1, 'sampleCode', 28, 5, 2015);

-- --------------------------------------------------------

--
-- Table structure for table `messages`
--

CREATE TABLE IF NOT EXISTS `messages` (
  `id` int(11) NOT NULL,
  `sender` int(11) NOT NULL,
  `receiver` int(11) NOT NULL,
  `message` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `messages`
--

INSERT INTO `messages` (`id`, `sender`, `receiver`, `message`) VALUES
(1, 12, 1, 'Hello loooooooser!!!');

-- --------------------------------------------------------

--
-- Table structure for table `notifications`
--

CREATE TABLE IF NOT EXISTS `notifications` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `text` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `notifications`
--

INSERT INTO `notifications` (`id`, `user`, `text`) VALUES
(1, 1, 'Sample notification');

-- --------------------------------------------------------

--
-- Table structure for table `projects`
--

CREATE TABLE IF NOT EXISTS `projects` (
  `id` int(11) NOT NULL,
  `projectlevel` int(11) NOT NULL DEFAULT '1',
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  `type` int(11) NOT NULL DEFAULT '0',
  `admin` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `projects`
--

INSERT INTO `projects` (`id`, `projectlevel`, `name`, `description`, `type`, `admin`) VALUES
(1, 1, 'Just Another Project', 'Some brand new description', 0, 1),
(3, 1, 'SAS', 'SAAAAS', 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `projectsusersxp`
--

CREATE TABLE IF NOT EXISTS `projectsusersxp` (
  `id` int(11) NOT NULL,
  `xp` int(11) NOT NULL DEFAULT '0',
  `user` int(11) NOT NULL,
  `project` int(11) NOT NULL,
  `type` varchar(4) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `tasks`
--

CREATE TABLE IF NOT EXISTS `tasks` (
  `id` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  `project` int(11) NOT NULL,
  `type` varchar(4) COLLATE utf8_unicode_ci NOT NULL,
  `user` int(11) NOT NULL,
  `issidequest` int(11) NOT NULL DEFAULT '0',
  `difficulty` int(11) NOT NULL DEFAULT '-1',
  `finished` int(11) NOT NULL DEFAULT '0',
  `deadlinedate` int(11) NOT NULL,
  `deadlinemonth` int(11) NOT NULL,
  `deadlineyear` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `uixp` int(11) NOT NULL DEFAULT '0',
  `dbxp` int(11) NOT NULL DEFAULT '0',
  `serverxp` int(11) NOT NULL DEFAULT '0',
  `backendxp` int(11) NOT NULL DEFAULT '0',
  `avatar` int(11) NOT NULL DEFAULT '1',
  `level` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `uixp`, `dbxp`, `serverxp`, `backendxp`, `avatar`, `level`) VALUES
(1, 'pep4eto1211', 'sha1$b7fc081e$1$a97fd77166032f561d1a01a9947ab1ffea8e3d29', 0, 0, 0, 0, 1, 1),
(2, 'baigosho', 'asdfg', 0, 0, 0, 0, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `usersachievements`
--

CREATE TABLE IF NOT EXISTS `usersachievements` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `achievement` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `usersprojects`
--

CREATE TABLE IF NOT EXISTS `usersprojects` (
  `id` int(11) NOT NULL,
  `user` int(11) NOT NULL,
  `project` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `usersprojects`
--

INSERT INTO `usersprojects` (`id`, `user`, `project`) VALUES
(3, 1, 1),
(4, 1, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `achievements`
--
ALTER TABLE `achievements`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `codesforsubmition`
--
ALTER TABLE `codesforsubmition`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `messages`
--
ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notifications`
--
ALTER TABLE `notifications`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `projects`
--
ALTER TABLE `projects`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `projectsusersxp`
--
ALTER TABLE `projectsusersxp`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tasks`
--
ALTER TABLE `tasks`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`), ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `usersachievements`
--
ALTER TABLE `usersachievements`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `usersprojects`
--
ALTER TABLE `usersprojects`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `achievements`
--
ALTER TABLE `achievements`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `codesforsubmition`
--
ALTER TABLE `codesforsubmition`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `messages`
--
ALTER TABLE `messages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `notifications`
--
ALTER TABLE `notifications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `projects`
--
ALTER TABLE `projects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `projectsusersxp`
--
ALTER TABLE `projectsusersxp`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tasks`
--
ALTER TABLE `tasks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `usersachievements`
--
ALTER TABLE `usersachievements`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `usersprojects`
--
ALTER TABLE `usersprojects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
