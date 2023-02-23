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
public interface ConducteurInterface <Conducteur>{
    void AddConducteur (Conducteur cl);
    void UpDateConducteur (Conducteur cl);
    void DeleteConducteur (int id_conducteur);
    public List <Conducteur> ConducteurList();
    
}
