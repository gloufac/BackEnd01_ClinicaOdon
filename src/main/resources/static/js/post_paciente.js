window.addEventListener('load', function () {
    //Al cargar la página buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará del nuevo paciente

    const formulario = document.querySelector('#add_new_paciente');

    if (formulario !== null) {
        //Ante un submit del formulario
        //se ejecutará la siguiente función
        formulario.addEventListener('submit', function (event) {
            //Si el ID del paciente es distinto de vacío
            if(document.querySelector('#paciente_id').value !== ""){
                update_paciente();
                event.preventDefault();
                return;
            }

            //creamos un JSON que tendrá los datos del nuevo paciente
            const formDomicilio = {
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value,
            };
            const formData = {
                cedula: document.querySelector('#cedula').value,
                nombre: document.querySelector('#nombre').value,
                apellido: document.querySelector('#apellido').value,
                fechaIngreso: document.querySelector('#fechaingreso').value,
                email: document.querySelector('#email').value,
                domicilio: formDomicilio
            };

            //invocamos la API utilizando la función fetch de JavaScript
            //con el método POST que guardará
            //el paciente que enviaremos en formato JSON
            const url = '/paciente';
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
                    //Si no hay ningún error, se muestra un mensaje diciendo que el paciente fue agregado
                    let successAlert = '<div class="alert alert-success alert-dismissible"><strong>Resultado:</strong> Paciente agregado </div>'
                    document.querySelector('#response').innerHTML = successAlert;
                    document.querySelector('#response').style.display = "block";
                    resetUploadForm();
                })

                .catch(error => {
                    //Si hay algún error, se muestra un mensaje diciendo que el paciente no se pudo guardar y se intente nuevamente
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                        '<button type="button" class="close"' +
                        'data-dismiss="alert">&times;</button>' +
                        '<strong> Error intente nuevamente</strong> </div>'

                    document.querySelector('#response').innerHTML = errorAlert;
                    document.querySelector('#response').style.display = "block";

                    //se dejan todos los campos vacíos por si se quiere ingresar otro paciente
                    resetUploadForm();
                })

                event.preventDefault();
                
        });

    }
});

function resetUploadForm() {
    document.querySelector('#cedula').value = "";
    document.querySelector('#nombre').value = "";
    document.querySelector('#apellido').value = "";
    document.querySelector('#fechaingreso').value = "";
    document.querySelector('#email').value = "";
    document.querySelector('#calle').value = "";
    document.querySelector('#numero').value = "";
    document.querySelector('#localidad').value = "";
    document.querySelector('#provincia').value = "";
}


$(document).ready(function(){    
    $("#staticBackdrop").on('hide.bs.modal', function(){
        resetUploadForm();
        listarPacientes();
        document.querySelector('#staticBackdropLabel').innerHTML = "Agregar Paciente";
    });
});