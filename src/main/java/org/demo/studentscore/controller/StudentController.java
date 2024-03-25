package org.demo.studentscore.controller;

import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.model.vo.StudentVO;
import org.demo.studentscore.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    StudentService studentService;

    /**
     * 构造器注入 service 成员变量
     */
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 模糊查询学生
     *
     * @param pageSize 分页每页数据数量
     * @param pageNum  表示第几页数据
     * @param keywords 从前端传入的关键字map键值对
     * @return 按照传入的关键字模糊查询到的数据
     */
    @GetMapping("/{pageSize}/{pageNum}")
    public R<?> getPageWithProperty(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNum") Integer pageNum, @RequestParam Map<String, String> keywords) {
        List<Student> students = studentService.getPageWithProperty(pageSize, pageNum, keywords);
        if (!students.isEmpty()) {
            List<StudentVO> studentVOS = conventToVOList(students);
            return R.success(studentVOS);
        } else {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
    }

    /**
     * 添加学生
     *
     * @param student 传入学生对象
     * @return 返回插入结果 不返回data数据
     */
    @PostMapping
    public R<?> addStudent(@RequestBody Student student) {
        Integer rows = studentService.addStudent(student);
        if (rows > 0) {
            return R.success(null);
        } else if (rows == -1) {
            return R.fail(StatusEnum.RECORD_ALREADY_EXISTS);
        } else {
            return R.fail(StatusEnum.FAIL);
        }
    }

    /**
     * 修改学生<br/>
     * 通过学号查询匹配的学生，并将记录按照传入的对象中对应的字段修改
     *
     * @param student 传入带有学生学号字段的对象
     * @return 返回修改结果
     */
    @PutMapping
    public R<?> modifyStudent(@RequestBody Student student) {
        Integer rows = studentService.modifyStudent(student);
        if (rows > 0) {
            return R.success(null);
        } else if (rows == -1) {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        } else {
            return R.fail(StatusEnum.FAIL);
        }
    }

    /**
     * 删除学生<br/>
     * 通过学号删除学生
     * 在删除学生时会同时将学生的成绩一并删除
     *
     * @param keywords 删除学生的关键字 sno学号字段
     * @return 返回删除结果
     */
    @DeleteMapping
    public R<?> removeStudent(@RequestParam Map<String, String> keywords) {
        Integer rows = studentService.removeStudent(keywords);
        if (rows > 0) {
            return R.success(null);
        } else if (rows == 0) {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        } else {
            return R.fail(StatusEnum.FAIL);
        }
    }

    /**
     * 将Student类转换为StudentVO对象 目的在于屏蔽Student类中的敏感字段
     *
     * @param student Student实体类
     * @return 返回StudentVO类型对象 仅返回学生基本信息
     */
    private StudentVO conventToVO(Student student) {
        StudentVO studentVO = new StudentVO(student.getSno(),
                student.getName(),
                student.getEmail(),
                student.getMid(),
                student.getCid());
        return studentVO;
    }

    /**
     * 功能同上，返回List
     *
     * @param students Student类组成的List集合
     * @return 返回VO对象的List集合
     */
    private List<StudentVO> conventToVOList(List<Student> students) {
        List<StudentVO> studentVOList = new ArrayList<>();
        students.forEach(student -> {
            studentVOList.add(conventToVO(student));
        });
        return studentVOList;
    }
}
