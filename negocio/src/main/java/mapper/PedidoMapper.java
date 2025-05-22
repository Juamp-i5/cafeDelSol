/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;

import DTOs.PagoDTO;
import DTOs.PedidoDTO;
import DTOs.PersistenciaDetallesEfectivoDTO;
import DTOs.PersistenciaDetallesTarjetaDTO;
import DTOs.PersistenciaPagoDTO;
import DTOs.PersistenciaPedidoDTO;
import DTOs.PersistenciaProductoDTO;
import DTOs.PersistenciaProductoPedidoDTO;
import DTOs.PersistenciaSaborDTO;
import DTOs.PersistenciaTamanioDTO;
import DTOs.PersistenciaToppingDTO;
import DTOs.ProductoMostrarDTO;
import DTOs.ProductoPedidoDTO;
import DTOs.ResultadoPagoDTO;
import DTOs.SaborMostrarDTO;
import DTOs.TamanioMostrarDTO;
import DTOs.ToppingMostrarDTO;
import entidades.Pedido;
import interfacesMapper.IPedidoMapper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rodri
 */
public class PedidoMapper implements IPedidoMapper {

    private static PedidoMapper instanceMapper;

    public PedidoMapper() {
    }

    public static PedidoMapper getInstance() {
        if (instanceMapper == null) {
            instanceMapper = new PedidoMapper();
        }
        return instanceMapper;
    }

    @Override
    public Pedido toEntity(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setPrecioTotal(pedidoDTO.getCostoTotal());
        pedido.setEstado(pedidoDTO.isTerminado() ? "TERMINADO" : "PENDIENTE");
        return pedido;
    }

    @Override
    public PersistenciaPedidoDTO toPersistenciaUnicoMetodo(PedidoDTO fuentePedido) {
        if (fuentePedido == null) {
            return null;
        }

        PersistenciaPedidoDTO persistenciaPedido = new PersistenciaPedidoDTO();

        // Mapeo de campos directos de Pedido
        persistenciaPedido.setId(null);
        persistenciaPedido.setFechaHora(LocalDateTime.now());
        persistenciaPedido.setPrecioTotal(fuentePedido.getCostoTotal());
        persistenciaPedido.setBaristaId(fuentePedido.getIdUsuario());

        if (fuentePedido.isTerminado()) {
            persistenciaPedido.setEstado("TERMINADO");
        } else {
            persistenciaPedido.setEstado("PENDIENTE");
        }

        // Mapeo de la lista de Productos
        if (fuentePedido.getProductos() != null) {
            List<PersistenciaProductoPedidoDTO> productosPersistenciaList = new ArrayList<>();
            for (ProductoPedidoDTO fuenteProdPedido : fuentePedido.getProductos()) {
                if (fuenteProdPedido == null) {
                    continue;
                }

                PersistenciaProductoPedidoDTO persistenciaProdPedido = new PersistenciaProductoPedidoDTO();

                // Mapeo de ProductoMostrarDTO a PersistenciaProductoDTO
                if (fuenteProdPedido.getProducto() != null) {
                    ProductoMostrarDTO fuenteProdMostrar = fuenteProdPedido.getProducto();
                    PersistenciaProductoDTO persistenciaProd = new PersistenciaProductoDTO();
                    persistenciaProd.setId(null);
                    persistenciaProd.setNombre(fuenteProdMostrar.getNombre());
                    persistenciaProdPedido.setProducto(persistenciaProd);
                }

                // Mapeo de SaborMostrarDTO a PersistenciaSaborDTO
                if (fuenteProdPedido.getSabor() != null) {
                    SaborMostrarDTO fuenteSaborMostrar = fuenteProdPedido.getSabor();
                    PersistenciaSaborDTO persistenciaSabor = new PersistenciaSaborDTO();
                    persistenciaSabor.setId(null);
                    persistenciaSabor.setNombre(fuenteSaborMostrar.getNombre());
                    persistenciaProdPedido.setSabor(persistenciaSabor);
                }

                // Mapeo de TamanioMostrarDTO a PersistenciaTamanioDTO
                if (fuenteProdPedido.getTamanio() != null) {
                    TamanioMostrarDTO fuenteTamanioMostrar = fuenteProdPedido.getTamanio();
                    PersistenciaTamanioDTO persistenciaTamanio = new PersistenciaTamanioDTO();
                    persistenciaTamanio.setId(null);
                    persistenciaTamanio.setNombre(fuenteTamanioMostrar.getNombre());
                    persistenciaProdPedido.setTamanio(persistenciaTamanio);
                }

                // Mapeo de ToppingMostrarDTO a PersistenciaToppingDTO
                if (fuenteProdPedido.getTopping() != null) {
                    ToppingMostrarDTO fuenteToppingMostrar = fuenteProdPedido.getTopping();
                    PersistenciaToppingDTO persistenciaTopping = new PersistenciaToppingDTO();
                    persistenciaTopping.setId(null);
                    persistenciaTopping.setNombre(fuenteToppingMostrar.getNombre());
                    persistenciaProdPedido.setTopping(persistenciaTopping);
                }

                persistenciaProdPedido.setCantidad(fuenteProdPedido.getCantidad());
                persistenciaProdPedido.setSubtotal(fuenteProdPedido.getCosto());

                double precioUnitarioBase = 0.0;
                if (fuenteProdPedido.getProducto() != null) {
                    precioUnitarioBase = fuenteProdPedido.getProducto().getPrecio();
                }
                if (fuenteProdPedido.getTamanio() != null) {
                    precioUnitarioBase += fuenteProdPedido.getTamanio().getPrecioAdicional();
                }
                persistenciaProdPedido.setPrecioUnitario(precioUnitarioBase);

                productosPersistenciaList.add(persistenciaProdPedido);
            }
            persistenciaPedido.setProductos(productosPersistenciaList);
        } else {
            persistenciaPedido.setProductos(new ArrayList<>());
        }

        // Mapeo de PagoDTO a PersistenciaPagoDTO
        if (fuentePedido.getPagoDTO() != null) {
            PagoDTO fuentePago = fuentePedido.getPagoDTO();
            PersistenciaPagoDTO persistenciaPago = new PersistenciaPagoDTO();
            persistenciaPago.setMoneda(fuentePago.getMoneda());

            PersistenciaDetallesTarjetaDTO pDetallesTarjeta = null;
            PersistenciaDetallesEfectivoDTO pDetallesEfectivo = null;
            String metodoPago = null;

            if (fuentePago.getResultadoPagoDTO() != null) {
                metodoPago = "TARJETA";
                ResultadoPagoDTO resPago = fuentePago.getResultadoPagoDTO();
                pDetallesTarjeta = new PersistenciaDetallesTarjetaDTO();
                pDetallesTarjeta.setTipoTarjeta(resPago.getTipoTarjeta());
                pDetallesTarjeta.setMarca(resPago.getMarca());
                pDetallesTarjeta.setBanco(resPago.getBanco());
                pDetallesTarjeta.setTitular(resPago.getTitular());
                pDetallesTarjeta.setNoAutorizacion(resPago.getIdTransaccion());

                if (fuentePago.getTarjetaDTO() != null && fuentePago.getTarjetaDTO().getNumTarjeta() != null) {
                    String numTarjeta = fuentePago.getTarjetaDTO().getNumTarjeta();
                    if (numTarjeta.length() >= 4) {
                        pDetallesTarjeta.setUltimosDigitos(numTarjeta.substring(numTarjeta.length() - 4));
                    } else {
                        pDetallesTarjeta.setUltimosDigitos(numTarjeta);
                    }
                } else {
                    pDetallesTarjeta.setUltimosDigitos(null);
                }
            } else if (fuentePago.getTarjetaDTO() != null) {
                metodoPago = "TARJETA";
                pDetallesTarjeta = new PersistenciaDetallesTarjetaDTO();
                if (fuentePago.getTarjetaDTO().getNumTarjeta() != null) {
                    String numTarjeta = fuentePago.getTarjetaDTO().getNumTarjeta();
                    if (numTarjeta.length() >= 4) {
                        pDetallesTarjeta.setUltimosDigitos(numTarjeta.substring(numTarjeta.length() - 4));
                    } else {
                        pDetallesTarjeta.setUltimosDigitos(numTarjeta);
                    }
                }
                pDetallesTarjeta.setBanco(fuentePago.getTarjetaDTO().getNombreBanco());
            }

            if (fuentePago.getEfectivoDTO() != null) {
                if (metodoPago != null && metodoPago.equals("TARJETA")) {
                    System.err.println("Advertencia: Datos de tarjeta y efectivo presentes. Revisar lógica de método de pago.");
                }
                metodoPago = "EFECTIVO";
                pDetallesEfectivo = new PersistenciaDetallesEfectivoDTO();
                pDetallesEfectivo.setMontoRecibido(fuentePago.getEfectivoDTO().getCantidadIngresada());
                if (fuentePago.getCambioDTO() != null) {
                    pDetallesEfectivo.setCambio(fuentePago.getCambioDTO().getCambio());
                } else {
                    pDetallesEfectivo.setCambio(0.0);
                }
            }

            persistenciaPago.setMetodoPago(metodoPago);
            if ("EFECTIVO".equals(metodoPago)) {
                persistenciaPago.setDetallesEfectivo(pDetallesEfectivo);
                persistenciaPago.setDetallesTarjeta(null); // Asegurar que el otro sea null
            } else if ("TARJETA".equals(metodoPago)) {
                persistenciaPago.setDetallesTarjeta(pDetallesTarjeta);
                persistenciaPago.setDetallesEfectivo(null); // Asegurar que el otro sea null
            }

            persistenciaPedido.setPago(persistenciaPago);
        }

        return persistenciaPedido;
    }

}
