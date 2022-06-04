const routes = [
    {
        name: 'mymusic',
        path: '/mymusic',
        component: () => import('../view/mymusic.vue')
    },
    {
        name: 'header',
        path: '/header',
        component: () => import('../view/header.vue')
    },
    {
        name:'search',
        path:'/search',
        component: () => import('../view/search.vue')
    },
    {
        name:'songlist',
        path:'/songlist',
        component: () => import('../view/songlist.vue')
    }
    
];
 
export default routes