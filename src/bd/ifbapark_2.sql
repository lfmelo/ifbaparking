-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 17-Dez-2019 às 22:43
-- Versão do servidor: 5.7.23
-- versão do PHP: 7.1.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ifbapark`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `motorista`
--

CREATE TABLE `motorista` (
  `id` int(10) UNSIGNED NOT NULL,
  `nome` varchar(30) NOT NULL,
  `fone` varchar(20) NOT NULL,
  `hab_num` varchar(20) NOT NULL,
  `hab_cat` varchar(5) NOT NULL,
  `data_cad` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `motorista`
--

INSERT INTO `motorista` (`id`, `nome`, `fone`, `hab_num`, `hab_cat`, `data_cad`) VALUES
(4, 'Lucas Fernandes de Melo', '(77) 99999-7777', '3045607590', 'DE', '2018-12-09 10:09:19'),
(5, 'Paulo Andrade Silva', '(77) 98872-5455', '74715823', 'C', '2018-06-26 08:03:56'),
(6, 'Antonio Marcos Oliveira', '(77) 03421-5563', '65662314', 'D', '2018-06-26 08:11:20'),
(7, 'Mauricio Silva Moreira', '(77) 98175-4141', '2311546413', 'B', '2019-11-23 20:09:00'),
(8, 'Claudio Almeida Dias', '(77) 98889-5256', '55620230', 'B', '2018-06-26 08:12:43'),
(9, 'Silvana Prates de Oliveira', '(77) 88888-8888', '876968657', 'D', '2018-12-09 10:12:41'),
(10, 'Mariano Gomes da Silva', '(77) 98877-1244', '55463225', 'D', '2018-06-30 19:16:39'),
(15, 'Alison Simão Zuccari Lima', '(77) 98813-0535', '32456165451', 'D', '2018-11-26 04:09:07'),
(17, 'Joaquim Froes', '(77) 98878-5555', '54654654654', 'D', '2019-09-27 06:29:29'),
(19, 'Paulo Silva Pinto', '(77) 98885-5555', '456132789', 'A', '2019-11-23 20:35:22');

-- --------------------------------------------------------

--
-- Estrutura da tabela `movimento`
--

CREATE TABLE `movimento` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_veiculo` int(11) NOT NULL,
  `data` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `movimento` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `movimento`
--

INSERT INTO `movimento` (`id`, `id_veiculo`, `data`, `movimento`) VALUES
(13, 4, '2018-06-23 16:35:07', 'SAIDA'),
(14, 4, '2018-06-23 16:44:41', 'ENTRADA'),
(15, 4, '2018-06-23 16:45:00', 'SAIDA'),
(16, 4, '2018-06-23 16:46:07', 'ENTRADA'),
(17, 4, '2018-06-23 16:48:52', 'SAIDA'),
(18, 4, '2018-06-26 07:40:21', 'ENTRADA'),
(19, 4, '2018-06-26 07:41:34', 'SAIDA'),
(20, 4, '2018-06-26 07:41:47', 'ENTRADA'),
(21, 4, '2018-06-26 07:42:03', 'SAIDA'),
(22, 4, '2018-06-26 07:49:05', 'ENTRADA'),
(23, 4, '2018-06-26 07:49:48', 'SAIDA'),
(24, 6, '2018-06-26 08:48:57', 'ENTRADA'),
(25, 6, '2018-06-26 08:52:40', 'SAIDA'),
(26, 5, '2018-06-26 08:52:50', 'ENTRADA'),
(27, 7, '2018-06-26 08:52:59', 'SAIDA'),
(28, 6, '2018-06-26 08:53:10', 'ENTRADA'),
(29, 7, '2018-06-26 08:53:19', 'SAIDA'),
(30, 7, '2018-06-26 08:53:39', 'ENTRADA'),
(31, 5, '2018-06-26 08:53:46', 'SAIDA'),
(32, 5, '2018-06-26 08:54:01', 'ENTRADA'),
(33, 7, '2018-06-26 08:54:09', 'SAIDA'),
(34, 6, '2018-06-26 08:54:18', 'ENTRADA'),
(35, 4, '2018-06-26 08:54:32', 'SAIDA'),
(36, 4, '2018-06-26 08:57:18', 'ENTRADA'),
(37, 7, '2018-06-26 08:57:27', 'SAIDA'),
(38, 5, '2019-06-04 07:37:50', 'ENTRADA'),
(39, 5, '2019-06-04 07:38:14', 'SAIDA'),
(40, 4, '2019-06-04 07:38:27', 'ENTRADA'),
(41, 7, '2019-06-04 07:38:40', 'SAIDA'),
(42, 6, '2019-06-04 07:38:50', 'ENTRADA'),
(43, 4, '2019-06-04 07:39:18', 'SAIDA'),
(44, 4, '2019-06-04 07:39:38', 'ENTRADA'),
(45, 4, '2019-06-04 07:48:02', 'SAIDA'),
(46, 6, '2019-06-04 07:48:20', 'ENTRADA'),
(47, 4, '2019-09-05 21:45:56', 'SAIDA'),
(48, 4, '2019-09-05 21:46:28', 'ENTRADA'),
(49, 4, '2019-09-05 21:47:06', 'SAIDA'),
(50, 4, '2019-09-05 21:47:41', 'ENTRADA'),
(51, 4, '2019-09-05 21:51:22', 'SAIDA'),
(52, 4, '2019-09-05 21:56:50', 'ENTRADA'),
(53, 4, '2019-09-09 14:58:20', 'SAIDA'),
(54, 4, '2019-09-09 14:59:22', 'ENTRADA'),
(55, 4, '2019-09-09 14:59:35', 'SAIDA'),
(56, 4, '2019-09-09 14:59:57', 'ENTRADA'),
(57, 4, '2019-09-09 15:02:55', 'SAIDA'),
(58, 4, '2019-09-12 08:05:58', 'ENTRADA'),
(59, 4, '2019-09-12 08:06:14', 'SAIDA'),
(60, 4, '2019-09-12 08:06:37', 'ENTRADA'),
(61, 4, '2019-09-12 08:06:53', 'SAIDA'),
(62, 4, '2019-09-12 08:36:38', 'ENTRADA'),
(63, 4, '2019-09-12 08:37:15', 'SAIDA'),
(64, 4, '2019-09-12 08:39:02', 'ENTRADA'),
(65, 4, '2019-09-12 08:46:50', 'SAIDA'),
(66, 4, '2019-09-12 08:47:30', 'ENTRADA'),
(67, 4, '2019-09-12 08:48:29', 'SAIDA'),
(68, 4, '2019-09-12 08:48:44', 'ENTRADA'),
(69, 4, '2019-09-12 08:48:58', 'SAIDA'),
(70, 4, '2019-09-12 08:52:00', 'ENTRADA'),
(71, 4, '2019-09-12 08:53:54', 'SAIDA'),
(72, 4, '2019-09-12 09:10:52', 'ENTRADA'),
(73, 4, '2019-09-12 09:11:08', 'SAIDA'),
(74, 4, '2019-09-20 09:02:03', 'ENTRADA'),
(75, 4, '2019-09-20 09:02:39', 'SAIDA'),
(76, 4, '2019-09-20 09:05:56', 'ENTRADA'),
(77, 4, '2019-09-20 09:07:59', 'SAIDA'),
(78, 4, '2019-09-20 09:08:16', 'ENTRADA'),
(79, 4, '2019-09-20 09:10:51', 'SAIDA'),
(80, 4, '2019-09-20 09:12:11', 'ENTRADA'),
(81, 4, '2019-09-20 09:13:32', 'SAIDA'),
(82, 4, '2019-09-20 09:18:35', 'ENTRADA'),
(83, 4, '2019-09-20 09:19:07', 'SAIDA'),
(84, 4, '2019-09-20 09:25:59', 'ENTRADA'),
(85, 4, '2019-09-20 09:26:30', 'SAIDA'),
(86, 4, '2019-09-20 09:37:04', 'ENTRADA'),
(87, 4, '2019-09-20 09:38:29', 'SAIDA'),
(88, 4, '2019-09-20 09:38:49', 'ENTRADA'),
(89, 4, '2019-09-20 09:39:29', 'SAIDA'),
(90, 4, '2019-09-20 09:41:50', 'ENTRADA'),
(91, 4, '2019-09-20 09:43:50', 'SAIDA'),
(92, 4, '2019-09-20 20:57:40', 'ENTRADA'),
(93, 4, '2019-09-20 20:58:15', 'SAIDA'),
(94, 4, '2019-09-20 21:32:09', 'ENTRADA'),
(95, 4, '2019-09-20 21:33:49', 'SAIDA'),
(96, 4, '2019-09-20 21:34:10', 'ENTRADA'),
(97, 4, '2019-09-20 21:34:49', 'SAIDA'),
(98, 4, '2019-09-20 21:35:04', 'ENTRADA'),
(99, 4, '2019-09-20 21:38:13', 'SAIDA'),
(100, 4, '2019-09-20 21:39:25', 'ENTRADA'),
(101, 4, '2019-09-20 21:40:28', 'SAIDA'),
(102, 4, '2019-09-20 21:43:33', 'ENTRADA'),
(103, 4, '2019-09-20 21:45:30', 'SAIDA'),
(104, 4, '2019-09-20 21:46:49', 'ENTRADA'),
(105, 4, '2019-09-20 21:51:10', 'SAIDA'),
(106, 4, '2019-09-20 21:52:16', 'ENTRADA'),
(107, 4, '2019-09-20 21:52:57', 'SAIDA'),
(108, 4, '2019-09-20 21:53:38', 'ENTRADA'),
(109, 4, '2019-09-20 21:54:17', 'SAIDA'),
(110, 4, '2019-09-27 06:05:31', 'ENTRADA'),
(111, 4, '2019-09-27 06:05:52', 'SAIDA'),
(112, 4, '2019-09-27 06:06:39', 'ENTRADA'),
(113, 4, '2019-09-27 06:06:58', 'SAIDA'),
(114, 7, '2019-09-27 06:07:48', 'ENTRADA'),
(115, 5, '2019-09-27 06:08:04', 'SAIDA'),
(116, 6, '2019-09-27 06:08:21', 'ENTRADA'),
(117, 6, '2019-09-27 06:09:11', 'SAIDA'),
(118, 4, '2019-09-27 06:09:55', 'ENTRADA'),
(119, 7, '2019-09-27 06:10:17', 'SAIDA'),
(120, 7, '2019-09-27 06:11:39', 'ENTRADA'),
(121, 7, '2019-09-27 06:12:41', 'SAIDA'),
(122, 7, '2019-09-27 06:13:33', 'ENTRADA'),
(123, 7, '2019-09-27 06:14:34', 'SAIDA'),
(124, 7, '2019-09-27 06:15:20', 'ENTRADA'),
(125, 7, '2019-09-27 06:16:02', 'SAIDA'),
(126, 7, '2019-09-27 06:17:16', 'ENTRADA'),
(127, 7, '2019-09-27 06:17:58', 'SAIDA'),
(128, 7, '2019-09-27 06:18:35', 'ENTRADA'),
(129, 7, '2019-09-27 06:19:21', 'SAIDA'),
(130, 7, '2019-09-27 06:20:58', 'ENTRADA'),
(131, 7, '2019-09-27 06:22:01', 'SAIDA'),
(132, 7, '2019-09-27 06:23:13', 'ENTRADA'),
(133, 7, '2019-09-27 06:23:46', 'SAIDA'),
(134, 7, '2019-09-27 06:24:24', 'ENTRADA'),
(135, 7, '2019-09-27 06:26:25', 'SAIDA'),
(136, 7, '2019-09-27 06:27:30', 'ENTRADA'),
(137, 6, '2019-09-27 06:27:48', 'SAIDA'),
(138, 5, '2019-09-27 06:29:54', 'ENTRADA'),
(139, 4, '2019-09-27 18:47:26', 'SAIDA'),
(140, 4, '2019-09-27 18:47:52', 'ENTRADA'),
(141, 4, '2019-09-27 18:48:18', 'SAIDA'),
(142, 4, '2019-09-27 18:49:38', 'ENTRADA'),
(143, 4, '2019-09-27 18:51:14', 'SAIDA'),
(144, 4, '2019-09-27 18:51:39', 'ENTRADA'),
(145, 4, '2019-09-27 19:15:11', 'SAIDA'),
(146, 4, '2019-09-27 19:15:25', 'ENTRADA'),
(147, 4, '2019-09-27 19:28:57', 'SAIDA'),
(148, 4, '2019-09-27 19:41:22', 'ENTRADA'),
(149, 4, '2019-09-27 19:42:12', 'SAIDA'),
(150, 4, '2019-09-27 19:43:32', 'ENTRADA'),
(151, 4, '2019-09-27 19:43:52', 'SAIDA'),
(152, 4, '2019-09-27 19:45:05', 'ENTRADA'),
(153, 4, '2019-09-27 19:46:28', 'SAIDA'),
(154, 6, '2019-09-27 20:40:36', 'ENTRADA'),
(155, 5, '2019-09-27 20:40:53', 'SAIDA'),
(156, 5, '2019-09-27 20:41:15', 'ENTRADA'),
(157, 6, '2019-09-27 20:41:32', 'SAIDA'),
(158, 4, '2019-09-27 20:44:18', 'ENTRADA'),
(159, 4, '2019-09-27 20:44:39', 'SAIDA'),
(160, 4, '2019-09-27 20:54:09', 'ENTRADA'),
(161, 4, '2019-09-27 20:54:27', 'SAIDA'),
(162, 6, '2019-09-27 20:54:54', 'ENTRADA'),
(163, 5, '2019-09-27 20:55:10', 'SAIDA'),
(164, 4, '2019-09-27 20:59:24', 'ENTRADA'),
(165, 4, '2019-09-27 20:59:44', 'SAIDA'),
(166, 5, '2019-09-27 21:06:33', 'ENTRADA'),
(167, 6, '2019-09-27 21:07:10', 'SAIDA'),
(168, 6, '2019-09-27 21:08:39', 'ENTRADA'),
(169, 6, '2019-09-27 21:08:58', 'SAIDA'),
(170, 5, '2019-09-27 21:09:17', 'ENTRADA'),
(171, 5, '2019-09-27 21:09:35', 'SAIDA'),
(172, 4, '2019-09-27 21:09:52', 'ENTRADA'),
(173, 4, '2019-09-27 21:10:08', 'SAIDA'),
(174, 4, '2019-09-27 21:14:27', 'ENTRADA'),
(175, 16, '2019-09-27 21:17:50', 'SAIDA'),
(176, 16, '2019-09-27 21:18:10', 'ENTRADA'),
(177, 6, '2019-10-01 00:01:10', 'SAIDA'),
(178, 6, '2019-10-01 00:15:01', 'ENTRADA'),
(179, 6, '2019-10-01 00:15:17', 'SAIDA'),
(180, 6, '2019-10-01 00:16:03', 'ENTRADA'),
(181, 6, '2019-10-01 00:17:22', 'SAIDA'),
(182, 6, '2019-10-01 00:18:15', 'ENTRADA'),
(183, 7, '2019-11-19 21:25:59', 'SAIDA'),
(184, 5, '2019-11-19 21:26:44', 'ENTRADA'),
(185, 6, '2019-11-19 21:27:07', 'SAIDA');

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuario`
--

CREATE TABLE `usuario` (
  `id` int(10) UNSIGNED NOT NULL,
  `cpf` char(14) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `nascimento` date NOT NULL,
  `matricula` varchar(10) NOT NULL,
  `senha` varchar(12) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `usuario`
--

INSERT INTO `usuario` (`id`, `cpf`, `nome`, `nascimento`, `matricula`, `senha`) VALUES
(6, '111.111.111-11', 'Administrador do Sistema', '2018-01-01', '1234', '1234'),
(7, '654.651.348-48', 'Lucas Melo', '1986-05-02', '4321', '1234'),
(8, '123.456.987-78', 'Paulo Miranda da Silva Santos', '2012-01-01', '4567', '4567'),
(10, '888.888.888-85', 'Joaquim Silverio', '2223-10-22', '5678', '5678');

-- --------------------------------------------------------

--
-- Estrutura da tabela `veiculo`
--

CREATE TABLE `veiculo` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_motorista` int(10) NOT NULL,
  `tipo` varchar(20) NOT NULL,
  `fabricante` varchar(20) NOT NULL,
  `modelo` varchar(20) NOT NULL,
  `cor` varchar(20) NOT NULL,
  `ano` char(4) NOT NULL,
  `placa` char(8) NOT NULL,
  `proprietario` varchar(30) NOT NULL,
  `permissao` tinyint(4) NOT NULL,
  `data_cad` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `etiqueta_id` varchar(30) NOT NULL,
  `local` tinyint(4) DEFAULT NULL,
  `cadastrar_tag` tinyint(4) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `veiculo`
--

INSERT INTO `veiculo` (`id`, `id_motorista`, `tipo`, `fabricante`, `modelo`, `cor`, `ano`, `placa`, `proprietario`, `permissao`, `data_cad`, `etiqueta_id`, `local`, `cadastrar_tag`) VALUES
(4, 4, 'Carro de passeio', 'Ford', 'Ka', 'Branco', '2005', 'JOX-2872', 'Danilo Azevedo', 1, '2019-11-19 21:27:07', '5A EF 93 85', 0, 1),
(5, 7, 'Pickup', 'Fiat', 'Toro', 'Vermelho', '2018', 'JMN-3655', 'Mario da Costa Silva', 1, '2019-11-19 21:27:07', '21 B2 0C CB', 0, 1),
(6, 5, 'Caro de Passeio', 'Toyota', 'Corolla', 'Prata', '2017', 'KLB-1988', 'Maria do Rosário Cavalcante', 1, '2019-11-19 21:27:07', 'F6 7F 05 00', 0, 1),
(7, 8, 'Onibus', 'Marcopolo', 'Viaggio', 'Amarelo', '2012', 'GJJ-1241', 'UESB', 1, '2019-11-19 21:27:07', 'BC D5 1C 2B', 0, 1),
(8, 5, 'Carro de Passeio', 'Fiat', 'Uno Drive', 'Branco', '2017', 'WSZ-1522', 'Leonardo da Silva Aguiar', 1, '2019-11-19 21:27:07', 'A4 F2 DD C1', 0, 1),
(13, 6, 'Veícluo de Aluguel', 'Chevrolet', 'F-250', 'Azul', '2017', 'JMN-1244', 'Jonas do Prado', 1, '2019-11-19 21:27:07', 'AABBCC', 0, 1),
(14, 15, 'Carro de Passeio', 'Chevrolet', 'Classic', 'Verde', '2016', 'HHH-1100', 'MARCOS ANTONIO VALERIO SILVA', 1, '2019-11-19 21:27:07', 'XXXDDD', 0, 1),
(16, 17, 'Carga', 'Mercedes', '1113', 'Vermelho', '1999', 'JXO-4567', 'Paulo da Silva Santos', 1, '2019-11-19 21:27:07', '', 0, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `motorista`
--
ALTER TABLE `motorista`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `movimento`
--
ALTER TABLE `movimento`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_Veiculo` (`id_veiculo`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `veiculo`
--
ALTER TABLE `veiculo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_MotoristaCarro` (`id_motorista`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `motorista`
--
ALTER TABLE `motorista`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `movimento`
--
ALTER TABLE `movimento`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=186;

--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `veiculo`
--
ALTER TABLE `veiculo`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
