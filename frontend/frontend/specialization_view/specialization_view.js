import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplaySpecialization();
    fetchAndDisplayDoctors();
    displayCreateDoctor();
});

function fetchAndDisplayDoctors() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayDoctors(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/specializations/' + getParameterByName('specialization')
        + '/doctors', true);
    xhttp.send();
}

function displayDoctors(doctors) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    doctors.doctors.forEach(doctor => {
        tableBody.appendChild(createTableRow(doctor));
    });

}

function displayCreateDoctor() {
    let newDocBtn = document.getElementById('newDoc');
    newDocBtn.appendChild(createLinkCell('Create a new doctor', '../doctor_create/doctor_create.html?specialization='
        + getParameterByName('specialization')))
}

function createTableRow(doctor) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(doctor.id));
    tr.appendChild(createTextCell(doctor.name));
    tr.appendChild(createLinkCell('view', '../doctor_view/doctor_view.html?specialization='
        + getParameterByName('specialization') + '&doctor=' + doctor.id));
    tr.appendChild(createLinkCell('edit', '../doctor_edit/doctor_edit.html?specialization='
        + getParameterByName('specialization') + '&doctor=' + doctor.id));
    tr.appendChild(createButtonCell('delete', () => deleteDoctor(doctor.id)));
    return tr;
}

function deleteDoctor(doctor) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayDoctors();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/specializations/' + getParameterByName('specialization')
        + '/doctors/' + doctor, true);
    xhttp.send();
}

function fetchAndDisplaySpecialization() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySpecialization(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/specializations/' + getParameterByName('specialization'), true);
    xhttp.send();
}

function displaySpecialization(specialization) {
    setTextNode('specialization', specialization.name);
    setTextNode('years_of_preparation', specialization.years_of_preparation);
    setTextNode('type', specialization.type);
    setTextNode('difficulty_level', specialization.difficulty_level);
}