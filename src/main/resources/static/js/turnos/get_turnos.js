window.addEventListener('load', function () {

  listarTurnos();
  (function () {
    let pathname = window.location.pathname;
    if (pathname == "../../turnos/get_turnos.html") {
      document.querySelector(".nav .nav-item a:last").addClass("active");
    }
  })

});

function listarTurnos() {
  const url = '/turno';
  const settings = {
    method: 'GET'
  }

  fetch(url, settings)
    .then(response => response.json())
    .then(data => {
      for (turno of data) {
        var table = document.getElementById("turnoTable");
        var turnoRow = table.insertRow();
        let tr_id = 'tr_' + turno.id;
        turnoRow.id = tr_id;
        let deleteButton = '<button' +
          ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
          ' type="button" onclick="deleteBy(' + turno.id + ')"' +
          'class="btn btn-danger btn_delete">' +
          '&times' +
          '</button>';

        let updateButton = '<button' +
          ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
          ' type="button" onclick="loadTurno(' + turno.id + ')"' +
          ' class="btn btn-dark btn_id">' +
          turno.id +
          '</button>';


        turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
          '<td class=\"td_turno\">' + turno.nombreturno + '</td>' +
          '<td class=\"td_fecha\">' + turno.fecha + '</td>' +
          '<td class=\"td_paciente\">' + turno.paciente.nombre + ' ' + turno.paciente.apellido + '</td>' +
          '<td class=\"td_odontologo\">' + turno.odontologo.nombre + ' ' + turno.odontologo.apellido + '</td>' +
          '<td>' + deleteButton + '</td>';
      };
    });
}

function loadTurno(id) {
  window.location = '/turnos/update_turnos.html?id=' + id;
}