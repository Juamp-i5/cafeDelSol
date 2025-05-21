/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BOs.salidas;


import DTOs.CRUDSalidas.DetalleSalidaDTO;
import DTOs.CRUDSalidas.SalidaListDTO;
import DTOs.CRUDSalidas.SalidaNuevaDTO;
import DTOs.salidas.DetalleSalidaDTOPersistencia;
import DTOs.salidas.SalidaListDTOPersistencia;
import DTOs.salidas.SalidaNuevaDTOPersistencia;
import entidades.Salida;
import org.bson.types.ObjectId;


/**
 *
 * @author katia
 */
public class SalidaMapper implements ISalidaMapper{
    private static SalidaMapper instancia;

    private SalidaMapper() {}

    public static SalidaMapper getInstance() {
        if (instancia == null) {
            instancia = new SalidaMapper();
        }
        return instancia;
    }
    
    @Override
    public SalidaNuevaDTOPersistencia toSalidaNuevaDTOPersistencia(SalidaNuevaDTO dto) {
        return new SalidaNuevaDTOPersistencia(
            dto.getFecha(),
            dto.getIdIngrediente(),
            dto.getCantidad(),
            dto.getMotivo()
        );
    }

    @Override
    public SalidaListDTO toSalidaListDTO(SalidaListDTOPersistencia dto) {
        return new SalidaListDTO(
            dto.getId(),
            dto.getNombreIngrediente(),
            dto.getCantidad(),
            dto.getMotivo(),
            dto.getFecha()
        );
    }

    @Override
    public DetalleSalidaDTO toDetalleSalidaDTO(DetalleSalidaDTOPersistencia dto) {
        return new DetalleSalidaDTO(
            dto.getNombreIngrediente(),
            dto.getCantidad(),
            dto.getMotivo(),
            dto.getFecha()
        );
    }

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
}
