import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const newDoc = document.getElementById('newDoc')
    newDoc.addEventListener('submit', event => createDoctor(event));
});


function createDoctor(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();

    xhttp.open("POST", getBackendUrl() + '/api/specializations/' + getParameterByName('specialization')
        + '/doctors', true);

    const request = {
        'name': document.getElementById("name").value,
        'surname': document.getElementById("surname").value,
        'age': document.getElementById("age").value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}