import { criarElemento } from "../utils.js";
import EntradaTexto from "./EntradaTexto.js";
const Formulario = () => {
    const formulario = criarElemento("form", "CHORHINTHINHAHS");
    const input = EntradaTexto("Nome: ");
    formulario.appendChild(input);
    const inputSobrenome = EntradaTexto("Sobrenome: ");
    formulario.appendChild(inputSobrenome);
    return formulario;
};
export default Formulario;
