<template>
  <div style="height: 60px;width: 100%;display: flex;justify-content: center;">
    <el-col :span="8" style="display:flex;justify-content: center;">
      <h2 style="line-height: 30px;">网易云音乐</h2>
    </el-col>
    <el-col :span="8">
      <div style="line-height: 60px;">
        <router-link>我的</router-link>
        <span>推荐</span>
        <span>动态</span>
      </div>
    </el-col>
    <el-col :span="8" style="line-height:60px;">

      <span style="width:80px;">
        <el-input style="width:150px;" placeholder="请输入搜索的歌曲"></el-input>
      </span>
    </el-col>
  </div>

</template>
<script setup lang="ts">
// 传值模板
// defineProps<{msg:string}>()
import { ref } from 'vue'
defineProps<{ msg: string }>()

const activeIndex = ref('1')
const activeIndex2 = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const count = ref(0);
</script>
<style scoped>
span {
  padding: 20px;
}
</style>
