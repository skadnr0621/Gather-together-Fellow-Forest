<template>
  <div id="login">
    <div class="absolute left-5 top-0 w-96 logo-home">
      <router-link to="/"><img src="@/assets/logo.png" /></router-link>
    </div>
    <div
      class="form rounded-xl border bg-slate-200 justify-self-end pt-5 pb-8 m-28 mr-36"
    >
      <div class="form-contents ml-12 mr-12">
        <div class="logo mb-5">
          <img src="@/assets/logo.png" />
        </div>
        <div class="grid grid-cols-3 gap-4 text-left mb-4">
          <div class="col-auto font-bold">출생년도</div>
          <datepicker
            id="input-id"
            :value="defaultDate"
            :format="DatePickerFormat"
            minimum-view="year"
            name="datepicker"
            input-class="input-class"
            placeholder="출생년도를 선택하세요"
          ></datepicker>
        </div>

        <div class="grid grid-cols-3 gap-4 text-left mb-4">
          <div class="col-auto font-bold">초등학교</div>
          <div class="col-start-2 col-span-2">
            <input
              class="w-full pl-2 pt-1 pb-1"
              placeholder="초등학교를 입력하세요"
            />
          </div>
          <div class="col-start-2 col-span-2">
            <input
              class="w-full pl-2 pt-1 pb-1"
              placeholder="졸업년도를 입력하세요"
            />
          </div>
        </div>
        <div class="grid grid-cols-3 gap-4 text-left mb-4">
          <div class="col-auto font-bold">중학교</div>
          <div class="col-start-2 col-span-2">
            <input
              class="w-full pl-2 pt-1 pb-1"
              placeholder="중학교를 입력하세요"
            />
          </div>
          <div class="col-start-2 col-span-2">
            <input
              class="w-full pl-2 pt-1 pb-1"
              placeholder="졸업년도를 입력하세요"
            />
          </div>
        </div>
        <div class="grid grid-cols-3 gap-4 text-left mb-4">
          <div class="col-auto font-bold">고등학교</div>
          <div class="col-start-2 col-span-2">
            <input
              class="w-full pl-2 pt-1 pb-1"
              placeholder="고등학교를 입력하세요"
            />
          </div>
          <div class="col-start-2 col-span-2">
            <input
              class="w-full pl-2 pt-1 pb-1"
              placeholder="졸업년도를 입력하세요"
            />
          </div>
        </div>

        <div>
          <button class="btn-regist rounded-md p-1 text-xl mt-8 font-bold">
            내 정보 등록하기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Datepicker from "vuejs-datepicker";
export default {
  components: {
    Datepicker,
  },
  data() {
    return {
      DatePickerFormat: "yyyy",
    };
  },
};
</script>

<style scoped>
#login {
  height: 100vh;
  overflow: hidden;
  margin: 0;
  background-image: url("@/assets/sample3.png");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  display: grid;
  align-items: center;
}
.logo-home {
  width: 15%;
}
.form {
  width: 30%;
}
.kakao-login button {
  width: 60%;
}
.logo {
  text-align: -webkit-center;
}
.logo img {
}
::v-deep .vdp-datepicker > div > input {
  padding-top: 0.25rem;
  padding-bottom: 0.25rem;
  padding-left: 0.5rem;
}
#input-id {
  border-radius: 10%;
}
.btn-regist {
  width: 100%;
  background-color: #48bae4;
  color: #fff;
}
</style>
