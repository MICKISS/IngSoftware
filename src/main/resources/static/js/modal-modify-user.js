
$(document).ready(function () {

});

let usernameGlobal='';

const modal_container2 = document.getElementById('modal-modify-container');
const close2 = document.getElementById('close-modify');
const okK = document.getElementById('button-modify-owner-pet' );

/**
 * METHOD TO OPEN MODAL
 */
function open_2(username) {
    usernameGlobal=username;

    modal_container2.classList.add('show-modify-owner-pet');
    cargarTexto(usernameGlobal);

}



/**
 * Method to close modal
 */
close2.addEventListener('click', () => {
    modal_container2.classList.remove('show-modify-owner-pet');
    // location.reload();
});
/**
 * Field validation
 */
okK.addEventListener('click', async () => {


    let nombres = document.getElementById('txtNombres_modify').value;
    let apellidos = document.getElementById('txtApellidos_modify').value;
    let tipoDocumento = document.getElementById('txtTipoDocumento_modify').value;
    let noDocumento = document.getElementById('txtNoDocumento_modify').value;
    let sexo = document.getElementById('txtSexo_modify').value;
    let direccion = document.getElementById('txtDireccion_modify').value;
    let telefono = document.getElementById('txtTelefono_modify').value;
    let rol = document.getElementById('txtRol_modify').value;
    let email = document.getElementById('txtEmail_modify').value;
    let password = document.getElementById('txtContraseña_modify').value;
    let repetirPassword = document.getElementById('txtRepetirPassword_modify').value;


    if (nombres !== "" && apellidos !== "" && tipoDocumento !== "Please select..." && noDocumento !== ""
        && sexo !== "Please select..."
        && direccion !== ""
        && telefono !== ""
        && rol !== "Please select..."
        && email !== ""
        && password !== ""
        &&repetirPassword !== ""
    ) {
        modificarUsuario();

    } else {

        alert("Complete los espacios en blanco");
    }
});

/**
 * methodToRegisterUser
 */
async function modificarUsuario() {
    let datos = {};
    datos.userName = usernameGlobal;
    datos.nombres = document.getElementById('txtNombres_modify').value;
    datos.apellidos = document.getElementById('txtApellidos_modify').value;
    datos.tipoDocumento = document.getElementById('txtTipoDocumento_modify').value;
    datos.noDocumento = document.getElementById('txtNoDocumento_modify').value;
    datos.sexo = document.getElementById('txtSexo_modify').value;
    datos.direccion = document.getElementById('txtDireccion_modify').value;
    datos.telefono = document.getElementById('txtTelefono_modify').value;
    datos.rol = document.getElementById('txtRol_modify').value;
    datos.email = document.getElementById('txtEmail_modify').value;
    datos.password = document.getElementById('txtContraseña_modify').value;

    let repetirPassword = document.getElementById('txtRepetirPassword_modify').value;


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

    alert("Actualización exitoso");
    registrarAuditoriaModificar(datos.userName);
    // window.location.href='login.html';

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


 async function registrarAuditoriaModificar(usuario) {

     let registro = "Modifico el usuario " + usuario;

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
async function cargarTexto(username) {



    const request = await fetch('api/usuarios/' + username, {
        method: 'PUT',
        headers: getHeaders()
    });
    const usuarios = await request.json();


    let nombres = document.getElementById('txtNombres_modify');
    let apellidos = document.getElementById('txtApellidos_modify');
    let tipoDocumento = document.getElementById('txtTipoDocumento_modify');
    let noDocumento = document.getElementById('txtNoDocumento_modify');
    let sexo = document.getElementById('txtSexo_modify');
    let direccion = document.getElementById('txtDireccion_modify');
    let telefono = document.getElementById('txtTelefono_modify');
    let rol = document.getElementById('txtRol_modify');
    let email = document.getElementById('txtEmail_modify');
    // let contraseña = document.getElementById('txtContraseña_modify');
    // let repetirContraseña = document.getElementById('txtRepetirPassword_modify');


    nombres.value = usuarios.nombres;
    apellidos.value = usuarios.apellidos;
    tipoDocumento.value = usuarios.tipoDocumento;
    noDocumento.value = usuarios.noDocumento;
    sexo.value = usuarios.sexo;
    direccion.value = usuarios.direccion;
    telefono.value = usuarios.telefono;
    rol.value = usuarios.rol;
    email.value = usuarios.email;
    // contraseña.value = usuarios.password;
    // repetirContraseña.value=usuarios.password;


    console.log(usuarios);

}





