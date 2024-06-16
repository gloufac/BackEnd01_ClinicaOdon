//El evento load se ejecuta al cargar la página que muestra la lista de pacientes
window.addEventListener('load', function () {
  listarPacientes();

  (function () {
    let pathname = window.location.pathname;
    if (pathname == "get_pacientes.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  });

})

function listarPacientes() {
  const url = '/paciente';
  const settings = {
    method: 'GET'
  }

  fetch(url, settings)
    .then(response => response.json())
    .then(data => {
      //recorremos la colección de pacientes del JSON
      var table = document.getElementById("pacienteTable");
      table.innerHTML = '';
      for (paciente of data) {
        var pacienteRow = table.insertRow();
        let tr_id = 'tr_' + paciente.id;
        pacienteRow.id = tr_id;
        //por cada paciente creamos un botón delete que agregaremos en cada fila para poder eliminar la misma
        //dicho botón invocará a la función de JavaScript deleteByKey que se encargará de llamar a la API para eliminar una paciente

        let deleteButton = '<button' +
          ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
          ' type="button" onclick="deleteBy(' + paciente.id + ')"' +
          'class="btn btn-danger btn_delete">' +
          '&times' +
          '</button>';

        //por cada paciente creamos un botón que muestra el ID
        //y que al hacerle clic invocará a la función de JavaScript findBy
        //que se encargará de buscar la paciente que queremos modificar
        //y mostrar los datos de la misma en un formulario.

        let updateButton = '<button' +
          ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
          ' type="button" onclick="findBy(' + paciente.id + ')"' +
          ' class="btn btn-info btn_id">' +
          paciente.id +
          '</button>';



        //armamos cada columna de la fila
        //como primera columna pondremos el botón modificar
        //luego los datos de la paciente
        //como última columna, el botón eliminar
        pacienteRow.innerHTML = '<td>' + updateButton + '</td>' +
          '<td class=\"td_cedula\">' + paciente.cedula + '</td>' +
          '<td class=\"td_nombrecompleto\">' + paciente.nombre + ' ' + paciente.apellido + '</td>' +
          '<td class=\"td_fechaingreso\">' + paciente.fechaIngreso + '</td>' +
          '<td class=\"td_email\">' + paciente.email + '</td>' +
          '<td class=\"td_domicilio\">' + paciente.domicilio.calle + ' ' + paciente.domicilio.numero + ' ' + paciente.domicilio.localidad + ' ' + paciente.domicilio.provincia + '</td>' +
          '<td>' + deleteButton + '</td>';
      };
    })
}