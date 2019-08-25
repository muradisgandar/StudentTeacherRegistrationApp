/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import abstractdb.AbstractDatabase;
/**
 *
 * @author murad_isgandar
 */
public class StudentDatabase extends AbstractDatabase<Student> {
    
    
    
    public static List<Student> getAllStudents(){
        List<Student> list = new ArrayList<>();
        
        try(Connection conn = connect()) {
            PreparedStatement prpm = conn.prepareStatement("select * from student");
            ResultSet rs = prpm.executeQuery();
            
            while(rs.next()){
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
    
    public static List<Student> getStudent(String name,String surname,Integer age,Integer teacher_id ){
        if(name==null && surname==null && age==null && teacher_id==null){
            return getAllStudents();
        }
        
        
        List<Student> list = new ArrayList<>();
        
        try(Connection conn = connect()) {
            PreparedStatement prpm = conn.prepareStatement("select * from student where name like ? and surname like ?");
            prpm.setString(1,"%"+ name+"%");
            prpm.setString(2,"%"+ surname+"%");
            
            ResultSet rs = prpm.executeQuery();
            
            while(rs.next()){
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
    public  boolean add(Student t){
        try(Connection conn = connect()) {
            PreparedStatement prpm = conn.prepareStatement("insert student(name,surname,age) values(?,?,?)");
            prpm.setString(1,t.getName());
            prpm.setString(2,t.getSurname());
            prpm.setInt(3,t.getAge());
            
            prpm.execute();
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
        
    }
    
    @Override
    public  boolean update(Student t,Integer id){
        try(Connection conn = connect()) {
           PreparedStatement prpm = conn.prepareStatement("update student set name=?,surname=?,age=? where id=?");
            prpm.setString(1,t.getName());
            prpm.setString(2,t.getSurname());
            prpm.setInt(3,t.getAge());  
            prpm.setInt(4, id);
            
            prpm.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
        
    }
    
    @Override
     public boolean delete(Integer id){
        try(Connection conn = connect()) {
           PreparedStatement prpm = conn.prepareStatement("delete from student where id=?");
            prpm.setInt(1,id);
            
            prpm.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
        
    }
     
    public static boolean addTeacher(Integer teacher_id,Integer id){
        try(Connection conn = connect()) {
           PreparedStatement prpm = conn.prepareStatement("update student set teacher_id=? where id=?");
            prpm.setInt(1,teacher_id);
            prpm.setInt(2, id);
            
            prpm.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
        
    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
