/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Client;
import interfaces.ClientInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 *
 * @author 21622
 */
public class ClientCRUD implements ClientInterface<Client> {

    private final String url = "jdbc:mysql://localhost:3306/autoxpress";
    private final String user = "root";
    private final String password = "";

    @Override
    //Addclient
    public void AddClient(Client c) {
        String sql = "INSERT INTO client(nom_client, prenom_client, cin_client, ville_client, telephone_client, email_client, mdp_client) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.getNom_client());
            pstmt.setString(2, c.getPrenom_client());
            pstmt.setInt(3, c.getCin_client());
            pstmt.setString(4, c.getVille_client());
            pstmt.setInt(5, c.getTelephone_client());
            pstmt.setString(6, c.getEmail_client());
            pstmt.setString(7, c.getMdp_client());
            pstmt.executeUpdate();
            System.out.println("Le client a été ajouté avec succès !");

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public void UpDateClient(Client c) {
        String sql = "UPDATE client SET nom_client = ?, prenom_client = ?, cin_client = ?, ville_client = ?, telephone_client = ?, email_client = ?, mdp_client = ? WHERE id_client = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, c.getNom_client());
            pstmt.setString(2, c.getPrenom_client());
            pstmt.setInt(3, c.getCin_client());
            pstmt.setString(4, c.getVille_client());
            pstmt.setInt(5, c.getTelephone_client());
            pstmt.setString(6, c.getEmail_client());
            pstmt.setString(7, c.getMdp_client());

            pstmt.executeUpdate();
            System.out.println("Le client a été modifié avec succès ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void DeleteClient(int id_client) {

        String sql = "DELETE FROM client WHERE id_client = ?";

        try (Connection conn = DriverManager.getConnection(url, user, password);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id_client);
            pstmt.executeUpdate();
            System.out.println(" Le client a été supprimé avec succès");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Client> ClientList() {

        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Client cd = new Client();
                cd.setId_client(rs.getInt("id_client"));
                cd.setNom_client(rs.getString("nom_client"));
                cd.setPrenom_client(rs.getString("prenom_client"));
                cd.setCin_client(rs.getInt("cin_client"));
                cd.setVille_client(rs.getString("ville_client"));
                cd.setTelephone_client(rs.getInt("telephone_client"));
                cd.setEmail_client(rs.getString("email_client"));
                cd.setMdp_client(rs.getString("mdp_client"));
                clients.add(cd);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return clients;
    }
public ObservableList<Client> getDataClient() {
     ObservableList<Client> list = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Client";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Client c = new Client();
                c.setId_client(rs.getInt("id_client"));
                c.setNom_client(rs.getString("nom_conducteur"));
                c.setPrenom_client(rs.getString("prenom_client"));
                c.setCin_client(rs.getInt("cin_client"));
                c.setVille_client(rs.getString("ville_client"));
                c.setTelephone_client(rs.getInt("telephone_client"));
                c.setEmail_client(rs.getString("email_client"));
                c.setMdp_client(rs.getString("mdp_client"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
