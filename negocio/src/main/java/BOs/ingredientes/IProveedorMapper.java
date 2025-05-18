/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BOs.ingredientes;

import DTOs.CRUDIngredientes.ProveedorViejoDTO;
import DTOs.ingredientes.ProveedorDTOPersistencia;
import java.util.List;

/**
 *
 * @author norma
 */
public interface IProveedorMapper {

    public List<ProveedorViejoDTO> toDTOList(List<ProveedorDTOPersistencia> listaPersistencia);
}
