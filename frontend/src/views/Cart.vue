<script setup>
import {reactive} from "vue";
import {getItems, removeItem} from "@/services/cartService.js";
import {useRouter} from "vue-router";

const router = useRouter();

// 반응형 상태
const state = reactive({
  item: [],
});

// 장바구니 상품 조회
(async function getItem() {
  const res = await getItems();
  if (res.status == 200) {
    state.item = res.data;
  }
})();

// 장바구니 상품 삭제
const deleteItem = async (itemId) => {
  const res = await removeItem(itemId);
  if (res.status == 200) {
    const reload = await getItems();
    if (res.status == 200) {
      state.item = reload.data;
    }
  }
}
const orderPage = () => {
  router.push('/order')
}

// 커스텀 생성 훅

</script>

<template>
  <div class="cart">
    <div class="container">
      <template v-if="state.item.length">
        <ul class="items">
          <li v-for="item in state.item" :key="item.id">
            <img :src="item.imgPath"/>
            <b class="name">{{ item.name }}</b>
            <span class="price">{{ item.price }}원 </span>
            <span class="remove float-end" title="삭제" @click="deleteItem(item.id)">&times;</span>
          </li>
        </ul>
        <div class="act">
          <button type="submit" class="btn btn-primary" @click="orderPage">주문하기</button>
        </div>
      </template>
      <div class="text-center py-5" v-else>장바구니가 비어있습니다.</div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.cart {
  .items {
    list-style: none;
    margin: 0;
    padding: 0;

    li {
      border: 1px solid #eee;
      margin-top: 25px;
      margin-bottom: 25px;
    }

    img {
      width: 150px;
      height: 150px;
    }

    .name {
      margin-left: 25px;
    }

    .price {
      margin-left: 25px;
    }

    .remove {
      cursor: pointer;
      font-size: 30px;
      padding: 5px 15px;
    }
  }

  .act .btn {
    width: 300px;
    display: block;
    margin: 0 auto;
    padding: 30px 50px;
    font-size: 20px;
  }
}
</style>