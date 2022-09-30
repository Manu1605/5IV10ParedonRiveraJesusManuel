/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aes;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
//Nos sirve para poder generar una clave secreta de mayor longitud
import sun.misc.*;
//Importamos las librerias que nos dan las bases 64 para que podamos leer un mensaje cifrado

/**
 *
 * @author alumno
 */
public class AES {
    
    //Vamos a generar una llave
    public static final byte[] keyvalue=new byte[]{
        /*Puede ser de 3 tamaños
            128->16 caracteres 9 rondas
            192->24 caracteres 11 rondas
            256->32 caracteres 13 rondas*/
        
        'q','w','e','r','t','y','u','i',
        'q','w','e','r','t','y','u','i'
    }; 
    
    //El ususario no va a poder modificar esta instancia
    private static final String instancia="AES";
    
    //Vamos a crear los metodos para cifrar
    
    public static String cifrar(String Data) throws Exception{
        //Para poder cifrar debemos generas las llaves, por lo que debemos crear un método para la generación de estas
        //Vamos a ocupar un objeto key
        
        Key llave=generarLlave();
        
        //Inicializamos el cifrado
        
        Cipher cifrado=Cipher.getInstance(instancia);
        
        //Activamos el modo de cifrado
        cifrado.init(Cipher.ENCRYPT_MODE, llave);
        
        //Vamos a obtener el mensaje y lo debemos transformar en bytes
        byte[] encValores=cifrado.doFinal(Data.getBytes());
        
        //Tenemos un problema es cual es el formato para poder leerlo, por lo que debemos aplicar un formato
        //El formato debe ser de BASE 64
        
        //Aquí son los valores sin formato
        System.out.println("Valores sin formato: "+encValores);
        
        //Aplicamos el formato con BASE64Encoder()
        String valoresEncriptados=new BASE64Encoder().encode(encValores);
        
        System.out.println("Valores con formato: "+valoresEncriptados);
        return valoresEncriptados;
        
    }
    
    public static String descifrar(String valoresEncriptados) throws Exception{
        //Para poder cifrar debemos generas las llaves, por lo que debemos crear un método para la generación de estas
        //Vamos a ocupar un objeto key
        
        Key llave=generarLlave();
        
        //Inicializamos el cifrado
        
        Cipher cifrado=Cipher.getInstance(instancia);
        
        //Activamos el modo de cifrado
        cifrado.init(Cipher.DECRYPT_MODE, llave);
        
        //Vamos a obtener el mensaje y lo vamos a decodificar
        byte[] valoresDecodificados=new BASE64Decoder().decodeBuffer(valoresEncriptados);
        
        //Tenemos un problema es cual es el formato para poder leerlo, por lo que debemos aplicar un formato
        //El formato debe ser de BASE 64

        //Transformar los valores
        byte[] decValores=cifrado.doFinal(valoresDecodificados);
                
        //Aquí son los valores sin formato
        System.out.println("Valores sin formato: "+decValores);
        
        //Aplico un formato de cadena
        String valoresDescifrados=new String(decValores);
        System.out.println("Valores con formato: "+valoresDescifrados);
        
        return valoresDescifrados;
        
    }

    private static Key generarLlave() throws Exception{
        //Vamos a ocupar las llaves de AES de acuerdo a la clase SecretKeySpec la cual nos genera 
        //todas las subllaves de ronda
        
        Key llave=new SecretKeySpec(keyvalue, instancia);
        return llave;
    }
}
