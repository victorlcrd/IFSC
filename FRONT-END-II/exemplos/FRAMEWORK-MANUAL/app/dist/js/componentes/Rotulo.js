import { criarElemento } from "../utils.js";
const Rotulo = (texto) => {
    const rotulo = criarElemento("label", texto);
    return rotulo;
};
export default Rotulo;
