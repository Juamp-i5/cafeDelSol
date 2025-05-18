package IDAOs.entradas;

import entidades.Entrada;
import excepciones.PersistenciaEntradasException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author pablo
 */
public interface IEntradaDAO {
    
    public boolean registrarEntrada(Entrada entrada) throws PersistenciaEntradasException;
    
    public List<Entrada> obtenerEntradasPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaEntradasException;
}
