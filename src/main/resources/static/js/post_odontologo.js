window.addEventListener('load', function () {
    //Al cargar la página buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará del nuevo odontologo

    const formulario = document.querySelector('#add_new_odontologo');
    console.log(formulario);
    if (formulario !== null) {
        //Ante un submit del formulario
        //se ejecutará la siguiente función
        formulario.addEventListener('submit', function (event) {
            //Si el ID del odontologo es distinto de vacío
            console.log('odontologo_id',document.querySelector('#odontologo_id'))
            if(document.querySelector('#odontologo_id').value !== ""){
                update_odontologo(); //revisar
                event.preventDefault();
                return;
            }

            const formData = {
                numeroMatricula: document.querySelector('#numeroMatricula').value
                nombre: document.querySelector('#nombre').value,
                apellido: document.querySelector('#apellido').value,
            };

            //invocamos la API utilizando la función fetch de JavaScript
            //con el método POST que guardará
            //el odontologo que enviaremos en formato JSON
            const url = '/odontologos';
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
                    //Si no hay ningún error, se muestra un mensaje diciendo que el odontologo fue agregado
                    let successAlert = '<div class="alert alert-success alert-dismissible"><strong>Resultado:</strong> Odontologo agregado </div>'
                    document.querySelector('#response').innerHTML = successAlert;
                    document.querySelector('#response').style.display = "block";
                    resetUploadForm();
                })

                .catch(error => {
                    //Si hay algún error, se muestra un mensaje diciendo que el odontologo no se pudo guardar y se intente nuevamente
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
        document.querySelector('#staticBackdropLabel').innerHTML = "Agregar Paciente";
    });
});