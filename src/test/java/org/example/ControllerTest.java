package org.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {

    Controller controller = new Controller();

        String email= "javierlagoamoedo.daw@gmail.com";
        Float cantidadInicial = 1000F;
        Float cantidadNegativa = -1000F;
        float ingreso = 100;
        Cuenta testAccount = new Cuenta(email, cantidadInicial);
        Repository repo = new Repository();




    @Test
    @DisplayName("Registro de cuenta con una cantidad superior a 0")
    void checkregistrarCuenta() throws CantidadInicialMenorOIgualQueCeroException {
        assertEquals(true, controller.registrarCuenta(email,cantidadInicial));
        Assertions.assertThrows(CantidadInicialMenorOIgualQueCeroException.class, () -> {
            controller.registrarCuenta(email,cantidadNegativa)  ;
        });


    }

    @Test
    @DisplayName("consulta saldo y captura Excepcion")
    void checkconsultarSaldo() throws CuentaNoExisteException, CantidadInicialMenorOIgualQueCeroException {
        controller.registrarCuenta(email,cantidadInicial);
        assertEquals(1000F, controller.consultarSaldo(email));
        Assertions.assertThrows(CuentaNoExisteException.class, () -> {
            controller.consultarSaldo("cuentanoexiste@gmail,com") ;
        });

    }

    @Test
    @DisplayName("Limites maximos en los ingresos")
    void checkingresarDinero() throws CantidadInicialMenorOIgualQueCeroException, CuentaNoExisteException, CantidadInIntervaloValidoException {


        Assertions.assertThrows(CantidadInIntervaloValidoException.class, () -> {
            controller.ingresarDinero(email,0.49f);;
        });
        Assertions.assertThrows(CantidadInIntervaloValidoException.class, () -> {
            controller.ingresarDinero(email,501f);;
        });





    }

    @Test
    @DisplayName("Limites maximos en los retiros")
    void checksacarDinero() {
        Assertions.assertThrows(CantidadInIntervaloValidoException.class, () -> {
            controller.sacarDinero(email,0.49f);;
        });
        Assertions.assertThrows(CantidadInIntervaloValidoException.class, () -> {
            controller.sacarDinero(email,501f);;
        });


    }
}