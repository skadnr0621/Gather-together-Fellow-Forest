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
              class="w-full h-10 border pl-2 pt-2 pb-2"
              style="text"
              placeholder="제목을 입력해주세요."
            />
          </div>
          <div class="border h-56">ckEditor 들어가는자리</div>
        </div>

        <div class="flex btnLeftRightWrap w-5/6">
          <button class="btn border pl-3 pr-3 pt-1 pb-1 mt-4">목록</button>
          <div class="flex">
            <button class="btn border pl-3 pr-3 pt-1 pb-1 mt-4">등록</button>
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
export default {};
</script>

<style scoped>
#create-board {
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
