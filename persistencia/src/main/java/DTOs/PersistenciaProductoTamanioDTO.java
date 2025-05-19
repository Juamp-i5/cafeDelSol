/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOs;

import java.util.List;

/**
 *
 * @author Jp
 */
public class PersistenciaProductoTamanioDTO {

    private PersistenciaTamanioDTO tamanio;
    private List<PersistenciaProductoTamanioIngredienteDTO> ingredientes;

    public PersistenciaProductoTamanioDTO() {
    }

    public PersistenciaProductoTamanioDTO(PersistenciaTamanioDTO tamanio, List<PersistenciaProductoTamanioIngredienteDTO> ingredientes) {
        this.tamanio = tamanio;
        this.ingredientes = ingredientes;
    }

    public PersistenciaTamanioDTO getTamanio() {
        return tamanio;
    }

    public void setTamanio(PersistenciaTamanioDTO tamanio) {
        this.tamanio = tamanio;
    }

    public List<PersistenciaProductoTamanioIngredienteDTO> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<PersistenciaProductoTamanioIngredienteDTO> ingredientes) {
        this.ingredientes = ingredientes;
    }

}
