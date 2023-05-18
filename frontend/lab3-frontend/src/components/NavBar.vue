<script setup>
import { RouterLink } from 'vue-router'
</script>

<template>
    <div class="navbar">
        <router-link class="home-link" to="/">Početna stranica</router-link>
        <router-link class="home-link" to="/board-games">Društvene igre</router-link>
        <router-link class="home-link" to="/sifrarnici">Šifrarnici</router-link>
    </div>
</template>

<style scoped>
.home-link {
    margin: 10px;
    text-decoration: none;
    color: black;
    background-color: rgb(197, 197, 225);
    padding: 5px;
    border-radius: 5px;
    font-weight: bold;
}

.navbar {
    margin-top: 1em;
}
</style>