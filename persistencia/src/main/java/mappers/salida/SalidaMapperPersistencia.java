/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers.salida;

import DTOs.salidas.DetalleSalidaDTOPersistencia;
import DTOs.salidas.SalidaListDTOPersistencia;
import DTOs.salidas.SalidaNuevaDTOPersistencia;
import entidades.Salida;
import interfacesMappers.salidas.ISalidaMapperPersistencia;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author katia
 */
public class SalidaMapperPersistencia implements ISalidaMapperPersistencia{
    @Override
    public Salida toEntity(SalidaNuevaDTOPersistencia dto) {
        return new Salida(
            dto.getFecha(),
            new ObjectId(dto.getIdIngrediente()),
            dto.getCantidad(),
            dto.getMotivo()
        );
    }

    @Override
    public SalidaListDTOPersistencia toSalidaListDTO(Salida salida, String nombreIngrediente) {
        return new SalidaListDTOPersistencia(
            salida.getId().toHexString(),
            nombreIngrediente,
            salida.getCantidad(),
            salida.getMotivo(),
            salida.getFecha()
        );
    }

    @Override
    public DetalleSalidaDTOPersistencia toDetalleSalidaDTO(Salida salida, String nombreIngrediente) {
        return new DetalleSalidaDTOPersistencia(
            nombreIngrediente,
            salida.getCantidad(),
            salida.getMotivo(),
            salida.getFecha()
        );
    }
    
    @Override
    public List<SalidaListDTOPersistencia> toSalidaListDTOList(List<Salida> salidas, List<String> nombresIngredientes) {
        List<SalidaListDTOPersistencia> lista = new ArrayList<>();
        for (int i = 0; i < salidas.size(); i++) {
            lista.add(toSalidaListDTO(salidas.get(i), nombresIngredientes.get(i)));
        }
        return lista;
    }

    @Override
    public List<DetalleSalidaDTOPersistencia> toDetalleSalidaDTOList(List<Salida> salidas, List<String> nombresIngredientes) {
        List<DetalleSalidaDTOPersistencia> lista = new ArrayList<>();
        for (int i = 0; i < salidas.size(); i++) {
            lista.add(toDetalleSalidaDTO(salidas.get(i), nombresIngredientes.get(i)));
        }
        return lista;
    }    
}
