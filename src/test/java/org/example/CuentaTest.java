package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    Cuenta testAccount = new Cuenta("javierlagoamoedo@gmail.com", 1000);
    float cantidad = 100;
    float saldoRestado = 900;
    float saldoSumado = 1100;

    float cantidadSuperiorAsaldo = 1200;


    @Test
    void checkrestarASaldo() throws CantidadMayorQueSaldoException {
        testAccount.restarASaldo(cantidad);
        assertEquals(saldoRestado, testAccount.getSaldo());
        Assertions.assertThrows(Exception.class, () -> {
            testAccount.restarASaldo(cantidadSuperiorAsaldo);
        });
    }

    @Test
    void sumarASaldo() {
        testAccount.sumarASaldo(cantidad);
        assertEquals(saldoSumado, testAccount.getSaldo());


    }
}