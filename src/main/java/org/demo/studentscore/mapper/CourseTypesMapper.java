package org.demo.studentscore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.demo.studentscore.model.entity.CourseTypes;

@Mapper
public interface CourseTypesMapper extends BaseMapper<CourseTypes> {
}
