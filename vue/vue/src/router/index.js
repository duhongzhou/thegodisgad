/* jshint esversion: 6 */
import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/index'


import Login from '@/components/user/login'
import Registered from '@/components/user/registered'
import Information from '@/components/user/information'
import Teacher from '@/components/school/teacher'
// eslint-disable-next-line camelcase
import Show_school from '@/components/school/show_school'
// eslint-disable-next-line camelcase
import School_management from '@/components/school/school_management'
// eslint-disable-next-line camelcase
import Add_blog from '@/components/blog/add_blog'
// eslint-disable-next-line camelcase
import Show_blog from '../components/blog/show_blog'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [{
    path: '/',
    name: 'index',
    component: Index
  },
  {
    path: '/user/login',
    name: 'login',
    component: Login
  }, {
    path: '/user/registered',
    name: 'registered',
    component: Registered
  }, {
    path: '/user/information',
    name: 'Information',
    component: Information
  }, {
    path: '/school/teacher',
    name: 'teacher',
    component: Teacher
  }, {
    path: '/show_school',
    name: 'show_school',
    component: Show_school
  }, {
    path: '/school/management',
    name: 'school_management',
    component: School_management
  }, {
    path: '/blog/add',
    name: 'add_blog',
    component: Add_blog
  }, {
    path: '/blog/show',
    name: 'show_blog',
    component: Show_blog
  }
  ]
}
)
