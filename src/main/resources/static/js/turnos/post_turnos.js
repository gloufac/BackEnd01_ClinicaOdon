window.addEventListener('load', function () {

    let resultadoPacientes = listarPacientes();
    let resultadoOdontologos = listarOdontologos();

    /*if (!resultadoPacientes || !resultadoOdontologos) {
        this.alert('No hay pacientes u odontologos configurados');
        location.replace("../index.html")
        return;
    }*/


    const formulario = document.querySelector('#add_new_turno');
    if (formulario !== null) {
        formulario.addEventListener('submit', function (event) {
            const selPaciente = {
                id: document.querySelector('#lstPacientes').value
            };
            const selOdontologo = {
                id: document.querySelector('#lstOdontologos').value
            };
            const formData = {
                fecha: document.querySelector('#fecha').value,
                paciente: selPaciente,
                odontologo: selOdontologo,
            };

            const url = '/turno';
            const settings = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            }

            fetch(url, settings)
                .then(response => response.json())
                .then(data => {
                    //console.log(data);
                    if(data.status != undefined && data.status !== 200){
                        let error = data.error;
                        let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                        '<strong> '+error+'</strong> </div>'
                        document.querySelector('#response').innerHTML = errorAlert;
                        document.querySelector('#response').style.display = "block";
                    } else {
                        let successAlert = '<div class="alert alert-success alert-dismissible"><strong>Resultado:</strong> Turno agregado </div>'
                        document.querySelector('#response').innerHTML = successAlert;
                        document.querySelector('#response').style.display = "block";
                        resetUploadForm();
                    }
                })
                .catch(error => {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                        '<strong> Error intente nuevamente</strong> </div>'

                    document.querySelector('#response').innerHTML = errorAlert;
                    document.querySelector('#response').style.display = "block";

                    resetUploadForm();
                })

                event.preventDefault();
                
        });

    }
});
