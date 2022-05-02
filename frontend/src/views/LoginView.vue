<template>
  <div id="login">
    <div class="absolute left-5 top-0 w-96">
      <button><img src="../assets/logo.png" /></button>
    </div>
    <div
      class="form rounded-md border bg-slate-200 absolute top-48 right-16 pt-5 pb-8"
    >
      <div class="logo mb-5">
        <img src="../assets/logo.png" />
      </div>
      <div class="kakao-login">
        <button><img src="../assets/kakaoLogin.png" /></button>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
#login {
  height: 100vh;
  overflow: hidden;
  margin: 0;
  background-image: url("../assets/sample3.png");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
}
.form {
  width: 40%;
}
.kakao-login button {
  width: 50%;
}
.kakao-login button img {
}
.logo {
  text-align: -webkit-center;
}
.logo img {
  width: 70%;
}
</style>
