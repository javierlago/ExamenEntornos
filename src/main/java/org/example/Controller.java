package org.example;

/**
 * @author a22javierla
 */
public class Controller {
    /**
     * Clase en la que se gestiona cuentas.Se pueden realizar acciones como registrar cuentas,
     * consultar su saldo, retirar e ingresar dinero.
     *
     * @apiNote dfadfasdf
     *
     */


    private final Repository repository = new Repository();

    private static final float MIN_CANTIDAD_TRANSACCION = 0.50f;
    private static final float MAX_CANTIDAD_TRANSACCION = 500f;

    /**
     *
     * @param emailRegistro Email con el que se va a registrar el ususria(Sritng)
     * @param cantidadInicial Cantidad inicial con la que se debe de realizar la cuenta
     *
     * @return El return de esta cuenta añade una cuenta a la base de datos.
     * @throws CantidadInicialMenorOIgualQueCeroException Lanza una excepcion en caso de que la cantidad
     * añadida en el metodo sea menor o igual a O;
     */
    public boolean registrarCuenta(String emailRegistro, float cantidadInicial)
            throws CantidadInicialMenorOIgualQueCeroException {
        if (cantidadInicial <= 0) throw new CantidadInicialMenorOIgualQueCeroException();
        return repository.add(new Cuenta(emailRegistro, cantidadInicial));
    }

    /**
     *
     * @param email Email de la cunta que se desea consultar
     * @return Se obtiene el saldo de la cuenta que se localizo a traves del email
     * @throws CuentaNoExisteException Lanza una excepcion en caso de que el email introducido
     * no se encuentre en la base de datos.
     */
    public float consultarSaldo(String email) throws CuentaNoExisteException {
        Cuenta cuenta = repository.findByEmail(email);
        if (cuenta==null) throw new CuentaNoExisteException();
        else return cuenta.getSaldo();
    }

    /**
     *
     * @param emailOrigen Recibe un String Email en el que se desea realizar el ingreso.Debe cumplir con el regecx que hay en ValidacionTest
     * @param cantidad Cantidad que se desea ingresar
     * @throws CantidadInicialMenorOIgualQueCeroException Lanza una exccepcion cuando se intenta ingresar un valor menor o igual que O
     * @throws CantidadInIntervaloValidoException Lanza esta excepcion cunado la cantidad a ingresar no cumle unos requisitos,
     *(MIN_CANTIDAD_TRANSACCION = 0.50f//MAX_CANTIDAD_TRANSACCION = 500f; )
     */
    public void ingresarDinero(String emailOrigen, float cantidad)
            throws CantidadInicialMenorOIgualQueCeroException, CantidadInIntervaloValidoException {
        if (cantidad <= 0) throw new CantidadInicialMenorOIgualQueCeroException();
        if ((cantidad < MIN_CANTIDAD_TRANSACCION) || (cantidad > MAX_CANTIDAD_TRANSACCION))
            throw new CantidadInIntervaloValidoException();
        repository.findByEmail(emailOrigen).sumarASaldo(cantidad);
    }

    /**
     *
     * @param emailDestino Email de la cuenta en la que se va a realizar el retiro
     * @param cantidad cantidad a retirar
     * @throws CantidadInicialMenorOIgualQueCeroException Lanza una exccepcion cuando se intentaningresar un valor menor o igual que O
     * @throws CantidadInIntervaloValidoException Lanza esta excepcion cunado la cantidad a ingresar no cumle unos requisitos,
     * (MIN_CANTIDAD_TRANSACCION = 0.50f//MAX_CANTIDAD_TRANSACCION = 500f; )
     * @throws CantidadMayorQueSaldoException Lanza un excepcion cuando el la cantidad a retirar es mayor que el saldo actual de la cuenta
     */
    public void sacarDinero(String emailDestino, float cantidad)
            throws CantidadInicialMenorOIgualQueCeroException, CantidadInIntervaloValidoException,
            CantidadMayorQueSaldoException {
        if (cantidad <= 0) throw new CantidadInicialMenorOIgualQueCeroException();
        if ((cantidad < MIN_CANTIDAD_TRANSACCION) || (cantidad > MAX_CANTIDAD_TRANSACCION))
            throw new CantidadInIntervaloValidoException();
        repository.findByEmail(emailDestino).restarASaldo(cantidad);
    }


}
