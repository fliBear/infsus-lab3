<script setup>
import { ref, computed } from 'vue';

//Refs for changing a specific advertisement
let selectedAd = ref();
let boardGame = ref();
let price = ref();
let condition = ref();
let city = ref();

//Refs for adding new advertisement
let creatingNew = ref(false);

let ads = [
    {"advertisement_id":1, "price":2, "condition":"Dobro", "city":{"city_id":7, "name":"Zagreb"}, "boardGame":{"name":"Catan"}},
    { "advertisement_id": 2, "price": 21, "condition": "Dobro", "city": { "city_id": 8, "name": "Osijek" }, "boardGame": { "name": "Catan" } }
]

function editAd(id) {
    if(selectedAd.value === Number(id)) {
        selectedAd.value = -1;
    } else {
        selectedAd.value = Number(id);
        creatingNew.value = false;
        boardGame.value = null;
        price.value = null;
        condition.value = null;
        city.value = null;
    }
}

function deleteAd(id) {

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

function createNew() {
    if(creatingNew.value) {
        //axios dio
    } else {
        selectedAd.value = null;
        boardGame.value = null;
        price.value = null;
        condition.value = null;
        city.value = null;
    }
    creatingNew.value = !creatingNew.value
}

</script>

<template>
    <form class="details-element-create" @submit.prevent="preventReload" v-if="creatingNew">
        <h3>Dodavanje novog oglasa</h3>
        <div class="input-container">
            <label for="boardGame" class="details-label">Društvena igra:</label>
            <select id="boardGame" class="details-input details-select" v-model="boardGame">
                <option v-for="bg in boardGames" :key="bg.id" :value="bg">{{ bg.name }}</option>
            </select>
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
                <option v-for="c in cities" :key="c" :value="c.id">{{ c.name }}</option>
            </select>
        </div>
    </form>
    <button class="button create-button" @click="createNew">Stvori novi</button>
    <button class="button create-button" @click="cancelCreate" v-if="creatingNew">Odustani</button>
    <div class="details-list-container">
        <ul class="details-list">
            <li class="details-element">
                <div>
                    <span class="details-element-description details-element-data">Društevna igra </span>
                    <span class="details-element-description details-element-data">Cijena </span>
                    <span class="details-element-description details-element-data">Stanje </span>
                    <span class="details-element-description details-element-data">Grad </span>
                </div>
            </li>
            <li v-for="ad in ads" :key="ad.advertisement_id" class="details-element">
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
                    </div>
                    <div class="details-element-actions">
                        <button type="button" class="edit-button button" @click="editAd(ad.advertisement_id)">
                            {{editText(ad.advertisement_id)}}
                        </button>
                        <button type="button" class="delete-button button" @click="deleteAd(ad.advertisement_id)">Obriši</button>
                        <button type="button" class="edit-button button" @click="cancelEdit" v-if="showEdit(ad.advertisement_id)">Odustani</button>
                    </div>
                </div>
                <form class="details-element-edit" @submit.prevent="preventReload" v-if="showEdit(ad.advertisement_id)">
                    <div class="input-container">
                        <label for="boardGame" class="details-label">Društvena igra:</label>
                        <select id="boardGame" class="details-input details-select" v-model="boardGame">
                            <option v-for="bg in boardGames" :key="bg.id" :value="bg">{{ bg.name }}</option>
                        </select>
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
                            <option v-for="c in cities" :key="c" :value="c.id">{{ c.name }}</option>
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