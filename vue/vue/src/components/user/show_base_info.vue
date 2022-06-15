<template>
  <div class="container">

    <img :src="'http://10.90.246.218:8080/api/file/request/'+headPortrait+'?token='+token"
         alt="..." class="rounded img-thumbnail mx-auto d-block">
    <table class="table">
      <thead class="row-cols-2">
      <th class="col-2">基本信息</th>
      <th class="col-10"></th>
      </thead>
      <tbody>
      <tr>
        <th>
          <label class="form-lable" for="nickName">昵称</label>
        </th>
        <td>
          <div class="input-group">
            <input id="nickName" v-model="nickName" aria-describedby="nickNamesHelpBlock"
                   class="form-control border "
                   type="text"
                   v-bind:class="{'rounded-pill':nickNames>1,'border-success':nickNames==1,'border-warning':nickNames==3}"
                   v-bind:readonly="nickNames==1"
                   @dblclick="nickNames=2"
                   @input="nickNames=3">
            <div v-if="nickNames>=2">
              <button :class="{'btn-outline-warning':nickNames==3}" :disabled="nickNames!=3" class="btn " type="button"
                      @click="update_nickName">修改
              </button>
              <button class="btn btn-outline-secondary" type="reset" @click="reset_nickName"
              >取消
              </button>
            </div>
          </div>
        </td>
      <tr>
        <th>
          <label class="form-lable" for="userName">账号</label>
        </th>
        <td>
          <div class="input-group">

            <span class="form-control text-left">{{ userName }}</span>
          </div>
        </td>
      <tr>
        <th>
          <label class="form-lable" for="mobile">手机</label>
        </th>
        <td>
          <div class="input-group">
            <input id="nickName" v-model="mobile" aria-describedby="mobilesHelpBlock"
                   class="form-control border"
                   type="text"
                   v-bind:class="{'rounded-pill':mobiles>1,'border-success':mobiles==1,'border-warning':mobiles==3}"
                   v-bind:readonly="mobiles==1"
                   @dblclick="mobiles=2"
                   @input="mobiles=3">
            <div v-if="mobiles>=2">
              <button :class="{'btn-outline-warning':mobiles==3}" :disabled="mobiles!=3" class="btn " type="button"
                      @click="update_mobile">修改
              </button>
              <button class="btn btn-outline-secondary" type="reset" @click="reset_mobile"
              >取消
              </button>
            </div>
          </div>
        </td>
      <tr>
        <th>
          <label class="form-lable" for="email">邮箱</label>
        </th>
        <td>
          <div class="input-group">
            <input id="nickName" v-model="email" aria-describedby="emailsNamesHelpBlock"
                   class="form-control border"
                   type="text"
                   v-bind:class="{'rounded-pill':emails>1,'border-success':emails==1,'border-warning':emails==3}"
                   v-bind:readonly="emails==1"
                   @dblclick="emails=2"
                   @input="emails=3">
            <div v-if="emails>=2">
              <button :class="{'btn-outline-warning':emails==3}" :disabled="emails!=3" class="btn " type="button"
                      @click="update_email">修改
              </button>
              <button class="btn btn-outline-secondary" type="reset" @click="reset_email"
              >取消
              </button>
            </div>
          </div>
        </td>
      </tr>
      <tr>
        <th>性别</th>
        <td>
          <div class="input-group border border-success">
            <div v-if="genders==1" class="form-control text-left" @dblclick="genders=2">
              <span v-if="gender==1">男</span>
              <span v-else>女</span>
            </div>
            <div v-else class="form-check form-check-inline">

              <label class="form-check-label" for="man"><input id="man" v-model="gender"
                                                               class="form-check-input"
                                                               name="geder" type="radio" value="1">男</label>
              <label class="form-check-label" for="woman"><input id="woman" v-model="gender"
                                                                 class="form-check-input"
                                                                 name="geder" type="radio"
                                                                 value="0"
              >女</label>

              <div v-if="genders>=2">
                <button :class="{'btn-outline-warning':gender!=user.gender}" :disabled="gender==user.gender"
                        class="btn "
                        type="button"
                        @click="update_gender">修改
                </button>
                <button class="btn btn-outline-secondary" type="reset" @click="reset_gender"
                >取消
                </button>
              </div>
            </div>
          </div>
        </td>
      </tr>
      <tr>
        <th>密码</th>
        <td>
          <div>
            <div v-if="certificates==1" class="input-group text-left" @dblclick="certificates=2">
              <span>修改密码</span>
            </div>
            <div v-else class="input-group">
              <span class="input-group-text">旧密码</span>
              <input v-model="oldCertificate" class="form-control" type="password">
              <span class="input-group-text">新密码</span>
              <input v-model="certificate" class="form-control" type="password">
              <span class="input-group-text">确认密码</span>
              <input v-model="checkCertificate" class="form-control" type="password">
              <button
                :class="{'btn-outline-warning':checkCertificates()}"
                :disabled="!checkCertificates()"
                class="btn"
                type="button"
                @click='update_certificate'>修改
              </button>
              <button class="btn btn-outline-secondary" type="reset" @click="reset_certificate"
              >取消
              </button>
            </div>
          </div>

        </td>
      </tr>
      </tbody>

    </table>
  </div>
</template>

<script>

// eslint-disable-next-line no-unused-vars

export default {
  name: 'show_base_info',

  mounted () {
    this.get_information()
  },
  data: function () {
    return {
      token: localStorage.getItem('token'),
      userId: 'userId',
      nickName: 'nickName',
      nickNames: 1,
      userName: 'userName',
      userNames: 1,
      mobile: 'mobile',
      mobiles: 1,
      email: 'email',
      emails: 1,
      birthday: 'birthday',
      birthdays: 1,
      gender: 'gender',
      genders: 1,
      headPortrait: 1,
      user: {},
      certificates: 1,
      certificate: '',
      checkCertificate: '',
      oldCertificate: ''
    }
  },
  methods: {
    setdate: function (data) {
      if (data !== null || data !== undefined) {
        if (data.code == '-1') {
          const user = data.result
          // console.log(user)
          this.user = user
          this.nickName = user.nickName
          this.userName = user.userName
          this.email = user.email
          this.mobile = user.mobile
          this.gender = user.gender
          this.birthday = user.birthdays
          this.headPortrait = user.headPortrait
        }
      }
    },
    get_information: function () {
      this.$axios.post('user/information/show', {}, {
        params: {
          token: localStorage.getItem('token')
        }
      }).then(response => {
        if (response.data.code == '-1') {
          this.setdate(response.data)
        } else {
          alert(response.data.message)
        }
      })
    },
    update_nickName: function () {
      this.$axios.post('/user/information/update', {nickName: this.nickName}, {
        params: {
          token: localStorage.getItem('token')
        }
      }).then(response => {
        var date = response.data
        if (date.code == '-1') {
          this.setdate(response.data)
          this.mobiles = 1
        }
        alert(response.data.message)
      })
    },
    reset_nickName: function () {
      this.nickNames = 1
      this.nickName = this.user.nickName
    },
    update_mobile: function () {
      this.$axios.post('/user/information/update', {mobile: this.mobile}, {
        params: {
          token: localStorage.getItem('token')
        }
      }).then(response => {
        var date = response.data
        if (date.code == '-1') {
          this.setdate(response.data)
          this.mobiles = 1
        }
        alert(response.data.message)
      })
    },
    reset_mobile: function () {
      this.mobiles = 1
      this.mobile = this.user.mobile
    },
    update_email: function () {
      var p = new RegExp('^[\\S\\d]*@\\S*\\.[a-z]{2,5}')
      if (p.test(this.email)) {
        this.$axios.post('/user/information/update', {email: this.email}, {
          params: {
            token: localStorage.getItem('token')
          }
        }).then(response => {
          var date = response.data
          if (date.code == '-1') {
            this.setdate(response.data)
            this.emails = 1
          }
          alert(response.data.message)
        })
      } else {
        alert('请输入正确的邮箱')
      }
    },
    reset_email: function () {
      this.emails = 1
      this.email = this.user.email
    },
    update_gender: function () {
      this.$axios.post('/user/information/update', {gender: this.gender}, {
        params: {
          token: localStorage.getItem('token')
        }
      }).then(response => {
        var date = response.data
        if (date.code == '-1') {
          this.setdate(response.data)
          this.genders = 1
        }
        alert(response.data.message)
      })
    },
    reset_gender: function () {
      this.genders = 1
      this.gender = this.user.gender
    },
    update_certificate: function () {
      this.$axios.post('/user/information/update', {
        oldCertificate: this.oldCertificate,
        certificate: this.certificate
      }, {
        params: {
          token: localStorage.getItem('token')
        }
      }).then(response => {
        alert(response.data.message)
        if (response.data.code == '-1') {
          window.location.href = '/user/login'
        }
      })
    },
    reset_certificate: function () {
      this.certificate = ''
      this.checkCertificate = ''
      this.oldCertificate = ''
      this.certificates = 1
    },
    checkCertificates: function () {
      // console.log(this.checkCertificate === this.certificate && this.certificate !== '' && this.oldCertificate !== '')
      if (this.oldCertificate == '') {
        return false
      }
      if (this.certificate == '') {
        return false
      }
      if (this.checkCertificate == '') {
        return false
      }
      if (this.certificate != this.checkCertificate) {
        return false
      }
      return true
    }
  }

}
</script>

<style scoped>

</style>
