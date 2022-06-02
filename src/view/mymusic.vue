<template>
  <el-row>
    <el-col :span="12">
      <div class="grid-content bg-purple" style="display:flex;justify-content: center;margin-top: 60px">
        <div class="auioplay">
          <div style="height:140px;">
            <div style="color: blue;display: flex; margin-left: 20px;">
              <span style="margin-top:60px;"> <br /><a> 每日推荐歌曲<br />15首</a></span>
              <span style="margin-left:100px;margin-top:80px;width: 40px;height: 40px;
                            border-radius: 50%; background-color: blue;">
                <iconPlay v-if="play" style="margin-left: 10px;margin-top: 10px;" />
              </span>
            </div>
          </div>
        </div>
      </div>
    </el-col>
    <el-col :span="12">
      <div style=" width: 700px;margin-top: 15px;">
        <div v-for="item in state.songslist">
          <div style="width: 200px;height: 60px;float: left;">
            <span style="padding:10px;margin-top: 10px;">
              <a href="javascript:;" style="text-decoration:none;color: black;  ">
                <el-image :src="item.al.picUrl" style="width: 50px;height: 50px;float: left;border-radius: 20%;" />
                {{ item.name }}
              </a>
            </span>
          </div>
        </div>
      </div>
    </el-col>
    <el-col :span="24">
      <div>
        <h2 style="width: 350px;display: flex;justify-content: center;">歌单推荐</h2>
        <div style="display:flex ;justify-content: center;">
          <div style="width: 1500px;height: 500px;">
            <div v-for="itemlist in state.sentlist">
              <div style="display:flex;justify-content: center; float: left;width: 300px;">
                <span style="width:200px;">
                  <el-image :src="itemlist.picUrl" style="width:200px;height: 200px;border-radius: 10%;margin-bottom: 5px;"></el-image>
                  <a href="javascript:;" style="color: black;text-decoration: none;padding: 0 15px;">{{ itemlist.name }}</a>
                </span>
              </div>
            </div>
          </div>
        </div>

      </div>
    </el-col>
  </el-row>
</template>
<script lang="ts">
import { reactive, ref } from 'vue';
import iconPlay from '../components/icon/icon-play.vue';
import request from '../config/request';
import store from '../store';
export default {
  components: {
    iconPlay
  },
  setup() {
    // 每日推荐播放
    const play = ref(true)
    // 获取本地保存的用户信息
    const userInfo: any = JSON.parse(JSON.stringify(store.state.userInfo))
    // console.log(JSON.parse(JSON.stringify(store.state.userInfo)))
    // 获取每日推荐歌单 * 需要登录
    const recommend: any = []
    request.get("/recommend/songs").then(res => {
      let a = res.data.data.dailySongs
      for (let i = 0; a[i]; i++) {
        recommend.push(a[i])
      }
      state.songslist = recommend.splice(0, 15)
    })
    // 获取每日推荐歌单 * 需要登录
    const reclist: any = []
    request.get("/recommend/resource").then(res => {
      let b = res.data.recommend
      for (let i = 0; b[i]; i++) {
        reclist.push(b[i])
      }
      state.sentlist = reclist.splice(0, 10)
    })
    const state = reactive({
      songslist: '',
      sentlist: ''
    })
    return {
      play,
      state,
    }
  }
}
</script>
<style scoped>
.auioplay {
  background-color: rgb(234, 240, 254);
  width: 300px;
  height: 140px;
  border-radius: 10%;
}
</style>