package org.demo.studentscore.common;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseTypesEnumTest {
    @Test
    void test() {
        CourseTypesEnum courseTypesEnum = CourseTypesEnum.valueOf("0");
        System.out.println(courseTypesEnum);
    }
}