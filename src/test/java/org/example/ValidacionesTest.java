package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.example.Validaciones.*;
import static org.junit.jupiter.api.Assertions.*;

class ValidacionesTest {
    String emailInvalido = ".mailNoValid@gmail.com";
    String EmailValido = "emailvalido@gmail.com";
    @Test
    void checkisEmailInValido() {
        Assertions.assertAll(
               ()->assertTrue(isEmailInValido(emailInvalido),"Se espera un email no valido"),
               ()->assertFalse(isEmailInValido(EmailValido),"Se espera un email valido")
        );

    }


}