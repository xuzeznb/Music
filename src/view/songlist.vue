<template>
  <div v-cloak style="display:flex ;justify-content: center;width: 100%;">
    <div style="width: 900px;height: 500px;padding-top: 20px;">
      <el-row>
        <el-col :span="6">
          <el-image :src="playlistDetails?.coverImgUrl" lazy style="width: 200px;height: 200px;float: right;"/>
        </el-col>
        <el-col :span="18">
          <h3 style="margin-top: 5px;padding-left:20px;height: 40px;">
            <a style="border:1px solid orange ; color: orange;font-size: 15px;padding: 0 5px;border-radius: 10%; ">歌单</a>
            {{ playlistDetails?.name || '未知名歌单' }}
          </h3>
          <div style="padding-left:15px; ">
            <div style="display: flex;">
              <el-image :src="playlistDetails?.creator.avatarUrl" lazy
                        style="width:30px;height: 30px;border-radius: 50%;"/>
              <el-image
                  v-if="playlistDetails?.creator.avatarDetail"
                  :src="playlistDetails?.creator.avatarDetail.identityIconUrl" lazy
                  style="width:15px;height: 15px;margin-top: 15px;position:absolute;top:45px;left:260px;"/>
              <router-link :to="`/userInfo?id=${playlistDetails?.userId}`"
                           style="text-decoration:none;line-height: 30px; font-size: 15px; padding: 0 5px;">
                {{ playlistDetails?.creator.nickname || '未知名歌手' }}
              </router-link>
            </div>
            <div style="padding-top:15px;height: 105px; overflow: hidden;">
              <span>
                <el-button @click="playAll">
                  <small><IconPlayWhite/>播放全部</small>
                </el-button>
                <el-button>
                  <a v-show="star"><IconStar/>收藏</a>
                  <a v-show="!star"><IconStar2/>已收藏</a>
                </el-button>
                <el-button>
                  <el-icon><Share/></el-icon> 分享
                </el-button>
              </span>
              <br/>
              <span style="margin-top:5px;line-height: 25px;">
                <a style="font-size:13px;">标签：</a>
                  <a v-for="tag in playlistDetails?.tags" :key="tag.id" href="JavaScript:;"
                     style="text-decoration: none;font-size: 13px;color: black"> {{ "#" + tag }}
                  </a>
                <br/>
                <a style="font-size:13px;color: black;">歌曲: {{
                    playlistDetails?.trackCount
                  }} &nbsp; 播放：{{ useNumberFormat(playlistDetails?.playCount) }} </a>
                <br/>
                <el-popover :content=" playlistDetails?.description " :width="500" placement="top-start" title="简介"
                            trigger="hover">
                  <template #reference>
                    <a style="font-size: 13px;height: 20px;" @mouseenter="leave"> 简介：{{
                        playlistDetails?.description
                      }}</a>
                  </template>
                </el-popover>
              </span>
            </div>
          </div>
        </el-col>
        <el-col :span="24" style="margin-top: 40px;">
          <el-tabs v-model="activeName" class="demo-tabs" lazy>
            <el-tab-pane :label="'歌曲'+ playlistDetails?.trackCount || '歌单' " name="first">
              <el-table ref="table" v-loading="loading" :data="songs" :element-loading-spinner="svg"
                        :highlight-current-row="true"
                        :max-height="height" element-loading-background="#fff"
                        element-loading-svg-view-box="-10, -10, 50, 50" element-loading-text="加载中..."
                        empty-text="列表为空！"
                        height="480"
                        ighlight-current-row="true"
                        style="width: 100%" @cell-dblclick="broadcast">
                <el-table-column type="index" width="50">
                </el-table-column>
                <el-table-column :show-overflow-tooltip="true" label="歌单" width="260">
                  <template #default="Song">
                    <div>
                      <a v-show="Song.row.originCoverType === 1"
                         style="border: 1px solid #c05a5a; user-select: none;font-size:10px; color: #c05a5a;padding: 1px">原唱</a>
                      <a v-show="Song.row.originCoverType === 2"
                         style="border: 1px solid orange;color: orange; user-select: none;font-size:10px; padding: 1px">翻唱</a>
                      <a v-show="Song.row.originCoverType === 0"
                         style="border: 1px solid #a1a1a0;color: #a1a1a0;padding: 1px; user-select: none;font-size:10px;">未知</a>
                      <small style="padding-left: 5px;  user-select: none;">{{ Song.row.name }}</small>
                    </div>
                    <div>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column label="歌手" width="280">
                  <template #default="aaaa">
                    <div>
                      <router-link :to="`/singer?id=${aaaa.row.ar.id}`"
                                   style="user-select: none; float: left;text-decoration: none;color:#606266;">
                        <small> {{ aaaa.row.ar?.first().name }}</small>
                      </router-link>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column :show-overflow-tooltip="true" label="专辑"
                                 width="180">
                  <template #default="album">
                    <div>
                      <small>
                        {{ album.row.al.name }}
                      </small>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column label="时间" width="120">
                  <template #default="Time">
                    <small style=" user-select: none;"> {{ useFormatDuring(Time.row.dt / 1000) }}</small>
                  </template>
                </el-table-column>
              </el-table>
            </el-tab-pane>
            <el-tab-pane label="音乐评论" name="second">
              <div v-for="item in commentArea?.comments" style="display:flex;padding: 10px;">
                <el-image :src="item.user?.avatarUrl" style="width: 60px;height: 60px;border-radius: 50%"></el-image>
                <small style="margin-top: 15px;padding:0 5px "><a style="color: blue">{{ item.user?.nickname }}:</a>>
                  {{ item?.content }}
                  <br/>
                  <small style="color:#a1a1a0;">{{ item?.timeStr }}</small>
                </small>
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
import {ref} from 'vue';
import {useRoute} from 'vue-router';
import {useFormatDuring, useNumberFormat} from '../utils/number'
import IconStar2 from '../components/icon/icon-star2.vue';
import IconStar from '../components/icon/icon-star.vue';
import {comment, playlistInformation, playlistSong,} from "../utils/api";
import {useUserStore} from "../store/user";
import {usePlayerStore} from '../store/player'
import IconPlayWhite from "../components/icon/icon-play-white.vue";

const {play, pushPlayList} = usePlayerStore()

const {openLoad} = useUserStore()

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
const height = ref(null)
const playAll = () => {
  pushPlayList(false, ...songs.value)
  play(songs.value.first().id)
}
let route = useRoute();
let id: any = route.query.id
// 获取歌单的全部歌曲
const songs = ref()
//获取作者信息
const playlistDetails = ref()
//评论
const loading = ref()
const commentArea = ref()
loading.value = openLoad()
playlistSong(id).then(res => {
  songs.value = res
  loading.value = false
})
const broadcast = (row: any, column: any, cell: any, event: any) => {
  play(row.id)
}
playlistInformation(id).then(res => {
  playlistDetails.value = res
})
comment(id).then(res => {
  commentArea.value = res
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
</style>
