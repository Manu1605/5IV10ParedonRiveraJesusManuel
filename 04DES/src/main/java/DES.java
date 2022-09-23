/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author alumno
 */

//Vamos a programar bajo un modo de crifrado por bloques de 64 bits
//Vamos a manejar una llave privada de 64 bits
//Se debe tomar para la inicialización de la llave 56 bit para formar 16 subllaves
//Las tablas se van a cargar por parte del proveedor de servicios
//Para poder hacer todo esto vamos a usar librerias, crypto y security

import java.security.*;
//Crypto es para poder generar las claves y sus llaves
import javax.crypto.spec.*;
import javax.crypto.*;
//Entradas o salidas del flujo de datos
import java.io.*;

public class DES {
    public static void main (String[] args) throws Exception{
        //Vamos a comprobar la entrada de un archivo o fichero para cifrar
        if(args.length!=1){
            mensajeAyuda();
            System.exit(1);
            /*Lo primero que tenemos que hacer es cargar una instancia del proveedor del tipo de cifrado, para
            eso esta la parte de las librerias*/
            
            System.out.println("1.- Generar la clave DES: ");
            
            //Vamos a ocupar la clase KeyGenerator
            KeyGenerator generadorDES=KeyGenerator.getInstance("DES");
            //Inicializamos la llave, siempre debe ser de 56
            generadorDES.init(56);
            
            //Vamos a generar las claves
            SecretKey clave=generadorDES.generateKey();
            //Con esto estamos creando las 16 subllaves
            System.out.println("La clave es "+clave);
            
            /*Recordemos que en la criptografia moderna solo se ocupan bits o bytes*/
            
            //La llave que se ha generado no tiene formato
            mostrarBytes(clave.getEncoded());
            System.out.println("");
            
            //Vamos a cifrar, para ello debemos aplicar los estándares vistos en clase. 
            //Usaremos PKCS, para ello tenemos que crear una instancia del algoritmo DES en el modo de cifrado
            
            //ALGORITMO DES 
            //Modo ECB (Electronic Code Book) RELLENO PKCS5
            
            
        }
    }

    private static void mostrarBytes(byte[] encoded) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void mensajeAyuda() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
