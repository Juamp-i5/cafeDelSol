package DTOs.entradas;

import entidades.Entrada;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author pablo
  * Esta clase, `EntradaMapperPersistencia`, es un componente clave en la capa de persistencia,
 * actuando como un **mapeador** entre las entidades de dominio `Entrada` y sus
 * respectivos Objetos de Transferencia de Datos (DTOs) para persistencia:
 * `EntradaNuevaDTOPersistencia` y `EntradaViejaDTOPersistencia`.
 * Su rol es traducir los datos entre estos diferentes formatos, lo que es esencial
 * para desacoplar el modelo de dominio de los detalles específicos de la base de datos.
 * Implementa el patrón Singleton para asegurar que solo haya una instancia de este mapeador en la aplicación.
 */
public class EntradaMapperPersistencia implements IEntradaMapperPersistencia {

    // Se instancia un mapeador de detalles de entrada, ya que las entradas contienen listas de detalles.
    IDetalleEntradaMapperPersistencia detalleEntradaMapper = DetalleEntradaMapperPersistencia.getInstance();
    private static EntradaMapperPersistencia instanceMapper;

    /**
     * Implementa el patrón Singleton para garantizar una única instancia de este mapeador.
     * Si la instancia aún no existe, la crea y la devuelve; de lo contrario, retorna
     * la instancia ya existente. Esto ayuda a optimizar los recursos y mantener la consistencia.
     *
     * @return La única instancia de `EntradaMapperPersistencia`.
     */
    public static EntradaMapperPersistencia getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new EntradaMapperPersistencia();
        }
        return instanceMapper;
    }

    //--------------------------Nuevo----------------------------------------
    /**
     * Convierte un objeto `EntradaNuevaDTOPersistencia` (DTO para crear una nueva entrada)
     * a su correspondiente entidad de dominio `Entrada`. Este método se utiliza cuando
     * se va a persistir una nueva entrada en la base de datos. Se encarga de transferir
     * los datos básicos de la entrada y utiliza el `detalleEntradaMapper` para convertir
     * la lista de DTOs de detalle a sus respectivas entidades. Nótese que este mapeo no
     * maneja el ID de la entrada, ya que se asume que será generado por la base de datos.
     *
     * @param entradaDTO El DTO `EntradaNuevaDTOPersistencia` a convertir.
     * @return Un objeto `Entrada` listo para ser persistido.
     */
    @Override
    public Entrada toEntityNuevo(EntradaNuevaDTOPersistencia entradaDTO) {
        Entrada entrada = new Entrada();
        entrada.setProveedor(entradaDTO.getProveedor());
        entrada.setFechaHora(entradaDTO.getFechaHora());   
        entrada.setPrecioTotal(entradaDTO.getPrecioTotal());
        entrada.setDetallesEntrada(detalleEntradaMapper.toEntityList(entradaDTO.getDetallesEntrada()));
        return entrada;
    }

    /**
     * Convierte una entidad `Entrada` a un `EntradaNuevaDTOPersistencia`. Aunque este DTO
     * está diseñado principalmente para la entrada de datos, esta operación inversa podría
     * ser útil en escenarios donde se necesita re-enviar una entidad (que aún no tiene ID
     * asignado o se quiere tratar como nueva) a un proceso que espera este tipo de DTO.
     * También delega el mapeo de los detalles de entrada al `detalleEntradaMapper`.
     *
     * @param entrada La entidad `Entrada` a convertir.
     * @return Un objeto `EntradaNuevaDTOPersistencia`.
     */
    @Override
    public EntradaNuevaDTOPersistencia todtoNuevoPersistencia(Entrada entrada) {
        EntradaNuevaDTOPersistencia entradaDTO = new EntradaNuevaDTOPersistencia();
        entradaDTO.setFechaHora(entrada.getFechaHora());
        entradaDTO.setProveedor(entrada.getProveedor());
        entradaDTO.setPrecioTotal(entrada.getPrecioTotal());
        entradaDTO.setDetallesEntrada(detalleEntradaMapper.todtoPersistenciaList(entrada.getDetallesEntrada()));
        return entradaDTO;
    }

    //Listas
    /**
     * Convierte una lista de `EntradaNuevaDTOPersistencia` a una lista de entidades `Entrada`.
     * Este método es útil para procesar por lotes múltiples nuevas entradas antes de su persistencia.
     * Itera sobre cada DTO de entrada en la lista y realiza el mapeo individual a su entidad correspondiente.
     *
     * @param entradasDTO La lista de DTOs `EntradaNuevaDTOPersistencia` a convertir.
     * @return Una `List` de objetos `Entrada`.
     */
    @Override
    public List<Entrada> toEntityNuevoList(List<EntradaNuevaDTOPersistencia> entradasDTO) {
        List<Entrada> entradas = new ArrayList<>();
        for (EntradaNuevaDTOPersistencia entradasdto : entradasDTO) {
            Entrada entrada = new Entrada();
            entrada.setFechaHora(entradasdto.getFechaHora());
            entrada.setProveedor(entradasdto.getProveedor());
            entrada.setPrecioTotal(entradasdto.getPrecioTotal());
            entrada.setDetallesEntrada(detalleEntradaMapper.toEntityList(entradasdto.getDetallesEntrada()));
            entradas.add(entrada);
        }
        return entradas;
    }

    /**
     * Convierte una lista de entidades `Entrada` a una lista de `EntradaNuevaDTOPersistencia`.
     * Similar al método individual `todtoNuevoPersistencia`, esta operación es para casos
     * donde se necesita exportar o transferir una colección de entidades (sin IDs de persistencia)
     * en el formato de DTOs de nuevas entradas.
     *
     * @param entradas La lista de entidades `Entrada` a convertir.
     * @return Una `List` de objetos `EntradaNuevaDTOPersistencia`.
     */
    @Override
    public List<EntradaNuevaDTOPersistencia> todtoNuevoPersistenciaList(List<Entrada> entradas) {
        List<EntradaNuevaDTOPersistencia> entradasDTO = new ArrayList<>();
        for (Entrada entrada : entradas) {
            EntradaNuevaDTOPersistencia entradadto = new EntradaNuevaDTOPersistencia();
            entradadto.setFechaHora(entrada.getFechaHora());
            entradadto.setProveedor(entrada.getProveedor());
            entradadto.setPrecioTotal(entrada.getPrecioTotal());
            entradadto.setDetallesEntrada(detalleEntradaMapper.todtoPersistenciaList(entrada.getDetallesEntrada()));
            entradasDTO.add(entradadto);
        }
        return entradasDTO;
    }

    //--------------------------Viejo----------------------------------------
    /**
     * Convierte un objeto `EntradaViejaDTOPersistencia` (DTO para una entrada existente)
     * a su correspondiente entidad de dominio `Entrada`. Este método se utiliza cuando
     * se recupera una entrada de la base de datos. Incluye la conversión del ID de la
     * entrada de `String` a `ObjectId`, que es el tipo utilizado en la entidad
     * para interactuar con MongoDB. También mapea recursivamente los detalles de la entrada.
     *
     * @param entradaDTO El DTO `EntradaViejaDTOPersistencia` a convertir.
     * @return Un objeto `Entrada` que representa la entidad de dominio completa.
     */
    @Override
    public Entrada toEntityViejo(EntradaViejaDTOPersistencia entradaDTO) {
        Entrada entrada = new Entrada();
        ObjectId entradaid = new ObjectId(entradaDTO.getIdEntrada());
        entrada.setIdEntrada(entradaid);
        entrada.setFechaHora(entradaDTO.getFechaHora());
        entrada.setProveedor(entradaDTO.getProveedor());
        entrada.setPrecioTotal(entradaDTO.getPrecioTotal());
        entrada.setDetallesEntrada(detalleEntradaMapper.toEntityList(entradaDTO.getDetallesEntrada()));
        return entrada;
    }

    /**
     * Convierte una entidad `Entrada` a un `EntradaViejaDTOPersistencia` (DTO para una entrada existente).
     * Este método es fundamental cuando se recuperan datos de la base de datos y se necesitan
     * transferir a otras capas de la aplicación, ya que el DTO incluye el ID de la entrada
     * en un formato de `String` más amigable. También mapea los detalles de entrada a sus DTOs correspondientes.
     *
     * @param entrada La entidad `Entrada` a convertir.
     * @return Un objeto `EntradaViejaDTOPersistencia`.
     */
    @Override
    public EntradaViejaDTOPersistencia todtoViejoPersistencia(Entrada entrada) {
        EntradaViejaDTOPersistencia entradaDTO = new EntradaViejaDTOPersistencia();
        entradaDTO.setIdEntrada(entrada.getIdEntrada().toHexString());
        entradaDTO.setFechaHora(entrada.getFechaHora());
        entradaDTO.setProveedor(entrada.getProveedor());
        entradaDTO.setPrecioTotal(entrada.getPrecioTotal());
        entradaDTO.setDetallesEntrada(detalleEntradaMapper.todtoPersistenciaList(entrada.getDetallesEntrada()));
        return entradaDTO;
    }

    //Listas
    /**
     * Convierte una lista de `EntradaViejaDTOPersistencia` a una lista de entidades `Entrada`.
     * Este método es útil para procesar colecciones de entradas ya existentes, por ejemplo,
     * al cargar múltiples registros de la base de datos. Itera sobre cada DTO de entrada
     * en la lista y realiza el mapeo individual a su entidad correspondiente.
     *
     * @param entradasDTO La lista de DTOs `EntradaViejaDTOPersistencia` a convertir.
     * @return Una `List` de objetos `Entrada`.
     */
    @Override
    public List<Entrada> toEntityViejoList(List<EntradaViejaDTOPersistencia> entradasDTO) {
        List<Entrada> entradas = new ArrayList<>();
        for (EntradaViejaDTOPersistencia entradasdto : entradasDTO) {
            Entrada entrada = new Entrada();
            ObjectId entradaid = new ObjectId(entradasdto.getIdEntrada());
            entrada.setIdEntrada(entradaid);
            entrada.setFechaHora(entradasdto.getFechaHora());
            entrada.setProveedor(entradasdto.getProveedor());
            entrada.setPrecioTotal(entradasdto.getPrecioTotal());
            entrada.setDetallesEntrada(detalleEntradaMapper.toEntityList(entradasdto.getDetallesEntrada()));
            entradas.add(entrada);
        }
        return entradas;
    }

    /**
     * Convierte una lista de entidades `Entrada` a una lista de `EntradaViejaDTOPersistencia`.
     * Este método es esencial para la transferencia masiva de datos desde la capa de persistencia
     * a otras capas, asegurando que cada entidad se transforme correctamente en su formato DTO
     * (incluyendo el ID y los detalles) antes de ser devuelta.
     *
     * @param entradas La lista de entidades `Entrada` a convertir.
     * @return Una `List` de objetos `EntradaViejaDTOPersistencia`.
     */
    @Override
    public List<EntradaViejaDTOPersistencia> todtoViejoPersistenciaList(List<Entrada> entradas) {
        List<EntradaViejaDTOPersistencia> entradasDTO = new ArrayList<>();
        for (Entrada entrada : entradas) {
            EntradaViejaDTOPersistencia entradadto = new EntradaViejaDTOPersistencia();
            entradadto.setIdEntrada(entrada.getIdEntrada().toHexString());
            entradadto.setFechaHora(entrada.getFechaHora());
            entradadto.setProveedor(entrada.getProveedor());
            entradadto.setPrecioTotal(entrada.getPrecioTotal());
            entradadto.setDetallesEntrada(detalleEntradaMapper.todtoPersistenciaList(entrada.getDetallesEntrada()));
            entradasDTO.add(entradadto);
        }
        return entradasDTO;
    }
}
