import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";
import MasterDetailView from "../views/MasterDetailView.vue";
import SifrarnikView from "../views/SifrarnikView.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: "/",
            name: "home",
            component: HomeView,
        },
        {
            path: "/master-detail/:id",
            name: "master-detail",
            component: MasterDetailView,
        },
        {
            path: "/sifrarnici",
            name: "sifrarnici",
            component: SifrarnikView,
        },
    ],
});

export default router;
