<script setup>
import MasterComponent from "../components/MasterComponent.vue";
import DetailsComponent from "../components/DetailsComponent.vue";
import { useRoute } from 'vue-router'
import { ref, onMounted } from 'vue'
import { RouterLink } from 'vue-router'

let route = useRoute();
let param = ref(0);

let previousId = Number(route.params.id) === 1 ? 1 : Number(route.params.id) - 1;
let previous = ref("/master-detail/" + previousId);
let nextId = Number(route.params.id) + 1;
let next = ref("/master-detail/" + nextId)

onMounted(() => {
    param.value = route.params.id
})

</script>

<template>
    <div class="master-detail-container">
        <div class="master-detail-nav">
            <RouterLink class="master-detail-link" :to="previous">Prethodni</RouterLink>
            <RouterLink class="master-detail-link" :to="next">Sljedeći</RouterLink>
        </div>
        <MasterComponent></MasterComponent>
        <hr>
        <DetailsComponent></DetailsComponent>
    </div>
</template>

<style scoped>


.master-detail-nav {
    margin: 10px;
    display: flex;
    flex-direction: row;
}

.master-detail-link {
    margin-top: 5px;
    margin-right: 3px;
    text-decoration: none;
    color: black;
    background-color: rgb(157, 157, 230);
    padding: 10px;
    border-radius: 5px;
    font-weight: bold;
    width: fit-content;
}

</style>
