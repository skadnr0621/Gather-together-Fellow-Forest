import Vue from "vue";
import Vuex from "vuex";
import VueJwtDecode from "vue-jwt-decode";
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    accessToken: null,
    user: {
      id: 0,
      nickname: "",
      schoolInfo: {
        element: "",
        middle: "",
        high: "",
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
      return state.user.schoolInfo.element == "" ? false : true;
    },
  },
  mutations: {
    SET_TOKEN(state, token) {
      const tokenInfo = VueJwtDecode.decode(token);
      console.log(tokenInfo);
      state.accessToken = token;
      state.user.id = tokenInfo.email;
      //닉네임 추가 예정
    },
  },
  actions: {
    setToken: ({ commit }, token) => {
      commit("SET_TOKEN", token);
    },
  },
  modules: {},
  plugins: [createPersistedState()],
});
