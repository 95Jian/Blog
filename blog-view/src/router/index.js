import Vue from 'vue'
import VueRouter from 'vue-router'
import Index from "@/views/Index";
import Home from "@/views/home/Home";
import Archives from "@/views/archives/Archives";
import Blog from "@/views/blog/Blog";
import Tag from "@/views/tag/Tag";
import Category from "@/views/category/Category";
import Moments from "@/views/moments/Moments";
import About from "@/views/about/About";
import Login from "@/views/Login";

Vue.use(VueRouter)

const routes = [{
        path: '/login',
        component: Login,
        meta: {
            title: '登录'
        }
    },
    {
        path: '/',
        component: Index,
        redirect: '/home',
        children: [{
                path: '/home',
                name: 'home',
                component: Home,
                meta: {
                    title: '首页'
                }
            },
            {
                path: '/archives',
                name: 'archives',
                component: Archives,
                meta: {
                    title: '归档'
                }
            },
            {
                path: '/blog/:id',
                name: 'blog',
                component: Blog,
                meta: {
                    title: '博客'
                }
            },
            {
                path: '/tag/:name',
                name: 'tag',
                component: Tag,
                meta: {
                    title: '标签'
                }
            },
            {
                path: '/category/:name',
                name: 'category',
                component: Category,
                meta: {
                    title: '分类'
                }
            },
            {
                path: '/moments',
                name: 'moments',
                component: Moments,
                meta: {
                    title: '动态'
                }
            },
            {
                path: '/about',
                name: 'about',
                component: About,
                meta: {
                    title: '关于我'
                }
            }
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})



export default router