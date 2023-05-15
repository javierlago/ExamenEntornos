package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {
    public String emailPrueba = "javierlagoamoedo@gmail.com";
    Float saldoInicial= 1000F;
     Repository Repo = new Repository();
     Cuenta testAccaount = new Cuenta(emailPrueba,saldoInicial);



    @Test
    @DisplayName("Se añade unacuenta a la base de datos")
    void checkaddAccount() {
        assertTrue(Repo.add(testAccaount));
    }

    @Test
    @DisplayName("Array e")
    void checkfindByEmail() {
        Repo.add(testAccaount);
        assertEquals(testAccaount, Repo.findByEmail(emailPrueba),"Se espera un array que este en la base de datos");
        assertEquals(null,Repo.findByEmail("cuentaquenoexiste@gamil.com"),"El email no existe y debe devolver null");
    }
}