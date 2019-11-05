/*
 Navicat Premium Data Transfer

 Source Server         : liqiaochen
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : lsxsglxt

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 05/11/2019 21:39:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像图片路径',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '管理员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin', '店长', '2636317752@qq.com', '/lsxsglxt/upload/20191026/9b79028981e5402d9a822091b8350771.png', 'aabbcc');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户Id',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '商品Id',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单id',
  `product_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `num` tinyint(4) NULL DEFAULT NULL COMMENT '商品数量',
  `price` double(10, 2) NULL DEFAULT NULL COMMENT '单价',
  `total` double(10, 2) NULL DEFAULT NULL COMMENT '总计',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态(1：未生成订单2：未生成订单)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user`(`user_id`) USING BTREE,
  INDEX `product`(`product_id`) USING BTREE,
  INDEX `order`(`order_id`) USING BTREE,
  CONSTRAINT `order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '购物车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart` VALUES (1, 1, 11, 5, '云南特产德和老字号大理乳扇沙琪玛120g零食品糕点糖果沙琪玛点心', 1, 39.00, 39.00, 2);
INSERT INTO `cart` VALUES (3, 1, 9, 5, '【良品铺子-葵花籽120gx2袋】原味瓜子小包装休闲零食品坚果炒货', 1, 19.00, 19.00, 2);
INSERT INTO `cart` VALUES (5, 1, 9, 5, '【良品铺子-葵花籽120gx2袋】原味瓜子小包装休闲零食品坚果炒货', 2, 19.00, 38.00, 2);
INSERT INTO `cart` VALUES (7, 1, 11, 6, '云南特产德和老字号大理乳扇沙琪玛120g零食品糕点糖果沙琪玛点心', 1, 39.00, 39.00, 2);
INSERT INTO `cart` VALUES (8, 1, 14, 6, '良品铺子甜蜜零食大礼包 香辣肉食山珍素食休闲果干蜜饯果脯坚果', 2, 47.00, 94.00, 2);
INSERT INTO `cart` VALUES (9, 1, 15, 7, '新疆干无花果茶干泡茶特级片烘干大个煲汤用小零食养生茶喝的饮品', 1, 31.00, 31.00, 2);
INSERT INTO `cart` VALUES (10, 1, 16, 8, '网红水果茶花茶组合纯手工果粒茶泡水喝的东西饮品养生洛神花果茶', 2, 30.00, 60.00, 2);
INSERT INTO `cart` VALUES (14, 1, 22, 13, '德芙麦提莎麦芽脆心夹心巧克力520g桶装巧克力球休闲零食', 1, 89.00, 89.00, 2);
INSERT INTO `cart` VALUES (15, 5, 11, 9, '云南特产德和老字号大理乳扇沙琪玛120g零食品糕点糖果沙琪玛点心', 3, 39.00, 117.00, 2);
INSERT INTO `cart` VALUES (16, 5, 11, 10, '云南特产德和老字号大理乳扇沙琪玛120g零食品糕点糖果沙琪玛点心', 3, 39.00, 117.00, 2);
INSERT INTO `cart` VALUES (17, 6, 15, 11, '新疆干无花果茶干泡茶特级片烘干大个煲汤用小零食养生茶喝的饮品', 3, 31.00, 93.00, 2);
INSERT INTO `cart` VALUES (18, 6, 4, 12, '小辣椒牛肉干(沙嗲)110g/袋    110g / 袋', 2, 34.00, 68.00, 2);
INSERT INTO `cart` VALUES (19, 1, 6, 13, '【三只松鼠_猪肉脯160g】麻辣零食风干熟食小吃食品肉脯特产肉干', 2, 23.00, 46.00, 2);
INSERT INTO `cart` VALUES (20, 1, 11, NULL, '云南特产德和老字号大理乳扇沙琪玛120g零食品糕点糖果沙琪玛点心', 1, 39.00, 39.00, 1);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ordercode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `status` int(11) NULL DEFAULT NULL COMMENT '订单状态(1：未付款2：未发货3：未收货4：已完成5：取消订单)',
  `orderTime` datetime(0) NULL DEFAULT NULL COMMENT '下单时间',
  `total` double(10, 2) NULL DEFAULT NULL COMMENT '订单总额',
  `payment` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付方式',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户Id',
  `orderitem_id` int(11) NULL DEFAULT NULL COMMENT '订单详情Id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_n7j2urgoicw0qa2b5s2pidind`(`orderitem_id`) USING BTREE,
  CONSTRAINT `FKj435mnwwxw5wci0t6xi15ddxk` FOREIGN KEY (`orderitem_id`) REFERENCES `orderitem` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (5, '2019102423554868770711', 5, '2019-10-24 15:55:49', 96.00, '在线支付', 1, 5);
INSERT INTO `order` VALUES (6, '2019102520145373517428', 4, '2019-10-25 12:14:54', 133.00, '在线支付', 1, 6);
INSERT INTO `order` VALUES (7, '2019102520303024440221', 4, '2019-10-25 12:30:30', 31.00, '在线支付', 1, 7);
INSERT INTO `order` VALUES (8, '2019102521213035324434', 5, '2019-10-25 13:21:30', 60.00, '在线支付', 1, 8);
INSERT INTO `order` VALUES (9, '2019102821570734441524', 1, '2019-10-28 13:57:07', 117.00, '在线支付', 5, 9);
INSERT INTO `order` VALUES (10, '2019102823023310829258', 1, '2019-10-28 15:02:33', 117.00, '在线支付', 5, 10);
INSERT INTO `order` VALUES (11, '2019102823091211001918', 1, '2019-10-28 15:09:27', 93.00, '在线支付', 6, 11);
INSERT INTO `order` VALUES (12, '2019102823125844186306', 1, '2019-10-28 15:13:01', 68.00, '在线支付', 6, 12);
INSERT INTO `order` VALUES (13, '2019102912325004480412', 4, '2019-10-29 04:32:50', 135.00, '在线支付', 1, 13);

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderCode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `confirmTime` datetime(0) NULL DEFAULT NULL COMMENT '确认收货时间',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `payTime` datetime(0) NULL DEFAULT NULL COMMENT '付款时间',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货电话',
  `shipTime` datetime(0) NULL DEFAULT NULL COMMENT '发货时间',
  `updatename` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发货人',
  `updatetime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态',
  `totalPrice` double(10, 0) NULL DEFAULT NULL COMMENT '总价',
  `remake` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单备注',
  `address_id` int(11) NULL DEFAULT NULL COMMENT '收货地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES (5, '2019102423554868770711', NULL, '2019-10-24 15:55:49', '2019-10-25 08:13:27', '无', NULL, NULL, NULL, 5, 96, '快', 1);
INSERT INTO `orderitem` VALUES (6, '2019102520145373517428', '2019-10-25 12:59:55', '2019-10-25 12:14:54', '2019-10-25 12:17:22', '17670990611', '2019-10-25 12:59:14', 'admin', NULL, 4, 133, '礼包好', 1);
INSERT INTO `orderitem` VALUES (7, '2019102520303024440221', '2019-10-25 13:22:20', '2019-10-25 12:30:30', '2019-10-25 13:12:00', '17670990611', '2019-10-25 13:13:45', 'admin', NULL, 4, 31, '', 1);
INSERT INTO `orderitem` VALUES (8, '2019102521213035324434', NULL, '2019-10-25 13:21:30', NULL, '17670990611', NULL, NULL, NULL, 5, 60, '', 2);
INSERT INTO `orderitem` VALUES (9, '2019102821570734441524', NULL, '2019-10-28 13:57:07', NULL, '无', NULL, NULL, NULL, 1, 117, '琪玛120g零食品糕点糖果沙琪玛点心', 6);
INSERT INTO `orderitem` VALUES (10, '2019102823023310829258', NULL, '2019-10-28 15:02:33', NULL, '无', NULL, NULL, NULL, 1, 117, 'adas ', 6);
INSERT INTO `orderitem` VALUES (11, '2019102823091211001918', NULL, '2019-10-28 15:09:27', NULL, '无', NULL, NULL, NULL, 1, 93, '啊啊啊', 8);
INSERT INTO `orderitem` VALUES (12, '2019102823125844186306', NULL, '2019-10-28 15:13:01', NULL, '无', NULL, NULL, NULL, 1, 68, '', 7);
INSERT INTO `orderitem` VALUES (13, '2019102912325004480412', '2019-10-29 04:35:30', '2019-10-29 04:32:50', '2019-10-29 04:33:09', '17670990611', '2019-10-29 04:34:46', 'admin', NULL, 4, 135, 'aaaa', 1);

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `level` int(11) NULL DEFAULT NULL COMMENT '级别',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `Admin_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者姓名',
  `product_id` int(11) NULL DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKf820vwoskrv05yxj80mlbj226`(`Admin_name`) USING BTREE,
  INDEX `product_pic`(`product_id`) USING BTREE,
  CONSTRAINT `product_pic` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 46 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '图片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of picture
-- ----------------------------
INSERT INTO `picture` VALUES (5, 0, '可口可乐', '2019-10-08 13:41:27', '/lsxsglxt/upload/20191008/7adace1e31d34654bfc55276957b05e0.jpg', 'admin', 2);
INSERT INTO `picture` VALUES (6, 1, '可口可乐', '2019-10-08 13:41:27', '/lsxsglxt/upload/20191008/1fc966ea8ac54b51b770e139f8a2678f.jpg', 'admin', 2);
INSERT INTO `picture` VALUES (7, 1, '小辣椒牛肉干(沙嗲)110g/袋    110g / 袋', '2019-10-09 10:42:06', '/lsxsglxt/upload/20191009/798fad5a48db45f8a0aee554849ba873.jpg', 'admin', 4);
INSERT INTO `picture` VALUES (8, 0, '小辣椒牛肉干(沙嗲)110g/袋    110g / 袋', '2019-10-09 10:42:06', '/lsxsglxt/upload/20191009/e53cf2bbe4fd485783fe31a2010a5f9f.jpg', 'admin', 4);
INSERT INTO `picture` VALUES (9, 1, 'TOPVALU五香牛肉片100g/袋    100g / 袋', '2019-10-09 10:47:27', '/lsxsglxt/upload/20191009/15f8bc7bfa524860b2d91cd817ec0d1f.jpg', 'admin', 5);
INSERT INTO `picture` VALUES (10, 2, 'TOPVALU五香牛肉片100g/袋    100g / 袋', '2019-10-09 10:47:27', '/lsxsglxt/upload/20191009/83482e55e78a4c5384b75ba2fb54accb.jpg', 'admin', 5);
INSERT INTO `picture` VALUES (11, 3, 'TOPVALU五香牛肉片100g/袋    100g / 袋', '2019-10-09 13:53:44', '/lsxsglxt/upload/20191009/34d93f50d24044cd8dd774e9060d1dda.jpg', 'admin', 5);
INSERT INTO `picture` VALUES (12, 1, '【三只松鼠_猪肉脯160g】麻辣零食风干熟食小吃食品肉脯特产肉干', '2019-10-19 09:48:53', '/lsxsglxt/upload/20191019/f71755c9eddb46d4887fb4e5c0180ea0.jpg', 'admin', 6);
INSERT INTO `picture` VALUES (13, 0, '【三只松鼠_猪肉脯160g】麻辣零食风干熟食小吃食品肉脯特产肉干', '2019-10-19 09:48:53', '/lsxsglxt/upload/20191019/ecba517058b44f8ebe3df4ab925d23a8.png', 'admin', 6);
INSERT INTO `picture` VALUES (14, 0, '满减【三只松鼠_黄桃干106g】休闲零食特产果脯蜜饯水果干桃干', '2019-10-19 10:11:54', '/lsxsglxt/upload/20191019/61c740e930ee433ba3900eb1101eb93b.jpg', 'admin', 7);
INSERT INTO `picture` VALUES (15, 1, '满减【三只松鼠_黄桃干106g】休闲零食特产果脯蜜饯水果干桃干', '2019-10-19 10:11:54', '/lsxsglxt/upload/20191019/353ab9fbfd8c49f58dc56b68f91cf44a.jpg', 'admin', 7);
INSERT INTO `picture` VALUES (16, 0, '美国原装进口kirkland柯克兰蓝莓干原味果干零食567g*2包', '2019-10-19 10:15:17', '/lsxsglxt/upload/20191019/62702d4a06a344369a1bb69733167441.jpg', 'admin', 8);
INSERT INTO `picture` VALUES (17, 1, '美国原装进口kirkland柯克兰蓝莓干原味果干零食567g*2包', '2019-10-19 10:15:17', '/lsxsglxt/upload/20191019/e1fccb771f7b401c9e6adb74c6c8740f.jpg', 'admin', 8);
INSERT INTO `picture` VALUES (18, 0, '【良品铺子-葵花籽120gx2袋】原味瓜子小包装休闲零食品坚果炒货', '2019-10-19 10:19:41', '/lsxsglxt/upload/20191019/214baf3f0b574807b06dc9ea26885f63.jpg', 'admin', 9);
INSERT INTO `picture` VALUES (19, 1, '【良品铺子-葵花籽120gx2袋】原味瓜子小包装休闲零食品坚果炒货', '2019-10-19 10:19:41', '/lsxsglxt/upload/20191019/d8a1218e7b454101b73d115d90442d62.jpg', 'admin', 9);
INSERT INTO `picture` VALUES (20, 0, '徽记金刚葵大颗粒瓜子原香葵花籽休闲零食坚果小吃炒货208g*3罐装', '2019-10-19 11:40:09', '/lsxsglxt/upload/20191019/8fc696df022241aeb3261983e28deda4.jpg', 'admin', 10);
INSERT INTO `picture` VALUES (21, 1, '徽记金刚葵大颗粒瓜子原香葵花籽休闲零食坚果小吃炒货208g*3罐装', '2019-10-19 11:40:09', '/lsxsglxt/upload/20191019/5e2b5aa12f924ebaae3569e3f7d2579c.jpg', 'admin', 10);
INSERT INTO `picture` VALUES (22, 0, '云南特产德和老字号大理乳扇沙琪玛120g零食品糕点糖果沙琪玛点心', '2019-10-19 11:44:02', '/lsxsglxt/upload/20191019/8a8533f79d374b4b85caae334470ef1a.jpg', 'admin', 11);
INSERT INTO `picture` VALUES (23, 1, '云南特产德和老字号大理乳扇沙琪玛120g零食品糕点糖果沙琪玛点心', '2019-10-19 11:44:02', '/lsxsglxt/upload/20191019/3c7d133f72fb416384a9e29678f65287.jpg', 'admin', 11);
INSERT INTO `picture` VALUES (24, 0, '欧贝拉蔓越莓雪花酥网红零食小吃休闲食品饼干整箱糕点心牛轧糖果', '2019-10-19 11:46:59', '/lsxsglxt/upload/20191019/3cb3a3664fd742059f98e5a6ca0fde32.jpg', 'admin', 12);
INSERT INTO `picture` VALUES (25, 1, '欧贝拉蔓越莓雪花酥网红零食小吃休闲食品饼干整箱糕点心牛轧糖果', '2019-10-19 11:46:59', '/lsxsglxt/upload/20191019/3fc1762c1dcd4eaba1231f2ab9c59691.jpg', 'admin', 12);
INSERT INTO `picture` VALUES (26, 0, '【西马克】山珍玉笋 咸菜笋丝 即食袋装零食 素食新鲜竹笋干128g', '2019-10-19 11:49:37', '/lsxsglxt/upload/20191019/4afbcfaaebf24855a17b08e2fd932cff.jpg', 'admin', 13);
INSERT INTO `picture` VALUES (27, 1, '【西马克】山珍玉笋 咸菜笋丝 即食袋装零食 素食新鲜竹笋干128g', '2019-10-19 11:49:37', '/lsxsglxt/upload/20191019/4bf57e45a1154eb082ffa458a420b66d.jpg', 'admin', 13);
INSERT INTO `picture` VALUES (28, 0, '良品铺子甜蜜零食大礼包 香辣肉食山珍素食休闲果干蜜饯果脯坚果', '2019-10-19 11:53:27', '/lsxsglxt/upload/20191019/3108f4e8eb6341b0a23bb5fe4d6115ef.jpg', 'admin', 14);
INSERT INTO `picture` VALUES (29, 1, '良品铺子甜蜜零食大礼包 香辣肉食山珍素食休闲果干蜜饯果脯坚果', '2019-10-19 11:53:27', '/lsxsglxt/upload/20191019/265bf744e68b4fa79201cbb4b5106156.jpg', 'admin', 14);
INSERT INTO `picture` VALUES (30, 0, '新疆干无花果茶干泡茶特级片烘干大个煲汤用小零食养生茶喝的饮品', '2019-10-19 11:58:12', '/lsxsglxt/upload/20191019/9332e9d94d2e40338c1d645fa29e5d89.jpg', 'admin', 15);
INSERT INTO `picture` VALUES (31, 1, '新疆干无花果茶干泡茶特级片烘干大个煲汤用小零食养生茶喝的饮品', '2019-10-19 11:58:12', '/lsxsglxt/upload/20191019/2d64a90fbf8f4a4f959fca0c10389071.jpg', 'admin', 15);
INSERT INTO `picture` VALUES (32, 0, '网红水果茶花茶组合纯手工果粒茶泡水喝的东西饮品养生洛神花果茶', '2019-10-19 12:01:27', '/lsxsglxt/upload/20191019/111d687da957414a9c88cd261d42393f.jpg', 'admin', 16);
INSERT INTO `picture` VALUES (33, 1, '网红水果茶花茶组合纯手工果粒茶泡水喝的东西饮品养生洛神花果茶', '2019-10-19 12:01:27', '/lsxsglxt/upload/20191019/3ab245143b4f46eb9cf23d7738c177b6.jpg', 'admin', 16);
INSERT INTO `picture` VALUES (34, 0, '【良品铺子麻辣零食大礼包】网红小吃鸭脖整箱休闲小食品散装一箱', '2019-10-19 12:10:22', '/lsxsglxt/upload/20191019/c95fcee5ce93474bb27e9a805d58cb33.jpg', 'admin', 17);
INSERT INTO `picture` VALUES (35, 1, '【良品铺子麻辣零食大礼包】网红小吃鸭脖整箱休闲小食品散装一箱', '2019-10-19 12:10:22', '/lsxsglxt/upload/20191019/bab20022c2214f948486f44444c059ad.jpg', 'admin', 17);
INSERT INTO `picture` VALUES (36, 0, '舟山特产海鲜零食裕达烧烤鱿鱼仔500g即食休闲小吃带籽海兔墨鱼仔', '2019-10-19 12:12:59', '/lsxsglxt/upload/20191019/a5367dcf73494c8dafb5fb8efbb93c42.jpg', 'admin', 18);
INSERT INTO `picture` VALUES (37, 1, '舟山特产海鲜零食裕达烧烤鱿鱼仔500g即食休闲小吃带籽海兔墨鱼仔', '2019-10-19 12:12:59', '/lsxsglxt/upload/20191019/d6b7737ab3364c9f90fd2a930a62a848.jpg', 'admin', 18);
INSERT INTO `picture` VALUES (38, 0, '亿滋 奥利奥夹心饼干巧克力味原味696G*2盒网红休食零食共24小包', '2019-10-19 12:15:44', '/lsxsglxt/upload/20191019/a5c3a56b5298431b87a0cd3b066f790d.jpg', 'admin', 19);
INSERT INTO `picture` VALUES (39, 1, '亿滋 奥利奥夹心饼干巧克力味原味696G*2盒网红休食零食共24小包', '2019-10-19 12:15:44', '/lsxsglxt/upload/20191019/279cb898d1974b7aad2b7ec66d60f955.jpg', 'admin', 19);
INSERT INTO `picture` VALUES (40, 0, '甜甜乐星球杯桶装超大正品1000g巧克力杯儿童饼干零食小吃大礼包', '2019-10-19 12:19:39', '/lsxsglxt/upload/20191019/c83036cdd79e41d6ba912af2459ca282.jpg', 'admin', 20);
INSERT INTO `picture` VALUES (41, 1, '甜甜乐星球杯桶装超大正品1000g巧克力杯儿童饼干零食小吃大礼包', '2019-10-19 12:19:39', '/lsxsglxt/upload/20191019/136c6ac6ee0645af955a172ac277028b.jpg', 'admin', 20);
INSERT INTO `picture` VALUES (42, 0, '梁丰麦丽素巧克力豆朱古力抹茶怀旧麦咪零食儿童散装（代可可脂）', '2019-10-19 12:22:40', '/lsxsglxt/upload/20191019/cc81abd6213140478963c57a81f4d1ad.jpg', 'admin', 21);
INSERT INTO `picture` VALUES (43, 1, '梁丰麦丽素巧克力豆朱古力抹茶怀旧麦咪零食儿童散装（代可可脂）', '2019-10-19 12:22:40', '/lsxsglxt/upload/20191019/8800ff6899f74130b8e83b8bb9aa411f.jpg', 'admin', 21);
INSERT INTO `picture` VALUES (44, 0, '德芙麦提莎麦芽脆心夹心巧克力520g桶装巧克力球休闲零食', '2019-10-19 12:25:33', '/lsxsglxt/upload/20191019/c83a299cb25c4e3ca5aa6dfe8d534af1.jpg', 'admin', 22);
INSERT INTO `picture` VALUES (45, 1, '德芙麦提莎麦芽脆心夹心巧克力520g桶装巧克力球休闲零食', '2019-10-19 12:25:33', '/lsxsglxt/upload/20191019/57700a812cfa499585914dac1d44ba2b.jpg', 'admin', 22);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品编码',
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `price` double(11, 0) NULL DEFAULT NULL COMMENT '价格',
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `stock` bigint(20) NULL DEFAULT NULL COMMENT '库存',
  `recommend` tinyint(2) NULL DEFAULT NULL COMMENT '推荐(1推荐,2不推荐)',
  `turnover` int(255) NULL DEFAULT NULL COMMENT '成交量',
  `hot` tinyint(2) NULL DEFAULT NULL COMMENT '热门(1热门2不热门)',
  `unit` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态(1上架2下架)',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `createAdmin_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者姓名',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `updateAdmin_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改者姓名',
  `type_id` int(11) NULL DEFAULT NULL COMMENT '类别Id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK96wxe8531cmahwxjoen492n2h`(`updateAdmin_name`) USING BTREE,
  INDEX `FKklsdhflskdhfl5s468ytr131j3hg`(`type_id`) USING BTREE,
  CONSTRAINT `FKklsdhflskdhfl5s468ytr131j3hg` FOREIGN KEY (`type_id`) REFERENCES `producttype` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (2, '1002', '可口可乐', 44, '百事可乐爱心罐碳酸汽水饮料整箱330ml*24罐经典口味百事出品\n新老包装 箱/塑膜更克换重随机发货实物为准', 300, 1, 600, 1, '330ml*24', 1, '2019-10-08 13:41:27', 'admin', '2019-10-09 10:08:59', 'admin', 13);
INSERT INTO `product` VALUES (4, '1003', '小辣椒牛肉干(沙嗲)110g/袋    110g / 袋', 34, '小辣椒牛肉干', 300, NULL, 300, 1, '袋', 1, '2019-10-09 10:42:06', 'admin', NULL, NULL, 4);
INSERT INTO `product` VALUES (5, '1004', 'TOPVALU五香牛肉片100g/袋    100g / 袋', 21, '五香牛肉片', 300, 2, 350, 2, '袋', 2, '2019-10-09 10:47:27', 'admin', '2019-10-09 13:53:44', 'admin', 4);
INSERT INTO `product` VALUES (6, '1005', '【三只松鼠_猪肉脯160g】麻辣零食风干熟食小吃食品肉脯特产肉干', 23, '新品速递，进店铺抢10万份爆款5折', 300, NULL, 0, 2, '160/包', 1, '2019-10-19 09:48:53', 'admin', NULL, NULL, 4);
INSERT INTO `product` VALUES (7, '1006', '满减【三只松鼠_黄桃干106g】休闲零食特产果脯蜜饯水果干桃干', 23, '新品速递，满减专区满300减200', 300, NULL, 0, 2, '106gx1袋', 1, '2019-10-19 10:11:54', 'admin', NULL, NULL, 16);
INSERT INTO `product` VALUES (8, '1007', '美国原装进口kirkland柯克兰蓝莓干原味果干零食567g*2包', 149, '富含花青素，颗粒饱满，烘焙零食好选择', 300, NULL, 400, 2, '567g*2包', 1, '2019-10-19 10:15:17', 'admin', NULL, NULL, 16);
INSERT INTO `product` VALUES (9, '1008', '【良品铺子-葵花籽120gx2袋】原味瓜子小包装休闲零食品坚果炒货', 19, '自然原香 大颗粒', 300, 1, 0, 1, '120gx2袋', 1, '2019-10-19 10:19:41', 'admin', '2019-10-19 11:37:39', 'admin', 18);
INSERT INTO `product` VALUES (10, '1009', '徽记金刚葵大颗粒瓜子原香葵花籽休闲零食坚果小吃炒货208g*3罐装', 50, '原香味 精选大颗粒', 300, NULL, 0, 1, '208g*3罐装', 1, '2019-10-19 11:40:09', 'admin', NULL, NULL, 18);
INSERT INTO `product` VALUES (11, '1010', '云南特产德和老字号大理乳扇沙琪玛120g零食品糕点糖果沙琪玛点心', 39, '手工制作 云南风味', 300, 1, 0, 1, '120g/盒', 1, '2019-10-19 11:44:02', 'admin', NULL, NULL, 19);
INSERT INTO `product` VALUES (12, '1011', '欧贝拉蔓越莓雪花酥网红零食小吃休闲食品饼干整箱糕点心牛轧糖果', 10, '', 300, NULL, 0, 1, '400g/箱(约35包)', 1, '2019-10-19 11:46:59', 'admin', NULL, NULL, 19);
INSERT INTO `product` VALUES (13, '1012', '【西马克】山珍玉笋 咸菜笋丝 即食袋装零食 素食新鲜竹笋干128g', 13, '买一发二 买二发五（口味随机）满58包邮', 300, NULL, 0, 1, '128g/包', 1, '2019-10-19 11:49:37', 'admin', NULL, NULL, 20);
INSERT INTO `product` VALUES (14, '1013', '良品铺子甜蜜零食大礼包 香辣肉食山珍素食休闲果干蜜饯果脯坚果', 47, '', 300, NULL, 80, 1, '889g（5袋）', 1, '2019-10-19 11:53:27', 'admin', NULL, NULL, 20);
INSERT INTO `product` VALUES (15, '1014', '新疆干无花果茶干泡茶特级片烘干大个煲汤用小零食养生茶喝的饮品', 31, '精选品质', 300, NULL, 0, 1, '瓶', 1, '2019-10-19 11:58:12', 'admin', NULL, NULL, 21);
INSERT INTO `product` VALUES (16, '1015', '网红水果茶花茶组合纯手工果粒茶泡水喝的东西饮品养生洛神花果茶', 30, '买一发三共540g（蓝色忧郁；柠檬香甜；巴黎', 300, NULL, 0, 1, '瓶', 1, '2019-10-19 12:01:27', 'admin', NULL, NULL, 21);
INSERT INTO `product` VALUES (17, '1016', '【良品铺子麻辣零食大礼包】网红小吃鸭脖整箱休闲小食品散装一箱', 40, '好零食 选良品 挑更好的不会错', 300, NULL, 0, 2, '20小包/410g', 1, '2019-10-19 12:10:22', 'admin', NULL, NULL, 22);
INSERT INTO `product` VALUES (18, '1016', '舟山特产海鲜零食裕达烧烤鱿鱼仔500g即食休闲小吃带籽海兔墨鱼仔', 30, '多口味选择，适合不同客户', 300, 2, 0, 2, '500g/包', 1, '2019-10-19 12:12:59', 'admin', '2019-10-28 13:27:53', 'admin', 22);
INSERT INTO `product` VALUES (19, '1017', '亿滋 奥利奥夹心饼干巧克力味原味696G*2盒网红休食零食共24小包', 50, '', 300, NULL, 0, 2, '696G*2盒', 1, '2019-10-19 12:15:44', 'admin', NULL, NULL, 23);
INSERT INTO `product` VALUES (20, '1018', '甜甜乐星球杯桶装超大正品1000g巧克力杯儿童饼干零食小吃大礼包', 15, '童年味道 多规格可选 每罐勺子约40个左右', 300, NULL, 0, 2, '桶装390g', 1, '2019-10-19 12:19:39', 'admin', NULL, NULL, 23);
INSERT INTO `product` VALUES (21, '1019', '梁丰麦丽素巧克力豆朱古力抹茶怀旧麦咪零食儿童散装（代可可脂）', 20, '梁丰工厂直供 国货经典 80后经典小包装零食', 300, 1, 0, 2, '原味30g*14包', 1, '2019-10-19 12:22:40', 'admin', '2019-10-19 12:25:54', 'admin', 24);
INSERT INTO `product` VALUES (22, '1020', '德芙麦提莎麦芽脆心夹心巧克力520g桶装巧克力球休闲零食', 89, '精选原料 麦芽脆心 分享装', 300, 1, 0, 2, '520g桶装', 1, '2019-10-19 12:25:33', 'admin', '2019-10-19 12:26:07', 'admin', 24);

-- ----------------------------
-- Table structure for producttype
-- ----------------------------
DROP TABLE IF EXISTS `producttype`;
CREATE TABLE `producttype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类别名称',
  `status` tinyint(2) NULL DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '商品类别' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of producttype
-- ----------------------------
INSERT INTO `producttype` VALUES (4, '肉脯鱼干', 1);
INSERT INTO `producttype` VALUES (13, '可乐', 1);
INSERT INTO `producttype` VALUES (16, '果干果脯', 1);
INSERT INTO `producttype` VALUES (18, '坚果炒货', 1);
INSERT INTO `producttype` VALUES (19, '糕点糖果', 1);
INSERT INTO `producttype` VALUES (20, '素食山珍', 1);
INSERT INTO `producttype` VALUES (21, '花茶饮品', 1);
INSERT INTO `producttype` VALUES (22, '休闲零食', 1);
INSERT INTO `producttype` VALUES (23, '夹心饼干', 2);
INSERT INTO `producttype` VALUES (24, '巧克力', 1);
INSERT INTO `producttype` VALUES (26, '小雨仔', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `balance` double NULL DEFAULT NULL COMMENT '账户余额',
  `Signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `hobby` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '爱好',
  `telPhone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座机电话',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '小王', '123456', 0, '真好', '无', '17670990611', 'bbbb');
INSERT INTO `user` VALUES (2, '小明', '123456', 0, '无', '无', '无', '无');
INSERT INTO `user` VALUES (3, 'admin', 'admin', 0, '无', '无', '无', '无');
INSERT INTO `user` VALUES (4, '小李', '123456', 0, '无', '无', '无', '无');
INSERT INTO `user` VALUES (5, '小胡', '123456', 0, '无', '无', '无', '无');
INSERT INTO `user` VALUES (6, '小雷', '1', 0, '无', '无', '无', '无');

-- ----------------------------
-- Table structure for useraddress
-- ----------------------------
DROP TABLE IF EXISTS `useraddress`;
CREATE TABLE `useraddress`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `consignee` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收件人',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `zipCode` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮政编码',
  `Street` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '街道',
  `Region` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区',
  `Tag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_c0hoxg699yrbg42lrq6738j0n`(`user_id`) USING BTREE,
  CONSTRAINT `FK_c0hoxg699yrbg42lrq6738j0n` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户地址' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of useraddress
-- ----------------------------
INSERT INTO `useraddress` VALUES (1, '小王', '1562311532', '214045', '民丰西苑82号202室', '江苏 无锡市 北塘区', '家', 1);
INSERT INTO `useraddress` VALUES (2, '小王', '1562311532', '214045', '民丰西苑82号202室', '江苏 无锡市 北塘区', '公司', 1);
INSERT INTO `useraddress` VALUES (5, '小胡', '166766263', '123123', '330号', '湖南', '家', 5);
INSERT INTO `useraddress` VALUES (6, '阿松大', '阿松大', '123132', '东江镇', '阿松大', ' 啊', 5);
INSERT INTO `useraddress` VALUES (7, '李巧郴', '17670849877', '123132', '12312', '湖南', '家', 6);
INSERT INTO `useraddress` VALUES (8, '贾宇钢', '13595054106', '551100', '阿松大', '贵州省', '1', 6);

SET FOREIGN_KEY_CHECKS = 1;
