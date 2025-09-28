// function inteiroUnico(){
//    return inteiroUnico.contador++
//}

//inteiroUnico.contador = 1;

function GerarInteiroUnico(){
    let contador = 1;
    return () => contador++;
}

const inteiroUnico = GerarInteiroUnico();

console.log(inteiroUnico);
console.log(inteiroUnico());
console.log(inteiroUnico());
console.log(inteiroUnico());
console.log(inteiroUnico());
console.log(inteiroUnico());
console.log(inteiroUnico());
console.log(inteiroUnico());