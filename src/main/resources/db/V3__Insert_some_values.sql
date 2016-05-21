INSERT INTO `movie` (`id`, `description`, `duration`, `name`, `director`, `genre`) VALUES
  (123, 'Leonardo di Caprio cheatin USA banking system.', 120, 'Catch me if you can', 'Some dude', 'action'),
  (124, 'Young people having fun', 118, 'American pie', 'Some another dude', 'comedy'),
  (125, 'Two guys getting bounded in local jail.', 170, 'Shawshank redemption', 'Dude number three', 'triller'),
  (126, 'Retiring detective has one last job to do, but it is not just a job.', 150, 'Se7en', 'Njega sam znao jbg', 'triller'),
  (127, 'Brainfuck movie as simple as that', 210, 'Interstellar', 'Christpher Nolan', 'drama');


INSERT INTO `users` (`id`, `address`, `adminFlag`, `email`, `name`, `phone`, `username`, `password`, `enabled`, `role`) VALUES
  (30, 'Tesanjska 24', 1, 'mmujic1@etf.unsa.ba', 'Muhamed Mujic', '062355108', 'admin', '$2a$10$sPG2e41kizLGXlFecZmGbOG5GLFJvfMXJKmkWR3YgDVJ5Ec/LglAS', 1, 'ROLE_ADMIN'),
  (34, 'WallST 12', 1, 'kurt@live.com', 'Kurt Cobain', '123456789', 'kurt', 'kurt', 0, 'ROLE_ADMIN'),
  (35, 'Zagrebacka 12', 0, 'himzo@live.com', 'Himzo Polovina', '123456789', 'himzo', '$2a$10$sVtd52j685Y/LqM5a4vg.OPrX9WiqrT0DRs.3pwlpucHVYkohI5XS', 0, 'ROLE_USER'),
  (36, 'WallSt 12', 0, 'johny@gmail.com', 'Johny Depp', '123456789', 'johnny', '$2a$10$x0.fPkhh3CSmTsdkDf2BZOtoAxO8qhee8x0W9U4g7rVOE1Ct7RMfm', 0, 'ROLE_USER');