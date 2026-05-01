-- =====================================================
-- 小区物业管理系统数据库脚本
-- 数据库：MySQL 5.5.28
-- 字符集：UTF-8
-- =====================================================

-- 创建数据库
CREATE DATABASE IF NOT EXISTS property_management 
DEFAULT CHARACTER SET utf8 
DEFAULT COLLATE utf8_general_ci;

USE property_management;

-- =====================================================
-- 1. 用户表（所有角色的基础表）
-- =====================================================
CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    real_name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(100),
    role ENUM('ADMIN', 'RESIDENT', 'STAFF', 'ADVERTISER') NOT NULL,
    status TINYINT DEFAULT 1 COMMENT '1-正常，0-禁用',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    last_login_time DATETIME
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- =====================================================
-- 2. 小区住户信息表
-- =====================================================
CREATE TABLE residents (
    resident_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    building_no VARCHAR(10) NOT NULL COMMENT '楼栋号',
    unit_no VARCHAR(10) NOT NULL COMMENT '单元号',
    room_no VARCHAR(10) NOT NULL COMMENT '房间号',
    area DECIMAL(10,2) COMMENT '房屋面积',
    owner_type ENUM('OWNER', 'RENTER') DEFAULT 'OWNER' COMMENT '业主/租户',
    id_card VARCHAR(20),
    family_count INT DEFAULT 1,
    move_in_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- =====================================================
-- 3. 物业工作人员信息表
-- =====================================================
CREATE TABLE staff (
    staff_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    department VARCHAR(50) COMMENT '部门',
    position VARCHAR(50) COMMENT '职位',
    hire_date DATE,
    salary DECIMAL(10,2),
    status TINYINT DEFAULT 1 COMMENT '1-在职，0-离职',
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- =====================================================
-- 4. 费用类型表
-- =====================================================
CREATE TABLE fee_types (
    fee_type_id INT PRIMARY KEY AUTO_INCREMENT,
    fee_name VARCHAR(50) NOT NULL COMMENT '费用名称',
    fee_unit_price DECIMAL(10,2) NOT NULL COMMENT '单价',
    fee_unit VARCHAR(20) COMMENT '计费单位',
    description VARCHAR(200)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- =====================================================
-- 5. 费用账单表
-- =====================================================
CREATE TABLE fee_bills (
    bill_id INT PRIMARY KEY AUTO_INCREMENT,
    resident_id INT NOT NULL,
    fee_type_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    period VARCHAR(20) COMMENT '缴费周期',
    due_date DATE NOT NULL,
    status ENUM('UNPAID', 'PAID', 'OVERDUE') DEFAULT 'UNPAID',
    pay_time DATETIME,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (resident_id) REFERENCES residents(resident_id),
    FOREIGN KEY (fee_type_id) REFERENCES fee_types(fee_type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- =====================================================
-- 6. 缴费记录表
-- =====================================================
CREATE TABLE payment_records (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    bill_id INT NOT NULL,
    user_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    pay_method VARCHAR(20) COMMENT '支付方式',
    pay_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    remark VARCHAR(200),
    FOREIGN KEY (bill_id) REFERENCES fee_bills(bill_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- =====================================================
-- 7. 报修申请表
-- =====================================================
CREATE TABLE repair_requests (
    request_id INT PRIMARY KEY AUTO_INCREMENT,
    resident_id INT NOT NULL,
    request_title VARCHAR(100) NOT NULL,
    request_desc TEXT NOT NULL,
    request_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('PENDING', 'ASSIGNED', 'IN_PROGRESS', 'COMPLETED', 'REJECTED') DEFAULT 'PENDING',
    assigned_staff_id INT,
    handle_desc TEXT,
    handle_time DATETIME,
    complete_time DATETIME,
    FOREIGN KEY (resident_id) REFERENCES residents(resident_id),
    FOREIGN KEY (assigned_staff_id) REFERENCES staff(staff_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- =====================================================
-- 8. 广告申请表
-- =====================================================
CREATE TABLE advertisement_applications (
    ad_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    company_name VARCHAR(100) NOT NULL,
    contact_person VARCHAR(50) NOT NULL,
    contact_phone VARCHAR(20) NOT NULL,
    ad_type VARCHAR(50) COMMENT '广告类型',
    ad_location VARCHAR(100) COMMENT '广告位置',
    ad_duration INT COMMENT '广告时长（天）',
    ad_content TEXT,
    apply_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    status ENUM('PENDING', 'APPROVED', 'REJECTED') DEFAULT 'PENDING',
    review_admin_id INT,
    review_time DATETIME,
    review_comment VARCHAR(500),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (review_admin_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- =====================================================
-- 9. 通知公告表
-- =====================================================
CREATE TABLE notices (
    notice_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    publisher_id INT,
    publish_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    target_role VARCHAR(20) COMMENT '目标角色',
    is_top TINYINT DEFAULT 0,
    FOREIGN KEY (publisher_id) REFERENCES users(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- =====================================================
-- 10. 在线用户统计表（监听器使用）
-- =====================================================
CREATE TABLE online_users_log (
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    session_id VARCHAR(100),
    login_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    logout_time DATETIME,
    ip_address VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- =====================================================
-- 初始化数据
-- =====================================================

-- 插入默认管理员账号 (密码：admin123)
INSERT INTO users (username, password, real_name, phone, email, role, status) 
VALUES ('admin', 'admin123', '系统管理员', '13800000000', 'admin@property.com', 'ADMIN', 1);

-- 插入默认住户账号 (密码：resident123)
INSERT INTO users (username, password, real_name, phone, email, role, status) 
VALUES ('resident001', 'resident123', '张三', '13800000001', 'zhangsan@email.com', 'RESIDENT', 1);

INSERT INTO residents (user_id, building_no, unit_no, room_no, area, owner_type, id_card, family_count, move_in_date)
VALUES (2, '1', '1', '101', 120.00, 'OWNER', '110101199001011234', 3, '2023-01-01');

-- 插入默认工作人员账号 (密码：staff123)
INSERT INTO users (username, password, real_name, phone, email, role, status) 
VALUES ('staff001', 'staff123', '李四', '13800000002', 'lisi@property.com', 'STAFF', 1);

INSERT INTO staff (user_id, department, position, hire_date, salary, status)
VALUES (3, '维修部', '维修工', '2023-01-15', 5000.00, 1);

-- 插入默认广告商账号 (密码：advertiser123)
INSERT INTO users (username, password, real_name, phone, email, role, status) 
VALUES ('advertiser001', 'advertiser123', '王五', '13800000003', 'wangwu@company.com', 'ADVERTISER', 1);

-- 插入费用类型
INSERT INTO fee_types (fee_name, fee_unit_price, fee_unit, description) VALUES
('物业费', 2.50, '元/平米/月', '小区物业管理费'),
('水费', 3.50, '元/吨', '生活用水费'),
('电费', 0.60, '元/度', '生活用电费'),
('停车费', 300.00, '元/月', '车位管理费'),
('垃圾清运费', 20.00, '元/月', '生活垃圾清运费');

-- 插入示例费用账单
INSERT INTO fee_bills (resident_id, fee_type_id, amount, period, due_date, status)
VALUES (1, 1, 300.00, '2024-01', '2024-01-31', 'UNPAID');

-- 插入示例公告
INSERT INTO notices (title, content, publisher_id, target_role, is_top) VALUES
('关于缴纳2024年1月物业费的通知', '请各位住户及时缴纳本月物业费，谢谢配合！', 1, 'RESIDENT', 1),
('小区停水通知', '因管道维修，本周五上午9:00-12:00将暂停供水，请提前做好储水准备。', 1, 'RESIDENT', 0);
