function listarPacientes() {
    let resultado = false;
    const url = '/paciente';
    const settings = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            for (paciente of data) {
                resultado = true;
                let option = document.createElement('option');
                option.value = paciente.id;
                option.text = paciente.nombre + ' ' + paciente.apellido;
                document.querySelector('#lstPacientes').appendChild(option);
            }

        });
    return resultado;
}

function listarOdontologos() {
    let resultado = false;
    const url = '/odontologo';
    const settings = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        }
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            for (odontologo of data) {
                resultado = true;
                let option = document.createElement('option');
                option.value = odontologo.id;
                option.text = odontologo.nombre + ' ' + odontologo.apellido;
                document.querySelector('#lstOdontologos').appendChild(option);
            }
        });
    return resultado;
}