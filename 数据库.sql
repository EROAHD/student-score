# 创建数据库
DROP DATABASE IF EXISTS student_score;
CREATE DATABASE student_score;
USE student_score;
# 设置所有用户的默认密码
SET @password = '$2a$10$K5LSqCW1cJoljwu9Wd/f6uPeFoGbywW1XWyssmiXRFQIQp.72nF6y';

# 创建管理员表
CREATE TABLE admin
(
    admin_id BIGINT PRIMARY KEY COMMENT '管理员账号',
    password VARCHAR(500) NOT NULL COMMENT '管理员密码',
    name     VARCHAR(50)  NOT NULL COMMENT '管理员名称',
    email    VARCHAR(200) NOT NULL COMMENT '管理员邮箱',
    phone    VARCHAR(11)  NOT NUll COMMENT '管理员手机号'
);
INSERT INTO admin(admin_id, password, name, email, phone) VALUE (8573924610, @password, '赵天', '8573924610@protonmail.com', 13942331234);

# 专业大类表
CREATE TABLE majors
(
    majors_id   BIGINT PRIMARY KEY COMMENT '专业大类编号',
    name        VARCHAR(50)   NOT NULL UNIQUE COMMENT '专业大类名称',
    description VARCHAR(1000) NOT NULL COMMENT '专业大类描述'
);
INSERT INTO majors(majors_id, name, description) VALUE (10, '工程学',
                                                        '工程学是一门科学与技术领域，致力于设计、构建和维护各种物理结构、设备和系统。在这个专业大类中，学生可以选择专攻机械工程或电子工程等专业。机械工程涉及机械设计、热力学和材料力学等领域，而电子工程则涉及电路分析、通信原理和微处理器应用等方面的知识。');
INSERT INTO majors(majors_id, name, description) VALUE (11, '计算机科学',
                                                        '计算机科学是研究计算机系统、算法和编程的学科。在这个专业大类中，学生可以选择专攻软件工程或数据科学等专业。软件工程涉及软件设计、开发和测试等方面，而数据科学则涉及数据分析、机器学习和大数据技术等领域的应用。');
INSERT INTO majors(majors_id, name, description) VALUE (12, '商科',
                                                        '商科是研究商业和管理的学科领域。在这个专业大类中，学生可以选择专攻会计学或国际贸易等专业。会计学涉及财务会计、管理会计和审计学等方面的知识，而国际贸易则涉及国际经济学、跨国公司管理和贸易法律等领域的学习。');

# 专业表
CREATE TABLE major
(
    mid       BIGINT PRIMARY KEY COMMENT '专业编号',
    name      VARCHAR(50) NOT NULL UNIQUE COMMENT '专业名称',
    majors_id BIGINT      NOT NULL COMMENT '专业大类编号',
    FOREIGN KEY (majors_id) REFERENCES majors (majors_id)
);
/* 插入工程学专业 */
INSERT INTO major(mid, name, majors_id) VALUE (1001, '机械工程', 10);
INSERT INTO major(mid, name, majors_id) VALUE (1002, '电子工程', 10);
/* 插入计算机科学专业 */
INSERT INTO major(mid, name, majors_id) VALUE (1101, '软件工程', 11);
INSERT INTO major(mid, name, majors_id) VALUE (1102, '数据科学', 11);
/* 插入商科专业 */
INSERT INTO major(mid, name, majors_id) VALUE (1201, '会计学', 12);
INSERT INTO major(mid, name, majors_id) VALUE (1202, '国际贸易', 12);

-- 创建教师表
CREATE TABLE teacher
(
    tno      BIGINT UNSIGNED PRIMARY KEY COMMENT '教师编号',
    password VARCHAR(500) NOT NULL COMMENT '教师密码',
    sex      BIT          NOT NULL COMMENT '教师性别',
    name     VARCHAR(50)  NOT NULL COMMENT '教师名称',
    phone    VARCHAR(11)  NOT NULL COMMENT '手机号'
);

-- 插入教师数据
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1234567890, '李思源', 1, 13812345678, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (2345678901, '周芳华', 0, 15923456789, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (3456789012, '王美丽', 0, 18734567890, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1567890123, '赵小康', 1, 13845678901, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1678901234, '钱晓红', 0, 15956789012, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1789012345, '孙志强', 1, 18067890123, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1890123456, '吴艳芳', 0, 13178901234, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1901234567, '郑秀英', 0, 15289012345, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1012345678, '汪文杰', 1, 18390123456, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1234567891, '陆鹏飞', 1, 13501234567, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1345678902, '罗小龙', 1, 15612345678, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1456789013, '谭晓明', 1, 18723456789, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1567890124, '黄俊杰', 1, 13834567890, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1678901235, '曹建国', 1, 15945678901, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1789012346, '彭小芳', 0, 18056789012, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1890123457, '萧明珠', 0, 13167890123, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1901234568, '魏志强', 1, 15278901234, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1012345679, '蒋丽丽', 0, 18389012345, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1234567892, '沈红梅', 0, 13490123456, @password);
INSERT INTO teacher(tno, name, sex, phone, password)
VALUES (1345678903, '周小龙', 1, 15601234567, @password);

-- 创建课程类型表
CREATE TABLE course_types
(
    type_id     INT AUTO_INCREMENT PRIMARY KEY COMMENT '表示课程类型id',
    type_name   VARCHAR(50) NOT NULL COMMENT '表示课程类型名',
    description TEXT COMMENT '课程类型描述'
);

INSERT INTO course_types(type_id, type_name) VALUE (1, '必修');
INSERT INTO course_types(type_id, type_name) VALUE (2, '选修');

-- 创建课程表
CREATE TABLE course
(
    cid     BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '课程编号',
    name    VARCHAR(50)     NOT NULL UNIQUE COMMENT '课程名称',
    mid     BIGINT COMMENT '课程对应专业编号',
    tno     BIGINT UNSIGNED NOT NULL COMMENT '教师编号',
    type_id INT             NOT NULL COMMENT '课程类型',
    FOREIGN KEY (tno) REFERENCES teacher (tno), -- 任课教师作为外键
    FOREIGN KEY (type_id) REFERENCES course_types (type_id)
);
ALTER TABLE course
    AUTO_INCREMENT = 1001;

-- 插入机械工程课程
INSERT INTO course(name, mid, tno, type_id)
VALUES ('机械设计', 1001, 1234567890, 1);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('热力学', 1001, 2345678901, 1);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('控制工程', 1001, 3456789012, 1);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('材料力学', 1001, 1567890123, 1);

-- 插入电子工程课程
INSERT INTO course(name, mid, tno, type_id)
VALUES ('电路分析', 1002, 1678901234, 1);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('数字电子技术', 1002, 1789012345, 1);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('通信原理', 1002, 1890123456, 1);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('微处理器应用', 1002, 1901234567, 1);

-- 插入软件工程课程
INSERT INTO course(name, mid, tno, type_id)
VALUES ('软件设计与构建', 1101, 1012345678, 1);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('数据库管理', 1101, 1234567891, 1);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('软件测试与质量保证', null, 1345678903, 2);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('软件项目管理', 1101, 1456789013, 1);

-- 插入数据科学课程
INSERT INTO course(name, mid, tno, type_id)
VALUES ('数据分析与可视化', 1102, 1567890123, 1);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('机器学习', null, 1012345678, 2);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('数据挖掘', 1102, 1012345678, 1);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('大数据技术', 1102, 1890123457, 1);

-- 插入会计学课程
INSERT INTO course(name, mid, tno, type_id)
VALUES ('财务会计', 1201, 1901234568, 1);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('管理会计', null, 1012345679, 2);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('税务会计', 1201, 1234567892, 1);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('审计学', 1201, 1345678903, 1);

-- 插入国际贸易课程
INSERT INTO course(name, mid, tno, type_id)
VALUES ('国际经济学', 1202, 3456789012, 1);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('跨国公司管理', null, 1567890124, 2);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('跨文化沟通', 1202, 1678901235, 1);
INSERT INTO course(name, mid, tno, type_id)
VALUES ('海关法与实务', 1202, 1789012346, 1);

# 班级表
CREATE TABLE s_class
(
    cid  BIGINT PRIMARY KEY COMMENT '班级编号',
    name VARCHAR(20) NOT NULL COMMENT '班级名称',
    mid  BIGINT      NOT NULL COMMENT '专业编号',
    FOREIGN KEY (mid) REFERENCES major (mid) # 专业编号作为外键
);
/* 插入机械工程班级 */
INSERT INTO s_class(cid, name, mid) VALUE (100101, '机械工程1班', 1001);
INSERT INTO s_class(cid, name, mid) VALUE (100102, '机械工程2班', 1001);
INSERT INTO s_class(cid, name, mid) VALUE (100103, '机械工程3班', 1001);
/* 插入电子工程班级 */
INSERT INTO s_class(cid, name, mid) VALUE (100201, '电子工程1班', 1002);
INSERT INTO s_class(cid, name, mid) VALUE (100202, '电子工程2班', 1002);
/* 插入软件工程班级 */
INSERT INTO s_class(cid, name, mid) VALUE (110101, '软件工程1班', 1101);
INSERT INTO s_class(cid, name, mid) VALUE (110102, '软件工程2班', 1101);
INSERT INTO s_class(cid, name, mid) VALUE (110103, '软件工程3班', 1101);
INSERT INTO s_class(cid, name, mid) VALUE (110104, '软件工程4班', 1101);
/* 插入数据科学班级 */
INSERT INTO s_class(cid, name, mid) VALUE (110201, '数据科学1班', 1102);
/* 插入会计学班级 */
INSERT INTO s_class(cid, name, mid) VALUE (120101, '会计学1班', 1201);
INSERT INTO s_class(cid, name, mid) VALUE (120102, '会计学2班', 1201);
INSERT INTO s_class(cid, name, mid) VALUE (120103, '会计学3班', 1201);
/* 插入国际贸易班级 */
INSERT INTO s_class(cid, name, mid) VALUE (120201, '国际贸易1班', 1202);
INSERT INTO s_class(cid, name, mid) VALUE (120202, '国际贸易2班', 1202);

# 学生表
CREATE TABLE student
(
    sno      BIGINT UNSIGNED PRIMARY KEY COMMENT '学号',
    password VARCHAR(500) NOT NULL COMMENT '登录密码',
    name     VARCHAR(50)  NOT NULL COMMENT '学生姓名',
    sex      BIT          NOT NULL COMMENT '学生性别',
    email    VARCHAR(100) NOT NULL COMMENT '学生邮箱',
    mid      BIGINT       NOT NULL COMMENT '专业编号',
    cid      BIGINT       NOT NULL COMMENT '班级编号',
    FOREIGN KEY (cid) REFERENCES s_class (cid), # 班级编号作为外键
    FOREIGN KEY (mid) REFERENCES major (mid)    # 专业编号作为外键
);
/*
    插入学生数据
*/

/* 机械工程1班学生 */
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1910010101,
                                                                      @password,
                                                                      '龙衡冲', 1,
                                                                      '11627451824@outlook.com', 1001, 100101);
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1910010102,
                                                                      @password,
                                                                      '宣屹昶', 1,
                                                                      '51811826388@163.com', 1001, 100101);
/* 机械工程2班学生 */
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1910010201,
                                                                      @password,
                                                                      '瞿铮丛', 1,
                                                                      '71771258427@gmail.com', 1001, 100102);
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1910010202,
                                                                      @password,
                                                                      '唐冲军', 1,
                                                                      '19936491337@yahoo.com', 1001, 100102);
/* 机械工程3班学生 */
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1910010301,
                                                                      @password,
                                                                      '陈祺岚', 1,
                                                                      '70253453356@126.com', 1001, 100103);
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1910010302,
                                                                      @password,
                                                                      '郜冕元', 1,
                                                                      '68391606781@outlook.com', 1001, 100103);
/* 电子工程1班学生 */
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1910020101,
                                                                      @password,
                                                                      '丁昂歌', 1,
                                                                      '18201093658@126.com', 1002, 100201);
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1910020102,
                                                                      @password,
                                                                      '梅卉维', 0,
                                                                      '51537485583@yahoo.com', 1002, 100201);
/* 电子工程2班学生 */
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1910020201,
                                                                      @password,
                                                                      '苗颖义', 1,
                                                                      '91884912580@163.com', 1002, 100202);
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1910020202,
                                                                      @password,
                                                                      '龙剑丞', 1,
                                                                      '90446517973@163.com', 1002, 100202);
/* 软件工程1班学生 */
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1911010101,
                                                                      @password,
                                                                      '谭晓香', 0,
                                                                      '74306478223@outlook.com', 1101, 110101);
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1911010102,
                                                                      @password,
                                                                      '石韦涌', 1,
                                                                      '73990427998@163.com', 1101, 110101);
/* 软件工程2班学生 */
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1911010201,
                                                                      @password,
                                                                      '仲荷莲', 0,
                                                                      '36702485386@126.com', 1101, 110102);
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1911010202,
                                                                      @password,
                                                                      '袁益煜', 1,
                                                                      '99602985054@yahoo.com', 1101, 110102);
/* 数据科学1班学生 */
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1911020101,
                                                                      @password,
                                                                      '祝印珑', 0,
                                                                      '16354350177@outlook.com', 1102, 110201);
/* 会计学1班学生 */
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1912010101,
                                                                      @password,
                                                                      '叶澜秦', 1,
                                                                      '13729116400@163.com', 1102, 120101);
/* 会计学2班学生 */
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1912010201,
                                                                      @password,
                                                                      '祖漪椒', 1,
                                                                      '58312596850@yahoo.com', 1102, 120102);
/* 会计学3班学生 */
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1912010301,
                                                                      @password,
                                                                      '芮垚刚', 1,
                                                                      '14222740660@gmail.com', 1102, 120103);
/* 国际贸易1班 */
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1912020101,
                                                                      @password,
                                                                      '冯博靖', 1,
                                                                      '42088064830@126.com', 1102, 120201);
/* 国际贸易2班 */
INSERT INTO student(sno, password, name, sex, email, mid, cid) VALUE (1912020201,
                                                                      @password,
                                                                      '翟元云', 0,
                                                                      '29291703269@yahoo.com', 1102, 120202);


CREATE TABLE s_score
(
    sno       BIGINT UNSIGNED COMMENT '学号',
    cid       BIGINT NOT NULL COMMENT '课程编号',
    score     INT UNSIGNED COMMENT '成绩',
    is_failed BIT DEFAULT 0 COMMENT '是否挂科 0表示没有 1表示挂科',
    FOREIGN KEY (sno) REFERENCES student (sno),
    PRIMARY KEY (sno, cid)
);

-- 机械工程1班学生
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010101, 1001, 88, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010101, 1002, 75, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010101, 1003, 92, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010101, 1004, 81, 0);

INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010102, 1001, 79, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010102, 1002, 87, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010102, 1003, 70, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010102, 1004, 93, 0);

-- 机械工程2班学生
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010201, 1001, 84, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010201, 1002, 78, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010201, 1003, 89, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010201, 1004, 76, 0);

INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010202, 1001, 91, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010202, 1002, 82, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010202, 1003, 85, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010202, 1004, 88, 0);

-- 机械工程3班学生
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010301, 1001, 77, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010301, 1002, 90, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010301, 1003, 83, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010301, 1004, 79, 0);

INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010302, 1001, 85, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010302, 1002, 73, 1);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010302, 1003, 88, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910010302, 1004, 82, 0);

-- 电子工程1班学生
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910020101, 1005, 92, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910020101, 1006, 86, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910020101, 1007, 78, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910020101, 1008, 90, 0);

INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910020102, 1005, 80, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910020102, 1006, 89, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910020102, 1007, 84, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910020102, 1008, 87, 0);

-- 电子工程2班学生
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910020201, 1005, 92, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910020201, 1006, 86, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910020201, 1007, 78, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910020201, 1008, 90, 0);

INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910020202, 1005, 80, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910020202, 1006, 89, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910020202, 1007, 84, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1910020202, 1008, 87, 0);

-- 软件工程1班学生
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911010101, 1009, 80, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911010101, 1010, 82, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911010101, 1011, 83, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911010101, 1012, 90, 0);

INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911010102, 1009, 81, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911010102, 1010, 60, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911010102, 1011, 50, 1);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911010102, 1012, 62, 0);

-- 软件工程2班学生
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911010201, 1009, 81, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911010201, 1010, 87, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911010201, 1011, 70, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911010201, 1012, 64, 0);

INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911010202, 1009, 81, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911010202, 1010, 83, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911010202, 1011, 90, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911010202, 1012, 96, 0);

-- 数据科学1班学生
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911020101, 1013, 81, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911020101, 1014, 64, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911020101, 1015, 67, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1911020101, 1016, 89, 0);

-- 会计学1班学生
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912010101, 1017, 89, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912010101, 1018, 94, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912010101, 1019, 98, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912010101, 1020, 65, 0);

-- 会计学2班学生
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912010201, 1017, 98, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912010201, 1018, 82, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912010201, 1019, 83, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912010201, 1020, 89, 0);

-- 会计学3班学生
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912010301, 1017, 89, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912010301, 1018, 67, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912010301, 1019, 80, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912010301, 1020, 74, 0);

-- 国际贸易1班
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912020101, 1021, 80, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912020101, 1022, 82, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912020101, 1023, 62, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912020101, 1024, 50, 1);

-- 国际贸易2班
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912020201, 1021, 65, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912020201, 1022, 90, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912020201, 1023, 91, 0);
INSERT INTO s_score (sno, cid, score, is_failed)
VALUES (1912020201, 1024, 76, 0);

CREATE TABLE roles
(
    username BIGINT PRIMARY KEY COMMENT '用户用于登陆的用户账号',
    role     VARCHAR(50) NOT NULL COMMENT '用户的角色规则'
);

INSERT INTO roles(username, role)
SELECT student.sno, 'STUDENT'
FROM student;

INSERT INTO roles(username, role)
SELECT teacher.tno, 'TEACHER'
FROM teacher;

INSERT INTO roles(username, role)
SELECT admin.admin_id, 'ADMIN'
FROM admin;

# 其他需求所需表
CREATE TABLE avatar
(
    user_id     BIGINT       NOT NULL COMMENT '用户头像id',
    save_path   VARCHAR(100) NOT NULL COMMENT '用户头像保存的实际位置',
    upload_date TIMESTAMP    NOT NULL COMMENT '用户头像的上传时间'
);

# 创建学生选课表
CREATE TABLE s_elective
(
    sno BIGINT NOT NULL COMMENT '学号',
    cid BIGINT NOT NULL COMMENT '学生选课id'
);

INSERT INTO s_elective(sno, cid) VALUE (1910010101, 1011);
INSERT INTO s_elective(sno, cid) VALUE (1910010101, 1014);
INSERT INTO s_elective(sno, cid) VALUE (1910010101, 1018);
INSERT INTO s_elective(sno, cid) VALUE (1910010101, 1022);

# 创建首页走马灯信息表
CREATE TABLE banner
(
    banner_id   CHAR(36) PRIMARY KEY   DEFAULT UUID(),
    banner_type BIT           NOT NULL DEFAULT 1 COMMENT '1: 本地文件, 0: 网络图片',
    banner_url  VARCHAR(1000) NOT NULL COMMENT '点击图片后的跳转路径',
    file_path   VARCHAR(1000) NULL COMMENT '本地文件路径'
);

INSERT INTO banner(banner_type, banner_url, file_path)
VALUES (0, '/product/1', '/uploads/banner1.jpg'), -- 本地文件, 点击跳转到 /product/1
       (1, 'https://example.com', NULL); -- 网络图片, 点击跳转到 https://example.com