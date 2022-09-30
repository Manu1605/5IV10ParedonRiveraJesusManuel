/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aes;

/**
 *
 * @author alumno
 */
public class PrincipalAES{
    public static void main(String[] args) throws Exception{
        String mensaje="A gustabo le apesta el ano bien maciso";
        String mensajecifrado=AES.cifrar(mensaje);
        String mensajeDescifrado=AES.descifrar(mensajecifrado);
        
        System.out.println("El mensaje a cifrar es: "+mensaje);
        System.out.println("El mensaje cifrado es: "+mensajecifrado);
        System.out.println("El mensaje descifrado es: "+mensajeDescifrado);
        
    }
}
