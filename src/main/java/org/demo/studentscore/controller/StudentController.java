package org.demo.studentscore.controller;

import org.demo.studentscore.common.R;
import org.demo.studentscore.common.StatusEnum;
import org.demo.studentscore.model.entity.Student;
import org.demo.studentscore.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    StudentService studentService;

    // 构造器注入 service 成员变量
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 通过学号获取一个学生
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<?> getById(@PathVariable("id") Integer id) {
        Student student = studentService.getById(id);
        if (student != null) {
            return R.success(student);
        } else {
            return R.fail(StatusEnum.RECORD_NOT_FOUND);
        }
    }

    /**
     * 分页查询学生信息
     *
     * @param pageSize
     * @param pageNum
     * @return
     */
    @GetMapping("/{pageSize}/{pageNum}")
    public R<List<Student>> getPage(@PathVariable("pageSize") Integer pageSize, @PathVariable("pageNum") Integer pageNum) {
        return R.success(studentService.getPage(pageSize, pageNum));
    }


    /**
     * 模糊查询学生
     *
     * @param keywords 从前端传入的关键字map键值对
     * @return 按照传入的关键字模糊查询到的数据
     */
    @GetMapping
    public R<?> getWithProperty(@RequestParam Map<String,String> keywords) {
        List<Student> students = studentService.getWithProperty(keywords);
        if (students != null)
            return R.success(students);
        return R.fail(StatusEnum.RECORD_NOT_FOUND);
    }

}
