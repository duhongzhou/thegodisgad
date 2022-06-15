import Vue from 'vue'
import App from './App.vue'
import router from './router'
Vue.config.productionTip = false
import axios from 'axios'
// import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import mavonEfitor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import 'bootstrap/dist/css/bootstrap.min.css'
Vue.use(mavonEfitor)
Vue.config.productionTip = false
axios.withCredentials = true
axios.defaults.withCredentials = true
axios.defaults.baseURL = 'http://10.90.246.218:8080/api'
axios.defaults.headers.post['Content-Type'] = 'application/json; charset=utf-8'
Vue.prototype.$axios = axios
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
