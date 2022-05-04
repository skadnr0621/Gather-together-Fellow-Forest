<template>
  <div id="detail-board" class="w-5/6">
    <div class="mainWrap w-5/6">
      <div class="title pt-40 mb-8 text-2xl font-bold">자유 게시판</div>

      <div class="w-full">
        <div class="board-contants w-5/6">
          <div class="flex contants-topWrap mb-2">
            <div class="text-xl font-bold">나는야 김남욱!</div>
            <div class="flex text-xs contants-tool">
              <div class="mr-2">수정</div>
              <div>삭제</div>
            </div>
          </div>
          <div class="flex text-xs">
            <div class="mr-2">김남욱</div>
            <div class="mr-2">2022-05-04</div>
            <div class="mr-2">조회 0</div>
          </div>
          <div class="main-contants text-left mt-4 mb-2 pt-4 pb-4">
            <img class="w-1/3" src="@/assets/logo.png" />
            <div>
              오늘 내 생일파티 즐거웠어<br />
              다들 축하해줘서 고마워
            </div>
          </div>
          <div class="comment">
            <!-- comment-contants v-for -->
            <div class="comment-contants mb-2 pb-2">
              <div class="flex comment-topWrap">
                <div class="flex text-md font-bold comment-left mb-2">
                  <div>이호열</div>
                  <div class="text-xs ml-2">22.05.04</div>
                </div>
                <div class="flex text-xs contants-tool">
                  <div class="mr-2">수정</div>
                  <div>삭제</div>
                </div>
              </div>
              <div class="text-left ml-1">asdasd</div>
            </div>
            <div class="comment-contants mb-2 pb-2">
              <div class="flex comment-topWrap">
                <div class="flex text-md font-bold comment-left mb-2">
                  <div>이호열</div>
                  <div class="text-xs ml-2">22.05.04</div>
                </div>
                <div class="flex text-xs contants-tool">
                  <div class="mr-2">수정</div>
                  <div>삭제</div>
                </div>
              </div>
              <div class="text-left ml-1">asdasd</div>
            </div>
          </div>
        </div>

        <div class="flex btnLeftRightWrap w-5/6">
          <button class="btn border pl-3 pr-3 pt-1 pb-1 mt-4">글쓰기</button>
          <button class="btn border pl-3 pr-3 pt-1 pb-1 mt-4">목록</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
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
</style>
