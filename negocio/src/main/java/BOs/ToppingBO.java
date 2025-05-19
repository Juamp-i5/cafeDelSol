package BOs;

import DTOs.PersistenciaToppingDTO;
import DTOs.ToppingMostrarDTO;
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

    IToppingDAO toppingDAO = AccesoDatos.getToppingDAO();
    IToppingMapper toppingMapper = new ToppingMapper();

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
    public List<ToppingMostrarDTO> cargarProductos() throws NegocioException {
        try {

            List<PersistenciaToppingDTO> toppings = toppingDAO.buscarTodos();
            List<ToppingMostrarDTO> toppingsDTO = new ArrayList<>();

            for (PersistenciaToppingDTO topping : toppings) {
                ToppingMostrarDTO toppingDTO = toppingMapper.toToppingsMostrarDTO(topping);
                toppingsDTO.add(toppingDTO);
            }

            return toppingsDTO;
        } catch (PersistenciaException ex) {
            Logger.getLogger(ProductoBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error al cargar los productos");
        }
    }

}
