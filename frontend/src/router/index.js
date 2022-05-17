import Vue from "vue";
import VueRouter from "vue-router";

import HomeView from "../views/HomeView.vue";
import TestView from "../views/TestView.vue";

import Login from "../views/Login.vue";
import LoginView from "../components/login/LoginView.vue";
import EnterInfo from "../components/login/EnterInfo.vue";
import KakaoRedirect from "../components/login/KakaoRedirect.vue";

import BoardView from "../views/BoardView.vue";
import CreateBoard from "../components/board/CreateBoard.vue";
import ListBoard from "../components/board/ListBoard.vue";
import DetailBoard from "../components/board/DetailBoard.vue";

import ScheduleView from "../views/ScheduleView.vue";
import ScheduleBoard from "../components/schedule/ScheduleBoard.vue";

import ModongmunView from "../views/PlayView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/test",
    name: "test",
    component: TestView,
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
      {
        path: "kakaoRedirect",
        name: "KakaoRedirect",
        component: KakaoRedirect,
      },
    ],
  },
  {
    path: "/board",
    name: "board",
    component: BoardView,
    children: [
      {
        path: "/",
        name: "listBoard",
        component: ListBoard,
      },
      {
        path: "create",
        name: "createBoard",
        component: CreateBoard,
      },
      {
        path: "detail",
        name: "detailBoard",
        component: DetailBoard,
      },
    ],
  },
  {
    path: "/schedule",
    name: "schedule",
    component: ScheduleView,
    children: [
      {
        path: "/",
        name: "scheduleBoard",
        component: ScheduleBoard,
      },
    ],
  },
  {
    path: "/modongmun",
    name: "modongmun",
    component: ModongmunView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
