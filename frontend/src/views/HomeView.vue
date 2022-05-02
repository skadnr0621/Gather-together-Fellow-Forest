<template>
  <div id="home">
    <Nav />
    <div class="sections-menu">
      <span
        class="menu-point"
        v-bind:class="{ active: activeSection == index }"
        v-on:click="scrollToSection(index)"
        v-for="(offset, index) in offsets"
        v-bind:key="index"
      >
      </span>
    </div>

    <section class="fullpage">
      <div class="items-center grid grid-cols-2">
        <div class="col-span-1 p-20">
          <img src="../assets/sample1.png" />
        </div>
        <div class="logo col-span-1 p-20">
          <img src="../assets/logo.png" />
          <div class="font-bold text-3xl mb-3">
            메타버스에서 옛 친구들을 만나보세요
          </div>
          <div>
            메타버스는 어쩌구 저쩌구~~ 모여봐요 동문의숲은 어쩌구저쩌구~~~
          </div>
        </div>
      </div>
    </section>
    <section class="fullpage">
      <div class="items-center grid grid-cols-2">
        <div class="logo col-span-1 p-20">
          <img src="../assets/logo.png" />
          <div class="font-bold text-3xl mb-3">
            메타버스에서 옛 친구들을 만나보세요
          </div>
          <div>
            메타버스는 어쩌구 저쩌구~~ 모여봐요 동문의숲은 어쩌구저쩌구~~~
          </div>
        </div>
        <div class="col-span-1 p-20">
          <img src="../assets/sample2.png" />
        </div>
      </div>
    </section>
    <section class="fullpage">
      <h1>Section 3</h1>
      <p>works on <b>desktop & mobile</b></p>
    </section>
    <section class="fullpage">
      <h1>Section 4</h1>
      <p>
        Tutorial
        <a
          href="https://webdeasy.de/en/programming-vue-js-fullpage-scroll/?referer=cp-NVOEBL"
          target="_blank"
          >here</a
        >
      </p>
    </section>
  </div>
</template>

<script>
//https://webdeasy.de/en/programming-vue-js-fullpage-scroll
import Nav from "@/common/NavView.vue";
export default {
  components: { Nav },
  data() {
    return {
      inMove: false,
      activeSection: 0,
      offsets: [],
      touchStartY: 0,
    };
  },
  methods: {
    calculateSectionOffsets() {
      let sections = document.getElementsByTagName("section");
      let length = sections.length;
      for (let i = 0; i < length; i++) {
        let sectionOffset = sections[i].offsetTop;
        this.offsets.push(sectionOffset);
      }
    },
    handleMouseWheel: function (e) {
      if (e.wheelDelta < 30 && !this.inMove) {
        this.moveUp();
      } else if (e.wheelDelta > 30 && !this.inMove) {
        this.moveDown();
      }

      e.preventDefault();
      return false;
    },
    handleMouseWheelDOM: function (e) {
      if (e.detail > 0 && !this.inMove) {
        this.moveUp();
      } else if (e.detail < 0 && !this.inMove) {
        this.moveDown();
      }

      return false;
    },
    moveDown() {
      this.inMove = true;
      this.activeSection--;

      if (this.activeSection < 0) this.activeSection = this.offsets.length - 1;

      this.scrollToSection(this.activeSection, true);
    },
    moveUp() {
      this.inMove = true;
      this.activeSection++;

      if (this.activeSection > this.offsets.length - 1) this.activeSection = 0;

      this.scrollToSection(this.activeSection, true);
    },
    scrollToSection(id, force = false) {
      if (this.inMove && !force) return false;

      this.activeSection = id;
      this.inMove = true;

      document.getElementsByTagName("section");
      document
        .getElementsByTagName("section")
        [id].scrollIntoView({ behavior: "smooth" });

      setTimeout(() => {
        this.inMove = false;
      }, 400);
    },
    touchStart(e) {
      e.preventDefault();

      this.touchStartY = e.touches[0].clientY;
    },
    touchMove(e) {
      if (this.inMove) return false;
      e.preventDefault();

      const currentY = e.touches[0].clientY;

      if (this.touchStartY < currentY) {
        this.moveDown();
      } else {
        this.moveUp();
      }

      this.touchStartY = 0;
      return false;
    },
  },
  mounted() {
    this.calculateSectionOffsets();

    window.addEventListener("DOMMouseScroll", this.handleMouseWheelDOM);
    window.addEventListener("mousewheel", this.handleMouseWheel, {
      passive: false,
    });

    window.addEventListener("touchstart", this.touchStart, { passive: false });
    window.addEventListener("touchmove", this.touchMove, { passive: false });
  },
  destroyed() {
    window.removeEventListener("mousewheel", this.handleMouseWheel, {
      passive: false,
    });
    window.removeEventListener("DOMMouseScroll", this.handleMouseWheelDOM);

    window.removeEventListener("touchstart", this.touchStart);
    window.removeEventListener("touchmove", this.touchMove);
  },
};
</script>

<style scoped>
body {
  margin: 0;
  color: #fff;
  font-family: Helvetica, arial, sans-serif;
  overflow: hidden;
}

.fullpage {
  height: 100vh;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.sections-menu {
  position: fixed;
  right: 1rem;
  top: 50%;
  transform: translateY(-50%);
}

.sections-menu .menu-point {
  width: 10px;
  height: 10px;
  background-color: #000;
  display: block;
  margin: 1rem 0;
  opacity: 0.6;
  transition: 0.4s ease all;
  cursor: pointer;
}

.sections-menu .menu-point.active {
  opacity: 1;
  transform: scale(1.5);
}
.logo {
  text-align: -webkit-center;
}
.logo img {
  width: 100%;
}
</style>
