package org.demo.studentscore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.demo.studentscore.model.entity.Score;
import org.demo.studentscore.model.vo.ScoreVO;

import java.util.List;

@Mapper
public interface ScoreMapper extends BaseMapper<Score> {
    List<ScoreVO> selectAllInfo(@Param("sno") Integer sno);
}
