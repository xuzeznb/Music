import {createStore} from "vuex";

// @ts-ignore
export default createStore({
  state: {
    //音乐播放
    onplay: false,
    //音乐暂停
    onPause: true,
    //歌曲id
    id: 0,
    //歌曲url
    url: '',
    //自动播放
    autoplay: false,
    //音乐列表播放方式：1.歌曲循环；2.歌曲随机；3.单曲循环
    playType: 1,
    //歌词
    lyric: '',
    playing: true, //播放状态：true或false,暂停
    playList: [],//歌曲列表
    currentTime: 0,//当前播放时间
    duration: 0,//总时长
    ended: false,//是否播放结束
    muted: false,//是否静音
  },
  mutations: {
    musicID(state, value) {
      state.id = value
    },
    musicUrl(state, value) {
      state.url = value
    },

  },
  actions: {}
})
