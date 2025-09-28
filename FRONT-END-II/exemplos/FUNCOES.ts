// Funções nomeadas

function saudacao(nome: String): String {
    return `Olá, ` + nome;
}

// Função seta
let saudacao2 = (nome: String): String => {
    return `Olá, ` + nome;
}

// Função seta com retorno implícito
let saudacao3 = (nome: String): String => `Olá, ` + nome;

//Expressão de função
let saudacao4 = function (nome: String): String {
    return `Olá, ` + nome;
} 

// Parâmetros opcionais e padrão
function log(message: String, userId?: String ) {
    let time = new Date().toLocaleTimeString();
    console.log(time, message, userId || 'Not signed in');
}

function log2(message: String, userId = 'Not signed in') {
    let time = new Date().toLocaleTimeString();
    console.log(time, message, userId);
}

//Parâmetros REST
function calcularArea(...args: number[]): number {
    if (args.length === 1) {
        return Math.PI * args[0] ** 2;
    } else if (args.length === 2) {
        return args[0] * args[1];
    } else {
        throw new Error("Número inválido de argumentos");
    }
}

function somar(...nums: number[]): number {
    return nums.reduce((total, n) => total + n, 0);
}

// Uso do type
type Operacao = (a: number, b: number) => number;

const somar1: Operacao = (a, b) => a + b;
const subtrair: Operacao = (a, b) => a - b;
const multiplicar: Operacao = (a, b) => a * b;
const dividir: Operacao = (a, b) => a / b;

// Uso de type na completa com sobrecarga
type Operacao2 = {
   (a2: number, b2: number): number;
    (a2: string, b2: string): string;   
}

// Teste para garantir que os dois são do mesmo tipo
const combinar: Operacao2 = (a2, b2): any => {
    if (typeof a2 === 'number' && typeof b2 === 'string') {
        return a2.toString().concat(b2);
    } else if (typeof a2 === 'string' && typeof b2 === 'number') {
        return a2 + b2.toString();
    }
}

// Assinatura de função com sobrecarga
function formatarData(data: Date): string;
function formatarData(texto: string, maiusculas?: boolean): string;

function formatarData (a: Date | string, b2?: boolean): string {
    if (a instanceof Date) {
        return a.toISOString().slice(0, 10);
    } if (typeof a === 'string') {
        return b2 ? a.toUpperCase() : a.toLowerCase();
    }
    return '';
}