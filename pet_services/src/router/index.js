import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/Login.vue'
import Register from '@/views/Register.vue'
import UserHome from '@/views/UserHome.vue'
import AdminHome from '@/views/AdminHome.vue'
import User from '@/components/admin/User.vue'
import Welcome from '@/components/admin/Welcome.vue'
import Admin from '@/components/admin/Admin.vue'
import Category from '@/components/admin/Category.vue'
import Serve from '@/components/admin/Serve.vue'
import Goods from '@/components/admin/Goods.vue'
import Booking from '@/components/admin/Booking.vue'
import Order from '@/components/admin/Order.vue'
import Square from '@/components/user/Square.vue'
import PetServices from '@/components/user/PetServices.vue'
import PetGoods from '@/components/user/PetGoods.vue'
import MyBooking from '@/components/user/MyBooking.vue'
import MyOrder from '@/components/user/MyOrder.vue'
import ChangePassword from '@/components/ChangePassword.vue'
import MyInfo from '@/components/user/MyInfo.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: '默认登录页',
    component: Login
  },
  {
    path: '/login',
    name: '登录页',
    component: Login
  },
  {
    path: '/register',
    name: '注册',
    component: Register
  },
  {
    path: '/userHome',
    name: '用户页面',
    component: UserHome,
    redirect: '/userHome/square',
    children: [
      {
        path: 'square',
        component: Square,
        meta: {
          title: '萌宠广场'
        }
      },
      {
        path: 'petServices',
        component: PetServices,
        meta: {
          title: '宠物服务'
        }
      },
      {
        path: 'petGoods',
        component: PetGoods,
        meta: {
          title: '宠物用品'
        }
      },
      {
        path: 'myBooking',
        component: MyBooking,
        meta: {
          title: '我的预约'
        }
      },
      {
        path: 'myOrder',
        component: MyOrder,
        meta: {
          title: '我的订单'
        }
      },
      {
        path: 'changePassword',
        component: ChangePassword,
        meta: {
          title: '用户更改密码'
        }
      },
      {
        path: 'myInfo',
        component: MyInfo,
        meta: {
          title: '我的信息'
        }
      },
    ]
  },
  {
    path: '/adminHome',
    name: '管理员页面',
    component: AdminHome,
    redirect: '/adminHome/welcome',
    children: [
      {
        path: 'welcome',
        component: Welcome,
        meta: {
          title: '首页'
        }
      },
      {
        path: 'user',
        component: User,
        meta: {
          title: '用户管理'
        }
      },
      {
        path: 'admin',
        component: Admin,
        meta: {
          title: '管理员管理'
        }
      },
      {
        path: 'category',
        component: Category,
        meta: {
          title: '分类管理'
        }
      },
      {
        path: 'serve',
        component: Serve,
        meta: {
          title: '服务管理'
        }
      },
      {
        path: 'goods',
        component: Goods,
        meta: {
          title: '商品管理'
        }
      },
      {
        path: 'booking',
        component: Booking,
        meta: {
          title: '预约管理'
        }
      },
      {
        path: 'order',
        component: Order,
        meta: {
          title: '订单管理'
        }
      },
      {
        path: 'changePassword',
        component: ChangePassword,
        meta: {
          title: '更改密码'
        }
      },
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

const originalPush = VueRouter.prototype.push
// 解决ElementUI导航栏中的vue-router在3.0版本以上重复点菜单报错问题
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

//路由守卫
router.beforeEach((to, from, next) => {
  const pathArr = ['/adminHome', '/userHome']
  const token = localStorage.user
  const token2 = localStorage.admin
  if (pathArr.indexOf(to.path) != -1) {
    if (token || token2) {
      next()
    } else {
      //解决重复路由报错 将错误抛出
      router.push({
        path: '/login'
      }
      ).catch(err => { })
    }
  } else {
    next()
  }
})

export default router
