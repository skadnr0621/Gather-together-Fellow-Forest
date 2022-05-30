<template>
  <div>
    <div class="nav">
      <div class="logo">Lush</div>
      <div class="menu">
        <ul id="main-menu">
          <li><a href="#">MENU1</a></li>
          <li>
            <a href="#">MENU2</a>
            <ul id="sub-menu">
              <li><a href="#">submenu</a></li>
              <li><a href="#">submenu</a></li>
            </ul>
          </li>
          <li>
            <a href="#">MENU3</a>
            <ul id="sub-menu">
              <li><a href="#">submenu</a></li>
              <li><a href="#">submenu</a></li>
              <li><a href="#">submenu</a></li>
              <li><a href="#">submenu</a></li>
              <li><a href="#">submenu</a></li>
              <li><a href="#">submenu</a></li>
              <li><a href="#">submenu</a></li>
            </ul>
          </li>
          <li>
            <a href="#">MENU4</a>
            <ul id="sub-menu">
              <li><a href="#">submenu</a></li>
              <li><a href="#">submenu</a></li>
            </ul>
          </li>
        </ul>
      </div>
      <div class="nav_tool">
        <div>검색</div>
        <div>장바구니</div>
        <div>로그인</div>
      </div>
    </div>
    <section class="carousel" aria-label="Gallery">
      <ol class="carousel__viewport">
        <li id="carousel__slide1" tabindex="0" class="carousel__slide">
          <div class="carousel__snapper"></div>
        </li>
        <li id="carousel__slide2" tabindex="0" class="carousel__slide">
          <div class="carousel__snapper"></div>
        </li>
        <li id="carousel__slide3" tabindex="0" class="carousel__slide">
          <div class="carousel__snapper"></div>
        </li>
        <li id="carousel__slide4" tabindex="0" class="carousel__slide">
          <div class="carousel__snapper"></div>
        </li>
      </ol>
      <aside class="carousel__navigation">
        <ol class="carousel__navigation-list">
          <li class="carousel__navigation-item">
            <a href="#carousel__slide1" class="carousel__navigation-button"
              >Go to slide 1</a
            >
          </li>
          <li class="carousel__navigation-item">
            <a href="#carousel__slide2" class="carousel__navigation-button"
              >Go to slide 2</a
            >
          </li>
          <li class="carousel__navigation-item">
            <a href="#carousel__slide3" class="carousel__navigation-button"
              >Go to slide 3</a
            >
          </li>
          <li class="carousel__navigation-item">
            <a href="#carousel__slide4" class="carousel__navigation-button"
              >Go to slide 4</a
            >
          </li>
        </ol>
      </aside>
    </section>
  </div>
</template>

<script>
export default {
  name: "S06P31B103TestView",

  data() {
    return {};
  },

  mounted() {},

  methods: {},
};
</script>

<style lang="scss" scoped>
* {
  color: #fff;
}
.nav {
  display: flex;
  justify-content: space-between;
  padding: 2%;
  background: #000;
  height: 5%;
}
.logo {
  width: 25%;
  text-align: left;
}
.menu {
  width: 50%;
}

.menu > div {
  margin-left: 5%;
}
.nav_tool {
  display: flex;
  justify-content: right;
  width: 25%;
}
.nav_tool > div {
  margin-left: 5%;
}
#main-menu {
  display: flex;
  justify-content: center;
  width: inherit;
  position: absolute;
}
#main-menu > li {
  margin-left: 5%;
  margin-right: 5%;
}
#main-menu > li:hover #sub-menu {
  opacity: 1;
  visibility: visible;
}
#sub-menu {
  visibility: hidden;
  opacity: 0;
  background: rgba(0, 0, 0, 0.025);
  padding-top: 60%;
}
@keyframes tonext {
  75% {
    left: 0;
  }
  95% {
    left: 100%;
  }
  98% {
    left: 100%;
  }
  99% {
    left: 0;
  }
}

@keyframes tostart {
  75% {
    left: 0;
  }
  95% {
    left: -300%;
  }
  98% {
    left: -300%;
  }
  99% {
    left: 0;
  }
}

@keyframes snap {
  96% {
    scroll-snap-align: center;
  }
  97% {
    scroll-snap-align: none;
  }
  99% {
    scroll-snap-align: none;
  }
  100% {
    scroll-snap-align: center;
  }
}

body {
  max-width: 37.5rem;
  margin: 0 auto;
  padding: 0 1.25rem;
  font-family: "Lato", sans-serif;
}

* {
  box-sizing: border-box;
  scrollbar-color: transparent transparent; /* thumb and track color */
  scrollbar-width: 0px;
}

*::-webkit-scrollbar {
  width: 0;
}

*::-webkit-scrollbar-track {
  background: transparent;
}

*::-webkit-scrollbar-thumb {
  background: transparent;
  border: none;
}

* {
  -ms-overflow-style: none;
}

ol,
li {
  list-style: none;
  margin: 0;
  padding: 0;
}

.carousel {
  z-index: -1;
  position: relative;
  position: relative;
  padding-top: 20%;
  filter: drop-shadow(0 0 10px #0003);
  perspective: 100px;
}

.carousel__viewport {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  display: flex;
  overflow-x: scroll;
  counter-reset: item;
  scroll-behavior: smooth;
  scroll-snap-type: x mandatory;
}

.carousel__slide {
  position: relative;
  flex: 0 0 100%;
  width: 100%;
  background-color: #f99;
  counter-increment: item;
}

.carousel__slide:nth-child(even) {
  background-color: #99f;
}

.carousel__snapper {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  scroll-snap-align: center;
}

@media (hover: hover) {
  .carousel__snapper {
    animation-name: tonext, snap;
    animation-timing-function: ease;
    animation-duration: 4s;
    animation-iteration-count: infinite;
  }

  .carousel__slide:last-child .carousel__snapper {
    animation-name: tostart, snap;
  }
}

@media (prefers-reduced-motion: reduce) {
  .carousel__snapper {
    animation-name: none;
  }
}

.carousel:hover .carousel__snapper,
.carousel:focus-within .carousel__snapper {
  animation-name: none;
}

.carousel__navigation {
  position: absolute;
  right: 0;
  bottom: 20px;
  left: 0;
  text-align: center;
}

.carousel__navigation-list,
.carousel__navigation-item {
  display: inline-block;
}

.carousel__navigation-button {
  display: inline-block;
  width: 1.5rem;
  height: 1.5rem;
  background-color: #333;
  background-clip: content-box;
  border: 0.25rem solid transparent;
  border-radius: 50%;
  font-size: 0;
  transition: transform 0.1s;
}
</style>
