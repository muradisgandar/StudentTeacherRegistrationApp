/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractdb;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author murad_isgandar
 */
public abstract class AbstractDatabase<T> {
    
    public static Connection connect() throws Exception {

        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/studentteacherregister?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "root12345";
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;

    }
    
    public abstract boolean add(T obj);
    public abstract boolean update(T obj,Integer id);
    public abstract boolean delete(Integer id);
    
    
    
    
    
    
    
    
    
    
}
