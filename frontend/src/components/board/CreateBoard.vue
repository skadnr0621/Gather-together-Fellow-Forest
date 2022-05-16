<template>
  <div id="create-board" class="w-5/6">
    <div class="mainWrap w-5/6">
      <div class="title pt-40 mb-8 text-2xl font-bold">자유 게시판</div>

      <div class="w-full">
        <div class="board-write w-5/6">
          <div class="text-left write-topWrap mb-2">
            <div class="text-xl font-bold pb-2">글쓰기</div>
          </div>
          <div class="contants-title">
            <input
              class="w-full h-10 border pl-2 pt-2 pb-2 mb-2"
              style="text"
              v-model="title"
              placeholder="제목을 입력해주세요."
            />
          </div>
          <div class="contants-content border h-56">
            <textarea
              class="content pl-2"
              v-model="content"
              ref="content"
              placeholder="내용을 입력해주세요."
            ></textarea>
          </div>
          <div class="contants-image border mt-2">
            <div v-if="images" class="w-full h-full flex items-center">
              <img :src="images" alt="image" />
            </div>
            <div
              v-else
              class="w-full h-full flex items-center justify-center cursor-pointer hover:bg-pink-100"
              @click="clickInputTag()"
            >
              <input
                ref="image"
                id="input"
                type="file"
                name="image"
                accept="image/*"
                multiple="multiple"
                class="hidden"
                @change="uploadImage()"
              />
              <svg
                class="w-8 h-8"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                stroke="currentColor"
              >
                <path
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M12 9v3m0 0v3m0-3h3m-3 0H9m12 0a9 9 0 11-18 0 9 9 0 0118 0z"
                />
              </svg>
            </div>
          </div>
        </div>

        <div class="flex btnLeftRightWrap w-5/6">
          <button class="btn border pl-3 pr-3 pt-1 pb-1 mt-4">목록</button>
          <div class="flex justify-end">
            <button
              @click="registForm()"
              class="btn border pl-3 pr-3 pt-1 pb-1 mt-4"
            >
              등록
            </button>
            <button class="btn-cancel border ml-2 pl-3 pr-3 pt-1 pb-1 mt-4">
              취소
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { postRequest } from "../../api/index.js";
export default {
  data() {
    //변수 생성
    return {
      title: "",
      content: "",
      userId: "",
      schoolId: "",
    };
  },
  methods: {
    async registForm() {
      console.log(this.$store.state.user.id);
      const boardData = {
        title: this.title,
        content: this.content,
        userId: "3",
        // userId: this.$store.state.user.id,
        schoolId: "1",
      };
      const response = await postRequest("api/board/posts", boardData);
      console.log(response);

      if (response.status == 200) {
        alert("게시글 등록 완료");
        this.$router.push({ name: "listBoard" });
      } else {
        alert(response.data);
      }
    },

    // uploadImage: function () {
    //   let image = this.$refs["image"].files[0];
    //   this.images = image;
    //   console.log(image);
    // },
    // clickInputTag: function () {
    //   this.$refs["image"].click();
    // },
  },
};
</script>

<style scoped>
#create-board {
  width: 75%;
}
input {
  border-radius: 10px;
  background: #e2e2e2;
}
.mainWrap {
  height: 100vh;
  overflow: hidden;
  margin: 0;
  background-repeat: no-repeat;
  background-position: center;
  background-image: url("@/assets/boardBg.png");
}
.content {
  width: 100%;
  height: inherit;
}
.btn {
  background-color: #48bae4;
  color: #fff;
  font-weight: bold;
}
.btn-cancel {
  background-color: #48bae44d;
  color: #48bae4;
  font-weight: bold;
}
.board-write {
  line-height: 2.5rem;
}
.write-topWrap {
  border-bottom: 2px solid #8f8f8f;
}
.btnLeftRightWrap {
  justify-content: space-between;
}
</style>
