<template>
  <el-row style="height: 80px">
    <el-col :span="24" style="width: 100%;height: 80px;background-color: #ffffff">
      <el-col :span="6" style="margin-top: 20px">
        <div class="divimage" @Mouseover="Mouseover()" @mouseleave="Mouseleave()">
          <el-image :src="usePlayerStore().song.al?.picUrl || OpticalDisk"></el-image>
          <transition name="fade">
            <IconUp v-if="showIcon" style="position:absolute; left: 261px;top: 30px; cursor: pointer; transition: 0.5s;"
                    @click="openlyrics(usePlayerStore().id)"/>
          </transition>
        </div>
      </el-col>
      <el-col :span="6" style="margin-top: 20px">
        <div>
          <small style="padding-left: 10px;">{{ usePlayerStore().song?.name || '个性音乐' }}
            -
            <small style="color: #a1a1a0">{{ usePlayerStore().song.ar?.first().name || '小麦' }}</small>
          </small>
          <div style="margin-top: 5px;padding-left: 10px">
            <span style="padding-right:10px" @click="next()"><IconPreviousSong/></span>
            <span style="padding-right:10px ">
            <a v-if="!usePlayerStore().isPlaying" @click="setPlay()"><IconPlay/></a>
            <a v-show="usePlayerStore().isPlaying" @click="setPause()"><IconPause/></a>
          </span>
            <span @click="prev()"><IconNextSong/></span>
            <span v-show="usePlayerStore().loopType === 0" style="padding-left: 10px;cursor:pointer"
                  @click="toggleLoop()"><IconSingleCycle/> </span>
            <span v-show="usePlayerStore().loopType === 1" style="padding-left: 10px;cursor:pointer"
                  @click="toggleLoop()"><IconListLoop/></span>
            <span v-show="usePlayerStore().loopType === 2" style="padding-left: 10px;cursor:pointer"
                  @click="toggleLoop()"><IconShufflePlayback/></span>
            <span style="padding-left: 10px"><IconLike/></span>
            <span style="padding-left: 10px" @click="songDownload(usePlayerStore().id)"><IconDownload/></span>
          </div>
        </div>
      </el-col>
      <el-col :span="6" style="margin-left: 60px;margin-top: 20px; display: flex">
        <div class="slider-demo-block" style="width: 500px">
          <small class="demonstration">{{ useFormatDuring(usePlayerStore().currentTime) }}/{{
              useFormatDuring(usePlayerStore().duration)
            }}</small>
          <el-slider v-model="usePlayerStore().currentTime" :input="whileSwiping" :max="usePlayerStore().duration"
                     :show-tooltip="false" size="small" @change="slide"/>
        </div>
      </el-col>
      <el-col :span="6" style="width: 500px; margin-top: 20px;">
        <div style="float: right;line-height: 50px">
          <IconPlaylistMusic style="cursor: pointer" @click="MusicList"/>
          <el-drawer v-model="visible" :show-close="false" size="20%">
            <template #header="{ close, titleId, titleClass }">
              <h4 :id="titleId" :class="titleClass">音乐播放列表</h4>
              <el-button type="success" @click="empty">清空列表</el-button>
            </template>
            <div>
              <el-table ref="table" :data="usePlayerStore().playList"
                        empty-text="列表为空！"
                        style="width: 100%" @cell-dblclick="broadcast">
                <el-table-column type="index" width="50"/>
                <el-table-column :show-overflow-tooltip="true" label="歌名" width="150">
                  <template #default="title">
                    <small>{{ title.row.name }}</small>
                  </template>
                </el-table-column>
                <el-table-column label="时间" width="80">
                  <template #default="Time">
                    <small> {{ useFormatDuring(Time.row.dt / 1000) }}</small>
                  </template>
                </el-table-column>
              </el-table>
            </div>
          </el-drawer>
        </div>
      </el-col>
    </el-col>
  </el-row>
</template>
<script lang="ts" setup>
import IconPlay from "./icon/icon-play.vue";
import IconPause from "./icon/icon-pause.vue";
import IconNextSong from "./icon/icon-nextSong.vue";
import IconPreviousSong from "./icon/icon-previousSong.vue";
import {usePlayerStore} from '../store/player'
import {OpticalDisk} from "../assets/img";
import IconListLoop from "./icon/icon-listLoop.vue";
import IconShufflePlayback from "./icon/icon-shufflePlayback.vue";
import IconSingleCycle from "./icon/icon-singleCycle.vue";
import {useFormatDuring} from '../utils/number'
import IconDownload from "./icon/icon-download.vue";
import IconLike from "./icon/icon-like.vue";
import {ref} from "vue";
import IconPlaylistMusic from "./icon/icon-playlistMusic.vue";
import IconUp from "./icon/icon-up.vue";
import router from "../router";

const {
  toggleLoop,
  setPause,
  setPlay,
  next,
  prev,
  onSliderChange,
  onSliderInput,
  donwnload,
  clearPlayList,
  play
} = usePlayerStore()
const showIcon = ref(false)
const Mouseover = () => {
  // console.log("开始悬浮")
  showIcon.value = true
}
const Mouseleave = () => {
  // console.log("离开")
  showIcon.value = false
}
const openlyrics = (id: number) => {
  router.push({path: '/songDetails', query: {id: id}})
}
const drawer = ref(false)
const slide = (val: number) => {
  onSliderChange(val)
}
const whileSwiping = (val: number) => {
  onSliderInput(val)
}
const songDownload = (id: number) => {
  donwnload()
}
const visible = ref(false)
const MusicList = () => {
  visible.value = true
}
const broadcast = (row: any) => {
  play(row.id)
}
const empty = () => {
  clearPlayList();
}
</script>
<style scoped>
.divimage {
  margin-left: 250px;
  display: flex;
}

.divimage .el-image {
  width: 50px;
  height: 50px;
  border-radius: 10px;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}

.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */
{
  opacity: 0;
  transition: opacity 0.5s;
}
</style>
