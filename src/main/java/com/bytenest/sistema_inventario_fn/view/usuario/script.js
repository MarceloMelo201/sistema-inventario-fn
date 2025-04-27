document.getElementById('formCadastro').addEventListener('submit', function(event) {
    event.preventDefault();

    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;
    const cargo = document.getElementById('cargo').value;

    const userData = {
        email: email,
        senha: senha,
        cargo: cargo
    };

    fetch('http://localhost:8080/usuarios', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('responseMessage').textContent = "Usuário cadastrado com sucesso!";
        document.getElementById('responseMessage').style.color = "green";
    })
    .catch(error => {
        document.getElementById('responseMessage').textContent = "Erro ao cadastrar usuário: " + error;
        document.getElementById('responseMessage').style.color = "red";
    });
});
