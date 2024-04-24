package org.demo.studentscore.model.entity;

import org.junit.jupiter.api.Test;

class StudentTest {

    @Test
    void getSno() {
        Student student1 = new Student();
        student1.setSno(908129031L);
        System.out.println(student1.getSno().toString());
    }
}