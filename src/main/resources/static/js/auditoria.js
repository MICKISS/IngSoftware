$(document).ready(function () {
    cargarAuditoria();
    $('#usuarios').DataTable();
    actualizarusername();
});

function actualizarusername() {
    document.getElementById('txt-userName').outerHTML = localStorage.userName;

}


async function cargarAuditoria() {

    const request = await fetch('api/auditoria', {
        method: 'GET',
        headers: getHeaders()
    });
    const historial = await request.json();

    let listadoHtml = '';
    let fecha="";


    for (let auditoria of historial) {

        var date = new Date(auditoria.fecha);
        const formatDate = (current_datetime)=>{
            let formatted_date = current_datetime.getFullYear() + "-" + (current_datetime.getMonth() + 1) + "-" + current_datetime.getDate() + " " + current_datetime.getHours() + ":" + current_datetime.getMinutes() + ":" + current_datetime.getSeconds();
            return formatted_date;
        }

        let auditoriaHtml = '<tr>\n' +
            '        <td>' + auditoria.id + '</td>\n' +
            '        <td>' + auditoria.usuario + '</td> \n' +
            '        <td>' + auditoria.actividad + '</td>\n' +
            '        <td>' + formatDate(date)+ '</td>\n' +
            '        <td>' + auditoria.ip + '</td>\n' +
            '    </tr>';
        listadoHtml += auditoriaHtml;
    }


    document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
}

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        // 'Authorization':localStorage.token
    };
}

