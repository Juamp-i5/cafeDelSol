package BOs;

import DTOs.ToppingsMostrarDTO;
import entidades.Topping;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import interfacesBO.IToppingBO;
import interfacesMapper.IToppingMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapper.ToppingMapper;
import IDAOs.IToppingDAO;
import acceso.AccesoDatos;

/**
 *
 * @author rodri
 */
public class ToppingBO implements IToppingBO {

    IToppingDAO tamanioDAO = AccesoDatos.getToppingDAO();
    IToppingMapper productoMapper = ToppingMapper.getInstance();

    private static ToppingBO instanceBO;

    public ToppingBO() {
    }

    public static ToppingBO getInstance() {
        if (instanceBO == null) {
            instanceBO = new ToppingBO();
        }
        return instanceBO;
    }

    @Override
    public List<ToppingsMostrarDTO> cargarProductos() throws NegocioException {
        try {

            List<Topping> toppings = tamanioDAO.buscarTodos();
            List<ToppingsMostrarDTO> toppingsDTO = new ArrayList<>();

            for (Topping topping : toppings) {
                ToppingsMostrarDTO toppingDTO = productoMapper.aDTO(topping);
                toppingsDTO.add(toppingDTO);
            }

            return toppingsDTO;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al cargar los productos");
        }
    }

}
