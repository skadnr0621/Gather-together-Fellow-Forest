<template>
  <div id="nav" class="flex p-5 items-center">
    <div class="logo">
      <button><img src="../assets/logo.png" /></button>
    </div>
    <div>
      <div class="login">
        <router-link to="/login"
          ><button class="rounded-full pl-8 pr-8 p-4 text-3xl">
            LOGIN
          </button></router-link
        >
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
#nav {
  width: 100%;
  height: 100px;
  position: fixed;
  justify-content: space-between;
  background-color: #a6e0ba;
}
.logo img {
  height: 100px;
}
.login button {
  background-color: #67b76f;
  color: #fff;
}
</style>
