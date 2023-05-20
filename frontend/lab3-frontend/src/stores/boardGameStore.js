import { ref } from "vue";
import { defineStore } from "pinia";

export const useBoardGameStore = defineStore("bg", () => {
    const bgId = ref();
    function setId(id) {
        bgId.value = id;
    }

    return { bgId, setId };
});
