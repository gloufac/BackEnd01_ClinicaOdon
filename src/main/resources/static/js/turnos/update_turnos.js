function update_turno() {
    const selPaciente = {
        id: document.querySelector('#lstPacientes').value
    };
    const selOdontologo = {
        id: document.querySelector('#lstOdontologos').value
    };
    const formData = {
        id: document.querySelector('#turno_id').value,
        nombreturno: document.querySelector('#turnoname').value,
        fecha: document.querySelector('#fecha').value,
        paciente: selPaciente,
        odontologo: selOdontologo,
    };


    const url = '/turno';
    const settings = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
    }

    fetch(url, settings)
        .then((response) => response)
        .then(response => {
            if (response.status !== 200) {
                response.text().then((text) => {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                        '<strong> ' + text + '</strong> </div>'
                    document.querySelector('#response').innerHTML = errorAlert;
                    document.querySelector('#response').style.display = "block";
                });
                return;
            } else {
                response.text().then((text) => {
                    console.log(text);
                    let successAlert = '<div class="alert alert-success alert-dismissible"><strong>Respuesta</strong> ' + text + ' </div>'
                    document.querySelector('#response').innerHTML = successAlert;
                    document.querySelector('#response').style.display = "block";
                });
            }
        });
}

window.addEventListener('load', function () {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(queryString);
    const idTurno = urlParams.get('id')
    const formulario = document.querySelector('#update_turno');
    if (formulario !== null) {
        findBy(idTurno);

        formulario.addEventListener('submit', function (event) {
            event.preventDefault();
            update_turno();
        })
    }
})

function findBy(id) {
    const url = '/turno' + "/" + id;
    const settings = {
        method: 'GET'
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let turno = data;
            document.querySelector('#turno_id').value = turno.id;
            document.querySelector('#turnoname').value = turno.nombreturno;
            document.querySelector('#fecha').value = turno.fecha;

            listarPacientes(turno.paciente.id);
            listarOdontologos(turno.odontologo.id);
        }).catch(error => {
            alert("Error: " + error);
        })

}