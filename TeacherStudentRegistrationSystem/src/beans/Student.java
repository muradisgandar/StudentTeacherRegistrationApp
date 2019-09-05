/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author murad_isgandar
 */
public class Student {
    private Integer id;
    private String name;
    private String surname;
    private Integer age;
    private Integer teacher_id;

    public Student() {
    }

    public Student(Integer id, String name, String surname, Integer age, Integer teacher_id) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.teacher_id = teacher_id;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + age + ", teacher_id=" + teacher_id + '}';
    }
    
    
    
    
    
    
    
}
