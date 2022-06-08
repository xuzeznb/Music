import {createStore} from "vuex";

export default createStore({
    state:{
        audio:{
            'id':0,
            'name':'歌曲名称',
            'singer':'演唱者',
            //  播放器照片位置
            'albumPic':'',
            // 位置
            'location':'',
            'album':''
        },

        //音乐播放
            onplay:false,
            //音乐暂停
            onPause:true,
            //音乐播放
            autoplay:false,
        //    音乐列表播放方式：1.歌曲循环；2.歌曲随机；3.单曲循环
            playType:1,
            lyric:'',
            currentIndex:0, //当前播放的歌曲位置
            playing:false, //播放状态：true或false,暂停
            loading:false,//是否正在加载
            showDetail:false,
            songList:[],//歌曲列表
            currentTime:0,//实时时间
            tmpCurrentTime:0,//当前时间
            durationTime:0,//持续时间
            bufferedTime:0, //缓冲时间
            change:false
    },
    getters:{
      audio:state => state.audio,
      playing:state => state.playing,
      loading:state => state.loading,
      showDetail:state => state.showDetail,
      durationTime: state => state.durationTime,
      currentIndex: state => state.currentIndex,
      bufferedTime: state => state.bufferedTime,
      tmpCurrentTime: state => state.tmpCurrentTime,
      songList: state => state.songList,
      playType: state => state.playType,
      change: state => state.change,
      currentTime: state => state.currentTime,
      prCurrentTime: state => {
          return state.currentTime / state.durationTime * 100;
      },
      prBufferedTime: state => {
         return state.bufferedTime / state.durationTime * 100;
      }
    },
    mutations:{
    // 播放
    play (state) {
        state.playing = true;
    },
    // 暂停
    pause (state) {
        state.playing = false;
    },
    // 切换至音乐播放界面
    toggleDetail (state) {
        state.showDetail = !state.showDetail;
    },
    // 设置播放音乐
    setAudio (state) {
        state.audio = state.songList[state.currentIndex - 1];
    },
    setAudioIndex (state, index) {
        state.audio = state.songList[index];
        state.currentIndex = index + 1;
    },
    removeAudio (state, index) {
        state.songList.splice(index, 1);
        if (index === state.songList.length) {
            index--;
        }
        state.audio = state.songList[index];
        state.currentIndex = index + 1;
        if (state.songList.length === 0) {
            state.audio = {
                'id': 0,
                'name': '歌曲名称',
                'singer': '演唱者',
                'albumPic': '/static/player-bar.png',
                'location': '',
                'album': ''
            };
            state.playing = false;
        }
    },
    removeList (state) {
        state.songList.splice(0, state.songList.length);
    },
    setChange (state, flag) {
        state.change = flag;
    },
    setLocation (state, location) {
        state.audio.location = location;
    },
    setLrc (state, lrc) {
        state.lyric = lrc;
    },
    setPlayType (state) {
        if (state.playType < 3) {
            state.playType++;
        } else {
            state.playType = 1;
        }
    },
    updateCurrentTime (state, time) {
        state.currentTime = time;
    },
    updateDurationTime (state, time) {
        state.durationTime = time;
    },
    updateBufferedTime (state, time) {
        state.bufferedTime = time;
    },
    changeTime (state, time) {
        state.tmpCurrentTime = time;
    },
    openLoading (state) {
        state.loading = true;
    },
    closeLoading (state) {
        state.loading = false;
    },
    resetAudio (state) {
        state.currentTime = 0;
    },
    playNext (state) { // 播放下一曲
        state.currentIndex++;
        if (state.currentIndex > state.songList.length) {
            state.currentIndex = 1;
        }
        state.audio = state.songList[state.currentIndex - 1];
    },
    playPrev (state) { // 播放上一曲
        state.currentIndex--;
        if (state.currentIndex < 1) {
            state.currentIndex = state.songList.length;
        }
        state.audio = state.songList[state.currentIndex - 1];
    },
    addToList (state, song) {
        let items = Array.prototype.concat.call(song);
        items.forEach(item => {
            let flag = false;
            state.songList.forEach((element:any, index:number)=> { // 检测歌曲重复
                if (element.id === item.id) {
                    flag = true;
                    state.currentIndex = index + 1;
                }
            });
            if (!flag) {
                // state.songList.push(item);
                state.currentIndex = state.songList.length;
            }
        });
    },
    addAllToList (state, songs) {
        state.songList = songs;
    }
},
    actions:{

    }
})
