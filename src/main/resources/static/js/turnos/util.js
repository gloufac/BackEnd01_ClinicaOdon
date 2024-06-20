function listarPacientes(id = 0) {
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
                if(id > 0 && id == paciente.id){
                    option.selected = true;
                }
                option.text = paciente.nombre + ' ' + paciente.apellido;
                document.querySelector('#lstPacientes').appendChild(option);
            }

        });
    return resultado;
}

function listarOdontologos(id = 0) {
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
                if(id > 0 && id == odontologo.id){
                    option.selected = true;
                }
                option.text = odontologo.nombre + ' ' + odontologo.apellido;
                document.querySelector('#lstOdontologos').appendChild(option);
            }
        });
    return resultado;
}

function hometurnos() {
    window.location = '/turnos/get_turnos.html';
}

function resetUploadForm() {
    document.querySelector('#fecha').value = "";
    document.querySelector('#lstPacientes').value = "0";
    document.querySelector('#lstOdontologos').value = "0";
}