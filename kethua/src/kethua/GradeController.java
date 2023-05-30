/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kethua;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AD
 */
public class GradeController {
    public List<SVgrademodel> getall(){
         List<SVgrademodel> sv = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try{
            
            String urldb = "jdbc:sqlserver://localhost:1443; databasename=DiemThi; encrypt=false ";
            String userName = "sa";
            String password = "123456";
            connection = DriverManager.getConnection(urldb, userName, password);
            String sql = "select ID, Tensv, diemgk, diemck, tenmon, diemgk+diemck as tongdiem from DiemSV";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                SVgrademodel std = new SVgrademodel(resultSet.getInt("ID"),resultSet.getString("Tensv"),resultSet.getFloat("diemgk"), resultSet.getFloat("diemck"),
                                                    resultSet.getFloat("tongdiem"),resultSet.getString("tenmon"));
                
                sv.add(std);
            }
            System.out.println(2);
        }catch(SQLException ex){
            Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(statement != null){
            try{
                statement.close();
            }
            catch(SQLException ex){
                Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(connection != null){
           try{
               connection.close();
           } catch(SQLException ex){
               Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
   }
        
        return sv;
    }
    public static void insert(SVgrademodel sv){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            String urldb = "jdbc:sqlserver://localhost:1443; databasename=DiemThi; encrypt=false ";
            String userName = "sa";
            String password = "123456";
            connection = DriverManager.getConnection(urldb, userName, password);
            String sql  = "insert into DiemSV(ID, Tensv, diemgk, diemck, tenmon) values(?, ?, ?, ?, ?)";
            statement = connection.prepareCall(sql);
             statement.setInt(1, sv.getId());
            statement.setString(2, sv.getTensv());
            statement.setFloat(3, sv.getDiemgk());
            statement.setFloat(4, sv.getDiemck());
            statement.setString(5, sv.getSubject());
            statement.execute();
        }catch(SQLException ex){
            Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(statement != null){
            try{
                statement.close();
            }
            catch(SQLException ex){
                Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(connection != null){
           try{
               connection.close();
           } catch(SQLException ex){
               Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
}
    }
    public static void delete(int id, String tenmon){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            String urldb = "jdbc:sqlserver://localhost:1443; databasename=DiemThi; encrypt=false ";
            String userName = "sa";
            String password = "123456";
            connection = DriverManager.getConnection(urldb, userName, password);
            String sql = "delete from DIEMSV where ID = ? and tenmon = ?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, id);
            statement.setString(2, tenmon);
            statement.execute();
        }catch(SQLException ex){
            Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(statement != null){
            try{
                statement.close();
            }
            catch(SQLException ex){
                Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(connection != null){
           try{
               connection.close();
           } catch(SQLException ex){
               Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }
    }
   public List<SVgrademodel>hocbong(){
       List<SVgrademodel> sv = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try{
            
            String urldb = "jdbc:sqlserver://localhost:1443; databasename=DiemThi; encrypt=false ";
            String userName = "sa";
            String password = "123456";
            connection = DriverManager.getConnection(urldb, userName, password);
            String sql = "select distinct ID,Tensv,tenmon,case when diemgk+diemck < 10.0 then 'truot'"
                    + " when diemgk+diemck > 10.0 then 'qua'"
                    + "end as Trangthai from DiemSV;";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                SVgrademodel std = new SVgrademodel(resultSet.getInt("ID"),resultSet.getString("Tensv"),resultSet.getString("tenmon"),resultSet.getString("Trangthai"));
                sv.add(std);
            }
            System.out.println(2);
        }catch(SQLException ex){
            Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(statement != null){
            try{
                statement.close();
            }
            catch(SQLException ex){
                Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(connection != null){
           try{
               connection.close();
           } catch(SQLException ex){
               Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
   }
        sv.get(0).getTruot();
        return sv;
    }
   public List<SVgrademodel>HStruot(){
    List<SVgrademodel> sv = new ArrayList<>();
     List<SVgrademodel> student = new ArrayList<>();
     sv = hocbong();
    for(int i = 0; i < sv.size();i++){
        if(sv.get(i).getTruot().equalsIgnoreCase("truot")){
            student.add(sv.get(i));
        }
    }
    System.out.println(sv.get(0).getTruot());
    return student;
}
 public List<SVgrademodel>Hocbong(){
     List<SVgrademodel> sv = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try{
            
            String urldb = "jdbc:sqlserver://localhost:1443; databasename=DiemThi; encrypt=false ";
            String userName = "sa";
            String password = "123456";
            connection = DriverManager.getConnection(urldb, userName, password);
            String sql = "  select ID,Tensv,avg(diemgk+diemck) as trungbinh,case when avg(diemgk+diemck) < 15 then'A'when avg(diemgk+diemck) > 15 then 'B'when avg(diemgk+diemck) = 15 then 'C'end as  chu from DiemSV group by ID,Tensv";
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                SVgrademodel std = new SVgrademodel(resultSet.getInt("ID"),resultSet.getString("Tensv"),resultSet.getFloat("trungbinh"),resultSet.getString("chu"));
                sv.add(std);
            }
            System.out.println(2);
        }catch(SQLException ex){
            Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(statement != null){
            try{
                statement.close();
            }
            catch(SQLException ex){
                Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(connection != null){
           try{
               connection.close();
           } catch(SQLException ex){
               Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
   }
        return sv;
 }
 public List<SVgrademodel>hocbong1(){
     List<SVgrademodel> sv = new ArrayList<>();
     List<SVgrademodel> student = new ArrayList<>();
     sv = HStruot();
     student = Hocbong();
     for(int i = 0; i < sv.size();i++){
         for(int j = 0; j < student.size();j++){
             if(sv.get(i).getId() == student.get(j).getId()){
                 student.remove(student.get(j));
             }
         }
     }
    return student;
 }
 public List<SVgrademodel>Findgrade(int ID){
     List<SVgrademodel> sv = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            String urldb = "jdbc:sqlserver://localhost:1443; databasename=DiemThi; encrypt=false ";
            String userName = "sa";
            String password = "123456";
            connection = DriverManager.getConnection(urldb, userName, password);
            String sql = "select diemgk, diemck, tenmon, diemgk+diemck as tongdiem from DiemSV where ID = ?";
            statement = connection.prepareCall(sql);
            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                SVgrademodel std = new SVgrademodel(resultSet.getFloat("diemgk"),resultSet.getFloat("diemck"),resultSet.getFloat("tongdiem"),
                                                    resultSet.getString("tenmon"));
                sv.add(std);
            }
        }catch(SQLException ex){
            Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            if(statement != null){
            try{
                statement.close();
            }
            catch(SQLException ex){
                Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(connection != null){
           try{
               connection.close();
           } catch(SQLException ex){
               Logger.getLogger(StudentManager.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
    }
        return sv;
    }
 }

