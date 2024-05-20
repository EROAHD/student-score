package org.demo.studentscore.service;

public interface DashBoardService {
    Long getStudentScoreReports(String minScore, String maxScore);

    Long getUserCountReports(Integer userType);

    Long getCourseCountReports(Integer courseType);
}
