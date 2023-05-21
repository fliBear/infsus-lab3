<script setup>
import { ref, computed, onMounted, watch } from 'vue';
import User from "../models/User";
import { useBoardGameStore } from "../stores/boardGameStore";
import { useRoute } from 'vue-router'

let bgStore = useBoardGameStore();

let route = useRoute();

//Refs for changing a specific advertisement
let selectedAd = ref();
let boardGame = ref();
let price = ref(); 
let condition = ref();
let city = ref();
let expiryDate = ref();

let ads = ref();
let cities = ref();

//Refs for adding new advertisement
let creatingNew = ref(false);

function editAd(id) {
    if(selectedAd.value === Number(id)) {
        let adData = ads.value.find((el) => el.id === selectedAd.value);
        let bgId = adData.boardGame.id;
        let userId = adData.user.id;
        let cityId = cities.value.find((el) => el.name === city.value).id;
        User.editAd(selectedAd.value, price.value, condition.value, new Date(expiryDate.value).toISOString(), null, userId, bgId, cityId);
        selectedAd.value = -1;
        setTimeout(async () => {
            await loadData();
        }, 500);
    } else {
        selectedAd.value = Number(id);
        let adData = ads.value.find((el) => el.id === selectedAd.value);
        creatingNew.value = false;
        price.value = adData.price;
        condition.value = adData.condition;
        city.value = adData.city.name;
        expiryDate.value = adData.expiryDate.split("T")[0];
    }
}

function deleteAd(id) {
    User.removeAd(id);
    setTimeout(async () => {
        await loadData();
    }, 500);
}

function preventReload() {}

function showEdit(id) {
    return Number(selectedAd.value) === Number(id)
}

function editText(id) {
    return selectedAd.value === Number(id) ? "Spremi" : "Uredi";
}

function cancelEdit() {
    selectedAd.value = null;
    boardGame.value = null;
    price.value = null;
    condition.value = null;
    city.value = null;
}

function cancelCreate() {
    creatingNew.value = false;
    boardGame.value = null;
    price.value = null;
    condition.value = null;
    city.value = null;
}

async function createNew() {
    if(creatingNew.value) {
        let adData = ads.value[0];
        let bgId = bgStore.id;
        if (bgId === undefined) {
            let boardGames = await User.loadBoardGames();
            let boardGame = chooseBoardGame(boardGames);
            bgId = boardGame.id;
        }
        let users = await User.loadUsers();
        if(users.length === 0) return;
        let userId = users[0].id;
        let cityId = cities.value.find((el) => el.name === city.value).id;
        User.createAd(price.value, condition.value, new Date(expiryDate.value).toISOString(), null, userId, bgId, cityId);
        setTimeout(async () => {
            await loadData();
        }, 500);
    } else {
        selectedAd.value = null;
        boardGame.value = null;
        price.value = null;
        condition.value = null;
        city.value = cities.value[0].name;
    }
    creatingNew.value = !creatingNew.value
}

function chooseBoardGame(boardGames) {
    return boardGames[Number(route.params.id) - 1];
}

async function loadData() {
    let loadedData = await User.loadAdvertisements();
    let bgId = bgStore.id;
    if(bgId === undefined) {
        let boardGames = await User.loadBoardGames();
        let boardGame = chooseBoardGame(boardGames);
        bgId = boardGame.id;
    }
    ads.value = loadedData.filter((el) => Number(el.boardGame.id) === Number(bgId));
    cities.value = await User.loadCities();
    city.value = cities.value[0].name;
}

onMounted(async() => {
    await loadData();
})

let checkPrice = computed(() => {
    return price.value && price.value >= 0;
})

let checkCondition = computed(() => {
    return condition.value;
})

function checkEditing (id) {
    return id === selectedAd.value;
}

watch(route, async (to, from) => {
    loadData();
})
</script>

<template>
    <form class="details-element-create" @submit.prevent="preventReload" v-if="creatingNew">
        <h3>Dodavanje novog oglasa</h3>
        <div class="input-container">
            <label for="expiryDate" class="details-label">Istek oglasa:</label>
            <input type="date" id="expiryDate" class="details-input details-select" v-model="expiryDate">
        </div>

        <div class="input-container">
            <label for="price" class="details-label">Cijena:</label>
            <input id="price" type="number" class="details-input" v-model="price">
        </div>

        <div class="input-container">
            <label for="condition" class="details-label">Stanje:</label>
            <input id="condition" type="text" class="details-input" v-model="condition">
        </div>

        <div class="input-container">
            <label for="city" class="details-label">Grad:</label>
            <select id="city" class="details-input details-select" v-model="city">
                <option v-for="c in cities" :key="c.ic" :value="c.name">{{ c.name }}</option>
            </select>
        </div>
    </form>
    <button class="button create-button" @click="createNew" :disabled="creatingNew && (!checkCondition || !checkPrice)" >Stvori novi</button>
    <button class="button create-button" @click="cancelCreate" v-if="creatingNew">Odustani</button>
    <div class="details-list-container">
        <ul class="details-list">
            <li class="details-element">
                <div>
                    <span class="details-element-description details-element-data">Društevna igra </span>
                    <span class="details-element-description details-element-data">Cijena </span>
                    <span class="details-element-description details-element-data">Stanje </span>
                    <span class="details-element-description details-element-data">Grad </span>
                    <span class="details-element-description details-element-data">Istek oglasa </span>
                </div>
            </li>
            <li v-for="ad in ads" :key="ad.id" class="details-element">
                <div class="details-element-container">
                    <div>
                        <span class="details-element-data">
                            {{ ad.boardGame.name }}
                        </span>
                        <span class="details-element-data">
                            {{ ad.price }}
                        </span>
                        <span class="details-element-data">
                            {{ ad.condition }}
                        </span>
                        <span class="details-element-data">
                            {{ ad.city.name }}
                        </span>
                        <span class="details-element-data">
                            {{ new Date(ad.expiryDate).toLocaleDateString() }}
                        </span>
                    </div>
                    <div class="details-element-actions">
                        <button type="button" class="edit-button button" @click="editAd(ad.id)" :disabled="checkEditing(ad.id) && (!checkCondition || !checkPrice)">
                            {{editText(ad.id)}}
                        </button>
                        <button type="button" class="delete-button button" @click="deleteAd(ad.id)">Obriši</button>
                        <button type="button" class="edit-button button" @click="cancelEdit" v-if="showEdit(ad.id)">Odustani</button>
                    </div>
                </div>
                <form class="details-element-edit" @submit.prevent="preventReload" v-if="showEdit(ad.id)">
                    <div class="input-container">
                        <label for="expiryDate" class="details-label">Istek oglasa:</label>
                        <input type="date" id="expiryDate" class="details-input details-select" v-model="expiryDate">
                    </div>

                    <div class="input-container">
                        <label for="price" class="details-label">Cijena:</label>
                        <input id="price" type="number" class="details-input" v-model="price">
                    </div>

                    <div class="input-container">
                        <label for="condition" class="details-label">Stanje:</label>
                        <input id="condition" type="text" class="details-input" v-model="condition">
                    </div>

                    <div class="input-container">
                        <label for="city" class="details-label">Grad:</label>
                        <select id="city" class="details-input details-select" v-model="city">
                            <option v-for="c in cities" :key="c.id" :value="c.name">{{ c.name }}</option>
                        </select>
                    </div>
                </form>
            </li>
        </ul>
    </div>
</template>

<style scoped>

.details-list-container {
    margin-top: 1em;
    margin-left: 1em;
}

.details-list {
    list-style: none;
    margin: 0;
    padding: 0;
}

.details-list:last-child {
    border-bottom: 2px solid rgb(99, 99, 207);
}

.details-element {
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

.details-element-data {
    display: inline-block;
    height: fit-content;
    margin-right: 3em;
    width: 10em;
    word-wrap: break-word;
}

.details-element-description {
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

.create-button {
    margin-top: 1em;
    margin-left: 1em;
    font-weight: bold;
    background-color: rgb(132, 132, 245);
}

.delete-button {
    font-weight: bold;
    background-color: rgb(237, 67, 67);
    margin-right: 1em;
}

.details-element-edit {
    margin-top: 1em;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}

.details-element-create {
    margin-top: 1em;
    margin-left: 1em;
    display: flex;
    flex-direction: column;
    justify-content: space-between;
}

.input-container {
    display: flex;
    flex-direction: column;
}

.details-label {
    font-weight: bold;
}

.details-input {
    height: 1.2em;
    width: 10em;
    margin-top: 5px;
}

.details-select {
    height: 2em;
}

</style>