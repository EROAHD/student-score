package org.demo.studentscore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.demo.studentscore.pojo.Student;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {

}
