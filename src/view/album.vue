<template>
  <div style="display:flex ;justify-content: center;width: 100%;">
    <div style="width: 1000px;height: 500px;padding-top: 20px;">
      <el-row>
        <el-col :span="6">
          <el-image :src="state.picUrl" style="width: 200px;height: 200px;float: right;"/>
        </el-col>
        <el-col :span="18">
          <h3 style="margin-top: 5px;padding-left:20px;">
            <a style="border:1px solid orange ; color: orange;font-size: 15px;padding: 0 5px;border-radius: 10%;">专辑</a>
            {{ state.title }}
          </h3>
          <div style="padding-left:20px; ">
            <div>
              <!-- TODO: 主页的跳转 -->
              <span style="font-size: 15px; ">歌手:</span>
              <router-link :to="`/singer?id=${state.id}`"
                           style="text-decoration:none;cursor:pointer; font-size: 15px; padding: 0 15px;">
                {{ state.nickname }}
              </router-link>
            </div>
            <div>
              <span style="font-size: 12px; padding-top: 10px;color: #a1a1a0">创建时间：{{
                }}</span>
            </div>
          </div>
          <div style="padding-top:15px;height: 105px; overflow: hidden;">
            <span style="padding-left: 15px;">
              <el-button><a v-show="star">
                  <IconStar/>收藏
                </a><a v-show="!star">
                  <IconStar2/>已收藏
                </a></el-button>
              <el-button>
                <el-icon>
                  <Share/>
                </el-icon> 分享
              </el-button>
            </span>
            <br/>
          </div>
        </el-col>
        <el-col :span="24" style="margin-top: 40px;">
          <el-tabs v-model="activeName" class="demo-tabs">
            <el-tab-pane label="专辑列表" name="first">
              <el-table ref="table" v-loading="state.loading" :data="state.tableData" :element-loading-spinner="svg"
                        :max-height="heght" element-loading-background="#fff"
                        element-loading-svg-view-box="-10, -10, 50, 50"
                        element-loading-text="加载中..."
                        height="550" style="width: 100%">
                <el-table-column type="index" width="50"/>
                <el-table-column :show-overflow-tooltip="true" label="歌单" prop="name" width="200">
                </el-table-column>
                <el-table-column label="作者" width="280">
                  <template #default="information">
                    <div v-for="a in information.row.ar" :key="a" class="author">
                      <a href="javascript:;" style="float: left;text-decoration: none;color:#606266;">{{
                          a.name + "~"
                        }}</a>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column :show-overflow-tooltip="true" label="专辑" prop="al.name" width="180"/>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="专辑评论" name="second">
            </el-tab-pane>
          </el-tabs>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {reactive, ref} from "vue-demi"
import {useRoute} from "vue-router"
import IconStar2 from '../components/icon/icon-star2.vue';
import IconStar from '../components/icon/icon-star.vue';
import request from "../config/request"
import {onMounted} from "vue";

const route = useRoute()
const id = route.query.id
const activeName = ref('first')
const star = ref(true)
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
onMounted(() => {
  state.loading = ref(true)
  setTimeout(() => {
    state.loading = false
  }, 2000)
})
request.get("/album?id=" + id).then(res => {
  state.title = res.data.album.name
  state.picUrl = res.data.album.picUrl
  state.nickname = res.data.album.artists[0].name
  state.id = res.data.album.artists[0].id
  state.tableData = res.data.songs
  state.publishTime = res.data.album.publishTime

})
const heght = "500px"

const state: any = reactive({
  title: '',
  tableData: [],
  publishTime: '',
  picUrl: '',
  nickname: '',
  loading: ''
})

</script>

<style scoped>
</style>
