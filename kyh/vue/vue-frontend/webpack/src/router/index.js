import { createRouter, createWebHistory } from 'vue-router';
import Signup from '@/components/SignUp.vue';
import HelloWorld from '@/components/HelloWorld.vue';

const routes = [
    { path: '/', component: HelloWorld },
    { path: '/signup', component: Signup }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

export default router;
