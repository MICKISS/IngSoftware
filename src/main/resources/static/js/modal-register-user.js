const open = document.getElementById('modal-register-button');
const modal_container = document.getElementById('modal-register-container');
const close = document.getElementById('close-register');
const ok = document.getElementById('button-register-owner-pet');

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
    let vigencia = document.getElementById('txtVigencia').value;


    if (nombres !== "" && apellidos !== "" && tipoDocumento !== "Please select..." && noDocumento !== ""
        && sexo !== "Please select..."
        && direccion !== ""
        && telefono !== ""
        && rol !== "Please select..."
        && email !== ""
        && userName !== ""
        && password !== ""
        && repetirPassword !== ""
        && vigencia !== ""
    ) {

        longitudTelefono = telefono.toString().length;
        longitudNoDocumento = noDocumento.toString().length;
        var expReg = /^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/;
        var valido = expReg.test(email)
        regex = /^(?=.*\d)(?=.*[a-záéíóúüñ]).*[A-ZÁÉÍÓÚÜÑ]/;
        var validoPassword = regex.test(password);

        if (vigencia <= 30 && vigencia>0) {
            if (longitudTelefono < 11 && longitudTelefono > 6 && telefono > 0) {
                if (longitudNoDocumento < 11 && longitudNoDocumento >= 6 && noDocumento > 0) {
                    if (valido == true) {
                        if (validoPassword == true) {
                            validarusername(userName);
                            enviarEmail(userName, password, nombres, email);

                        } else {
                            alert("La contraseña debe tener al menos una mayúscula, una minúscula y un dígito");
                        }

                    } else {
                        alert("La dirección de email es incorrecta.");
                    }

                } else {
                    alert("Ingrese un numero de identificación valido");
                }

            } else {
                alert("Ingrese un numero de teléfono valido");
            }
        } else {
            alert("Ingrese un numero de vigencia menor o igual a 30 y mayor a 0");
        }


    } else {

        alert("Complete los espacios en blanco");
    }

});

/**
 * methodToRegisterUser
 */
async function registrarUsuario() {
    // let dias = 5;
    // var date = new Date();


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
    // datos.fechaInicial = date;
    // datos.fechaFinal = date;
    // datos.dias = dias;

    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        //Con este metodo se convierte cualquier string en json
        body: JSON.stringify(datos)
    });
    registrarAuditoria(datos.userName);


    location.reload();

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

async function validarusername(username) {

    let contraseña = document.getElementById('txtContraseña').value;
    let repetirContraseña = document.getElementById('txtRepetirPassword').value;
    let dias = document.getElementById('txtVigencia').value;
    const requests = await fetch('api/validarusuario/' + username, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        //Con este metodo se convierte cualquier string en json
    });
    const response = await requests.text();
    if (response === "false") {
        if (contraseña != repetirContraseña) {
            alert('La contraseña que escribiste es diferente');
            return;
        } else {
            registrarUsuario();
            registrarCaducidad(username, dias);
            ip();

            alert("Registro exitoso");
        }

    } else {
        alert("Ya se encuentra un usuario con el mismo username");

    }

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

async function registrarAuditoriaVisualizar(evento) {

    ip();

    let datos = {};

    datos.usuario = localStorage.userName;
    datos.actividad = "Visualizo la tabla de " + evento;
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

async function registrarCaducidad(username, dias) {

    var date = new Date();

    let datos = {};
    datos.userName = username;
    datos.fechaInicial = date;
    datos.fechaFinal = date;
    datos.dias = dias;

   const request = await fetch('api/caducidad', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        //Con este metodo se convierte cualquier string en json
        body: JSON.stringify(datos)
    });

}

async function cambiarPassword(username) {
    var date = new Date();
    let password = document.getElementById('txt_renovar_password').value;

    regex = /^(?=.*\d)(?=.*[a-záéíóúüñ]).*[A-ZÁÉÍÓÚÜÑ]/;
    var validoPassword = regex.test(password);


    if (validoPassword === true) {

        const requests = await fetch('api/cambiarcaducidad/'+username, {
            method: 'PUT',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
        });
        const response = await  requests.text();



        const request = await fetch('api/cambiapass/' + username + "/" + password, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },

        });
        registrarCaducidad(username, response);
        window.location.href = "login.html"
    }else {
        alert("La contraseña debe tener al menos una mayúscula, una minúscula y un dígito");
    }



}





