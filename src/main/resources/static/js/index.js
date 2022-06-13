const dropArea = document.querySelector(".drop-area");
const dragText = dropArea.querySelector("h2");
const button = dropArea.querySelector("button");
const input = dropArea.querySelector("#input-file");
let files;

button.addEventListener("click", (e) => {
    input.click();
});


input.addEventListener('change', (e) => {

    files = input.files;
    dropArea.classList.add("active");
    showFiles(files);
    dropArea.classList.remove("active");
});
dropArea.addEventListener("dragover", (e) => {
    e.preventDefault();
    dropArea.classList.add("active");
    dragText.textContent = "Suelta para subir los archivos";
});
dropArea.addEventListener("dragleave", (e) => {
    e.preventDefault();
    dropArea.classList.remove("active");
    dragText.textContent = "Arrastra y suelta documentos";
});

dropArea.addEventListener("drop", (e) => {
    e.preventDefault();
    files = e.dataTransfer.files;
    showFiles(files);
    dropArea.classList.remove("active");
    dragText.textContent = "Arrastra y suelta documentos";
});

function showFiles(files) {
    if (files.length === undefined) {
        processFile(files);
    } else {
        for (const file of files) {
            {
                processFile(file);
            }
        }
    }
}

function processFile(file) {
    const docType = file.type;
    const validExtensions = ['application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'];

    if (validExtensions.includes(docType)) {
        const fileReader = new FileReader();
        const id = `file-${Math.random().toString(32).substring(7)}`;
        fileReader.addEventListener('load', (e) => {
            const fileUrl = fileReader.result;
            const image = `<div id="${id}" class="file-container">
                              <img src="https://i.blogs.es/9de470/excel/840_560.jpg" alt="${file.name}" width="50px">
                              <div class="status">
                                 <span>${file.name}</span>
                                 <span id ="status"class="status-text">
                                    Loading...
                                  </span>
                               </div>

                            </div>`;
            const html = document.querySelector("#preview").innerHTML;
            document.querySelector('#preview').innerHTML = image + html;
        });
        fileReader.readAsDataURL(file);
        uploadFile(file, id);
    } else {
        alert("No es un archivo valido");
    }
}

async function uploadFile(file,id) {
    const formData = new FormData();
    formData.append("file", file);

        const response = await fetch("api/excel/upload", {
            method: "POST",
            body: formData,
        });

        const responseText = await response.text();

         document.getElementById('status').outerHTML = responseText;



}