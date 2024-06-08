
function update_paciente() {
    //creamos un JSON que tendrá los datos de la pacientea diferencia de una paciente nueva, en este caso enviamos el ID para poder identificarla y modificarla para no cargarla como nueva
    const formDomicilio = {
        id: document.querySelector('#domicilio_id').value,
        calle: document.querySelector('#calle').value,
        numero: document.querySelector('#numero').value,
        localidad: document.querySelector('#localidad').value,
        provincia: document.querySelector('#provincia').value,
    };

    const formData = {
        id: document.querySelector('#paciente_id').value,
        cedula: document.querySelector('#cedula').value,
        nombre: document.querySelector('#nombre').value,
        apellido: document.querySelector('#apellido').value,
        fechaIngreso: document.querySelector('#fechaingreso').value,
        email: document.querySelector('#email').value,
        domicilio: formDomicilio
    };

    //invocamos utilizando la función fetch la API paciente con el método PUT que modificará la paciente que enviaremos en formato JSON
    const url = '/paciente';
    const settings = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
    }
    fetch(url, settings)
        .then((response) => response.text())
        .then(response => {
            let successAlert = '<div class="alert alert-success alert-dismissible"><strong>Respuesta</strong> ' + response + ' </div>'
            document.querySelector('#response').innerHTML = successAlert;
            document.querySelector('#response').style.display = "block";
            //window.location.href = "get_pacientes.html";

        });
}

window.addEventListener('load', function () {
    //Buscamos y obtenemos el formulario donde están
    //los datos que el usuario pudo haber modificado de la paciente
    const formulario = document.querySelector('#update_paciente');

    if (formulario !== null) {
        formulario.addEventListener('submit', function (event) {
            // codigo de update_paciente  
        })
    }
})



//Es la función que se invoca cuando se hace clic sobre el ID de una paciente del listado se encarga de llenar el formulario con los datos de la paciente que se desea modificar
function findBy(id) {
    const url = '/paciente' + "/" + id;
    const settings = {
        method: 'GET'
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let paciente = data;
            document.querySelector('#paciente_id').value = paciente.id;
            document.querySelector('#cedula').value = paciente.cedula;
            document.querySelector('#nombre').value = paciente.nombre;
            document.querySelector('#apellido').value = paciente.apellido;
            document.querySelector('#fechaingreso').value = paciente.fechaIngreso;
            document.querySelector('#email').value = paciente.email;

            document.querySelector('#domicilio_id').value = paciente.domicilio.id;
            document.querySelector('#calle').value = paciente.domicilio.calle;
            document.querySelector('#numero').value = paciente.domicilio.numero;
            document.querySelector('#localidad').value = paciente.domicilio.localidad;
            document.querySelector('#provincia').value = paciente.domicilio.provincia;
            document.querySelector('#response').innerHTML = "";
            document.querySelector('#staticBackdropLabel').innerHTML = "Actualizar Paciente";
            //el formulario por default está oculto y al editar se habilita

            //document.querySelector('#div_paciente_adding').style.display = "block";
            var myModal = new bootstrap.Modal(document.getElementById('staticBackdrop'));
            myModal.show();

        }).catch(error => {
            alert("Error: " + error);
        })

}