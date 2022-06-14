import request from "../config/request";
import {albums, banners, comment, dailySongs, playlist, recommend, result} from '../interface/songList'
import {useProfile} from "../interface/user";
import {SongUrl} from "../interface/song_url";
import {Song} from "../interface/song";


//推荐歌单
export async function recommendedPlaylist(limit: number = 8) {
  const {result} = await request.get<{
    result: result[]
  }>('/personalized?limit=' + limit)
  return (result).splice(0, 8)
}

//轮播图
export async function carousel(type: number = 0) {
  const {banners} = await request.get<{
    banners: banners[]
  }>('/banner?type=' + type)
  // 剪切前面6个图片
  return banners.splice(0, 4)
}

// 推荐音乐
export async function newMusic(limit: number = 10) {
  const {result} = await request.get<{
    result: result[]
  }>('/personalized/newsong', {limit: limit})
  return (result)
}

// 登录
export async function useLogin(phone: string, password: string) {
  return await request.get<{
    profile: useProfile,
    code: number,
    cookie: string,
    token: string
  }>('/login/cellphone', {phone: phone, password: password})
}

//验证是否登录
export async function verifyLogin() {
  return request.get<{
    data: {
      code: number
      profile: useProfile
    }
  }>('/login/status')
}

export async function signOut() {
  return request.get<{ code: number }>('/logout')
}

//每日推荐歌曲
export async function userSong() {
  const {data} = await request.get<{
    data: {
      dailySongs: dailySongs[]
    }
  }>('/recommend/songs')
  return data.dailySongs.splice(0, 15)
}

//每日推荐歌单
export async function userPlaylist() {
  const {recommend} = await request.get<{
    recommend: recommend[]
  }>('/recommend/resource')
  return recommend.splice(0, 10)
}

//推荐电台
export async function recommendedStations() {
  const {albums} = await request.get<{
    albums: albums[]
  }>('/album/newest')
  return albums
}

// 歌单全部歌曲
export async function playlistSong(id: number) {
  const {songs} = await request.get<{ songs: dailySongs[], }>('playlist/track/all', {id: id})
  return songs
}

//歌单作者详细信息
export async function playlistInformation(id: number) {
  const {playlist} = await request.get<{ playlist: playlist[] }>('/playlist/detail', {id: id})
  return playlist
}

export async function comment(id: number) {
  return request.get<{ data: comment[] }>('comment/playlist', {id: id})
}


export async function singerInformation(useId: number) {
  const {profile} = await request.get<{ profile: any }>('/user/detail', {uid: useId})
  console.log(profile)
  return profile
}

export async function userLevel(useId: number) {
  const {data} = await request.get<{
    data: {
      level: number
    }
  }>('/user/level', {useId: useId})
  return data
}

export async function personalPlaylist(uid: number) {
  const {playlist} = await request.get<{ playlist: playlist }>('/user/playlist', {uid: uid})
  return playlist
}

export async function useSongUrl(id: number) {
  const {data} = await request.get<{ data: SongUrl[] }>('/song/url', {id: id})
  return data[0]
}

export async function useDetail(id: number) {
  const {songs} = await request.get<{ songs: Song[] }>('/song/detail', {ids: id})
  return songs.first()
}

export async function download(id: number) {
  return request.get('/song/download/url', {id: id})
}
