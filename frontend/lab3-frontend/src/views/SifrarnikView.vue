<script setup>
import { ref, computed, onMounted } from 'vue';
import User from "../models/User";

let search = ref("");

//Refs for changing a specific advertisement
let selectedSif = ref();
let sifrarnici = ref();
let username = ref();
let email = ref();
let phoneNumber = ref();
let age = ref();
let role = ref();
let roles = ref();
let city = ref();
let cities = ref();

//Refs for adding a new advertisemenet
let creatingNew = ref(false);

function filterSifrarnik(s) {
    if (search.value === "") return true;
    return s.username.toLowerCase().includes(search.value.toLowerCase());
}

const searchResult = computed(() => {
    if(!sifrarnici.value) return true;
    return sifrarnici.value.filter(filterSifrarnik);
}); 

function editAd(id) {
    if (selectedSif.value === Number(id)) {
        let userRole = roles.value.find((el) => role.value === el.description);
        let userCity = cities.value.find((el) => city.value === el.name);
        User.editUser(selectedSif.value, username.value, email.value, phoneNumber.value, age.value, userRole, userCity)
        selectedSif.value = -1;
        setTimeout(async () => {
            loadData();
        }, 100);
    } else {
        selectedSif.value = Number(id);
        creatingNew.value = null;
        username.value = null;
        email.value = null;
        phoneNumber.value = null;
        age.value = null;
        role.value = sifrarnici.value.find((el) => Number(el.id) === selectedSif.value).role.description;
        city.value = sifrarnici.value.find((el) => Number(el.id) === selectedSif.value).city.name;
    }
}

async function deleteUser(id) {
    User.deleteUser(id);
    setTimeout(async () => {
        loadData();
    }, 100);
}

function preventReload() { }

function showEdit(id) {
    return Number(selectedSif.value) === Number(id)
}

function editText(id) {
    return selectedSif.value === Number(id) ? "Spremi" : "Uredi";
}

function cancelEdit() {
    selectedSif.value = null;
    username.value = null;
    email.value = null;
    phoneNumber.value = null;
    age.value = null;
}

function cancelCreate() {
    creatingNew.value = false;
    username.value = null;
    email.value = null;
    phoneNumber.value = null;
    age.value = null;
}

function createNew() {
    if (creatingNew.value) {
        let userRole = roles.value.find((el) => role.value === el.description);
        let userCity = cities.value.find((el) => city.value === el.name);
        User.createUser(username.value, email.value, phoneNumber.value, age.value, userRole, userCity);
        setTimeout(async () => {
            loadData();
        }, 100);
    } else {
        selectedSif.value = null;
        username.value = null;
        email.value=null;
        phoneNumber.value = null;
        age.value = null;
        role.value = roles.value[0].description;
        city.value = cities.value[0].name;
    }
    creatingNew.value = !creatingNew.value
}

async function loadData() {
    sifrarnici.value = await User.loadUsers();
    roles.value = await User.loadRoles();
    role.value = roles.value[0].description;
    cities.value = await User.loadCities();
    city.value = cities.value[0].name;
}

onMounted(async () => {
    await loadData();
})

</script>

<template>
    <div class="sifrarnik-container">
        <h2>Šifrarnici</h2>
        <input class="search-bar" v-model="search" placeholder="Filtriranje po korisničkom imenu">
        <form class="sifrarnik-element-create" @submit.prevent="preventReload" v-if="creatingNew">
            <h3>Dodavanje novog šifrarnika</h3>
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
            <div class="input-container">
                <label for="role" class="sifrarnik-label">Uloga:</label>
                <select id="role" class="sifrarnik-input sifrarnik-select" v-model="role">
                    <option v-for="r in roles" :key="r.id" :value="r.description">{{ r.description }}</option>
                </select>
            </div>
            <div class="input-container">
                <label for="city" class="sifrarnik-label">Grad:</label>
                <select id="city" class="sifrarnik-input sifrarnik-select" v-model="city">
                    <option v-for="c in cities" :key="c.id" :value="c.name">{{ c.name }}</option>
                </select>
            </div>
        </form>
        <div>
            <button class="button create-button" @click="createNew">Stvori novi</button>
            <button class="button create-button" @click="cancelCreate" v-if="creatingNew">Odustani</button>
        </div>
        <div class="sifrarnici-list-container">
            <ul class="sifrarnici-list">
                <li class="sifrarnik-element">
                    <div>
                        <span class="sifrarnik-element-description sifrarnik-element-data">Korisničko ime </span>
                        <span class="sifrarnik-element-description sifrarnik-element-data">Email </span>
                        <span class="sifrarnik-element-description sifrarnik-element-data">Broj telefona </span>
                        <span class="sifrarnik-element-description sifrarnik-element-data">Dob </span>
                        <span class="sifrarnik-element-description sifrarnik-element-data">Uloga </span>
                        <span class="sifrarnik-element-description sifrarnik-element-data">Grad </span>
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
                            <span class="sifrarnik-element-data">
                                {{ s.role.description }}
                            </span>
                            <span class="sifrarnik-element-data">
                                {{ s.city.name }}
                            </span>
                        </div>
                        <div class="details-element-actions">
                            <button type="button" class="edit-button button" @click="editAd(s.id)">
                                    {{ editText(s.id) }}
                            </button>
                            <button type="button" class="delete-button button" @click="deleteUser(s.id)">
                                Obriši
                            </button>
                            <button type="button" class="edit-button button" @click="cancelEdit()" v-if="showEdit(s.id)">
                                Odustani
                            </button>
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
                        <div class="input-container">
                            <label for="role" class="sifrarnik-label">Uloga:</label>
                            <select id="role" class="sifrarnik-input sifrarnik-select" v-model="role">
                                <option v-for="r in roles" :key="r.id" :value="r.description">{{ r.description }}</option>
                            </select>
                        </div>
                        <div class="input-container">
                            <label for="city" class="sifrarnik-label">Grad:</label>
                            <select id="city" class="sifrarnik-input sifrarnik-select" v-model="city">
                                <option v-for="c in cities" :key="c.id" :value="c.name">{{ c.name }}</option>
                            </select>
                        </div>
                    </form>
                </li>
            </ul>
        </div>
    </div>
</template>

<style scoped>

.sifrarnik-container {
    display: flex;
    flex-direction: column;
}

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
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
}

.edit-button {
    font-weight: bold;
    background-color: rgb(132, 132, 245);
    margin-right: 1em;
}

.delete-button {
    font-weight: bold;
    background-color: rgb(237, 67, 67);
    margin-right: 1em;
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
    height: 17em;
}

.sifrarnik-input {
    height: 1.2em;
    width: 10em;
    margin-top: 5px;
}

.sifrarnik-label {
    font-weight: bold;
}

.sifrarnik-element-create {
    margin-top: 1em;
    margin-left: 1em;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}

.create-button {
    margin-top: 1em;
    margin-left: 1em;
    font-weight: bold;
    background-color: rgb(132, 132, 245);
    width: fit-content;
}

.sifrarnik-select {
    height: 2em;
}

</style>
