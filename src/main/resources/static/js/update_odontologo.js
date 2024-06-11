
function update_odontologo() {
    //creamos un JSON que tendrá los datos de la diferencia de un odontologo nuevo, en este caso enviamos el ID para poder identificarla y modificarla para no cargarla como nueva

    const formData = {
        id: document.querySelector('#odontologo_id').value,
        numeroMatricula: document.querySelector('#numeroMatricula').value,
        nombre: document.querySelector('#nombre').value,
        apellido: document.querySelector('#apellido').value
    };

    //invocamos utilizando la función fetch la API odontologo con el método PUT que modificará el odontologo que enviaremos en formato JSON
    const url = '/odontologo';
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
            //window.location.href = "get_odontologos.html";

        });
}

window.addEventListener('load', function () {
    //Buscamos y obtenemos el formulario donde están
    //los datos que el usuario pudo haber modificado del odontologo
    const formulario = document.querySelector('#update_odontologo');

    if (formulario !== null) {
        formulario.addEventListener('submit', function (event) {
            // codigo de update_odontologo
        })
    }
})



//Es la función que se invoca cuando se hace clic sobre el ID de un odontologo del listado se encarga de llenar el formulario con los datos del odontologo que se desea modificar
function findBy(id) {
    const url = '/odontologo' + "/" + id;
    const settings = {
        method: 'GET'
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let odontologo = data;
            document.querySelector('#odontologo_id').value = odontologo.id;
            document.querySelector('#numeroMatricula').value = odontologo.numeroMatricula;
            document.querySelector('#nombre').value = odontologo.nombre;
            document.querySelector('#apellido').value = odontologo.apellido;

            document.querySelector('#response').innerHTML = "";
            document.querySelector('#staticBackdropLabel').innerHTML = "Actualizar Odontologo";
            //el formulario por default está oculto y al editar se habilita

            //document.querySelector('#div_odontologo_adding').style.display = "block";
            var myModal = new bootstrap.Modal(document.getElementById('staticBackdrop'));
            myModal.show();

        }).catch(error => {
            alert("Error: " + error);
        })

}