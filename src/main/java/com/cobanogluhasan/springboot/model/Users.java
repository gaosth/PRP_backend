package com.cobanogluhasan.springboot.model;

import javax.persistence.*;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = "Email")  // 确保列名大小写匹配数据库
})
public class Users {

    // 生成唯一ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID")  // 确保列名大小写匹配数据库
    private Integer userId;  // 数据库中是 int 类型，因此使用 Integer

    @Column(name = "User_Type", nullable = false, length = 20)  // 确保列名大小写匹配数据库
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "Name", nullable = false, length = 255)  // 确保列名大小写匹配数据库
    private String name;

    @Column(name = "Email", nullable = false, unique = true, length = 255)  // 确保列名大小写匹配数据库
    private String email;

    @Column(name = "Password", nullable = false, length = 255)  // 确保列名大小写匹配数据库
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "Avatar_ID", referencedColumnName = "Image_ID", nullable = true)  // 确保列名大小写匹配数据库
    private UploadImages avatar;

    public enum UserType {
        admin("admin"),
        user("user");
    
        private final String value;
    
        UserType(String value) {
            this.value = value;
        }
    
        @Override
        public String toString() {
            return this.value;
        }
    }

    // 默认构造函数
    public Users() {}

    // 全参构造函数
    public Users(UserType userType, String name, String email, String password, UploadImages avatar) throws NoSuchAlgorithmException {
        this.userType = userType;
        this.name = name;
        this.email = email;
        setPassword(password);
        this.avatar = avatar;
    }

    // Getter 和 Setter 方法
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UploadImages getAvatar() {
        return avatar;
    }

    public void setAvatar(UploadImages avatar) {
        this.avatar = avatar;
    }
}