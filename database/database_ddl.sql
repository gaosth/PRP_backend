-- 用户表
CREATE TABLE Users (
    User_ID INT PRIMARY KEY AUTO_INCREMENT,
    User_Type ENUM('admin', 'user') NOT NULL,
    Name VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Avatar_ID INT,
    INDEX (Email)
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 上传图像表
CREATE TABLE UploadImages (
    Image_ID INT PRIMARY KEY AUTO_INCREMENT,
    Minio_ID VARCHAR(255) NOT NULL,
    User_ID INT
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 添加外键约束
ALTER TABLE Users
ADD CONSTRAINT FK_Users_Avatar
FOREIGN KEY (Avatar_ID) REFERENCES UploadImages(Image_ID) ON DELETE SET NULL;

ALTER TABLE UploadImages
ADD CONSTRAINT FK_UploadImages_User
FOREIGN KEY (User_ID) REFERENCES Users(User_ID) ON DELETE CASCADE;

-- 项目表
CREATE TABLE Projects (
    Project_ID INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(255) NOT NULL,
    Artist VARCHAR(255),
    Era VARCHAR(255),
    Material VARCHAR(255),
    Size VARCHAR(50),
    Institution VARCHAR(255),
    Description TEXT,
    Poem TEXT,
    Image_ID INT,
    Status VARCHAR(50),
    User_ID INT NOT NULL,
    Ref JSON,
    FOREIGN KEY (Image_ID) REFERENCES UploadImages(Image_ID) ON DELETE SET NULL,
    FOREIGN KEY (User_ID) REFERENCES Users(User_ID) ON DELETE CASCADE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 标注数据表
CREATE TABLE Annotations (
    Annotation_ID INT PRIMARY KEY AUTO_INCREMENT,
    Project_ID INT NOT NULL,
    Axis JSON NOT NULL,
    Mix_Method JSON NOT NULL,
    FOREIGN KEY (Project_ID) REFERENCES Projects(Project_ID) ON DELETE CASCADE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 修复记录表
CREATE TABLE RestorationRecords (
    Record_ID INT PRIMARY KEY AUTO_INCREMENT,
    Project_ID INT NOT NULL,
    Step INT NOT NULL,
    Image_Url VARCHAR(255) NOT NULL,
    FOREIGN KEY (Project_ID) REFERENCES Projects(Project_ID) ON DELETE CASCADE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 标记项目表
CREATE TABLE StarProjects (
    Star_ID INT PRIMARY KEY AUTO_INCREMENT,
    User_ID INT NOT NULL,
    Project_ID INT NOT NULL,
    FOREIGN KEY (User_ID) REFERENCES Users(User_ID) ON DELETE CASCADE,
    FOREIGN KEY (Project_ID) REFERENCES Projects(Project_ID) ON DELETE CASCADE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 实际图数据表
CREATE TABLE Photos (
    Photo_ID INT PRIMARY KEY AUTO_INCREMENT,
    Species VARCHAR(255),
    Minio_ID VARCHAR(255) NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 颜料表
CREATE TABLE Pigments (
    Pigment_ID INT PRIMARY KEY AUTO_INCREMENT,
    Thickness FLOAT NOT NULL,
    Material VARCHAR(255) NOT NULL,
    Lightness VARCHAR(50) NOT NULL,
    Spectrum JSON NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Painting表
CREATE TABLE Paintings (
    Painting_ID INT PRIMARY KEY AUTO_INCREMENT,
    Mask JSON NOT NULL,
    Tensor JSON NOT NULL,
    Representative_Color VARCHAR(50) NOT NULL,
    Title VARCHAR(255) NOT NULL,
    Artist VARCHAR(255),
    Era VARCHAR(255),
    Material VARCHAR(255),
    Size VARCHAR(50),
    Institution VARCHAR(255),
    Description TEXT,
    Poem TEXT,
    Remarks JSON NOT NULL
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Poem_Query表
CREATE TABLE PoemQueries (
    PQ_ID INT PRIMARY KEY AUTO_INCREMENT,
    Project_ID INT NOT NULL,
    Poem TEXT NOT NULL,
    Heatmap_Path JSON NOT NULL,
    FOREIGN KEY (Project_ID) REFERENCES Projects(Project_ID) ON DELETE CASCADE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Seg_Query表
CREATE TABLE SegQueries (
    SQ_ID INT PRIMARY KEY AUTO_INCREMENT,
    Project_ID INT NOT NULL,
    Photo_ID INT NOT NULL,
    Slice JSON NOT NULL,
    FOREIGN KEY (Project_ID) REFERENCES Projects(Project_ID) ON DELETE CASCADE,
    FOREIGN KEY (Photo_ID) REFERENCES Photos(Photo_ID) ON DELETE CASCADE
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;