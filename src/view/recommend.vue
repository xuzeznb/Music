<template>
  <el-row v-cloak>
    <div style="width: 100%">
      <div style="width:200px;margin-top: 20px; justify-content: right;display: flex">
        <strong style="color: rgb(51,65,85); font-size: 20px; ">推荐</strong>
      </div>
      <div style="display:flex;justify-content: center">
        <div v-for="item in Carousel" style="float: left">
          <el-image :src="item.imageUrl" style="width: 330px;height: 140px;padding: 6px; border-radius:30px;"/>
        </div>
      </div>
      <div style="width:270px; justify-content: right;display: flex">
        <strong style="color:black">今日歌单推荐 ></strong>
      </div>
      <div style="display:flex;justify-content: center">
        <div v-for="items in result"
             style="padding: 10px;height: 210px;overflow: hidden;text-overflow: ellipsis;">
          <span style="height: 180px;">
          <el-image :src="items.picUrl" style="width: 150px;height: 150px;border-radius: 10%"/>
            <a style="display:flex; width: 150px">{{ items.name }}</a>
          </span>
        </div>
      </div>
      <div style="width:270px;margin-top: 10px; justify-content: right;display: flex">
        <strong style="color:black">推荐今日音乐 ></strong>
      </div>
      <div>
        <!--                <el-image></el-image>-->
      </div>
    </div>
  </el-row>
</template>
<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {carousel, recommendedPlaylist} from "../utils/api";

const result = ref()
const Carousel = ref()
onMounted(() => {
  recommendedPlaylist().then(res => {
    result.value = res
  })
  carousel().then(res => {
    Carousel.value = res
  })
})
</script>
<style scoped>
@tailwind base;
@tailwind components;
@tailwind utilities;
.el-carousel__item h3 {
  display: flex;
  color: #475669;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
