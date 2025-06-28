const apiBase = 'http://localhost:8080/clientes';

function listarClientes() {
    fetch(apiBase)
        .then(res => res.json())
        .then(data => {
            const tbody = document.getElementById('clientesTabela');
            tbody.innerHTML = '';
            data.forEach(cliente => {
                const telefone = cliente.telefones.length > 0 ? cliente.telefones[0].numero : '';
                tbody.innerHTML += `
                    <tr data-id="${cliente.idCliente}">
                        <td>${cliente.nomeCliente}</td>
                        <td>${cliente.emailCliente}</td>
                        <td>${telefone}</td>
                        <td>${cliente.uf}</td>
                        <td class="actions">
                            <button onclick="editarCliente('${cliente.idCliente}')">Editar</button>
                            <button onclick="deletarCliente('${cliente.idCliente}')">Excluir</button>
                        </td>
                    </tr>
                `;
            });
        })
        .catch(err => console.error("Erro ao listar clientes:", err));
}

function salvarCliente() {
    const clienteId = document.getElementById('clienteId').value.trim();

    const method = clienteId ? 'PUT' : 'POST';
    const url = clienteId ? `${apiBase}/${clienteId}` : apiBase;

    if (method === 'POST'){
        const cliente = {
            nomeCliente: document.getElementById('nomeCliente').value,
            emailCliente: document.getElementById('emailCliente').value,
            numero: document.getElementById('numero').value,
            cep: document.getElementById('cep').value,
            cidade: document.getElementById('cidade').value,
            bairro: document.getElementById('bairro').value,
            uf: document.getElementById('uf').value
        };

        fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(cliente)
        })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(text) });
            }
            return response.json();
        })
        .then(() => {
            resetarFormulario();
            listarClientes();
        })
        .catch(err => console.error("Erro ao salvar cliente:", err));
    } else {
        const cliente = {
            idCliente: document.getElementById('clienteId').value,
            nomeCliente: document.getElementById('nomeCliente').value,
            emailCliente: document.getElementById('emailCliente').value,
            numero: document.getElementById('numero').value,
            cep: document.getElementById('cep').value,
            cidade: document.getElementById('cidade').value,
            bairro: document.getElementById('bairro').value,
            uf: document.getElementById('uf').value
        };
        
        fetch(url, {
            method: method,
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(cliente)
        })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(text) });
            }
            return response.json();
        })
        .then(() => {
            resetarFormulario();
            listarClientes();
        })
        .catch(err => console.error("Erro ao salvar cliente:", err));
    }
}


function editarCliente(id) {
    fetch(`${apiBase}/${id}`)
        .then(res => {
            if (!res.ok) throw new Error("Erro ao buscar cliente para edição");
            return res.json();
        })
        .then(cliente => {
            document.getElementById('clienteId').value = cliente.idCliente;  // importante!
            document.getElementById('nomeCliente').value = cliente.nomeCliente;
            document.getElementById('emailCliente').value = cliente.emailCliente;
            document.getElementById('numero').value = cliente.telefones.length > 0 ? cliente.telefones[0].numero : '';
            document.getElementById('cep').value = cliente.cep;
            document.getElementById('cidade').value = cliente.cidade;
            document.getElementById('bairro').value = cliente.bairro;
            document.getElementById('uf').value = cliente.uf;
        })
        .catch(err => console.error("Erro ao carregar cliente:", err));
}


function deletarCliente(id) {
    fetch(`${apiBase}/${id}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (!response.ok) {
                return response.text().then(text => { throw new Error(text) });
            }
            listarClientes();
        })
        .catch(err => console.error("Erro ao deletar cliente:", err));
}

function resetarFormulario() {
    document.getElementById('clienteId').value = '';
    document.getElementById('nomeCliente').value = '';
    document.getElementById('emailCliente').value = '';
    document.getElementById('numero').value = '';
    document.getElementById('cep').value = '';
    document.getElementById('cidade').value = '';
    document.getElementById('bairro').value = '';
    document.getElementById('uf').value = 'SP';
}

document.addEventListener('DOMContentLoaded', listarClientes);