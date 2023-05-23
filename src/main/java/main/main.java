/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import controller.ProjectController;
import java.sql.Connection;
import java.util.List;
import model.Project;
import util.ConnectionFactory;

/**
 *
 * @author willi
 */
public class main {

    public static void main(String[] args) {
        ProjectController projectController = new ProjectController();
        
        Project project = new Project();
        project.setName("Projeto teste");
        project.setDescription("Descricao");
        projectController.save(project);
        
       // project.setName("Novo nome do projeto");
       // projectController.update(project);
        
       // List<Project> projects = projectController.getAll();
       // System.out.println("Total de projetos: " + projects.size());
        
    }
}
