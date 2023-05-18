<script setup>
import { ref, onMounted } from 'vue'

let publishers = ["Prvi", "Drugi", "Treći"];

let name = ref();
let maxPlayers = ref();
let minPlayers = ref();
let age = ref();
let avgPlayTime = ref();
let publisher = ref(publishers[0]);
let langauge = ref(publishers[0]);
let genre = ref(publishers[0]);

function preventReload(){}

function save() {
    console.log(maxPlayers.value);
    console.log(name.value);
    console.log(minPlayers.value);
    console.log(age.value);
    console.log(avgPlayTime.value);
    console.log(publisher.value);
    console.log(langauge.value);
    console.log(genre.value);
}

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
                <input id="max-players" type="number" class="master-input" v-model="maxPlayers">
            </div>

            <div class="input-container">
                <label for="min-players" class="master-label"> Minimalni broj igrača:</label>
                <input id="min-players" type="number" class="master-input" v-model="minPlayers">
            </div>

           <div class="input-container">
                <label for="age" class="master-label">Preporučena dob:</label>
                <input id="age" type="number" class="master-input" v-model="age">
           </div>

            <div class="input-container">
                <label for="avg-play-time" class="master-label">Prosječno trajanje igre:</label>
                <input id="avg-play-time" type="number" class="master-input" v-model="avgPlayTime">
            </div>

            <div class="input-container">
                <label for="publishers" class="master-label">Izdavač:</label>
                <select id="publishers" class="master-input master-select" v-model="publisher">
                    <option v-for="p in publishers" :key="p" :value="p">{{ p }}</option>
                </select>
            </div>

            <div class="input-container">
                <label for="langauges" class="master-label">Jezik:</label>
                <select id="languages" class="master-input master-select" v-model="langauge">
                    <option v-for="p in publishers" :key="p" :value="p">{{ p }}</option>
                </select>
            </div>

            <div class="input-container">
                <label for="genres" class="master-label">Žanr:</label>
                <select id="genres" class="master-input master-select" v-model="genre">
                    <option v-for="p in publishers" :key="p" :value="p">{{ p }}</option>
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