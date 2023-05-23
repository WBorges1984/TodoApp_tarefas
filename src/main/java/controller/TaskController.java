/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author willi
 */
public class TaskController {
    
    public void save(Task task) {
        
        String sql = "INSERT INTO tasks (idProject"
                + "name,"
                + "description,"
                + "completed,"
                + "notes,"
                + "deadline,"
                + "createdAt,"
                + "updateAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa " + ex.getMessage(), ex);
            
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
        
    }
    
    public void update(Task task) {
        String sql = "UPDATE tasks SET idProject = ?, "
                + "name= ?, "
                + "description = ?,"
                + "notes = ?,  "
                + "completed = ?,"
                + "deadline = ?,"
                + "createdAt = ?,"
                + "updateAt = ?,"
                + "WHERE id= ?";
        
        Connection connecetion = null;
        PreparedStatement statement = null;
        
        try {
            connecetion = ConnectionFactory.getConnection();
            statement = connecetion.prepareCall(sql);
            
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setString(4, task.getNotes());
            statement.setBoolean(5, task.isIsCompleted());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.setInt(9, task.getId());
            statement.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa " + ex.getMessage(), ex);
        }
    }
    
    public void removeById(int taskId) throws SQLDataException, SQLException {
        String sql = "DELETE FROM TASK WHERE ID = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareCall(sql);
            statement.setInt(1, taskId);
            statement.execute();
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar a tarefa" + ex.getMessage(), ex);
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    public List<Task> getAll(int idProject) {
        
        String sql = "SELECT * FROM tasks WHERE idProject= ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resulSet = null;
        
        List<Task> tasks = new ArrayList<Task>();
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareCall(sql);
            statement.setInt(1, idProject);
            resulSet =  statement.executeQuery();
            
            while (resulSet.next()) {
                Task task = new Task();
                
                task.setId(resulSet.getInt("id"));
                task.setIdProject(resulSet.getInt("idProject"));
                task.setName(resulSet.getString("name"));
                task.setDescription(resulSet.getString("description"));
                task.setNotes(resulSet.getString("notes"));
                task.setIsCompleted(resulSet.getBoolean("completed"));
                task.setDeadline(resulSet.getDate("deadline"));
                task.setCreatedAt(resulSet.getDate("createdAt"));
                task.setUpdatedAt(resulSet.getDate("updateAt"));
                
                tasks.add(task);
                
            }
            
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao inserir a tarefa" + ex.getMessage(), ex);
        }finally{
            ConnectionFactory.closeConnection(connection, statement, resulSet);
        }
        
        return tasks;
    }
    
}
