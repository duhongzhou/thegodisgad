<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="col-2">
          <select v-if="user===null" v-model="refs" class="form-control rounded-pill btn btn-outline-info"
                  @change="se_hrf">

            <option selected value='http://localhost:8081/user/login'>登录</option>
            <option value='http://localhost:8081/user/registered'>注册</option>
          </select>
          <select v-else v-model="refs" class="form-control rounded-pill btn btn-outline-info" @change="se_hrf">
            <option selected value='http://localhost:8081/user/information'>个人中心</option>
            <option selected value='http://localhost:8081/blog/add'>创作博客</option>
            <option value="http://10.90.246.218:8080">退出登录</option>
              <hr class="dropdown-divider">
              <option v-if="teacher!==null" value='http://localhost:8081/school/management'>学校管理</option>
          </select>
        </div>
      </div>

    </div>
    <view_blog></view_blog>
  </div>
</template>

<script>
// eslint-disable-next-line camelcase
import View_blog from './blog/view_blog.vue'

export default {

  // eslint-disable-next-line vue/multi-word-component-names
  name: 'index',
  created () {
    this.token = localStorage.getItem('token')
    if (this.token === null) {
      alert('请登录')
    } else {
      this.get_user_info()
      this.is_teacher()
    }
  },
  components: {
    // eslint-disable-next-line vue/no-unused-components
    View_blog
  },
  data: function () {
    return {
      token: '',
      user: null,
      teacher: null,
      refs: '#'
    }
  },
  methods: {
    logout () {
      this.$axios.get('/user/logout', {params: {token: this.token}}).then(response => {
        if (response.data.code === '-1') {
          this.teacher = null
          this.user = null
        }
        alert(response.data.message)
      })
    },
    se_hrf () {
      var re =new RegExp('8080')
      if(re.test(this.refs))
      {
        this.logout()
      }else{
      window.location.href = this.refs
      }
    },
    is_teacher () {
      this.$axios.get('/teacher/is', {
        params: {
          token: this.token
        }
      }).then(response => {
        console.log(response.data)
        if (response.data.code === '-1') {
          this.teacher = response.data.result
        }
      })
    },
    get_user_info () {
      this.$axios.get('/user/information/show', {
        params: {
          token: this.token
        }
      }).then(response => {
        if (response.data.code === '-1') {
          this.user = response.data.result
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
