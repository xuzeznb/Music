<template>
  <el-row v-cloak>
    <el-col :span="8" style="display:flex ; justify-content: right;margin-top: 20px;">
      <el-space alignment="flex-start" direction="vertical">
        <el-skeleton :loading="loading" :throttle="500" animated style="width: 200px">
          <template #template>
            <el-skeleton-item style="width: 200px; height: 200px" variant="image"/>
          </template>
          <template #default>
            <img
                :src="state.avatarUrl"
                class="image"
                style="width:200px;height: 200px"
            />
          </template>
        </el-skeleton>
      </el-space>
    </el-col>
    <el-col :span="26">
      <div style="margin-top: 20px;padding-left: 10px">
        <h2>{{ state.name }}</h2>
        <div>
          <el-button style="border-radius:40px;" @click="star">
            <el-icon>
              <Star/>
            </el-icon>
            收藏
          </el-button>
          <el-button style="border-radius:40px;" @click="home">
            <router-link :to="`/userInfo?id=${id}`" style="color: black;text-decoration: none">
              <el-icon>
                <User/>
              </el-icon>
              个人主页
            </router-link>
          </el-button>
        </div>
        <div style="margin-top: 20px;font-size: 13px">
          <a>单曲数:{{}}</a>
          <a style="padding-left: 40px;">专辑数:{{}}</a>
        </div>
      </div>
    </el-col>
    <el-col :span="24">
      <div style="margin-top: 20px;display: flex;justify-content: center">
        <el-tabs v-model="activeName" class="demo-tabs" style="width: 1100px;">
          <el-tab-pane label="专辑" name="first">
            <div style="">
              <h3>热门50首
                <el-icon style="margin-left: 20px;">
                  <VideoPlay/>
                </el-icon>
                <el-icon style="margin-left: 10px">
                  <Star/>
                </el-icon>
              </h3>
              <el-table
                  ref="singleTableRef"
                  :data="state.Create"
                  highlight-current-row
                  style="width:500px"
              >
                <el-table-column type="index" width="50"/>
                <el-table-column label="操作" width="100">
                  <a href="javascript:;">
                    <icon-like/>
                  </a>
                  <a href="javascript:;" style="padding-left: 8px" @click="download">
                    <icon-download/>
                  </a>
                </el-table-column>
                <a href="javascript:;" @click="like"></a>
                <el-table-column :show-overflow-tooltip="true" label="歌名" property="name" width="150"/>
                <el-table-column property="address"/>
              </el-table>
              <div style="color: #a1a1a0;font-size: 13px;width: 500px;text-align: center">
                <a v-show="state.wholesong" style="cursor:pointer" @click="whole">查看全部50首 </a>
              </div>
            </div>
          </el-tab-pane>
          <el-tab-pane label="MV" name="second">MV</el-tab-pane>
          <el-tab-pane label="歌手详情" name="third">歌手详情</el-tab-pane>
          <el-tab-pane label="相似歌手" name="fourth">相似歌手</el-tab-pane>
        </el-tabs>
      </div>
    </el-col>
  </el-row>
</template>
<script lang="ts" setup>
import {reactive, ref} from 'vue'
import request from "../config/request";
import {useRoute} from "vue-router";
import IconLike from "../components/icon/icon-like.vue";
import IconDownload from "../components/icon/icon-download.vue";

const loading = ref(true)
const activeName = ref("first")
const currentDate: string = new Date().toDateString()
const id = useRoute().query.id
const state: any = reactive({})
const recommend: any = []
const cookie = localStorage.cookie

setTimeout(() => {
  loading.value = false
}, 3000)

request.get("/artist/detail?id=" + id).then(res => {
  const reslist = res.data.data
  state.name = reslist.artist.name
  state.avatarUrl = reslist.user.avatarUrl
})

request.get("/artist/top/song?id=" + id).then(res => {
  const list = res.data.songs
  for (let i = 0; list[i]; i++) {
    recommend.push(list[i])
  }
  state.Create = recommend.splice(0, 10)
})

const whole = () => {
  state.Create = recommend.splice(0, 50)
  state.wholesong = ref(false)
}
state.wholesong = ref(true)


const star = () => {
}

const home = () => {
}

</script>
