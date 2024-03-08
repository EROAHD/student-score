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