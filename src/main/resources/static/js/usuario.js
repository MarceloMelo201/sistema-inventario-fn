const apiUrl = "http://localhost:8080/usuarios";

document.addEventListener("DOMContentLoaded", () => {
    carregarUsuarios();

    const form = document.getElementById("usuarioForm");
    form.addEventListener("submit", async (e) => {
        e.preventDefault();
        await salvarUsuario();
    });
});

async function carregarUsuarios() {
    const tbody = document.querySelector("#usuariosTable tbody");
    tbody.innerHTML = "";

    try {
        const response = await fetch(apiUrl);
        const usuarios = await response.json();

        usuarios.forEach(usuario => {
            const tr = document.createElement("tr");

            tr.innerHTML = `
                <td>${usuario.id}</td>
                <td>${usuario.email}</td>
                <td>${usuario.cargo}</td>
                <td>
                    <button class="edit-button" onclick="editarUsuario(${usuario.id})">Editar</button>
                    <button onclick="deletarUsuario(${usuario.id})">Excluir</button>
                </td>
            `;

            tbody.appendChild(tr);
        });
    } catch (error) {
        console.error("Erro ao carregar usuários:", error);
    }
}

async function salvarUsuario() {
    const email = document.getElementById("email").value;
    const senha = document.getElementById("senha").value;
    const cargo = document.getElementById("cargo").value;

    try {
        const response = await fetch(apiUrl, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ email, senha, cargo }),
        });

        if (response.ok) {
            alert("Usuário salvo com sucesso!");
            document.getElementById("usuarioForm").reset();
            carregarUsuarios();
        } else {
            const errorData = await response.text();
            alert("Erro ao salvar usuário: " + errorData);
        }
    } catch (error) {
        console.error("Erro:", error);
    }
}

async function deletarUsuario(id) {
    if (confirm("Tem certeza que deseja excluir este usuário?")) {
        try {
            const response = await fetch(`${apiUrl}/${id}`, {
                method: "DELETE",
            });

            if (response.ok) {
                alert("Usuário deletado com sucesso!");
                carregarUsuarios();
            } else {
                const errorData = await response.text();
                alert("Erro ao deletar usuário: " + errorData);
            }
        } catch (error) {
            console.error("Erro:", error);
        }
    }
}

async function editarUsuario(id) {
    const novoEmail = prompt("Novo email:");
    const novaSenha = prompt("Nova senha:");
    const novoCargo = prompt("Novo cargo (ADMIN ou USUARIO):");

    if (novoEmail && novaSenha && novoCargo) {
        try {
            const response = await fetch(`${apiUrl}/${id}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    email: novoEmail,
                    senha: novaSenha,
                    cargo: novoCargo,
                }),
            });

            if (response.ok) {
                alert("Usuário atualizado com sucesso!");
                carregarUsuarios();
            } else {
                const errorData = await response.text();
                alert("Erro ao atualizar usuário: " + errorData);
            }
        } catch (error) {
            console.error("Erro:", error);
        }
    } else {
        alert("Todos os campos são obrigatórios para atualizar.");
    }
}
