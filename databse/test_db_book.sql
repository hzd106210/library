-- MySQL dump 10.13  Distrib 8.0.36, for macos14 (x86_64)
--
-- Host: 127.0.0.1    Database: test_db
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cover` varchar(100) DEFAULT NULL COMMENT '封面',
  `name` varchar(50) NOT NULL COMMENT '书名',
  `auth` varchar(50) NOT NULL COMMENT '作者',
  `desc` text NOT NULL COMMENT '简介',
  `publishing_house` varchar(50) NOT NULL COMMENT '出版社',
  `type` varchar(100) DEFAULT NULL COMMENT '类型',
  `stock` int NOT NULL COMMENT '库存',
  `borrow_num` int DEFAULT NULL COMMENT '借阅数量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `status` int NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (2,'','彷徨','鲁迅','《彷徨》是近代文学家鲁迅创作的小说集，原作首发于《东方杂志》《小说月报》《妇女杂志》《民国日报副刊》《语丝》《莽原》等报刊，署名鲁迅。首篇《祝福》写于1924年2月16日，末篇《离婚》写于1925年11月6日，于1926年8月由北京北新书局初版，列为作者所编的《乌合丛书》之一， [32]后编入《鲁迅全集》。','万卷出版公司','14',2,0,'2024-05-07 15:19:20',0),(3,'','半生缘','张爱玲','该小说以沈世钧与顾曼桢的情感悲欢离合为轴心，讲述了都市中沈世钧、顾曼桢、许叔惠、石翠芝等青年男女于乱世中阴差阳错的爱情悲剧。小说展示了女性视角下对人生、爱情婚姻的选择，体现了女性在困境中的生存状态与生命意志，以及作者对于各个人物深切的关照。','皇冠出版社','14',10,0,'2024-05-07 15:21:21',1),(4,'','红玫瑰与白玫瑰','张爱玲','该小说讲述了主人公佟振保与两个女人的情感纠葛。作为情妇的“红玫瑰”娇蕊，以及妻子“白玫瑰”烟鹂，她们都在振保身上倾注了大量感情，在恋情中卑微妥协，而振保爱自己更胜他人，最终还是负了她们，三人为爱身陷囹圄，不得善终。小说生动地呈现了新旧交替时期人们的生活情感和婚姻状态，对这两位女性的行为和心理变化大量着墨，通过跌宕曲折的故事情节，再现了女性在男权意识统治的社会下生活的困境，揭示了悲剧产生的根源。','皇冠文化出版公司','14',6,0,'2024-05-07 15:22:32',1),(5,'','金锁记','张爱玲','《金锁记》主要描写一个小商人家庭出身的女子曹七巧的心灵变迁历程。七巧做过残疾人的妻子，欲爱而不能爱，几乎像疯子一样在姜家过了30年。在财欲与情欲的压迫下，她的性格终于被扭曲，行为变得乖戾，亲手毁掉自己儿女的幸福。 [2]张爱玲另辟蹊径，讲述了一个母亲对自己亲生儿女迫害摧残的传奇故事，从而反映了特定的社会环境和具体的生活环境怎样把一个原本有着温情性格的正常女人变成一个阴鸷狠毒的“吃人者”。','皇冠文化出版公司','14',14,0,'2024-05-07 15:44:50',1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-14 18:40:33
