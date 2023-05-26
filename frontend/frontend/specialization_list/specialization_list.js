import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplaySpecializations();
});

function fetchAndDisplaySpecializations() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displaySpecializations(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/specializations', true);
    xhttp.send();
}

function displaySpecializations(specializations) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    specializations.specializations.forEach(specialization => {
        tableBody.appendChild(createTableRow(specialization.name));
    })
}

function createTableRow(specialization) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(specialization));
    tr.appendChild(createLinkCell('view', '../specialization_view/specialization_view.html?specialization='
        + specialization));
    tr.appendChild(createLinkCell('edit', '../specialization_edit/specialization_edit.html?specialization='
        + specialization));
    tr.appendChild(createButtonCell('delete', () => deleteSpecialization(specialization)));
    return tr;
}

function deleteSpecialization(specialization) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplaySpecializations();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/specializations/' + specialization, true);
    xhttp.send();
}