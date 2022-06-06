<template>
  <el-row>
    <el-col :span="8">
      <div style="float:right;padding: 15px;">
        <el-image :src="state.avatarUrl" style="width:200px ;height:200px;border-radius:10%;" />
      </div>
    </el-col>
    <el-col :span="16" style="padding:15px;">
      <div>
        <h4 style="width: 400px;">{{ state.title }}</h4>
        <div class="userInfodetailed" style="height: 40px;">
          <a href="javascript:;" v-if="state.sgsimg != null"
            style="background-color: rgb(253, 228, 226);padding: 2px;border-radius: 50px;">
            <el-image v-if="state.sgsimg != null" :src="state.sgsimg" style="width:15px;height: 15px;margin-top: 10px;">
            </el-image>
            <a v-if="state.sgsname != undefined">{{ state.sgsname }}</a>
          </a>
          <a href="javascript:;" style="padding-left: 10px;">
            LV{{ state.level }}
          </a>
          <a href="javascript:;" v-if="state.gender == 1" style="padding-left: 10px;">
            <el-icon color="rgb(131,204,234)" style="background-color: rgb(192,243,255);border-radius:50% ; "
              :size="13">
              <Male />
            </el-icon>
          </a>
          <a href="javascript:;" v-if="state.gender == 2" style="padding-left: 10px;">
            <el-icon color="red" style="background-color: rgb(255,205,232);border-radius:50% ; " :size="13">
              <Female />
            </el-icon>
          </a>
        </div>
        <div style="width:300px;height:40px ;">
          <el-button style="width:80px;" type="success">
            关注
          </el-button>
          <el-button style="width:80px; margin-left: 30px;" type="warning">
            举报
          </el-button>
        </div>
        <div class="userInfodetailed">
          <span>
            <a href="javascript:;">社交平台:{{}}</a>
          </span>
          <br />
          <span style="margin-top: 20px;">
            <a href="javascript:;">简介: {{ state.signature }}</a>
          </span>
        </div>
      </div>
    </el-col>
    <el-col :span="24">
      <div style="display:flex;justify-content: center; width:100%;">
        <el-tabs lazy v-model="activeName" class="demo-tabs">
          <el-tab-pane label="创建的歌单" name="first" style="width:1300px;">
            <div v-for="items in state.Create" :key="items.id" >
              <div style="float: left; width: 260px;">
                <el-image :src="items.coverImgUrl" style="
                    width: 200px;
                    height: 200px;
                    border-radius: 10%;
                    margin-bottom: 5px;
                  "></el-image>
                <div style="height: 60px; width: 200px">
                  <router-link :to="`/songlist?id=${items.id}`"
                    style="color: black; text-decoration: none">{{ items.name }}</router-link>
                </div>
              </div>
        </div>
          </el-tab-pane>
        </el-tabs>
      </div>
    </el-col>
  </el-row>
</template>
<script lang="ts" setup>
import { reactive, ref } from 'vue';
import { useRoute } from 'vue-router';
import request from '../config/request';
const activeName = ref("first")
const heght = ref("120px")
const useid = useRoute().query.id
request.get("/user/detail?uid=" + useid).then(res => {
  state.title = res.data.profile.nickname,
    state.signature = res.data.profile.signature,
    state.avatarUrl = res.data.profile.avatarUrl,
    state.gender = res.data.profile.gender
  if (res.data.identify) {
    state.sgsimg = res.data.identify.imageUrl
    state.sgsname = res.data.identify.imageDesc
  }
})
const cookie = localStorage.cookie
request.get("/user/level?cookie=" + cookie).then(res => {
  state.level = res.data.data.level
})
const recommend: any = [];
request.get('/user/playlist?uid=' + useid).then(res => {
  const list = res.data.playlist
  for (let i = 0; list[i]; i++) {
    recommend.push(list[i])
  }
  state.Create = recommend
})
const state: any = reactive({})
const svg = `
        <path class="path" d="
          M 30 15
          L 28 17
          M 25.61 25.61
          A 15 15, 0, 0, 1, 15 30
          A 15 15, 0, 1, 1, 27.99 7.5
          L 15 15
        " style="stroke-width: 4px; fill: rgba(0, 0, 0, 0)"/>
      `
</script>
<style scoped>
* {
  margin: 0;
  padding: 0;
}

.userInfodetailed a {
  color: black;
  text-decoration: none;
  font-size: 10px;
}
</style>