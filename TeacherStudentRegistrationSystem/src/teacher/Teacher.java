/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacher;

/**
 *
 * @author murad_isgandar
 */
public class Teacher {
    private Integer id;
    private String name;
    private String surname;
    private int age;

    public Teacher() {
    }

    public Teacher(Integer id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Teacher{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + age + '}';
    }
    
    
    
    
}
