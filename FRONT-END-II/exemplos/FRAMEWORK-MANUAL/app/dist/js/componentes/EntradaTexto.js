import { criarElemento } from "../utils.js";
import Rotulo from "./Rotulo.js";
const EntradaTexto = (texto, tipo = "text", atributos = {}) => {
    const inputContainer = criarElemento("div");
    const atributosInput = {
        ...atributos,
        placeholder: "Digite algo " + texto,
        type: tipo,
    };
    const input = criarElemento('input', "", atributosInput);
    const rotulo = Rotulo(texto);
    inputContainer.appendChild(rotulo);
    inputContainer.appendChild(input);
    return inputContainer;
};
export default EntradaTexto;
