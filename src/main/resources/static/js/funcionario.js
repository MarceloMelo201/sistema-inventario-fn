const API_URL = 'http://localhost:8080/funcionarios';

document.getElementById('formFuncionario').addEventListener('submit', async function (e) {
    e.preventDefault();

    const form = e.target;
    const data = {
        nome: form.nome.value,
        cpf: form.cpf.value,
        email: form.email.value,
        telefone: form.telefone.value,
        status: form.status.value
    };

    const response = await fetch(API_URL, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
    });

    if (response.ok) {
        alert('Funcionário cadastrado com sucesso!');
        form.reset();
        carregarFuncionarios();
    } else {
        alert('Erro ao cadastrar funcionário.');
    }
});

async function carregarFuncionarios() {
    const response = await fetch(API_URL);
    const funcionarios = await response.json();

    const lista = document.getElementById('listaFuncionarios');
    lista.innerHTML = '';

    funcionarios.forEach(func => {
        const row = `
            <tr>
                <td>${func.nome}</td>
                <td>${func.cpf}</td>
                <td>${func.email}</td>
                <td>${func.telefone}</td>
                <td>${func.statusFuncionario}</td>
                <td><button onclick="deletarFuncionario('${func.idFunc}')">Deletar</button></td>
            </tr>
        `;
        lista.innerHTML += row;
    });
}

async function deletarFuncionario(id) {
    if (confirm('Deseja realmente deletar este funcionário?')) {
        const response = await fetch(`${API_URL}/${id}`, { method: 'DELETE' });

        if (response.ok) {
            alert('Funcionário deletado com sucesso.');
            carregarFuncionarios();
        } else {
            alert('Erro ao deletar funcionário.');
        }
    }
}

// Carrega automaticamente ao abrir
window.onload = carregarFuncionarios;
