<template>
  <div>
    <div class="header navbar-fixed-top container-fluid">
      <div class="nav justify-content-center row-cols-4">
        <a class="navbar-brand col-4" href="/">TheGodIsGad</a>
        <div class="nav-item col-2">
          <button :class="{'bg-success text-light':navit===1}"
                  class="btn btn-outline-success"
                  type="button"
                  @click="navit=1">
            学校信息
          </button>
        </div>
        <div class="nav-item col-2">
          <button :class="{'bg-success text-light':navit===2}"
                  class="btn btn-outline-success"
                  type="button"
                  @click="navit=2">
            开设学院
          </button>
        </div>

      </div>
      <div v-if="navit===1" class="container">
        <div id="show_school_information">
          <table class="table">
            <thead>
            <tr class="row-cols-2">
              <th class="col-2"></th>
              <th class="col-10">{{ school.schoolName }}</th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <th>学校代码</th>
              <td>
                <div class="input-group">
                  <span class="form-control border-0">{{ school.schoolCode }}</span>
                </div>
              </td>
            </tr>
            <tr>
              <th>主管部门</th>
              <td>
                <div class="input-group">
                  <span class="form-control border-0">{{ school.competentDepartment }}</span>
                </div>
              </td>
            </tr>
            <tr>
              <th>省市</th>
              <td>
                <div class="input-group">
                  <span class="form-control border-0">{{ school.provincesCities }}</span>
                </div>
              </td>
            </tr>
            <tr>
              <th>所在地</th>
              <td>
                <div class="input-group">
                  <span class="form-control border-0">{{ school.location }}</span>
                </div>
              </td>
            </tr>
            <tr>
              <th>办学层次</th>
              <td>
                <div class="input-group">
                  <span class="form-control border-0">{{ school.educationLevel }}</span>
                </div>
              </td>
            </tr>
            <tr>
              <th>简介</th>
              <td>
                <div class="input-group">
                  <textarea :value="school.note" class="form-control" readonly></textarea>
                </div>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div v-else id="show_academy" class="container">
        <div class="row-cols-4 ">
          <button v-for="(ac,index) in academys" :key="index" :class="academys_class[index]" class="btn"
                  style="margin-right: 5px;margin-top: 10px;"
                  @click="set_academy(ac)">{{
              ac.academyName
            }}
          </button>
          <button class="btn btn-outline-dark" style="margin-right: 5px;margin-top: 10px;"
                  @click="()=>{this.academy={};}">重置
          </button>
        </div>
        <div v-if="JSON.stringify(academy)!='{}'" class="show_academy">
          <table class="table">
            <thead>
            <tr class="row-cols-2">
              <th class="col-2"></th>
              <th class="col-10">{{ academy.academyName }}</th>
            </tr>
            </thead>
            <tbody>
            <tr :key="index" v-for="(pro,index) in this.academy.professionals">
              <th>{{ pro.professionalCode }}</th>
              <td>
                <div class="input-group">
                <span class="form-control">
              {{ pro.professionalName }}
                </span>
                </div>
              </td>
            </tr>
            <tr>
              <th>简介</th>
              <td>
                <div class="input-group">
                  <textarea :value="academy.note" class="form-control" readonly></textarea>
                </div>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  name: 'show_school',
  created () {
    this.get_school_id()
  },
  data: function () {
    return {
      academy: {},
      academys: [],
      academys_class: [],
      navit: 1,
      schoolId: 0,
      school: {}
    }
  },
  methods: {
    radom: function () {
      this.academys_class = []
      if (this.academys != null) {
        for (var i = 0; i < this.academys.length; i++) {
          var num = Math.floor(Math.random() * 100) % 6
          switch (num) {
            case 0:
              this.academys_class.push('btn-outline-info  text-dark')
              break
            case 1:
              this.academys_class.push('btn-outline-primary  text-dark')
              break
            case 2:
              this.academys_class.push('btn-outline-secondary  text-dark')
              break
            case 3:
              this.academys_class.push('btn-outline-success  text-dark')
              break
            case 4:
              this.academys_class.push('btn-outline-warning  text-dark')
              break
            case 5:
              this.academys_class.push('btn-outline-danger  text-dark')
              break
          }
        }
      }
    },
    set_academy: function (ac) {
      this.academy = ac

      this.add_academy = 1
    },
    get_academy: function () {
      this.$axios.get('/openingProfessional/school', {
        params: {
          token: localStorage.getItem('token'),
          schoolId: this.schoolId
        }
      }).then(response => {
        if (response.data.code == '-1') {
          this.academys = response.data.result
          this.radom()
          //  console.log(this.academys)
        }
      })
    },
    get_school_id: function () {
      var schoolId = this.$route.query.school_id
      if (schoolId === undefined) {
        // TODO 跳转到首页
        console.log('跳转到首页')
        // window.location.href = '/'
      } else {
        let nums = new RegExp('^\\d*$')
        if (nums.test(schoolId)) {
          this.schoolId = schoolId
          this.get_school_info()
          // alert(this.schoolId)
        } else {
          console.log('跳转到首页')
          // window.location.href = '/'
        }
      }
    },
    get_school_info: function () {
      this.$axios.get('/school/get_by_school_id', {
        params: {
          token: localStorage.getItem('token'),
          schoolId: this.schoolId
        }
      }).then(response => {
        let data = response.data
        // alert(data)
        if (data.code === '-1') {
          this.school = data.result
          this.get_academy()
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
