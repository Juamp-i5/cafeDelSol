/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package IDAOs.salidas;

import entidades.Salida;
import excepciones.PersistenciaSalidasException;
import java.time.LocalDate;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author katia
 */
public interface ISalidaDAO {
    /**
     * Registra una nueva salida en la base de datos.
     * @param salida Salida que se desea perssitir.
     * @return True si la inserción fue exitosa.
     * @throws PersistenciaSalidasException Por si ocurre un error al insertar la salida.
     */
    public boolean registrarSalida(Salida salida) throws PersistenciaSalidasException;
    
    /**
     * Consulta todas las salidas almacenadas en la base de datos.
     * @return Lista de objetos tipo Salida.
     * @throws PersistenciaSalidasException Por si ocurre un error al recuperar las salidas.
     */
    public List<Salida> consultarTodas() throws PersistenciaSalidasException;
    
    /**
     * Consulta las salidas registradas en un periodo específico.
     * @param fechaInicio Fecha de inicio del rango.
     * @param fechaFin Fecha de fin del rango.
     * @return Lista de salidas dentro del rango de fechas.
     * @throws PersistenciaSalidasException En caso de error durante la consulta.
     */
    public List<Salida> consultarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaSalidasException;
    
    /**
     * Consulta una salida específica por su identificador único.
     * @param id Identificador único de MongoDB.
     * @return Objeto de tipo Salida correspondiente al ID.
     * @throws PersistenciaSalidasException Si no se encuentra la salida o si ocurre un error al obtenerla.
     */
    public Salida consultarPorId(ObjectId id) throws PersistenciaSalidasException;

}
