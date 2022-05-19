<template>
  <div id="list-Board" class="w-5/6">
    <div class="mainWrap w-5/6">
      <div class="title pt-40 text-2xl font-bold">자유 게시판</div>

      <div class="searchWrap text-right w-5/6 mb-5 mt-8">
        <input
          class="w-1/3 mr-3 pt-1 pb-1 pl-1 border"
          placeholder="검색어를 입력해주세요"
          type="text"
        /><button class="pl-3 pr-3 pt-1 pb-1 btn">검색</button>
      </div>
      <div class="w-full">
        <table class="board-table w-5/6">
          <colgroup>
            <col width="6%" />
            <col width="*" />
            <col width="10%" />
            <col width="20%" />
          </colgroup>
          <tr>
            <th>No</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
          </tr>
          <tbody>
            <tr v-for="item in boardList" :key="item.postId">
              <td>{{ item.postId }}</td>
              <td>
                <a href="javascript:;" @click="fnView(item.postId)">{{
                  item.title
                }}</a>
              </td>
              <td>{{ item.username }}</td>
              <td>{{ item.createDate.substring(0, 10) }}</td>
            </tr>
          </tbody>
        </table>

        <div class="btnRightWrap w-5/6">
          <button
            class="btn border pl-3 pr-3 pt-1 pb-1 float-right mt-4"
            @click="writeBoard()"
          >
            글쓰기
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getRequest } from "../../api/index.js";
export default {
  data() {
    return {
      requestBody: {}, //리스트 페이지 데이터전송
      boardList: {},
    };
  },
  methods: {
    async getList() {
      //localStorage.setItem("selectSchool", "1");
      const paramas = { selectSchool: localStorage.getItem("selectSchool") };
      const response = await getRequest(
        "api/board/posts",
        paramas,
        this.$store.getters.getToken
      ); //파라미터 schoolID
      this.boardList = response.data;
      console.log(this.boardList[0]);
    },
    fnView(num) {
      this.requestBody.num = num;
      this.$router.push({ path: "/board/detail", query: this.requestBody }); //추가한 상세페이지 라우터
    },
    writeBoard() {
      this.$router.push({ path: "/board/create" });
    },
  },
  created() {
    this.getList();
  },
};
</script>

<style scoped>
#list-Board {
  width: 75%;
}
.searchWrap > input {
  border-style: solid;
}
.mainWrap {
  height: 100vh;
  overflow: hidden;
  margin: 0;
  background-repeat: no-repeat;
  background-position: center;
  background-image: url("@/assets/boardBg.png");
}
.btn {
  background-color: #48bae4;
  color: #fff;
  font-weight: bold;
}
.board-table {
  border-top: 2px solid black;
  border-bottom: 2px solid black;
  line-height: 2.5rem;
}
.board-table > tbody > tr {
  border-top: 1px solid #efefef;
}
.board-table > tbody > tr > td {
  text-align: center;
}
.board-table > tbody > tr > .list-title {
  text-align: left !important;
}
</style>
