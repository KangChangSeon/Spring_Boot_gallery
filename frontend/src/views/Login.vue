<script setup>
import {reactive} from "vue";
import {login} from "@/services/accountService"
import {useRouter} from "vue-router";

const state = reactive({
  form: {
    loginId : "",
    loginPw : ""
  }
});

const router = useRouter();

const submit = async () => {
  const res = await login(state.form);

  if (res.status === 200) {
    window.alert("로그인 성공");
    await router.push("/");
  }
}
</script>

<template>
  <div class="login">
    <div class="container">
      <form class="py-5 d-flex flex-column gap-3" @submit.prevent="submit">
        <h1 class="h5 mb-3">로그인</h1>
        <div class="form-floating">
          <input type="text" class="form-control" id="loginId" placeholder="이메일" v-model="state.form.loginId"/>
          <label for="name">이메일</label>
        </div>
        <div class="form-floating">
          <input type="password" class="form-control" id="loginPw" placeholder="비밀번호" v-model="state.form.loginPw"/>
          <label for="name">비밀번호</label>
        </div>
        <button type="submit" class="w-100 h6 btn py-3 btn-primary">로그인</button>
      </form>
    </div>
  </div>
</template>
<style lang="scss" scoped>

</style>