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
        elName: "",
        mdName: "",
        hiName: "",
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
      state.user.userId = tokenInfo.id;
      state.user.id = tokenInfo.email;
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
    },
    SET_SCHOOLNAME(state, data) {
      state.user.schoolInfo.elName = data.elName;
      state.user.schoolInfo.mdName = data.mdName;
      state.user.schoolInfo.hiName = data.hiName;
      state.user.birthYear = data.birthYear;
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
    setSchoolName: ({ commit }, data) => {
      commit("SET_SCHOOLNAME", data);
    },
  },
  modules: {},
  plugins: [createPersistedState()],
});
