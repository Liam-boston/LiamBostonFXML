/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usermodel;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author liamb
 */
public class FXMLDocumentController implements Initializable {

    // Database manager
    EntityManager manager;

    @FXML
    private Label label;

    @FXML
    private Button buttonCreate;

    @FXML
    private Button buttonRead;

    @FXML
    private Button buttonUpdate;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonFindByNameAge;

    @FXML
    private Button buttonReadByNameAge;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        manager = (EntityManager) Persistence.createEntityManagerFactory("LiamBostonFXMLPU").createEntityManager();
    }

    /************************ Button Operations *****************************/

    
    @FXML
    void createUser(ActionEvent event) {
        Scanner input = new Scanner(System.in);
        //Usermodel user = new Usermodel();

        // read input from cmd line
        System.out.println("Enter ID: ");
        int id = input.nextInt();

        System.out.println("Enter Name: ");
        String name = input.next();

        System.out.println("Enter Height: ");
        String height = input.next();

        System.out.println("Enter Weight: ");
        Double weight = input.nextDouble();

        System.out.println("Enter Age: ");
        int age = input.nextInt();

        //create usermodel instance
        Usermodel user = new Usermodel();

        //set usermodel values
        user.setId(id);
        user.setName(name);
        user.setHeight(height);
        user.setWeight(weight);
        user.setAge(age);

        create(user);
    }

    @FXML
    void readUser(ActionEvent event) {
        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();

        Usermodel user = readById(id);
        System.out.println(user.toString());
    }

    @FXML
    void updateUser(ActionEvent event) {
        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter ID: ");
        int id = input.nextInt();

        System.out.println("Enter new Name: ");
        String name = input.next();

        System.out.println("Enter new Height: ");
        String height = input.next();

        System.out.println("Enter new Weight: ");
        Double weight = input.nextDouble();

        System.out.println("Enter new Age: ");
        int age = input.nextInt();

        //create usermodel instance
        Usermodel user = new Usermodel();

        //set usermodel values
        user.setId(id);
        user.setName(name);
        user.setHeight(height);
        user.setWeight(weight);
        user.setAge(age);

        // save this student to database by calling Create operation       
        System.out.println("User of id: " + id + " has been updated.");
        update(user);
    }

    @FXML
    void deleteUser(ActionEvent event) {
        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter ID:");
        int id = input.nextInt();

        Usermodel user = readById(id);
        System.out.println("we are deleting this user: " + user.toString());
        delete(user);

    }

    @FXML
    void findByNameAge(ActionEvent event) {
        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter Name:");
        String name = input.next();

        System.out.println("Enter Age:");
        int age = input.nextInt();

        // create a student instance      
        findByNameAndAge(name, age);
    }

    @FXML
    void readByNameAge(ActionEvent event) {
        Scanner input = new Scanner(System.in);

        // read input from command line
        System.out.println("Enter Name:");
        String name = input.next();

        System.out.println("Enter Age:");
        int age = input.nextInt();

        // create a user instance      
        List<Usermodel> users = readByNameAndAge(name, age);

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");

        Query query = manager.createNamedQuery("Usermodel.findAll");
        List<Usermodel> data = query.getResultList();

        for (Usermodel s : data) {
            System.out.println(s.getId() + " " + s.getName() + " " + s.getHeight() + " " + s.getWeight() + " " + s.getAge());
        }
    }

    /************************ Helper Methods *****************************/
    
    public void create(Usermodel user) {
        try {
            // begin transaction
            manager.getTransaction().begin();

            // sanity check
            if (user.getId() != null) {

                // create student
                manager.persist(user);

                // end transaction
                manager.getTransaction().commit();

                System.out.println(user.toString() + " is created");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Usermodel readById(int id) {
        Query query = manager.createNamedQuery("Usermodel.findById");

        // setting query parameter
        query.setParameter("id", id);

        // execute query
        Usermodel user = (Usermodel) query.getSingleResult();
        if (user != null) {
            System.out.println(user.getId() + " " + user.getName() + " " + user.getHeight() + " " + user.getWeight() + " " + user.getAge());
        }

        return user;
    }

    public void update(Usermodel user) {
        try {
            Usermodel existingUser = manager.find(Usermodel.class, user.getId());

            if (existingUser != null) {
                // begin transaction
                manager.getTransaction().begin();

                // update all atttributes
                existingUser.setName(user.getName());
                existingUser.setHeight(user.getHeight());
                existingUser.setWeight(user.getWeight());
                existingUser.setAge(user.getAge());

                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void delete(Usermodel user) {
        try {
            Usermodel existingUser = manager.find(Usermodel.class, user.getId());

            // sanity check
            if (existingUser != null) {

                // begin transaction
                manager.getTransaction().begin();

                //remove student
                manager.remove(existingUser);

                // end transaction
                manager.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int findByNameAndAge(String name, int age) {
        Query query = manager.createNamedQuery("Usermodel.findByNameAndAge");

        // setting query parameter
        query.setParameter("name", name);
        query.setParameter("age", age);

        // execute query
        Usermodel user = (Usermodel)query.getSingleResult();

        if (user != null){
            System.out.println("User with name: " + name + "\nAnd age: " + age + "\nHas an ID of: " + user.getId());
        } else {
            System.out.println("No user was found with those credentials.");
        }

        return user.getId();
    }

    public List<Usermodel> readByNameAndAge(String name, int age) {
        Query query = manager.createNamedQuery("Usermodel.readByNameAndAge");

        // setting query parameter
        query.setParameter("name", name);
        query.setParameter("age", age);

        // execute query
        List<Usermodel> users = query.getResultList();

        if (!users.isEmpty()) {
            for (Usermodel user : users) {
                System.out.println(user.getId() + " " + user.getName() + " " + user.getHeight() + " " + user.getWeight() + " " + user.getAge());
            }
        } else {
            System.out.println("No user was found with those credentials.");
        }

        return users;
    }

}
