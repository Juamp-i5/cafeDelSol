/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observers;

import java.util.ArrayList;
import java.util.List;
import interfacesObservers.NuevaVentaObserver;

/**
 *
 * @author norma
 */
public class AdministradorSistemaObserver implements NuevaVentaObserver {

    private List<String> notificaciones = new ArrayList<>();
    int cont = 0;

    @Override
    public void update() {
        if (esStockBajo() == true) {
            String mensaje = "Alerta: Stock bajo para de ingrediente BLAH";
            notificaciones.add(mensaje);
            enviarAlerta(mensaje);
        }
    }

    private boolean esStockBajo() {
        //Aqui seria la lógica para checar el bajo stock.
        //Pero como no tenemos ingredientes en este caso de uso...
        if (cont % 2 == 0) {
            cont = cont + 1;
            return true;
        }
        return false;
    }

    private void enviarAlerta(String mensaje) {
        System.out.println("🔔 Administrador: " + mensaje);
    }

    public List<String> getNotificaciones() {
        return notificaciones;
    }
}
