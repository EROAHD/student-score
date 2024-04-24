package org.demo.studentscore.service;

import org.demo.studentscore.model.entity.Score;
import org.demo.studentscore.model.vo.ScoreVO;

import java.util.List;
import java.util.Map;

public interface ScoreService {
    /**
     * 通过学号查询学生的成绩
     *
     * @param keywords 查询关键字
     * @return 返回查询到的成绩列表 作为VO对象的返回值
     */
    List<ScoreVO> getPageWithProperty(Integer pageSize, Integer pageNum, Map<String, String> keywords);

    /**
     * 返回影响的行数
     * 传入score对象
     * 通过其中的cid 在course 表中查询是否存在此课程
     * 通过其中的sno 在Student表中查询是狗存在此学生
     * 通过score对象插入成绩
     *
     * @param score 传入Score对象
     * @return 返回影响的行数 <br/> 返回 -1 表示传入的 cid sno 组合的数据已存在<br/>返回 -2 表示学号或课程号未找到
     */
    Integer addScore(Score score);

    Integer modifyScore(Score score);

    Integer deleteScore(Score score);
}
