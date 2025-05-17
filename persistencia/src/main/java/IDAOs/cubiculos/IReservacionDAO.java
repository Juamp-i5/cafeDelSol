/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs.cubiculos;

import entidades.Reservacion;
import java.util.List;
import org.bson.conversions.Bson;

/**
 *
 * @author rodri
 */
public interface IReservacionDAO {
    
    public Reservacion agregarReservacion(Reservacion reservacion);
    
    public Reservacion actualizarReservacion(Reservacion reservacion);
    
    public List<Reservacion> buscarReservaciones();
    
    public Reservacion buscarPorId(Long id);
    
    public List<Reservacion> buscarPorFiltro(Bson filtro);
}
