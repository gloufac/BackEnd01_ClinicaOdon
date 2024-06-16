function deleteBy(id) {
    //con fetch invocamos a la API de pacientes con el método DELETE pasándole el ID en la URL
    const url = '/turno/'+ id;
    const settings = {
       method: 'DELETE',
    }

    fetch(url,settings)
    .then((response) => response.text())
    .then(response => {
       alert(response);
    });

    //borrar la fila el paciente eliminado
    let row_id = "#tr_" + id;
    document.querySelector(row_id).remove();
}