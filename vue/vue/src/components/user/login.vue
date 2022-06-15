<template>
  <!--  <div class="container">-->
  <!--    <div class="row-cols-3">-->
  <!--      <div class="col"></div>-->
  <!--      <div class="form_control">-->
  <!--        <label for="idetifier">用户名</label>-->
  <!--        <input type="text" name="idetifiter" id="idetifiter" placeholder="账号" class="col-12  form_control">-->
  <!--      </div>-->
  <!--      <div class=" form_control">-->
  <!--        <label for="certificate">密码</label>-->
  <!--        <input type="password" name="certificate" id="certificate" placeholder="账号" class="col-12  form_control">-->
  <!--      </div>-->
  <!--      <div class="form_control">-->
  <!--        <div class="border-left">-->
  <!--          <label for="verificationCode">验证码</label>-->
  <!--          <input type="password" name="verificationCode" id="verificationCode" placeholder="请输入验证码"-->
  <!--                 class="col-6  form_control">-->
  <!--        </div>-->
  <!--        <img src="http://10.90.246.218:8080/api/user/verificationCode" alt="服务器不在线">-->
  <!--      </div>-->
  <!--    </div>-->
  <!--  </div>-->
  <div class="container">
    <div class="form row">
      <form class="form-horizontal col-sm-offset-2 col-md-offset-2">
        <h3 class="form-title">登录</h3>
        <div class="col-sm-12 col-md-12">
          <div class="form-group">
            <!--        用户名图标和用户名输入框            -->
            <i aria-hidden="true" class="fa fa-user"></i>
            <input id="identifier" v-model="identifier" autofocus class="form-control" name="identifier"
                   placeholder="请输入用户名" required type="text">
          </div>
          <!--        密码图标和密码输入框                -->
          <div class="form-group">
            <i aria-hidden="true" class="fa fa-key"></i>
            <input id="certificate" v-model="certificate" class="form-control " name="certificate" placeholder="请输入密码"
                   required type="password">
          </div>
          <!--          <div class="form-group">-->
          <!--            <i class="fa fa-key" aria-hidden="true"></i>-->
          <!--            <input class="form-control pull-right" v-model="code" type="text" name="code" id="code" placeholder="请输入验证码"-->
          <!--                   required>-->
          <!--            <img src="#" id="img" @click="getcode" class="pull-left" alt="服务器不在线">-->
          <!--          </div>-->
          <!--        用超链接跳转到注册页面               -->
          <div class="form-group">
            <br>
            <a href="registered">没有账号？立即注册</a>
          </div>
          <!--         登录按钮           -->
          <div class="form-group">
            <input class="btn btn-success pull-right" type="button" value="登录" @click="login">
          </div>

        </div>
      </form>
    </div>
  </div>
</template>

<script>


export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'login',
  created () {
  },
  mounted: function () {
    this.getcode()
  },
  data () {
    return {
      identifier: '',
      certificate: '',
      code: '',
      temporaryToken: ''
    }
  },
  methods: {
    login: function () {
      this.$axios.post('/user/login/post',
        {
          identifier: this.identifier,
          certificate: this.certificate,
        },
        {
          withCredentials: true,
          headers: {
            withCredentials: true,
            data_type: 'application/json',
            content_type: 'application',
            Access_Control_Allow_Methods: '*'
          }
        }).then(function (response) {
        if (response.data.code == '-1') {
          localStorage.setItem('token', response.data.result)
          var item = localStorage.getItem('token')
          console.log(item)
          alert(response.data.message)
          window.location.href = '/'
        } else {
          alert('登录失败')
        }
      }).catch(function (reason) {
        console.log(reason)
        alert(reason)
      })
    }
  }
}

</script>
<style>

/*设置背景图片*/
body {
  /*background: url(../img/carback.jpg) no-repeat;*/
  background-size: cover;
  font-size: 15px;
}

/*给表单的边框设置大小,颜色和不透明度*/
.form {
  background: rgba(255, 255, 255, 0.9);
  width: 400px;
  margin: 50px auto;
}

/*设置字体和大小*/
label {
  font-family: Arial;
  font-size: 15px
}

/*当输入正确时,给输入框设置浅绿色*/
input.form-control:valid {
  background-color: mintcream;
}

/*当输入正确时,给输入框设置浅红色*/
input.form-control:invalid {
  background-color: #ffeeed;
}

/*设置图标为行内元素，并设置大小和颜色*/
.fa {
  display: inline-block;
  top: 27px;
  left: 6px;
  position: relative;
  color: #575757;
}

/*为几个输入框设置padding,防止和图标重合*/
input[type="text"], input[type="password"], input[type="email"] {
  padding-left: 26px;
}

.form-title {
  padding-top: 20px;
}

#button {
  padding-bottom: 50px;
}
</style>
