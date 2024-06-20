window.addEventListener('load', function () {
    //Al cargar la página buscamos y obtenemos el formulario donde estarán
    //los datos que el usuario cargará del nuevo odontologo

    const formulario = document.querySelector('#add_new_odontologo');

    if (formulario !== null) {
        //Ante un submit del formulario
        //se ejecutará la siguiente función
        formulario.addEventListener('submit', function (event) {
            //Si el ID del odontologo es distinto de vacío
            if (document.querySelector('#odontologo_id').value !== "") {
                update_odontologo();
                event.preventDefault();
                return;
            }

            const formData = {
                numeroMatricula: document.querySelector('#numeroMatricula').value,
                nombre: document.querySelector('#nombre').value,
                apellido: document.querySelector('#apellido').value,
            };

            //invocamos la API utilizando la función fetch de JavaScript
            //con el método POST que guardará
            //el odontologo que enviaremos en formato JSON
            const url = '/odontologo';
            const settings = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(formData)
            }

            fetch(url, settings)
                .then(response => response)
                .then(data => {
                    if (data.status !== 200) {
                        data.text().then((text) => {
                            let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                '<strong> ' + text + '</strong> </div>'
                            document.querySelector('#response').innerHTML = errorAlert;
                            document.querySelector('#response').style.display = "block";
                        });
                        return;
                    } else {
                        //Si no hay ningún error, se muestra un mensaje diciendo que el paciente fue agregado
                        data.json().then((json) => {
                            //Si no hay ningún error, se muestra un mensaje diciendo que el odontologo fue agregado
                            let successAlert = '<div class="alert alert-success alert-dismissible"><strong>Resultado:</strong> Odontologo agregado </div>'
                            document.querySelector('#response').innerHTML = successAlert;
                            document.querySelector('#response').style.display = "block";
                            resetUploadForm();
                        });
                    }
                })

                .catch(error => {
                    //Si hay algún error, se muestra un mensaje diciendo que el odontologo no se pudo guardar y se intente nuevamente
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
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
    document.querySelector('#numeroMatricula').value = "";
    document.querySelector('#nombre').value = "";
    document.querySelector('#apellido').value = "";
}


$(document).ready(function () {
    $("#staticBackdrop").on('hide.bs.modal', function () {
        resetUploadForm();
        listarOdontologos();
        document.querySelector('#response').innerHTML = "";
        document.querySelector('#staticBackdropLabel').innerHTML = "Agregar Odontologo";
    });
});