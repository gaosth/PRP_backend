-- the AI-generated data for testing

-- User
INSERT INTO Users (User_Type, Name, Email, Password, Avatar_ID) VALUES
        ('user', '张三', 'zhangsan@example.com', 'password123', null),
        ('user', '李四', 'lisi@example.com', 'password456', null),
        ('user', '王五', 'wangwu2024@example.com', 'password789', null),
        ('user', 'Alice王', 'alice_wang@example.com', 'passwordAlice', null),
        ('user', '小明123', 'xiaoming123@example.com', 'passwordXiaoMing', null),
        ('user', '测试用户', 'test_user@example.com', 'passwordTest', null),
        ('admin', '管理员1号', 'admin1@example.com', 'passwordAdmin1', null),
        ('user', 'John中文', 'john_chinese@example.com', 'passwordJohn', null),
        ('user', '测试ABC', 'test_abc@example.com', 'passwordABC', null),
        ('admin', '系统管理员', 'sysadmin@example.com', 'passwordSysAdmin', null);

-- Project
-- 插入测试数据
INSERT INTO Projects (Title, Artist, Era, Material, Size, Institution, Description, Poem, Image_ID, Status, User_ID, Ref) VALUES
        ('清明上河图', '张择端', '宋代', '绢本设色', '24.8x528.7 cm', '故宫博物院', '中国古代名画之一，描绘北宋时期东京汴梁繁荣景象。', '远看山有色，近听水无声。', 1, '已展出', 1, '{"references": ["history", "art", "culture"]}'),
        ('星夜', 'Vincent van Gogh 梵高', '1889年', '布面油画', '73.7x92.1 cm', '纽约现代艺术博物馆', '梵高著名作品之一，描绘夜晚星空与村庄。', '每个人心中都有一颗星。', 1, '已展出', 1, '{"references": ["post-impressionism", "modern art"]}'),
        ('兰亭集序', '王羲之', '东晋', '纸本墨迹', '24.5x69.9 cm', '台北故宫博物院', '中国书法史上的瑰宝，被誉为“天下第一行书”。', '永和九年，岁在癸丑，暮春之初。', 1, '正在修复', 2, '{"references": ["calligraphy", "classic literature"]}'),
        ('向日葵', 'Vincent van Gogh', '1888年', '布面油画', '91x72 cm', '荷兰梵高博物馆', '梵高创作的一系列以向日葵为主题的作品之一。', '生如夏花之绚烂，死如秋叶之静美。', 1, '已展出', 1, '{"references": ["nature", "flowers", "vivid colors"]}'),
        ('富春山居图', '黄公望', '元代', '纸本水墨', '33x636 cm', '浙江省博物馆', '中国古代山水画巅峰之作之一，表现了文人隐逸思想。', '大江东去，浪淘尽千古风流人物。', 1, '正在研究', 3, '{"references": ["landscape", "ancient art"]}'),
        ('时代的火焰', '未来艺术家', '2024年', '数字艺术', '无', '虚拟美术馆', '探索未来艺术形式，融合科技与文化。', '数码永恒，创意无边。', 1, '数字化展示', 1, '{"references": ["digital art", "AI"]}'),
        ('月亮与六便士', 'Charlie Moon', '现代', '装置艺术', '可变', '伦敦艺术中心', '灵感来自毛姆的同名小说，探索人生与艺术的冲突与平衡。', '抬头仰望月亮，低头寻找六便士。', 1, '已展出', 2, '{"references": ["installation art", "philosophy"]}');
