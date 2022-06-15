<template>
  <div class="container">
    <div class="form row">
      <form class="form-horizontal col-sm-offset col-md-offset-3">
        <h3 class="form-title">注册</h3>
        <div class="col-sm-12 col-md-12">
          <div class="form-group">
            <!--        用户名图标和用户名输入框            -->

            <!--            <span id="basic-addon1" class="form-group-text">账号</span>-->
            <input id="userName" class="form-control required" name="userName" placeholder="请输入用户名" required
                   type="text">
          </div>

          <!--        密码图标和密码输入框                -->
          <div class="form-group">

            <!--            <span id="basic-addon1" class="form-group-text">密码</span>-->
            <input id="certificate" class="form-control required" name="certificate" placeholder="请输入密码" required
                   type="password">
          </div>
          <!--          确认密码              -->
          <div class="form-group">

            <!--            <label id="basic-addon1" class="from-label col-form-label">确认密码</label>-->
            <input id="oldCertificate" class="form-control required" name="oldCertificate" placeholder="请确认密码"
                   required
                   type="password">
          </div>
          <div class="form-group">
            <!--        用户名图标和用户名输入框            -->

            <input id="nickName" class="form-control required" name="nickName" placeholder="请输入昵称" required
                   type="text">
          </div>
          <!--注册按钮-->
          <div class="form-group">
            <input class="btn btn-secondary" type="button" value="注册" @click="register">
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import $ from 'jquery'

export default {
  methods: {
    register: function () {
      var serializeArray = $('form').serializeArray()
      var obj = {}
      $.each(serializeArray, function (i, s) {
        obj[s.name] = s.value
      })
      $.ajax(
        {
          url: 'http://10.90.246.218:8080/api/user/registered',
          type: 'POST',
          dataType: 'json',
          contentType: 'application/json;charset=utf-8',
          data: JSON.stringify(obj),
          success: function (response) {
            console.log(response.code)
            if (response.code == -1) {
              alert(response.message)
              window.location.href = 'login'
            } else {
              alert(response.message)
              console.log(response)
            }
          },
          error: function (data) {
            alert('失败'+data)
          }
        }
      )
    }
  },
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'registered'
}
</script>

<style lang="css" scoped>
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
