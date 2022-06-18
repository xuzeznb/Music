<template>
  <el-row>
    <el-col :span="12">
      <div style="margin-top: 80px">
        <div style="display: flex; justify-content: center">
          <el-image :src="usePlayerStore().song.al?.picUrl || OpticalDisk"
                    style="width: 250px;height: 240px;border-radius: 50%"></el-image>
        </div>
      </div>
    </el-col>
    <el-col :span="12">
      <el-scrollbar height="400px" style="width: 400px">
        <ul ref="lyricUL" style="list-style: none">
          <li v-for="(item, i) in lyricsObjArr" :key="item.uid"
              ref="lyricli" :data-index='i'
              :style="{color: useFormatDuring(usePlayerStore().currentTime)  === useFormatDuring(item.time) ? 'skyblue' : '#a1a1a1'}"
              style="list-style: none;font-size:12.5px">{{ item.lyric }}
          </li>
        </ul>
      </el-scrollbar>
    </el-col>
  </el-row>
</template>


<script lang="ts" setup>
import {lyric} from "../utils/api";
import {useRoute} from "vue-router";
import {formatLyricTime} from "../utils/formatLyricTime";
import {ref, watch} from "vue";
import {usePlayerStore} from "../store/player";
import {useFormatDuring} from '../utils/number'
import {OpticalDisk} from '../assets/img/'

let lyricli: any = ref(null)
let lyricUL: any = ref()
const lyricIndex: any = ref()
let id: any = useRoute().query
const lyricsObjArr: any = ref([])
const lyricsSection = ref(null)
lyric(id.id).then(res => {
  //格式化歌词的数组
  let regNewLine = /\n/
  //获取每行歌词
  const lineArr = res.lyric.split(regNewLine)
  const regTime = /\[\d{2}:\d{2}.\d{2,3}\]/
  lineArr.forEach(item => {
    if (item === '') return
    const obj: any = {}
    const time = item.match(regTime)
    obj.lyric = item.split(']')[1].trim() === '' ? '' : item.split(']')[1].trim()
    obj.time = time ? formatLyricTime(time[0].slice(1, time[0].length - 1)) : 0
    obj.uid = Math.random().toString().slice(-6)
    if (obj.lyric === '') {
      console.log('这一行没有歌词')
    } else {
      lyricsObjArr.value.push(obj)
    }
  })
  const currentRow = ref()
  const index = ref()
  watch(() => usePlayerStore().currentTime, () => {
    if (usePlayerStore().currentTime === lyricsObjArr.time) {
      usePlayerStore().currentTime = currentRow.value
      lyricsObjArr.time = index.value
    }
  })
  console.log()
})
</script>
