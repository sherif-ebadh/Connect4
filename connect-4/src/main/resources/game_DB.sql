CREATE DATABASE `gamedb` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE `player` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=latin1;


CREATE TABLE `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `game_arr` varchar(250) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `nextPlayer` int(11) DEFAULT NULL,
  `player1_id` int(11) NOT NULL,
  `player2_id` int(11) NOT NULL,
  `next_player` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Player1_FK_idx` (`player1_id`),
  KEY `Player2_FK_idx` (`player2_id`),
  CONSTRAINT `FKf6omt0g5ph9r6rd6j2uih24cm` FOREIGN KEY (`player2_id`) REFERENCES `player` (`id`),
  CONSTRAINT `FKuj22waqa0w9ju8c0re84u3pj` FOREIGN KEY (`player1_id`) REFERENCES `player` (`id`),
  CONSTRAINT `Player1_FK` FOREIGN KEY (`player1_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Player2_FK` FOREIGN KEY (`player2_id`) REFERENCES `player` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=latin1 COMMENT='												';
