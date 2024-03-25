package org.demo.studentscore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.demo.studentscore.model.entity.Course;

@Mapper
public interface CourseMapper extends BaseMapper<Course> {
}
