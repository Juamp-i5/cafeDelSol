/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package gestionSalidas;

import DTOs.CRUDSalidas.DetalleSalidaDTO;
import DTOs.CRUDSalidas.SalidaListDTO;
import DTOs.CRUDSalidas.SalidaNuevaDTO;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author katia
 */
public class GestorCRUDSalidasTest {
    
    public GestorCRUDSalidasTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getInstance method, of class GestorCRUDSalidas.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        GestorCRUDSalidas expResult = null;
        GestorCRUDSalidas result = GestorCRUDSalidas.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registrarSalida method, of class GestorCRUDSalidas.
     */
    @Test
    public void testRegistrarSalida() throws Exception {
        System.out.println("registrarSalida");
        SalidaNuevaDTO salidaNueva = null;
        GestorCRUDSalidas instance = new GestorCRUDSalidas();
        boolean expResult = false;
        boolean result = instance.registrarSalida(salidaNueva);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consultarTodas method, of class GestorCRUDSalidas.
     */
    @Test
    public void testConsultarTodas() throws Exception {
        System.out.println("consultarTodas");
        GestorCRUDSalidas instance = new GestorCRUDSalidas();
        List<SalidaListDTO> expResult = null;
        List<SalidaListDTO> result = instance.consultarTodas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consultarPorRangoFechas method, of class GestorCRUDSalidas.
     */
    @Test
    public void testConsultarPorRangoFechas() throws Exception {
        System.out.println("consultarPorRangoFechas");
        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;
        GestorCRUDSalidas instance = new GestorCRUDSalidas();
        List<SalidaListDTO> expResult = null;
        List<SalidaListDTO> result = instance.consultarPorRangoFechas(fechaInicio, fechaFin);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of consultarPorId method, of class GestorCRUDSalidas.
     */
    @Test
    public void testConsultarPorId() throws Exception {
        System.out.println("consultarPorId");
        String id = "";
        GestorCRUDSalidas instance = new GestorCRUDSalidas();
        DetalleSalidaDTO expResult = null;
        DetalleSalidaDTO result = instance.consultarPorId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
