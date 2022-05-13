<template>
  <div class="about">
    <Unity :unity="unityContext" width="1920px" height="1080px" />
  </div>
</template>
<script>
import UnityWebgl from "unity-webgl";

const Unity = new UnityWebgl({
  loaderUrl: "/Build/Build.loader.js",
  dataUrl: "/Build/Build.data",
  frameworkUrl: "/Build/Build.framework.js",
  codeUrl: "/Build/Build.wasm",
});

export default {
  components: {
    Unity: UnityWebgl.vueComponent,
  },
  data() {
    return {
      unityContext: Unity,
    };
  },
};
</script>
