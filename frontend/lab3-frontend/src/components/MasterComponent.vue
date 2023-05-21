<script setup>
import { ref, onMounted, computed, watch } from 'vue'
import User from "../models/User";
import { useRoute, useRouter } from 'vue-router'
import {useBoardGameStore} from "../stores/boardGameStore";

let route = useRoute();
let router = useRouter();

let bgStore = useBoardGameStore();

let publishers = ref();
let languages = ref();
let genres = ref();

let id = ref();
let name = ref();
let noPlayersMax = ref();
let noPlayersMin = ref();
let age = ref();
let avgPlayingTime = ref();
let publisher = ref();
let language = ref();
let genre = ref();

function preventReload(){}

async function save() {
    let bgPublisher = publishers.value.find((el) => el.name === publisher.value);
    let bgLanguage = languages.value.find((el) => el.name === language.value);
    let bgGenre = genres.value.find((el) => el.name === genre.value);
    User.editBoardGame(id.value, name.value,
        noPlayersMax.value,
        noPlayersMin.value,
        age.value,
        avgPlayingTime.value,
        bgPublisher,
        bgLanguage,
        bgGenre)
    setTimeout(async () => {
        await loadData();
    }, 500);
}

async function deleteBoardGame() {
    await User.deleteBoardGame(id.value);
    router.push("/master-detail/" + (Number(route.params.id) + 1));
}

function chooseBoardGame(boardGames) {
    return boardGames[Number(route.params.id) - 1];
}

async function loadData() {
    let boardGames = await User.loadBoardGames();
    let boardGame = chooseBoardGame(boardGames);
    bgStore.setId(boardGame.id);
    id.value = boardGame.id;
    name.value = boardGame.name;
    noPlayersMax.value = boardGame.noPlayersMax;
    noPlayersMin.value = boardGame.noPlayersMin;
    age.value = boardGame.age;
    avgPlayingTime.value = boardGame.avgPlayingTime;

    languages.value = await User.loadLanguages();
    publishers.value = await User.loadPublishers();
    genres.value = await User.loadGenres();

    language.value = boardGame.language.name;
    publisher.value = boardGame.publisher.name;
    genre.value = boardGame.genre.name;
}

onMounted(async () => {
    let boardGames = await User.loadBoardGames();
    if(boardGames.length === 0) {
        router.push("/");
    }
    if (Number(route.params.id) > boardGames.length) {
        router.push("/master-detail/" + boardGames.length);
    }
    if (Number(route.params.id) < 1) {
        router.push("/master-detail/1");
    }
    await loadData();
})

let checkName = computed(() => {
    return name.value;
})

let checkAge = computed(() => {
    return age.value > 0;
})

let checkMax = computed(() => {
    return noPlayersMax.value >= 1 && noPlayersMin.value ? noPlayersMax.value >= noPlayersMin.value : noPlayersMax.value >= 1;
})

let checkMin = computed(() => {
    return noPlayersMin.value >= 1 && noPlayersMax.value ? noPlayersMax.value >= noPlayersMin.value : noPlayersMin.value >= 1;

})

let checkAvg = computed(() => {
    return avgPlayingTime.value > 0; 
})

watch(route, async (to, from) => {
    let boardGames = await User.loadBoardGames();
    let boardGame = chooseBoardGame(boardGames);
    bgStore.setId(boardGame.id);
    if(Number(to.params.id) > boardGames.length) {
        router.push("/master-detail/" + boardGames.length);
    }
    loadData();
})

</script>

<template>
    <form class="master-form" @submit.prevent="preventReload">
        <div class="master-inputs">
            <div class="input-container">
                <label for="name" class="master-label">Ime:</label>
                <input id="name" type="text" class="master-input" v-model="name">
            </div>

            <div class="input-container">
                <label for="max-players" class="master-label">Maksimalni broj igrača:</label>
                <input id="max-players" type="number" class="master-input" v-model="noPlayersMax">
            </div>

            <div class="input-container">
                <label for="min-players" class="master-label"> Minimalni broj igrača:</label>
                <input id="min-players" type="number" class="master-input" v-model="noPlayersMin">
            </div>

           <div class="input-container">
                <label for="age" class="master-label">Preporučena dob:</label>
                <input id="age" type="number" class="master-input" v-model="age">
           </div>

            <div class="input-container">
                <label for="avg-play-time" class="master-label">Prosječno trajanje igre:</label>
                <input id="avg-play-time" type="number" class="master-input" v-model="avgPlayingTime">
            </div>

            <div class="input-container">
                <label for="publishers" class="master-label">Izdavač:</label>
                <select id="publishers" class="master-input master-select" v-model="publisher">
                    <option v-for="p in publishers" :key="p.id" :value="p.name">{{ p.name }}</option>
                </select>
            </div>

            <div class="input-container">
                <label for="langauges" class="master-label">Jezik:</label>
                <select id="languages" class="master-input master-select" v-model="language">
                    <option v-for="l in languages" :key="l.id" :value="l.name">{{ l.name }}</option>
                </select>
            </div>

            <div class="input-container">
                <label for="genres" class="master-label">Žanr:</label>
                <select id="genres" class="master-input master-select" v-model="genre">
                    <option v-for="g in genres" :key="g.id" :value="g.name">{{ g.name }}</option>
                </select>
            </div>
        </div>
        <div class="master-actions">
            <button class="save-button button" @click="save" :disabled="!checkAge || !checkAvg || !checkMax || !checkMin || !checkName">Spremi</button>
            <button class="delete-button button" @click="deleteBoardGame">Obriši</button>
        </div>
    </form>

</template>

<style scoped>

.master-form {
    display: flex;
    flex-direction: column;
}

.master-inputs {
    display: flex;
    flex-wrap: wrap;
    width: 60vw;
}

.master-input {
    height: 1.2em;
    width: 10em;
    margin-top: 5px;
}

.input-container {
    display: flex;
    flex-direction: column;
    flex-basis: 30%;
    margin-top: 1em;
}

.master-select {
    height: 2em;
}

.master-label {
    font-weight: bold;
}

.master-actions {
    margin: 0.5em;
    margin-top: 2em;
    width: 10em;
    display: flex;
    justify-content: space-around;
}

.save-button {
    background-color: rgb(34, 198, 34);
    font-weight: bold;
}

.delete-button {
    background-color: rgb(237, 67, 67);
    font-weight: bold;
}

</style>