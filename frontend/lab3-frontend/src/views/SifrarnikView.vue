<script setup>
import { ref, computed } from 'vue';

let sifarniciList = [
    {"id":1,"username":"prvi","email":"prviemail","phoneNumber":"123","age":2},
    { "id": 2, "username": "drugi", "email": "drugiemail", "phoneNumber": "567", "age": 7 }
]

let search = ref("");
let selectedSif = ref();
let sifrarnici = ref(sifarniciList);
let username = ref();
let email = ref();
let phoneNumber = ref();
let age = ref();

function filterSifrarnik(s) {
    if (search.value === "") return true;
    return s.username.toLowerCase().includes(search.value.toLowerCase());
}

const searchResult = computed(() => {
    return sifrarnici.value.filter(filterSifrarnik);
}); 

function editAd(id) {
    if (selectedSif.value === Number(id)) {
        selectedSif.value = -1;
    } else {
        selectedSif.value = Number(id);
    }
}

function deleteAd(id) {
}

function preventReload() { }

function showEdit(id) {
    return Number(selectedSif.value) === Number(id)
}

function editText(id) {
    return selectedSif.value === Number(id) ? "Spremi" : "Uredi";
}

</script>

<template>
    <h2>Šifrarnici</h2>
    <input class="search-bar" v-model="search" placeholder="Filtriranje po korisničkom imenu">
    <div class="sifrarnici-list-container">
        <ul class="sifrarnici-list">
            <li class="sifrarnik-element">
                <div>
                    <span class="sifrarnik-element-description sifrarnik-element-data">Korisničko ime </span>
                    <span class="sifrarnik-element-description sifrarnik-element-data">Email </span>
                    <span class="sifrarnik-element-description sifrarnik-element-data">Broj telefona </span>
                    <span class="sifrarnik-element-description sifrarnik-element-data">Dob </span>
                </div>
            </li>
            <li v-for="s in searchResult" :key="s.id" class="sifrarnik-element">
                <div class="details-element-container">
                    <div>
                        <span class="sifrarnik-element-data">
                            {{ s.username }}
                        </span>
                        <span class="sifrarnik-element-data">
                            {{ s.email }}
                        </span>
                        <span class="sifrarnik-element-data">
                            {{ s.phoneNumber }}
                        </span>
                        <span class="sifrarnik-element-data">
                            {{ s.age }}
                        </span>
                    </div>
                    <div class="details-element-actions">
                        <button type="button" class="edit-button button" @click="editAd(s.id)">
                                {{ editText() }}
                            </button>
                            <button type="button" class="delete-button button" @click="deleteAd()">Obriši</button>
                    </div>
                </div>
                <form class="sifrarnik-element-edit" @submit.prevent="preventReload" v-if="showEdit(s.id)">
                    <div class="input-container">
                        <label for="username" class="sifrarnik-label">Korisničko ime:</label>
                        <input id="username" type="text" class="sifrarnik-input" v-model="username">
                    </div>

                    <div class="input-container">
                        <label for="email" class="sifrarnik-label">Email:</label>
                        <input id="email" type="text" class="sifrarnik-input" v-model="email">
                    </div>

                    <div class="input-container">
                        <label for="phoneNumber" class="sifrarnik-label">Broj telefona:</label>
                        <input id="phoneNumber" type="text" class="sifrarnik-input" v-model="phoneNumber">
                    </div>

                    <div class="input-container">
                        <label for="age" class="sifrarnik-label">Dob:</label>
                        <input id="age" type="number" class="sifrarnik-input" v-model="age">
                    </div>
                </form>
            </li>
        </ul>
    </div>
</template>

<style scoped>

.search-bar {
    width: 30vw;
    margin-left: 5em;
}


.sifrarnici-list-container {
    margin-top: 1em;
    margin-left: 1em;
}

.sifrarnici-list {
    list-style: none;
    margin: 0;
    padding: 0;
}

.sifrarnici-list:last-child {
    border-bottom: 2px solid rgb(99, 99, 207);
}

.sifrarnik-element {
    padding-top: 1em;
    padding-bottom: 1em;
    padding-left: 10px;
    border-top: 2px solid rgb(99, 99, 207);
}

.details-element-container {
    display: flex;
    flex-direction: row;
    align-items: center;
}

.sifrarnik-element-data {
    display: inline-block;
    height: fit-content;
    margin-right: 3em;
    width: 10em;
    word-wrap: break-word;
}

.sifrarnik-element-description {
    font-weight: bold;
}

.details-element-actions {
    width: 10em;
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
}

.edit-button {
    font-weight: bold;
    background-color: rgb(132, 132, 245);
}

.delete-button {
    font-weight: bold;
    background-color: rgb(237, 67, 67);
}

.input-container {
    display: flex;
    flex-direction: column;
}

.sifrarnik-element-edit {
    margin-top: 1em;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    height: 13em;
}

.sifrarnik-input {
    height: 1.2em;
    width: 10em;
    margin-top: 5px;
}

.sifrarnik-label {
    font-weight: bold;
}


</style>
