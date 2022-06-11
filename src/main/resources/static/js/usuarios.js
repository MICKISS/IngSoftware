// Call the dataTables jQuery plugin

$(document).ready(function () {
    cargarUsuarios();
    $('#usuarios').DataTable();
    actualizarusername();
});

function actualizarusername() {
    document.getElementById('txt-userName').outerHTML = localStorage.userName;

}


async function cargarUsuarios() {


    const request = await fetch('api/usuarios', {
        method: 'GET',
        headers: getHeaders()
    });
    const usuarios = await request.json();

    let listadoHtml = '';


    for (let usuario of usuarios) {

        let botonEliminar = '<a href="#"  onclick="eliminarUsuario(`' + usuario.userName + '`);registrarAuditoriaEliminar(`' + usuario.userName + '`)" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

        let botonActualizar =
            '<button type="button"  class="btn btn-danger btn-sm px-3" onclick="open_2(`' + usuario.userName + '`)">   <img src="../img/modificar.svg">  </button>';

        let telefonoTexto = usuario.telefono == null ? '-' : usuario.telefono;
        let usuarioHtml = '<tr>\n' +
            '        <td>' + usuario.userName + '</td>\n' +
            '        <td>' + usuario.nombres + '</td> \n' +
            '        <td>' + usuario.apellidos + '</td>\n' +
            '        <td>' + usuario.tipoDocumento + '</td>\n' +
            '        <td>' + usuario.noDocumento + '</td>\n' +
            '        <td>' + usuario.direccion + '</td> \n' +
            '        <td>' + telefonoTexto + '</td> \n' +
            '        <td>' + usuario.sexo + '</td> \n' +
            '        <td>' + usuario.rol + '</td> \n' +
            '        <td>' + usuario.email + '</td> \n' +
            '        <td>' + usuario.estado + '</td>\n' +
            '        <td>' + botonEliminar + '</td>\n' +
            '        <td>' + botonActualizar + '</td>\n' +

            '    </tr>';
        listadoHtml += usuarioHtml;
    }

    console.log(usuarios);

    document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
}

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        // 'Authorization':localStorage.token
    };
}

async function eliminarUsuario(username, rol) {

    if (!confirm("¿Desea eliminar este usuario?")) {
        return;
    }
    if (username === localStorage.userName) {
        alert("No puede eliminarse a si mismo")
        return;
    } else {
        const request = await fetch('api/usuarios/' + username, {

            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json',
                // 'Authorization':localStorage.token
            }
        });
    }


    location.reload();


}


async function registrarAuditoriaEliminar(usuario) {
    ip();

    let registro = "Elimino el usuario " + usuario;

    let datos = {};


    datos.usuario = localStorage.userName;
    datos.actividad = registro;
    datos.fecha = "";
    datos.ip = "";


    const request = await fetch('api/auditoria', {

        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            // 'Authorization':localStorage.token
        },
        body: JSON.stringify(datos)

    });


}




