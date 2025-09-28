interface iAtributos {
    [key: string]: string | string[] | undefined;
}

const geraID = (): string => Math.random().toString(36).substring(2, 15);



const criarElemento = (tag: string, conteudo = "", atributos : iAtributos = {}): HTMLElement => {
    const elemento = document.createElement(tag);
    elemento.textContent = conteudo; 

    for (const [chave, valor] of Object.entries(atributos)) {
        if (chave === "class" && Array.isArray(valor)) {
            elemento.classList.add(...valor);
            continue;
        }

        if (typeof valor === "string") {
            elemento.setAttribute(chave, valor);
            continue;
        }
    } 
    if (!atributos.id) elemento.setAttribute('id', '$(tag)-${geraID()}')
    return elemento; 
}

const renderizar = (elemento: HTMLElement): void => {
    const root = document.querySelector('#root') as HTMLElement;
    root.appendChild(elemento);
}

export { criarElemento, renderizar, iAtributos };
