<template>
  <div>kakao</div>
</template>

<script>
import { getRequest } from "../../api/index.js";
export default {
  data() {
    return {};
  },

  mounted() {
    this.login();
  },

  methods: {
    async login() {
      const jwtToken = await this.getCookie("Authorization");
      localStorage.setItem("accessToken", jwtToken);
      this.$store.dispatch("setToken", jwtToken);
      console.log("토큰 저장확인");
      console.log(this.$store.getters.getToken);

      const response = await getRequest(
        "api/user/users/" + this.$store.state.user.id,
        "",
        jwtToken
      );
      this.$store.dispatch("setUserInfo", response.data);
      console.log("this.$store.state.user.id getRequest");
      console.log(response);
      console.log(this.checkSchoolInfo);
      if (!this.checkSchoolInfo) {
        this.$router.push({ name: "enterInfo" });
      } else {
        this.$router.push({ name: "modongmun" });
      }
    },
    getCookie(key) {
      var result = null;
      var cookie = document.cookie.split(";");
      cookie.some(function (item) {
        // 공백을 제거
        item = item.replace(" ", "");

        var dic = item.split("=");

        if (key === dic[0]) {
          result = dic[1];
          return true; // break;
        }
      });
      return result;
    },
  },
  computed: {
    checkSchoolInfo() {
      return this.$store.getters.isSchool;
    },
  },
};
</script>

<style lang="scss" scoped></style>
