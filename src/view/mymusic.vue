<template>
  <el-row v-if="cookie!=null">
    <el-col :span="12">
      <div class="grid-content bg-purple" style="display: flex; justify-content: center; margin-top: 60px">
        <div class="auioplay">
          <div style="height: 140px">
            <div style="color: blue; display: flex; margin-left: 20px">
              <span style="margin-top: 60px">
                <br /><a> 每日推荐歌曲<br />15首</a></span>
              <span style="
                  margin-left: 100px;
                  margin-top: 80px;
                  width: 40px;
                  height: 40px;
                  border-radius: 50%;
                  background-color: blue;
                ">
                <iconPlay v-if="play" @click="playMusic" style="margin-left: 10px; margin-top: 10px" />
                 <audio :ref="audio" :src="songUrl"></audio>
              </span>
            </div>
          </div>
        </div>
      </div>
    </el-col>
    <el-col :span="12">
      <div style="width: 700px; margin-top: 15px">
        <div v-for="item in state.songslist" :key="item.id">
          <div style="width: 200px; height: 60px; float: left">
            <span style="padding: 10px; margin-top: 10px">
              <a href="javascript:;" style="text-decoration: none; color: black">
                <el-image :src="item.al.picUrl" style="
                    width: 50px;
                    height: 50px;
                    float: left;
                    border-radius: 20%;
                  " />
                <a @click="songplay(item.al.id)">{{ item.name }}</a>
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
            <div v-for="itemlist in state.sentlist" :key="itemlist.id">
              <div style="float: left; width: 250px; height: 270px">
                <el-image :src="itemlist.picUrl" style="
                    width: 200px;
                    height: 200px;
                    border-radius: 10%;
                    margin-bottom: 5px;
                  "></el-image>
                <div style="height: 60px; width: 200px">
                  <router-link :to="`/songlist?id=${itemlist.id}`" href="javascript:;"
                    style="color: black; text-decoration: none">{{ itemlist.name }}</router-link>
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
            <div v-for="items in state.Album" :key="items.id">
              <div style="float: left; width: 250px; height: 270px">
                <el-image :src="items.picUrl" style="
                    width: 200px;
                    height: 200px;
                    border-radius: 10%;
                    margin-bottom: 5px;
                  "></el-image>
                <div style="height: 60px; width: 200px">
                  <router-link :to="`/album?id=${items.id}`" href="javascript:;"
                    style="color: black; text-decoration: none">{{ items.name }}</router-link>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-col>
  </el-row>
</template>
<script lang="ts">
import { reactive, ref } from "vue";
import iconPlay from "../components/icon/icon-play.vue";
import request from "../config/request";
import store from "../store";
import music from "../components/music.vue";
import { useRouter } from 'vue-router';
import IconDownload from "../components/icon/icon-download.vue";
export default {
  components: {
    IconDownload,
    iconPlay,
    music,
  },
  setup(context: any, props: any) {
    const songplay = (id: any) => { };
    // 每日推荐播放
    const play = ref(true);
    // 获取本地保存的用户信息
    const userInfo: any = JSON.parse(JSON.stringify(store.state.userInfo));
    // console.log(JSON.parse(JSON.stringify(store.state.userInfo)))
    // 获取每日推荐歌单 * 需要登录
    const recommend: any = [];
    const cookie = localStorage.cookie;
    request.get("/recommend/songs?cookie=" + cookie).then((res) => {
      let a = res.data.data.dailySongs;
      for (let i = 0; a[i]; i++) {
        recommend.push(a[i]);
      }
      state.songslist = recommend.splice(0, 15);
    });
    // 获取每日推荐歌单 * 需要登录
    const reclist: any = [];
    request.get("/recommend/resource?cookie=" + cookie).then((res) => {
      let b = res.data.recommend;
      for (let i = 0; b[i]; i++) {
        reclist.push(b[i]);
      }
      state.sentlist = reclist.splice(0, 10);
    });
    request.get("/album/newest").then(res=>{
      const a = res.data.albums
      state.Album=a.splice(0,10)
    })
    const audio:any = ref(null)
    //音乐播放
    const playMusic = ()=>{
      store.commit('play')
    }
    const musicPause = () => {
      store.commit('pause')
    }
    const state: any = reactive({
      songslist: "",
      sentlist: "",
      songid: "",
      Album:''
    });
    return {
      play,
      state,
      songplay,
      cookie,
      playMusic,
      musicPause,
    };
  },
};
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
