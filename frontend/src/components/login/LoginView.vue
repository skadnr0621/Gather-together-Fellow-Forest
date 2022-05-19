<template>
  <div id="login">
    <div class="absolute left-5 top-0 w-96 logo-home">
      <router-link to="/"><img src="@/assets/logo.png" /></router-link>
    </div>
    <div
      class="form rounded-xl border bg-slate-200 justify-self-end pt-5 pb-8 m-28"
    >
      <div class="logo mb-5">
        <img src="@/assets/logo.png" />
      </div>
      <div class="kakao-login">
        <button @click="loginWithKakao">
          <img src="@/assets/kakaoLogin.png" />
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { KAKAO_AUTH_URL } from "@/api/user.js";
export default {
  methods: {
    // back url 요청
    loginWithKakao() {
      window.open(KAKAO_AUTH_URL, "_SELF");
    },
  },
};
</script>

<style scoped>
#login {
  height: 100vh;
  overflow: hidden;
  margin: 0;
  background-image: url("@/assets/loginBg.png");
  background-size: cover;
  background-repeat: no-repeat;
  /* background-position: center; */
  display: grid;
  align-items: center;
}
.logo-home {
  width: 15%;
}
.form {
  width: 40%;
}
.kakao-login button {
  width: 60%;
}
.logo {
  text-align: -webkit-center;
}
.logo img {
  width: 50%;
}
</style>
