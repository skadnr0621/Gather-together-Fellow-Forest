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
      const jwtToken = await this.$route.query.Authorization;
      localStorage.setItem("accessToken", jwtToken.replace("Bearer", ""));
      this.$store.dispatch("setToken", jwtToken.replace("Bearer", ""));
      console.log("토큰 저장확인");
      console.log(this.$store.getters.getToken);

      const response = await getRequest(
        "api/user/users/" + this.$store.state.user.id
      );
      this.$store.dispatch("setUserInfo", response.data);
      console.log(response);
      console.log(this.checkSchoolInfo);
      if (!this.checkSchoolInfo) {
        this.$router.push({ name: "enterInfo" });
      } else {
        this.$router.push({ name: "modongmun" });
      }
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
