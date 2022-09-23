//Vamos a ocupar parte de cesar

const abc= ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 
'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
'x', 'y', 'z'];

//Llave

let key="";

//Interpretamos código
//El $ sirve para mandar a llamar
//Function() vacia se le llama función anónima

$(document).ready(function(){
    //Funcion para poder cifrar con viggenere
    $('#ci').click(function(){
        //Para cifrar vamos a utilizar una funcion de modulo la cual es y=(x+z)mod27

        key=document.getElementById('llave').value;
        //Debemos verificar la llave
        key=key.replace(/ /g, '');

        //Vamos a traer los datos del mensaje
        let mess=document.getElementById('mess').value;
        //Debemos verificar la llave
        mess=mess.replace(/ /g, '');

        let newMess="";
        let keyCompleta="";

        //Para aplicar el algoritmos debemos crear una función que se encargue de revisar las condiciones del mismo

        if(revision(mess.key)){
            //Vamos primero por aplicar y obtener la poscion de la longitud del mensaje y emparejarlo contra la llave

            for(var i=0;i<mess.length;i++){
                //Emparejo conforme a la posicion del caracter obteniendo el número de dicha posición
                keyCompleta+=key.charAt((i%Number(key.length)));
            }
            alert(keyCompleta);

            //Tengo que volver a recorrel el mensaje para obtener caracteres y posiciones

            for(var i=0;i<mess.length;i++){
                //Obtener la posicion de la letra
                let charr=mess.charAt(i);

                //Debemos crear una funcion para obtener la posicion del caracter
                let posm=getPosicion(charr);

                //Aplicarlo a la llave
                charr=keyCompleta.charAt(i);

                //Obtenemos la posicion
                let posk=getPosicion(charr);

                //Tenemos que ejecutar el cifrado
                let newValores=cifrado(posm,posk);

                //Se está almacenando el resultado del cifrado
                newMess+=abc[newValores];
            }

            //Imprimir el resultado
            document.getElementById("rs").value=newMess;

        }else{
            //No se cumple
            alert("No se cumplió el cifrado");
        }
    });

    //DESCIFRAR

    $('#de').click(function(){
        //Para cifrar vamos a utilizar una funcion de modulo la cual es y=(x+z)mod27

        key=document.getElementById('llave').value;
        //Debemos verificar la llave
        key=key.replace(/ /g, '');

        //Vamos a traer los datos del mensaje
        let mess=document.getElementById('mess').value;
        //Debemos verificar la llave
        mess=mess.replace(/ /g, '');

        let newMess="";
        let keyCompleta="";

        //Para aplicar el algoritmos debemos crear una función que se encargue de revisar las condiciones del mismo

        if(revision(mess.key)){
            //Vamos primero por aplicar y obtener la poscion de la longitud del mensaje y emparejarlo contra la llave

            for(var i=0;i<mess.length;i++){
                //Emparejo conforme a la posicion del caracter obteniendo el número de dicha posición
                keyCompleta+=key.charAt((i%Number(key.length)));
            }
            alert(keyCompleta);

            //Tengo que volver a recorrel el mensaje para obtener caracteres y posiciones

            for(var i=0;i<mess.length;i++){
                //Obtener la posicion de la letra
                let charr=mess.charAt(i);

                //Debemos crear una funcion para obtener la posicion del caracter
                let posm=getPosicion(charr);

                //Aplicarlo a la llave
                charr=keyCompleta.charAt(i);

                //Obtenemos la posicion
                let posk=getPosicion(charr);

                //Tenemos que ejecutar el descifrado
                let newValores=descifrar(posm,posk);

                //Se está almacenando el resultado del cifrado
                newMess+=abc[newValores];
            }

            //Imprimir el resultado
            document.getElementById("rs").value=newMess;

        }else{
            //No se cumple
            alert("No se cumplió el descifrado");
        }
    });
})

//FUNCION DE CIFRADO
function cifrado(posm,posk){
    //Aplicamos la fórmula
    let y=(posm+posk)%27;
    return y
}

//FUNCION DE DESCIFRADO
function descifrar(posm,posk){
    //Vamos a obtener una variable val
    let val=0;
    if((posm-posk)>=0){
        //La posición existe asi que todo bien
        val=(posm+posk)%27;
    }else{
        //Compensamos con el +27 que se corran los caracteres
        val=(posm-posk+27)
    }return val;
}

//FUNCION DE LA POSICION

function getPosicion(letra){
    //indexOf retorna el primer índice en el que se puede encontrar un elemento dado en el array, ó retorna -1 si el elemento no esta presente
    let posicion=abc.indexOf(letra);
    return posicion;
}

//FUNCION DE LA REVISIÓN

function revision(mess,desp){
    //Primero hay que validar la entrada de los datos a partir de una expresión regular
    //El ? es un evaluador

    var expresion= /^([a-zñ?]+([]*[a-zñ?]?['-]?[a-zñ?]+)*)$/;
    
    var aceptado=true;

    //Empiezo a evaluar la expresión
    if(!expresion.test(mess)){
        alert("El texto que ingresó no ha sido aceptado, ingrese solo minúsculas y evite números y símbolos");
        aceptado=false;
    }
    
    if(!expresion.test(desp)){
        alert("La clave ingresada es incorrecta, no cumple con las normas de solo usar minúsculas sin números ni símbolos");
        aceptado=false;
    }

    if(desp.length>mess.length){
        alert("La clave no puede ser mayor al mensaje");
        aceptado=false;
    }
}