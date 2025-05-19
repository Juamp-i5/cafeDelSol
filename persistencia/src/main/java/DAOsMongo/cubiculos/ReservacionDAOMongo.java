/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOsMongo.cubiculos;

import DTOs.cubiculos.CancelacionDTOPersistencia;
import DTOs.cubiculos.IReservacionMapperPersistencia;
import DTOs.cubiculos.ReagendaDTOPersistencia;
import DTOs.cubiculos.ReservacionDTOPersistencia;
import DTOs.cubiculos.ReservacionMapperPersistencia;
import IDAOs.cubiculos.IReservacionDAO;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
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
    public ReservacionDTOPersistencia agregarReservacion(ReservacionDTOPersistencia reservacion) throws PersistenciaCubiculoEsception {
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
    public ReservacionDTOPersistencia buscarPorId(Integer id) throws PersistenciaCubiculoEsception {
        try {
            Bson filtro = Filters.eq("numReservacion", id);
            Reservacion entidad = coleccion.find(filtro).first();
            ReservacionDTOPersistencia dto = reservacionMapper.toDTO(entidad);
            return dto;

        } catch (Exception e) {
            throw new PersistenciaCubiculoEsception("Error al buscar reservación por número: " + e.getMessage(), e);
        }
    }

    @Override
    public List<ReservacionDTOPersistencia> buscarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaCubiculoEsception {
        try {
            Bson filtro = new Document();

            List<ReservacionDTOPersistencia> listaDTO;

            if (fechaInicio != null && fechaFin != null) {
                filtro = Filters.and(
                        Filters.gte("fecha", fechaInicio.atStartOfDay()),
                        Filters.lte("fecha", fechaFin.atTime(LocalTime.MAX))
                );
            } else if (fechaInicio != null) {
                filtro = Filters.gte("fecha", fechaInicio);
            } else if (fechaFin != null) {
                filtro = Filters.lte("fecha", fechaFin);
            } // Si las dos son null, se usa el filtro vacío (en teoría)

            listaDTO = reservacionMapper.toDTOList(coleccion.find(filtro).into(new ArrayList<>()));

            return listaDTO;
        } catch (Exception e) {
            throw new PersistenciaCubiculoEsception("Error al buscar reservaciones por rango de fechas", e);
        }
    }

    @Override
    public boolean cancelacionReservacion(Integer numReservacion, String motivo, LocalDateTime fechaHora) throws PersistenciaCubiculoEsception {
        try {
            Bson filtro = Filters.eq("numReservacion", numReservacion);

            Bson actualizacion = Updates.combine(
                    Updates.set("motivo", motivo),
                    Updates.set("fechaHora", fechaHora)
            );

            UpdateResult resultado = coleccion.updateOne(filtro, actualizacion);

            if (resultado.getMatchedCount() == 0) {
                throw new PersistenciaCubiculoEsception("No se encontró la reservación.");
            }

            return resultado.wasAcknowledged();
        } catch (Exception e) {
            throw new PersistenciaCubiculoEsception("Error al cancelar la reservacion", e);
        }
    }

    @Override
    public boolean reagendaReservacion(Integer numReservacion, Integer numReservacionNuevo, String motivo, LocalDateTime fechaHora) throws PersistenciaCubiculoEsception {
        try {
            Bson filtro = Filters.eq("numReservacion", numReservacion);

            Bson actualizacion = Updates.combine(
                    Updates.set("numReservacionNuevo", numReservacionNuevo),
                    Updates.set("motivo", motivo),
                    Updates.set("fechaHora", fechaHora)
            );

            UpdateResult resultado = coleccion.updateOne(filtro, actualizacion);

            if (resultado.getMatchedCount() == 0) {
                throw new PersistenciaCubiculoEsception("No se encontró la reservación original.");
            }

            return resultado.wasAcknowledged();
        } catch (Exception e) {
            throw new PersistenciaCubiculoEsception("Error al actualizar los datos de reagenda", e);
        }
    }

}
