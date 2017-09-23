CREATE TABLE `tictactoe`.`history` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `game_id` VARCHAR(255) NOT NULL,
  `player_ip` VARCHAR(45) NOT NULL,
  `cell_id` INT(11) NOT NULL,
  `created_at` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`));