<template>
  <div v-cloak style="display:flex ;justify-content: center;width: 100%;">
    <div style="width: 1000px;height: 500px;padding-top: 20px;">
      <el-row>
        <el-col :span="6">
          <el-image :src="state.avatarUrl" style="width: 200px;height: 200px;float: right;"/>
        </el-col>
        <el-col :span="18">
          <h3 style="margin-top: 5px;padding-left:20px;height: 40px;">
            <a style="border:1px solid orange ; color: orange;font-size: 15px;padding: 0 5px;border-radius: 10%; ">歌单</a>
            {{ state.playlist }}
          </h3>
          <div style="padding-left:15px; ">
            <div style="display: flex;">
              <el-image :src="state.useravatarUrl" style="width:30px;height: 30px;border-radius: 50%;"/>
              <el-image v-if="state.identityIconUrl != null" :src="state.identityIconUrl"
                        style="width:15px;height: 15px;margin-top: 15px;position:absolute;top:45px;left:285px;"/>
              <!-- TODO: 主页的跳转 -->
              <router-link :to="`/userInfo?id=${state.userId}`"
                           style="text-decoration:none;line-height: 30px; font-size: 15px; padding: 0 5px;">
                {{ state.nickname }}
              </router-link>
            </div>
            <div style="padding-top:15px;height: 105px; overflow: hidden;">
              <span>
                <el-button>
                  <a v-if="star"><IconStar/>收藏</a>
                  <a v-if="!star"><IconStar2/>已收藏</a>
                </el-button>
                <el-button>
                  <el-icon><Share/></el-icon> 分享
                </el-button>
              </span>
              <br/>
              <span style="margin-top:5px;line-height: 25px;">
                <a style="font-size:13px;">标签：</a>
                  <a v-for="tag in state.tags" :key="tag.id" href="JavaScript:;"
                     style="text-decoration: none;font-size: 13px;color: blue;"> {{ tag + "、" }}
                  </a>
                <br/>
                <a style="font-size:13px;color: black;">歌曲: {{  }} &nbsp; 播放：{{ state.playCount }} </a>
                <br/>
                <el-popover :content=" state.description " :width="500" placement="top-start" title="简介"
                            trigger="hover">
                  <template #reference>
                <a style="font-size: 13px;height: 20px;  " @mouseenter="leave"> 简介：{{ state.description }}</a>
                  </template>
                </el-popover>
              </span>
            </div>
          </div>
        </el-col>
        <el-col :span="24" style="margin-top: 40px;">
          <el-tabs v-model="activeName" class="demo-tabs" lazy>
            <el-tab-pane label="音乐列表" name="first">
              <el-table ref="table" v-loading="state.loading" :data="state.tableData" :element-loading-spinner="svg"
                        :max-height="heght"
                        element-loading-background="#fff" element-loading-svg-view-box="-10, -10, 50, 50"
                        element-loading-text="加载中..."
                        empty-text="抱歉,没有获取到数据"
                        height="550" style="width: 100%">
                <el-table-column type="index" width="50">
                </el-table-column>
                <el-table-column :show-overflow-tooltip="true" label="歌单" prop="name" width="200">
                </el-table-column>
                <el-table-column label="歌手" width="280">
                  <template #default="aaaa">
                    <div v-for="a in aaaa.row.ar" :key="a" class="author">
                      <router-link :to="`/singer?id=${a.id}`" style="float: left;text-decoration: none;color:#606266;">
                        {{ a.name + "~" }}
                      </router-link>
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
                <el-table-column :show-overflow-tooltip="true" label="专辑" prop="al.name" width="180"/>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="音乐评论" name="second">
              <div>
                <span>
                  <div v-if="state.hotComments !=null">
                  <h4>精彩评论</h4>
                  <div v-for="content in state.hotComments">
                    <div style="width:1000px;display: flex;padding:10px">
                    <el-image :src="content.user.avatarUrl"
                              style="width: auto;height:70px; border-radius: 50%;"></el-image>
                      <span style="font-size: 13px;padding: 5px;">
                        <a style="color:lawngreen">
                        {{ content.user.nickname }}</a>:
                        <a style="padding-left: 2px;">
                          {{ content.content }}
                        </a>
                      <br/>
                     <a style="color: #a1a1a0">{{ content.timeStr }}</a>
                      </span>
                    </div>
                  </div>
                    <div style="display: flex;justify-content: center">
                    <el-button style="border-radius: 50px;">查看全部评论 ↓</el-button>
                    </div>
                  </div>
                  <div style="margin-top: 10px;">
                    <h4>最新评论</h4>
                    <div v-for="content in state.comments">
                    <div style="width:1000px;display: flex;padding:10px">
                    <el-image :src="content.user.avatarUrl"
                              style="width: auto;height:70px; border-radius: 50%;"></el-image>
                      <span style="font-size: 13px;padding: 5px;">
                        <a style="color:lawngreen">
                        {{ content.user.nickname }}</a>:
                        <a style="padding-left: 2px;">
                          {{ content.content }}
                        </a>
                      <br/>
                     <a style="color: #a1a1a0">{{ content.timeStr }}</a>
                      </span>
                    </div>
                    </div>
                    <div style="display: flex;justify-content: center">
                    <el-button style="border-radius: 50px;">查看全部评论 ↓</el-button>
                    </div>
                  </div>
                </span>
              </div>
              <div>
              </div>
            </el-tab-pane>
          </el-tabs>
        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script lang="ts" setup>
import {onMounted, reactive, ref} from 'vue';
import {useRoute} from 'vue-router';
import request from '../config/request'
import IconStar2 from '../components/icon/icon-star2.vue';
import IconStar from '../components/icon/icon-star.vue';
import {ElMessage} from "element-plus";

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
request.get("playlist/track/all?id=" + id).then(res => {
  if (res.data.code == 200) {
    state.tableData = res.data.songs
  } else {
    ElMessage.error("由于接口原因,歌单获取失败")
  }
})
// 获取歌单详细信息
request.get("/playlist/detail?id=" + id + "&cookie=" + cookie).then(res => {
  if (res.data.code == 200) {
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
  } else {
    ElMessage.error("歌单详细获取失败")
  }
})
request.get("/comment/playlist?id=" + id).then(res => {
  state.hotComments = (res.data.hotComments).splice(0, 10)
  state.comments = (res.data.comments).splice(0, 15)
})
</script>
<style>
* {
  margin: 0;
  padding: 0;
}

.el-tabs__nav-wrap::after {
  background-color: #fff;
}

[v-cloak] {
  display: none;
}
</style>
