<script setup>
import {getItems} from "@/services/itemService";
import Card from "@/components/Card.vue";
import {reactive} from "vue";

// 반응형 상태 : state 객체
// 내부에 상품 목록인 items 배열, 해당 배열에 데이터가 추가되거나,
// 삭제되면 자동으로 UI 에 반영
const state = reactive({
  items: [],
});

//커스텀 생성 훅 : 컴포넌트에서 라이프사이클 훅 중 가장 먼저 실행되는 훅이어서
// 상품 서비스의 상품 목록을 해당 뷰가 렌더링 되기 전에 데이터를 먼저 셋팅하기 위해 커스텀 함
// getItems() 메서드를 호출하고 리턴 데이터를 state.items 에 저장
(async function onCreated() {
  const res = await getItems();
  if (res.status === 200) {
    state.items = res.data;
    console.log(res.data);
  }
})();


</script>

<template>
  <div class="home">
    <!--상품 목록을 출력하는 요소-->
    <div class="album py-5 bg-light">
      <div class="container">
        <div class="row row-cols-1 row-cols-2 row-cols-3">
          <div class="col" v-for="item in state.items">
            <Card :item="item" imgPath="imgPath"/>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>
