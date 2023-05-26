import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));
    fetchAndDisplayCharacter();
});

function fetchAndDisplayCharacter() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/specializations/' + getParameterByName('specialization'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayCharacter();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/specializations/' + getParameterByName('specialization'), true);

    const request = {
        'name': document.getElementById('name').value,
        'years_of_preparation': document.getElementById('years_of_preparation').value,
        'type': document.getElementById('type').value,
        'difficulty_level': document.getElementById('difficulty_level').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}
