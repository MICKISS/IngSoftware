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
    if(response=="OK"){
        // localStorage.token=response;
        // localStorage.userName = datos.userName;
        window.location.href="usuarios.html"
    }else {
        alert("Las credenciales son incorrectas. Por favor intente nuevamente");
    }
}


