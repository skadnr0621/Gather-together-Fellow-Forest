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
          <div class="col-start-2-end-3 col-span-2">
            <input
              v-model="birthYear"
              class="w-full pl-2 pt-1 pb-1"
              placeholder="출생년도를 입력하세요"
            />
          </div>
        </div>

        <div class="grid grid-cols-3 gap-4 text-left mb-4">
          <div class="col-auto font-bold">초등학교</div>
          <div class="col-start-2-end-3 col-span-2">
            <input
              class="w-full pl-2 pt-1 pb-1"
              placeholder="초등학교를 입력하세요"
              v-model="dataEl"
              @click="openModal('el')"
            />
          </div>
          <div class="col-start-2 col-span-2">
            <input
              class="w-full pl-2 pt-1 pb-1"
              placeholder="졸업년도를 입력하세요"
              v-model="egYear"
            />
          </div>
        </div>
        <div class="grid grid-cols-3 gap-4 text-left mb-4">
          <div class="col-auto font-bold">중학교</div>
          <div class="col-start-2 col-span-2">
            <input
              class="w-full pl-2 pt-1 pb-1"
              placeholder="중학교를 입력하세요"
              v-model="dataMd"
              @click="openModal('md')"
            />
          </div>
          <div class="col-start-2 col-span-2">
            <input
              class="w-full pl-2 pt-1 pb-1"
              placeholder="졸업년도를 입력하세요"
              v-model="mgYear"
            />
          </div>
        </div>
        <div class="grid grid-cols-3 gap-4 text-left mb-4">
          <div class="col-auto font-bold">고등학교</div>
          <div class="col-start-2 col-span-2">
            <input
              class="w-full pl-2 pt-1 pb-1"
              placeholder="고등학교를 입력하세요"
              v-model="dataHi"
              @click="openModal('hi')"
            />
          </div>
          <div class="col-start-2 col-span-2">
            <input
              class="w-full pl-2 pt-1 pb-1"
              placeholder="졸업년도를 입력하세요"
              v-model="hgYear"
            />
          </div>
        </div>

        <div>
          <button
            @click="registInfo"
            class="btn-regist rounded-md p-1 text-xl mt-8 font-bold"
          >
            내 정보 등록하기
          </button>
          <div class="example-modal-window">
            <!-- 컴포넌트 MyModal -->
            <Modal @close="closeModal" v-if="modal">
              <!-- default 슬롯 콘텐츠 -->
              <p>학교 검색</p>
              <div class="div-search flex">
                <input
                  class="input-search p-1"
                  v-on:keyup.enter="doSearch"
                  v-model="search"
                /><button
                  @click="doSearch"
                  class="btn-search rounded-md p-1 text-l font-bold"
                >
                  검색
                </button>
              </div>
              <table class="school-table">
                <colgroup>
                  <col width="50%" />
                  <col width="50%" />
                </colgroup>
                <tr>
                  <th class="m1-4 mr-4">소재지</th>
                  <th class="m1-4 mr-4">학교명</th>
                </tr>
                <tr
                  class="table-list"
                  v-for="item in schoolList"
                  :key="item.schoolId"
                  href="javascript:;"
                  @click="selectSchool(item.name, item.schoolId)"
                >
                  <td>{{ item.location }}</td>
                  <td>
                    <a></a>
                    {{ item.name }}
                  </td>
                </tr>
              </table>
              <!-- /default -->
              <!-- footer 슬롯 콘텐츠 -->
              <template slot="footer"> </template>
              <!-- /footer -->
            </Modal>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getRequest, patchRequest } from "../../api/index.js";
import Modal from "./ModalView.vue";
export default {
  components: { Modal },
  data() {
    return {
      birthYear: "",
      modal: false,
      search: "",
      dataEl: "",
      dataMd: "",
      dataHi: "",
      schoolList: "",
      gubun: "",
      elName: "",
      mdName: "",
      hiName: "",
      egYear: "",
      mgYear: "",
      hgYear: "",
      elementarySchoolId: "",
      middleSchoolId: "",
      highSchoolId: "",
    };
  },
  methods: {
    openModal(gubun) {
      this.modal = true;
      if (gubun == "el") {
        console.log("초등학교 선택");
        this.gubun = "el";
      } else if (gubun == "md") {
        this.gubun = "md";
      } else if (gubun == "hi") {
        this.gubun = "hi";
      }
    },
    closeModal() {
      this.schoolList = "";
      this.modal = false;
    },
    doSearch() {
      if (this.search.length > 0) {
        this.findschool();
        this.search = "";
        //this.dataEl = this.message;
      } else {
        alert("메시지를 입력해주세요.");
      }
    },
    selectSchool(name, id) {
      if (this.gubun == "el") {
        this.elName = name;
        this.elementarySchoolId = id;
        this.dataEl = name;
        this.schoolList = "";
        this.gubun = "";
        this.closeModal();
      } else if (this.gubun == "md") {
        this.mdName = name;
        this.middleSchoolId = id;
        this.dataMd = name;
        this.gubun = "";
        this.schoolList = "";
        this.closeModal();
      } else if (this.gubun == "hi") {
        this.hiName = name;
        this.highSchoolId = id;
        this.dataHi = name;
        this.gubun = "";
        this.schoolList = "";
        this.closeModal();
      }
    },
    async findschool() {
      const paramas = { keyword: this.search };
      const response = await getRequest(
        "api/school/schools",
        paramas,
        this.$store.getters.getToken
      );
      console.log(response);
      this.schoolList = response.data;
    },
    async registInfo() {
      const schoolData = {
        egYear: this.egYear,
        mgYear: this.mgYear,
        hgYear: this.hgYear,
        elementarySchoolId: this.elementarySchoolId,
        middleSchoolId: this.middleSchoolId,
        highSchoolId: this.highSchoolId,
        birthYear: parseInt(this.birthYear),
      };
      const schoolName = {
        elName: this.elName,
        mdName: this.mdName,
        hiName: this.hiName,
        birthYear: this.birthYear,
      };
      this.$store.dispatch("setSchoolName", schoolName);
      console.log(schoolData);
      console.log(schoolName);
      console.log(this.$store.state);
      //const response = aait patchRequest("api/user/users/"+this.$store.user.id)
      const response = await patchRequest(
        "api/user/users/" + this.$store.state.user.userId,
        schoolData,
        this.$store.getters.getToken
      );
      console.log(response);
      this.$router.push({ name: "modongmun" });
    },
  },
  watch: {
    birthYear(newDate) {
      if (newDate.length == 4) {
        this.egYear = parseInt(newDate) + 13;
        this.mgYear = this.egYear + 3;
        this.hgYear = this.mgYear + 3;
      }
    },
  },
};
</script>

<style scoped>
#login {
  height: 100vh;
  overflow: hidden;
  margin: 0;
  background-image: url("@/assets/enterInfo.png");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center;
  display: grid;
  align-items: center;
}
input {
  border-radius: 10px;
  background: #fff;
}
.vdp-datepicker {
  border-radius: 10px;
  background: #fff;
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
  border-radius: 10px;
}
.grid {
  align-items: center;
}
.input-search {
  border: 1px solid #e2e2e2;
  border-radius: 10px;
  margin-right: 1%;
}
.btn-search {
  background-color: #48bae4;
  color: #fff;
}
.btn-regist {
  width: 100%;
  background-color: #48bae4;
  color: #fff;
}
.school-table {
  margin: auto;
  margin-top: 3%;
  width: 100%;
}
.table-list:hover {
  text-decoration-line: underline;
  cursor: pointer;
}
</style>
