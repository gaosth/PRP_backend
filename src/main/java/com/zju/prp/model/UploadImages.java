package com.zju.prp.model;

import javax.persistence.*;

import java.util.StringJoiner;

import static com.zju.prp.util.MinioConfig.*;

@Entity
@Table(name = "uploadimages")  // 匹配数据库表名
public class UploadImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Image_ID")  // 确保列名大小写匹配数据库
    private Integer imageId;  // 数据库中是 int 类型，因此使用 Integer

    @Column(name = "Minio_ID", nullable = false, length = 255)  // 确保列名大小写匹配数据库
    private String minioId;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "User_ID", referencedColumnName = "user_id", nullable = true)  // 确保列名大小写匹配数据库
    private Users user;

    // 默认构造函数
    public UploadImages() {}

    // 全参构造函数
    public UploadImages(String minioId, Users user) {
        this.minioId = minioId;
        this.user = user;
    }

    // Getter 和 Setter 方法
    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getMinioId() {
        return minioId;
    }

    public void setMinioId(String minioId) {
        this.minioId = minioId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public void setUserId(int userId) {
        this.user = new Users();
        this.user.setUserId(userId);
    }
//
    public String getImageUrl(){
        StringJoiner sj = new StringJoiner("/");
        sj.add(endpoint);
        sj.add(bucketName);

        return sj + this.minioId;
    }
}