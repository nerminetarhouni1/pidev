/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Client;
import interfaces.ClientInterface;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.ClientCRUD;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author 21622
 */
public class ClientController implements Initializable {

    @FXML
    private TableView<Client> tabclient;
    @FXML
    private TableColumn<Client, Integer> tabid_client;
    @FXML
    private TableColumn<?, ?> tabnom_client;
    @FXML
    private TableColumn<?, ?> tabprenom_client;
    @FXML
    private TableColumn<?, ?> tabcin_client;
    @FXML
    private TableColumn<?, ?> tabville_client;
    @FXML
    private TableColumn<?, ?> tabtelephone_client;
    @FXML
    private TableColumn<?, ?> tabemail_client;
    @FXML
    private TableColumn<?, ?> tabmdp_client;
    @FXML
    private TextField tftid_client;
    @FXML
    private TextField tftville_client;
    @FXML
    private TextField tftnom_client;
    @FXML
    private TextField tfttelephonr_client;
    @FXML
    private TextField tftprenom_client;
    @FXML
    private TextField tftmdp_client;
    @FXML
    private TextField tftcin_client;
    @FXML
    private TextField tftemail_client;
    @FXML
    private Button btndelete_client;
    @FXML
    private Button btnupdate_client;
    @FXML
    private Button btnadd_client;

    ObservableList<Client> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UpdateTabClient();
        // update the table view with the new data
        ClientCRUD ocd = new ClientCRUD();
        List<Client> clients = ocd.ClientList();
        tabclient.getItems().setAll(clients);

    }

    @FXML
    private void delete_client(ActionEvent event) {

        ClientCRUD pcd = new ClientCRUD();

        if (tftid_client.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Aucun client supprimée !");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Voulez-vous vraiment supprimer le client ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            pcd.DeleteClient(Integer.parseInt(tftid_client.getText()));
            System.out.println("Client supprimé !");
            //  updatetabclient();
        }

        //clean(event);
    }

    @FXML
    private void update_client(ActionEvent event) throws NoSuchAlgorithmException {
        // Récupérer les informations du client à partir des champs de texte
        int id = Integer.parseInt(tftid_client.getText());
        String nom = tftnom_client.getText();
        String prenom = tftprenom_client.getText();
        int cin = Integer.parseInt(tftcin_client.getText());
        String ville = tftville_client.getText();
        int telephone = Integer.parseInt(tfttelephonr_client.getText());
        String email = tftemail_client.getText();
        String mdp = tftmdp_client.getText();

        // Créer un objet Client avec ces informations
        Client client = new Client(id, nom, prenom, cin, ville, telephone, email, mdp);

        // Appeler la méthode UpDateClient avec cet objet Client et l'ID du client à modifier
        ClientCRUD c=new ClientCRUD();
        c.UpDateClient(client, id);

        // Rafraîchir la table view pour afficher les modifications
        List<Client> clients = c.ClientList();
        tabclient.getItems().setAll(clients);
    }

    @FXML
    private void add_client(ActionEvent event) {
        // get the values from the text fields
        String nom_client = tftnom_client.getText();
        String prenom_client = tftprenom_client.getText();
        int cin_client = Integer.parseInt(tftcin_client.getText());
        String ville_client = tftville_client.getText();
        int telephone_client = Integer.parseInt(tfttelephonr_client.getText());
        String email_client = tftemail_client.getText();
        String mdp_client = tftmdp_client.getText();

        // create a new Client object with the entered values
        Client newClient = new Client(nom_client, prenom_client, cin_client, ville_client, telephone_client, email_client, mdp_client);

        // call the ClientCRUD method to add the new client to the database
        ClientCRUD clientCRUD = new ClientCRUD();
        clientCRUD.AddClient(newClient);

        // display a success message and clear the text fields
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setContentText("Le client a été ajouté avec succès.");
        alert.showAndWait();
        //clean(event);

        // update the table view with the new data
        List<Client> clients = clientCRUD.ClientList();
        tabclient.getItems().setAll(clients);
    }

    public void UpdateTabClient() {
        //System.out.println("sdfsdf");
        ClientCRUD cv = new ClientCRUD();

        tabid_client.setCellValueFactory(new PropertyValueFactory<>("id_client"));
        tabnom_client.setCellValueFactory(new PropertyValueFactory<>("nom_client"));
        tabprenom_client.setCellValueFactory(new PropertyValueFactory<>("prenom_client"));
        tabcin_client.setCellValueFactory(new PropertyValueFactory<>("cin_client"));
        tabville_client.setCellValueFactory(new PropertyValueFactory<>("ville_client"));
        tabtelephone_client.setCellValueFactory(new PropertyValueFactory<>("telephone_client"));
        tabemail_client.setCellValueFactory(new PropertyValueFactory<>("email_client"));
        tabmdp_client.setCellValueFactory(new PropertyValueFactory<>("mdp_client"));
        list = cv.getDataClient();
        tabclient.setItems(FXCollections.observableArrayList());
        // tabclient.setItems(list);    
        //List<Client> list = cv.ClientList();
        //System.out.println(list);
        //tabclient.setItems(FXCollections.observableArrayList(list));
    }

}
