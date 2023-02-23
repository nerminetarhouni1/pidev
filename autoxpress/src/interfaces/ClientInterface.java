/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 * @author 21622
 */
public interface ClientInterface <Client>{
    void AddClient(Client c);
    void UpDateClient (Client c);
    void DeleteClient (int id_client);
    public List <Client> ClientList();
    
    
}
