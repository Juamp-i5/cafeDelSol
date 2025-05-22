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

    public boolean registrarSalida(Salida salida) throws PersistenciaSalidasException;

    public List<Salida> consultarTodas() throws PersistenciaSalidasException;

    public List<Salida> consultarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaSalidasException;

    public Salida consultarPorId(ObjectId id) throws PersistenciaSalidasException;

}
