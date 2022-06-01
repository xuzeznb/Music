const routes = [
    {
        name: 'content',
        path: '/content',
        component: () => import('../view/content.vue')
    },
    {
        name: 'header',
        path: '/header',
        component: () => import('../view/header.vue')
    },
    
];
 
export default routes