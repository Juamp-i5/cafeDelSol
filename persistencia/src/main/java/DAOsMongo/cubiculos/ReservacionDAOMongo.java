/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOsMongo.cubiculos;

import DTOs.cubiculos.IReservacionDetalleMapperPersistencia;
import DTOs.cubiculos.IReservacionMapperPersistencia;
import DTOs.cubiculos.ReservacionDTOCompletaPersistencia;
import DTOs.cubiculos.ReservacionDetalleDTOPersistencia;
import DTOs.cubiculos.ReservacionDetalleMapperPersistencia;
import DTOs.cubiculos.ReservacionMapperPersistencia;
import IDAOs.cubiculos.IReservacionDAO;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import conexion.IConexionMongo;
import entidades.Reservacion;
import enumCubiculos.Estado;
import excepciones.PersistenciaCubiculoEsception;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

/**
 *
 * @author rodri
 */
public class ReservacionDAOMongo implements IReservacionDAO {

    private static ReservacionDAOMongo instancia;
    private final IConexionMongo conexion;
    private final MongoDatabase database;
    private final MongoCollection<Reservacion> coleccion;
    private static final String NOMBRE_COLECCION = "reservaciones";

    IReservacionMapperPersistencia reservacionMapper = new ReservacionMapperPersistencia();
    IReservacionDetalleMapperPersistencia detalleMapper = new ReservacionDetalleMapperPersistencia();

    // Constructor con la conexion y codec para POJOs
    public ReservacionDAOMongo(IConexionMongo conexion) {
        this.conexion = conexion;

        // Registrar codec para POJOs
        CodecRegistry codecRegistry = fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build())
        );

        this.database = conexion.getDatabase().withCodecRegistry(codecRegistry);
        this.coleccion = database.getCollection(NOMBRE_COLECCION, Reservacion.class);
    }

    // Singleton getter
    public static ReservacionDAOMongo getInstance(IConexionMongo conexion) {
        if (instancia == null) {
            instancia = new ReservacionDAOMongo(conexion);
        }
        return instancia;
    }

    @Override
    public ReservacionDTOCompletaPersistencia agregarReservacion(ReservacionDTOCompletaPersistencia reservacion) throws PersistenciaCubiculoEsception {
        try {
            coleccion.insertOne(reservacionMapper.toMongo(reservacion));
            System.out.println("Reservación creada correctamente.");
            return reservacion;
        } catch (Exception e) {
            throw new PersistenciaCubiculoEsception("Error al hacer la reservacion: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean actualizarEstadoReservacion(Integer numReservacion, Estado estado) throws PersistenciaCubiculoEsception {
        try {
            Bson filtro = Filters.eq("numReservacion", numReservacion);
            Bson actualizacion = Updates.set("estado", estado);
            UpdateResult resultado = coleccion.updateOne(filtro, actualizacion);
            if (resultado.getMatchedCount() == 0) {
                throw new PersistenciaCubiculoEsception("No se encontró la reservación con el número proporcionado.");
            }
            if (resultado.wasAcknowledged()) {
                return true;
            } else {
                throw new PersistenciaCubiculoEsception("No se pudo actualizar el estado de la reservación");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new PersistenciaCubiculoEsception("Error al editar el estado de la reservación", e);
        }
    }

    @Override
    public ReservacionDTOCompletaPersistencia buscarPorId(Integer id) throws PersistenciaCubiculoEsception {
        try {
            Bson filtro = Filters.eq("numReservacion", id);
            Reservacion entidad = coleccion.find(filtro).first();
            ReservacionDTOCompletaPersistencia dto = reservacionMapper.toDTO(entidad);
            return dto;

        } catch (Exception e) {
            throw new PersistenciaCubiculoEsception("Error al buscar reservación por número: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ReservacionDTOCompletaPersistencia> buscarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaCubiculoEsception {
        try {
            Bson filtro = new Document();

            List<ReservacionDTOCompletaPersistencia> listaDTO;

            if (fechaInicio != null && fechaFin != null) {
                filtro = Filters.and(
                        Filters.gte("fechaReserva", fechaInicio.atStartOfDay()),
                        Filters.lte("fechaReserva", fechaFin.atTime(LocalTime.MAX))
                );
            } else if (fechaInicio != null) {
                filtro = Filters.gte("fechaReserva", fechaInicio);
            } else if (fechaFin != null) {
                filtro = Filters.lte("fechaReserva", fechaFin);
            } // Si las dos son null, se usa el filtro vacío (en teoría)

            listaDTO = reservacionMapper.toDTOList(coleccion.find(filtro).into(new ArrayList<>()));

            return listaDTO;
        } catch (Exception e) {
            throw new PersistenciaCubiculoEsception("Error al buscar reservaciones por rango de fechas", e);
        }
    }

    @Override
    public List<ReservacionDTOCompletaPersistencia> buscarPendientesPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaCubiculoEsception {
        try {
            List<Bson> filtros = new ArrayList<>();

            // Filtro por estado "PENDIENTE"
            filtros.add(Filters.in("estado", Estado.PENDIENTE, Estado.ACTIVA));

            // Filtros por fechas, si no son null
            if (fechaInicio != null && fechaFin != null) {
                filtros.add(Filters.gte("fechaReserva", fechaInicio.atStartOfDay()));
                filtros.add(Filters.lte("fechaReserva", fechaFin.atTime(LocalTime.MAX)));
            } else if (fechaInicio != null) {
                filtros.add(Filters.gte("fechaReserva", fechaInicio.atStartOfDay()));
            } else if (fechaFin != null) {
                filtros.add(Filters.lte("fechaReserva", fechaFin.atTime(LocalTime.MAX)));
            }

            // Combina los filtros (estado + fechas)
            Bson filtroFinal = Filters.and(filtros);

            // Ejecuta la consulta
            List<ReservacionDTOCompletaPersistencia> listaDTO = reservacionMapper.toDTOList(
                    coleccion.find(filtroFinal).into(new ArrayList<>())
            );

            return listaDTO;
        } catch (Exception e) {
            throw new PersistenciaCubiculoEsception("Error al buscar reservaciones por rango de fechas y estado PENDIENTE", e);
        }
    }

    @Override
    public boolean modificarReservacion(Integer numReservacion, Integer numReservacionNuevo, String motivo, LocalDateTime fechaHora) throws PersistenciaCubiculoEsception {
        try {
            Bson filtro = Filters.eq("numReservacion", numReservacion);
            Bson actualizacion;

            if (numReservacionNuevo == null) {
                actualizacion = Updates.combine(
                        Updates.set("estado", Estado.CANCELADO),
                        Updates.set("motivo", motivo),
                        Updates.set("fechModificacion", fechaHora)
                );
            } else {
                actualizacion = Updates.combine(
                        Updates.set("estado", Estado.REAGENDADO),
                        Updates.set("numReservacionNuevo", numReservacionNuevo),
                        Updates.set("motivo", motivo),
                        Updates.set("fechModificacion", fechaHora)
                );
            }

            UpdateResult resultado = coleccion.updateOne(filtro, actualizacion);

            if (resultado.getMatchedCount() == 0) {
                throw new PersistenciaCubiculoEsception("No se encontró la reservación original.");
            }

            return resultado.wasAcknowledged();
        } catch (Exception e) {
            throw new PersistenciaCubiculoEsception("Error al actualizar los datos de reagenda", e);
        }
    }

    @Override
    public ReservacionDetalleDTOPersistencia getDetalleReservacion(Integer numReservacion) throws PersistenciaCubiculoEsception {
        try {
            Bson filtro = Filters.eq("numReservacion", numReservacion);
            Reservacion entidad = coleccion.find(filtro).first();
            ReservacionDetalleDTOPersistencia dto = detalleMapper.toDTO(entidad);
            return dto;

        } catch (Exception e) {
            throw new PersistenciaCubiculoEsception("Error al obtener detalle: " + e.getMessage(), e);
        }
    }
    
    

}
