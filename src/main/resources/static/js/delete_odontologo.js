function deleteBy(id) {
         //con fetch invocamos a la API de odontologos con el método DELETE pasándole el ID en la URL
         const url = '/odontologo/'+ id;
         const settings = {
            method: 'DELETE',
         }

         fetch(url,settings)
         .then((response) => response.text())
         .then(response => {
            alert(response);
         });

         //borrar la fila el odontologo eliminado
         let row_id = id;
         document.querySelector(row_id).remove();
}