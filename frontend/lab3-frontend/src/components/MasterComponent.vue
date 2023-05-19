<script setup>
import { ref, onMounted } from 'vue'
import User from "./models/User";
import { useRoute } from 'vue-router'

let route = useRoute();

let publishers = ref([]);
let languages = ref([]);
let genres = ref();

let name = ref();
let noPlayersMax = ref();
let noPlayersMin = ref();
let age = ref();
let avgPlayingTime = ref();
let publisher = ref();
let language = ref();
let genre = ref();

function preventReload(){}

// function save() {
//     axios.post(calls.boardGame, {
//         "name": name.value,
//         "noPlayersMax": noPlayersMax.value,
//         "noPlayersMin": noPlayersMin.value,
//         "age": age.value,
//         "avgPlayingTime": avgPlayingTime.value,
//         "publisher": {"id": 1, "name": "nekoime"},
//         "language": {},
//         "genre": {}
//     })
// }

async function load() {
    let boardGame = await User.loadBoardGame(route.params.id);
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
    await load();
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
            <button class="save-button button" @click="save">Spremi</button>
            <button class="delete-button button">Obriši</button>
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