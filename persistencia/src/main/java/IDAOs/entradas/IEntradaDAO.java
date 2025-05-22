package IDAOs.entradas;

import DTOs.entradas.EntradaNuevaDTOPersistencia;
import DTOs.entradas.EntradaViejaDTOPersistencia;
import excepciones.PersistenciaEntradasException;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author pablo
 */
public interface IEntradaDAO {
    
    public boolean registrarEntrada(EntradaNuevaDTOPersistencia entrada) throws PersistenciaEntradasException;
    
    public EntradaViejaDTOPersistencia obtenerDetallesConIngredientes(String entradaId) throws PersistenciaEntradasException;
    
    public List<EntradaViejaDTOPersistencia> obtenerEntradasPorFechas(LocalDateTime fechaInicio, LocalDateTime fechaFin) throws PersistenciaEntradasException;
}
