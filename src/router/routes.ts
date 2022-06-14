const routes = [
  {
    path: '/',
    redirect: '/recommend',
  },

  {
    name: 'mymusic',
    path: '/mymusic',
    component: () => import('../view/mymusic.vue'),
    meta: {
      menu: '主页',
      keepAlive: true,
    }
  },
  {
    name: 'album',
    path: '/album',
    component: () => import('../view/album.vue')
  },
  {
    name: 'search',
    path: '/search',
    component: () => import('../view/search.vue')
  },
  {
    name: 'songlist',
    path: '/songlist',
    component: () => import('../view/songlist.vue'),
    meta: {
      menu: '歌单列表',
      keepAlive: true,
    }
  },
  {
    name: 'userInfo',
    path: '/userInfo',
    component: () => import('../view/userInfo.vue')
  },
  {
    name: 'singer',
    path: '/singer',
    component: () => import('../view/singer.vue')
  },
  {
    name: 'recommend',
    path: '/recommend',
    component: () => import('../view/recommend.vue'),
    meta: {
      menu: '推荐',
      keepAlive: true,
    }
  }

];

export default routes
