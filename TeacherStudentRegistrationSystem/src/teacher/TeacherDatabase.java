/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import student.Student;

/**
 *
 * @author murad_isgandar
 */
public class TeacherDatabase {

    public static Connection connect() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/studentteacherregister?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "root12345";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;

    }

    public static List<Teacher> getAllTeachers() {
        List<Teacher> list = new ArrayList<>();
        try (Connection conn = connect()) {
            PreparedStatement prpm = conn.prepareStatement("select * from teacher");
            ResultSet rs = prpm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                list.add(new Teacher(id, name, surname, age));
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;
    }

    public static List<Teacher> getTeacher(String name, String surname, Integer age) {
        if (name == null && surname == null && age == null) {
            return getAllTeachers();
        }

        List<Teacher> list = new ArrayList<>();
        try (Connection conn = connect()) {

            PreparedStatement prpm = conn.prepareStatement("select * from teacher where name like ? and surname like ?");
            prpm.setString(1, "%" + name + "%");
            prpm.setString(2, "%" + surname + "%");
            ResultSet rs = prpm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String tname = rs.getString("name");
                String tsurname = rs.getString("surname");
                int tage = rs.getInt("age");
                list.add(new Teacher(id, tname, tsurname, tage));
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return list;

    }

    public static boolean add(Teacher t) {
        try (Connection conn = connect()) {
            PreparedStatement prpm = conn.prepareStatement("insert teacher(name,surname,age) values(?,?,?)");
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

    public static boolean update(Teacher t, Integer id) {
        try (Connection conn = connect()) {
            PreparedStatement prpm = conn.prepareStatement("update teacher set name=?,surname=?,age=? where id=?");
            prpm.setString(1, t.getName());
            prpm.setString(2, t.getSurname());
            prpm.setInt(3, t.getAge());
            prpm.setInt(4, id);

            prpm.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public static boolean delete(Integer id) {
        try (Connection conn = connect()) {
            PreparedStatement prpm = conn.prepareStatement("delete from teacher where id=?");
            prpm.setInt(1, id);

            prpm.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public static List<Student> showStudents(Integer id) {

        List<Student> list = new ArrayList<>();

        try (Connection conn = connect()) {
            PreparedStatement prpm = conn.prepareStatement("select * from student s\n"
                    + "where exists(\n"
                    + "select * from teacher t where t.id = s.teacher_id and t.id =?\n"
                    + ")");
            
            prpm.setInt(1, id);
            
            ResultSet rs = prpm.executeQuery();
            
            while(rs.next()){
                Integer sid = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                Integer age = rs.getInt("age");
                Integer teacher_id = rs.getInt("teacher_id");
                
                list.add(new Student(sid, name, surname, age, teacher_id));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

}
