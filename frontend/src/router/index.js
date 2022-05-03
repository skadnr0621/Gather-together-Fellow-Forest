import Vue from "vue";
import VueRouter from "vue-router";

import HomeView from "../views/HomeView.vue";

import Login from "../views/Login.vue";
import LoginView from "../components/login/LoginView.vue";
import EnterInfo from "../components/login/EnterInfo.vue";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/login",
    name: "login",
    component: Login,
    children: [
      {
        path: "/",
        name: "loginView",
        component: LoginView,
      },
      {
        path: "enterInfo",
        name: "enterInfo",
        component: EnterInfo,
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
