/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databases;

import beans.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import databases.AbstractDatabase;

/**
 *
 * @author murad_isgandar
 */
public class StudentDatabase extends AbstractDatabase<Student> {

    public static List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();

        try (Connection conn = connect()) {
            PreparedStatement prpm = conn.prepareStatement("select * from student");
            ResultSet rs = prpm.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Integer age = rs.getInt("age");
                Integer teacher_id = rs.getInt("teacher_id");

                list.add(new Student(id, name, surname, age, teacher_id));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public static List<Student> getStudent(String name, String surname, Integer age, Integer teacher_id) {
        if (name == null && surname == null && age == null && teacher_id == null) {
            return getAllStudents();
        }

        List<Student> list = new ArrayList<>();

        try (Connection conn = connect()) {
            PreparedStatement prpm = conn.prepareStatement("select * from student where name like ? and surname like ?");
            prpm.setString(1, "%" + name + "%");
            prpm.setString(2, "%" + surname + "%");

            ResultSet rs = prpm.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String sname = rs.getString("name");
                String ssurname = rs.getString("surname");
                Integer sage = rs.getInt("age");
                Integer steacher_id = rs.getInt("teacher_id");

                list.add(new Student(id, sname, ssurname, sage, steacher_id));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    @Override
    public boolean add(Student t) {
        try (Connection conn = connect()) {
            PreparedStatement prpm = conn.prepareStatement("insert student(name,surname,age) values(?,?,?)");
            prpm.setString(1, t.getName());
            prpm.setString(2, t.getSurname());
            prpm.setInt(3, t.getAge());

            prpm.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    @Override
    public boolean update(Student s) {
        try (Connection conn = connect()) {

            StringBuilder queryStr = new StringBuilder("update student set name=name");

            if (s.getName() != null && !s.getName().isEmpty()) {
                queryStr.append(", name=?");
            }
            if (s.getSurname() != null && !s.getSurname().isEmpty()) {
                queryStr.append(", surname=?");
            }
            if (s.getAge() != null) {
                queryStr.append(", age=?");
            }

            queryStr.append(" where id=?");

            PreparedStatement prpm = conn.prepareStatement(queryStr.toString());
            int i = 0;
            if (s.getName() != null && !s.getName().isEmpty()) {
                prpm.setString(++i, s.getName());
            }
            if (s.getSurname() != null && !s.getSurname().isEmpty()) {
                prpm.setString(++i, s.getSurname());
            }
            if (s.getAge() != null) {
                prpm.setInt(++i, s.getAge());
            }

            prpm.setInt(++i, s.getId());

            prpm.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    @Override
    public boolean delete(Integer id) {
        try (Connection conn = connect()) {
            PreparedStatement prpm = conn.prepareStatement("delete from student where id=?");
            prpm.setInt(1, id);

            prpm.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public static boolean addTeacher(Integer teacher_id, Integer id) {
        try (Connection conn = connect()) {
            PreparedStatement prpm = conn.prepareStatement("update student set teacher_id=? where id=?");
            prpm.setInt(1, teacher_id);
            prpm.setInt(2, id);

            prpm.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

}
