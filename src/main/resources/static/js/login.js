let contador = 0;

// Call the dataTables jQuery plugin
$(document).ready(function () {

});


async function iniciarSesion() {

    let datos = {};

    datos.userName = document.getElementById('txtUserName').value;
    datos.password = document.getElementById('txtPassword').value;


    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        //Con este metodo se convierte cualquier string en json
        body: JSON.stringify(datos)
    });
    const response = await request.text();
    const requests = await fetch('api/validarusuario/' + datos.userName, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

    });

    const responses = await requests.text();

    const ingreso = await fetch('api/validarestado/' + datos.userName, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

    });

    const ingresos = await ingreso.text();


    const caducidad = await fetch('api/validarcaducidad/' + datos.userName, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

    });

    const caducidads = await caducidad.text();

    const roll = await fetch('api/rol/' + datos.userName, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

    });
    const rolls = await roll.text();


    if (responses === "true") {
        if (ingresos === "Activo") {
            if (caducidads === "disponible") {
                if (contador < 2) {
                    if (response == "OK") {
                        localStorage.userName = datos.userName;

                        registrarAuditoriaAcceso(datos.userName, 'ingresar')
                        rol(datos.userName);

                        contador = 0;
                    } else if (rolls === "Administrador") {

                        alert("Las credenciales son incorrectas. Por favor intente nuevamente");
                    } else {
                        contador += 1;
                        alert("Las credenciales son incorrectas. Por favor intente nuevamente");
                    }


                } else {
                    alert("Cuenta bloqueada, comuniquese con el administrador")
                    bloquear(datos.userName);
                    contador = 0;

                }
            } else if (caducidads === "cadujo") {
                if (contador < 3) {
                    if (response == "OK") {
                        localStorage.userName = datos.userName;

                        window.location.href = "forgot-password.html"
                    } else if (rolls === "Administrador") {

                        alert("Las credenciales son incorrectas. Por favor intente nuevamente");
                    }else {
                        contador += 1;
                        alert("Las credenciales son incorrectas. Por favor intente nuevamente");
                    }
                }
                else {
                    alert("Cuenta bloqueada, comuniquese con el administrador")
                    bloquear(datos.userName);
                    contador = 0;
                }


            }
        }
        if (ingresos === "Bloqueado") {
            alert("Cuenta bloqueada, comuniquese con el administrador");
        }

        if (ingresos === "Inactivo") {
            alert("Las credenciales son incorrectas. Por favor intente nuevamente");
        }
    } else {
        alert("Las credenciales son incorrectas. Por favor intente nuevamente");
    }


}

async function registrarAuditoriaAcceso(usuario, evento) {
    let registro = "";
    if (evento === 'ingresar') {
        registro = "Ingreso el usuario " + usuario;
    }
    if (evento === 'salir') {
        registro = "Salio el usuario " + usuario;
    }


    let datos = {};

    ip();
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

async function rol(username) {


    const request = await fetch('api/rol/' + username, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

    });
    const response = await request.text();


    if (response === "Administrador") {


        window.location.href = "usuarios.html"
    }

    if (response === "Empleado") {

        window.location.href = "empleado.html"
    }
    return response
}


async function bloquear(username) {
    const request = await fetch('api/bloquear/' + username, {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

    });
}

async function validarIngreso(username) {

}
