import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const newSpec = document.getElementById('newSpec')
    newSpec.addEventListener('submit', event => createSpecialization(event));
});


function createSpecialization(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();

    xhttp.open("POST", getBackendUrl() + '/api/specializations', true);

    const request = {
        'name': document.getElementById("name").value,
        'years_of_preparation': parseInt(document.getElementById("years").value),
        'type': document.getElementById("type").value,
        'difficulty_level': parseInt(document.getElementById("difficulty_level").value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}