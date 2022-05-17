import Vue from "vue";
import Vuex from "vuex";
import VueJwtDecode from "vue-jwt-decode";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    accessToken: null,
    user: {
      userId: "",
      id: "",
      username: "",
      birthYear: "",
      schoolInfo: {
        elementarySchoolId: "",
        middleSchoolId: "",
        highSchoolId: "",
        egYear: "",
        mgYear: "",
        hgYear: "",
      },
    },
  },
  getters: {
    isLogin(state) {
      return state.accessToken == null ? false : true;
    },
    getToken(state) {
      return state.accessToken;
    },
    isSchool(state) {
      return state.user.schoolInfo.elementarySchoolId == null ? false : true;
    },
  },
  mutations: {
    SET_TOKEN(state, token) {
      const tokenInfo = VueJwtDecode.decode(token);
      console.log(tokenInfo);
      state.accessToken = token;
      state.user.id = tokenInfo.email;
      console.log(state.user.nickname);
      //닉네임 추가 예정
    },
    SET_USERINFO(state, data) {
      state.user.userId = data.userId;
      state.user.username = data.username;
      state.user.birthYear = data.birthYear;
      state.user.schoolInfo.elementarySchoolId = data.elementarySchoolId;
      state.user.schoolInfo.middleSchoolId = data.middleSchoolId;
      state.user.schoolInfo.highSchoolId = data.highSchoolId;
      state.user.schoolInfo.egYear = data.egYear;
      state.user.schoolInfo.mgYear = data.mgYear;
      state.user.schoolInfo.hgYear = data.hgYear;
      console.log("store유저정보");
      console.log(state.user);
    },
  },
  actions: {
    setToken: ({ commit }, token) => {
      commit("SET_TOKEN", token);
    },
    setUserInfo: ({ commit }, data) => {
      commit("SET_USERINFO", data);
    },
  },
  modules: {},
  plugins: [createPersistedState()],
});
