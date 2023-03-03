/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

//import entities.Client;
import entities.Conducteur;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ConducteurCRUD;

/**
 * FXML Controller class
 *
 * @author 21622
 */
public class ConducteurController implements Initializable {

    @FXML
    private TableView<Conducteur> tabconducteur;
    @FXML
    private TableColumn<Conducteur, String> tabid_conducteur;
    @FXML
    private TableColumn<Conducteur, String> tabcin_conducteur;
    @FXML
    private TableColumn<Conducteur, String> tabnom_conducteur;
    @FXML
    private TableColumn<Conducteur, String> tabprenom_conducteur;
    @FXML
    private TableColumn<Conducteur, String> tabtelephone_conducteur;
    @FXML
    private TableColumn<Conducteur, String> tabemeil_conducteur;
    @FXML
    private TableColumn<Conducteur, String> tabville_conducteur;
    @FXML
    private TableColumn<Conducteur, String> tabmdp_conducteur;
    @FXML
    private TableColumn<Conducteur, String> tabtype_de_permis;
    @FXML
    private TableColumn<Conducteur, String> tabimage_conducteur;
    @FXML
    private TextField tftid_conducteur;
    @FXML
    private TextField tftcin_conducteur;
    @FXML
    private TextField tftnom_conducteur;
    @FXML
    private TextField tftprenom_conducteur;
    @FXML
    private TextField tfttelephone_conducteur;
    @FXML
    private TextField tftemail_conducteur;
    @FXML
    private TextField tftville_conducteur;
    @FXML
    private TextField tftmdp_conducteur;
    @FXML
    private TextField tfttype_de_permis;
    @FXML
    private TextField tftimage_conducteur;
    @FXML
    private Button btnadd_conducteur;
    @FXML
    private Button btnupdate_conducteur;
    @FXML
    private Button btndelete_conducteur;
    ObservableList<Conducteur> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // UpdateTabconducteur();
        ConducteurCRUD c = new ConducteurCRUD();
        ObservableList<Conducteur> conducteurList = FXCollections.observableArrayList(c.ConducteurList());
        tabid_conducteur.setCellValueFactory(new PropertyValueFactory<>("id_conducteur"));
        tabcin_conducteur.setCellValueFactory(new PropertyValueFactory<>("cin_conducteur"));
        tabnom_conducteur.setCellValueFactory(new PropertyValueFactory<>("nom_conducteur"));
        tabprenom_conducteur.setCellValueFactory(new PropertyValueFactory<>("prenom_conducteur"));
        tabtelephone_conducteur.setCellValueFactory(new PropertyValueFactory<>("telephone_conducteur"));
        tabemeil_conducteur.setCellValueFactory(new PropertyValueFactory<>("email_conducteur"));
        tabville_conducteur.setCellValueFactory(new PropertyValueFactory<>("ville_conducteur"));
        tabmdp_conducteur.setCellValueFactory(new PropertyValueFactory<>("mdp_conducteur"));
        tabtype_de_permis.setCellValueFactory(new PropertyValueFactory<>("type_de_permis"));
        tabimage_conducteur.setCellValueFactory(new PropertyValueFactory<>("image_conducteur"));

        tabconducteur.setItems(conducteurList);

    }

    @FXML
    private void add_conducteur(ActionEvent event) {
        int cin_conducteur = Integer.parseInt(tftcin_conducteur.getText());
        String nom_conducteur = tftnom_conducteur.getText();
        String prenom_conducteur = tftprenom_conducteur.getText();
        int telephone_conducteur = Integer.parseInt(tfttelephone_conducteur.getText());
        String email_conducteur = tftemail_conducteur.getText();
        String ville_conducteur = tftville_conducteur.getText();
        String mdp_conducteur = tftmdp_conducteur.getText();
        String type_de_permis = tfttype_de_permis.getText();
        String image_conducteur = tftimage_conducteur.getText();

        Conducteur cn = new Conducteur(0, cin_conducteur, nom_conducteur, prenom_conducteur, telephone_conducteur, email_conducteur, ville_conducteur, mdp_conducteur, type_de_permis, image_conducteur);

        // call the ConducteurCRUD method to add the new conducteur to the database
        ConducteurCRUD conducteurCRUD = new ConducteurCRUD();
        conducteurCRUD.AddConducteur(cn);

        // display a success message and clear the text fields
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setContentText("Le conducteur a été ajouté avec succès.");
        alert.showAndWait();
        //clean(event);
        List<Conducteur> conducteur = conducteurCRUD.ConducteurList();
        //tabconducteur.getItems().setAll(conducteur);
        // List<Conducteur> conducteurs = ConducteurList();
        //tabconducteur.getItems().setAll(conducteur);
        tabconducteur.getItems().setAll(conducteur);
    }

    @FXML
    private void update_conducteur(ActionEvent event) {
        // récupérer les nouvelles valeurs du conducteur à mettre à jour
        String nom_conducteur = tftnom_conducteur.getText();
        String prenom_conducteur = tftprenom_conducteur.getText();
        int cin_conducteur = Integer.parseInt(tftcin_conducteur.getText());
        String ville_conducteur = tftville_conducteur.getText();
        int telephone_conducteur = Integer.parseInt(tfttelephone_conducteur.getText());
        String email_conducteur = tftemail_conducteur.getText();
        String mdp_conducteur = tftmdp_conducteur.getText();
        String type_de_permis = tfttype_de_permis.getText();
        String image_conducteur = tftimage_conducteur.getText();

// récupérer l'ID du conducteur sélectionné dans le tableau
        int id_conducteur = tabconducteur.getSelectionModel().getSelectedItem().getId_conducteur();

// créer un nouvel objet Conducteur avec les nouvelles valeurs
        Conducteur conducteurToUpdate = new Conducteur(id_conducteur,cin_conducteur, nom_conducteur, prenom_conducteur,telephone_conducteur, ville_conducteur, email_conducteur, mdp_conducteur, type_de_permis, image_conducteur);
// appeler la méthode UpDateConducteur() pour mettre à jour le conducteur
        ConducteurCRUD conducteurCRUD = new ConducteurCRUD();
        conducteurCRUD.UpDateConducteur(conducteurToUpdate,id_conducteur);
        
        ConducteurCRUD c = new ConducteurCRUD();
        ObservableList<Conducteur> conducteurList = FXCollections.observableArrayList(c.ConducteurList());
        tabid_conducteur.setCellValueFactory(new PropertyValueFactory<>("id_conducteur"));
        tabcin_conducteur.setCellValueFactory(new PropertyValueFactory<>("cin_conducteur"));
        tabnom_conducteur.setCellValueFactory(new PropertyValueFactory<>("nom_conducteur"));
        tabprenom_conducteur.setCellValueFactory(new PropertyValueFactory<>("prenom_conducteur"));
        tabtelephone_conducteur.setCellValueFactory(new PropertyValueFactory<>("telephone_conducteur"));
        tabemeil_conducteur.setCellValueFactory(new PropertyValueFactory<>("email_conducteur"));
        tabville_conducteur.setCellValueFactory(new PropertyValueFactory<>("ville_conducteur"));
        tabmdp_conducteur.setCellValueFactory(new PropertyValueFactory<>("mdp_conducteur"));
        tabtype_de_permis.setCellValueFactory(new PropertyValueFactory<>("type_de_permis"));
        tabimage_conducteur.setCellValueFactory(new PropertyValueFactory<>("image_conducteur"));

        tabconducteur.setItems(conducteurList);
    }

    @FXML
    private void delete_conducteur(ActionEvent event) {
        ConducteurCRUD pcd = new ConducteurCRUD();
        if (tftid_conducteur.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText("Aucun conducteur supprimée !");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirmation de suppression");
        alert.setContentText("Voulez-vous vraiment supprimer le conducteur ?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            pcd.DeleteConducteur(Integer.parseInt(tftid_conducteur.getText()));
            System.out.println("Conducteur supprimé !");
            //  updatetabclient();
        }
    }
}
