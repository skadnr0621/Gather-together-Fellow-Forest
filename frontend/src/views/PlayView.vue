<template>
  <div class="Unity">
    <div class="div-unlity">
      <div id="DIV_BTNLINK" class="div-btnLink">
        <button class="w-full btn_onLink" @click="onLink"></button>
      </div>
      <Unity
        :unity="unityContext"
        width="1664px"
        height="936px"
        style="
          position: absolute;
          left: 50%;
          top: 50%;
          transform: translate(-50%, -50%);
          z-index: 0;
        "
      />
    </div>
  </div>
</template>
<script>
import UnityWebgl from "unity-webgl";

const Unity = new UnityWebgl({
  loaderUrl: "/Build/vueBuild.loader.js",
  dataUrl: "/Build/vueBuild.data",
  frameworkUrl: "/Build/vueBuild.framework.js",
  codeUrl: "/Build/vueBuild.wasm",
});
Unity.on(
  "created",
  () => (document.getElementById("DIV_BTNLINK").style.visibility = "visible")
);

export default {
  components: {
    Unity: UnityWebgl.vueComponent,
  },
  data() {
    return {
      unityContext: Unity,
      data: {
        elementarySchool: this.$store.state.user.schoolInfo.elementarySchoolId,
        middleSchool: this.$store.state.user.schoolInfo.middleSchoolId,
        highSchool: this.$store.state.user.schoolInfo.highSchoolId,
        userName: this.$store.state.user.username,
        birth: this.$store.state.user.birthYear,
        elName: this.$store.state.user.schoolInfo.elName,
        mdName: this.$store.state.user.schoolInfo.mdName,
        hiName: this.$store.state.user.schoolInfo.hiName,
        accessToken: this.$store.state.accessToken,
        userId: this.$store.state.user.userId,
      },
    };
  },
  methods: {
    onLink() {
      console.log(this.$store.state.user.schoolInfo);
      Unity.send(
        "ConnectVueManager",
        "responseUserInfo",
        JSON.stringify(this.data)
      );
      document.getElementById("DIV_BTNLINK").style.visibility = "hidden";
    },
  },
  updated() {
    Unity.on("requestBoard ", () => {
      console.log("121212");
      console.log("requestBoard테스트");
    });
  },
};
</script>
<style scoped>
.div-unity {
  position: relative;
}
.div-btnLink {
  position: absolute;
  width: 100%;
  height: 100%;
  z-index: 1000;
  visibility: hidden;
}
.div-btnLink > button {
  width: 100%;
  height: 100%;
}
</style>
