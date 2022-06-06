const routes = [
    {
        name: 'mymusic',
        path: '/mymusic',
        component: () => import('../view/mymusic.vue'),
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
        component: () => import('../view/songlist.vue')
    },
    {
        name: 'userInfo',
        path: '/userInfo',
        component: () => import('../view/userInfo.vue')
    }

];

export default routes