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
    
];
 
export default routes