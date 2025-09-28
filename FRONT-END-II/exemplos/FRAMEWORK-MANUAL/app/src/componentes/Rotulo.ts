import { criarElemento } from "../utils.js";

const Rotulo = (texto: string) => {

    const rotulo = criarElemento("label", texto);
    return rotulo;
};

export default Rotulo;