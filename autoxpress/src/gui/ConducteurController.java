/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Conducteur;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import services.ConducteurCRUD;

/**
 * FXML Controller class
 *
 * @author 21622
 */
public class ConducteurController implements Initializable {

    @FXML
    private TableView<?> tabconducteur;
    @FXML
    private TableColumn<?, ?> tabid_conducteur;
    @FXML
    private TableColumn<?, ?> tabcin_conducteur;
    @FXML
    private TableColumn<?, ?> tabnom_conducteur;
    @FXML
    private TableColumn<?, ?> tabprenom_conducteur;
    @FXML
    private TableColumn<?, ?> tabtelephone_conducteur;
    @FXML
    private TableColumn<?, ?> tabemeil_conducteur;
    @FXML
    private TableColumn<?, ?> tabville_conducteur;
    @FXML
    private TableColumn<?, ?> tabmdp_conducteur;
    @FXML
    private TableColumn<?, ?> tabtype_de_permis;
    @FXML
    private TableColumn<?, ?> tabimage_conducteur;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    }

    @FXML
    private void update_conducteur(ActionEvent event) {
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
