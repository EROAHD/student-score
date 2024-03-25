# 查询某个专业所有的班级
SELECT major.mid, major.name, s_class.name
FROM major
         LEFT JOIN s_class ON major.mid = s_class.mid
WHERE major.mid = ?;

# 查询某个学生的成绩
SELECT course.name, mid, sno
FROM course
         LEFT JOIN s_score ON course.cid = s_score.cid
WHERE sno = ?;

# 查询学生的基本信息
SELECT *
FROM student;

INSERT INTO teacher(tno, name, phone) VALUE (1234123423, '张东升', 12342232231);

SELECT *
FROM teacher
         LEFT JOIN student_score.course ON teacher.tno = course.tno
WHERE teacher.tno = 1234123423;

SELECT course.cid, course.name, s_score.score, teacher.name teacher
FROM course
         LEFT JOIN s_score on course.cid = s_score.cid
         RIGHT JOIN teacher ON teacher.tno = course.tno
WHERE course.mid =
      (SELECT mid
       FROM major
       WHERE mid = (SELECT mid FROM student WHERE sno = 1910010101))
  AND sno = 1910010101
