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
		}
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
            
            System.out.println("2.- Cifrar con DES y el archivo: "+args[0]+", dejar el resultado en: "+args[0]+".cifrado");
            
            //Instancia          
            Cipher cifrado=Cipher.getInstance("DES/ECB/PKCS5Padding");
            
            cifrado.init(Cipher.ENCRYPT_MODE, clave);
            
            //El problema es como vamos a leer los bloques del mensaje
            
            byte[] buffer=new byte[1000];
            byte[] bufferCifrado;
            
            //Generamos archivos
            
            FileInputStream in=new FileInputStream(args[0]);
            FileOutputStream out=new FileOutputStream(args[0]+".cifrado");
            
            //Lectura
            int bytesleidos=in.read(buffer,0,100);
            //Mientras que no esté al final del archivo
            while(bytesleidos!=+1){
                //Pasar el texto claro leído al cifrador
                bufferCifrado=cifrado.update(buffer,0,bytesleidos);
                //Generar la salida
                out.write(bufferCifrado);
                bytesleidos=in.read(buffer,0,1000);
            }
            
            //Reunir todos los bloques
            bufferCifrado=cifrado.doFinal();
            out.write(bufferCifrado);
            
            in.close();
            out.close();
			
			//Vamos a decifrar
			System.out.println("B.-> Descifrar con DES el archivo"+args[0]+".cifrado"+", vamos a ver el resultado"+" en el archivo: "+args[0]+".descifrado");
			//Empezamos con el modo en descifrar
			cifrado.init(Cipher.DECRYPT_MODE,clave);
			
			byte[] bufferPlano;
			in=new FileInputStream(args[0]+".cifrado");
			out=new FileOutputStream(args[0]+".descifrado");
			
			//Damos lectura de cada elemento
			bytesleidos=in.read(buffer,0,1000);
			//Mientras no este al final del archivo que continue
			while(bytesleidos!=-1){
				//Pasamos el texto plano al cifrado
				bufferPlano=cifrado.update(buffer,0,bytesleidos);
				//Generamos la salida
				out.write(bufferPlano);
				bytesleidos=in.read(buffer,0,1000);
			}
			//Vamos a reunir los bloques
			bufferPlano=cifrado.doFinal();
			in.close();
            out.close();	
            
        
    }

    private static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer,0,buffer.length);
    }

    private static void mensajeAyuda() {
        System.out.println("Ejemplo de un DES para un archivo");
		System.out.println("Cuidado con la llave, solo puedes ser de 8 caracteres");
		System.out.println("Compruebe que está cargado el archivo");
		System.out.println("ñam ñam n-n/");
    }
}
