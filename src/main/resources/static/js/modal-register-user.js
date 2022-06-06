const open = document.getElementById('modal-register-button');
const modal_container = document.getElementById('modal-register-container');
const close = document.getElementById('close-register');
const ok = document.getElementById('button-register-owner-pet');
$(document).ready(function () {


});
/**
 *  Method to open the modal
 */
open.addEventListener('click', () => {
    modal_container.classList.add('show-register-user');
});

/**
 *  Method to close the modal
 */
close.addEventListener('click', () => {
    modal_container.classList.remove('show-register-user');
    clearDataFrom();
});

/**
 * Field validation
 */
ok.addEventListener('click', async () => {

    let nombres = document.getElementById('txtNombres').value;
    let apellidos = document.getElementById('txtApellidos').value;
    let tipoDocumento = document.getElementById('txtTipoDocumento').value;
    let noDocumento = document.getElementById('txtNoDocumento').value;
    let sexo = document.getElementById('txtSexo').value;
    let direccion = document.getElementById('txtDireccion').value;
    let telefono = document.getElementById('txtTelefono').value;
    let rol = document.getElementById('txtRol').value;
    let email = document.getElementById('txtEmail').value;
    let userName = document.getElementById('txtUsername').value;
    let password = document.getElementById('txtContraseña').value;
    let repetirPassword = document.getElementById('txtRepetirPassword').value;


    if (nombres !== "" && apellidos !== "" && tipoDocumento !== "Please select..." && noDocumento !== ""
        && sexo !== "Please select..."
        && direccion !== ""
        && telefono !== ""
        && rol !== "Please select..."
        && email !== ""
        && userName !== ""
        && password !== ""
        && repetirPassword !== ""
    ) {

        // if (await validateName(userName) === "false") {


        registrarUsuario();
        // enviarEmail(userName, password, nombres, email);
        ip();


        //
        // } else {
        //
        //     alert("El username ya existe");
        // }
    } else {

        alert("Complete los espacios en blanco");
    }

});

/**
 * methodToRegisterUser
 */
async function registrarUsuario() {
    let datos = {};
    datos.userName = document.getElementById('txtUsername').value;

    datos.nombres = document.getElementById('txtNombres').value;
    datos.apellidos = document.getElementById('txtApellidos').value;
    datos.tipoDocumento = document.getElementById('txtTipoDocumento').value;
    datos.noDocumento = document.getElementById('txtNoDocumento').value;
    datos.sexo = document.getElementById('txtSexo').value;
    datos.direccion = document.getElementById('txtDireccion').value;
    datos.telefono = document.getElementById('txtTelefono').value;
    datos.rol = document.getElementById('txtRol').value;
    datos.email = document.getElementById('txtEmail').value;

    datos.password = document.getElementById('txtContraseña').value;

    let repetirPassword = document.getElementById('txtRepetirPassword').value;


    if (repetirPassword != datos.password) {
        alert('La contraseña que escribiste es diferente');
        return;
    }

    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        //Con este metodo se convierte cualquier string en json
        body: JSON.stringify(datos)
    });

    alert("Registro exitoso");
    registrarAuditoria(datos.userName);
    //location.reload();

}

/**
 * Method to clean field from
 */
function clearDataFrom() {
    let userName = document.getElementById('txtUsername').value;
    let nombres = document.getElementById('txtNombres').value;
    let apellidos = document.getElementById('txtApellidos').value;
    let tipoDocumento = document.getElementById('txtTipoDocumento').value;
    let noDocumento = document.getElementById('txtNoDocumento').value;
    let sexo = document.getElementById('txtSexo').value;
    let direccion = document.getElementById('txtDireccion').value;
    let rol = document.getElementById('txtRol').value;
    let email = document.getElementById('txtEmail').value;

    let contraseña = document.getElementById('txtContraseña').value;

    nombres.value = "";
    apellidos.value = "";
    tipoDocumento.value = "0";
    noDocumento.value = "";
    sexo.value = "0";
    direccion.value = "";
    rol.value = "0";
    email.value = "";
    userName.value = "";
    contraseña.value = "";

}

async function enviarEmail(username, password, nombres, mail) {


    const request = await fetch('api/sendmail/' + username + "/" + password + "/" + nombres + "/" + mail, {

        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            // 'Authorization':localStorage.token
        },

    });


}

async function ip() {

    const request = await fetch('api/ip', {

        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            // 'Authorization':localStorage.token
        },

    });


}

async function registrarAuditoria(usuario) {

    let registro = "Registro el usuario " + usuario;

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



