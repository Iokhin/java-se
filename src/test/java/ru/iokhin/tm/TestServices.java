package ru.iokhin.tm;

public class TestServices {
    TestRep tr;

    public void addStudent(String name) {
        tr.addStudent(name);
    }

    public void listStudent() {
        tr.listStudents();
    }

    public TestServices(TestRep tr) {
        this.tr = tr;
    }
}
