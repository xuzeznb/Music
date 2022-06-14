<template>
  <el-row v-cloak>
    <el-col :span="8">
      <div style="float:right;padding: 15px;">
        <el-image :src="Info?.avatarUrl" style="width:200px ;height:200px;border-radius:10%;"/>
      </div>
    </el-col>
    <el-col :span="16" style="padding:15px;">
      <div>
        <h4>{{ Info?.nickname }}</h4>
        <div class="userInfodetailed" style="height: 40px;margin-top: 5px">
          <a v-if="Info?.avatarDetail != null"
             style="background-color: rgb(253, 228, 226);padding: 2px;border-radius: 50px;">
            <el-image :src="Info?.avatarDetail.identityIconUrl" lazy
                      style="width:15px;height: 15px;">
            </el-image>
            <a style="padding-left: 10px">{{ Info?.mainAuthType.desc }}</a>
          </a>
          <a v-show="Level" href="javascript:;" style="padding-left: 10px;
            border-radius: 10px">
            LV{{ Level?.level }}
          </a>
          <a v-show="Info?.gender === 1" href="javascript:;" style="padding-left: 10px;">
            <el-icon :size="13" color="rgb(131,204,234)"
                     style="background-color: rgb(192,243,255);border-radius:50% ; ">
              <Male/>
            </el-icon>
          </a>
          <a v-show="Info?.gender === 2" href="javascript:;" style="padding-left: 10px;">
            <el-icon :size="13" color="red" style="background-color: rgb(255,205,232);border-radius:50% ; ">
              <Female/>
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
          <br/>
          <span style="margin-top: 20px;">
                    <a href="javascript:;">简介: {{ Info?.signature }}</a>
          </span>
        </div>
      </div>
    </el-col>
    <el-col :span="24">
      <div style="display:flex;justify-content: center; width:100%;">
        <el-tabs v-model="activeName" class="demo-tabs" lazy>
          <el-tab-pane label="创建的歌单" name="first" style="width:1300px;">
            <div v-for="items in playlist" :key="items.id">
              <div style="float: left; width: 260px;">
                <el-image :src="items.coverImgUrl" lazy style="
                        width: 200px;
                        height: 200px;
                        border-radius: 10%;
                        margin-bottom: 5px;
                      "></el-image>
                <div style="height: 60px; width: 200px">
                  <router-link :to="`/songlist?id=${items.id}`"
                               style="color: black; text-decoration: none">{{ items.name }}
                  </router-link>
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
import {ref} from 'vue';
import {useRoute} from 'vue-router';
import {personalPlaylist, singerInformation, userLevel} from "../utils/api";

const Info = ref()
const activeName = ref("first")
const useId: any = useRoute().query.id
const Level = ref()
const playlist = ref()
singerInformation(useId).then(res => {
  Info.value = res
})
userLevel(useId).then(res => {
  Level.value = res
})
personalPlaylist(useId).then(res => {
  playlist.value = res
})
// request.get("/user/detail?uid=" + useid).then(res => {
//   state.title = res.data.profile.nickname,
//     state.signature = res.data.profile.signature,
//     state.avatarUrl = res.data.profile.avatarUrl,
//     state.gender = res.data.profile.gender
//   if (res.data.identify) {
//     state.sgsimg = res.data.identify.imageUrl
//     state.sgsname = res.data.identify.imageDesc
//   }
// })
// const cookie = localStorage.cookie
// request.get("/user/level?cookie=" + cookie).then(res => {
//   state.level = res.data.data.level
// })
// const recommend: any = [];
// request.get('/user/playlist?uid=' + useid).then(res => {
//   const list = res.data.playlist
//   for (let i = 0; list[i]; i++) {
//     recommend.push(list[i])
//   }
//   state.Create = recommend
// })
// const state: any = reactive({})
/*const svg = `*/
//         <path class="path" d="
//           M 30 15
//           L 28 17
//           M 25.61 25.61
//           A 15 15, 0, 0, 1, 15 30
//           A 15 15, 0, 1, 1, 27.99 7.5
//           L 15 15
//         " style="stroke-width: 4px; fill: rgba(0, 0, 0, 0)"/>
//       `
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
