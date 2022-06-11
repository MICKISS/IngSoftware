async function validarcaducidad(username) {
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
        alert("Registro exitoso");
        registrarUsuario();
        ip();
    } else {
        alert("Ya se encuentra un usuario con el mismo username");

    }

}

async function cambiarCaducidad(){
alert("ENTRO")
    password = document.getElementById('txt_renovar_password').value;

    const requests = await fetch('api/caducidad/' +  localStorage.userName+"/"+password, {
        method: 'PUT',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },

        //Con este metodo se convierte cualquier string en json
    });


}

async function registrarCaducidad(username) {
    let dias = 5;
    alert("entro")
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