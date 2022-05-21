<template>
  <div id="detail-board" class="w-5/6">
    <div class="mainWrap w-5/6">
      <div class="title pt-40 mb-8 text-2xl font-bold">자유 게시판</div>

      <div class="w-full">
        <div class="board-contants w-5/6">
          <div class="flex contants-topWrap mb-2">
            <div class="text-xl font-bold">{{ boardData.title }}</div>
            <div
              v-if="boardData.userId == $store.state.user.userId"
              class="flex justify-end text-xs contants-tool"
            >
              <div class="mr-2">수정</div>
              <div @click="deleteBoard(boardData.postId)">삭제</div>
            </div>
          </div>
          <div class="flex text-xs">
            <div class="mr-2">{{ boardData.username }}</div>
            <div class="mr-2">
              {{ (boardData.createDate || "").split("T")[0] }}
              {{ (boardData.createDate || "").split("T")[1].substring(0, 8) }}
            </div>
            <div class="mr-2">조회 0</div>
          </div>
          <div class="main-contants text-left mt-4 mb-2 pt-4 pb-4">
            <div>
              {{ boardData.content }}
            </div>
          </div>
          <div class="comment">
            <div class="comment-write w-full flex mt-6 mb-2 p-2">
              <div class="flex">
                <div class="comment-write-name">
                  {{ this.$store.state.user.nickname }}이제훈
                </div>
                <input
                  class="comment-write-input w-5/6 ml-4 mr-2"
                  type="text"
                  placeholder="댓글을 입력해주세요."
                  v-model="comment"
                />
              </div>
              <div>
                <button class="btn_comment text-sm p-2" @click="writeComment">
                  댓글 작성
                </button>
              </div>
            </div>

            <!-- comment-contants v-for -->
            <div
              v-for="item in commentList"
              :key="item.commentId"
              class="comment-contants mb-2 pb-2"
            >
              <div class="flex comment-topWrap">
                <div class="flex text-md font-bold comment-left mb-2">
                  <div>{{ item.username }}</div>
                  <div class="text-xs ml-2">
                    {{ item.createDate.split("T")[0] }}
                    {{ item.createDate.split("T")[1].substring(0, 8) }}
                  </div>
                </div>

                <div
                  v-if="item.userId == $store.state.user.userId"
                  class="flex justify-end text-xs contants-tool"
                >
                  <div class="mr-2">수정</div>
                  <div @click="deleteComment(item.commentId)">삭제</div>
                </div>
              </div>
              <div class="text-left ml-1">{{ item.content }}</div>
            </div>
          </div>
        </div>

        <div class="text-right w-5/6">
          <!--<button class="btn border pl-3 pr-3 pt-1 pb-1 mt-4">글쓰기</button>-->
          <button @click="goList()" class="btn border pl-3 pr-3 pt-1 pb-1 mt-4">
            목록
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getRequest, postRequest, deleteRequest } from "../../api/index.js";
export default {
  data() {
    return {
      requestBody: this.$route.query,
      boardData: [],
      comment: "",
      commentList: [],
    };
  },
  methods: {
    async getList() {
      const response = await getRequest(
        "api/board/posts/" + this.requestBody.num,
        "",
        this.$store.getters.getToken
      );
      this.boardData = response.data;
      console.log(this.boardData);
      const res = await getRequest(
        "api/board/posts/" + this.boardData.postId + "/comments",
        "",
        this.$store.getters.getToken
      );
      console.log(res);
      this.commentList = res.data;
      console.log(this.commentList);
    },

    async writeComment() {
      const commentData = {
        postId: this.boardData.postId,
        userId: this.$store.state.user.userId,
        content: this.comment,
      };
      console.log(commentData);
      const response = await postRequest(
        "api/board/posts/" + this.boardData.postId + "/comments",
        commentData,
        this.$store.getters.getToken
      );
      console.log(response);
      this.$router.go();
    },
    goList() {
      this.$router.push({ path: "/board" });
    },
    deleteComment(commentId) {
      const response = deleteRequest(
        "api/board/posts/" + this.boardData.postId + "/comments/" + commentId,
        "",
        this.$store.getters.getToken
      );
      alert("댓글이 삭제되었습니다.");
      this.$router.go();
    },
    deleteBoard(boardId) {
      const response = deleteRequest(
        "api/board/posts/" + boardId,
        "",
        this.$store.getters.getToken
      );
      console.log(response);
      alert("게시글이 삭제되었습니다.");
      this.$router.push({ path: "/board" });
    },
  },
  created() {
    this.getList();
  },
  mounted() {},
};
</script>

<style scoped>
#detail-board {
  width: 75%;
}
.mainWrap {
  height: 100vh;
  overflow: hidden;
  margin: 0;
  background-repeat: no-repeat;
  background-position: center;
  background-image: url("@/assets/boardBg.png");
}
.topWrap {
  justify-content: space-between;
  align-items: center;
}
.contants-tool {
  margin-right: 1rem;
}
.btn {
  background-color: #48bae4;
  color: #fff;
  font-weight: bold;
}
.board-contants {
  border: 1px solid #e2e2e2;
  line-height: 2.5rem;
  border-radius: 10px;
  padding: 1rem;
}
.contants-topWrap {
  justify-content: space-between;
}
.contants-tool {
  color: #e2e2e2;
}
.main-contants {
  border-top: 1px solid #e2e2e2;
  border-bottom: 1px solid #e2e2e2;
}
.comment-write {
  border: 1px solid #e2e2e2;
  border-radius: 10px;
  justify-content: space-between;
}
.comment-write-input {
  border-bottom: 1px solid #e2e2e2;
}
.btn_comment {
  border: 1px solid #e2e2e2;
  border-radius: 10px;
}
.comment-contants {
  line-height: normal;
  border-bottom: 1px solid #e2e2e2;
}
.comment-topWrap {
  justify-content: space-between;
  align-items: center;
}
.comment-topWrap > .comment-left {
  align-items: center;
}
.comment-topWrap > .comment-left > div:nth-of-type(2) {
  color: #e2e2e2;
}
.btnLeftRightWrap {
  justify-content: space-between;
}
.contants-tool > div {
  cursor: pointer;
}
</style>
