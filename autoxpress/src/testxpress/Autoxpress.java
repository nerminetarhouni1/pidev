/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testxpress;

import entities.Client;
import entities.Conducteur;
import services.ClientCRUD;
import services.ConducteurCRUD;

/**
 *
 * @author 21622
 */
public class Autoxpress {

    /**
     * @param args the command line arguments
     */
    //AddClient
    public static void main(String[] args) {
        // TODO code application logic here
        Client c = new Client();
        c.setNom_client("dridi");
        c.setPrenom_client("farah");
        c.setCin_client(07526525);
        c.setVille_client("tunis");
        c.setTelephone_client(25541585);
        c.setEmail_client("farah.toumi@gmail.com");
        c.setMdp_client("kjyufjuyfkfuyl");
       // ClientCRUD cl = new ClientCRUD();
        
       //cl.AddClient(c);

        Conducteur cd = new Conducteur();
        cd.setNom_conducteur("toumi");
        cd.setPrenom_conducteur("khalil");
        cd.setCin_conducteur(07245425);
        cd.setVille_conducteur("tunis");
        cd.setTelephone_conducteur(20551505);
        cd.setEmail_conducteur("khalil.toumi@gmail.com");
        cd.setMdp_conducteur("kjyufjuyfkfuyl");
        cd.setType_de_permis("a");
       // ConducteurCRUD c2 = new ConducteurCRUD();

        //c2.AddConducteur(cd);
    
    Client g = new Client ();
    //ClientCRUD d= new ClientCRUD ();
    //d.DeleteClient(1);

     Conducteur h = new Conducteur ();
    //ConducteurCRUD  v = new ConducteurCRUD ();
   // v.DeleteConducteur(1);
   
    ClientCRUD rs =new ClientCRUD();
    System.out.println(rs.ClientList());
    
    ConducteurCRUD cf =new ConducteurCRUD();
    System.out.println(cf.ConducteurList());
    
    }
    
}
