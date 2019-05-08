package ru.iokhin.tm;

import java.util.ArrayList;
import java.util.Collection;

public class TestRep {
    Collection<TestEntityStudent> studentArrayList = new ArrayList<>(0);

    public void addStudent(String name) {
        studentArrayList.add(new TestEntityStudent(name));
    }

    public  void listStudents() {
        for (TestEntityStudent student : studentArrayList) {
            System.out.println(student.getName());
        }
    }

}
