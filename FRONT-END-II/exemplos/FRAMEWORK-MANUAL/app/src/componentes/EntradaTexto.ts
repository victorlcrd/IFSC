import { criarElemento, iAtributos } from "../utils.js";
import Rotulo from "./Rotulo.js";
    
const EntradaTexto = (texto: string, tipo = "text", atributos: iAtributos = {}) => {

    const inputContainer = criarElemento("div");
    const atributosInput: iAtributos = {
        ...atributos,
        placeholder: "Digite algo " + texto,
        type: tipo,
    };
    const input = criarElemento('input', "", atributosInput) as HTMLInputElement;
    const rotulo = Rotulo(texto);

    inputContainer.appendChild(rotulo);
    inputContainer.appendChild(input);

    return inputContainer;
};
export default EntradaTexto;