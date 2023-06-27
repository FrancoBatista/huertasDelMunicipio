
package com.FrancoBatista.huertasDelmunicipio.excepciones;


public class MiException extends Exception{
     
    //genearmos este constructor que servira para lanzar una excepcion en caso de que sea un error propio y no del sistema
    public MiException(String msg){
        super(msg);
}
}