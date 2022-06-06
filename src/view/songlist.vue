<template>
  <div style="display:flex ;justify-content: center;width: 100%;">
    <div style="width: 1000px;height: 500px;padding-top: 20px;">
      <el-row>
        <el-col :span="6">
          <el-image  :src="state.avatarUrl" style="width: 200px;height: 200px;float: right;" />
        </el-col>
        <el-col :span="18">
          <h3 style="margin-top: 5px;padding-left:20px;height: 40px;">
            <a
              style="border:1px solid orange ; color: orange;font-size: 15px;padding: 0 5px;border-radius: 10%; ">歌单</a>
            {{ state.playlist }}
          </h3>
          <div style="padding-left:15px; ">
            <div style="display: flex;">
              <el-image :src="state.useravatarUrl" style="width:30px;height: 30px;border-radius: 50%;" />
              <el-image v-if="state.identityIconUrl != null" :src="state.identityIconUrl"
                style="width:15px;height: 15px;margin-top: 15px;position:absolute;top:45px;left:285px;" />
              <!-- TODO: 主页的跳转 -->
              <router-link :to="`/userInfo?id=${state.userId}`"
                style="text-decoration:none;line-height: 30px; font-size: 15px; padding: 0 5px;">{{
                    state.nickname
                }}</router-link>
            </div>
            <div style="padding-top:15px;height: 105px; overflow: hidden;">
              <span>
                <el-button><a v-if="star">
                    <IconStar />收藏
                  </a><a v-if="!star">
                    <IconStar2 />已收藏
                  </a></el-button>
                <el-button>
                  <el-icon>
                    <Share />
                  </el-icon> 分享
                </el-button>
              </span>
              <br />
              <span style="margin-top:5px;line-height: 25px;">
                <a style="font-size:13px;">标签：</a>
                <a href="JavaScript:;" v-for="tag in state.tags" :key="tag.id"
                  style="text-decoration: none;font-size: 13px;color: blue;"> {{ tag + "、" }}</a>
                <br />
                <a style="font-size:13px;color: black;">歌曲: {{ state.songlength }} &nbsp; 播放：{{ state.playCount }} </a>
                <br />
                <a style="font-size: 13px;height: 20px;">简介：{{ state.description }}</a>
              </span>
            </div>
          </div>
        </el-col>
        <el-col :span="24" style="margin-top: 40px;">
          <el-tabs lazy v-model="activeName" class="demo-tabs">
            <el-tab-pane label="音乐列表" name="first">
              <el-table :data="state.tableData" ref="table"  height="550" v-loading="state.loading"
                element-loading-text="加载中..." :element-loading-spinner="svg"
                element-loading-svg-view-box="-10, -10, 50, 50" element-loading-background="rgba(122, 122, 122, 0.8)"
                :max-height="heght" style="width: 100%">
                <el-table-column type="index" width="50">
                </el-table-column>
                <el-table-column prop="name" :show-overflow-tooltip="true" label="歌单" width="200">
                </el-table-column>
                <el-table-column label="作者(点击名字即可跳转主页)" width="280">
                  <template #default="aaaa">
                    <div v-for="a in aaaa.row.ar" :key="a" class="author">
                      <a href="javascript:;" style="float: left;text-decoration: none;color:#606266;">{{a.name+"~"}}</a>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column label="是否原唱" width="180">
                  <template #default="scope">
                    <div v-if="scope.row.originCoverType == 1">
                      原创
                    </div>
                    <div v-if="scope.row.originCoverType == 2">
                      翻唱
                    </div>
                    <div v-if="scope.row.originCoverType == 0">
                      未知
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="al.name"  :show-overflow-tooltip="true" label="专辑" width="180" />
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="音乐评论" name="second">
              <span>

              </span>
            </el-tab-pane>
          </el-tabs>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue';
import { useRoute } from 'vue-router';
import request from '../config/request'
import IconStar2 from '../components/icon/icon-star2.vue';
import IconStar from '../components/icon/icon-star.vue';
const state: any = reactive({})
state.loading = ref(true)
onMounted(() => {
  setTimeout(() => {
    state.loading = false
  }, 2000)
})
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
const activeName = ref('first')
const star = ref(true)
const heght = ref()
let route = useRoute();
const table: any = ref(null)
console.log(table)
let id = route.query.id
let cookie = localStorage.cookie
const time = new Date().getTime()
// 获取歌单的全部歌曲
request.get("/playlist/track/all?cookie="+cookie+"&id="+id).then(res => {
  state.songlength = res.data.songs.length
  state.tableData = res.data.songs
})
// 获取歌单详细信息
request.get("/playlist/detail?id=" + id +"&cookie="+cookie).then(res => {
  const playlist = res.data.playlist
  state.playlist = playlist.name
  state.avatarUrl = playlist.coverImgUrl
  state.useravatarUrl = playlist.creator.avatarUrl
  if (playlist.creator.avatarDetail) {
    state.identityIconUrl = playlist.creator.avatarDetail.identityIconUrl
  }
  state.nickname = playlist.creator.nickname
  state.playCount = playlist.playCount
  state.description = playlist.description
  state.tags = playlist.tags
  state.userId = playlist.creator.userId
})
request.get("/comment/playlist?id=" + id ).then(res=>{

})
</script>
<style>
* {
  margin: 0;
  padding: 0;
}
</style>