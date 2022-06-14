<template>
  <el-row v-cloak>
    <el-col :span="12">
      <div class="grid-content bg-purple" style="display: flex; justify-content: center; margin-top: 60px">
        <div class="auioplay">
          <div style="height: 140px">
            <div style="color: blue; display: flex; margin-left: 20px">
              <span style="margin-top: 60px">
                <br/><a> 每日推荐歌曲<br/>15首</a></span>
              <span style="
                  margin-left: 100px;
                  margin-top: 80px;
                  width: 40px;
                  height: 40px;
                  border-radius: 50%;
                  background-color: blue;">
                <IconPlayWhite style="margin-left: 10px; margin-top: 10px" @click="playall"/>
                <IconPauseWhite style="margin-left: 10px; margin-top: 10px"/>
              </span>
            </div>
          </div>
        </div>
      </div>
    </el-col>
    <el-col :span="12">
      <div style="width: 700px; margin-top: 15px">
        <div v-for="item in playlistsong" :key="item.id">
          <div style="width: 200px; height: 60px; float: left">
            <span style="padding: 10px; margin-top: 10px">
              <a href="javascript:;" style="text-decoration: none; color: black">
                <el-image :src="item.al.picUrl" lazy style="
                    width: 50px;
                    height: 50px;
                    float: left;
                    border-radius: 20%;
                  "/>
                <a @click="play(item.id)">{{ item.name }}</a>
              </a>
            </span>
          </div>
        </div>
      </div>
    </el-col>
    <el-col :span="24">
      <div style="width: 100%;">
        <div style="display: flex;width: 500px; justify-content: center;">
          <h2 style="margin-bottom: 20px;">最新歌单</h2>
        </div>
        <div style="display: flex; justify-content: center">
          <div style="width: 1300px; height: 500px">
            <div v-for="itemlist in playPlaylist" :key="itemlist.id">
              <div style="float: left; width: 250px; height: 270px">
                <el-image :src="itemlist.picUrl" lazy style="
                    width: 200px;
                    height: 200px;
                    border-radius: 10%;
                    margin-bottom: 5px;
                  "></el-image>
                <div style="height: 60px; width: 200px">
                  <router-link :to="`/songlist?id=${itemlist.id}`" href="javascript:;"
                               style="color: black; text-decoration: none">{{ itemlist.name }}
                  </router-link>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-col>
    <el-col :span="24" style="margin-top: 60px;">
      <div style="width: 100%;">
        <div style="display: flex;width: 500px; justify-content: center;">
          <h2 style="margin-bottom: 20px;">最新专辑</h2>
        </div>
        <div style="display: flex; justify-content: center">
          <div style="width: 1300px; height: 500px">
            <div v-for="items in radioPlaylist" :key="items.id">
              <div style="float: left; width: 250px; height: 270px">
                <el-image :src="items.picUrl" lazy style="
                    width: 200px;
                    height: 200px;
                    border-radius: 10%;
                    margin-bottom: 5px;
                  "></el-image>
                <div style="height: 60px; width: 200px">
                  <router-link :to="`/album?id=${items.id}`" href="javascript:;"
                               style="color: black; text-decoration: none">{{ items.name }}
                  </router-link>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-col>
  </el-row>
</template>
<script lang="ts" setup>
import {recommendedStations, userPlaylist, userSong} from "../utils/api";
import {usePlayerStore} from '../store/player'
import {ref} from "vue";
import IconPlayWhite from "../components/icon/icon-play-white.vue";
import IconPauseWhite from "../components/icon/icon-pause-white.vue";

const {play, pushPlayList} = usePlayerStore();
const playlistsong = ref()
const playPlaylist = ref()
const radioPlaylist = ref()
userSong().then(res => {
  playlistsong.value = res
})
userPlaylist().then(res => {
  playPlaylist.value = res
})
recommendedStations().then(res => {
  radioPlaylist.value = res
})
const playall = () => {
  pushPlayList(false, ...playlistsong.value)
  play(playlistsong.value.first().id)
}

</script>
<style scoped>
.auioplay {
  background-color: rgb(234, 240, 254);
  width: 300px;
  height: 140px;
  border-radius: 10%;
}

.star-song a {
  border: none;
  padding: 0 20px;
  font-size: 13px;
}
</style>
