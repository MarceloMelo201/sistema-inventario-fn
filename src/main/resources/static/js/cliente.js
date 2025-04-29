const apiUrl = "http://localhost:8080/clientes";
const form = document.querySelector("form");
const tableBody = document.querySelector("tbody");

let isEditing = false;
let editingId = null;

// Carregar clientes ao iniciar
document.addEventListener("DOMContentLoaded", listarClientes);

// Submeter formulário
form.addEventListener("submit", async function (e) {
    e.preventDefault();
    const dados = Object.fromEntries(new FormData(form).entries());

    try {
        const resposta = await fetch(
            isEditing ? `${apiUrl}/${editingId}` : apiUrl,
            {
                method: isEditing ? "PUT" : "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(dados),
            }
        );

        if (!resposta.ok) {
            const msg = await resposta.text();
            throw new Error(`Erro ${resposta.status}: ${msg}`);
        }

        form.reset();
        isEditing = false;
        editingId = null;
        listarClientes();
    } catch (erro) {
        alert("Erro ao salvar cliente: " + erro.message);
    }
});

// Listar todos os clientes
async function listarClientes() {
    try {
        const resposta = await fetch(apiUrl);
        const clientes = await resposta.json();

        tableBody.innerHTML = ""; // limpar

        clientes.forEach((cliente) => {
            const tr = document.createElement("tr");

            tr.innerHTML = `
                <td>${cliente.nomeCliente}</td>
                <td>${cliente.emailCliente}</td>
                <td>${cliente.telefone}</td>
                <td>${cliente.uf}</td>
                <td>
                    <button class="edit-button" onclick="editarCliente('${cliente.idCliente}')">Editar</button>
                    <button onclick="deletarCliente('${cliente.idCliente}')">Excluir</button>
                </td>
            `;

            tableBody.appendChild(tr);
        });
    } catch (erro) {
        alert("Erro ao listar clientes: " + erro.message);
    }
}

// Editar cliente
async function editarCliente(id) {
    try {
        const resposta = await fetch(`${apiUrl}/${id}`);
        const cliente = await resposta.json();

        // Preencher o formulário com os dados
        for (const key in cliente) {
            if (form.elements[key]) {
                form.elements[key].value = cliente[key];
            }
        }

        isEditing = true;
        editingId = id;
    } catch (erro) {
        alert("Erro ao buscar cliente: " + erro.message);
    }
}

// Excluir cliente
async function deletarCliente(id) {
    if (!confirm("Tem certeza que deseja excluir este cliente?")) return;

    try {
        const resposta = await fetch(`${apiUrl}/${id}`, {
            method: "DELETE",
        });

        if (!resposta.ok) {
            const msg = await resposta.text();
            throw new Error(msg);
        }

        listarClientes();
    } catch (erro) {
        alert("Erro ao deletar cliente: " + erro.message);
    }
}
