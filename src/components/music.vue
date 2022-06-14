<template>
  <el-row>
    <el-col :span="24" style="width: 100%;height: 50px; background-color: #ffffff">
      <el-col :span="8">
        <div style="margin-left: 250px;display: flex">
          <el-image :src="usePlayerStore().song.al?.picUrl || OpticalDisk"
                    style="width: 50px; height: 50px;border-radius:10px;"/>
        </div>
      </el-col>
      <el-col :span="8">
        <small style="padding-left: 10px;">{{ usePlayerStore().song?.name || '免费音乐' }}
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
      </el-col>
      <el-col :span="8" style="margin-left: 60px">
        <div class="slider-demo-block" style="width: 500px">
          <small class="demonstration">{{ useFormatDuring(usePlayerStore().currentTime) }}/{{
              useFormatDuring(usePlayerStore().duration)
            }}</small>
          <el-slider v-model="usePlayerStore().currentTime" :input="whileSwiping" :max="usePlayerStore().duration"
                     :show-tooltip="false" size="small" @change="slide"/>
        </div>
      </el-col>
    </el-col>
  </el-row>
</template>
<style scoped>
</style>
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

const {toggleLoop, setPause, setPlay, next, prev, onSliderChange, onSliderInput, donwnload} = usePlayerStore()

const slide = (val: number) => {
  onSliderChange(val)
}
const whileSwiping = (val: number) => {
  onSliderInput(val)
}
const songDownload = (id: number) => {
  donwnload()
}
</script>
