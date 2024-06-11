window.addEventListener('load', function () {
  (function () {
    const url = '/turnos';
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
            ' type="button" onclick="findBy(' + turno.id + ')"' +
            ' class="btn btn-info btn_id">' +
            turno.id +
            '</button>';


          turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
            '<td class=\"td_cedula\">' + turno.cedula + '</td>' +
            '<td class=\"td_nombrecompleto\">' + paciente.nombre + ' ' + paciente.apellido + '</td>' +
            '<td class=\"td_fechaingreso\">' + paciente.fechaIngreso + '</td>' +
            '<td class=\"td_email\">' + paciente.email + '</td>' +
            '<td class=\"td_domicilio\">' + paciente.domicilio.calle +' '+ paciente.domicilio.numero+' '+ paciente.domicilio.localidad+' '+ paciente.domicilio.provincia + '</td>' +
            '<td>' + deleteButton + '</td>';
        };
      })
  })



    (function () {
      let pathname = window.location.pathname;
      if (pathname == "get_pacientes.html") {
        document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })

})