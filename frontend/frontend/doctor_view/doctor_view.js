import {
    getParameterByName,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayDoctor();
});

function fetchAndDisplayDoctor() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayDoctor(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/specializations/' + getParameterByName('specialization')
        + '/doctors/' + getParameterByName('doctor'), true);
    xhttp.send();
}

function displayDoctor(doctor) {
    setTextNode('name', doctor.name);
    setTextNode('surname', doctor.surname);
    setTextNode('age', doctor.age);
    setTextNode('specialization', doctor.specialization);
}
