/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IDAOs;

import DTOs.PersistenciaProductoDTO;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos para las entidades de
 * tipo Producto. Establece el contrato para la persistencia de productos,
 * permitiendo la abstracción de la implementación específica de la base de
 * datos (por ejemplo, MongoDB, SQL, etc.).
 *
 * @author rodri
 */
public interface IProductoDAO {

    /**
     * Recupera una lista de todos los productos existentes en el sistema de
     * persistencia.
     *
     * @return Una lista de {@link PersistenciaProductoDTO} que representan
     * todos los productos. La lista estará vacía si no existen productos.
     * @throws PersistenciaException Si ocurre un error durante el acceso a los
     * datos o la comunicación con la base de datos.
     */
    public List<PersistenciaProductoDTO> buscarTodos() throws PersistenciaException;

    /**
     * Recupera una lista de todos los productos que están marcados como
     * "habilitados" y para los cuales existe stock suficiente para al menos una
     * de sus configuraciones de tamaño e ingredientes.
     * <p>
     * Este método es útil para mostrar a los clientes solo los productos que
     * efectivamente se pueden preparar y vender.
     *
     * @return Una lista de {@link PersistenciaProductoDTO} de productos
     * habilitados y con stock. La lista estará vacía si no se encuentran
     * productos que cumplan los criterios.
     * @throws PersistenciaException Si ocurre un error durante el acceso a los
     * datos, la verificación del stock o la comunicación con la base de datos.
     */
    public List<PersistenciaProductoDTO> buscarTodosHabilitadosConStock() throws PersistenciaException;

    /**
     * Busca productos que coincidan con un patrón de nombre y/o una categoría
     * específica. El filtro de nombre típicamente permite búsquedas parciales
     * (case-insensitive). El filtro de categoría, si se proporciona, debe ser
     * una coincidencia exacta.
     *
     * @param filtroNombre El texto o patrón a buscar en el nombre de los
     * productos. Puede ser {@code null} o vacío para no filtrar por nombre.
     * @param filtroCategoria La categoría exacta por la cual filtrar los
     * productos. Puede ser {@code null} o vacía para no filtrar por categoría.
     * @return Una lista de {@link PersistenciaProductoDTO} que coinciden con
     * los criterios de búsqueda. La lista estará vacía si no se encuentran
     * productos.
     * @throws PersistenciaException Si ocurre un error durante el acceso a los
     * datos o la comunicación con la base de datos.
     */
    public List<PersistenciaProductoDTO> buscarPorNombreYCategoria(String filtroNombre, String filtroCategoria) throws PersistenciaException;

    /**
     * Busca y recupera un producto específico por su identificador único.
     *
     * @param id El identificador único (como String) del producto a buscar.
     * @return Un {@link PersistenciaProductoDTO} que representa el producto
     * encontrado, o {@code null} si no se encuentra ningún producto con el ID
     * proporcionado.
     * @throws PersistenciaException Si el formato del ID es inválido o si
     * ocurre un error durante el acceso a los datos o la comunicación con la
     * base de datos.
     */
    public PersistenciaProductoDTO buscarPorId(String id) throws PersistenciaException;

    /**
     * Busca y recupera un producto específico por su nombre exacto. Se espera
     * que los nombres de los productos sean únicos o que este método devuelva
     * la primera coincidencia encontrada.
     *
     * @param nombre El nombre exacto del producto a buscar.
     * @return Un {@link PersistenciaProductoDTO} que representa el producto
     * encontrado, o {@code null} si no se encuentra ningún producto con ese
     * nombre.
     * @throws PersistenciaException Si ocurre un error durante el acceso a los
     * datos o la comunicación con la base de datos.
     */
    public PersistenciaProductoDTO buscarPorNombre(String nombre) throws PersistenciaException;

    /**
     * Guarda un nuevo producto en el sistema de persistencia. Si el
     * {@link PersistenciaProductoDTO} tiene un ID nulo o vacío, se espera que
     * la implementación de persistencia genere un nuevo ID.
     *
     * @param producto El {@link PersistenciaProductoDTO} que contiene la
     * información del producto a guardar. No debe ser {@code null}.
     * @throws PersistenciaException Si el objeto producto es {@code null}, si
     * los datos del producto son inválidos, o si ocurre un error durante la
     * operación de guardado en la base de datos.
     */
    public void guardarProducto(PersistenciaProductoDTO producto) throws PersistenciaException;

    /**
     * Actualiza la información de un producto existente en el sistema de
     * persistencia. El producto a actualizar se identifica mediante el ID
     * contenido en el objeto {@link PersistenciaProductoDTO} proporcionado.
     *
     * @param producto El {@link PersistenciaProductoDTO} que contiene el ID del
     * producto a actualizar y la nueva información. No debe ser {@code null} y
     * debe tener un ID válido.
     * @throws PersistenciaException Si el objeto producto es {@code null}, si
     * su ID es inválido, si el producto a actualizar no se encuentra, o si
     * ocurre un error durante la operación de actualización en la base de
     * datos.
     */
    public void actualizarProducto(PersistenciaProductoDTO producto) throws PersistenciaException;

}
